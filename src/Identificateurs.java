import java.util.ArrayList;

public class Identificateurs {
    public static ArrayList<ArrayList<Object>> identificateurs = new ArrayList<>();
    public static int generationAdresse = -1;


    public static int chercher(String nomIdentificateur){
        for(ArrayList<Object> identificateur : identificateurs){
            if(identificateur.get(0).equals(nomIdentificateur)){
                return identificateurs.indexOf(identificateur);
            }
        }
        return -1;
    }

    public static int inserer(String nomIdentificateur, IdentificateurStatut statut, IdentificateurType type,
                              Object valeur, int dimension, int adresse,
                              IdentificateurModePassage modePassage,
                              int nombreParametres,
                              IdentificateurType valeurRetour){
        ArrayList<Object> parametres = new ArrayList<>();
        for(ArrayList<Object> identificateur : identificateurs){
            if(identificateur.get(0).equals(nomIdentificateur)){
                return chercher(nomIdentificateur);
            }
        }
        parametres.add(nomIdentificateur);

        // Statut correspond à variable, ou constante, ou fonction
        if(statut != null){
            parametres.add(statut.ordinal());
        }
        else{
            parametres.add("STATUT_A_DEFINIR");
        }

        if(type != null){
            parametres.add(type.ordinal());
        }
        else{
            parametres.add("TYPE_A_DEFINIR");
        }

        if(valeur != null){
            parametres.add(valeur);
        }
        else{
            parametres.add("Aucun");
        }

        // Dimension
        parametres.add(dimension);

        // Adresse
        parametres.add(adresse);

        if(modePassage != null){
            parametres.add(valeur);
        }
        else{
            parametres.add("Aucun");
        }

        // -1 = quand ce n'est pas une fonction
        parametres.add(nombreParametres);

        parametres.add(valeurRetour);

        identificateurs.add(parametres);

        return chercher(nomIdentificateur);
    }

    public static int inserer(String nomIdentificateur){
        return inserer(nomIdentificateur, null, null, null, 0, generationAdresse++, null, -1, null);
    }

    public static void modifier(String nomIdentificateur, int parametre, Object nouvelleValeur){
        int indexIdent = chercher(nomIdentificateur);
        identificateurs.get(indexIdent).set(parametre, nouvelleValeur);

    }

    public static ArrayList<Object> getIdentificateur(String nomIdentificateur){
        int indexIdent = chercher(nomIdentificateur);
        return identificateurs.get(indexIdent);
    }

    public static Object getIdentificateur(String nomIdentificateur, IdentificateurParametres parametre){
        int indexIdent = chercher(nomIdentificateur);
        return identificateurs.get(indexIdent).get(parametre.ordinal());
    }

    public static void afficheTableIdentificateur(){
        int i = 0;
        for (ArrayList<Object> ident : identificateurs){
            i++;
            System.out.println("\r\n");
            System.out.println("------");
            System.out.println("Identificateur n°" + i);
            System.out.println("Nom : " + ident.get(0));
            System.out.println("Statut : " + ident.get(1));
            System.out.println("Type : " + ident.get(2));
            System.out.println("Valeur : " + ident.get(3));
            System.out.println("Dimension : " + ident.get(4));
            System.out.println("Adresse : " + ident.get(5));
            System.out.println("Mode de passage : " + ident.get(6));
            if((int)ident.get(7) == -1){
                System.out.println("Nombre paramètres : Aucun");
            }
            else{
                System.out.println("Nombre paramètres : " + ident.get(7));
            }

            System.out.println("Type de retour : " + ident.get(8));

        }
    }

}
