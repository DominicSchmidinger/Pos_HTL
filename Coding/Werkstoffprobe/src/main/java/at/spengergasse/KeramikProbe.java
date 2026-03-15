package at.spengergasse;

public class KeramikProbe extends Werkstoffprobe{
    public KeramikProbe(String id, double dichte, String bezeichnung, double masse) {
        super(id, dichte, bezeichnung, masse);
    }

    @Override
    public double berechneQualitaetsIndex() {
        return getDichte() * 2;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PolymerProbe: [");
        sb.append(super.toString());
        sb.append(']');
        return sb.toString();
    }
}
