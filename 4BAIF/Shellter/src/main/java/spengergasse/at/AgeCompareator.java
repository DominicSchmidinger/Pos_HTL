package spengergasse.at;

import java.util.Comparator;

public class AgeCompareator implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        return Double.compare(o1.getAge(), o2.getAge());
    }
}
