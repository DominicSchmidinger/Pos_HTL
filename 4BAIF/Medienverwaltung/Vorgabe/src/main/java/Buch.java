import java.time.Year;

public class Buch extends Medium {
    private int seiten;

    public Buch(String titel, Year erscheinungsjahr, int bewertungen, int seiten) throws MedienException {
        super(titel, erscheinungsjahr, bewertungen);
        setSeiten(seiten);
    }

    public int getSeiten() {
        return seiten;
    }

    public void setSeiten(int seiten) throws MedienException {
        // TODO: Seiten müssen größer als 0 sein
        if (seiten <= 0)
            throw new MedienException("Bro Seite darf nicht null sein");
        this.seiten = seiten;
    }

    @Override
    public double berechneBeliebtheit() {
        // TODO: bewertungen * 1.5 berechnen


        return getBewertungen() * 1.5;
    }

    @Override
    public String toCsvString() {
        // TODO: Buch als CSV-Zeile erzeugen
        return String.format("Buch;%s;%s,%s", this.getTitel(), this.getErscheinungsjahr(), this.getBewertungen());
    }

    @Override
    public String toString() {
        return "Buch: " + super.toString() + ", Seiten: " + seiten;
    }
}
