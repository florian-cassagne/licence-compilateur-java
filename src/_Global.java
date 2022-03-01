import java.io.BufferedReader;
import java.util.HashSet;

public class _Global {
    public static String SOURCE;

    public static int NUM_LIGNE;

    public static char CARLU;
    public static int NOMBRE = 0;
    public static String DERN_CHAINE = "";
    public static String DERN_IDENT;
    public static String DERN_MOT_CLE;
    public static Types DERN_UNITE_LEXICALE;


    private static HashSet<String> MOTS_CLES_RESERVES = new HashSet<>();

    public static int LONG_MAX_IDENT = 20;
    public static int LONG_MAX_CHAINE = 50;
    public static int LONG_MAX_MOTCLE = 9;
    public static int MAXINT = Integer.MAX_VALUE;

    public static BufferedReader READER;
    public static boolean EST_EOF_ATTEINTE = false;

    public static String[] TABLE_MOTS_RESERVES = {
            "CONST", "DEBUT", "ECRIRE", "FIN", "LIRE", "PROGRAMME", "VAR"
    };

    public static void insereTableMotsReserves(String motCle)
    {
        MOTS_CLES_RESERVES.add(motCle);
    }


    public static void setNumLigne(int valeur)
    {
        NUM_LIGNE = valeur;
    }

    public static void incrementLineNumber()
    {
        NUM_LIGNE++;
    }
}
