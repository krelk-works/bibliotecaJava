package Prestecs;

import Connexio.Connexio;
import Usuaris.Usuari;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class ConsultaPrestecs extends JFrame {
    private Usuari usuari;
    private JTable table;
    private DefaultTableModel tableModel;

    public ConsultaPrestecs(Usuari usuari) {
        this.usuari = usuari;

        setTitle("Consulta de Préstecs");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear la taula per llistar els préstecs
        tableModel = new DefaultTableModel(new Object[]{"Títol", "Autor", "Editorial", "Categoria", "Data Préstec", "Estat"}, 0);
        table = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                String estat = (String) getValueAt(row, 5);
                if ("retardat".equals(estat)) {
                    if (column == 5) {
                        c.setForeground(Color.RED);
                    }
                } else {
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        };
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(200); // Títol
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Autor
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Editorial
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Categoria
        table.getColumnModel().getColumn(4).setPreferredWidth(150); // Data Préstec
        table.getColumnModel().getColumn(5).setPreferredWidth(100); // Estat

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
                "SELECT l.Títol, l.Autor, l.Editorial, l.Categoria, p.Data_Préstec, p.Data_Retorn_Prevista, p.Data_Retorn_Real " +
                "FROM llibres l " +
                "INNER JOIN préstecs p ON l.ID_Llibre = p.ID_Llibre " +
                "WHERE p.ID_Usuari = ?"))
            {
            // Jorge debería estar orgulloso xd
            statement.setInt(1, usuari.getId());
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String titol = resultSet.getString("Títol");
                    String autor = resultSet.getString("Autor");
                    String editorial = resultSet.getString("Editorial");
                    String categoria = resultSet.getString("Categoria");
                    Date dataPrestec = resultSet.getDate("Data_Préstec");
                    Date dataRetornPrevista = resultSet.getDate("Data_Retorn_Prevista");
                    Date dataRetornReal = resultSet.getDate("Data_Retorn_Real");

                    String estat;
                    if (dataRetornReal == null) {
                        Date dataAvui = new Date(System.currentTimeMillis());
                        if (dataAvui.after(dataRetornPrevista)) {
                            estat = "retardat";
                        } else {
                            estat = "actiu";
                        }
                    } else {
                        if (dataRetornReal.after(dataRetornPrevista)) {
                            estat = "retardat";
                        } else {
                            estat = "completat";
                        }
                    }

                    tableModel.addRow(new Object[]{titol, autor, editorial, categoria, dataPrestec, estat});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
