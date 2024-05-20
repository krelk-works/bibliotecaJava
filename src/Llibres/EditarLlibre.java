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
    private JTextField txtIsbn;
    private JTextField txtEditorial;
    private JTextField txtAnyPublicacio;
    private JTextField txtCategoria;
    private JTextField txtEstat;
    private LlibresGUI parent;

    public EditarLlibre(LlibresGUI parent, int llibreId) {
        this.parent = parent;
        this.llibreId = llibreId;

        setTitle("Editar Llibre");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));

        // Obtenir les dades del llibre de la base de dades
        Llibre llibre = obtenirLlibreDeLaBaseDeDades(llibreId);

        // Afegir els components per editar el llibre
        panel.add(new JLabel("Títol:"));
        txtTitol = new JTextField(llibre.getTitol());
        panel.add(txtTitol);

        panel.add(new JLabel("Autor:"));
        txtAutor = new JTextField(llibre.getAutor());
        panel.add(txtAutor);

        panel.add(new JLabel("ISBN:"));
        txtIsbn = new JTextField(llibre.getIsbn());
        panel.add(txtIsbn);

        panel.add(new JLabel("Editorial:"));
        txtEditorial = new JTextField(llibre.getEditorial());
        panel.add(txtEditorial);

        panel.add(new JLabel("Any Publicació:"));
        txtAnyPublicacio = new JTextField(String.valueOf(llibre.getAnyPublicacio()));
        panel.add(txtAnyPublicacio);

        panel.add(new JLabel("Categoria:"));
        txtCategoria = new JTextField(llibre.getCategoria());
        panel.add(txtCategoria);

        panel.add(new JLabel("Estat:"));
        txtEstat = new JTextField(llibre.getEstat());
        panel.add(txtEstat);

        // Afegir un panell per centrar el botó
        JPanel buttonPanel = new JPanel();
        JButton btnActualitzar = new JButton("Actualitzar");
        btnActualitzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualitzarLlibre();
            }
        });
        buttonPanel.add(btnActualitzar);

        // Afegir el panell del botó al panell principal
        panel.add(new JLabel()); // Espaiador
        panel.add(buttonPanel);

        add(panel);
        setVisible(true);
    }

    private Llibre obtenirLlibreDeLaBaseDeDades(int llibreId) {
        Llibre llibre = null;
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Llibres WHERE ID_Llibre = ?")) {
            statement.setInt(1, llibreId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    llibre = new Llibre(
                            resultSet.getInt("ID_Llibre"),
                            resultSet.getString("Títol"),
                            resultSet.getString("Autor"),
                            resultSet.getString("ISBN"),
                            resultSet.getString("Editorial"),
                            resultSet.getInt("Any_Publicació"),
                            resultSet.getString("Categoria"),
                            resultSet.getString("Estat")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return llibre;
    }

    private void actualitzarLlibre() {
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Llibres SET Títol = ?, Autor = ?, ISBN = ?, Editorial = ?, Any_Publicació = ?, Categoria = ?, Estat = ? WHERE ID_Llibre = ?")) {
            statement.setString(1, txtTitol.getText());
            statement.setString(2, txtAutor.getText());
            statement.setString(3, txtIsbn.getText());
            statement.setString(4, txtEditorial.getText());
            statement.setInt(5, Integer.parseInt(txtAnyPublicacio.getText()));
            statement.setString(6, txtCategoria.getText());
            statement.setString(7, txtEstat.getText());
            statement.setInt(8, llibreId);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Llibre actualitzat correctament.");
            parent.actualitzarTaulaLlibres(); // Actualitzar la taula de llibres al parent
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en actualitzar el llibre.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
