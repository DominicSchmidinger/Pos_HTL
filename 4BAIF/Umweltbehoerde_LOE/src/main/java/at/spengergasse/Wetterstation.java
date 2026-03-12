package at.spengergasse;

import java.time.Year;

public class Wetterstation extends Messstation{
    private double temperatur;

    public Wetterstation(String standort, Year installationsJahr, double messwert, double temperatur) {
        super(standort, installationsJahr, messwert);
        setTemperatur(temperatur);
    }

    public double getTemperatur() {
        return temperatur;
    }

    public void setTemperatur(double temperatur) {
        if(temperatur > -200.0 && temperatur < 200.0) { // Annahme: exklusive
            this.temperatur = temperatur;
        } else {
            System.out.println("setTemperatur: Wert muss zwischen -200.0 und 200.0 liegen!");
        }
    }

    @Override
    public double berechneUmweltIndex() {
        if(getMesswert() > temperatur) {
            return getMesswert() + (temperatur * 0.5);
        }
        return getMesswert();
    }

    @Override
    public String getStationTyp() {
        return "Wetterstation";
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", Temperatur: ").append(getTemperatur());
        return sb.toString();
    }
}
