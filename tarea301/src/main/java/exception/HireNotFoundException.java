package exception;
/**
 * clase para controlar si un alquiler se hizo con éxito
 * @author alba_
 */
public class HireNotFoundException extends Exception {
    public HireNotFoundException(String message) {
        super(message);
    }

    public HireNotFoundException() {
        super();
    }
}
