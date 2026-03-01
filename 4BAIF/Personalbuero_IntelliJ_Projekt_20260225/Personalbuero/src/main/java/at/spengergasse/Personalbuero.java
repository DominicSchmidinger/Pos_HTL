package at.spengergasse;

import java.time.Year;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Personalbuero {

    private List<Mitarbeiter> employees;  // Interface-Typ List wird verwendet

    public Personalbuero() {
        employees = new LinkedList<>();
    }

    public boolean aufnehmen(Mitarbeiter mitarbeiter) { // wann sind Objekte gleich? equals() generieren!
        if (mitarbeiter == null || mitarbeiter.berechneAlter() < 15
                || employees.contains(mitarbeiter)) {
            return false;
        }
        return employees.add(mitarbeiter);
    }

    public int zaehleMitarbeiter() {
        return employees.size();
    }

    public double berechneGehaltsumme() {
        double summe = 0.0;
        for(Mitarbeiter ma : employees) {
            summe += ma.berechneGehalt();
        }
        return summe;
    }

    public int zaehleAlter(int alter) {
        int anzahlMitAlter = 0;
        int fehlercode = -99;
        if(alter < 15) {
            System.out.println("Fehler: alter darf nicht unter 15 sein");
            return fehlercode;
        }
        if (zaehleMitarbeiter() == 0) {
            System.out.println("Fehler: keine MitarbeiterInnen vorhanden");
            return fehlercode;
        }
        for(Mitarbeiter ma : employees) {
            if (ma.berechneAlter() == alter) {
                anzahlMitAlter++;
            }
        }
        return anzahlMitAlter;
    }

    public boolean kuendigen(int pos) {
        if (pos < 0 || pos >= employees.size() || zaehleMitarbeiter() == 0) {
            return false;
        }
        employees.remove(pos);
        return true;
    }

    public boolean kuendigen(String name) {
        if (name == null || name.isEmpty() || employees.isEmpty()) {
            return false;
        }
        for (Mitarbeiter ma : employees) {
            if(ma.getName().equals(name)) {
                return employees.remove(ma);
            }
        }
        return false;
    }

    public int kuendigenAlle(String name) {
        if (name == null || name.isEmpty() || employees.isEmpty()) {
            return -99;
        }
        int anzahlGekuendigt = 0;
//        for(Mitarbeiter m : employees) {  // SO NICHT
//            if(m.getName().equals(name)) {
//                employees.remove(m); // ConcurrentModificationException
//                anzahlGekuendigt++;
//            }
//        }
        Iterator<Mitarbeiter> iter = employees.iterator();
        while (iter.hasNext()) {
            if (iter.next().getName().equals(name)) {
                iter.remove();
                anzahlGekuendigt++;
            }
        }
        return anzahlGekuendigt;
    }

    public boolean kuendigen (Mitarbeiter ma) {
        if (ma == null || employees.isEmpty()) {
            return false;
        }
        return employees.remove(ma);
    }

    public void gehaltsListe() {
        if (employees.isEmpty()) {
            System.out.println("Keine MitarbeiterInnen vorhanden");
        } else {
            for (Mitarbeiter ma : employees) {
                System.out.println(ma);
            }
        }
        System.out.println("Gehaltsumme: " + berechneGehaltsumme());
    }

    // UE mit Iterator
    /*
        Liefert das Durchschnittsalter aller Mitarbeiter zurück.
        Sind keine Mitarbeiter vorhanden, dann wird -99.0 zurückgeliefert.
     */
    public double berechneDurchschnittsalter() {
        if (zaehleMitarbeiter() == 0) {
            return -99.0;
        }
        Iterator<Mitarbeiter> iterator = employees.iterator();
        double summeAlter = 0.0;
        while(iterator.hasNext()) {
            summeAlter += iterator.next().berechneAlter();
        }
        return summeAlter/zaehleMitarbeiter();
    }

    public double kuendigen(double gehalt) {
        if (gehalt < 0.0) {
            return -99.0;
        }
        double summeGehalt = 0.0;
        Iterator<Mitarbeiter> iterator = employees.iterator();
        Mitarbeiter m;
        while(iterator.hasNext()) {
            m = iterator.next();
                // z.B.   1700.0      >    1000.0 -> remove
            if(m.berechneGehalt() > gehalt) {
                summeGehalt += m.berechneGehalt();
                iterator.remove();
            }
        }
        return summeGehalt;
    }

    public boolean sortierenNachName() {
        if (employees.isEmpty()) {
            return false;
        }
        employees.sort(null); // null -> "natürliche Ordnung" verwenden und keinen Comparator
        return true;
    }

    // TODO
    public Mitarbeiter getMitarbeiter(int index) {
        // TODO
        if (employees.isEmpty() || index >= employees.size() || index < 0) {
            return null;
        }
        return employees.get(index);
    }



    public int kuendigen(Year eintrjahr){
        if (eintrjahr == null || eintrjahr.isAfter(Year.now())){
        return-99;
        }

        int anzahlGekuendigt = 0;

        Iterator<Mitarbeiter> iter = employees.iterator(); //muss ich noch anschauen
        // methoden des Iterator hasnext(); next(); rmeove()

        while (iter.hasNext()){
            if (iter.next().getEintrJahr().equals(eintrjahr)){
                iter.remove();
                anzahlGekuendigt++;
            }
        }
        return anzahlGekuendigt;
    }

    public int summeFreelancerStunde(){
        if(employees.isEmpty() || zaehleFreelancer() <= 0){
            return -99;
        }
        int summe = 0;
        for (Mitarbeiter ma: employees){
            if (ma instanceof Freelancer){
                summe += ((Freelancer) ma).getStunden();
            }
        }
        return summe;
    }

    private int zaehleFreelancer() {
        int anzahlFreelancer = 0;

        for (Mitarbeiter ma : employees){
            if(ma instanceof Freelancer){
                anzahlFreelancer++;
            }
        }

        return anzahlFreelancer;
    }

    public int zaehleAngestellte(){
        int anzahlAngestellte = 0;

        for (Mitarbeiter ma : employees){
            if(ma instanceof Angestellter){
                anzahlAngestellte++;
            }
        }
        return anzahlAngestellte;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Personalbuero:\n");
        if (zaehleMitarbeiter() > 0) {
            for(Mitarbeiter ma : employees) {
                sb.append(ma.toString()).append("\n");
            }
        } else {
            sb.append("keine Mitarbeiter vorhanden");
        }
        return sb.toString();
    }
}