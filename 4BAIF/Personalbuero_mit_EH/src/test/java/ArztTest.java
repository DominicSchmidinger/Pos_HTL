import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class ArztTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testArzt_testBerechneStundensatz_sollFunktionieren() {
        try {
            Personalbuero personalbuero = new Personalbuero();
            Arzt arzt = new Arzt("Schmid", Year.of(2005), Year.now(), 40, 20000.0);

            assertTrue(personalbuero.aufnehmen(arzt));
            System.out.println(personalbuero);

            assertEquals((20000.0 / 40), arzt.berechneStundensatz());
        } catch (PersonalException e) {
            fail();
        }
    }













}