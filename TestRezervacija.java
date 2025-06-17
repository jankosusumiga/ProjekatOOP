import javax.swing.*;
import java.util.ArrayList;

public class TestRezervacija extends JFrame {

    private DefaultListModel modelFilm = new DefaultListModel<>(); //  model koji sadrži listu filmova
    private JList<Film> listaFilm = new JList<>(modelFilm); // GUI komponenta koja prikazuje sve dodate filmove
    private JTextArea prikazMesta = new JTextArea(); // tekstualno polje koje prikazuje sva mesta za izabrani film
    private JTextField poljeIme = new JTextField(); // unos imena i prezimena korisnika koji rezerviše kartu
    private JTextField poljePrezime = new JTextField();
    private JTextField izracunajZaradu = new JTextField(); // prikazuje izračunatu zaradu za izabrani film
    private ArrayList<Rezervacija> listaRezervacija = new ArrayList<>(); // lista svih napravljenih rezervacija (veza korisnik–film–mesto)

    public TestRezervacija() {
        setTitle("Sistem za rezervaciju karata");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 450);
        setLayout(null);
        setResizable(false);

        JLabel labela1 = new JLabel("Filmovi:");
        labela1.setBounds(10,10,50,20);
        add(labela1);
        JScrollPane scrollPane1 = new JScrollPane(listaFilm);
        scrollPane1.setBounds(10,30,280,200);
        add(scrollPane1);

        prikazMesta.setEditable(false);
        JLabel labela2 = new JLabel("Mesta:");
        labela2.setBounds(300,10,50,20);
        add(labela2);
        JScrollPane scrollPane2 = new JScrollPane(prikazMesta);
        scrollPane2.setBounds(300,30,280,200);
        add(scrollPane2);

        JTextField poljeBrojMesta = new JTextField();
        JButton dugmeRezervisi = new JButton("Rezerviši");
        JButton dugmePregled = new JButton("Pregled rezervacija");
        JButton dugmeDodaj = new JButton("Dodaj film");

        poljeIme.setBounds(10, 300, 200, 20);
        poljePrezime.setBounds(10, 340, 200, 20);
        poljeBrojMesta.setBounds(10, 380, 200, 20);

        izracunajZaradu.setEditable(false);
        izracunajZaradu.setBounds(365, 360, 150, 20);
        JLabel labela6 = new JLabel("Ukupna zarada filma:");
        labela6.setBounds(380, 340, 150, 20);

        JLabel labela3 = new JLabel("Ime:");
        labela3.setBounds(10, 280, 100, 20);
        JLabel labela4 = new JLabel("Prezime:");
        labela4.setBounds(10, 320, 100, 20);
        JLabel labela5 = new JLabel("Broj mesta:");
        labela5.setBounds(10, 360, 100, 20);

        dugmeDodaj.setBounds(10, 230, 280, 40);
        dugmePregled.setBounds(300, 230, 280, 40);
        dugmeRezervisi.setBounds(300, 270, 280, 40);

        add(labela3);
        add(labela4);
        add(labela5);
        add(labela6);
        add(poljeIme);
        add(poljePrezime);
        add(poljeBrojMesta);
        add(izracunajZaradu);
        add(dugmeRezervisi);
        add(dugmePregled);
        add(dugmeDodaj);


        // otvara input dijalog za film
        dugmeDodaj.addActionListener(e -> {
            String naziv = JOptionPane.showInputDialog(this, "Naziv filma:");
            double cenaKarte = Double.valueOf(JOptionPane.showInputDialog(this, "Cena karte:"));
            // ako naziv nije prazan
            if (naziv != null && !naziv.isEmpty()) {
                Film p = new Film(naziv, "17.06.2025", "20:00", 10);
                p.setCenaKarte(cenaKarte);
                modelFilm.addElement(p);
            }
        });

        // prikaz mesta za izabrani film
        listaFilm.addListSelectionListener(e -> {
            Film p = listaFilm.getSelectedValue();
            if (p != null) {
                prikazMesta.setText("");
                for (Mesto m : p.getMesta()) {
                    prikazMesta.append("Mesto " + m.getBroj() + ": " + (m.isZauzeto() ? "Zauzeto" : "Slobodno") + "\n");
                }
                double zarada = p.getZarada();
                izracunajZaradu.setText(String.valueOf(zarada));
            }
        });

        // Rezervacija
        dugmeRezervisi.addActionListener(e -> {
            Film p = listaFilm.getSelectedValue();
            if (p == null) {
                JOptionPane.showMessageDialog(this, "Izaberite film."); // ako korisnik nije izabrao film dobija error
                return;
            }

            // trim funkcija sluzi da se ocisti unos od razmaka sa strane
            String ime = poljeIme.getText().trim();
            String prezime = poljePrezime.getText().trim();
            String brojStr = poljeBrojMesta.getText().trim();

            if (ime.isEmpty() || prezime.isEmpty() || brojStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Popunite sva polja.");
                return;
            }

            try {
                int broj = Integer.parseInt(brojStr);
                System.out.println(broj);
                if (broj < 1 || broj > p.getMesta().length) throw new Exception();

                Mesto m = p.getMesta()[broj - 1];
                if (m.isZauzeto()) {
                    JOptionPane.showMessageDialog(this, "Mesto je već zauzeto.");
                } else {
                    Korisnik k = new Korisnik(ime, prezime);
                    Rezervacija r = new Rezervacija(k, p, m);
                    listaRezervacija.add(r);
                    JOptionPane.showMessageDialog(this, "Uspešno rezervisano!");

                    // Očisti formu
                    poljeBrojMesta.setText("");
                    poljeIme.setText("");
                    poljePrezime.setText("");
                    prikazMesta.setText("");
                    listaFilm.clearSelection();
                    izracunajZaradu.setText("");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Neispravan broj mesta.");
            }
        });

        // Prikaz svih rezervacija
        dugmePregled.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Rezervacija r : listaRezervacija) {
                sb.append(r.getInfo()).append("\n");
            }
            JOptionPane.showMessageDialog(this, !sb.isEmpty() ? sb.toString() : "Nema rezervacija.");
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        TestRezervacija tr = new TestRezervacija();
    }
}
