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
}
