package test.util.math;

import java.util.Random;

public class RandomNum{
	public static int randInt(int min, int max) {
	
	    Random rand = new Random();
	
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	
	    return randomNum;
	}
}