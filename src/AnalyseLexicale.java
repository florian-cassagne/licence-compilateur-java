import java.io.IOException;
import java.util.Arrays;

public class AnalyseLexicale {

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
            //new Erreur("EOF").afficherErreur(true);
        }

    }

    public static void Analex() throws IOException{
        lireChar();
        while(!_Global.EST_EOF_ATTEINTE){
            if((_Global.CARLU >= '0') &&
                    (_Global.CARLU <= '9')){
                recoEntier();
            }
            else if(_Global.CARLU == '\''){
                recoChaine();

            }
            else if((_Global.CARLU >= 'A') &&
                    (_Global.CARLU <= 'z')){
                recoIdentOuMotReserve();
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
                Type type = recoSymbole();
                TableLexicale.genererUniteLexicale(type, type);
            }
        }
        System.out.println("Unités lexicales (valeurs) :");
        System.out.println(TableLexicale.unitesLexicales);
        System.out.println("Unités lexicales (types) :");
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

    public static Type recoEntier() throws IOException{
        StringBuilder suiteNombre = new StringBuilder();
        while((_Global.CARLU >= '0') &&
              (_Global.CARLU <= '9')){
            suiteNombre.append(_Global.CARLU);
            if(Integer.parseInt(suiteNombre.toString()) > _Global.MAXINT){
                new Erreur("DEPASSEMENT_ENTIER", null).afficherErreur(true);
            }
            _Global.NOMBRE = Integer.parseInt(suiteNombre.toString());

            lireChar();
        }
        TableLexicale.genererUniteLexicale(_Global.NOMBRE, Type.ent, _Global.NOMBRE);
        return Type.ent;
    }

    public static Type recoChaine() throws IOException {
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
            new Erreur("DEPASSEMENT_CHAINE", null).afficherErreur(true);
        }

        lireChar();

        TableLexicale.genererUniteLexicale(suiteChaine.toString(), Type.ch, suiteChaine.toString());
        return Type.ch;
    }

    public static Type recoIdentOuMotReserve() throws IOException {

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


        if(mot.length() > _Global.LONG_MAX_IDENT){
            new Erreur("DEPASSEMENT_IDENTIFICATEUR", null).afficherErreur(true);
        }
        mettreChaineEnMajuscule(mot);

        if(Arrays.asList(_Global.TABLE_MOTS_RESERVES).contains(mot)){
            _Global.DERN_MOT_CLE = mot;
            TableLexicale.genererUniteLexicale(mot, Type.motcle);
            return Type.motcle;
        }
        else{
            _Global.DERN_IDENT = mot;
            TableLexicale.genererUniteLexicale(mot, Type.ident);
            return Type.ident;
        }

    }

    public static Type recoSymbole() throws IOException {
        if(_Global.CARLU == ';'){
            lireChar();
            return Type.ptvirg;
        }
        else if(_Global.CARLU == ','){
            lireChar();
            return Type.virg;
        }
        else if(_Global.CARLU == '.'){
            lireChar();
            return Type.point;
        }
        else if(_Global.CARLU == '='){
            lireChar();
            return Type.eg;
        }
        else if(_Global.CARLU == '+'){
            lireChar();
            return Type.plus;
        }
        else if(_Global.CARLU == '-'){
            lireChar();
            return Type.moins;
        }
        else if(_Global.CARLU == '*'){
            lireChar();
            return Type.mult;
        }
        else if(_Global.CARLU == '/'){
            lireChar();
            return Type.divi;
        }
        else if(_Global.CARLU == '('){
            lireChar();
            return Type.parouv;
        }
        else if(_Global.CARLU == ')'){
            lireChar();
            return Type.parfer;
        }
        else if(_Global.CARLU == '<'){
            lireChar();
            if(_Global.CARLU == '>'){
                lireChar();
                return Type.eg;
            }
            else if(_Global.CARLU == '='){
                lireChar();
                return Type.infe;
            }
            else{
                lireChar();
                return Type.inf;
            }
        }
        else if(_Global.CARLU == '>'){
            if(_Global.CARLU == '='){
                lireChar();
                return Type.supe;
            }
            else{
                lireChar();
                return Type.sup;
            }
        }
        else if(_Global.CARLU == ':'){
            lireChar();
            if(_Global.CARLU == '='){
                lireChar();
                return Type.aff;
            }
            else{
                lireChar();
                return Type.deuxpts;
            }
        }
        else{
            return null;
        }
    }

}
