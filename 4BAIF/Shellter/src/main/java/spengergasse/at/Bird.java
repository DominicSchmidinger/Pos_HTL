package spengergasse.at;

import java.util.Objects;

public class Bird extends Animal{
    private boolean ableFlying;

    public Bird(int age, String name, boolean ableFlying) {
        super(age, name);
        setAbleFlying(ableFlying);
    }

    public void setAbleFlying(boolean ableFlying) {
        this.ableFlying = ableFlying;
    }

    public boolean isAbleFlying() {
        return ableFlying;
    }
    
    public void fly(){
        if (ableFlying == true){
            System.out.println(this.getName() + "can fly");
        }else {
            System.out.println(this.getName() + "can't fly");
        }
    }

    public String makeSound(){
        return "chirp chirp";
    }


    public String animalTyp() {
        return "this animal is a "+ getClass().getSimpleName();
    }

    public void eat() {
        System.out.println(this.getName() + "eats !");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bird bird = (Bird) o;
        return ableFlying == bird.ableFlying;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ableFlying);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
