package at.spengergasse;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        testZahlenPruefer();
        testtextzuKleinBuchstaben();


    }

    private static void testtextzuKleinBuchstaben() {
        String text = "DavidStamfest";
        Textverarbeitung klein = kleinertext -> kleinertext.toLowerCase();
        System.out.println("Jetzt ist david klein: " + klein.textOperation(text) + "\n");

        String text2 = "DavidStamfest";
        Textverarbeitung groß = großertext -> großertext.toUpperCase();
        System.out.println("Jetzt ist David Groß: " + groß.textOperation(text2) + "\n");

        String text3 = "David Stamfest";
        Textverarbeitung bindestrich = txtBindestrich -> txtBindestrich.replaceAll(" ", "-");
        System.out.println("Jetzt hat David einen Bindestrich " + bindestrich.textOperation(text3) + "\n");

        String text4 = "DavidStamfest";
        Textverarbeitung random = randomword -> randomword + " is gemein";
        System.out.println("Random word: " + random.textOperation(text4));
    }


    private static void testZahlenPruefer() {

        Zahlen_pruefer pruefer = nummer -> nummer > 0;
        Zahlen_pruefer pruefer1 = nummer -> nummer > 100 && nummer < 300;
        Zahlen_pruefer pruefer2 = nummer -> nummer % 5 == 0;
        Zahlen_pruefer pruefer3 = nummer -> nummer % 2 != 0;
        for (int i = -30; i < 45; i += 5) {
            System.out.println("Nummer: " + i);
            System.out.println("Positiv: " + pruefer.pruefe(i));
            System.out.println("Durch 5 teilbar: " + pruefer2.pruefe(i));
            System.out.println("Ungerade: " + pruefer3.pruefe(i) + "\n");
        }

            for (int k = 20; k < 400; k += 10) {

                System.out.println("Nummer: " + k);
                System.out.println("Größer: " + pruefer1.pruefe(k) + "\n");

            }
        }

}


