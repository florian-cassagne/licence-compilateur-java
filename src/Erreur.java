public class Erreur {
    private int code;
    private int type;
    private String messageErreur;

    public Erreur(int codeErreur){
        this.code = codeErreur;

            switch (code){
            case 1:
                messageErreur = "Fin de fichier atteinte";
                this.type = 1;
                break;
            case 2:
                messageErreur = "Erreur de type";
                this.type = 1;
                break;

        }
    }

    public void afficherErreur(){
        String messageTypeErreur = "";
        switch (type){
            case 1:
                messageTypeErreur = "Erreur de syntaxe";
                break;
            case 2:
                messageTypeErreur = "Erreur de type";
                break;
            case 3:
                messageTypeErreur = "Erreur sémantique";
                break;
        }

        System.out.println("--- " + messageTypeErreur + " ---");
        System.out.println(code + " : " + messageErreur);
    }
}
