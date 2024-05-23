// Source code is decompiled from a .class file using FernFlower decompiler.

package Usuaris;

import java.sql.Date;
import org.mindrot.jbcrypt.BCrypt;

public class Usuari {
   private int id;
   private String nom;
   private String cognoms;
   private String email;
   private String telefon;
   private String rol;
   private Date dataRegistre;
   private String contrasenya;

   public Usuari(int id, String nom, String cognoms, String email, String telefon, String rol, Date dataRegistre) {
      this.id = id;
      this.nom = nom;
      this.cognoms = cognoms;
      this.email = email;
      this.telefon = telefon;
      this.rol = rol;
      this.dataRegistre = dataRegistre;
   }

   public Usuari(String nom, String cognoms, String email, String telefon, String rol, Date dataRegistre) {
      this.nom = nom;
      this.cognoms = cognoms;
      this.email = email;
      this.telefon = telefon;
      this.rol = rol;
      this.dataRegistre = dataRegistre;
   }

   public Usuari(String nom, String cognoms, String email, String telefon, String rol, Date dataRegistre, String contrasenya) {
      this.nom = nom;
      this.cognoms = cognoms;
      this.email = email;
      this.telefon = telefon;
      this.rol = rol;
      this.dataRegistre = dataRegistre;
      this.contrasenya = contrasenya;
   }

   public Usuari(int id, String nom, String cognoms, String email, String telefon, String rol, Date dataRegistre, String contrasenya) {
      this.nom = nom;
      this.cognoms = cognoms;
      this.email = email;
      this.telefon = telefon;
      this.rol = rol;
      this.dataRegistre = dataRegistre;
      this.contrasenya = contrasenya;
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

   public String getContrasenya() {
      return this.contrasenya;
    }

    public void setContrasenya(String contrasenya) {
      this.contrasenya = BCrypt.hashpw(contrasenya, BCrypt.gensalt());
    }

    public boolean checkContrasenya(String contrasenya) {
      return BCrypt.checkpw(contrasenya, this.contrasenya);
    }

   public String toString() {
      int var10000 = this.id;
      return "ID: " + var10000 + " | Nom: " + this.nom + " | Cognoms: " + this.cognoms + " | Email: " + this.email + " | Tel\u00e8fon: " + this.telefon + " | Rol: " + this.rol + " | Data de registre: " + String.valueOf(this.dataRegistre);
   }
}