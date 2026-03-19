import java.util.Comparator;

public class PreisComperator implements Comparator<Maschine> {
    @Override
    public int compare(Maschine o1, Maschine o2) {
        return Double.compare(o2.getPreiEur(), o1.getPreiEur());
    }
}
