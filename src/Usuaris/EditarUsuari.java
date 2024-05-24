package Usuaris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Connexio.Connexio;

public class EditarUsuari extends JFrame {
    private int usuariId;
    private JTextField txtNom;
    private JTextField txtCognoms;
    private JTextField txtEmail;
    private JTextField txtTelefon;
    private JComboBox<String> comboRol;
    private JPasswordField txtContrasenya;
    private UsuarisGUI parent;

    public EditarUsuari(UsuarisGUI parent, int usuariId) {
        this.parent = parent;
        this.usuariId = usuariId;

        setTitle("Editar Usuari");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));

        // Obtenir les dades de l'usuari de la base de dades
        Usuari usuari = obtenirUsuariDeLaBaseDeDades(usuariId);

        // Afegir els components per editar l'usuari
        panel.add(new JLabel("Nom:"));
        txtNom = new JTextField(usuari.getNom());
        panel.add(txtNom);

        panel.add(new JLabel("Cognoms:"));
        txtCognoms = new JTextField(usuari.getCognoms());
        panel.add(txtCognoms);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField(usuari.getEmail());
        panel.add(txtEmail);

        panel.add(new JLabel("Telèfon:"));
        txtTelefon = new JTextField(usuari.getTelefon());
        panel.add(txtTelefon);

        panel.add(new JLabel("Rol:"));
        comboRol = new JComboBox<>(new String[]{"lector", "bibliotecari"});
        comboRol.setSelectedItem(usuari.getRol());
        panel.add(comboRol);

        panel.add(new JLabel("Contrasenya:"));
        txtContrasenya = new JPasswordField("");
        panel.add(txtContrasenya);

        // Afegir un panell per centrar el botó
        JPanel buttonPanel = new JPanel();
        JButton btnActualitzar = new JButton("Actualitzar");
        btnActualitzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualitzarUsuari();
            }
        });
        buttonPanel.add(btnActualitzar);

        // Afegir el panell del botó al panell principal
        panel.add(new JLabel()); // Espaiador
        panel.add(buttonPanel);

        add(panel);
        setVisible(true);
    }

    private Usuari obtenirUsuariDeLaBaseDeDades(int usuariId) {
        Usuari usuari = null;
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuaris WHERE ID_Usuari = ?")) {
            statement.setInt(1, usuariId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usuari = new Usuari(
                            resultSet.getInt("ID_Usuari"),
                            resultSet.getString("Nom"),
                            resultSet.getString("Cognoms"),
                            resultSet.getString("Email"),
                            resultSet.getString("Telèfon"),
                            resultSet.getString("Rol"),
                            resultSet.getDate("Data_Registre"),
                            resultSet.getString("Contrasenya")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuari;
    }

    private void actualitzarUsuari() {
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE usuaris SET Nom = ?, Cognoms = ?, Email = ?, Telèfon = ?, Rol = ?, Contrasenya = ? WHERE ID_Usuari = ?")) {
            statement.setString(1, txtNom.getText());
            statement.setString(2, txtCognoms.getText());
            statement.setString(3, txtEmail.getText());
            statement.setString(4, txtTelefon.getText());
            statement.setString(5, (String) comboRol.getSelectedItem());
            statement.setString(6, new String(txtContrasenya.getPassword()));
            statement.setInt(7, usuariId);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Usuari actualitzat correctament.");
            parent.actualitzarTaulaUsuaris(); // Actualitzar la taula d'usuaris al parent
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en actualitzar l'usuari.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
