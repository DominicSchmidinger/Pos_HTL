package at.spengergasse;

import java.util.Arrays;

public class Pegelstand {
    private String fluss;
    private int[] werte;
    private int anzahl;


    public Pegelstand() {
        fluss = "Donau";
        werte = new int[7];

    }

    public Pegelstand(String fluss) {
        setFluss(fluss);
        werte = new int[7];
    }

    public void setFluss(String fluss) {
        if (fluss != null && !fluss.isEmpty()) {
            this.fluss = fluss;
        } else {
            throw new IllegalArgumentException("name ungültig");
        }
    }

    public String getFluss() {
        return fluss;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public int getWert(int index) {
        if (index < 0 || index >= werte.length) {
            return -99;
        }

        return werte[index];
    }

    public boolean messen(int wert) {
        if (wert < 0 || anzahl >= werte.length) {
            return false;
        }
        werte[anzahl] = wert;
        anzahl++;
        return true;
    }

    public boolean tauschen(int index1, int index2) {
        if (index1 < 0 || index2 < 0 ||
                index1 >= anzahl || index2 >= anzahl ||
                index1 == index2) {
            return false;
        }
        int tmpWert = werte[index1];             //dreiecks tausch
        werte[index1] = werte[index2];
        werte[index2] = tmpWert;
        return true;
    }

    public void entferneLetztenWert() {
        if (anzahl > 0) {
            werte[anzahl - 1] = 0;
            anzahl--; // ein wert weniger im array
        } else {
            System.out.println("Es muss min ein wert geben");
        }
    }

    public void loeschen(int index) {
        if (index >= 0 && index < anzahl) {
            for (int i = index; i < anzahl - 1; i++) {
                werte[i] = werte[i + 1];

            }
            werte[anzahl - 1] = 0;
            anzahl--;
        } else {
            System.out.println("index mag ich nicht");
        }
    }

    public void einfuegen(int index, int wert) {
        if (index >= 0 && index < anzahl) {
            if (wert >= 0) {
                if (anzahl < werte.length) {
                    for (int i = anzahl; i > index; i--) {
                        werte[i] = werte[i - 1];
                    }
                    werte[index] = wert;
                    anzahl++;
                } else {
                    System.out.println("kein platz zum einfügen");
                }
            } else {
                System.out.println("wert ist ungültig");
            }
        } else {
            System.out.println("index ungültig");
        }
    }

    public void sortiereWerte(){
        for(int n = anzahl; n > 1; n--){
            for (int i = 0; i < n - 1; i++) {
                if (werte[i] > werte[i+1]){
                    tauschen(i, i+1);
                }
            }
        }
    }


    public void ausgebenwerte() {
        for (int i = 0; i < werte.length; i++) {
            System.out.println("[" + i + "]: " + werte[i]);

        }
    }

    public void fuelleTestwerte() {
        werte = new int[]{250, 290, 310, 310, 300, 300, 300};
        anzahl = 7;
    }

    public float avgWert() {
        if (anzahl == 0) {
            return -99f;
        }
        float summe = 0f;
        for (int i = 0; i < anzahl; i++) {
            summe += werte[i];
        }
        return summe / anzahl;
    }

    public int minWert() {
        if (anzahl == 0) return -99;
        int min = werte[0];

        for (int i = 1; i < anzahl; i++) {
            if (werte[i] < min) {
                min = werte[i];
            }
        }
        return min;
    }

    public int maxWert() {
        if (anzahl == 0) return -99;
        int max = werte[0];

        for (int i = 1; i < anzahl; i++) {
            if (werte[i] > max) {
                max = werte[i];
            }
        }
        return max;
    }

    public int maxWertIndex() {
        int max = werte[0];
        int maxWertIndex = 0;
        for (int i = 1; i < anzahl; i++) {
            if (werte[i] > max) {
                max = werte[i];
                maxWertIndex = i;
            }
        }
        return maxWertIndex();
    }


    public void printPegelstand() {
        StringBuilder sb = new StringBuilder();
        sb.append(fluss).append("\n");
        if (anzahl > 0) {
            for (int i = 0; i < anzahl; i++) {
                // System.out.println("[" + i + "]: " + werte[i] + " cm");
                sb.append("[").append(i).append("]:").append(werte[i]).append(" cm\n");
            }
        } else {
            System.out.println("keine werte vorhanden");
        }

//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("Pegelstand:");
//        sb.append(fluss).append('\'');
//        sb.append(", ").append(Arrays.toString(werte));
//        sb.append(", ").append(anzahl);
//        sb.append(" Messwerte");
//        return sb.toString();
    }
}
