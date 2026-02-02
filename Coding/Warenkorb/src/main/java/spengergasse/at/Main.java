package spengergasse.at;

import org.w3c.dom.ls.LSOutput;

public class Main {
    public static void main(String[] args) {

        Warenkorb meinKorb = new Warenkorb();

        Artikel a1 = new Artikel("Schockolade", 60, 1);
        Artikel a2 = new Artikel("Döner", 5.50, 1);
        Artikel a3 = new Artikel("Freunde", 10, 1);
        Artikel a4 = new Artikel("Meinen Vater", 20, 1);

        meinKorb.aufnehmen(a1);
        meinKorb.aufnehmen(a2);
        meinKorb.aufnehmen(a1);
        meinKorb.aufnehmen(a3);
        meinKorb.aufnehmen(a4);

        System.out.println(meinKorb);
    }

}
