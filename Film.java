public class Film {
    private String naziv;
    private String datum;
    private String vreme;
    private Mesto[] mesta; // niz mesta
    private double cena;

    public Film(String naziv, String datum, String vreme, int brojMesta) {
        this.naziv = naziv;
        this.datum = datum;
        this.vreme = vreme;
        this.mesta = new Mesto[brojMesta];
        for (int i = 0; i < brojMesta; i++) {
            mesta[i] = new Mesto(i+1);
        }
    }

    public void setCenaKarte(double cenaKarte) {
        this.cena = cenaKarte;
    }

    public Mesto[] getMesta() {
        return mesta;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getDatum() {
        return datum;
    }

    public String getVreme() {
        return vreme;
    }

    public double getZarada() {
        int brojac = 0;
        for (Mesto mesto : mesta) {
            if (mesto.isZauzeto())
                brojac++;
        }
        return brojac * cena;
    }


    @Override
    public String toString() {
        return naziv;
    }

}
