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

    // Validació de Llibres
    private boolean isValidTitol(String titol) {
        return titol != null && !titol.trim().isEmpty();
    }

    private boolean isValidAutor(String autor) {
        return autor != null && !autor.trim().isEmpty();
    }

    private boolean isValidIsbn(String isbn) {
        String isbnRegex = "^(97(8|9))?\\d{9}(\\d|X)$";
        return isbn != null && isbn.matches(isbnRegex);
    }

    private boolean isValidEditorial(String editorial) {
        return editorial != null && !editorial.trim().isEmpty();
    }

    private boolean isValidAnyPublicacio(int anyPublicacio) {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        return anyPublicacio > 0 && anyPublicacio <= currentYear;
    }

    private boolean isValidCategoria(String categoria) {
        return categoria != null && !categoria.trim().isEmpty();
    }

    private boolean isValidEstat(String estat) {
        return estat != null && !estat.trim().isEmpty();
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
