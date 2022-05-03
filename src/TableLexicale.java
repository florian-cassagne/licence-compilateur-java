import java.util.ArrayList;

public class TableLexicale {
    public static ArrayList<ArrayList<Object>> unitesLexicales;

    public static void initialiserUnitesLexicales(){
        unitesLexicales = new ArrayList<>();
    }

    private static void insererUniteLexicale(Object valeurUnite, Type type, Object valeur)
    {
        ArrayList<Object> parametres = new ArrayList<>();
        parametres.add(valeurUnite);
        parametres.add(type);
        parametres.add(valeur);
        unitesLexicales.add(parametres);
        _Global.DERN_UNITE_LEXICALE = type;
    }

    public static void genererUniteLexicale(Object valeurUnite, Type type, Object valeur){
        insererUniteLexicale(valeurUnite, type, valeur);
    }

    public static void genererUniteLexicale(Object valeurUnite, Type type){
        insererUniteLexicale(valeurUnite, type, 0);
    }


    public static int chercher(String nomIdentificateur){
        for(ArrayList<Object> uniteLexicale : unitesLexicales){
            if(uniteLexicale.get(0).equals(nomIdentificateur)){
                return unitesLexicales.indexOf(uniteLexicale);
            }
        }
        return -1;
    }



    public static ArrayList<Object> getUniteLexicale(int index){
        return unitesLexicales.get(index);
    }

    public static Object getUniteLexicale(int index_element, int index_parametre){
        return unitesLexicales.get(index_element).get(index_parametre);
    }


}
