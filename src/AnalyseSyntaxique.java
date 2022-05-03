import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class AnalyseSyntaxique {
    private static int INDICE_UNILEX = 0;
    private static ArrayList<Object> UNILEX;

    private static HashSet<String> identificateursDeclares = new HashSet<>();

    private enum UnilexParametre{
        NOM_UNILEX, TYPE_UNILEX, VALEUR_UNILEX
    }

    public static void incrementerIndiceUnilex(){
        INDICE_UNILEX++;
        UNILEX = TableLexicale.getUniteLexicale(INDICE_UNILEX);
    }


    // Cette fonction va permettre de tester si un identificateur du même nom a déjà été déclaré
    public static boolean ajouterIdentificateurDeclare(String nom){
        if(identificateursDeclares.contains(nom)){
            new Erreur("IDENTIFICATEUR_DEJA_DECLARE", new ArrayList<>(Arrays.asList(nom))).afficherErreur(true);
            return false;
        }
        else{
            identificateursDeclares.add(nom);
            return true;
        }
    }


    public static void verificationMotClePROGRAMME(){
        if(TableLexicale.chercher("PROGRAMME") == 0){
            incrementerIndiceUnilex();
        }
        else{
            new Erreur("MANQUE_MOTCLE_PROGRAMME", null).afficherErreur(true);
        }
    }

    public static void verificationSyntaxePROGRAMME(){
        verificationMotClePROGRAMME();

        if(TableLexicale.getUniteLexicale(INDICE_UNILEX,
                UnilexParametre.TYPE_UNILEX.ordinal()) == Type.ident){
                incrementerIndiceUnilex();
            if(TableLexicale.getUniteLexicale(INDICE_UNILEX,
            UnilexParametre.TYPE_UNILEX.ordinal()) == Type.ptvirg){
                incrementerIndiceUnilex();
            }
            else{
                new Erreur("MAUVAISE_SYNTAXE_PROGRAMME", null).afficherErreur(true);
            }
        }
        else{
            new Erreur("MAUVAISE_SYNTAXE_PROGRAMME", null).afficherErreur(true);
        }

    }

    public static void verificationSyntaxeVAR() throws InterruptedException {

        if((TableLexicale.getUniteLexicale(INDICE_UNILEX,
                UnilexParametre.TYPE_UNILEX.ordinal()) == Type.motcle)
            &&
            TableLexicale.getUniteLexicale(INDICE_UNILEX,
                    UnilexParametre.NOM_UNILEX.ordinal()).equals("VAR")

        ){
            incrementerIndiceUnilex();

            boolean premiereVariableLu = false;


            while(TableLexicale.getUniteLexicale(INDICE_UNILEX,
                    UnilexParametre.TYPE_UNILEX.ordinal()) != Type.ptvirg){

                if(premiereVariableLu){
                    if(TableLexicale.getUniteLexicale(INDICE_UNILEX,
                            UnilexParametre.TYPE_UNILEX.ordinal()) == Type.virg){
                        incrementerIndiceUnilex();
                    }
                    else{
                        new Erreur("MAUVAISE_SYNTAXE_VAR", null).afficherErreur(true);
                    }

                }
                else{
                    premiereVariableLu = true;
                }


                if(TableLexicale.getUniteLexicale(INDICE_UNILEX,
                        UnilexParametre.TYPE_UNILEX.ordinal()) == Type.ident){

                    ajouterIdentificateurDeclare((String) UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal()));
                    Identificateurs.inserer((String) UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal()));
                    Identificateurs.modifier((String) UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal()), IdentificateurParametres.STATUT.ordinal(), IdentificateurStatut.VARIABLE);


                    incrementerIndiceUnilex();
                }
                else{
                    new Erreur("MAUVAISE_SYNTAXE_VAR", null).afficherErreur(true);
                }

            }

            if(TableLexicale.getUniteLexicale(INDICE_UNILEX,
                    UnilexParametre.TYPE_UNILEX.ordinal()) == Type.ptvirg){
                incrementerIndiceUnilex();

            }
            else{
                new Erreur("MAUVAISE_SYNTAXE_VAR", null).afficherErreur(true);
            }
        }
        else{
            new Erreur("MAUVAISE_SYNTAXE_VAR", null).afficherErreur(true);
        }

    }


    public static void verificationSyntaxeCONST(){

        if((TableLexicale.getUniteLexicale(INDICE_UNILEX,
                UnilexParametre.TYPE_UNILEX.ordinal()) == Type.motcle)
                &&
                TableLexicale.getUniteLexicale(INDICE_UNILEX,
                        UnilexParametre.NOM_UNILEX.ordinal()).equals("CONST")

        ){
            incrementerIndiceUnilex();

            boolean premiereConstLu = false;
            String dernierIdent = null;

            while(TableLexicale.getUniteLexicale(INDICE_UNILEX,
                    UnilexParametre.TYPE_UNILEX.ordinal()) != Type.ptvirg){

                // virgule
                if(premiereConstLu){
                    if(TableLexicale.getUniteLexicale(INDICE_UNILEX,
                            UnilexParametre.TYPE_UNILEX.ordinal()) == Type.virg){
                        incrementerIndiceUnilex();
                    }
                    else{
                        System.out.println("Debug 1");
                        new Erreur("MAUVAISE_SYNTAXE_CONST", null).afficherErreur(true);
                    }

                }
                else{
                    premiereConstLu = true;
                }

                // ident
                if(TableLexicale.getUniteLexicale(INDICE_UNILEX,
                        UnilexParametre.TYPE_UNILEX.ordinal()) == Type.ident){
                    dernierIdent = (String) UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal());
                    ajouterIdentificateurDeclare(dernierIdent);
                    Identificateurs.inserer(dernierIdent);
                    Identificateurs.modifier(dernierIdent, IdentificateurParametres.STATUT.ordinal(), IdentificateurStatut.CONSTANTE);

                    incrementerIndiceUnilex();
                }
                else{
                    System.out.println("Debug 2");
                    new Erreur("MAUVAISE_SYNTAXE_CONST", null).afficherErreur(true);
                }

                // egal
                if(TableLexicale.getUniteLexicale(INDICE_UNILEX,
                        UnilexParametre.TYPE_UNILEX.ordinal()) == Type.eg){
                    incrementerIndiceUnilex();
                }
                else{
                    System.out.println("Debug 3");
                    new Erreur("MAUVAISE_SYNTAXE_CONST", null).afficherErreur(true);
                }

                // valeur
                if(TableLexicale.getUniteLexicale(INDICE_UNILEX,
                        UnilexParametre.TYPE_UNILEX.ordinal()) == Type.ent
                ||
                    TableLexicale.getUniteLexicale(INDICE_UNILEX,
                        UnilexParametre.TYPE_UNILEX.ordinal()) == Type.ch
                ){
                    if(TableLexicale.getUniteLexicale(INDICE_UNILEX,
                            UnilexParametre.TYPE_UNILEX.ordinal()) == Type.ent){
                        Identificateurs.modifier(dernierIdent, IdentificateurParametres.TYPE.ordinal(), Type.ent);
                        Identificateurs.modifier(dernierIdent, IdentificateurParametres.VALEUR.ordinal(), UNILEX.get(UnilexParametre.VALEUR_UNILEX.ordinal()));
                    }
                    else if(TableLexicale.getUniteLexicale(INDICE_UNILEX,
                            UnilexParametre.TYPE_UNILEX.ordinal()) == Type.ch){
                        Identificateurs.modifier(dernierIdent, IdentificateurParametres.TYPE.ordinal(), Type.ch);
                        Identificateurs.modifier(dernierIdent, IdentificateurParametres.VALEUR.ordinal(), UNILEX.get(UnilexParametre.VALEUR_UNILEX.ordinal()));
                    }
                    else{
                        System.out.println("Debug 4");
                        // A CHANGER
                        new Erreur("MAUVAISE_SYNTAXE_CONST", null).afficherErreur(true);
                    }
                        incrementerIndiceUnilex();
                }
                else{
                    System.out.println("Debug 5");
                    new Erreur("MAUVAISE_SYNTAXE_CONST", null).afficherErreur(true);
                }

            }

            if(TableLexicale.getUniteLexicale(INDICE_UNILEX,
                    UnilexParametre.TYPE_UNILEX.ordinal()) == Type.ptvirg){
                incrementerIndiceUnilex();

            }
            else{
                new Erreur("MAUVAISE_SYNTAXE_CONST", null).afficherErreur(true);
            }
        }
        else{
            new Erreur("MAUVAISE_SYNTAXE_CONST", null).afficherErreur(true);
        }
    }


    public static void verificationSyntaxeDEBUT(){
        if((TableLexicale.getUniteLexicale(INDICE_UNILEX,
                UnilexParametre.TYPE_UNILEX.ordinal()) == Type.motcle)
                &&
                (TableLexicale.getUniteLexicale(INDICE_UNILEX,
                        UnilexParametre.NOM_UNILEX.ordinal()).equals("DEBUT"))
        ){
            incrementerIndiceUnilex();
        }
        else{
            new Erreur("MAUVAISE_SYNTAXE_DEBUT", null).afficherErreur(true);
        }
    }


    public static void verificationSyntaxeFIN(){

        if((TableLexicale.getUniteLexicale(INDICE_UNILEX,
                UnilexParametre.TYPE_UNILEX.ordinal()) == Type.motcle)
                &&
            (TableLexicale.getUniteLexicale(INDICE_UNILEX,
                    UnilexParametre.NOM_UNILEX.ordinal()).equals("FIN"))
        ){
            incrementerIndiceUnilex();
            if(TableLexicale.getUniteLexicale(INDICE_UNILEX,
                    UnilexParametre.TYPE_UNILEX.ordinal()) == Type.point){

                if(INDICE_UNILEX < TableLexicale.unitesLexicales.size()-1){
                    new Erreur("DEPASSEMENT_SYNTAXE_FIN", null).afficherErreur(true);
                }
            }
            else{
                new Erreur("MAUVAISE_SYNTAXE_FIN", null).afficherErreur(true);
            }
        }
        else{
            new Erreur("MAUVAISE_SYNTAXE_FIN", null).afficherErreur(true);
        }

    }

    public static void validationExpression(){
        String nomVariable = (String) UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal());
        incrementerIndiceUnilex();
        System.out.println(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()));

        ArrayList<Type> listeOpe = new ArrayList<>(Arrays.asList(Type.plus, Type.moins, Type.mult, Type.divi));
        ArrayList<Object> elements = new ArrayList<>();
        ArrayList<Object> operandes = new ArrayList<>();

        if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.aff)){
            incrementerIndiceUnilex();

            boolean premierNombreLu = false;

            while(!UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.ptvirg)){
                System.out.println(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()));

                if(premierNombreLu){
                    if(listeOpe.contains(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()))){
                        System.out.println("OPERANDE");
                    }

                }
                else{
                    premierNombreLu = true;
                }

                if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.moins)){
                    System.out.println("MOINS");
                }

                if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.ident) &&
                        Identificateurs.getIdentificateur((String) UNILEX.get(0), IdentificateurParametres.TYPE).equals(IdentificateurType.ENTIER)){
                    operandes.add(Identificateurs.getIdentificateur((String) UNILEX.get(0), IdentificateurParametres.VALEUR));
                }
                else{
                    //operandes.add(Identificateurs.getIdentificateur);
                }

            }
        }
        else{
            new Erreur("VARIABLE_MANIPULATION", new ArrayList<>(List.of(UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal())))).afficherErreur(true);
        }
    }


    public static void validationECRIRE(){
        String affichage = "";
        incrementerIndiceUnilex();
        incrementerIndiceUnilex();

        boolean premierParametre = false;

        while(!UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.parfer)){
            System.out.println(UNILEX);

            if(premierParametre){
                incrementerIndiceUnilex();
            }
            else{
                premierParametre = true;
            }

            System.out.println(UNILEX);

            if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.ch) || UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.ident)){
                if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.ch)){
                    affichage += UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal());
                }
                else if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.ident)){
                    affichage += UNILEX.get(UnilexParametre.VALEUR_UNILEX.ordinal());
                }
                incrementerIndiceUnilex();
            }
            else{
                new Erreur("MAUVAIS_TYPE_PARAMETRE_ECRIRE", null).afficherErreur(true);
            }
        }

        incrementerIndiceUnilex(); // pour passer le ')'
        System.out.println(UNILEX);
        if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.ptvirg)){
            incrementerIndiceUnilex();
        }
        else{
            new Erreur("MAUVAISE_SYNTAXE_ECRIRE", null).afficherErreur(true);
        }

        ecrireContenu(affichage);
    }

    public static void validationLIRE(){
        incrementerIndiceUnilex();
        incrementerIndiceUnilex();

        System.out.println(UNILEX);

        if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.ident) &&
                (Identificateurs.getIdentificateur((String) UNILEX.get(0), IdentificateurParametres.STATUT).equals(IdentificateurStatut.VARIABLE))){
            if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.ch)){
                incrementerIndiceUnilex();
            }
            else if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.ident)){
                new Erreur("MAUVAIS_TYPE_PARAMETRE_LIRE", null).afficherErreur(true);
            }

        }
        else{
            new Erreur("MAUVAIS_TYPE_PARAMETRE_LIRE", null).afficherErreur(true);
        }

        incrementerIndiceUnilex(); // pour passer le ')'
        System.out.println(UNILEX);

        if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.ptvirg)){
            incrementerIndiceUnilex();
        }
        else{
            new Erreur("MAUVAISE_SYNTAXE_ECRIRE", null).afficherErreur(true);
        }

        System.out.print("Entrez la valeur de " + UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal()) + " : ");
        //age = console.nextInt();
    }


    public static void ecrireContenu(String chaine){
        System.out.println(chaine);
    }


    public static void validationEnsembleProgramme(){
        System.out.println("Le programme a été compilé avec succès");
        System.out.println("Il va s'exécuter dès maintenant ...");
    }


    public static void Analex() throws InterruptedException {
        UNILEX = TableLexicale.getUniteLexicale(INDICE_UNILEX);

        verificationSyntaxePROGRAMME();

        if(UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal()).equals("VAR")){
            verificationSyntaxeVAR();
        }

        if(UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal()).equals("CONST")){
            verificationSyntaxeCONST();
        }

        verificationSyntaxeDEBUT();

        System.out.println(UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal()));
        System.out.println(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()));

        while(true){
            if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.ident)){
                System.out.println(UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal()));
                if(Identificateurs.chercher((String) UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal())) >= 0){
                    if(Identificateurs.getIdentificateur((String) UNILEX.get(0), IdentificateurParametres.STATUT).equals(IdentificateurStatut.VARIABLE)){

                        System.out.println("ON EST DANS UNE VARIABLE");
                        validationExpression();
                    }
                    else{
                        new Erreur("MANIPULATION_CONSTANTE", new ArrayList<>(List.of(UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal())))).afficherErreur(true);
                    }
                }
                else{
                    new Erreur("VARIABLE_INCONNUE", new ArrayList<>(List.of(UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal())))).afficherErreur(true);
                }

            }

            else if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.motcle) &&
                    UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal()).equals("ECRIRE")){
                validationECRIRE();
            }

            else if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.motcle) &&
                    UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal()).equals("LIRE")){
                validationLIRE();
            }

            else if(UNILEX.get(UnilexParametre.TYPE_UNILEX.ordinal()).equals(Type.motcle) &&
                    UNILEX.get(UnilexParametre.NOM_UNILEX.ordinal()).equals("FIN")){
                verificationSyntaxeFIN();
                validationEnsembleProgramme();
                break;
            }
        }





    }


}
