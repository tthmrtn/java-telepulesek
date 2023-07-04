import java.util.ArrayList;
import java.util.List;

public class Telepules {
    private String nev;
    private String email;
    private List<Kerulet> keruletek;
    private double terulet;

    public Telepules(String nev, double terulet) {
        if (nev.charAt(0) == nev.toUpperCase().charAt(0)) {
            this.nev = nev;
        } else {
            this.nev = nev;
            throw new IllegalArgumentException("Hibas varosnev: " + nev);
        }
        this.terulet = (terulet <= 0) ? 1 : terulet;
        this.keruletek = new ArrayList<Kerulet>();
    }

    public double nepsuruseg() {
        return 0;
    }


    public String getEmail() {
        return email;
    }

    public void emailFrissitese(String email) throws TelepuleskezeloException {
        if (email.startsWith("info") && email.endsWith(".hu") && email.contains("@") && email.split("@").length == 2) {
            this.email = email;
        } else throw new TelepuleskezeloException(this, "Hibas e-mail cim: " + email);
    }

    public String getNev() {
        return nev;
    }

    public double getTerulet() {
        return terulet;
    }

    public void setTerulet(double terulet) {
        this.terulet = terulet;
    }

    public void ujLakok(String kerulet, int lakokDb) throws TelepuleskezeloException {
        boolean contained = false;
        for (Kerulet k : this.keruletek) {
            if (kerulet.toLowerCase().equals(k.getNev().toLowerCase())) {
                k.setLakosokSzama(k.getLakosokSzama() + 1);
                contained = true;
            }
        }
        if (!contained) throw new TelepuleskezeloException(this, "Nem talalhato a megadott kerulet: " + kerulet);
    }

    public void ujKerulet(String nev, int lakokDb) {
        this.keruletek.add(new Kerulet(nev, lakokDb));
    }

    public int getLakosokSzama() {
        int ossz = 0;
        for (Kerulet k : this.keruletek) {
            ossz += k.getLakosokSzama();
        }
        return ossz;
    }

    public class Kerulet{
        private int lakosokSzama;
        private final String nev;
        private Kerulet(String nev, int lakosokSzama){
            this.lakosokSzama = lakosokSzama;
            this.nev = nev;
        }

        public String toString(){
            return this.nev+"("+Telepules.this.getNev()+")";
        }

        public double LakokAranya(){
            return this.getLakosokSzama()/Telepules.this.getLakosokSzama();
        }

        public int getLakosokSzama() {
            return this.lakosokSzama;
        }

        public void setLakosokSzama(int lakosokSzama) {
            this.lakosokSzama = lakosokSzama;
        }

        public String getNev() {
            return this.nev;
        }
    }
}

