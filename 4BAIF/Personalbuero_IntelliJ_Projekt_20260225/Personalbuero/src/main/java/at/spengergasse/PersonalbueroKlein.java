package at.spengergasse;

public class PersonalbueroKlein {

    private Mitarbeiter[] employees;
    // ArrayList
    private int anzahl;

    public int getAnzahl() {
        return anzahl;
    }

    public PersonalbueroKlein() {
        employees = new Mitarbeiter[3];
        //anzahl = 0;
    }

    public boolean aufnehmen(Mitarbeiter employee) {
        if (anzahl >= employees.length || employee == null || employee.berechneAlter() < 15) {
            return false;
        }
        employees[anzahl] = employee;
        anzahl++;
        return true;
    }
}
