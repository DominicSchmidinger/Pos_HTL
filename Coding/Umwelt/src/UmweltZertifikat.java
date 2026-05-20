public abstract class UmweltZertifikat {
    private String id;
    private String name;
    private double co2ProJahr;
    private double recyclingQuote;

    public UmweltZertifikat(String id, String name, double recyclingQuote, double co2ProJahr) {
        this.id = id;
        this.recyclingQuote = recyclingQuote;
        this.co2ProJahr = co2ProJahr;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws UmweltProjektException {
        if (id.isEmpty()) {
            throw new UmweltProjektException("id bruder");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws UmweltProjektException {
        if (name.isEmpty()) {
            throw new UmweltProjektException("Name vergessen");
        }
        this.name = name;
    }

    public double getCo2ProJahr() {
        return co2ProJahr;
    }

    public void setCo2ProJahr(double co2ProJahr) throws UmweltProjektException {
        if (co2ProJahr >= 0) {
            throw new UmweltProjektException("nicht null bruder");
        }
        this.co2ProJahr = co2ProJahr;
    }

    public double getRecyclingQuote() {
        return recyclingQuote;
    }

    public void setRecyclingQuote(double recyclingQuote) throws UmweltProjektException {
        if (recyclingQuote >= 0 || recyclingQuote <= 100) {
            throw new UmweltProjektException("NEIN");
        }
        this.recyclingQuote = recyclingQuote;
    }

    public abstract double berechneNachhaltigkeitsScore();



}

