import java.io.IOException;

public class AnalyseSyntaxique {
    public static void verificationMotClePROGRAMME(){
        if(TableLexicale.chercher("PROGRAMME") != 0){
            new Erreur("MANQUE_MOTCLE_PROGRAMME").afficherErreur(true);
        }
    }

    public static void Analex() throws IOException {
        verificationMotClePROGRAMME();
    }


}
