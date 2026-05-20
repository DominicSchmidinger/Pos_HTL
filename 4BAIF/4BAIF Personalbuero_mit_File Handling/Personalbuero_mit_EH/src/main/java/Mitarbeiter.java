import java.time.Year;

public abstract class Mitarbeiter implements Comparable<Mitarbeiter> {

    private String name;
    private Year gebJahr;
    private Year eintrJahr;

    public Mitarbeiter(String name, Year gebJahr, Year eintrJahr) throws PersonalException {
        setName(name);
        setGebJahr(gebJahr);
        setEintrJahr(eintrJahr);
    }

    public void setEintrJahr(Year eintrJahr) throws PersonalException {
        if (eintrJahr != null) {
            if (eintrJahr.isAfter(gebJahr)) {
                this.eintrJahr = eintrJahr;
            } else {
                //System.out.println("Fehler: eintrJahr muss nach dem gebJahr liegen");
                throw new PersonalException("Fehler: eintrJahr muss nach dem gebJahr liegen");
            }
        } else {
            //System.out.println("Fehler: eintrJahr ist ungültig");
            throw new PersonalException("Fehler: eintrJahr ist ungültig");
        }
    }

    public void setGebJahr(Year gebJahr) throws PersonalException {
        if (gebJahr != null && !gebJahr.isAfter(Year.now())) {
            this.gebJahr = gebJahr;
        } else {
            //System.out.println("Fehler: gebJahr ist ungültig");
            throw new PersonalException("Fehler: gebJahr ist ungültig");
        }
    }

    public void setName(String name) throws PersonalException {
        if (name != null && !name.isBlank()) {
            this.name = name;
        } else {
            //System.out.println("Fehler: name ungültig");
            throw new PersonalException("Fehler: name ungültig");
        }
    }

    public String getName() {
        return name;
    }

    public Year getGebJahr() {
        return gebJahr;
    }

    public Year getEintrJahr() {
        return eintrJahr;
    }

    public int berechneAlter() {
        return Year.now().getValue() - gebJahr.getValue();
    }

    public int berechneDienstalter() {
        return Year.now().getValue() - eintrJahr.getValue();
    }

    public abstract double berechneGehalt();

    public double berechnePraemie() {
        switch (berechneDienstalter()) {
            case 15:
                return berechneGehalt(); // break;
            case 20:
                return 2 * berechneGehalt();
            case 25:
                return 3 * berechneGehalt();
            case 50:
                return 6 * berechneGehalt();
            default:
                return 0.0;
        }

//        if (berechneDienstalter() == 15) {
//            return berechneGehalt();
//        }
//        if (berechneDienstalter() == 20) {
//            return 2* berechneGehalt();
//        }
//        return 0.0;
    }

    @Override
    public int compareTo(Mitarbeiter o) {
        return name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Mitarbeiter that = (Mitarbeiter) o;
        return name.equals(that.name) && gebJahr.equals(that.gebJahr) && eintrJahr.equals(that.eintrJahr);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + gebJahr.hashCode();
        result = 31 * result + eintrJahr.hashCode();
        return result;
    }


    public String toCsvString() {
        // Angestellter;Anna;2001;2026
        return getClass().getSimpleName() + ";" + name + ";" + gebJahr + ";" + eintrJahr;
    }


    @Override
    public String toString() {
        return "Name: " + name +
                ", Geb.Jahr: " + gebJahr +
                ", Alter: " + berechneAlter() +
                ", Eintr.Jahr: " + eintrJahr +
                ", Dienstalter: " + berechneDienstalter() +
                ", Gehalt: " + berechneGehalt();
    }

    public void print() {
        System.out.println(this);
    }

}
