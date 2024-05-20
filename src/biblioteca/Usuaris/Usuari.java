package biblioteca.Usuaris;

import java.sql.Date;

public class Usuari {
    private int id;
    private String nom;
    private String cognoms;
    private String email;
    private String telefon;
    private String rol;
    private Date dataRegistre;
    
    // Constructor necessari per a fer operacions a la base de dades amb usuaris
    public Usuari(int id, String nom, String cognoms, String email, String telefon, String rol, Date dataRegistre) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
        this.email = email;
        this.telefon = telefon;
        this.rol = rol;
        this.dataRegistre = dataRegistre;
    }

    // Constructor necesari per afegir un nou usuari a la base de dades, per exemple.
    public Usuari(String nom, String cognoms, String email, String telefon, String rol, Date dataRegistre) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.email = email;
        this.telefon = telefon;
        this.rol = rol;
        this.dataRegistre = dataRegistre;
    }

    public int getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getCognoms() {
        return this.cognoms;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public String getRol() {
        return this.rol;
    }

    public Date getDataRegistre() {
        return this.dataRegistre;
    }

    // Deshabilitem els setters perquè de moment no tenim codi que els necessiti. Els deixem creats per si en un futur són necessaris.
    /*
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setDataRegistre(Date dataRegistre) {
        this.dataRegistre = dataRegistre;
    }
    */

    public String toString() {
        return "ID: " + this.id + " | Nom: " + this.nom + " | Cognoms: " + this.cognoms + " | Email: " + this.email + " | Telèfon: " + this.telefon + " | Rol: " + this.rol + " | Data de registre: " + this.dataRegistre;
    }
}
