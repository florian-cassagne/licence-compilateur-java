public class ChaineCaractere {
    private String chaine;

    public ChaineCaractere(String chaine){
        if(chaine.length() > _Global.LONG_MAX_CHAINE){
            chaine = chaine.substring(0, _Global.LONG_MAX_CHAINE);
        }
        this.chaine = chaine;
    }
}
