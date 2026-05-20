import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class PersonalbueroTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAufnehmen_einAngestellter_einFreelancer_sollFunktionieren() {
        try {
            // given
            Personalbuero personalbuero = new Personalbuero();
            Mitarbeiter ma1 = new Angestellter("Hubert", Year.of(2000), Year.now());
            Mitarbeiter ma2 = new Freelancer();

            // when
            assertTrue(personalbuero.aufnehmen(ma1));
            assertTrue(personalbuero.aufnehmen(ma2));

            // then
            assertEquals(2, personalbuero.zaehleMitarbeiter());
            System.out.println(personalbuero);
            System.out.println();
        } catch (PersonalException e) {
            // Test ist nicht ok -> soll "rot" werden
            fail();
        } finally {
            System.out.println("Testfall Ende");  // kann weggelassen werden
        }
    }

    @Test
    void testSortierenNachName_einAngestellterHubert_einFreelancerAnna_sollFunktionieren_returnTrue() {
        try {
            // given
            Personalbuero personalbuero = new Personalbuero();
            Mitarbeiter ma1 = new Angestellter("Hubert", Year.of(2000), Year.now());
            Mitarbeiter ma2 = new Freelancer();
            assertTrue(personalbuero.aufnehmen(ma1));
            assertTrue(personalbuero.aufnehmen(ma2));
            System.out.println(personalbuero); // Hubert, Anna
            System.out.println();
            // when
            boolean ergebnis = personalbuero.sortierenNachName();
            // then
            assertTrue(ergebnis);
            System.out.println(personalbuero); // Anna, Hubert
            System.out.println();
        } catch (PersonalException e) {
            fail();
        }
    }


    @Test
    void testAufnehmen_sollNichtFunktionieren_einAngestellter_einFreelancer_inhaltsgleicherFreelancer_returnsFalse() {
        try {
            // given
            Personalbuero personalbuero = new Personalbuero();
            Mitarbeiter ma1 = new Angestellter("Hubert", Year.of(2000), Year.now());
            Mitarbeiter ma2 = new Freelancer();
            System.out.println("ma2: " + ma2);
            Mitarbeiter ma3 = new Freelancer();
            System.out.println("ma3: " + ma3);

            // when
            assertTrue(personalbuero.aufnehmen(ma1));
            assertTrue(personalbuero.aufnehmen(ma2));
            assertFalse(personalbuero.aufnehmen(ma3));
            fail();

            // then
        } catch (PersonalException e) {
            System.out.println("Erwartete Exception: keine doppelt Aufnahme " + e.getMessage());
        }
    }

    //NEU
    @Test
    void testAufnehmen_sollFunktionieren_einAngestellter_zweiNichtInhaltsgleicheFreelancer_returnsTrue() {
        try{
        // given
        Personalbuero personalbuero = new Personalbuero();
        Mitarbeiter ma1 = new Angestellter("Hubert", Year.of(2000), Year.now());
        Mitarbeiter ma2 = new Freelancer("Maria", Year.of(2000), Year.now(), 50.0, 20);
        System.out.println("ma2: " + ma2);
        Mitarbeiter ma3 = new Freelancer("Maria", Year.of(2000), Year.now(), 100.0, 10);
        System.out.println("ma3: " + ma3);

        // when
        assertTrue(personalbuero.aufnehmen(ma1));
        assertTrue(personalbuero.aufnehmen(ma2));
        assertTrue(personalbuero.aufnehmen(ma3));

        // then
        assertEquals(3, personalbuero.zaehleMitarbeiter());
        System.out.println(personalbuero);
        System.out.println();
        } catch (PersonalException e) {
            fail();
        }
    }














}