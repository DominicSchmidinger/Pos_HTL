package at.spengergasse;

import java.io.*;
import java.util.*;

public class Notizen {

    //    key               value
    //   Notiz             Kategorie
    //"Brot einkaufen", "Einkauf"
    //"Video über Java-Streams ansehen", "Hobby"
    //"Geschenk für Petra besorgen", "Familie"

    private HashMap<String, String> notizenListe;

    public Notizen() {
        this.notizenListe = new HashMap<>();
    }

    public String notizHinzufuegen(String notiz, String kategorie) throws NotizenException {
        if(notiz == null || notiz.isBlank())  {
//            return "Fehler: notiz null oder leer";
            //throw new IllegalArgumentException("Fehler: notiz null oder leer");
            // Runtime-Exception
            // -> eigene NotizenException-Klasse
            throw new NotizenException("Fehler: notiz null oder leer");
        }
        String notizKategorie;
        if(kategorie == null || kategorie.isBlank()) {
            notizKategorie = "Diverses";
        } else {
            notizKategorie = kategorie;
        }
        return notizenListe.put(notiz, notizKategorie);  // return null wenn neu, oder alte Kategorie
    }

    public int anzahlNotizen() {
        return notizenListe.size();
    }

    public void notizenAusgebenOhneKategorie() {
        System.out.println(notizenListe.keySet());
    }

    public void kategorienAusgebenOhneNotizen() {
        System.out.println(notizenListe.values());
    }

    public void readNotizFromCsvFile (){
        String filepath = "src/main/resources/notizenInput.csv";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));


            String line = br.readLine();
            while (line != null) {
                //System.out.println(line);

               String[] notiz = line.split(";");
                try {
                    switch (notiz.length){
                        case 1:{
                            notizHinzufuegen(notiz[0], null);
                            break;
                        }
                        case 2:{
                            notizHinzufuegen(notiz[0], notiz[1]);
                            break;
                        }
                        default: {
                            System.out.println("Fehler: Notiz Format faslch:" + line);
                        }
                    }
                    notizHinzufuegen(notiz[0], notiz[1]);
                } catch (NotizenException e) {
                    System.out.println("Fehler: Notiz Format falsch: " + line + " " + e.getMessage());
                }
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Fehler beim laden des Files" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Fehler beim lesen des Files" + e.getMessage());
        }
    }

    public void writeNotizToCsvFile(){
        String filepath = "src/main/resources/notizenOutput.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))){

            Set<String> notiz = notizenListe.keySet();
            for (String notizKey : notiz){
                bw.write(notizKey + ";" + notizenListe.get(notizKey) + "/n");
            }

        } catch (IOException e){
            System.out.println("Fehler beim Öffnen: " + e.getMessage());
        }
    }

    public void ausgeben() {  // Notizen sind die Keys, Values sind die Kategorien
        System.out.println(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Notizen:\n");
        if(notizenListe.isEmpty()) {
            sb.append("keine Notizen vorhanden") ;
        } else {
            Set<String> notizen = notizenListe.keySet();
            for (String k : notizen) {
                sb.append(k).append(" (" + notizenListe.get(k) + ")\n");
            }
            sb.append("Anzahl: ").append(anzahlNotizen());
        }
        return sb.toString();
    }
}
