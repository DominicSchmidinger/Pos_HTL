import java.time.Year;

public class Buch extends Medium {
    private int seiten;

    public Buch(String titel, Year erscheinungsjahr, int bewertungen, int seiten) throws MedienException {
        super(titel, erscheinungsjahr, bewertungen);
        setSeiten(seiten);
    }

    public int getSeiten() { return seiten; }

    public void setSeiten(int seiten) throws MedienException {
        if (seiten <= 0)
            throw new MedienException("Seiten müssen größer als 0 sein");
        this.seiten = seiten;
    }

    @Override
    public double berechneBeliebtheit() {
        return getBewertungen() * 1.5;
    }

    @Override
    public String toCsvString() {
        // FIX: Seiten hinzugefügt, Format: Buch;Titel;Jahr;Bewertungen;Seiten
        return String.format("Buch;%s;%d", super.toCsvString(), seiten);
    }

    @Override
    public String toString() {
        return "Buch: " + super.toString() + ", Seiten: " + seiten;
    }
}