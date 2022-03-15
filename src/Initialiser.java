import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Initialiser{

    public static void initialiser(String filename) throws FileNotFoundException{
        _Global.SOURCE = filename;
        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        _Global.READER = new BufferedReader(fileReader);
        _Global.NUM_LIGNE = 1;
        GestionTableLexicale.initialiserUnitesLexicales();
    }

}
