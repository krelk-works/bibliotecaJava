package Usuaris;

import javax.swing.*;

import Llibres.LlibresGUI;
import Usuaris.UsuarisGUI;
import Prestecs.PrestecsGUI;

import java.awt.*;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("unused")
public class FinestraBibliotecari extends JFrame {
    private Usuari usuari;

    public FinestraBibliotecari(Usuari usuari) {
        this.usuari = usuari;

        setTitle("Interfície de Bibliotecari");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Panell superior amb informació de l'usuari i el nom de la biblioteca
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel lblUsuari = new JLabel("Benvingut, " + usuari.getNom() + " " + usuari.getCognoms());
        JLabel lblBiblioteca = new JLabel("Biblioteca: Can Casacuberta", JLabel.RIGHT);
        topPanel.add(lblUsuari, BorderLayout.WEST);
        topPanel.add(lblBiblioteca, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(topPanel, BorderLayout.NORTH);

        // Panell central amb les opcions de gestió
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton btnGestioLlibres = new JButton("Gestió de Llibres");
        // Afegim la funcionalitat del botó de Gestió de Llibres
        btnGestioLlibres.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LlibresGUI();
            }
        });
        JButton btnGestioPrestecs = new JButton("Gestió de Préstecs");
        // Afegim la funcionalitat del botó de Gestió de Préstecs
        btnGestioPrestecs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PrestecsGUI();
            }
        });
        JButton btnGestioUsuaris = new JButton("Gestió d'Usuaris");
        // Afegim la funcionalitat del botó de Gestió d'Usuaris
        btnGestioUsuaris.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UsuarisGUI();
            }
        });
        centerPanel.add(btnGestioLlibres);
        centerPanel.add(btnGestioPrestecs);
        centerPanel.add(btnGestioUsuaris);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));
        panel.add(centerPanel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
}
