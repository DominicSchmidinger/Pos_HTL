package spengergasse.at; 
// Gibt an, zu welchem Package (Namensraum) diese Klasse gehört.
// Hilft bei der Organisation von Klassen und beim Vermeiden von Namenskonflikten.

public class Main {
// Definition der Hauptklasse "Main".
// In Java muss der Dateiname dem Klassennamen entsprechen (Main.java).

    public static void main(String[] args) {
    // Einstiegspunkt des Programms.
    // Die JVM startet das Programm immer in dieser main-Methode.

        PlaylistManeger playlist1 = new PlaylistManeger();
        // Erzeugt ein neues Objekt der Klasse PlaylistManeger.
        // playlist1 ist eine Referenz auf diese Playlist.

        Song s1 = new Song("Alex mein Bubu", "Jakob", "Rock");
        // Erstellt ein Song-Objekt mit Titel, Interpret und Genre.
        // Dieser Song gehört zum Genre "Rock".

        Song s2 = new Song("Meine Hoden brennen", "Julius", "Pop");
        // Erstellt ein zweites Song-Objekt mit einem anderen Genre ("Pop").

        playlist1.addSongs(s1);
        // Fügt den Song s1 der Playlist hinzu.

        playlist1.addSongs(s2);
        // Fügt den Song s2 der Playlist hinzu.

        playlist1.addSongs(s1);
        playlist1.addSongs(s1);
        playlist1.addSongs(s1);
        // Fügt s1 mehrfach hinzu, um Duplikate in der Playlist zu erzeugen.
        // Dient zum Testen der removeDuplicate-Methode.

        playlist1.addSongs(s2);
        playlist1.addSongs(s2);
        // Fügt auch s2 mehrfach hinzu (ebenfalls Duplikate).

        playlist1.addSongs(s1);
        // Noch ein weiteres Duplikat von s1.

        // Filter Genre new Array Test
        System.out.println(playlist1.filterByGernre("Rock"));
        // Ruft die Methode filterByGernre auf.
        // Gibt alle Songs mit dem Genre "Rock" aus (z. B. als Liste oder Array).

        System.out.println(playlist1.filterByGernre("Pop"));
        // Gibt alle Songs mit dem Genre "Pop" aus.

        // Pre Remove
        System.out.println(playlist1);
        // Gibt den aktuellen Zustand der Playlist aus,
        // bevor Duplikate entfernt werden.

        // s1 sollte nicht removed werden
        playlist1.removeDuplicate(s1);
        // Entfernt doppelte Einträge von s1 aus der Playlist,
        // lässt aber mindestens ein Exemplar von s1 bestehen.

        playlist1.removeDuplicate(s2);
        // Entfernt doppelte Einträge von s2,
        // ebenfalls mit mindestens einem verbleibenden Song.

        // After Remove
        System.out.println(playlist1);
        // Gibt die Playlist nach dem Entfernen der Duplikate aus,
        // um zu überprüfen, ob removeDuplicate korrekt funktioniert.
    }
}
