package at.spengergasse;

public class NotizenException extends Exception {
    public NotizenException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotizenException(String message) {
        super(message);
    }
}
