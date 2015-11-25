package agent.ar;

/**
 *  Dummy Action class for testing.
 */
public class DummyAction {
    String str;
    
    public DummyAction(String str) {
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
