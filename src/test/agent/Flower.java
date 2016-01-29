/**
 * 
 */
package test.agent;

/**
 * @author adexe
 *
 */
public class Flower {
	private int pollen;
	private int visibility;
	
	private RandomNum rand;
	
	public Flower (){
		pollen = rand.randInt(0,100);
		visibility = rand.randInt(0,10);
	}
}
