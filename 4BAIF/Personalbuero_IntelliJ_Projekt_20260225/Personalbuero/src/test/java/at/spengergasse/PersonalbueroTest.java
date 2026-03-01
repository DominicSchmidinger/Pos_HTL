package at.spengergasse;

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
    void testToString_leeresPersolabuero_keineMitarbeiterVorhanden(){
        Personalbuero personalbuero = new Personalbuero();
        System.out.println(personalbuero);
    }

    @Test
    void testToString_dreiAngestellte_toStringInformationenMitarbeiterInListe() {
        // given
        Mitarbeiter mitarbeiter1 = new Angestellter("Anna", Year.of(2000), Year.of(2025));
        Mitarbeiter mitarbeiter2 = new Angestellter("Berta", Year.of(2001), Year.of(2025));
        Mitarbeiter mitarbeiter3 = new Angestellter("Caesar", Year.of(2002), Year.of(2025));
        Personalbuero personalbuero = new Personalbuero();
        assertTrue(personalbuero.aufnehmen(mitarbeiter1));
        assertTrue(personalbuero.aufnehmen(mitarbeiter2));
        assertTrue(personalbuero.aufnehmen(mitarbeiter3));
        // when then
        System.out.println(personalbuero); // 3 MitarbeiterInnen in einer Liste
    }

    @Test
    void testBerechneGehaltsumme_shouldWork_einAngestellter_returnGehaltMitarbeiterIn() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        Mitarbeiter mitarbeiter1 = new Angestellter("Anna", Year.of(2000), Year.of(2025));
        personalbuero.aufnehmen(mitarbeiter1);
        // when
        double ergebnis = personalbuero.berechneGehaltsumme();
        double erwartet = mitarbeiter1.berechneGehalt();
        // then
        assertEquals(erwartet, ergebnis);
    }

    @Test
    void testZaehleAlter_shouldNotWork_keineMitarbeiterInnen_returnMinus99Komma0() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        // when, then
        assertEquals(-99, personalbuero.zaehleAlter(15));
    }

    @Test
    void testZaehleAlter_sollFunktionieren_dreiAngestellte_alle25_return3() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        Mitarbeiter mitarbeiter1 = new Angestellter("Anna", Year.of(2001), Year.of(2025));
        Mitarbeiter mitarbeiter2 = new Angestellter("Berta", Year.of(2001), Year.of(2025));
        Mitarbeiter mitarbeiter3 = new Angestellter("Caesar", Year.of(2001), Year.of(2025));
        assertTrue(personalbuero.aufnehmen(mitarbeiter1));
        assertTrue(personalbuero.aufnehmen(mitarbeiter2));
        assertTrue(personalbuero.aufnehmen(mitarbeiter3));
        // when then
        //int zuSuchendesAlter = Year.now().getValue() - Year.of(2001).getValue();
        int zuSuchendesAlter = mitarbeiter1.berechneAlter();
        assertEquals(3, personalbuero.zaehleAlter(zuSuchendesAlter));
    }

    @Test
    void testZaehleAlter_sollFunktionieren_dreiAngestellte_return0() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        Mitarbeiter mitarbeiter1 = new Angestellter("Anna", Year.of(2001), Year.of(2025));
        Mitarbeiter mitarbeiter2 = new Angestellter("Berta", Year.of(2001), Year.of(2025));
        Mitarbeiter mitarbeiter3 = new Angestellter("Caesar", Year.of(2001), Year.of(2025));
        assertTrue(personalbuero.aufnehmen(mitarbeiter1));
        assertTrue(personalbuero.aufnehmen(mitarbeiter2));
        assertTrue(personalbuero.aufnehmen(mitarbeiter3));
        // when then
        //assertEquals(0, personalbuero.zaehleAlter(15)); // 15 kommt bei den 3 MitarbeiterInnen nicht vor
        int zuSuchendesAlter = mitarbeiter1.berechneAlter() - 1; // 24
        assertEquals(0, personalbuero.zaehleAlter(zuSuchendesAlter));
    }

    @Test
    void testZaehleAlter_sollFunktionieren_dreiAngestellte_return2() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        Mitarbeiter mitarbeiter1 = new Angestellter("Anna", Year.of(2000), Year.of(2025));
        Mitarbeiter mitarbeiter2 = new Angestellter("Berta", Year.of(2000), Year.of(2025));
        Mitarbeiter mitarbeiter3 = new Angestellter("Caesar", Year.of(2002), Year.of(2025));
        assertTrue(personalbuero.aufnehmen(mitarbeiter1));
        assertTrue(personalbuero.aufnehmen(mitarbeiter2));
        assertTrue(personalbuero.aufnehmen(mitarbeiter3));
        // when
        int alter = mitarbeiter1.berechneAlter();
        int ergebnis = personalbuero.zaehleAlter(alter);
        int erwartet = 2;
        // then
        assertEquals(erwartet, ergebnis);
    }

    @Test
    void testKuendigenPos_sollFunktionieren_dreiAngestellte_kuendigen0_returnsTrue() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        Mitarbeiter mitarbeiter1 = new Angestellter("Anna", Year.of(2000), Year.of(2025));
        Mitarbeiter mitarbeiter2 = new Angestellter("Berta", Year.of(2000), Year.of(2025));
        Mitarbeiter mitarbeiter3 = new Angestellter("Caesar", Year.of(2002), Year.of(2025));
        assertTrue(personalbuero.aufnehmen(mitarbeiter1));
        assertTrue(personalbuero.aufnehmen(mitarbeiter2));
        assertTrue(personalbuero.aufnehmen(mitarbeiter3));
        int anzahlMitarbeiterVorDemKuendigen = personalbuero.zaehleMitarbeiter(); // 3
        // when
        boolean ergebnis = personalbuero.kuendigen(0);
        boolean erwartet = true;
        int zaehleMitarbeiterErwartet = anzahlMitarbeiterVorDemKuendigen - 1;  // 2
        // then
        assertEquals(erwartet, ergebnis); // check return-Wert
        assertEquals(zaehleMitarbeiterErwartet, personalbuero.zaehleMitarbeiter());  // check anzahl
    }

    @Test
    void testKuendigenAlle_sollFunktionieren_dreiAngestellte2MalAlbert_kuendigenAlbert_returns2() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        Mitarbeiter mitarbeiter1 = new Angestellter("Albert", Year.of(2000), Year.of(2025));
        Mitarbeiter mitarbeiter2 = new Angestellter("Berta", Year.of(2000), Year.of(2025));
        Mitarbeiter mitarbeiter3 = new Angestellter("Albert", Year.of(2002), Year.of(2025));
        assertTrue(personalbuero.aufnehmen(mitarbeiter1));
        assertTrue(personalbuero.aufnehmen(mitarbeiter2));
        assertTrue(personalbuero.aufnehmen(mitarbeiter3));
        int anzahlMitarbeiterVorDemKuendigen = personalbuero.zaehleMitarbeiter(); // 3
        // when
        int ergebnis = personalbuero.kuendigenAlle("Albert");
        int erwartet = 2;
        int zaehleMitarbeiterErwartet = anzahlMitarbeiterVorDemKuendigen - 2;  // 1
        // then
        assertEquals(erwartet, ergebnis); // check return-Wert
        assertEquals(zaehleMitarbeiterErwartet, personalbuero.zaehleMitarbeiter());  // check anzahl
    }

    @Test
    void testKuendigenGehalt_sollFunktionieren_dreiAngestellte_kuendigen0Komm0_returns4500Komma0() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        Mitarbeiter mitarbeiter1 = new Angestellter("Albert", Year.of(2000), Year.now());
        Mitarbeiter mitarbeiter2 = new Angestellter("Berta", Year.of(2000), Year.now());
        Mitarbeiter mitarbeiter3 = new Angestellter("Albert", Year.of(2002), Year.now());
        assertTrue(personalbuero.aufnehmen(mitarbeiter1));
        assertTrue(personalbuero.aufnehmen(mitarbeiter2));
        assertTrue(personalbuero.aufnehmen(mitarbeiter3));
        assertEquals(3, personalbuero.zaehleMitarbeiter());
        System.out.println(personalbuero);
        System.out.println();
        // when
        double erwartet = personalbuero.berechneGehaltsumme(); // 4500.0
        double ergebnis = personalbuero.kuendigen(0.0);
        int zaehleMitarbeiterErwartet = 0;
        // then
        assertEquals(erwartet, ergebnis); // check return-Wert
        assertEquals(zaehleMitarbeiterErwartet, personalbuero.zaehleMitarbeiter());  // check anzahl
        System.out.println(personalbuero);
        System.out.println();
    }


    @Test
    void testGehaltsListe_keineMitarbeiterInnen() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        // when, then
        personalbuero.gehaltsListe();
    }

    @Test
    void testGehaltsListe_eineAngestellte() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        Mitarbeiter mitarbeiter = new Angestellter("Alfred", Year.of(2000), Year.now());
        personalbuero.aufnehmen(mitarbeiter);
        // when, then
        personalbuero.gehaltsListe();  // ein Mitarbeiter, 1500.0
    }

    @Test
    void testGehaltsListe_dreiAngestellte() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        Mitarbeiter mitarbeiter = new Angestellter("Alfred", Year.of(2000), Year.now());
        Mitarbeiter mitarbeiter1 = new Angestellter("Alfred1", Year.of(2000), Year.now());
        Mitarbeiter mitarbeiter2 = new Angestellter("Alfred2", Year.of(2000), Year.now());
        personalbuero.aufnehmen(mitarbeiter);
        personalbuero.aufnehmen(mitarbeiter1);
        personalbuero.aufnehmen(mitarbeiter2);
        // when, then
        personalbuero.gehaltsListe();  // drei Mitarbeiter, 4500.0
    }

    @Test
    void testAufnehmen_eineAngestellte_doppeltesAufnehmen_returnFalse() {
        // given
        Mitarbeiter mitarbeiter1 = new Angestellter("Anna", Year.of(2000), Year.now().minusYears(1));
        Personalbuero personalbuero = new Personalbuero();
        assertTrue(personalbuero.aufnehmen(mitarbeiter1));
        // when then
        assertFalse(personalbuero.aufnehmen(mitarbeiter1));
        System.out.println(personalbuero); // 1 MitarbeiterIn in der Liste mit Prämie
        System.out.println();
    }

    @Test
    void testAufnehmen_zweiAngestelletAnna_doppeltesAufnehmen_returnFalse() {
        // given
        Personalbuero personalbuero = new Personalbuero();

        Mitarbeiter mitarbeiterAnna1 = new Angestellter("Anna", Year.of(2000), Year.now().minusYears(1));
        System.out.println(mitarbeiterAnna1);
        assertTrue(personalbuero.aufnehmen(mitarbeiterAnna1));

        Mitarbeiter mitarbeiterAnna2 = new Angestellter("Anna", Year.of(2000), Year.now().minusYears(1));
        System.out.println(mitarbeiterAnna2);
        System.out.println();

        // when then
        assertFalse(personalbuero.aufnehmen(mitarbeiterAnna2));
        System.out.println(personalbuero); // 1 MitarbeiterIn in der Liste mit Prämie
        System.out.println();
    }

    @Test
    void testBerechneGehaltFreelancer_defaultFreelancer_returns1000Komma0() {
        // given
        Freelancer freelancer = new Freelancer();
        // when
        double ergebnis = freelancer.berechneGehalt();
        double erwartet = freelancer.getStunden()* freelancer.getStundenSatz(); // 1000.0
        // then
        assertEquals(erwartet, ergebnis);
    }

    @Test
    void testFreenlacerAufnehmen_defaultFreelancer_returnsTrue() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        Mitarbeiter freelancer = new Freelancer();
        // when then
        assertTrue(personalbuero.aufnehmen(freelancer));
        System.out.println(personalbuero);
    }

    @Test
    void testPersonalbuero_sollFunktionieren_dreiAngestellte_eineAerztin() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        Mitarbeiter mitarbeiter = new Angestellter("Alfred", Year.of(2000), Year.now());
        Mitarbeiter mitarbeiter1 = new Angestellter("Alfred1", Year.of(2000), Year.now());
        Mitarbeiter mitarbeiter2 = new Angestellter("Alfred2", Year.of(2000), Year.now());
        Arzt arzt = new Arzt("Anna", Year.of(1999), Year.now(), 10, 1000.0);
        personalbuero.aufnehmen(mitarbeiter);
        personalbuero.aufnehmen(mitarbeiter1);
        personalbuero.aufnehmen(mitarbeiter2);
        personalbuero.aufnehmen(arzt);
        // when, then
        assertEquals(4, personalbuero.zaehleMitarbeiter());
        personalbuero.gehaltsListe();  // vier Mitarbeiter, 5500.0
    }

    // NEU
    @Test
    void testAufnehmen_sollNichtFunktionieren_einAngestellter_einFreelancer_doppelt_returnsFalse() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        Mitarbeiter ma1 = new Angestellter("Hubert", Year.of(2000), Year.now());
        Mitarbeiter ma2 = new Freelancer();

        // when
        assertTrue(personalbuero.aufnehmen(ma1));
        assertTrue(personalbuero.aufnehmen(ma2));
        assertFalse(personalbuero.aufnehmen(ma2));

        // then
        assertEquals(2, personalbuero.zaehleMitarbeiter());
        System.out.println(personalbuero);
        System.out.println();
    }

    @Test
    void testAufnehmen_sollFunktionieren_einAngestellter_zweiFreelancer_nichtInhaltsgleichBeiFreelancerAttributen_returnsTrue() {
        // given
        Personalbuero personalbuero = new Personalbuero();
        Mitarbeiter ma1 = new Angestellter("Hubert", Year.of(2000), Year.now());
        Mitarbeiter ma2 = new Freelancer("Zazei", Year.of(2005), Year.now(), 10.0, 10);
        Mitarbeiter ma3 = new Freelancer("Zazei", Year.of(2005), Year.now(), 100.0, 10);

        // when
        assertTrue(personalbuero.aufnehmen(ma1));
        assertTrue(personalbuero.aufnehmen(ma2));
        assertTrue(personalbuero.aufnehmen(ma3));

        // then
        assertEquals(3, personalbuero.zaehleMitarbeiter());
        System.out.println(personalbuero);
        System.out.println();
    }

    // NEU
    @Test
    void testAufnehmen_sollNichtFunktionieren_einAngestellter_einFreelancer_inhaltsgleicherFreelancer_returnsFalse() {
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

        // then
        assertEquals(2, personalbuero.zaehleMitarbeiter());
        System.out.println(personalbuero);
        System.out.println();
    }

    // NEU
    @Test
    void testSortierenNachName_einAngestellterHubert_einFreelancerAnna_sollFunktionieren_returnTrue() {
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
    }

    @Test
    void testKuendigenYear_sollNichtFunktionieren_ein_Angestelter_eintrJahr_null_returns_minus(){
        //given
        Personalbuero personalbuero = new Personalbuero();
        Angestellter angestellter = new Angestellter("Dominic", Year.of(2003), Year.now());
        assertTrue(personalbuero.aufnehmen(angestellter));
        assertEquals(1,personalbuero.zaehleMitarbeiter());
        //when then
        assertEquals(-99, personalbuero.kuendigen((Year) null)); //bei Methoden-Overload -> Durch casten den
                                                                // passenden typ mitteilen
    }
    @Test
    void test_Freelancer_stunden(){
        Personalbuero personalbuero = new Personalbuero();
        Freelancer freelancer1 = new Freelancer("Dominic", Year.of(2003), Year.now(), 100.0, 12);
        Freelancer freelancer2 = new Freelancer("Dominic", Year.of(2003), Year.now(), 90.0, 30);
        assertTrue(personalbuero.aufnehmen(freelancer1));
        assertTrue(personalbuero.aufnehmen(freelancer2));
        //when then
        assertEquals(42, personalbuero.summeFreelancerStunde());
    }
}