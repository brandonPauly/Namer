package namer;


import java.io.IOException;
import java.util.ArrayList;
import namer.namegeneration.MarkovModel;
import namer.namegeneration.NameScout;
import namer.ui.NamerUI;
import namer.ui.NamerUI.Gender;

/**
 *
 * @author Brandon Pauly
 * 
 * This is the controller class for the Namer application.  The program can be run from this class, and many of the necessary classes
 * that need instantiated are so within this class.  The program begins by creating a UI object to start the user interface.  The UI class
 * asks the user for their inputs throughout the program.  The Namer class also instantiates the NameScout class, which takes and processes 
 * an input file of names based on the user's choice of male or female names.  When the scout has gathered all of the names and prepared them 
 * to be used to create the Markov model, the names are passed back to the Namer class.  The MarkovModel is instantiated, and immediately 
 * given the list of names in order to draw up the model.  Next, the UI is called again to gather the minimum name length, the maximum name length,
 * and the number of names to generate from the user.  The Namer class then loops, asking the MarkovModel to generate a name until the requested
 * number of names are accepted based on the length boundaries.
 */
public class Namer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        // instantiates the UI
        NamerUI ui = new NamerUI();
        
        // calls the initial UI sequence to fire, asking user for gender desired
        ui.beginUI();
        
        // gets the chosen gender from the UI class
        Gender gender = ui.getGender();
        
        // instantiates the NameScout
        NameScout ns = new NameScout();
        
        // gives the enumerated gender option to the scoutNames function which 
        // parses the file for names and prepares the list of names for the training set
        ns.scoutNames(gender);
        
        // the list has been prepared and is given back to this class
        ArrayList names = ns.getNames();
        
        // instantiates the MarkovModel
        MarkovModel markovModel = new MarkovModel();
        
        // passes the list to the Markov model to find the 2nd order probabilities and create the 3d array
        markovModel.createModel(names);
        
        // UI is fired to gather the name parameters from the user
        ui.retreiveNumbers();
        
        // minimum length of the names to accept
        int min = ui.getMin();
        
        // maximum length of the names to accept
        int max = ui.getMax();
        
        // number of original names to gather
        int numberOfNames = ui.getNumberOfNames();
        
        // ArrayList for the new names to be stored in
        ArrayList<String> newNames = new ArrayList();
        
        // loops until the required number of names have been accepted based on the user's parameters
        while (newNames.size() < numberOfNames){
            // asking the Markov model to create a random name
            String name = markovModel.createName();
            // rejects the name if the name lenght is less than the minimum length, more than the maximum length, if the name
            // is already present in this list, or if the name is already present in the original list of names, otherwise adds
            // the name to the list
            if (name.length() <= max+4 && name.length() >= min+4 && !newNames.contains(name) && !names.contains(name)){
                newNames.add(name);
            }
        }
        // fires the UI by passing the list of new names in order to display to System out
        ui.outputNames(newNames);
    }
    
}
