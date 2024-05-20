package biblioteca.Llibres;

import biblioteca.Connexio.Connexio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibrosGUI extends JFrame {
    private JTextField tituloField, autorField, isbnField, editorialField, anyPublicacioField, categoriaField, estatField;
    private JButton agregarButton, modificarButton, eliminarButton;

    public LibrosGUI() {
        setTitle("Gestión de Libros");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 2));

        // Crear y añadir componentes
        add(new JLabel("Título:"));
        tituloField = new JTextField();
        add(tituloField);

        add(new JLabel("Autor:"));
        autorField = new JTextField();
        add(autorField);

        add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        add(isbnField);

        add(new JLabel("Editorial:"));
        editorialField = new JTextField();
        add(editorialField);

        add(new JLabel("Año de Publicación:"));
        anyPublicacioField = new JTextField();
        add(anyPublicacioField);

        add(new JLabel("Categoría:"));
        categoriaField = new JTextField();
        add(categoriaField);

        add(new JLabel("Estado:"));
        estatField = new JTextField();
        add(estatField);

        agregarButton = new JButton("Agregar Libro");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibro();
            }
        });
        add(agregarButton);

        modificarButton = new JButton("Modificar Libro");
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarLibro();
            }
        });
        add(modificarButton);

        eliminarButton = new JButton("Eliminar Libro");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarLibro();
            }
        });
        add(eliminarButton);
    }

    private void agregarLibro() {
        try (Connection connection = Connexio.getConnection()) {
            String query = "INSERT INTO Llibres (Títol, Autor, ISBN, Editorial, Any_Publicació, Categoria, Estat) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tituloField.getText());
                statement.setString(2, autorField.getText());
                statement.setString(3, isbnField.getText());
                statement.setString(4, editorialField.getText());
                statement.setInt(5, Integer.parseInt(anyPublicacioField.getText()));
                statement.setString(6, categoriaField.getText());
                statement.setString(7, estatField.getText());
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Libro agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarLibro() {
        // Similar implementación a agregarLibro() pero con UPDATE en vez de INSERT
    }

    private void eliminarLibro() {
        // Similar implementación a agregarLibro() pero con DELETE en vez de INSERT
    }
}
