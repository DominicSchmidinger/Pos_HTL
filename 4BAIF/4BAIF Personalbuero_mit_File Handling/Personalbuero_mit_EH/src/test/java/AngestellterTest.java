import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class AngestellterTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void testAngestellter_shouldWork_noExceptionThrown() {
        Angestellter angestellter = null;
        try {
            angestellter = new Angestellter("Anna", Year.of(2001), Year.now());
        } catch (PersonalException e) {
            System.out.println("FEHLER: unerwartete Exception: " + e.getMessage());
            fail();
        }
        System.out.println(angestellter); // Ausgabe ja, Angestellter-Objekt
    }

    @Test
    void testAngestellter_shouldNotWork_nameNull_exceptionThrown() {
        Angestellter angestellter = null;
        try {
            angestellter = new Angestellter(null, Year.of(2001), Year.now());
            fail(); // TODO assertThrows()...
            System.out.println("Darf dieser Text ausgegeben werden? Nein");
        } catch (PersonalException e) {
            System.out.println("Erwartete Exception null: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("NullPointerEx gecatcht: " + e.getMessage());
            fail();
        } catch (RuntimeException e) {
            System.out.println("Runtime gecatcht: " + e.getMessage());
            fail();
        } catch (Exception e) {
            System.out.println("Exception gecatcht: " + e.getMessage());
            fail();
        } catch (Error e) {
            System.out.println("Error gecatcht: " + e.getMessage());
            fail();
        } catch (Throwable e) {
            System.out.println("Throwable gecatcht: " + e.getMessage());
            fail();
        }
        System.out.println(angestellter); // Ausgabe ja, aber null
    }
}