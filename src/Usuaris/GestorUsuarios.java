package Usuaris;

import Connexio.Connexio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestorUsuarios {

    public void registrarUsuario(String nombre, String apellidos, String email, String telefono, String rol, String fechaRegistro) {
        try (Connection connection = Connexio.getConnection()) {
            String query = "INSERT INTO Usuaris (Nom, Cognoms, Email, Telèfon, Rol, Data_Registre) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nombre);
                statement.setString(2, apellidos);
                statement.setString(3, email);
                statement.setString(4, telefono);
                statement.setString(5, rol);
                statement.setDate(6, Date.valueOf(fechaRegistro));
                statement.executeUpdate();
                System.out.println("Usuario registrado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
