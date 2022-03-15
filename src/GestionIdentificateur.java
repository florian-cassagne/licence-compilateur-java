import java.util.ArrayList;

public class GestionIdentificateur {
    public static ArrayList<ArrayList<Object>> identificateurs;

    public static void genererTableauIdentificateurs(){
        identificateurs = new ArrayList<>();
        for(int i = 0 ; i < GestionTableLexicale.unitesLexicales.size() ; i++) {
            ArrayList<Object> record = new ArrayList<>();
            record.add(GestionTableLexicale.unitesLexicales.get(i).get(0));
            record.add(GestionTableLexicale.unitesLexicales.get(i).get(1));
            if(GestionTableLexicale.unitesLexicales.get(i).get(1) == Type.ident){
                identificateurs.add(record);
            }
        }
        System.out.println("---------");
        System.out.println(identificateurs);
    }

    public static int chercher(String nomIdentificateur){
        for(ArrayList<Object> identificateur : identificateurs){
            if(identificateur.get(0).equals(nomIdentificateur)){
                return identificateurs.indexOf(identificateur);
            }
        }
        return -1;
    }

    public static int inserer(String nomIdentificateur, Type type){
        ArrayList<Object> parametres = new ArrayList<>();
        parametres.add(nomIdentificateur);
        parametres.add(type);
        identificateurs.add(parametres);
        return chercher(nomIdentificateur);
    }

}
