package Prestecs;

import Connexio.Connexio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestorPrestamos {

    public void realizarPrestamo(int idLibro, int idUsuario, String fechaPrestamo, String fechaRetornoPrevista) {
        try (Connection connection = Connexio.getConnection()) {
            String query = "INSERT INTO Préstecs (ID_Llibre, ID_Usuari, Data_Préstec, Data_Retorn_Prevista, Estat) VALUES (?, ?, ?, ?, 'Activo')";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idLibro);
                statement.setInt(2, idUsuario);
                statement.setDate(3, Date.valueOf(fechaPrestamo));
                statement.setDate(4, Date.valueOf(fechaRetornoPrevista));
                statement.executeUpdate();
                System.out.println("Préstamo realizado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void devolverLibro(int idPrestamo, String fechaRetornoReal) {
        try (Connection connection = Connexio.getConnection()) {
            String query = "UPDATE Préstecs SET Data_Retorn_Real=?, Estat='Devuelto' WHERE ID_Préstec=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, Date.valueOf(fechaRetornoReal));
                statement.setInt(2, idPrestamo);
                statement.executeUpdate();
                System.out.println("Libro devuelto exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
