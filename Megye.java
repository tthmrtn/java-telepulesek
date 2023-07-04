import java.util.ArrayList;
import java.util.List;

public class Megye {
    private String nev;
    private String web;
    private List<Telepules> telepulesek;

    public Megye(String nev){
        this.nev = nev;
        this.web = "";
        this.telepulesek = new ArrayList<Telepules>();
    }

    public String toString(){
        return this.nev+" megye ("+this.web+")";
    }

    public boolean ujTelepules(String telepulesSzoveg){
        String[] data = telepulesSzoveg.split(":");
        Telepules temp = new Telepules(data[0],Double.parseDouble(data[1]));
        try {
            temp.emailFrissitese(data[2]);
        } catch (TelepuleskezeloException e) {
            throw new RuntimeException(e);
        }
        if (!temp.getEmail().equals(data[2])){
            return false;
        }
        return true;
    }

    public void ujLakok(int melyikVaros, String kerulet, int lakokSzama){
        if (this.telepulesek.size() >= melyikVaros){
            try {
                this.telepulesek.get(melyikVaros).ujLakok(kerulet,lakokSzama);
            } catch (TelepuleskezeloException e) {
                throw new IllegalArgumentException(this.telepulesek.get(melyikVaros).getNev()+" varosban nem letezik a megadott kerulet!", e);
            }
        } else {
            throw new IllegalArgumentException("Nem letezik a megadott indexu varos!");
        }
    }


    public void webcimFrissites(String web) {
        if (!web.toLowerCase().contains(this.nev.toLowerCase())){
            throw new IllegalArgumentException("Hibas webcim: "+web);
        } else {
            this.web = web;
        }
    }

    public int keres(String mire){
        int n = 0;
        for (Telepules t : this.telepulesek){
            if (t.getNev().contains(mire)){
                n++;
            }
        }
        return n;
    }

    public int lakossag(){
        int n = 0;
        for (Telepules t : this.telepulesek){
            n += t.getLakosokSzama();
        }
        return n;
    }
}
