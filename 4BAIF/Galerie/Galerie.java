package Galerie;

import java.util.ArrayList;

public abstract class Galerie {

    private String name;
    private ArrayList<Kunstwerk> kunstwerke;

    public ArrayList<Kunstwerk> getKunstwerke() {
        return kunstwerke;
    }

    public void setKunstwerke(ArrayList<Kunstwerk> kunstwerke) {
        this.kunstwerke = kunstwerke;
    }

    public String getName() throws GalerieExceptions {
        if (name.isEmpty()){
            throw new GalerieExceptions("Die Galerie hat einen namen");
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Galerie(String name){
        this.name = name;
    }

    public void add(Kunstwerk kw){
        if (kunstwerke.size() < 100){
            kunstwerke.add(kw);
        }
    }

    
}
