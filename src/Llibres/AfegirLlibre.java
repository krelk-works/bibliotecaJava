package Llibres;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Connexio.Connexio;

public class AfegirLlibre extends JFrame {
    private JTextField txtTitol;
    private JTextField txtAutor;
    private JTextField txtIsbn;
    private JTextField txtEditorial;
    private JTextField txtAnyPublicacio;
    private JTextField txtCategoria;
    private JTextField txtEstat;
    private LlibresGUI parent;

    public AfegirLlibre(LlibresGUI parent) {
        this.parent = parent;

        setTitle("Afegir Llibre");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));

        // Afegir els components per introduir el nou llibre
        panel.add(new JLabel("Títol:"));
        txtTitol = new JTextField();
        panel.add(txtTitol);

        panel.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        panel.add(txtAutor);

        panel.add(new JLabel("ISBN:"));
        txtIsbn = new JTextField();
        panel.add(txtIsbn);

        panel.add(new JLabel("Editorial:"));
        txtEditorial = new JTextField();
        panel.add(txtEditorial);

        panel.add(new JLabel("Any Publicació:"));
        txtAnyPublicacio = new JTextField();
        panel.add(txtAnyPublicacio);

        panel.add(new JLabel("Categoria:"));
        txtCategoria = new JTextField();
        panel.add(txtCategoria);

        panel.add(new JLabel("Estat:"));
        txtEstat = new JTextField();
        panel.add(txtEstat);

        // Afegir un panell per centrar el botó
        JPanel buttonPanel = new JPanel();
        JButton btnAfegir = new JButton("Afegir");
        btnAfegir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afegirLlibre();
            }
        });
        buttonPanel.add(btnAfegir);

        // Afegir el panell del botó al panell principal
        panel.add(new JLabel()); // Espaiador
        panel.add(buttonPanel);

        add(panel);
        setVisible(true);
    }

    private void afegirLlibre() {
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Llibres (Títol, Autor, ISBN, Editorial, Any_Publicació, Categoria, Estat) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, txtTitol.getText());
            statement.setString(2, txtAutor.getText());
            statement.setString(3, txtIsbn.getText());
            statement.setString(4, txtEditorial.getText());
            statement.setInt(5, Integer.parseInt(txtAnyPublicacio.getText()));
            statement.setString(6, txtCategoria.getText());
            statement.setString(7, txtEstat.getText());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Llibre afegit correctament.");
            parent.actualitzarTaulaLlibres(); // Actualitzar la taula de llibres al parent
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en afegir el llibre.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
