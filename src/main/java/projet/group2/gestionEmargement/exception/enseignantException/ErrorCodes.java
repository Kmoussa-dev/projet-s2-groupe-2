package projet.group2.gestionEmargement.exception.enseignantException;

public enum ErrorCodes {
   //les différentes Erreurs pour les enseignants
    ENSEIGNANT_NOT_FOUND(1000),
    ENSEIGNANT_NOT_VALID(5001),
    ID_ENSEIGNANT_NOT_VALID(5002),

    ENSEIGANT_ALREADY_IN_USE(5003),

   SECRETAIRE_NOT_FOUND(6000),
    SECRETAIRE_NOT_VALID (6001) ,
    SECRETAIRE_ALREADY_IN_USE(6002),

    ID_SECRETAIRE_NOT_VALID(6003),

    UTILISATEUR_ALREADY_IN_USE(7000);

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    }
