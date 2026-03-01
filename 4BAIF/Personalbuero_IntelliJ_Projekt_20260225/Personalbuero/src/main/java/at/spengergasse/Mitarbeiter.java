package at.spengergasse;

import java.time.Year;
import java.util.Locale;
import java.util.Objects;

public abstract class Mitarbeiter implements Comparable<Mitarbeiter> {
    private String name;
    private Year gebJahr;
    private Year eintrJahr;

    public Mitarbeiter(String name, Year gebJahr, Year eintrJahr) {
        setName(name);
        setGebJahr(gebJahr);
        setEintrJahr(eintrJahr);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name!=null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Fehler: name ungültig");
        }
    }

    public Year getGebJahr() {
        return gebJahr;
    }

    public void setGebJahr(Year gebJahr) {
        if (gebJahr!=null && !gebJahr.isAfter(Year.now())) {
            this.gebJahr = gebJahr;
        } else {
            System.out.println("Fehler: gebJahr ungültig");
        }
    }

    public Year getEintrJahr() {
        return eintrJahr;
    }

    public void setEintrJahr(Year eintrJahr) {
        if(eintrJahr!=null&&!eintrJahr.isAfter(Year.now())) {
            this.eintrJahr = eintrJahr;
        } else {
            System.out.println("Fehler: eintrJahr ungültig");
        }
    }

    // ----------------- weitere Methoden
    public int berechneAlter() {
        return Year.now().getValue() - gebJahr.getValue();
    }

    public int berechneDienstalter() {
        return Year.now().getValue() - eintrJahr.getValue();
    }

    public abstract double berechneGehalt(); //eine abstracte Methode

    public float berechnePraemie() {
       switch (berechneDienstalter()) {
            case 15: return Math.round(berechneGehalt()*100)/100f;
            case 20: return 2*Math.round(berechneGehalt()*100)/100f;
            case 25: return 3*Math.round(berechneGehalt()*100)/100f;
            case 50: return 6*Math.round(berechneGehalt()*100)/100f;
            default: return 0f;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Mitarbeiter that = (Mitarbeiter) o;
        return Objects.equals(name, that.name) && Objects.equals(gebJahr, that.gebJahr) &&
                Objects.equals(eintrJahr, that.eintrJahr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gebJahr, eintrJahr);
    }

    @Override
    public int compareTo(Mitarbeiter o) {  // "natürliche Ordnung"
        // Details kommen beim ExceptionHandling
//        if(o == null) {
//            throw new NullPointerException();
//        }
//        if (o.getClass() != getClass()) {
//            throw new ClassCastException();
//        }
        //name -> String
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH,"Name: %s, Geb.Jahr: %d, Alter: %d, Eintr.Jahr: %d, Dienstalter: %d, Gehalt: %.1f, Prämie: %.1f",
                                    name, gebJahr.getValue(),berechneAlter(),eintrJahr.getValue(),berechneDienstalter(),berechneGehalt(),berechnePraemie());
    }

    public void print() {
        System.out.println(this);
    }
}
