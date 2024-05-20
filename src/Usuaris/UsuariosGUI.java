package Usuaris;

import Connexio.Connexio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuariosGUI extends JFrame {
    private JTextField nombreField, apellidosField, emailField, telefonoField, rolField, fechaRegistroField;
    private JButton registrarButton;

    public UsuariosGUI() {
        setTitle("Gestión de Usuarios");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2));

        // Crear y añadir componentes
        add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Apellidos:"));
        apellidosField = new JTextField();
        add(apellidosField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        add(telefonoField);

        add(new JLabel("Rol:"));
        rolField = new JTextField();
        add(rolField);

        add(new JLabel("Fecha de Registro (YYYY-MM-DD):"));
        fechaRegistroField = new JTextField();
        add(fechaRegistroField);

        registrarButton = new JButton("Registrar Usuario");
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
        add(registrarButton);
    }

    private void registrarUsuario() {
        try (Connection connection = Connexio.getConnection()) {
            String query = "INSERT INTO Usuaris (Nom, Cognoms, Email, Telèfon, Rol, Data_Registre) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nombreField.getText());
                statement.setString(2, apellidosField.getText());
                statement.setString(3, emailField.getText());
                statement.setString(4, telefonoField.getText());
                statement.setString(5, rolField.getText());
                statement.setDate(6, Date.valueOf(fechaRegistroField.getText()));
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
