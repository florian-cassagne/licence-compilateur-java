import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Initialiser{

    public static void initialiser(String filename) throws FileNotFoundException{
        VariablesGlobales.SOURCE = filename;
        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        VariablesGlobales.READER = new BufferedReader(fileReader);
        VariablesGlobales.NUM_LIGNE = 1;
    }

}
