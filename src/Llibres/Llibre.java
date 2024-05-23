package Llibres;

public class Llibre {
    private int id = 0;
    private String titol;
    private String autor;
    private String isbn;
    private String editorial;
    private int anyPublicacio;
    private String categoria;
    private String estat;

    // Aquest constructor és necesari per a que poguem treballar amb el llibre i la connexió a la base de dades posteriorment
    public Llibre(int id, String titol, String autor, String isbn, String editorial, int anyPublicacio, String categoria, String estat) {
        this.id = id;
        this.titol = titol;
        this.autor = autor;
        this.isbn = isbn;
        this.editorial = editorial;
        this.anyPublicacio = anyPublicacio;
        this.categoria = categoria;
        this.estat = estat;
    }

    // Aquest constructor és necesari per quan necessitem afegir un nou llibre a la base de dades, per exemple
    public Llibre(String titol, String autor, String isbn, String editorial, int anyPublicacio, String categoria, String estat) {
        this.titol = titol;
        this.autor = autor;
        this.isbn = isbn;
        this.editorial = editorial;
        this.anyPublicacio = anyPublicacio;
        this.categoria = categoria;
        this.estat = estat;
        /*  esto se muesta por consola 
        if (isValidTitol(titol)) {
            this.titol = titol;
        } else {
            throw new IllegalArgumentException("Títol no vàlid");
        }

        if (isValidAutor(autor)) {
            this.autor = autor;
        } else {
            throw new IllegalArgumentException("Autor no vàlid");
        }

        if (isValidIsbn(isbn)) {
            this.isbn = isbn;
        } else {
            throw new IllegalArgumentException("ISBN no vàlid");
        }

        if (isValidEditorial(editorial)) {
            this.editorial = editorial;
        } else {
            throw new IllegalArgumentException("Editorial no vàlida");
        }

        if (isValidAnyPublicacio(anyPublicacio)) {
            this.anyPublicacio = anyPublicacio;
        } else {
            throw new IllegalArgumentException("Any de publicació no vàlid");
        }

        if (isValidCategoria(categoria)) {
            this.categoria = categoria;
        } else {
            throw new IllegalArgumentException("Categoria no vàlida");
        }

        if (isValidEstat(estat)) {
            this.estat = estat;
        } else {
            throw new IllegalArgumentException("Estat no vàlid");
        }
        */
    }

    public int getId() {
        return this.id;
    }

    public String getTitol() {
        return this.titol;
    }

    public String getAutor() {
        return this.autor;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getEditorial() {
        return this.editorial;
    }

    public int getAnyPublicacio() {
        return this.anyPublicacio;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public String getEstat() {
        return this.estat;
    }
    // De moment els Setters no són necessaris, ja que no tenim cap funció que modifiqui les dades del llibre

    /*
    public void setId(int id) {
        this.id = id;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public void setAutorautor(String autor) {
        this.autor = autor;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setAnyPublicacio(int anyPublicacio) {
        this.anyPublicacio = anyPublicacio;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }
    */

    public String toString() {
        return "Llibre [id=" + id + ", titol=" + titol + ", autor=" + autor + ", isbn=" + isbn + ", editorial=" + editorial + ", anyPublicacio=" + anyPublicacio + ", categoria=" + categoria + ", estat=" + estat + "]";
    }
}
