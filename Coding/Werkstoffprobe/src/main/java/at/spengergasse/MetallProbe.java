package at.spengergasse;

import at.spengergasse.Werkstoffprobe;

public class MetallProbe extends Werkstoffprobe {
    private double zugfestigkeit;
    private double korrisonsWert;

    public MetallProbe(String id, double dichte, String bezeichnung, double masse, double zugfestigkeit, double korrisonsWert) {
        super(id, dichte, bezeichnung, masse);
        setZugfestigkeit(zugfestigkeit);
        setKorrisonsWert(korrisonsWert);
    }

    public double getZugfestigkeit() {
        return zugfestigkeit;
    }

    public void setZugfestigkeit(double zugfestigkeit) {
        if (zugfestigkeit < 0){
            throw new IllegalArgumentException("zugfestigkeit kann nicht negativ sein");
        }
        this.zugfestigkeit = zugfestigkeit;
    }

    public double getKorrisonsWert() {
        return korrisonsWert;
    }

    public void setKorrisonsWert(double korrisonsWert) {
        if (korrisonsWert < 0 || korrisonsWert >  100){
            throw new IllegalArgumentException("korrisonswert muss zwischen 0-100 sein (laut prof halt ka was das is)");
        }
        this.korrisonsWert = korrisonsWert;
    }

    @Override
    public double berechneQualitaetsIndex(){
        double qualitaetsindex = 0;
        qualitaetsindex = zugfestigkeit/ (korrisonsWert +1);
        return qualitaetsindex;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MetallProbe: [");
        sb.append(super.toString());
        sb.append(", korrisonsWert=").append(korrisonsWert);
        sb.append(", zugfestigkeit=").append(zugfestigkeit);
        sb.append(']');
        return sb.toString();
    }
}
