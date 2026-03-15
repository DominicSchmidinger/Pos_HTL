package at.spengergasse;

public abstract class Werkstoffprobe {
    private String id;
    private String bezeichnung;
    private double dichte;
    private double masse;

    public Werkstoffprobe(String id, double dichte, String bezeichnung, double masse) {
        this.id = id;
        this.dichte = dichte;
        this.bezeichnung = bezeichnung;
        this.masse = masse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id darf nicht null oder leer sein");
        }
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        if (bezeichnung == null || bezeichnung.isEmpty()){
            throw new IllegalArgumentException("bezeichnung darf nicht null oder leer sein");
        }
        this.bezeichnung = bezeichnung;
    }

    public double getDichte() {
        return dichte;
    }

    public void setDichte(double dichte) {
        if (dichte == 0){
            throw new IllegalArgumentException("jede Probe hat eine dichte");
        }
        this.dichte = dichte;
    }

    public double getMasse() {
        return masse;
    }

    public void setMasse(double masse) {
        if (masse == 0){
            throw new IllegalArgumentException("jedes Objekt hat eine masse");
        }
        this.masse = masse;
    }

    public abstract double berechneVolumen();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Werkstoffprobe{");
        sb.append("id='").append(id).append('\'');
        sb.append(", bezeichnung='").append(bezeichnung).append('\'');
        sb.append(", dichte=").append(dichte);
        sb.append(", masse=").append(masse);
        sb.append('}');
        return sb.toString();
    }
}

