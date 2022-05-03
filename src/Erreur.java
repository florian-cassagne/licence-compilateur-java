import java.util.ArrayList;

import static java.lang.System.exit;

public class Erreur {
    private String code;
    private int type;
    private String messageErreur;

    public Erreur(String codeErreur, ArrayList<Object> parametres){
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
            case "DEPASSEMENT_IDENTIFICATEUR" -> {
                messageErreur = "Le nom d'un identificateur ne doit pas dépasser 20 caractères";
                this.type = 1;
            }

            case "MANQUE_MOTCLE_PROGRAMME" -> {
                messageErreur = "Le programme ne débute pas par le mot-clé PROGRAMME, il est obligatoire de le renseigner, et de cette manière :\r\n";
                messageErreur += "PROGRAMME <nom du programme>;";
                this.type = 2;
            }

            case "MAUVAISE_SYNTAXE_PROGRAMME" -> {
                messageErreur = "La ligne de déclaration du nom du programme n'est pas correctement écrire, voici sa syntaxe : \r\n";
                messageErreur += "PROGRAMME <nom du programme>;";
                this.type = 2;
            }

            case "MAUVAISE_SYNTAXE_VAR" -> {
                messageErreur = "La ligne de déclaration du nom du programme n'est pas correctement écrire, voici sa syntaxe : \r\n";
                messageErreur += "VAR <identifiant1>(, <identifiant2>);";
                this.type = 2;
            }

            case "MAUVAISE_SYNTAXE_CONST" -> {
                messageErreur = "La ligne de déclaration du nom du programme n'est pas correctement écrire, voici sa syntaxe : \r\n";
                messageErreur += "CONST <identifiant1> = <valeur1> (, <identifiant2> = <valeur2> (...));";
                this.type = 2;
            }

            case "MAUVAISE_SYNTAXE_DEBUT" -> {
                messageErreur = "Vous n'avez pas écrit la ligne indiquant le début du programme, voici sa syntaxe : \r\n";
                messageErreur += "DEBUT";
                this.type = 2;
            }

            case "MAUVAISE_SYNTAXE_FIN" -> {
                messageErreur = "La ligne pour indiquer la fin du programme n'est pas correctement écrire, voici sa syntaxe : \r\n";
                messageErreur += "FIN.";
                this.type = 2;
            }

            case "DEPASSEMENT_SYNTAXE_FIN" -> {
                messageErreur = "IL est interdit d'écrire du code après l'instruction FIN.";
                this.type = 2;
            }

            case "MAUVAISE_SYNTAXE_ECRIRE" -> {
                messageErreur = "Vous n'avez pas écrit la ligne indiquant le début du programme, voici sa syntaxe : \r\n";
                messageErreur += "ECRIRE(param1 {, param2 {, param3}});";
                this.type = 2;

            }

            case "VARIABLE_INCONNUE" -> {
                messageErreur = "La variable " + parametres.get(0) + " est inconnue";
                this.type = 2;
            }

            case "VARIABLE_MANIPULATION" -> {
                messageErreur = "Vous ne pouvez pas utiliser votre variable " + parametres.get(0) + " autre que pour de l'affectation ou utilisation dans les paramètres d'une fonction";
                this.type = 2;
            }

            case "IDENTIFICATEUR_DEJA_DECLARE" -> {
                messageErreur = "L'identificateur nommé " + parametres + " a déjà été déclaré !";
                this.type = 3;
            }

            case "MANIPULATION_CONSTANTE" -> {
                messageErreur = "L'identificateur nommé " + parametres + " est une constante, vous ne pouvez pas lui affecter de nouvelles valeurs autres que dans sa déclaration dans CONST !";
                this.type = 3;
            }

            case "MAUVAIS_TYPE_PARAMETRE_ECRIRE" -> {
                messageErreur = "Vous avez renseigné des types autres que chaine ou entier dans les paramètres de la fonction ECRIRE";
                this.type = 3;
            }

            case "MAUVAIS_TYPE_PARAMETRE_LIRE" -> {
                messageErreur = "Vous devrez renseigner qu'un seule variable de type entier dans la fonction LIRE";
                this.type = 3;
            }

        }
    }

    public void afficherErreur(boolean arreterProgramme){
        String messageTypeErreur = switch (type) {
            case 1 -> "Erreur lexicale";
            case 2 -> "Erreur syntaxique";
            case 3 -> "Erreur sémantique";
            default -> "";
        };

        System.out.println("\r\n");
        System.out.println("--- " + messageTypeErreur + " ---");
        System.out.println(code + " : " + messageErreur);

        if(arreterProgramme){
            exit(1);
        }
    }
}
