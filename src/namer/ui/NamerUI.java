package namer.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Brandon Pauly
 * 
 * This class is generates the user interface for the user of the Namer to interact with.  The NamerUI gathers info by presenting
 * the user with prompts, then using a scanner to read the users responses.  There are two major sequences throughout runtime, that 
 * have been split into two functions, and the class also has several accessors to retrieve the stored user's responses.
 */
public class NamerUI {

    /**
     * Public enumerator to enumerate the two genders for readability
     */
    public enum Gender { FEMALE, MALE };
    
    /**
     * Setting the initial gender to FEMALE
     */
    private Gender gender = Gender.FEMALE;
    
    /**
     * Scanner for user's responses
     */
    private Scanner responseReader;
    
    /**
     * integers representing the minimum name length, the maximum name length, and the number of desired names to generate
     */
    private int min, max, numberOfNames;
    
    /**
     * beginUI fires the initial display sequences and inquires with the user, what gender they would
     * like the names to be generated for
     */
    public void beginUI(){
        responseReader = new Scanner(System.in);
        System.out.println("Welcome to Namer, the original name generator!");
        System.out.println("Would you like to get female or male name suggestions?");
        System.out.println("Input 'f' for female or 'm' for male:");
        String response = responseReader.next();
        if (response.equals("m")){
            gender = Gender.MALE;
        }   
    }
    
    /**
     * retreiveNumbers asks the user to input numbers for the minimum desired length of names to accept for the name set,
     * the maximum desired length of names to accept for the name set, and the total number of names to accept given the length boundaries
     */
    public void retreiveNumbers(){
        responseReader = new Scanner(System.in);
        System.out.println("Input a positive value of 2 or greater for the minimum name length:");
        min = responseReader.nextInt();
        System.out.println("Input a positive value greater than or equal to your minimum name length choice for a maximum name length:");
        max = responseReader.nextInt();
        System.out.println("Input a positive value representing the total number of names you would like to generate:");
        numberOfNames = responseReader.nextInt();
    }
    
    /**
     * outputNames takes a list of names to prepare and output for the user.  When the function is called,
     * the names are preceded and ended with the string "__".  The list is sorted, then each name is displayed without the 
     * underscores and with the leading character capitalized  ex. "__jacob__" is displayed as "Jacob"
     * 
     * @param newNames a list of original names with "__" preceding and ending the name
    */
    public void outputNames(ArrayList<String> newNames) {
        Collections.sort(newNames);
        for (String name : newNames){
            name = name.substring(2, name.length()-2);
            name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
            System.out.println(name);
        }
    }
    
    /**
     * Accessor for the gender chosen by the user
     */
    public Gender getGender(){
        return gender;
    }
    
    /**
     * Accessor to get the minimum name length chosen by the user
    */
    public int getMin(){
        return min;
    }
    
    /**
     * Accessor to get the maximum name length chosen by the user 
     */
    public int getMax(){
        return max;
    }
    
    /**
     * Accessor to get the number of names to accept, chosen by the user 
     */
    public int getNumberOfNames(){
        return numberOfNames;
    }
}
