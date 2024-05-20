package biblioteca;

import biblioteca.Llibres.LibrosGUI;
import biblioteca.Prestecs.PrestamosGUI;
import biblioteca.Usuaris.UsuariosGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JButton gestionarLibrosButton, gestionarPrestamosButton, gestionarUsuariosButton;

    public MainGUI() {
        setTitle("Biblioteca - Sistema de Gestión");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        gestionarLibrosButton = new JButton("Gestionar Libros");
        gestionarLibrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LibrosGUI().setVisible(true);
            }
        });
        add(gestionarLibrosButton);

        gestionarPrestamosButton = new JButton("Gestionar Préstamos");
        gestionarPrestamosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PrestamosGUI().setVisible(true);
            }
        });
        add(gestionarPrestamosButton);

        gestionarUsuariosButton = new JButton("Gestionar Usuarios");
        gestionarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UsuariosGUI().setVisible(true);
            }
        });
        add(gestionarUsuariosButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }
}
