package at.spengergasse;

import java.time.Year;

public class Laermmessstation extends Messstation{
    private double maxErlaubterPegel;

    public Laermmessstation(String standort, Year installationsJahr, double messwert, double maxErlaubterPegel) {
        super(standort, installationsJahr, messwert);
        setMaxErlaubterPegel(maxErlaubterPegel);
    }

    public double getMaxErlaubterPegel() {
        return maxErlaubterPegel;
    }

    public void setMaxErlaubterPegel(double maxErlaubterPegel) {
        if(maxErlaubterPegel > 0) {
            this.maxErlaubterPegel = maxErlaubterPegel;
        } else {
            System.out.println("setMaxErlaubterPegel: Wert muss über 0 liegen!");
        }
    }

    @Override
    public double berechneUmweltIndex() {
        return getMesswert() - maxErlaubterPegel;
    }

    @Override
    public String getStationTyp() {
        return "Laermmessstation";
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", maximal erlaubter Pegel: ").append(getMaxErlaubterPegel());
        return sb.toString();
    }
}
