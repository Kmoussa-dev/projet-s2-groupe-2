package projet.group2.gestionEmargement.exception.generalException;

import java.util.List;

public class SecretaireException extends Exception {
    private ErrorCodes errorCode;

    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    public SecretaireException(String message) {
        super(message);
    }

    public SecretaireException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecretaireException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public SecretaireException(String message, ErrorCodes errorCode, List<String> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors=errors;
    }
}
