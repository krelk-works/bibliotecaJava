package Prestecs;

import Connexio.Connexio;

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
import java.util.Date;

public class PrestecsGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public PrestecsGUI() {
        setTitle("Gestió de Préstecs");
        setSize(1440, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Botó per afegir un nou préstec
        JButton btnAddPrestec = new JButton("Nou préstec");
        btnAddPrestec.setPreferredSize(new Dimension(1024, 30));
        btnAddPrestec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí pots afegir la funcionalitat per a afegir un nou préstec
                //new AfegirPrestec(PrestecsGUI.this).setVisible(true);
                new NouPrestec(PrestecsGUI.this);
            }
        });
        panel.add(btnAddPrestec, BorderLayout.NORTH);

        // Crear la taula per llistar els préstecs
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nom Lector", "Cognoms Lector", "Títol Llibre", "ISBN", "Data Préstec", "Data Retorn Prevista", "Data Retorn Real", "Devolució"}, 0);
        table = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return column == 8; // Només la columna de Devolució és editable
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                Date dataRetornPrevista = (Date) getValueAt(row, 6);
                Date dataRetornReal = (Date) getValueAt(row, 7);

                // Condicions per pintar la columna de Data Retorn Prevista de vermell
                if (column == 6 && ((dataRetornReal == null && new Date().after(dataRetornPrevista)) || (dataRetornReal != null && dataRetornReal.after(dataRetornPrevista)))) {
                    c.setForeground(Color.RED);
                } else if ( column == 7 && dataRetornReal != null && !dataRetornReal.after(dataRetornPrevista)) {
                    c.setForeground(Color.GREEN);
                } else {
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        };
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Nom Lector
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Cognoms Lector
        table.getColumnModel().getColumn(3).setPreferredWidth(300); // Títol Llibre
        table.getColumnModel().getColumn(4).setPreferredWidth(150); // ISBN
        table.getColumnModel().getColumn(5).setPreferredWidth(100); // Data Préstec
        table.getColumnModel().getColumn(6).setPreferredWidth(100); // Data Retorn Prevista
        table.getColumnModel().getColumn(7).setPreferredWidth(100); // Data Retorn Real
        table.getColumnModel().getColumn(8).setPreferredWidth(100); // Devolució

        // Omplir la taula amb dades de la base de dades
        omplirTaulaPrestecs();

        // Afegir scroll a la taula
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private void omplirTaulaPrestecs() {
        tableModel.setRowCount(0); // Esborrar les files existents
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT p.ID_Préstec, u.Nom, u.Cognoms, l.Títol, l.ISBN, p.Data_Préstec, p.Data_Retorn_Prevista, p.Data_Retorn_Real " +
                             "FROM usuaris u " +
                             "INNER JOIN préstecs p ON p.ID_Usuari = u.ID_Usuari " +
                             "INNER JOIN llibres l ON l.ID_Llibre = p.ID_Llibre");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idPrestec = resultSet.getInt("ID_Préstec");
                String nomLector = resultSet.getString("Nom");
                String cognomsLector = resultSet.getString("Cognoms");
                String titolLlibre = resultSet.getString("Títol");
                String isbn = resultSet.getString("ISBN");
                Date dataPrestec = resultSet.getDate("Data_Préstec");
                Date dataRetornPrevista = resultSet.getDate("Data_Retorn_Prevista");
                Date dataRetornReal = resultSet.getDate("Data_Retorn_Real");

                tableModel.addRow(new Object[]{idPrestec, nomLector, cognomsLector, titolLlibre, isbn, dataPrestec, dataRetornPrevista, dataRetornReal, "Devolució"});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Afegir funcionalitats als botons de la taula
        table.getColumn("Devolució").setCellRenderer(new ButtonRenderer());
        table.getColumn("Devolució").setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    public void actualitzarTaulaPrestecs() {
        omplirTaulaPrestecs();
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

            final int prestecId = (int) tableModel.getValueAt(selectedRow, 0);
            if (label.equals("Devolució")) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new Devolucio(prestecId, PrestecsGUI.this);
                    }
                });
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
}
}
