public class Ergometer extends Maschine{
    private int maxDrezahl;

    public Ergometer(String name, double preiEur, int maxDrezahl) {
        super(name, preiEur);
        setMaxDrezahl(maxDrezahl);
    }

    public int getMaxDrezahl() {
        return maxDrezahl;
    }

    public void setMaxDrezahl(int maxDrezahl) {
        if (maxDrezahl <= 5 || maxDrezahl >= 150){
            System.out.println("geht nicht habibi");
        }else{
        this.maxDrezahl = maxDrezahl;
        }
    }

    @Override
    public int wartungIntervall() {
        return 12;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Beinpresse: ");
        sb.append(super.toString());
        sb.append("Wartungsintervall: ").append(maxDrezahl).append(" Monate");
        sb.append("max. Gewicht: ").append(maxDrezahl);
        return sb.toString();
    }
}
