import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Initialisation.initialiser("resources/programme.txt");
        AnalyseLexicale.Analex();
        //Identificateurs.genererTableauIdentificateurs();

        AnalyseSyntaxique.Analex();

        System.out.println(Identificateurs.chercher("exemple"));
        System.out.println(Identificateurs.chercher("x"));
        System.out.println(Identificateurs.chercher("exa"));
        //System.out.println(Identificateurs.inserer("abc"));
        //System.out.println(GestionIdentificateur.inserer("zer", Type.ident));
        //System.out.println(GestionIdentificateur.inserer("abc", Type.ident));
        //Identificateurs.afficheTableIdentificateur();

        //System.out.println(TableLexicale.chercher("PROGRAMME"));
        //System.out.println(TableLexicale.chercher("ECRIRE"));
    }
}
