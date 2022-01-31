import java.util.Arrays;

public class MotCleGenerateur {
    private MotCle motCle;

    public MotCleGenerateur(String motCleChaine){
        motCleChaine = (motCleChaine.length() > Constantes.LONG_MAX_IDENT) ?
                motCleChaine.substring(0, Constantes.LONG_MAX_IDENT) : motCleChaine;
        if(Arrays.asList(VariablesGlobales.TABLE_MOTS_RESERVES)
                .contains(motCle)){
            this.motCle = new MotCleReserve(motCleChaine);
        }
    }
}
