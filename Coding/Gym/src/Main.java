public class Main {
    public static void main(String[] args) {
        System.out.println("--- 4.1 Test Aufstellen ---");
        testAufstellen();

        System.out.println("\n--- 4.2 Test Sortieren ---");
        testSortieren();

        System.out.println("\n--- 4.3 Test Entfernen ---");
        testEntfernen();
    }

    // 4.1 testAufstellen()
    private static void testAufstellen() {
        // Erstellt ein Gym-Objekt (z.B. max 5000€, max 10 Geräte)
        Gym gym = new Gym(5000.0, 10);

        // Ruft aufstellen() mit null auf
        System.out.println("Aufstellen null: " + gym.aufstellen(null));

        // Erstellt ein Beinpresse-Objekt
        Beinpresse b1 = new Beinpresse("LegMaster 3000", 2500.0, 300);

        // Ruft aufstellen() mit diesem Objekt auf
        System.out.println("Aufstellen Beinpresse: " + gym.aufstellen(b1));

        // Gibt das Gym auf der Konsole aus
        System.out.println(gym.toString());
    }

    // 4.2 testSortieren()
    private static void testSortieren() {
        Gym gym = new Gym(8000.0, 20);

        // Zwei Ergometer und eine Beinpresse erstellen
        Ergometer e1 = new Ergometer("Z-Ergo", 1200.0, 80);
        Ergometer e2 = new Ergometer("Alpha-Ergo", 1500.0, 100);
        Beinpresse b1 = new Beinpresse("Mittel-Presse", 3000.0, 200);

        // Objekte aufstellen
        gym.aufstellen(e1);
        gym.aufstellen(e2);
        gym.aufstellen(b1);

        System.out.println("Vor der Sortierung (alphabetisch nach Name):");
        System.out.println(gym);

        // Methode sortieren() aufrufen (natürliche Ordnung)
        gym.sortieren();

        System.out.println("Nach der Sortierung:");
        System.out.println(gym);
    }

    // 4.3 testEntfernen()
    private static void testEntfernen() {
        Gym gym = new Gym(6000.0, 15);

        // Entfernen mit null auf leerem Gym
        System.out.println("Entfernen null (leeres Gym): " + gym.entfernen(null));

        // Beinpresse erstellen und aufstellen
        Beinpresse b1 = new Beinpresse("Entfern-Mich", 2000.0, 250);
        gym.aufstellen(b1);

        System.out.println("Gym mit einer Maschine:");
        System.out.println(gym);

        // Entfernen mit null
        System.out.println("Entfernen null: " + gym.entfernen(null));

        // Entfernen mit dem echten Objekt
        System.out.println("Entfernen der Beinpresse: " + gym.entfernen(b1));

        // Ergebnis ausgeben
        System.out.println("Gym am Ende:");
        System.out.println(gym);
    }
}