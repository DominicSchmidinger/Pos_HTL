package at.spengergasse;

import java.util.Comparator;

public class WerkstoffQualitaetsIndexComparator implements Comparator<Werkstoffprobe> {
    @Override
    public int compare(Werkstoffprobe o1, Werkstoffprobe o2) {
        return Double.compare(o2.berechneQualitaetsIndex(),  o1.berechneQualitaetsIndex());
    }
}
