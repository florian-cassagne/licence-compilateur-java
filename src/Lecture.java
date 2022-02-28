import java.io.IOException;

public class Lecture{

    public static void lireChar() throws IOException {
        int caractereEntier;
        // On teste si le caractère lu (formaté actuellement en entier) est une fin de fichier ou non.
        if ((caractereEntier = _Global.READER.read()) == -1){
            _Global.EST_EOF_ATTEINTE = true;
        }
        _Global.CARLU = (char) caractereEntier;

        if(_Global.CARLU == '\n'){
            _Global.incrementLineNumber();
        }

        if(_Global.EST_EOF_ATTEINTE){
            new Erreur(1).afficherErreur();
        }

    }

    public static void lireAll() throws IOException, InterruptedException {
        lireChar();

        while(!_Global.EST_EOF_ATTEINTE){
            sauterSeparateurs();
            if((_Global.CARLU >= '0') &&
                    (_Global.CARLU <= '9')){
                recoEntier();
            }
            else if(_Global.CARLU == '\''){
                recoChaine();
            }
            Thread.sleep(1000);
            System.out.println(_Global.CARLU);
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

        if(_Global.CARLU == '{'){
            while(_Global.CARLU != '}'){
                lireChar();
            }
            lireChar();
        }
        while(_Global.CARLU == ' '){
            lireChar();
        }
        while(_Global.CARLU == '\t'){
            lireChar();
        }
        if(_Global.CARLU == '\r'){
            lireChar();
        }
        if(_Global.CARLU == '\n'){
            lireChar();
        }
    }

    public static Types recoEntier() throws IOException{
        StringBuilder suiteNombre = new StringBuilder();
        while((_Global.CARLU >= '0') &&
              (_Global.CARLU <= '9')){
            suiteNombre.append(_Global.CARLU);
            if(Integer.parseInt(suiteNombre.toString()) > _Global.MAXINT){
                new Erreur(2).afficherErreur();
            }
            _Global.NOMBRE = Integer.parseInt(suiteNombre.toString());

            lireChar();
        }
        return Types.ent;
    }

    // 10 'yookie' 56  'YOOKiE - VOiCES'
    public static Types recoChaine() throws IOException {
        StringBuilder suiteChaine = new StringBuilder();
        lireChar();

        while (_Global.CARLU != '\''){
            suiteChaine.append(_Global.CARLU);
            _Global.CHAINE = suiteChaine.toString();
            lireChar();
        }

        lireChar();

        System.out.println("LA CHAINE EST : " + suiteChaine);
        return Types.ch;
    }

    public static void recoFin(){

    }

}
