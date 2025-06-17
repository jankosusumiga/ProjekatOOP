public class Rezervacija {
    private Korisnik korisnik;
    private Film film;
    private Mesto mesto;

    public Rezervacija(Korisnik korisnik, Film film, Mesto mesto) {
        this.korisnik = korisnik;
        this.film = film;
        this.mesto = mesto;
        this.mesto.rezervisi();
    }

    public void otkazi() {
        this.mesto.otkazi();
    }

    public String getInfo() {
        return "Korisnik: " + korisnik.getPunoIme() +
                " | Predstava: " + film.getNaziv() +
                " | Mesto: " + mesto.getBroj();
    }
}
