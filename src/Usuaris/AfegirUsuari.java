package Usuaris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Connexio.Connexio;

public class AfegirUsuari extends JFrame {
    private JTextField txtNom;
    private JTextField txtCognoms;
    private JTextField txtEmail;
    private JTextField txtTelefon;
    private JComboBox<String> comboRol;
    private JPasswordField txtContrasenya;
    private UsuarisGUI parent;

    public AfegirUsuari(UsuarisGUI parent) {
        this.parent = parent;

        setTitle("Afegir Usuari");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));

        // Afegir els components per introduir el nou usuari
        panel.add(new JLabel("Nom:"));
        txtNom = new JTextField();
        panel.add(txtNom);

        panel.add(new JLabel("Cognoms:"));
        txtCognoms = new JTextField();
        panel.add(txtCognoms);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Telèfon:"));
        txtTelefon = new JTextField();
        panel.add(txtTelefon);

        panel.add(new JLabel("Rol:"));
        comboRol = new JComboBox<>(new String[]{"lector", "bibliotecari"});
        panel.add(comboRol);

        panel.add(new JLabel("Contrasenya:"));
        txtContrasenya = new JPasswordField();
        panel.add(txtContrasenya);

        // Afegir un panell per centrar el botó
        JPanel buttonPanel = new JPanel();
        JButton btnAfegir = new JButton("Afegir");
        btnAfegir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afegirUsuari();
            }
        });
        buttonPanel.add(btnAfegir);

        // Afegir el panell del botó al panell principal
        panel.add(new JLabel()); // Espaiador
        panel.add(buttonPanel);

        add(panel);
        setVisible(true);
    }

    private void afegirUsuari() {
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO usuaris (Nom, Cognoms, Email, Telèfon, Rol, Contrasenya) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, txtNom.getText());
            statement.setString(2, txtCognoms.getText());
            statement.setString(3, txtEmail.getText());
            statement.setString(4, txtTelefon.getText());
            statement.setString(5, (String) comboRol.getSelectedItem());
            statement.setString(6, new String(txtContrasenya.getPassword()));
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Usuari afegit correctament.");
            parent.actualitzarTaulaUsuaris(); // Actualitzar la taula d'usuaris al parent
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en afegir l'usuari.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
