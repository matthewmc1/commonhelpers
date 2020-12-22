package demo.exceptions;

public class UtilitiesExceptionHandler extends Exception {

    private static final long serialVersionUID = -32625870802007643L;
    private final Integer errorCode;
    private final String input;

    //common handler that will be used to parse and pass through all errors in the application
    //we will use the error codes enums to define all the error states in the application including error codes and messages
    //it also allows passing input so we can add additional information required for debug if required
    public UtilitiesExceptionHandler(Integer errorCode, String message, String input) {
        super(message);
        this.errorCode = errorCode;
        this.input = input;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getInput() {
        return input;
    }

    
}
