package at.spengergasse;

import java.time.Year;

public class Luftmessstation extends Messstation {
    private double feinstaubGrenzwert;

    public Luftmessstation(String standort, Year installationsJahr, double messwert, double feinstaubGrenzwert) {
        super(standort, installationsJahr, messwert);
        setFeinstaubGrenzwert(feinstaubGrenzwert);
    }

    public double getFeinstaubGrenzwert() {
        return feinstaubGrenzwert;
    }

    public void setFeinstaubGrenzwert(double feinstaubGrenzwert) {
        if(feinstaubGrenzwert > 0) {
            this.feinstaubGrenzwert = feinstaubGrenzwert;
        } else {
            System.out.println("setFeinstaubGrenzwert: Wert muss über 0 liegen!");
        }
    }

    @Override
    public double berechneUmweltIndex() {
        return getMesswert() / feinstaubGrenzwert;
    }

    @Override
    public String getStationTyp() {
        return "Luftmessstation";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Luftmessstation that = (Luftmessstation) o;
        return Double.compare(feinstaubGrenzwert, that.feinstaubGrenzwert) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Double.hashCode(feinstaubGrenzwert);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", Feinstaubgrenzwert: ").append(getFeinstaubGrenzwert());
        return sb.toString();
    }
}
