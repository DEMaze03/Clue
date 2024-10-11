package clueGame;

public class BadConfigFormatException extends Exception {
	// Default constructor
    public BadConfigFormatException() {
        super("Bad configuration format.");
    }

    // Constructor that accepts a custom error message
    public BadConfigFormatException(String message) {
        super(message);
    }

    // Constructor that accepts a custom error message and a cause
    public BadConfigFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor that accepts a cause
    public BadConfigFormatException(Throwable cause) {
        super(cause);
    }
}
