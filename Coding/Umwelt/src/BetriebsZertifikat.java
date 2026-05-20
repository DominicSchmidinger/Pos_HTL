public class BetriebsZertifikat extends UmweltZertifikat{
    private int mitarbeiter;

    public BetriebsZertifikat(String id, String name, double co2ProJahr, double recyclingQuote) {
        super(id, name, co2ProJahr, recyclingQuote);
        setMitarbeiter(mitarbeiter);
    }

    public int getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(int mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    @Override
    public double berechneNachhaltigkeitsScore() {
        return 0;
    }
}

