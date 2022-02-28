public class ChaineCaractere {
    private String chaine;

    public ChaineCaractere(String chaine){
        if(chaine.length() > Constantes.LONG_MAX_CHAINE){
            chaine = chaine.substring(0, Constantes.LONG_MAX_CHAINE);
        }
        this.chaine = chaine;
    }
}
