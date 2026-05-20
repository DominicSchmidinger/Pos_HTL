import java.time.Year;

public class Film extends Medium {
    private int dauerMinuten;

    public Film(String titel, Year erscheinungsjahr, int bewertungen, int dauerMinuten) throws MedienException {
        super(titel, erscheinungsjahr, bewertungen);
        setDauerMinuten(dauerMinuten);
    }

    public int getDauerMinuten() {
        return dauerMinuten;
    }

    public void setDauerMinuten(int dauerMinuten) throws MedienException {
        // TODO: Dauer in Minuten muss größer als 0 sein
        if (dauerMinuten <= 0){
            throw new MedienException("Bro was bist du zeitreisender ?!");
        }
        this.dauerMinuten = dauerMinuten;
    }

    @Override
    public double berechneBeliebtheit() {
        // TODO: bewertungen * 2.0 berechnen
        return getBewertungen() * 2.0;
    }

    @Override
    public String toCsvString() {
        // TODO: Film als CSV-Zeile erzeugen
        return "";
    }

    @Override
    public String toString() {
        return "Film: " + super.toString() + ", Dauer: " + dauerMinuten + " Minuten";
    }
}
