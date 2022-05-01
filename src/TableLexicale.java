import java.util.ArrayList;

public class TableLexicale {
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

    public static int chercher(String nomIdentificateur){
        for(ArrayList<Object> uniteLexicale : unitesLexicales){
            if(uniteLexicale.get(0).equals(nomIdentificateur)){
                return unitesLexicales.indexOf(uniteLexicale);
            }
        }
        return -1;
    }

    public static int inserer(String nomIdentificateur, Type type){
        ArrayList<Object> parametres = new ArrayList<>();
        for(ArrayList<Object> uniteLexicale : unitesLexicales){
            if(uniteLexicale.get(0).equals(nomIdentificateur)){
                return chercher(nomIdentificateur);
            }
        }
        parametres.add(nomIdentificateur);
        parametres.add(type);
        unitesLexicales.add(parametres);
        return chercher(nomIdentificateur);
    }

}
