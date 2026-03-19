package spengergasse.at;

public class Dog extends Animal {
    public Dog(int age, String name) {
        super(age, name);
    }

    public String makeSound(){
        return "hau hau";
    }

    public String animalTyp() {
        return "This animal is a " + getClass().getSimpleName();
    }
}
