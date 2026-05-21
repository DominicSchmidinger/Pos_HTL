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

    public String getTitel() { return titel; }
    public Year getErscheinungsjahr() { return erscheinungsjahr; }
    public int getBewertungen() { return bewertungen; }

    public void setTitel(String titel) throws MedienException {
        if (titel == null || titel.isBlank())
            throw new MedienException("Titel darf nicht leer sein");
        // trim() entfernt führende/abschließende Leerzeichen
        this.titel = titel.trim();
    }

    public void setErscheinungsjahr(Year erscheinungsjahr) throws MedienException {
        // FIX: && war falsch -> || (null ODER zu alt)
        if (erscheinungsjahr == null || erscheinungsjahr.isBefore(Year.of(2000)))
            throw new MedienException("Erscheinungsjahr muss nach 2000 liegen");
        this.erscheinungsjahr = erscheinungsjahr;
    }

    public void setBewertungen(int bewertungen) throws MedienException {
        if (bewertungen < 0)
            throw new MedienException("Bewertungen dürfen nicht negativ sein");
        this.bewertungen = bewertungen;
    }

    public abstract double berechneBeliebtheit();

    public String toCsvString() {
        // gemeinsame Felder: Titel;Jahr;Bewertungen
        return String.format("%s;%s;%d", titel, erscheinungsjahr, bewertungen);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
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