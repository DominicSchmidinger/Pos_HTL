import java.time.Year;

public class Film extends Medium {
    private int dauerMinuten;

    public Film(String titel, Year erscheinungsjahr, int bewertungen, int dauerMinuten) throws MedienException {
        super(titel, erscheinungsjahr, bewertungen);
        setDauerMinuten(dauerMinuten);
    }

    public int getDauerMinuten() { return dauerMinuten; }

    public void setDauerMinuten(int dauerMinuten) throws MedienException {
        if (dauerMinuten <= 0)
            throw new MedienException("Dauer muss größer als 0 sein");
        this.dauerMinuten = dauerMinuten;
    }

    @Override
    public double berechneBeliebtheit() {
        return getBewertungen() * 2.0;
    }

    @Override
    public String toCsvString() {
        // FIX: war "Film:%s;%s;%S" -> richtig: Film;Titel;Jahr;Bewertungen;Dauer
        return String.format("Film;%s;%d", super.toCsvString(), dauerMinuten);
    }

    @Override
    public String toString() {
        return "Film: " + super.toString() + ", Dauer: " + dauerMinuten + " Minuten";
    }
}