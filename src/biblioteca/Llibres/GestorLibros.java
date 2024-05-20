package biblioteca.Llibres;

import biblioteca.Connexio.Connexio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestorLibros {

    public void agregarLibro(String titulo, String autor, String isbn, String editorial, int anyPublicacio, String categoria, String estat) {
        try (Connection connection = Connexio.getConnection()) {
            String query = "INSERT INTO Llibres (Títol, Autor, ISBN, Editorial, Any_Publicació, Categoria, Estat) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, titulo);
                statement.setString(2, autor);
                statement.setString(3, isbn);
                statement.setString(4, editorial);
                statement.setInt(5, anyPublicacio);
                statement.setString(6, categoria);
                statement.setString(7, estat);
                statement.executeUpdate();
                System.out.println("Libro agregado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificarLibro(int idLibro, String nuevoTitulo, String nuevoAutor, String nuevoISBN, String nuevaEditorial, int nuevoAnyPublicacio, String nuevaCategoria, String nuevoEstat) {
        try (Connection connection = Connexio.getConnection()) {
            String query = "UPDATE Llibres SET Títol=?, Autor=?, ISBN=?, Editorial=?, Any_Publicació=?, Categoria=?, Estat=? WHERE ID_Llibre=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nuevoTitulo);
                statement.setString(2, nuevoAutor);
                statement.setString(3, nuevoISBN);
                statement.setString(4, nuevaEditorial);
                statement.setInt(5, nuevoAnyPublicacio);
                statement.setString(6, nuevaCategoria);
                statement.setString(7, nuevoEstat);
                statement.setInt(8, idLibro);
                statement.executeUpdate();
                System.out.println("Libro modificado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarLibro(int idLibro) {
        try (Connection connection = Connexio.getConnection()) {
            String query = "DELETE FROM Llibres WHERE ID_Llibre=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idLibro);
                statement.executeUpdate();
                System.out.println("Libro eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
