public class Beinpresse extends Maschine {
    private int maxGewicht;

    public Beinpresse(String name, double preiEur, int maxGewicht) {
        super(name, preiEur);
        setMaxGewicht(maxGewicht);
    }

    public int getMaxGewicht() {
        return maxGewicht;
    }

    public void setMaxGewicht(int maxGewicht) {
        if (maxGewicht <= 100 || maxGewicht >= 500){
            System.out.println("Geht ned sorry");
        } else {
            this.maxGewicht = maxGewicht;
        }
    }

    @Override
    public int wartungIntervall() {
        int intervall = 6;

        return intervall;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Beinpresse: ");
        sb.append(super.toString());
        sb.append("Wartungsintervall: ").append(maxGewicht).append(" Monate");
        sb.append("max. Gewicht: ").append(maxGewicht);
        return sb.toString();
    }
}
