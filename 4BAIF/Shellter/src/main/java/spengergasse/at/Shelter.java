package spengergasse.at;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Shelter {

    private ArrayList<Animal> animals;
    private int Error_Code;

    public Shelter(int error_Code) {
        animals = new ArrayList<>();
        Error_Code = error_Code;
    }

    public int getError_Code() {
        Error_Code = -99;
        return Error_Code;
    }

    public boolean addAnimal(Animal a){
        if (a == null || animals.contains(a)){
            return false;
        }
        return animals.add(a);
    }

    public double averageAge(){
        int average = 0;

        for (Animal a: animals){
            average = a.getAge() / animals.size();
        }
        return average;
    }

    public int counDogs(){
        int wuffis = 0;



        for (Animal a : animals){
            if (a instanceof Dog == false){
                return Error_Code;
            }
            if (a instanceof Dog){
                wuffis++;
            }
        }

        return wuffis;
    }

    public boolean removeFirstAnimalName(String name){
        Iterator<Animal> iter = animals.iterator();

        while (iter.hasNext()){
            if (iter.next().getName().equals(name)){
                iter.remove();
                return true;
            }

        }
        return false;
    }

    public int removeAllAnimalsByAge(int age){
        if (age == 0){
            return Error_Code;
        }
        int anzahl = 0;
        Iterator<Animal> iter = animals.iterator();

        while (iter.hasNext()){
            if (iter.next().getAge() == age){
                anzahl++;
                iter.remove();
            }
        }return anzahl;
    }

    public void sortAnimals(){
        animals.sort(null);
    }

    public void sortAnimalsByAge(){
        animals.sort(new AgeCompareator());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelter = (Shelter) o;
        return Error_Code == shelter.Error_Code && Objects.equals(animals, shelter.animals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animals, Error_Code);
    }
}
