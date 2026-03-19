package spengergasse.at;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            Bird bird1 = new Bird(14, "Tweety", true);

            System.out.println("Test");
            System.out.println(bird1);


        } catch (IllegalArgumentException e){
            System.err.println("Fehler bei der Validierung" + e.getMessage());
        }
    }

}