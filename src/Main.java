import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Initialiser.initialiser("resources/programme.txt");
        Lecture.Analex();
        GestionIdentificateur.genererTableauIdentificateurs();
        System.out.println(GestionIdentificateur.chercher("exemple"));
        System.out.println(GestionIdentificateur.chercher("x"));
        System.out.println(GestionIdentificateur.chercher("exa"));
        System.out.println(GestionIdentificateur.inserer("abc", Type.ident));
        System.out.println(GestionIdentificateur.inserer("zer", Type.ident));
        System.out.println(GestionIdentificateur.inserer("abc", Type.ident));
        System.out.println(GestionIdentificateur.inserer("exemple", Type.ident));
        System.out.println(GestionIdentificateur.inserer("abc", Type.ident));
        GestionIdentificateur.afficheTableIdentificateur();
    }
}
