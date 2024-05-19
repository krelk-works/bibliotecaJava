package Prestecs;

import java.sql.Date;

public class Prestec {
    private int id = 0;
    private int idLlibre;
    private int idUsuari;
    private Date dataPrestec;
    private Date dataRetornPrevista;
    private Date dataRetornReal;
    private String estat;

    // Constructor necesari per fer operacions amb la base de dades
    public Prestec(int id, int idLlibre, int idUsuari, Date dataPrestec, Date dataRetornPrevista, Date dataRetornReal, String estat) {
        this.id = id;
        this.idLlibre = idLlibre;
        this.idUsuari = idUsuari;
        this.dataPrestec = dataPrestec;
        this.dataRetornPrevista = dataRetornPrevista;
        this.dataRetornReal = dataRetornReal;
        this.estat = estat;
    }

    // Constructor necesari per afegir un nou prestec a la base de dades, per exemple.
    public Prestec(int idUsuari, int idLlibre, Date dataPrestec, Date dataRetornPrevista, String estat) {
        this.idUsuari = idUsuari;
        this.idLlibre = idLlibre;
        this.dataPrestec = dataPrestec;
        this.dataRetornPrevista = dataRetornPrevista;
        this.estat = estat;
    }

    public int getId() {
        return this.id;
    }

    public int getIdLlibre() {
        return this.idLlibre;
    }

    public int getIdUsuari() {
        return this.idUsuari;
    }

    public Date getDataPrestec() {
        return this.dataPrestec;
    }

    public Date getDataRetornPrevista() {
        return this.dataRetornPrevista;
    }

    public Date getDataRetornReal() {
        return this.dataRetornReal;
    }

    public String getEstat() {
        return this.estat;
    }

    // Deshabilitem els setters perquè no tenim cap necessitat d'utilitzar-los. Els deixem creats per si en un futur són necessaris.

    /*
    public void setId(int id) {
        this.id = id;
    }

    public void setIdLlibre(int idLlibre) {
        this.idLlibre = idLlibre;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public void setDataPrestec(Date dataPrestec) {
        this.dataPrestec = dataPrestec;
    }

    public void setDataRetornPrevista(Date dataRetornPrevista) {
        this.dataRetornPrevista = dataRetornPrevista;
    }

    public void setDataRetornReal(Date dataRetornReal) {
        this.dataRetornReal = dataRetornReal;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }
    */

    public String toString() {
        return "Prestec [id=" + id + ", idLlibre=" + idLlibre + ", idUsuari=" + idUsuari + ", dataPrestec=" + dataPrestec
                + ", dataRetornPrevista=" + dataRetornPrevista + ", dataRetornReal=" + dataRetornReal + ", estat=" + estat + "]";
    }
}
