package exception;

/**
 * clase que controlar que un usuario no aparezca en el json
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super();
    }

}
