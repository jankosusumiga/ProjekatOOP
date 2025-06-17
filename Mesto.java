public class Mesto {
    private int broj;
    private boolean zauzeto;

    public Mesto(int broj) {
        this.broj = broj;
        this.zauzeto = false;
    }

    public int getBroj() {
        return broj;
    }

    public boolean isZauzeto() {
        return zauzeto;
    }

    public void rezervisi() {
        this.zauzeto = true;
    }

    public void otkazi() {
        this.zauzeto = false;
    }

}
