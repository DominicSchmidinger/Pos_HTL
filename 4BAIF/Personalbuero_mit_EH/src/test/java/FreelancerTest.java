import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FreelancerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFreelancer() {
        Freelancer freelancer = null;
        try {
            freelancer = new Freelancer();
            System.out.println(freelancer);
        } catch (PersonalException e) {
            System.out.println("FEHLER: unerwartete Exception: " + e.getMessage());
        }
    }











}