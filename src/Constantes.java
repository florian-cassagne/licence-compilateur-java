import java.util.ArrayList;
import java.util.HashSet;

public class Constantes {
    private static int NUM_LIGNE;
    private static HashSet<String> MOTS_CLES_RESERVES = new HashSet<>();

    public static int LONG_MAX_IDENT = 20;
    public static int LONG_MAX_CHAINE = 50;
    public static int NB_MOTS_RESERVES = 7;

    public static void setNumLigne(int valeur)
    {
        NUM_LIGNE = valeur;
    }

    public static void insereTableMotsReserves(String motCle)
    {
        MOTS_CLES_RESERVES.add(motCle);
    }
}
