
package exception;

/**
 * clase para controlar si un código existe
 * @author alba_
 */
public class CodeNotFoundException extends Exception{
    public CodeNotFoundException(String message) {
        super(message);
    }

    public CodeNotFoundException() {
        super();
    }
}
