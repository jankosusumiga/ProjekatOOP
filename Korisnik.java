public class Korisnik {
    private String ime;
    private String prezime;

    public Korisnik(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getPunoIme() {
        return ime + " " + prezime;
    }

}
