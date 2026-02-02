package at.spengergasse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PegelstandTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void testSetFluss_sollFunktionieren_pegelstandvoll_flussInn_innGesetzt(){
        // given
        Pegelstand pegelstand = new Pegelstand(); // "Donau"
        assertEquals("Donau", pegelstand.getFluss());
        pegelstand.fuelleTestwerte();
        assertEquals(7, pegelstand.getAnzahl()); // volles Array
        pegelstand.ausgebenwerte(); // alle werte sind gemessen


        // when
        pegelstand.setFluss("Inn"); // "Inn"

        //then
        System.out.println(pegelstand.getFluss()); // "Inn"
        assertNotEquals(null, pegelstand.getFluss());
        assertNotEquals("Donau", pegelstand.getFluss());
        assertEquals("Inn", pegelstand.getFluss());
    }

    @Test

    void testAvgWert_sollNichtFunktionieren_leeresArray_returnMinusj99Komma28(){
        //given
        Pegelstand pegelstand = new Pegelstand();
        pegelstand.fuelleTestwerte();
        //when
        float actual = pegelstand.avgWert();//294,28
        float expectet = 294.28f;
        //then
        assertEquals(expectet, actual, 0.009);
        System.out.println(pegelstand);
        System.out.println();
        pegelstand.printPegelstand();
    }

    @Test
    void testMessen_sollFunktionieren_leeresArray_messen7werte_7xTrue_anzahlIst7(){
        //given
        Pegelstand p = new Pegelstand();
        System.out.println();
        //when
        for (int i = 0; i < 7; i++){
            assertTrue(p.messen(i));
        }
        //then
        assertEquals(7, p.getAnzahl());
        p.printPegelstand();
        System.out.println();

    }
    @Test
    void testLoeschen_sollFunktionieren_array3werte_loeschen0_anzahl2(){
        //given
        Pegelstand p = new Pegelstand();
        for (int i = 0; i < 3; i++) {
            assertTrue(p.messen(i));
        }
        assertEquals(3, p.getAnzahl());
        assertEquals(0, p.getWert(0));
        assertEquals(1, p.getWert(1));
        assertEquals(2, p.getWert(2));
        //when
        p.loeschen(0);
        assertEquals(2, p.getAnzahl());
        assertEquals(1,p.getWert(0));
        assertEquals(2,p.getWert(1));
        assertEquals(0, p.getWert(2));


    //then
    p.printPegelstand();
    System.out.println();
    }

    @Test

    void testSortiereWerte_sollFunktionieren_vollesArrayNichtSortiert_sortiersArray() {
        //given
        Pegelstand p = new Pegelstand();
        int wert = 6;
        do {
            assertTrue(p.messen(wert));
            wert--;
        } while (wert >= 0);
        p.printPegelstand();
        System.out.println();

        //when
        p.sortiereWerte();

        //then
        p.printPegelstand();
        System.out.println();
    }

}