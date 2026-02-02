package spengergasse.at;

public class KinoSaal {
    private boolean[][] sitzplan;
    private int reihen;
    private int plaetzeProReihe;

    public KinoSaal(int reihen, int plaetzeProReihe) {
        sitzplan = new boolean[reihen][plaetzeProReihe];
    }


    public boolean kannGruppenSitzen(int n) {
        for (int r = 0; r < sitzplan.length; r++) {
            int freipleatze = 0;
            for (int p = 0; p < sitzplan[r].length; p++) {
                if (!sitzplan[r][p]) {
                    freipleatze++;
                    if (freipleatze == n) {
                        return true;
                    }
                } else {
                    freipleatze = 0;
                }
            }

        }
        return false;
    }

    public boolean reservierungGruppen(int n) {
        for (int r = 0; r < sitzplan.length; r++) {
            int reservieren = 0;
            for (int p = 0; p < sitzplan[r].length; p++) {
                if (!sitzplan[r][p]) {
                    reservieren++;
                    if (reservieren == n) {
                        return true;
                    }

                } else {
                    reservieren = 0;
                }
            }
        }
        return false;
    }
    public String gibSitzplanaus(){
        String ausgabe = "";
        for (int i = 0; i < sitzplan.length; i++) {
            for (int j = 0; j < sitzplan[i].length; j++) {
                if (sitzplan[i][j]) {
                    ausgabe += "X "; // besetzt
                } else {
                    ausgabe += "O "; // frei
                }
            }
            ausgabe += "\n";
        }
        return ausgabe;
    }
    }

