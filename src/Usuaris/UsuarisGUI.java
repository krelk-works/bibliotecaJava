package Usuaris;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connexio.Connexio;

public class UsuarisGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public UsuarisGUI() {
        setTitle("Gestió d'Usuaris");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Botó per afegir un nou usuari
        JButton btnAddUser = new JButton("Afegir Usuari");
        btnAddUser.setPreferredSize(new Dimension(1024, 30));
        btnAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AfegirUsuari(UsuarisGUI.this).setVisible(true);
            }
        });
        panel.add(btnAddUser, BorderLayout.NORTH);

        // Crear la taula per llistar els usuaris
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nom", "Cognoms", "Rol", "Modificar", "Eliminar"}, 0);
        table = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return column == 4 || column == 5; // Només les columnes de Modificar i Eliminar són editables
            }
        };
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Nom
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Cognoms
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Rol
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Modificar
        table.getColumnModel().getColumn(5).setPreferredWidth(100); // Eliminar

        // Omplir la taula amb dades de la base de dades
        omplirTaulaUsuaris();

        // Afegir scroll a la taula
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private void omplirTaulaUsuaris() {
        tableModel.setRowCount(0); // Esborrar les files existents
        List<Usuari> usuaris = obtenirUsuarisDeLaBaseDeDades();
        for (Usuari usuari : usuaris) {
            tableModel.addRow(new Object[]{usuari.getId(), usuari.getNom(), usuari.getCognoms(), usuari.getRol(), "Modificar", "Eliminar"});
        }

        // Afegir funcionalitats als botons de la taula
        table.getColumn("Modificar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Modificar").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.getColumn("Eliminar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Eliminar").setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    public void actualitzarTaulaUsuaris() {
        omplirTaulaUsuaris();
    }

    private List<Usuari> obtenirUsuarisDeLaBaseDeDades() {
        List<Usuari> usuaris = new ArrayList<>();
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Usuaris ORDER BY Nom ASC");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Usuari usuari = new Usuari(
                        resultSet.getInt("ID_Usuari"),
                        resultSet.getString("Nom"),
                        resultSet.getString("Cognoms"),
                        resultSet.getString("Email"),
                        resultSet.getString("Telèfon"),
                        resultSet.getString("Rol"),
                        resultSet.getDate("Data_Registre")
                );
                usuaris.add(usuari);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuaris;
    }

    // Classe per renderitzar els botons a la taula
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true); // Assegura que el botó sigui opac
        }

        // Configura l'aparença del botó en funció de les dades de la cel·la
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Classe per editar les cel·les de la taula que contenen botons
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;

        // Inicialitza el botó i configura l'acció d'escolta
        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        // Retorna el component editor per a la cel·la
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        // Retorna el valor de l'editor
        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                final int selectedRow = table.getSelectedRow();
                if (selectedRow < 0 || selectedRow >= tableModel.getRowCount()) {
                    isPushed = false;
                    return label;
                }

                final int usuariId = (int) tableModel.getValueAt(selectedRow, 0);
                final String usuariNom = (String) tableModel.getValueAt(selectedRow, 1);
                final String usuariCognoms = (String) tableModel.getValueAt(selectedRow, 2);

                if (label.equals("Modificar")) {
                    // Si es fa clic a "Modificar", obrim la finestra EditarUsuari
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new EditarUsuari(UsuarisGUI.this, usuariId).setVisible(true);
                        }
                    });
                } else if (label.equals("Eliminar")) {
                    // Si es fa clic a "Eliminar", mostrem un diàleg de confirmació
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Estás segur d'eliminar l'usuari " + usuariNom + " " + usuariCognoms + "?",
                            "Confirmar eliminació",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        // Si es confirma l'eliminació, eliminem l'usuari de la base de dades i de la taula
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                eliminarUsuariDeLaBaseDeDades(usuariId);
                                tableModel.removeRow(selectedRow);
                            }
                        });
                    }
                }
            }
            isPushed = false;
            return label;
        }

        // Atura l'edició de la cel·la
        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        // Finalitza l'edició i actualitza la vista
        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }

        // Elimina l'usuari de la base de dades
        private void eliminarUsuariDeLaBaseDeDades(int usuariId) {
            try (Connection connection = Connexio.getConnection();
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM Usuaris WHERE ID_Usuari = ?")) {
                statement.setInt(1, usuariId);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
