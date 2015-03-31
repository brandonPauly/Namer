package namer.namegeneration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Brandon Pauly
 * 
 * The MarkovModel class houses much of the logic behind the AI itself.  The model is built on a 3d array of doubles that map
 * the probability of a letter occurring, given two specific preceding letters.  This model is built based on a set of 1000 names,
 * either male or female, depending on the user's choice.  The class runs through the name set, gathering the counts of the three letter sets,
 * then normalizes those sets to obtain probabilities of each letter, given two specific preceding letters.  The class also generates names randomly 
 * based on the probabilities in the MarkovModel.
 */
public class MarkovModel {
    
    /**
     * Three dimensional array of doubles to model the probability matrix
     */
    private double[][][] model;
    
    /**
     * HashMap to quickly grab the number code of the letter as its position in the alphabet
    */
    private HashMap<Character, Integer> letterSet;
    
    /**
     * String representing the alphabet, plus an underscore at the end to encode the underscore
     */
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz_";
    
    /**
     * to turn the alphabet variable into an array of chars
     */
    char[] chars;
    
    /**
     * Constructor to instantiate the collections, and set up the HashMap
     */
    public MarkovModel(){
        letterSet = new HashMap();
        chars = alphabet.toCharArray();
        int n = 0;
        for(char d : chars) {
           letterSet.put(d, n);
           n++;
        }
        model = new double[27][27][27];
    }

    /**
     * createModel loops through the list of names and calls analyzeName on each name in order to count all
     * of the occurrences of each set of three characters.  Then loops through the 3d array, normalizing each
     * set of letters to be row stochastic
     * @param names the list of names from the input file
     */
    public void createModel(ArrayList<String> names) {
        for (String name : names){
            analyzeName(name);
        }
        for (int i = 0; i < 27; i++){
            for (int j = 0; j < 27; j++){
                int rowCount = 0;
                for (int k = 0; k < 27; k++){
                    rowCount += model[i][j][k];
                }
                for (int m = 0; m < 27; m++){
                    model[i][j][m] = model[i][j][m]/rowCount;
                }
            }
        }
    }
    
    /**
     * analyzeName loops through the name given in sets of three letters, incrementing the appropriate
     * position in the 3d array as it goes
     * @param name the name being analyzed
     */
    private void analyzeName(String name){
        char c1, c2, c3;
        for (int i = 0; i < (name.length() - 2); i++){
            c1 = name.charAt(i);
            c2 = name.charAt(i+1);
            c3 = name.charAt(i+2);
            model[letterSet.get(c1)][letterSet.get(c2)][letterSet.get(c3)]++;
        }
    }

    /**
     * createName generates a random name based on the probabilities that are in the model and a random number generator
     * @return the generated name preceded and ended with "__"
     */
    public String createName() {
        ProbabilityBag pb = new ProbabilityBag();
        String name = "__";
        int nextChar = -1;
        while (nextChar != 26){
            nextChar = pb.nextNumber(model[letterSet.get(name.charAt(name.length()-2))][letterSet.get(name.charAt(name.length()-1))]);
            name = name + chars[nextChar];
        }
        name = name + "_";
        return name;
    }
    
}
