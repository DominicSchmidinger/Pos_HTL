import java.time.Year;

public abstract class Medium {
    private String titel;
    private Year erscheinungsjahr;
    private int bewertungen;

    public Medium(String titel, Year erscheinungsjahr, int bewertungen) throws MedienException {
        setTitel(titel);
        setErscheinungsjahr(erscheinungsjahr);
        setBewertungen(bewertungen);
    }

    public String getTitel() {
        return titel;
    }

    public Year getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public int getBewertungen() {
        return bewertungen;
    }

    public void setTitel(String titel) throws MedienException {
        // TODO: Titel prüfen
        if (titel == null || titel.isBlank() || titel.startsWith(" ") || titel.endsWith(" ")){
            throw new MedienException("Bro mal titel oda mach weg diese leerzeichen");
        }
        this.titel = titel;
    }

    public void setErscheinungsjahr(Year erscheinungsjahr) throws MedienException {
        // TODO: Erscheinungsjahr prüfen
        if (erscheinungsjahr == null && erscheinungsjahr.isBefore(Year.of(2000))){
            throw new MedienException("Bro jojo mach mal anderes jahr");
        }

        this.erscheinungsjahr = erscheinungsjahr;
    }

    public void setBewertungen(int bewertungen) throws MedienException {
        // TODO: Bewertungen müssen größer oder gleich 0 sein
        if (bewertungen < 0){
            throw new MedienException("Bro was für negativ so schlimm wars nicht");
        }
        this.bewertungen = bewertungen;
    }

    public abstract double berechneBeliebtheit();

    public String toCsvString() {
        // TODO: gemeinsame CSV-Felder erzeugen
        return "";
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Medium that = (Medium) other;
        return bewertungen == that.bewertungen
                && titel.equals(that.titel)
                && erscheinungsjahr.equals(that.erscheinungsjahr);
    }

    @Override
    public int hashCode() {
        int result = titel.hashCode();
        result = 31 * result + erscheinungsjahr.hashCode();
        result = 31 * result + bewertungen;
        return result;
    }

    @Override
    public String toString() {
        return "Titel: " + titel
                + ", Erscheinungsjahr: " + erscheinungsjahr
                + ", Bewertungen: " + bewertungen
                + ", Beliebtheit: " + berechneBeliebtheit();
    }
}
