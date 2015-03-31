/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namer.namegeneration;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Brandon Pauly
 * 
 * ProbabilityBag represents a bag of integers that are represented by a probability in their abundance.  The bag has
 * 1000 integers.  So an integer that has a probability of 0.2 will be added to the bag 200 times.  An RNG then generates
 * a random number and the bag position is based on this number.  The number at the position determined by the RNG is the integer
 * returned by a call to nextNumber.
 */
public class ProbabilityBag {
    
    // A bag to put the integers in for a random pull
    ArrayList<Integer> bag;
    
    public ProbabilityBag(){
    }
    
    /**
     * nextNumber produces a number based on probabilities in the array parameter, by adding the integer that represents a character
     * based on its probability of occurring next.  So if position 2 in the array has a value of 0.2, that means that the character 'c'
     * has a probability of 0.2 of following the two characters that precede the next character.  Thus, the number 2 is added to the bag 200 times.
     * Then the total number of integers in the bag will be 1000 and an RNG will generate a number, and the number at the position in the array
     * will be returned representing the next character in the new name being produced.
     * 
     * @param a An array of doubles, representing the probability of all letters in the alphabet occurring, given two specific previous letters
     * @return 
     */
    public int nextNumber(double[] a){
        bag = new ArrayList();
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < (a[i] * 1000); j++){
                bag.add(i);
            }
        }
        Random random = new Random();
        return bag.get(random.nextInt(1000));
    }
}
