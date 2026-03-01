package at.spengergasse;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
Notizen no = new Notizen();
String String1 = "Brot einlaufen";
String String2 = "Java vid ansehen";
String String3 = "Geschenk kaufen";

no.erstellen(String1);
no.erstellen(String2);
no.erstellen(String3);

no.sortieren();
no.notizenAusgeben();

    }
}