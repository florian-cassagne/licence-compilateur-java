import java.io.IOException;
import java.util.Arrays;

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
            new Erreur("EOF").afficherErreur(true);
        }

    }

    public static void lireAll() throws IOException, InterruptedException {
        lireChar();
        while(!_Global.EST_EOF_ATTEINTE){
            if((_Global.CARLU >= '0') &&
                    (_Global.CARLU <= '9')){
                _Global.DERN_UNITE_LEXICALE = recoEntier();
            }
            else if(_Global.CARLU == '\''){
                _Global.DERN_UNITE_LEXICALE = recoChaine();
            }
            else if((_Global.CARLU >= 'A') &&
                    (_Global.CARLU <= 'z')){
                System.out.println("LECTURE MOT CLE OU IDENTIFICATEUR");
                _Global.DERN_UNITE_LEXICALE = recoIdentOuMotReserve();
            }
            else if(  (_Global.CARLU == '\r') ||
                    (_Global.CARLU == '\n') ||
                    (_Global.CARLU == ' ') ||
                    (_Global.CARLU == '\t')
            ){
                sauterSeparateurs();
            }
            else if((_Global.CARLU >= '{')){
                sauterCommentaire();
            }
            else if(
                ((_Global.CARLU >= '(') && (_Global.CARLU <= '/')) ||
                ((_Global.CARLU >= ':') && (_Global.CARLU <= '>'))
            ){
                recoSymbole();
            }

            Thread.sleep(400);
            System.out.println(_Global.CARLU);
        }
    }

    public static void mettreChaineEnMajuscule(String chaine){
        _Global.DERN_CHAINE = chaine.toUpperCase();
    }

    public static void sauterCommentaire() throws IOException {
        while(_Global.CARLU != '}'){
            lireChar();
        }
        lireChar();
    }

    public static void sauterSeparateurs() throws IOException {
        /* Pour sauter respectivement :
           -Retour chariot
           -Saut de ligne
           -Tabulations
           -Espaces
        */

        while(  (_Global.CARLU == '\r') ||
                (_Global.CARLU == '\n') ||
                (_Global.CARLU == ' ') ||
                (_Global.CARLU == '\t')
        ){
            lireChar();
        }
    }

    public static Types recoEntier() throws IOException{
        StringBuilder suiteNombre = new StringBuilder();
        while((_Global.CARLU >= '0') &&
              (_Global.CARLU <= '9')){
            suiteNombre.append(_Global.CARLU);
            if(Integer.parseInt(suiteNombre.toString()) > _Global.MAXINT){
                new Erreur("DEPASSEMENT_ENTIER").afficherErreur(true);
            }
            _Global.NOMBRE = Integer.parseInt(suiteNombre.toString());

            lireChar();
        }
        return Types.ent;
    }

    public static Types recoChaine() throws IOException {
        StringBuilder suiteChaine = new StringBuilder();
        lireChar();

        int longueurActuelle = 0;
        while (_Global.CARLU != '\''){
            if(_Global.CARLU == '\\'){
                lireChar();
            }
            else{
                longueurActuelle++;
                suiteChaine.append(_Global.CARLU);
                mettreChaineEnMajuscule(suiteChaine.toString());
            }
            lireChar();
        }

        if(longueurActuelle > _Global.LONG_MAX_CHAINE){
            new Erreur("DEPASSEMENT_CHAINE").afficherErreur(true);
        }

        lireChar();
        System.out.println("LA CHAINE EST : " + suiteChaine);
        return Types.ch;
    }

    public static Types recoIdentOuMotReserve() throws IOException {

        StringBuilder suiteChaine = new StringBuilder();
        String mot = "";


        while (((_Global.CARLU >= 'A') &&
                (_Global.CARLU <= 'z')) ||
                ((_Global.CARLU >= '0') &&
                (_Global.CARLU <= '9')) ||
                (_Global.CARLU == '_'))
        {
            suiteChaine.append(_Global.CARLU);
            mot = suiteChaine.toString();
            lireChar();
        }

        System.out.println("L'identificateur/mot-clé est : " + suiteChaine);

        if(mot.length() > _Global.LONG_MAX_IDENT){
            mot = mot.substring(0, _Global.LONG_MAX_IDENT);
        }
        mettreChaineEnMajuscule(mot);

        System.out.println(">>>> " + mot);

        if(Arrays.asList(_Global.TABLE_MOTS_RESERVES).contains(mot)){
            _Global.DERN_MOT_CLE = mot;
            return Types.motcle;
        }
        else{
            _Global.DERN_IDENT = mot;
            return Types.ident;
        }

    }

    public static Types recoSymbole() throws IOException {
        lireChar();
        if(_Global.CARLU == ';'){
            return Types.ptvirg;
        }
        else if(_Global.CARLU == '.'){
            return Types.point;
        }
        else if(_Global.CARLU == '='){
            return Types.eg;
        }
        else if(_Global.CARLU == '+'){
            return Types.plus;
        }
        else if(_Global.CARLU == '-'){
            return Types.moins;
        }
        else if(_Global.CARLU == '*'){
            return Types.mult;
        }
        else if(_Global.CARLU == '/'){
            return Types.divi;
        }
        else if(_Global.CARLU == '('){
            return Types.parouv;
        }
        else if(_Global.CARLU == ')'){
            return Types.parfer;
        }
        else if(_Global.CARLU == '<'){
            lireChar();
            if(_Global.CARLU == '>'){
                return Types.eg;
            }
            else if(_Global.CARLU == '='){
                return Types.infe;
            }
            else{
                return Types.inf;
            }
        }
        else if(_Global.CARLU == '>'){
            lireChar();
            if(_Global.CARLU == '='){
                return Types.supe;
            }
            else{
                return Types.sup;
            }
        }
        else if(_Global.CARLU == ':'){
            lireChar();
            if(_Global.CARLU == '='){
                return Types.eg;
            }
            else{
                return Types.deuxpts;
            }
        }
        else{
            return null;
        }
    }

    public static void recoFin(){

    }

}
