import java.util.Comparator;

public class AlterComparator implements Comparator<Mitarbeiter> {
    @Override
    public int compare(Mitarbeiter o1, Mitarbeiter o2) {
        // Datentyp von alter ist int -> Integer
        // o1.berechneAlter, o2.berechneAlter
        return Integer.compare(o1.berechneAlter(), o2.berechneAlter());
    }
}
