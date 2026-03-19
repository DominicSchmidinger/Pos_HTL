package spengergasse.at;

public abstract class Animal {
    private String name;
    private int age;

    public Animal(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0){
            throw new IllegalArgumentException("Alter darf nicht kleiner null sein");
        }
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty() || name.length() == 0){
            throw new IllegalArgumentException("Bro der braucht nen namen");
        }
        this.name = name;
    }

    public abstract String makeSound();

    public abstract String animalTyp();

    public int compareTo(Animal animal){
        this.getName().compareTo(animal.name);
        return this.name.compareTo(animal.name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Animal = [");
        sb.append(name).append('\'');
        sb.append(" (" ).append(age).append(") ");
        sb.append(animalTyp());
        sb.append(']');
        return sb.toString();
    }
}


