import static java.lang.System.exit;

public class Erreur {
    private final String code;
    private int type;
    private String messageErreur;

    public Erreur(String codeErreur){
        this.code = codeErreur;

        switch (code) {
            case "INCONNU" -> {
                messageErreur = "Erreur inconnue";
                this.type = 1;
            }
            case "EOF" -> {
                messageErreur = "Fin de fichier atteinte";
                this.type = 1;
            }
            case "DEPASSEMENT_ENTIER" -> {
                messageErreur = "La taille d'un entier est limitée à 32 bits";
                this.type = 1;
            }
            case "DEPASSEMENT_CHAINE" -> {
                messageErreur = "La taille d'une chaîne de caractère NE DOIT PAS DEPASSER à 50 caractères";
                this.type = 1;
            }
        }
    }

    public void afficherErreur(boolean arreterProgramme){
        String messageTypeErreur = switch (type) {
            case 1 -> "Erreur de syntaxe";
            case 2 -> "Erreur de type";
            case 3 -> "Erreur sémantique";
            default -> "";
        };

        System.out.println("--- " + messageTypeErreur + " ---");
        System.out.println(code + " : " + messageErreur);

        if(arreterProgramme){
            exit(1);
        }
    }
}
