package app.util;

public abstract class Sys {
    
    /**
     * Wrapper function for System.out.print(). Prints each passed object on same line.
     * Note: there will be no added separators between objects.
     * @param objects
     */
    static public void out(Object... objects){
        String str = "";
        for (int i = 0; i < objects.length; i++)
            str += objects[i].toString();
        System.out.println(str);
    }
    
    /**
     * Wrapper function for System.out.println(). Prints each passed object on separate line.
     * @param objects
     */
    static public void outLn(Object... objects){
        for (int i = 0; i < objects.length; i++)
            System.out.println(objects[i]);
    }
    
    /**
     * Wrapper function for throwing an exception with a string formatted with String.format().
     * @param format    Format of the string
     * @param objects   Objects to be "embedded" into the string.
     * @throws Exception
     */
    public static void exception(String format, Object... objects) throws Exception{
        
        throw new Exception(String.format(format, objects));
    }
    
    /**
     * Wrapper function for System.out.println() and String.format().
     * Note: there will be no added separators between objects.
     * @param objects
     */
    static public void fout(String format, Object... objects){
        System.out.println(String.format(format, objects));
    }
}
