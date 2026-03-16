package at.spengergasse;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WerkstoffzentrumVerwaltung {

    private List<Werkstoffprobe> Werkstoffprobe;

    public WerkstoffzentrumVerwaltung() {
        Werkstoffprobe = new LinkedList<Werkstoffprobe>();
    }

    public boolean probenaufnehmen(Werkstoffprobe probe) {
        if (probe == null || Werkstoffprobe.contains(probe)) {
            throw new IllegalArgumentException("Fehler bei der Validierung: " + probe);
        }
        return Werkstoffprobe.add(probe);

    }
    public boolean removeProbe(String id){
        Iterator<Werkstoffprobe> iterator = Werkstoffprobe.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                iterator.remove();
            }
        }
        return false;
    }
    public int removeAllProbe(String Name){
        int glöschteProbe = 0;
        Iterator<Werkstoffprobe> iterator = Werkstoffprobe.iterator();
        while (iterator.hasNext()){
            if (iterator.next().getClass().getSimpleName().equals(Name)){
                glöschteProbe++;
                iterator.remove();
            }
        }
        return glöschteProbe;
    }

    public int AllMitIndexUnter (double grenzwert){
        int grenzwertzuhoch = 0;
        Iterator<Werkstoffprobe> iter = Werkstoffprobe.iterator();
        while (iter.hasNext()){
            if (iter.next().berechneQualitaetsIndex() < grenzwert){
                grenzwertzuhoch++;
                iter.remove();
            }
        } return grenzwertzuhoch;
    }

    public Werkstoffprobe sucheProbe (String id){
        for (Werkstoffprobe wp : Werkstoffprobe){
            if (wp.getId().contains(id)){
                return wp;
            }
        } return null;
    }

    public int zaehleMetallproben(){
        int anzahl = 0;
        for (Werkstoffprobe wp : Werkstoffprobe) {
            if (wp instanceof MetallProbe) {
                anzahl++;
            }
        }return anzahl;
    }

    public int zaehlePolymerproben(){
        int anzahl = 0;
        for (Werkstoffprobe wp : Werkstoffprobe) {
            if (wp instanceof PolymerProbe)  {
                anzahl++;
            }
        }return anzahl;
    }
    public int zeahleKeramikProben(){
        int anzahl = 0;
        for (Werkstoffprobe wp : Werkstoffprobe) {
            if (wp instanceof KeramikProbe)  {
                anzahl++;
            }
        }return anzahl;
    }

    public double gesQuliteatMetall() {
        double summe = 0;
        for (Werkstoffprobe wp : Werkstoffprobe) {
            if (wp instanceof MetallProbe)
                summe += wp.berechneQualitaetsIndex();
        }
        return summe;
    }

    public double gesQuliteatKeramik() {
        double summe = 0;
        for (Werkstoffprobe wp : Werkstoffprobe) {
            if (wp instanceof KeramikProbe)
                summe += wp.berechneQualitaetsIndex();
        }
        return summe;
    }


    public double gesQuliteatPolymer() {
        double summe = 0;
        for (Werkstoffprobe wp : Werkstoffprobe) {
            if (wp instanceof KeramikProbe)
                summe += wp.berechneQualitaetsIndex();
        }
        return summe;
    }

    public double durchschnittMetall(){
        double durchschnitt = 0;
        durchschnitt = gesQuliteatMetall() / zaehleMetallproben();
        return durchschnitt;
    }
    public double durchschnittKeramik() {
        double durchschnitt = 0;
        durchschnitt = gesQuliteatKeramik() / zeahleKeramikProben();
        return durchschnitt;
    }

    public double durchschnittPolymer() {
        double durchschnitt = 0;
        durchschnitt = gesQuliteatPolymer() / zaehlePolymerproben();
        return durchschnitt;
    }


    public void druckeStatistik(){
        System.out.println("----Werkstoffzentrum Statistik----");
        System.out.println("Gesamtzahl Metallproben: " + zaehleMetallproben());
        System.out.println("Gesamtzahl PolymerProben: " + zaehlePolymerproben());
        System.out.println("Gesamtzahl KeramikProben: " + zeahleKeramikProben());
        System.out.println("________________________________________________");
    }
    public void Standartsort(){
        Werkstoffprobe.sort(null);
    }
    public void sotierencoperator(){
        Werkstoffprobe.sort(new WerkstoffQualitaetsIndexComparator());
    }
}