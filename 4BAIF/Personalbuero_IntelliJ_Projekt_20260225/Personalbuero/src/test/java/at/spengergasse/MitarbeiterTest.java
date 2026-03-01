package at.spengergasse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class MitarbeiterTest {
/*
    @BeforeEach
    void setUp() {
        System.out.println("------------------- Testfall Beginn ------------");
    }

    @AfterEach
    void tearDown() {
        System.out.println("------------------- Testfall Ende ---------------\n");
    }

    @Test
    void testToString_konsolenausgabe() {
        Mitarbeiter mitarbeiter = new Mitarbeiter("Anna", Year.of(2000), Year.now());
        System.out.println(mitarbeiter);
    }

    @Test
    void testBerechneGehalt_sollFunktionieren_mitarbeiterIn1JahrDabei_return1550Komma0() {
        // given
        Mitarbeiter mitarbeiter = new Mitarbeiter("Anna", Year.of(2000), Year.of(2025));

        // when // then
        assertEquals(1550.0, mitarbeiter.berechneGehalt());
        System.out.println(mitarbeiter);
    }

    @Test
    void testBerechnePraemie_eineMitarbeiterIn_einDienstjahr_return0Komma0() {
        // given
        Mitarbeiter mitarbeiter1 = new Mitarbeiter("Anna", Year.of(2000), Year.now().minusYears(1));
        // when then
        System.out.println(mitarbeiter1); // 1 MitarbeiterIn in der Liste mit Prämie 0.0
        assertEquals(0f, mitarbeiter1.berechnePraemie());
    }

    @Test
    void testBerechnePraemie_eineMitarbeiterIn_15Dienstjahre_return2250Komma0() {
        // given
        Mitarbeiter mitarbeiter1 = new Mitarbeiter("Anna", Year.of(1960), Year.now().minusYears(15));
        // when then
        System.out.println(mitarbeiter1); // 1 MitarbeiterIn in der Liste mit Prämie
        double praemieErwartet = mitarbeiter1.berechneGehalt();
        assertEquals(praemieErwartet, mitarbeiter1.berechnePraemie());
    }

    @Test
    void testBerechnePraemie_eineMitarbeiterIn_20Dienstjahre_return5000Komma0() {
        // given
        Mitarbeiter mitarbeiter1 = new Mitarbeiter("Anna", Year.of(1960), Year.now().minusYears(20));
        // when then
        System.out.println(mitarbeiter1); // 1 MitarbeiterIn in der Liste mit Prämie
        double praemieErwartet = mitarbeiter1.berechneGehalt()*2;
        assertEquals(praemieErwartet, mitarbeiter1.berechnePraemie());
    }

    @Test
    void testBerechnePraemie_eineMitarbeiterIn_25Dienstjahre_return8250Komma0() {
        // given
        Mitarbeiter mitarbeiter1 = new Mitarbeiter("Anna", Year.of(1960), Year.now().minusYears(25));
        // when then
        System.out.println(mitarbeiter1); // 1 MitarbeiterIn in der Liste mit Prämie
        double praemieErwartet = mitarbeiter1.berechneGehalt()*3;
        assertEquals(praemieErwartet, mitarbeiter1.berechnePraemie());
    }

    @Test
    void testBerechnePraemie_eineMitarbeiterIn_50Dienstjahre_return24000Komma0() {
        // given
        Mitarbeiter mitarbeiter1 = new Mitarbeiter("Anna", Year.of(1960), Year.now().minusYears(50));
        // when then
        System.out.println(mitarbeiter1); // 1 MitarbeiterIn in der Liste mit Prämie
        double praemieErwartet = mitarbeiter1.berechneGehalt()*6;
        assertEquals(praemieErwartet, mitarbeiter1.berechnePraemie());
    }

    @Test
    void testBerechnePraemie_eineMitarbeiterIn_49Dienstjahre_return0Komma0() {
        // given
        Mitarbeiter mitarbeiter1 = new Mitarbeiter("Anna", Year.of(1960), Year.now().minusYears(49));
        // when then
        System.out.println(mitarbeiter1); // 1 MitarbeiterIn in der Liste mit Prämie
        double praemieErwartet = 0.0;
        assertEquals(praemieErwartet, mitarbeiter1.berechnePraemie());
    }

    @Test
    void testBerechnePraemie_eineMitarbeiterIn_51Dienstjahre_return0Komma0() {
        // given
        Mitarbeiter mitarbeiter1 = new Mitarbeiter("Anna", Year.of(1960), Year.now().minusYears(51));
        // when then
        System.out.println(mitarbeiter1); // 1 MitarbeiterIn in der Liste mit Prämie
        double praemieErwartet = 0.0;
        assertEquals(praemieErwartet, mitarbeiter1.berechnePraemie());
    }

    @Test
    void testBerechnePraemie_eineMitarbeiterIn_14Dienstjahre_return0Komma0() {
        // given
        Mitarbeiter mitarbeiter1 = new Mitarbeiter("Anna", Year.of(1960), Year.now().minusYears(14));
        // when then
        System.out.println(mitarbeiter1); // 1 MitarbeiterIn in der Liste mit Prämie
        double praemieErwartet = 0.0;
        assertEquals(praemieErwartet, mitarbeiter1.berechnePraemie());
    }

    @Test
    void testBerechnePraemie_eineMitarbeiterIn_16Dienstjahre_return0Komma0() {
        // given
        Mitarbeiter mitarbeiter1 = new Mitarbeiter("Anna", Year.of(1960), Year.now().minusYears(16));
        // when then
        System.out.println(mitarbeiter1); // 1 MitarbeiterIn in der Liste mit Prämie
        double praemieErwartet = 0.0;
        assertEquals(praemieErwartet, mitarbeiter1.berechnePraemie());
    }

 */
}