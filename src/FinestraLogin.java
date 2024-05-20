import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Connexio.Connexio;
import Usuaris.FinestraBibliotecari;
import Usuaris.FinestraLector;
import Usuaris.Usuari;

public class FinestraLogin extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public FinestraLogin() {
        // Definim el títol de la finestra
        setTitle("Login");

        // Definim les dimensions de la finestra 400x400
        setSize(400, 400);

        // Tanquem l'aplicació al prémer la creu de la finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Centrem la finestra al mig de la pantalla
        setLocationRelativeTo(null);

        // No permetem redimensionar la finestra (evitem problemes de layout)
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Afegir la imatge
        JLabel lblImage = new JLabel();
        lblImage.setHorizontalAlignment(JLabel.CENTER);
        String fileSeparator = System.getProperty("file.separator");
        ImageIcon icon = new ImageIcon(getClass().getResource("media" + fileSeparator + "cancasacuberta.jpg"));

        // Escalat de la imatge per a que s'adapti a la finestra
        Image image = icon.getImage().getScaledInstance((int)(getWidth() * 0.98), (int)(getHeight() * 0.5), Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(image));
        panel.add(lblImage, BorderLayout.NORTH);

        // Panell central per a les dades del login | Definim un layout GridBagLayout per a poder posicionar els elements i li afegim insets per a que no quedi tan apretat (marge)
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Labels i txtFields per a l'email i la contrasenya 
        JLabel lblEmail = new JLabel("Correu:");
        // Posició X i Y dels elements, alineació i afegim el component al panell
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(lblEmail, gbc);

        txtEmail = new JTextField(20);
        // Posició X i Y dels elements, alineació i afegim el component al panell
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(txtEmail, gbc);

        JLabel lblPassword = new JLabel("Contrasenya:");
        // Posició X i Y dels elements, alineació i afegim el component al panell
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(lblPassword, gbc);

        txtPassword = new JPasswordField(20);
        // Posició X i Y dels elements, alineació i afegim el component al panell
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(txtPassword, gbc);
        // ---------------------------------------------

        // Botó de login
        btnLogin = new JButton("Login");
        // Posició X i Y dels elements, alineació i afegim el component al panell
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(btnLogin, gbc);
        // ---------------------------------------------

        panel.add(loginPanel, BorderLayout.CENTER);

        // Afegim la funcionalitat del botó de login i també controlem l'error de connexió a la base de dades
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Intentem fer login trucant al metode login
                    login();
                } catch (SQLException ex) {
                    // En cas d'error mostrar-ho per pantalla com missatge d'error amb Siwng
                    JOptionPane.showMessageDialog(FinestraLogin.this, "No s'ha pogut establic connexió amb la base de dades", "Error", JOptionPane.ERROR_MESSAGE);
                    
                    //Comentem l'excepció perquè no ens interessa mostrar-la per pantalla
                    //ex.printStackTrace();
                }
            }
        });

        // Afegim el panell a la finestra principal
        add(panel);

        // Fem visible la finestra
        setVisible(true);
    }

    private void login() throws SQLException {
        // Obtenim les dades dels camps de text
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());

        // Obtenim la connexio a la base de dades
        Connection connection = Connexio.getConnection();

        // Preparem la consulta SQL per a comprovar si l'usuari existeix
        String query = "SELECT * FROM Usuaris WHERE Email = ? AND Contrasenya = ? LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(query);

        // Afegim els parametres a la consulta
        statement.setString(1, email);
        statement.setString(2, password);

        // TODO -> Utilitzar cifratges per a les contrasenyes!!!

        // Executem la consulta
        ResultSet resultSet = statement.executeQuery();

        // Si l'usuari existeix, obrim la interficie corresponent
        if (resultSet.next()) {
            String rol = resultSet.getString("Rol");
            if (rol.equals("bibliotecari")) {
                // Obrir interficie de bibliotecari
                Usuari usuari = new Usuari(
                    resultSet.getInt("ID_Usuari"),
                    resultSet.getString("Nom"),
                    resultSet.getString("Cognoms"),
                    resultSet.getString("Email"),
                    resultSet.getString("Telèfon"),
                    resultSet.getString("Rol"),
                    resultSet.getDate("Data_Registre")
                );
                new FinestraBibliotecari(usuari);
                System.out.println("Bibliotecari obrint finestra...");
            } else {
                // Obrir interficie de lector
                Usuari usuari = new Usuari(
                    resultSet.getInt("ID_Usuari"),
                    resultSet.getString("Nom"),
                    resultSet.getString("Cognoms"),
                    resultSet.getString("Email"),
                    resultSet.getString("Telèfon"),
                    resultSet.getString("Rol"),
                    resultSet.getDate("Data_Registre")
                );
                new FinestraLector(usuari);
                System.out.println("Lector obrint finestra...");
            }

            // Tanquem la finestra de login i alliberem els recursos
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuari o contrasenya incorrectes", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Tanquem la connexió
        statement.close();
        connection.close();
    }
}
