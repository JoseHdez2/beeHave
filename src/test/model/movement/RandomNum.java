package test.model.movement;

import java.util.Random;

public class RandomNum{
    
	/**
	 * @param min Minimum possible returned value, inclusive.
	 * @param max Maximum possible returned value, inclusive. 
	 * @return Random number between min and max.
	 */
	public static int randInt(int min, int max) {
	
	    Random rand = new Random();
	
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	
	    return randomNum;
	}
}