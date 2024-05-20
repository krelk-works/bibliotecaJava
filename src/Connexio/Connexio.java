package Connexio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexio {
    // Dades de connexió a la base de dades
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Returnem l'objecte de connexió que farem utilitzar per a les nostres operacions a la base de dades -> TODO : Control d'errors més específic
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}