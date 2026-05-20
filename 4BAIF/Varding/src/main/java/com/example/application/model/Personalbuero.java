package com.example.application.model;

import java.io.*;
import java.time.Year;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Personalbuero {

    private final List<Mitarbeiter> mitarbeiter;

    public Personalbuero() {
        mitarbeiter = new LinkedList<>();
    }

    public List<Mitarbeiter> getMitarbeiterListeCopy() {
        return List.copyOf(mitarbeiter);
    }

    public boolean aufnehmen(Mitarbeiter ma) throws PersonalException {
        if (ma == null) { // fail-fast
            //System.out.println("Fehler: null");
            throw new PersonalException("Fehler: null");
            //return false;
        }
        if(ma.berechneAlter() < 15){
            //System.out.println("Fehler: MitarbeiterIn zu jung, muss mindestens 15 sein");
            throw new PersonalException("Fehler: MitarbeiterIn zu jung, muss mindestens 15 sein");
            //return false;
        }
        if(mitarbeiter.contains(ma)){
            //System.out.println("Fehler: MitarbeiterIn schon vorhanden");
            throw new PersonalException("Fehler: MitarbeiterIn schon vorhanden");
            //return false;
        }
        return mitarbeiter.add(ma);
    }

    public double berechneGehaltsumme() {
        double summe = 0.0;
        for (Mitarbeiter ma : mitarbeiter) {
            summe += ma.berechneGehalt();
        }
        return summe;
    }

    private int zaehleFreelancer() {
        int count = 0;
        for(Mitarbeiter ma : mitarbeiter) {
            if(ma instanceof Freelancer) {
                count++;
            }
        }
        return count;
    }

    public double berechneDurchschnittsalterFreelancer() {
        if (mitarbeiter.isEmpty()) {
            return -99.0;
        }
        if (zaehleFreelancer() <= 0) {
            return 0.0;
        }
        double summeJahre = 0.0;
        for (Mitarbeiter ma : mitarbeiter) {
            if (ma instanceof Freelancer) {
                summeJahre += ma.berechneAlter();
            }
        }
        return summeJahre / zaehleFreelancer();
    }

    public double berechneDurchschnittsalter() {
        if (mitarbeiter.isEmpty()) {
            return -99.0;
        }
        double summeJahre = 0.0;
        for (Mitarbeiter ma : mitarbeiter) {
            summeJahre += ma.berechneAlter();
        }
        return summeJahre / zaehleMitarbeiter();
    }

    public int zaehleMitarbeiter() {
        return mitarbeiter.size();
    }

    public int zaehleAlter(int alter) {
        if(alter < 15 || mitarbeiter.isEmpty()) {
            return -99;
        }
        int anzahl = 0;
        for (Mitarbeiter ma : mitarbeiter) {
            if(ma.berechneAlter() == alter) {
                anzahl++;
            }
        }
        return anzahl;
    }

    public boolean kuendigen(String name) throws PersonalException {
        if (name != null) {
            if (!mitarbeiter.isEmpty()) {
                for(Mitarbeiter ma : mitarbeiter) {
                    if (ma.getName().equals(name)) {
                        mitarbeiter.remove(ma);
                        return true;
                    }
                }
            } else {
                //System.out.println("Fehler: leer");
                throw new PersonalException("Fehler: leer");
            }
        } else {
            //System.out.println("Fehler: null");
            throw new PersonalException("Fehler: null");
        }
        return false;
    }

    public int kuendigenAlle(String name) {
        if (name == null || mitarbeiter.isEmpty()) {
            return -99;
        }
        int count = 0;
        Iterator<Mitarbeiter> iter = mitarbeiter.iterator();
        while (iter.hasNext()) {
            if(iter.next().getName().equals(name)) {
                iter.remove();
                count++;
            }
        }
        return count;
    }

    public int kuendigenAlle(Year eintrJahr) {
        if(eintrJahr == null || mitarbeiter.isEmpty()) {
            return -99;
        }
        int count = 0;
        Iterator<Mitarbeiter> iterator = mitarbeiter.iterator();
        while(iterator.hasNext()) {
            if(iterator.next().getEintrJahr().equals(eintrJahr)) {
                iterator.remove();
                count++;
            }
        }
        return count;
    }

    public boolean kuendigen(Mitarbeiter ma) {
        if (ma == null || mitarbeiter.isEmpty()) {
            return false;
        }
        return mitarbeiter.remove(ma);
    }

    /*
    entfernt ALLE Mitarbeiter
    die mehr als das übergebene gehalt verdienen
    die gehaltsumme der entfernten Mitarbeiter
    wird zurückgeliefert
     */
    public double kuendigen(double gehalt) {
        // Plausibilitätsprüfung fail-fast -> return -99.0
        if (gehalt < 0.0) {
            return -99.0;
        }
        double gehaltSumme = 0.0;
        Iterator<Mitarbeiter> iterator = mitarbeiter.iterator();
        Mitarbeiter m;
        while (iterator.hasNext()) {
            m = iterator.next();
            if(m.berechneGehalt() > gehalt) {
                gehaltSumme += m.berechneGehalt();
                iterator.remove();
            }
        }
        return gehaltSumme;
    }

    public int kuendigenAlleFreelancer() {
        int count = 0;
        Iterator<Mitarbeiter> iterator = mitarbeiter.iterator();
        while(iterator.hasNext()) {
            if(iterator.next() instanceof Freelancer) { // "bist du vom Typ Freelancer"
                iterator.remove();
                count++;
            }
        }
        return count;
    }

    public int zaehleAngestellte() {
        if (mitarbeiter.isEmpty()) {
            return -99;
        }
        int count = 0;
        for (Mitarbeiter m : mitarbeiter) {
            if (m instanceof Angestellter) {
                count++;
            }
        }
        return count;
    }

    public Mitarbeiter getMitarbeiter(int index) {
        if (index < 0 || index >= mitarbeiter.size()) {
            return null;
        }
        return mitarbeiter.get(index);
    }

    public boolean sortierenNachName() {
        if (mitarbeiter.isEmpty()) {
            return false;
        }
        mitarbeiter.sort(null);
        return true;
    }

    public void sortierenNachAlter() {
        mitarbeiter.sort((o1, o2) -> Integer.compare(o1.berechneAlter(), o2.berechneAlter()));
    }

    public void sortierenNachDienstalter() {
        // mit DienstalterComparator-Klasse implements Comparator
        mitarbeiter.sort((o1, o2) -> Integer.compare(o1.berechneDienstalter(), o2.berechneDienstalter()));
    }

    public void sortierenNachDienstalterAbsteigend() {
        // mit DienstalterComparator-Klasse implements Comparator
        mitarbeiter.sort((o1, o2) -> Integer.compare(o2.berechneDienstalter(), o1.berechneDienstalter()));
    }

    public int summeFreelancerStunden() {
        if(mitarbeiter.isEmpty()) {
            return -99;
        }
        int stundenSumme = 0;
        for(Mitarbeiter m : mitarbeiter) {
            if(m instanceof Freelancer) {
                stundenSumme += ((Freelancer) m).getStunden();
            }
        }
        return stundenSumme;
    }

    public void writePersonalToCsv() {
        String filepath = "src/main/resources/personalOutput.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
            for(Mitarbeiter m : mitarbeiter) {
                bw.write(m.toCsvString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Fehler beim schreiben der PersonalOutput! " + e.getMessage());
        }
    }

    public void readPersonalFromCsv() {
        String filepath = "src/main/resources/personal.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = br.readLine();
            Year gebJahr = null;
            Year eintJahr = null;
            Mitarbeiter m = null;
            while (line != null) {
                // Angestellter;Anna;2001;2026
                try {
                    String[] fields = line.split(";");
                    if(fields.length >= 4) {
                        gebJahr = Year.parse(fields[2]);
                        eintJahr = Year.parse(fields[3]);
                        if(fields[0].equals("Angestellter")) {
                            if(fields.length == 4) {
                                m = new Angestellter(fields[1], gebJahr, eintJahr);
                            } else {
                                System.out.println("Fehler: Angestellter zu viele Spalten: " + line);
                            }
                        }
                        if(fields[0].equals("Freelancer")) {
                            if(fields.length == 6) {
                                m = new Freelancer(fields[1], gebJahr, eintJahr, Double.parseDouble(fields[4]), Integer.parseInt(fields[5]));
                            } else {
                                System.out.println("Fehler: Freelancer zu viele Spalten: " + line);
                            }
                        }

                        if(fields[0].equals("Arzt")) {
                            if(fields.length == 6) {
                                m = new Arzt(fields[1], gebJahr, eintJahr, Integer.parseInt(fields[4]), Double.parseDouble(fields[5]));
                            } else {
                                System.out.println("Fehler: Freelancer zu viele Spalten: " + line);
                            }
                        }

                        aufnehmen(m);
                    } else {
                        System.out.println("Fehler: Zeile zu wenige Elemente: " + line);
                    }
                } catch (PersonalException | DateTimeParseException e) {
                    System.out.println("Fehler: Mitarbeiter konnte nicht erstellt werden: " + line + " " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("Fehler beim Umwandeln der Zahl " + line + " " + e.getMessage());
                }

                line  = br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Fehler: Datei nicht gefunden! " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Fehler beim lesen/schlißen der Datei! " + e.getMessage());
        }
    }






    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Personalbuero:\n");
        if (mitarbeiter.isEmpty()) {
            sb.append("es gibt derzeit keine Mitarbeiter");
        } else {
            for(Mitarbeiter ma : mitarbeiter) {
                sb.append(ma).append("\n");
            }
        }
        return sb.toString();
    }
}
