package projet.group2.gestionEmargement.exception.enseignantException;

import java.util.List;

public class EnseignantException extends Exception{


    private ErrorCodes errorCode;

    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    public EnseignantException(String message) {
        super(message);
    }

    public EnseignantException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnseignantException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public EnseignantException(String message, ErrorCodes errorCode, List<String> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors=errors;
    }

}
