public class Erreur {
    private int codeErreur;
    private String messageErreur;

    public Erreur(int codeErreur){
        this.codeErreur = codeErreur;
        switch (codeErreur){
            case 1:
                messageErreur = "Erreur de syntaxe";
                break;
            case 2:
                messageErreur = "Erreur de type";
                break;

        }
    }

    public void afficherErreur(){
        System.out.println(codeErreur + " : " + messageErreur);
    }
}
