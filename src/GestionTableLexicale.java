import java.util.ArrayList;
import java.util.Arrays;

public class GestionTableLexicale {
    public static ArrayList<ArrayList<Object>> unitesLexicales;

    public static void initialiserUnitesLexicales(){
        unitesLexicales = new ArrayList<>();
    }

    public static void insererUniteLexicale(Object valeurUnite, Type type, int valeur)
    {
        ArrayList<Object> parametres = new ArrayList<>();
        parametres.add(valeurUnite);
        parametres.add(type);
        unitesLexicales.add(parametres);
        _Global.DERN_UNITE_LEXICALE = type;
    }

    public static void genererUniteLexicale(Object valeurUnite, Type type){
        insererUniteLexicale(valeurUnite, type, 0);
    }

    public static void genererUniteLexicale(Object valeurUnite, Type type, int valeur){
        insererUniteLexicale(valeurUnite, type, valeur);
    }

}
