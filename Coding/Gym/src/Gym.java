import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Gym {
    private double maxpreisEur;
    private int maxAnzahl;
    private List<Maschine> maschines;


    public Gym(double preisEur, int maxAnzahl) {
        this.maxpreisEur = preisEur;
        this.maxAnzahl = maxAnzahl;
        this.maschines = new ArrayList<>();
    }

    public double getMaxpreisEur() {
        return maxpreisEur;
    }

    public void setMaxpreisEur(double maxpreisEur) {
        if (maxpreisEur > 9000 || maxpreisEur < 0) {
            System.out.println("Ja...Nein");
        } else {
            this.maxpreisEur = maxpreisEur;
        }
    }

    public int getMaxAnzahl() {
        return maxAnzahl;
    }

    public void setMaxAnzahl(int maxAnzahl) {
        if (maxAnzahl < 5 || maxAnzahl > 100) {
            System.out.println("ja nein");
        } else {
            this.maxAnzahl = maxAnzahl;
        }
    }

    public boolean aufstellen(Maschine maschine) {
        if ((maschine.getPreiEur() > maxpreisEur) || (maschines.size() > maxAnzahl) || maschines.equals(maschine)) {
            return false;
        } else {
            return maschines.add(maschine);
        }
    }

    public double berechneAvrgPreis() {
        double ges = 0;

        for (Maschine m : maschines) {
            ges += m.getPreiEur();
        }
        return ges / maschines.size();
    }

    public void sortieren() {
        maschines.sort(null);
    }

    public void Preisortieren() {
        maschines.sort(new PreisComperator());
    }

    public int entferneAlle(char typ) {
        int count = 0;
        char low = Character.toLowerCase(typ);

        Iterator<Maschine> iter = maschines.iterator();

        while (iter.hasNext()) {
            Maschine m = iter.next();

            if (low == 'b' && m instanceof Beinpresse) {
                iter.remove();
                count++;
            } else if (low == 'e' && m instanceof Ergometer) {
                iter.remove();
                count++;
            }
        }
        return count;
    }

    public boolean entfernen(Maschine maschine){
        if (maschines.contains(maschine)){
            maschines.remove(maschine);
            return true;
        }
        return false;
    }

    public int maxIntervall(){
        int größte = 0;
        for (Maschine m : maschines){
            if (m.wartungIntervall() > größte){
                größte = m.wartungIntervall();
            }
        } return größte;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Gym{");
        sb.append("maxpreisEur=").append(maxpreisEur);
        sb.append(", maxAnzahl=").append(maxAnzahl);
        sb.append(", maschines=").append(maschines);
        sb.append('}');
        return sb.toString();
    }
}
