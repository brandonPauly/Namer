package namer.namegeneration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import namer.ui.NamerUI.Gender;

/**
 * NameScout is a class devoted to turning a text file of names, separated by the newline character, into 
 * an ArrayList of name strings, preceded and ended with "__".
 * 
 * @author Brandon Pauly
 */
public class NameScout {
   
    // a string representing the path to a file that has girl names, separated by the newline character
    private String femaleNamesFile = "data/namesGirls.txt";
    
    // a string representing the path to a file that has boy names, separated by the newline character
    private String maleNamesFile = "data/namesBoys.txt";
    
    // a buffered reader for reading the lines of the file of names, chosen by the user's input
    private BufferedReader bufferedReader;
    
    // an arraylist for the names from the file to pass back to the controller class Namer
    private ArrayList<String> names;
    
    /**
     * Based on the user's choice, either male or female, the function takes the name file and parses the lines to 
     * gather names, then turn those strings into something the model can use by prepending and appending the string "__" to the name.
     * 
     * @param gender the gender chosen by the user's input
     * @throws IOException
     */
    public void scoutNames(Gender gender) throws IOException{
        String file;
        if (gender == Gender.FEMALE){
            file = femaleNamesFile;
        }
        else{
            file = maleNamesFile;
        }
        names = new ArrayList();
        bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        String name;
        // read each line of the file and add "__" to both the beginning of the string, as well as the end of the string
        while ((line = bufferedReader.readLine()) != null) {
           name = "__" + line.toLowerCase() + "__";
           names.add(name);
        }
        bufferedReader.close();
    }
    
    /**
     * Accessor to return the list of names that are prepared to make the model
     * 
     * @return ArrayList of Strings of names preceded and ended with "__"
     */
    public ArrayList<String> getNames(){
        return names;
    }
}
