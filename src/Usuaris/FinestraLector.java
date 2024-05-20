package Usuaris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Llibres.ConsultaLlibres;
import Prestecs.ConsultaPrestecs;

public class FinestraLector extends JFrame {
    @SuppressWarnings("unused")
    private Usuari usuari;

    public FinestraLector(Usuari usuari) {
        this.usuari = usuari;

        setTitle("Interfície de Lector");
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
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JButton btnBuscarLlibres = new JButton("Buscar llibres disponibles");
        // Afegim la funcionalitat del botó de Buscar Llibres
        btnBuscarLlibres.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Buscar llibres disponibles");
                new ConsultaLlibres();
            }
        });
        JButton btnConsultarPrestecs = new JButton("Consultar els meus préstecs");
        // Afegim la funcionalitat del botó de Consultar Préstecs
        btnConsultarPrestecs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ConsultaPrestecs(usuari);
            }
        });
        centerPanel.add(btnBuscarLlibres);
        centerPanel.add(btnConsultarPrestecs);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));
        panel.add(centerPanel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
}
