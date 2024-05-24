package Llibres;

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

import Usuaris.Usuari;
import Connexio.Connexio;

@SuppressWarnings("unused")
public class LlibresGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public LlibresGUI() {
        setTitle("Gestió de Llibres");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Botó per afegir un nou llibre
        JButton btnAddBook = new JButton("Afegir Nou Llibre");
        btnAddBook.setPreferredSize(new Dimension(1024, 30));
        btnAddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AfegirLlibre(LlibresGUI.this).setVisible(true);
            }
        });
        panel.add(btnAddBook, BorderLayout.NORTH);

        // Crear la taula per llistar els llibres
        tableModel = new DefaultTableModel(new Object[]{"ID", "Títol", "ISBN", "Modificar", "Eliminar"}, 0);
        table = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return column == 3 || column == 4; // Només les columnes de Modificar i Eliminar són editables
            }
        };
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(300); // Títol
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // ISBN
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Modificar
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Eliminar

        // Omplir la taula amb dades de la base de dades
        omplirTaulaLlibres();

        // Afegir scroll a la taula
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private void omplirTaulaLlibres() {
        tableModel.setRowCount(0); // Esborrar les files existents
        List<Llibre> llibres = obtenirLlibresDeLaBaseDeDades();
        for (Llibre llibre : llibres) {
            tableModel.addRow(new Object[]{llibre.getId(), llibre.getTitol(), llibre.getIsbn(), "Modificar", "Eliminar"});
        }

        // Afegir funcionalitats als botons de la taula
        table.getColumn("Modificar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Modificar").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.getColumn("Eliminar").setCellRenderer(new ButtonRenderer());
        table.getColumn("Eliminar").setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    public void actualitzarTaulaLlibres() {
        omplirTaulaLlibres();
    }

    private List<Llibre> obtenirLlibresDeLaBaseDeDades() {
        List<Llibre> llibres = new ArrayList<>();
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM llibres ORDER BY Títol ASC");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Llibre llibre = new Llibre(
                        resultSet.getInt("ID_Llibre"),
                        resultSet.getString("Títol"),
                        resultSet.getString("Autor"),
                        resultSet.getString("ISBN"),
                        resultSet.getString("Editorial"),
                        resultSet.getInt("Any_Publicació"),
                        resultSet.getString("Categoria"),
                        resultSet.getString("Estat")
                );
                llibres.add(llibre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return llibres;
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

                final int llibreId = (int) tableModel.getValueAt(selectedRow, 0);
                final String llibreTitol = (String) tableModel.getValueAt(selectedRow, 1);

                if (label.equals("Modificar")) {
                    // Si es fa clic a "Modificar", obrim la finestra EditarLlibre
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new EditarLlibre(LlibresGUI.this, llibreId).setVisible(true);
                        }
                    });
                } else if (label.equals("Eliminar")) {
                    // Si es fa clic a "Eliminar", mostrem un diàleg de confirmació
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Estas segur en eliminar el llibre " + llibreTitol + "?",
                            "Confirmar eliminació",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        // Si es confirma l'eliminació, eliminem el llibre de la base de dades i de la taula
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                eliminarLlibreDeLaBaseDeDades(llibreId);
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

        // Elimina el llibre de la base de dades
        private void eliminarLlibreDeLaBaseDeDades(int llibreId) {
            try (Connection connection = Connexio.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM llibres WHERE ID_Llibre = ?")) {
                statement.setInt(1, llibreId);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
