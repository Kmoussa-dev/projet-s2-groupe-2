package projet.group2.gestionEmargement.exception.enseignantException;

public enum ErrorCodes {
   //les différentes Erreurs pour les enseignants
    ENSEIGNANT_NOT_FOUND(1000),
    ENSEIGNANT_NOT_VALID(5001),
    ID_ENSEIGNANT_NOT_VALID(5002),

    ENSEIGANT_ALREADY_IN_USE(5003);

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    }
