import java.util.Objects;

public abstract class Maschine {
    private String name;
    private double preiEur;


    public Maschine(String name, double preiEur) {
        this.name = name;
        this.preiEur = preiEur;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() == 0 || name.isEmpty()){
            System.out.println("nein");
        }
        this.name = name;
    }

    public double getPreiEur() {
        return preiEur;
    }

    public void setPreiEur(double preiEur) {
        if(preiEur < 0){
            throw new IllegalArgumentException("Darf nicht null oder minus sein");
        }
        this.preiEur = preiEur;
    }

    public abstract int wartungIntervall();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Maschine maschine = (Maschine) o;
        return Double.compare(preiEur, maschine.preiEur) == 0 && Objects.equals(name, maschine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, preiEur);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(name).append(", ").append('\'');
        sb.append(preiEur).append(" EUR");
        return sb.toString();
    }
}
