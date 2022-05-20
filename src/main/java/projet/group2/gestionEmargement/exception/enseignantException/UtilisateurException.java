package projet.group2.gestionEmargement.exception.enseignantException;

import java.util.List;

public class UtilisateurException extends Exception {

    private ErrorCodes errorCode;
    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    public UtilisateurException(String message) {
        super(message);
    }

    public UtilisateurException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilisateurException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public UtilisateurException(String message, ErrorCodes errorCode, List<String> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors=errors;
    }

}
