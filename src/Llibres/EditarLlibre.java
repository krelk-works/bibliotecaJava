package Llibres;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Connexio.Connexio;

public class EditarLlibre extends JFrame {
    private int llibreId;
    private JTextField txtTitol;
    private JTextField txtAutor;
    private JTextField txtISBN;
    private JTextField txtEditorial;
    private JTextField txtAnyPublicacio;
    private JTextField txtCategoria;
    private JComboBox<String> cmbEstat;
    private JButton btnActualitzar;

    public EditarLlibre(LlibresGUI llibresGUI, int llibreId) {
        this.llibreId = llibreId;

        setTitle("Editar Llibre");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));

        // Crear i afegir els components
        panel.add(new JLabel("Títol:"));
        txtTitol = new JTextField();
        panel.add(txtTitol);

        panel.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        panel.add(txtAutor);

        panel.add(new JLabel("ISBN:"));
        txtISBN = new JTextField();
        panel.add(txtISBN);

        panel.add(new JLabel("Editorial:"));
        txtEditorial = new JTextField();
        panel.add(txtEditorial);

        panel.add(new JLabel("Any de Publicació:"));
        txtAnyPublicacio = new JTextField();
        panel.add(txtAnyPublicacio);

        panel.add(new JLabel("Categoria:"));
        txtCategoria = new JTextField();
        panel.add(txtCategoria);

        panel.add(new JLabel("Estat:"));
        cmbEstat = new JComboBox<>(new String[]{"disponible", "prestat", "en manteniment"});
        panel.add(cmbEstat);

        btnActualitzar = new JButton("Actualitzar");
        panel.add(new JLabel()); // Placeholder
        panel.add(btnActualitzar);

        add(panel);

        // Acció del botó "Actualitzar"
        btnActualitzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualitzarLlibre();
                llibresGUI.actualitzarTaulaLlibres();
            }
        });

        // Carregar les dades del llibre
        carregarDadesLlibre();

        setVisible(true);
    }

    // Carregar les dades del llibre des de la base de dades
    private void carregarDadesLlibre() {
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT Títol, Autor, ISBN, Editorial, Any_Publicació, Categoria, Estat FROM llibres WHERE ID_Llibre = ?")) {
            statement.setInt(1, llibreId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    txtTitol.setText(resultSet.getString("Títol"));
                    txtAutor.setText(resultSet.getString("Autor"));
                    txtISBN.setText(resultSet.getString("ISBN"));
                    txtEditorial.setText(resultSet.getString("Editorial"));
                    txtAnyPublicacio.setText(resultSet.getString("Any_Publicació"));
                    txtCategoria.setText(resultSet.getString("Categoria"));
                    cmbEstat.setSelectedItem(resultSet.getString("Estat"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en carregar les dades del llibre", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Actualitzar el llibre a la base de dades
    private void actualitzarLlibre() {
        String titol = txtTitol.getText();
        String autor = txtAutor.getText();
        String isbn = txtISBN.getText();
        String editorial = txtEditorial.getText();
        int anyPublicacio = Integer.parseInt(txtAnyPublicacio.getText());
        String categoria = txtCategoria.getText();
        String estat = (String) cmbEstat.getSelectedItem();

        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE llibres SET Títol = ?, Autor = ?, ISBN = ?, Editorial = ?, Any_Publicació = ?, Categoria = ?, Estat = ? WHERE ID_Llibre = ?")) {
            statement.setString(1, titol);
            statement.setString(2, autor);
            statement.setString(3, isbn);
            statement.setString(4, editorial);
            statement.setInt(5, anyPublicacio);
            statement.setString(6, categoria);
            statement.setString(7, estat);
            statement.setInt(8, llibreId);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Llibre actualitzat correctament!");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en actualitzar el llibre", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
