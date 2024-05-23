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
    private JTextField txtISBN;
    private JTextField txtEditorial;
    private JTextField txtAnyPublicacio;
    private JTextField txtCategoria;
    private JComboBox<String> cmbEstat;
    private JButton btnAfegir;

    public AfegirLlibre(LlibresGUI llibresGUI) {
        setTitle("Afegir Llibre");
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

        btnAfegir = new JButton("Afegir");
        panel.add(new JLabel()); // Placeholder
        panel.add(btnAfegir);

        add(panel);

        // Acció del botó "Afegir"
        btnAfegir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afegirLlibre();
                llibresGUI.actualitzarTaulaLlibres();
            }
        });

        setVisible(true);
    }

    // Afegir un nou llibre a la base de dades
    private void afegirLlibre() {
        String titol = txtTitol.getText();
        String autor = txtAutor.getText();
        String isbn = txtISBN.getText();
        String editorial = txtEditorial.getText();
        int anyPublicacio = Integer.parseInt(txtAnyPublicacio.getText());
        String categoria = txtCategoria.getText();
        String estat = (String) cmbEstat.getSelectedItem();

        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO llibres (Títol, Autor, ISBN, Editorial, Any_Publicació, Categoria, Estat) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, titol);
            statement.setString(2, autor);
            statement.setString(3, isbn);
            statement.setString(4, editorial);
            statement.setInt(5, anyPublicacio);
            statement.setString(6, categoria);
            statement.setString(7, estat);

            if (!isValidTitol(titol)) {
                JOptionPane.showMessageDialog(this, "El titol no es valid");
            } else if(!isValidAutor(autor)) {
                JOptionPane.showMessageDialog(this, "El autor no es valid");
            } else {
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Llibre afegit correctament!");
                dispose();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en afegir el llibre", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private boolean isValidTitol(String titol) {
        return titol != null && !titol.trim().isEmpty();
    }

    private boolean isValidAutor(String autor) {
        return autor != null && !autor.trim().isEmpty();
    }
}
