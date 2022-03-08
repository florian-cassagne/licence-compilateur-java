import java.util.Arrays;

public class MotCleGenerateur {
    private MotCle motCle;

    public MotCleGenerateur(String motCleChaine){
        motCleChaine = (motCleChaine.length() > _Global.LONG_MAX_IDENT) ?
                motCleChaine.substring(0, _Global.LONG_MAX_IDENT) : motCleChaine;
        if(Arrays.asList(_Global.TABLE_MOTS_RESERVES)
                .contains(motCle)){
            this.motCle = new MotCleReserve(motCleChaine);
        }
    }
}
