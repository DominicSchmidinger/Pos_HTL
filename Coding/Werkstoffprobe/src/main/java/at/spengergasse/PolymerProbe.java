package at.spengergasse;

public class PolymerProbe extends Werkstoffprobe{
    private double glasuebergangsTemp;

    public PolymerProbe(String id, double dichte, String bezeichnung, double masse, double glasuebergangsTemp) {
        super(id, dichte, bezeichnung, masse);
        setGlasuebergangsTemp(glasuebergangsTemp);
    }

    public double getGlasuebergangsTemp() {
        return glasuebergangsTemp;
    }

    public void setGlasuebergangsTemp(double glasuebergangsTemp) {
        if (glasuebergangsTemp <= -273){
            throw new IllegalArgumentException("Physikallisch nicht möglich");
        }
        this.glasuebergangsTemp = glasuebergangsTemp;
    }

    @Override
    public double berechneQualitaetsIndex() {
        return glasuebergangsTemp * 0.1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PolymerProbe: [");
        sb.append(super.toString());
        sb.append("glasuebergangsTemp=").append(glasuebergangsTemp);
        sb.append(']');
        return sb.toString();
    }
}
