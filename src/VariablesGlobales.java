import java.io.BufferedReader;

public class VariablesGlobales {
    public static String SOURCE;
    public static char CARLU;
    public static int NOMBRE;
    public static String CHAINE;
    public static int NUM_LIGNE;

    public static BufferedReader READER;
    public static boolean EST_EOF_ATTEINTE = false;

    public static String[] TABLE_MOTS_RESERVES = {
            "PROGRAMME", "DEBUT", "FIN", "CONST", "VAR", "ECRIRE", "LIRE"
    };
}
