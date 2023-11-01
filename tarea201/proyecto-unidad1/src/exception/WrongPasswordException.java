package exception;

/**
 * clase que controla cuando se pone una contraseña incorrecta
 */
public class WrongPasswordException extends Exception {
    public WrongPasswordException(String message) {
        super(message);
    }

    public WrongPasswordException() {
        super();
    }

}
