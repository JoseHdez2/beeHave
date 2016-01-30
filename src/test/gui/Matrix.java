package test.gui;

/**
 * Non-redimensionable matrix with elements of type T.
 * 
 * @author jose
 *
 * @param <T>
 */
public class Matrix<T> {
    
    protected T[][] data; // Static 
    protected int width;  // Number of rows.
    protected int height; // Number of columns.

    
    public Matrix(T[][] data){
        this.data = data;
        this.width = data.length;   // TODO: Make sure it's not the other way around!
        this.width = data[0].length;
    }
    
    /*
     * Copy constructor
     */
    public Matrix(Matrix<T> other){
        this(other.data);
    }
    
    
    /**
     * Note: Zeroth notation.
     */
    public T get(int x, int y){
        return data[y][x];
    }
    
    /**
     * Note: Zeroth notation.
     */
    public void set(int x, int y, T value){
        data[y][x] = value;
    }
    
    /**
     * @return Number of columns in matrix.
     */
    public int getWidth(){
        return width;
    }
    
    /**
     * @return Number of rows in matrix.
     */
    public int getHeight(){
        return height;
    }
    
    public String toString(){
        String str = "";
        
        for (int j = 0; j < this.getHeight(); j++){
            for (int i = 0; i < this.getWidth(); i++){
                str += this.get(i,j).toString();
            }
            str += "\n";
        }
        
        return str;
    }
    

}
