@FunctionalInterface
public interface MedienFilter {
    boolean akzeptiert(Medium medium);
}