public class SymboleGenerateur {
    public SymboleGenerateur(char symbole){
        switch(symbole){
            case ',':
                // to do
            case ';':
                // to do
            case '.':
                // to do
            case ':':
                // to do
            case '(':
                // to do
            case ')':
                // to do
            case '<':
                // to do
            case '>':
                // to do
            case '=':
                // to do
            case '+':
                // to do
            case '-':
                // to do
            case '*':
                // to do
            case '/':
                // to do
            default:
                new Erreur(1, "Symbole '" + symbole + "' non reconnu").afficherErreur();
        }
    }
}
