package Llibres;

import Connexio.Connexio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaLlibres extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public ConsultaLlibres() {
        setTitle("Consulta de Llibres");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear la taula per llistar els llibres
        tableModel = new DefaultTableModel(new Object[]{"ID", "Títol", "Autor", "ISBN", "Editorial", "Any de Publicació", "Categoria"}, 0);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(200); // Títol
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Autor
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // ISBN
        table.getColumnModel().getColumn(4).setPreferredWidth(150); // Editorial
        table.getColumnModel().getColumn(5).setPreferredWidth(100); // Any de Publicació
        table.getColumnModel().getColumn(6).setPreferredWidth(150); // Categoria

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
        try (Connection connection = Connexio.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT ID_Llibre, Títol, Autor, ISBN, Editorial, Any_Publicació, Categoria FROM llibres WHERE Estat = 'disponible'");
            ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_Llibre");
                String titol = resultSet.getString("Títol");
                String autor = resultSet.getString("Autor");
                String isbn = resultSet.getString("ISBN");
                String editorial = resultSet.getString("Editorial");
                int anyPublicacio = resultSet.getInt("Any_Publicació");
                String categoria = resultSet.getString("Categoria");

                tableModel.addRow(new Object[]{id, titol, autor, isbn, editorial, anyPublicacio, categoria});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
