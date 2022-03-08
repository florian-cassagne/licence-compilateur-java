import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Initialiser {


    public Initialiser(String filename) throws FileNotFoundException{
        VariablesGlobales.SOURCE = filename;
        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
    }

}
