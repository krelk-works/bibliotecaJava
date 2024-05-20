package Prestecs;

import Connexio.Connexio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrestamosGUI extends JFrame {
    private JTextField idLibroField, idUsuarioField, fechaPrestamoField, fechaRetornoPrevistaField, idPrestamoField, fechaRetornoRealField;
    private JButton realizarPrestamoButton, devolverLibroButton;

    public PrestamosGUI() {
        setTitle("Gestión de Préstamos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2));

        // Crear y añadir componentes
        add(new JLabel("ID Libro:"));
        idLibroField = new JTextField();
        add(idLibroField);

        add(new JLabel("ID Usuario:"));
        idUsuarioField = new JTextField();
        add(idUsuarioField);

        add(new JLabel("Fecha de Préstamo (YYYY-MM-DD):"));
        fechaPrestamoField = new JTextField();
        add(fechaPrestamoField);

        add(new JLabel("Fecha de Retorno Prevista (YYYY-MM-DD):"));
        fechaRetornoPrevistaField = new JTextField();
        add(fechaRetornoPrevistaField);

        realizarPrestamoButton = new JButton("Realizar Préstamo");
        realizarPrestamoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarPrestamo();
            }
        });
        add(realizarPrestamoButton);

        add(new JLabel("ID Préstamo:"));
        idPrestamoField = new JTextField();
        add(idPrestamoField);

        add(new JLabel("Fecha de Retorno Real (YYYY-MM-DD):"));
        fechaRetornoRealField = new JTextField();
        add(fechaRetornoRealField);

        devolverLibroButton = new JButton("Devolver Libro");
        devolverLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverLibro();
            }
        });
        add(devolverLibroButton);
    }

    private void realizarPrestamo() {
        try (Connection connection = Connexio.getConnection()) {
            String query = "INSERT INTO Préstecs (ID_Llibre, ID_Usuari, Data_Préstec, Data_Retorn_Prevista, Estat) VALUES (?, ?, ?, ?, 'Activo')";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, Integer.parseInt(idLibroField.getText()));
                statement.setInt(2, Integer.parseInt(idUsuarioField.getText()));
                statement.setDate(3, Date.valueOf(fechaPrestamoField.getText()));
                statement.setDate(4, Date.valueOf(fechaRetornoPrevistaField.getText()));
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Préstamo realizado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al realizar el préstamo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void devolverLibro() {
        try (Connection connection = Connexio.getConnection()) {
            String query = "UPDATE Préstecs SET Data_Retorn_Real=?, Estat='Devuelto' WHERE ID_Préstec=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, Date.valueOf(fechaRetornoRealField.getText()));
                statement.setInt(2, Integer.parseInt(idPrestamoField.getText()));
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Libro devuelto exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al devolver el libro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
