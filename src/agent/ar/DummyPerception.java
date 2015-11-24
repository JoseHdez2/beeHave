package agent.ar;

/**
 *  Dummy Perception class for testing.
 */
public class DummyPerception {
    String str;
    
    public DummyPerception(String str) {
        this.str = str;
    }

    public String toString(){
        return getStr();
    }
    
    /*
     * Getters and setters.
     */
    
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
