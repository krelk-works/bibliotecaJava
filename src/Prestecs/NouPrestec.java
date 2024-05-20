package Prestecs;

import Connexio.Connexio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NouPrestec extends JFrame {
    private JComboBox<String> lectorComboBox;
    private JComboBox<String> llibreComboBox;
    private JComboBox<Integer> diesComboBox;
    private JButton afegirPrestecButton;

    public NouPrestec(PrestecsGUI prestecsGUI) {
        setTitle("Nou Préstec");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        // Crear components
        lectorComboBox = new JComboBox<>(getLectors());
        llibreComboBox = new JComboBox<>(getLlibresDisponibles());
        diesComboBox = new JComboBox<>(getDiesArray());
        afegirPrestecButton = new JButton("Afegir Préstec");

        // Afegir components al panell
        panel.add(new JLabel("Selecciona Lector:"));
        panel.add(lectorComboBox);
        panel.add(new JLabel("Selecciona Llibre:"));
        panel.add(llibreComboBox);
        panel.add(new JLabel("Nombre de dies:"));
        panel.add(diesComboBox);
        panel.add(new JLabel());
        panel.add(afegirPrestecButton);

        add(panel);

        // Acció del botó "Afegir Préstec"
        afegirPrestecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afegirPrestec();
                prestecsGUI.actualitzarTaulaPrestecs();
                dispose();
            }
        });

        setVisible(true);
    }

    // Obtenir els noms dels lectors des de la base de dades
    private String[] getLectors() {
        List<String> lectors = new ArrayList<>();
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT ID_Usuari, Nom, Cognoms FROM usuaris WHERE Rol = 'lector'");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID_Usuari");
                String nom = resultSet.getString("Nom");
                String cognoms = resultSet.getString("Cognoms");
                lectors.add(id + " - " + nom + " " + cognoms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lectors.toArray(new String[0]);
    }

    // Obtenir els llibres disponibles des de la base de dades
    private String[] getLlibresDisponibles() {
        List<String> llibres = new ArrayList<>();
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT ID_Llibre, Títol FROM llibres WHERE Estat = 'Disponible' AND ID_Llibre NOT IN (SELECT ID_Llibre FROM préstecs WHERE Estat = 'actiu')");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID_Llibre");
                String titol = resultSet.getString("Títol");
                llibres.add(id + " - " + titol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return llibres.toArray(new String[0]);
    }

    // Crear un array de nombres de dies (15-30)
    private Integer[] getDiesArray() {
        Integer[] dies = new Integer[16];
        for (int i = 0; i < dies.length; i++) {
            dies[i] = 15 + i;
        }
        return dies;
    }

    // Comprovar si l'usuari té una sanció activa
    private boolean usuariSancionat(int idLector) {
        boolean sancionat = false;
        Date sancioFins = null;
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT Sancio_Fins FROM usuaris WHERE ID_Usuari = ?")) {
            statement.setInt(1, idLector);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    sancioFins = resultSet.getDate("Sancio_Fins");
                    if (sancioFins != null && sancioFins.after(new Date(System.currentTimeMillis()))) {
                        sancionat = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (sancionat) {
            JOptionPane.showMessageDialog(this, "El préstec no s'ha pogut crear, l'usuari té sanció fins " + sancioFins);
        }
        return sancionat;
    }

    // Afegir un nou préstec a la base de dades
    private void afegirPrestec() {
        String lectorSeleccionat = (String) lectorComboBox.getSelectedItem();
        String llibreSeleccionat = (String) llibreComboBox.getSelectedItem();
        int diesSeleccionats = (Integer) diesComboBox.getSelectedItem();

        if (lectorSeleccionat != null && llibreSeleccionat != null) {
            int idLector = Integer.parseInt(lectorSeleccionat.split(" - ")[0]);
            int idLlibre = Integer.parseInt(llibreSeleccionat.split(" - ")[0]);

            if (usuariSancionat(idLector)) {
                return; // Si l'usuari està sancionat, no afegim el préstec
            }

            Date dataPrestec = new Date(System.currentTimeMillis());
            Date dataRetornPrevista = new Date(System.currentTimeMillis() + (diesSeleccionats * 86400000L)); // 86400000 ms = 1 dia

            try (Connection connection = Connexio.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO préstecs (ID_Usuari, ID_Llibre, Data_Préstec, Data_Retorn_Prevista, Data_Retorn_Real, Estat) VALUES (?, ?, ?, ?, NULL, 'actiu')")) {
                statement.setInt(1, idLector);
                statement.setInt(2, idLlibre);
                statement.setDate(3, dataPrestec);
                statement.setDate(4, dataRetornPrevista);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "S'ha afegit el préstec correctament");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
