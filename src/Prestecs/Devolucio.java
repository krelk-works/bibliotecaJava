package Prestecs;

import Connexio.Connexio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Devolucio extends JFrame {
    private int prestecId;
    private JLabel sancioLabel;
    private JComboBox<Integer> sancioComboBox;
    private JButton confirmarButton;

    public Devolucio(int prestecId, PrestecsGUI prestecsGUI) {
        this.prestecId = prestecId;

        setTitle("Devolució");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        sancioLabel = new JLabel("Selecciona els dies de sanció:");
        sancioComboBox = new JComboBox<>(getDiesArray());
        confirmarButton = new JButton("Confirmar");

        panel.add(sancioLabel);
        panel.add(sancioComboBox);
        panel.add(confirmarButton);

        add(panel);

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int diesSancio = (Integer) sancioComboBox.getSelectedItem();
                sancionarUsuari(prestecId, diesSancio);
                prestecsGUI.actualitzarTaulaPrestecs();
                dispose();
            }
        });

        if (isDevolucioFeta(prestecId)) {
            JOptionPane.showMessageDialog(null, "Ja s'ha fet la devolució d'aquest llibre.");
        } else if (isForaDeTermini(prestecId)) {
            setVisible(true);
        } else {
            retornarPrestec(prestecId);
            JOptionPane.showMessageDialog(null, "S'ha fet la devolució del llibre correctament!");
            prestecsGUI.actualitzarTaulaPrestecs();
        }
    }

    // Comprovar si la devolució ja s'ha fet
    private boolean isDevolucioFeta(int prestecId) {
        boolean devolucioFeta = false;
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT Data_Retorn_Real FROM préstecs WHERE ID_Préstec = ?")) {
            statement.setInt(1, prestecId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Date dataRetornReal = resultSet.getDate("Data_Retorn_Real");
                    if (dataRetornReal != null) {
                        devolucioFeta = true;
                        
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devolucioFeta;
    }

    // Comprovar si la devolució es farà fora de termini
    private boolean isForaDeTermini(int prestecId) {
        boolean foraDeTermini = false;
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT Data_Retorn_Prevista, Data_Retorn_Real FROM préstecs WHERE ID_Préstec = ?")) {
            statement.setInt(1, prestecId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Date dataRetornPrevista = resultSet.getDate("Data_Retorn_Prevista");
                    Date dataRetornReal = resultSet.getDate("Data_Retorn_Real");
                    Date dataAvui = new Date(System.currentTimeMillis());
                    if ((dataRetornReal == null && dataAvui.after(dataRetornPrevista)) || 
                        (dataRetornReal != null && dataRetornReal.after(dataRetornPrevista))) {
                        foraDeTermini = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foraDeTermini;
    }

    // Actualitzar la data de retorn real del préstec
    private void retornarPrestec(int prestecId) {
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE préstecs SET Data_Retorn_Real = ?, Estat = 'completat' WHERE ID_Préstec = ?")) {
            statement.setDate(1, new Date(System.currentTimeMillis()));
            statement.setInt(2, prestecId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sancionar l'usuari
    private void sancionarUsuari(int prestecId, int diesSancio) {
        try (Connection connection = Connexio.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT u.Nom, u.Cognoms FROM usuaris u INNER JOIN préstecs p ON p.ID_Usuari = u.ID_Usuari WHERE p.ID_Préstec = ?")) {
            statement.setInt(1, prestecId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nom = resultSet.getString("Nom");
                    String cognoms = resultSet.getString("Cognoms");
                    System.out.println("S'ha sancionat a " + nom + " " + cognoms + " amb " + diesSancio + " dies de inhabilitació dels serveis de la biblioteca.");
                    retornarPrestec(prestecId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        retornarPrestec(prestecId);
    }

    // Crear un array de nombres de dies (15-30)
    private Integer[] getDiesArray() {
        Integer[] dies = new Integer[16];
        for (int i = 0; i < dies.length; i++) {
            dies[i] = 15 + i;
        }
        return dies;
    }
}
