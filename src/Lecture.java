import java.io.IOException;

public class Lecture{

    public static void lireChar() throws IOException {
        int caractereEntier;
        // On teste si le caractère lu (formaté actuellement en entier) est une fin de fichier ou non.
        if ((caractereEntier = VariablesGlobales.READER.read()) == -1){
            VariablesGlobales.EST_EOF_ATTEINTE = true;
        }
        VariablesGlobales.CARLU = (char) caractereEntier;

        if(VariablesGlobales.CARLU == '\n'){
            VariablesGlobales.NUM_LIGNE++;
            //System.out.println("--------" + VariablesGlobales.NUM_LIGNE + "---------");
        }

        if(VariablesGlobales.EST_EOF_ATTEINTE){
            new Erreur(1).afficherErreur();
        }

    }

    public static void lireAll() throws IOException, InterruptedException {
        while(!VariablesGlobales.EST_EOF_ATTEINTE){
            sauterSeparateurs();
            recoEntier();
            //lireChar();
            Thread.sleep(100);
            System.out.println(VariablesGlobales.CARLU);
        }
    }

    public static void sauterSeparateurs() throws IOException {
        /* Pour sauter respectivement :
           -Commentaire
           -Retour chariot
           -Saut de ligne
           -Tabulations
           -Espaces
        */

        if(VariablesGlobales.CARLU == '{'){
            while(VariablesGlobales.CARLU != '}'){
                lireChar();
            }
            lireChar();
        }
        while(VariablesGlobales.CARLU == ' '){
            lireChar();
        }
        while(VariablesGlobales.CARLU == '\t'){
            lireChar();
        }
        if(VariablesGlobales.CARLU == '\r'){
            lireChar();
        }
        if(VariablesGlobales.CARLU == '\n'){
            lireChar();
        }
    }

    public static void recoEntier() throws IOException {
        StringBuilder suiteNombre = new StringBuilder();
        while((VariablesGlobales.CARLU >= '0') &&
              (VariablesGlobales.CARLU <= '9')){
            suiteNombre.append(VariablesGlobales.CARLU);
            VariablesGlobales.NOMBRE = Integer.parseInt(suiteNombre.toString());
            System.out.println(VariablesGlobales.NOMBRE);
            lireChar();
        }
    }

    public static void recoFin(){
        System.out.println("F");
    }

}
