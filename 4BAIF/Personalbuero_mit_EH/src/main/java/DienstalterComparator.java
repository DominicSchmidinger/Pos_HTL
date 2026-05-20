import java.util.Comparator;

public class DienstalterComparator implements Comparator<Mitarbeiter> {
    @Override
    public int compare(Mitarbeiter o1, Mitarbeiter o2) {
        return Integer.compare(o1.berechneDienstalter(), o2.berechneDienstalter());
    }
}
