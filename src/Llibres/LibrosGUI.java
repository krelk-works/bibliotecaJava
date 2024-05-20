package Llibres;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
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

public class LibrosGUI extends JFrame {
    private Usuari usuari;
    private JTable table;
    private DefaultTableModel tableModel;

    public LibrosGUI(Usuari usuari) {
        this.usuari = usuari;

        setTitle("Gestió de Llibres");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Panell superior amb informació de l'usuari i el nom de la biblioteca
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel lblUsuari = new JLabel("Usuari: " + usuari.getNom() + " " + usuari.getCognoms());
        JLabel lblBiblioteca = new JLabel("Biblioteca: Can Casacuberta", JLabel.RIGHT);
        topPanel.add(lblUsuari, BorderLayout.WEST);
        topPanel.add(lblBiblioteca, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(topPanel, BorderLayout.NORTH);

        // Botó per afegir un nou llibre
        JButton btnAddBook = new JButton("Afegir Nou Llibre");
        btnAddBook.setPreferredSize(new Dimension(1024, 30));
        btnAddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implementar funcionalitat per afegir un nou llibre
                System.out.println("Afegir Nou Llibre...");
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

    private List<Llibre> obtenirLlibresDeLaBaseDeDades() {
        List<Llibre> llibres = new ArrayList<>();
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Llibres");
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

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;

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

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                int selectedRow = table.getSelectedRow();
                int llibreId = (int) tableModel.getValueAt(selectedRow, 0);
                String llibreTitol = (String) tableModel.getValueAt(selectedRow, 1);

                if (label.equals("Modificar")) {
                    System.out.println("Modificar llibre amb ID " + llibreId);
                    // TODO: Implementar funcionalitat per modificar el llibre
                } else if (label.equals("Eliminar")) {
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Estas segur en eliminar el llibre " + llibreTitol + "?",
                            "Confirmar eliminació",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        System.out.println("Eliminar llibre amb ID " + llibreId);
                        // Eliminar la fila seleccionada de la base de dades i de la taula
                        eliminarLlibreDeLaBaseDeDades(llibreId);
                        tableModel.removeRow(selectedRow);
                    }
                }
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }

        private void eliminarLlibreDeLaBaseDeDades(int llibreId) {
            try (Connection connection = Connexio.getConnection();
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM Llibres WHERE ID_Llibre = ?")) {
                statement.setInt(1, llibreId);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
