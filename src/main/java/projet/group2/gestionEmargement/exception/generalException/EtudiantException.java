package projet.group2.gestionEmargement.exception.generalException;

import java.util.List;

public class EtudiantException extends Exception {
    private ErrorCodes errorCode;

    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    public EtudiantException(String message) {
        super(message);
    }

    public EtudiantException(String message, Throwable cause) {
        super(message, cause);
    }

    public EtudiantException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public EtudiantException(String message, ErrorCodes errorCode, List<String> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors=errors;
    }
}
