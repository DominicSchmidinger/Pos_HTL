package at.spengergasse;

import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;

public class UmweltBehoerde {
    private ArrayList<Messstation> messstationen;

    public UmweltBehoerde() {
        messstationen = new ArrayList<>();
    }

    public boolean addMessstation(Messstation m) {
        if (m == null || messstationen.contains(m)) {
            return false;
        }
        return messstationen.add(m);
    }

    public double berechneDurchschnittMesswert() {
        if (messstationen.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Messstation m : messstationen) {
            sum += m.getMesswert();
        }
        return sum / messstationen.size();
    }

    private double berechneDurchschnittMessstationen(String typ) {
        if (messstationen.isEmpty()) {
            return 0.0;
        }
        switch (typ) {
            case "Luftmessstation":
            case "Laermmessstation":
            case "Wetterstation": {
                double sum = 0.0;
                int anzahl = 0;
                for (Messstation m : messstationen) {
                    if (m.getStationTyp().equals(typ)) {
                        sum += m.getMesswert();
                        anzahl++;
                    }
                }
                return sum / anzahl;
            }
            default:
                return 0.0;
        }
    }

    public double berechneDurchschnittLuftmessstationen() {
        return berechneDurchschnittMessstationen("Luftmessstation");
    }

    public double berechneDurchschnittLaermmessstationen() {
        return berechneDurchschnittMessstationen("Laermmessstation");
    }

    public double berechneDurchschnittWetterstation() {
        return berechneDurchschnittMessstationen("Wetterstation");
    }

    public void sortiereMessstationen() {
        messstationen.sort(null);
    }

    private void zeigeStationen(String typ) {
        if (messstationen.isEmpty()) {
            System.out.println("Keine Messstationen vorhanden");
        }
        int count = 0;
        for (Messstation m : messstationen) {
            if ( (typ.equals("Luftmessstation") && m instanceof Luftmessstation) ||
                 (typ.equals("Lärmmessstation") &&  m instanceof Laermmessstation) ||
                 (typ.equals("alle")))   {
                        System.out.println(m);
                        count++;
            }
        }
        if (count == 0) {
            System.out.println("Keine " + typ +" vorhanden");
        }
    }

    public void zeigeAlleStationen() {
        zeigeStationen("alle");
    }

    public void zeigeNurLuftmessstationen() {
        zeigeStationen("Luftmessstation");
    }

    public void zeigeNurLaermessstationen() {
        zeigeStationen("Lärmmessstation");
    }

    public void zaehleStationstypen() {
        int anzahlLuft = 0;
        int anzahlLaerm = 0;
        int anzahlWetter = 0;

        for (Messstation m : messstationen) {
            if (m instanceof Luftmessstation) {
                anzahlLuft++;
            } else if (m instanceof Laermmessstation) {
                anzahlLaerm++;
            } else if (m instanceof Wetterstation) {
                anzahlWetter++;
            }
        }

        System.out.println("Anzahl Luft: " + anzahlLuft);
        System.out.println("Anzahl Lärm: " + anzahlLaerm);
        System.out.println("Anzahl Wetter: " + anzahlWetter);
    }

    public int zaehleAlleStationenMitZuHohemWert(double grenzwert) {
        if (grenzwert <= -200.0 || grenzwert >= 200.0) {
            return -99;
        }
        int anzahl = 0;
        for (Messstation m : messstationen) {
            if (m.getMesswert() > grenzwert) {
                anzahl++;
            }
        }
        return anzahl;
    }

    public boolean removeMessstation(Messstation m) {
        if (m == null || messstationen.isEmpty()) {
            return false;
        }
        return messstationen.remove(m);
    }

    public boolean removeErsteStationNachStandort(String standort) {
        if (standort == null || messstationen.isEmpty()) {
            return false;
        }
        Iterator<Messstation> iterator = messstationen.iterator();
        while(iterator.hasNext()) {
            if (iterator.next().getStandort().equals(standort)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public ArrayList<Messstation> removeAlleStationenAelterAls(int alterInJahren) {
        if(messstationen.isEmpty() || alterInJahren < 0) {
            return null;
        }

        ArrayList<Messstation> messtationenEntfernt = new ArrayList<>();

        int currentYear = Year.now().getValue();
        int installationsJahr;
        Iterator<Messstation> iter = messstationen.iterator();
        while (iter.hasNext()) {
            Messstation messstation = iter.next();
            installationsJahr = messstation.getInstallationsJahr().getValue();
            if ((currentYear - installationsJahr) > alterInJahren) {
                messtationenEntfernt.add(messstation);
                iter.remove();
            }
        }
        return messtationenEntfernt;
    }

    public void zeigeStationenMitGrenzwertUeberschreitung() {
        if (messstationen.isEmpty()) {
            throw new IllegalArgumentException("Geben sie eine wetterstation ein");
        }
        for (Messstation m : messstationen) {
            if (m instanceof Luftmessstation) {
                Luftmessstation lm = (Luftmessstation) m;
                if (m.getMesswert() > lm.getFeinstaubGrenzwert()) {
                    System.out.println(m);
                    System.out.println("FeinstuabGrenzwert: " + lm.getFeinstaubGrenzwert());
                    System.out.println("Messwert: " + lm.getMesswert());
                    System.out.println("Überschreitung: " + (lm.getMesswert() - lm.getFeinstaubGrenzwert()));
                }
            }
        }
        for (Messstation m : messstationen) {
            if (m instanceof Wetterstation) {
                Wetterstation wm = (Wetterstation) m;
                if (m.getMesswert() > wm.getTemperatur()) {
                    System.out.println(m);
                    System.out.println("FeinstuabGrenzwert: " + wm.getTemperatur());
                    System.out.println("Messwert: " + wm.getMesswert());
                    System.out.println("Überschreitung: " + (wm.getMesswert() - wm.getTemperatur()));

                }
            }
        }
        for (Messstation m : messstationen) {
            if (m instanceof Laermmessstation) {
                Laermmessstation la = (Laermmessstation) m;
                if (m.getMesswert() > la.getMaxErlaubterPegel()) {
                    System.out.println(m);
                    System.out.println("FeinstuabGrenzwert: " + la.getMaxErlaubterPegel());
                    System.out.println("Messwert: " + la.getMesswert());
                    System.out.println("Überschreitung: " + (la.getMesswert() - la.getMaxErlaubterPegel()));

                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Messstationen: ");
        for (Messstation m : messstationen) {
            sb.append("\n").append(m.toString());
        }
        return sb.toString();
    }
}
