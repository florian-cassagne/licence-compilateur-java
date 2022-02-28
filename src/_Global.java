import java.io.BufferedReader;
import java.util.HashSet;

public class _Global {
    public static String SOURCE;

    public static int NUM_LIGNE;

    public static char CARLU;
    public static int NOMBRE = 0;
    public static String CHAINE = "";
    public static String DERN_UNITE_LEXICALE;

    public static boolean EST_CHAINE_EN_COURS = false;
    public static boolean EST_COMMENTAIRE_EN_COURS = false;

    private static HashSet<String> MOTS_CLES_RESERVES = new HashSet<>();

    public static int LONG_MAX_IDENT = 20;
    public static int LONG_MAX_CHAINE = 50;
    public static int LONG_MAX_MOTCLE = 9;
    public static int MAXINT = 2147483647;

    public static BufferedReader READER;
    public static boolean EST_EOF_ATTEINTE = false;

    public static String[] TABLE_MOTS_RESERVES = {
            "PROGRAMME", "DEBUT", "FIN", "CONST", "VAR", "ECRIRE", "LIRE"
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
