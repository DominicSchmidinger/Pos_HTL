package at.spengergasse;

import java.time.Year;

public abstract class Messstation implements Comparable<Messstation>{
    private String standort;
    private Year installationsJahr;
    private double messwert;

    public Messstation(String standort, Year installationsJahr, double messwert) {
        setStandort(standort);
        setInstallationsJahr(installationsJahr);
        setMesswert(messwert);
    }

    public String getStandort() {
        return standort;
    }

    public void setStandort(String standort) {
        if(standort == null || standort.isBlank()) {
            System.out.println("setStandort: Darf nicht null oder leer sein!");
        }
        this.standort = standort;
    }

    public Year getInstallationsJahr() {
        return installationsJahr;
    }

    public void setInstallationsJahr(Year installationsJahr) {
        if(installationsJahr!=null && installationsJahr.isAfter(Year.of(1880))) {
            this.installationsJahr = installationsJahr;
        }else{
            System.out.println("setInstallationsJahr: Wert muss nach 1880 liegen!");
        }
    }

    public double getMesswert() {
        return messwert;
    }

    public void setMesswert(double messwert) {
        if(messwert >= 0) {
            this.messwert = messwert;
        }else{
            System.out.println("setMesswert: Wert muss über 0 liegen!");
        }
    }

    public abstract double berechneUmweltIndex();

    public String getStationTyp() {
        System.out.println(getClass());
        return getClass().toString();
    }

    @Override
    public int compareTo(Messstation o) {
        return Double.compare(messwert, o.getMesswert());
//        if(o.getMesswert() > getMesswert()) {
//            return -1;
//        }else if(o.getMesswert() < getMesswert()){
//            return 1;
//        }
//        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Messstation that = (Messstation) o;
        return Double.compare(messwert, that.messwert) == 0 && standort.equals(that.standort) && installationsJahr.equals(that.installationsJahr);
    }

    @Override
    public int hashCode() {
        int result = standort.hashCode();
        result = 31 * result + installationsJahr.hashCode();
        result = 31 * result + Double.hashCode(messwert);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Messtation: ");
        sb.append("Standort: '").append(standort).append('\'');
        sb.append(", Installationsjahr: ").append(installationsJahr);
        sb.append(", Messwert: ").append(messwert);
        sb.append(", Stationstyp: ").append(getClass().getSimpleName());
        return sb.toString();
    }
}
