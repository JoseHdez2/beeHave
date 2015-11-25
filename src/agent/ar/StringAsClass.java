package agent.ar;

/**
 *  Dummy class, to use in dummy classes.
 *  Use: being able to create dummy classes that are just strings.
 *  We don't use Strings because we want to keep the semantic meaning
 *  of the overlying code that uses the dummy classes/strings.
 *  This allows for easier substitution of dummy classes later on.
 */
public class StringAsClass {
    String str;
    
    public StringAsClass(String str) {
        this.str = str;
    }

    public String toString(){
        return getStr();
    }
    
    /*
     * Equals and hashCode
     */
    
    public boolean equals(Object ob){
        if (ob == null) return false;
        if (ob.getClass() != getClass()) return false;
        // TODO: can lower code be changed into [this.hashCode() == ob.hashCode()]?
        StringAsClass other = (StringAsClass)ob;
        if (!str.equals(other.str)) return false;
        return true;
    }
    
    public int hashCode(){
        return str.hashCode();
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
