public class Erreur {
    private int codeErreur;
    private String messageErreur;

    public Erreur(int codeErreur){
        this.codeErreur = codeErreur;
        this.messageErreur = messageErreur;
    }

    public void afficherErreur(){
        System.out.println(codeErreur + " : " + messageErreur);
    }
}
