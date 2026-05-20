package at.spengergasse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotizenTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void readNotizFromCsvFile() {
        Notizen notizen = new Notizen();

        notizen.readNotizFromCsvFile();

        System.out.println();
        notizen.ausgeben();

        notizen.writeNotizToCsvFile();
    }
}