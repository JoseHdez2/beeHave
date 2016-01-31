package util.typedef;

/**
 * 
 * @author jose
 *
 * Class that models a static-size matrix with elements of type T.
 */
public class Matrix<T> {
    
    T[][] data;
    
    public Matrix(T[][] data){
        this.data = data;
    }
    
    /**
     * Note: Zeroth notation, from upper left.
     * @param x Column number (horizontal offset)
     * @param y Row number (vertical offset)
     * @return Element in position x,y.
     */
    public T get(int x, int y){
        return data[y][x];
    }
    
    /**
     * Note: Zeroth notation, from upper left.
     * @param x Column number (horizontal offset)
     * @param y Row number (vertical offset)
     * @param value Element to write into position x,y.
     */
    public void set(int x, int y, T value){
        data[y][x] = value;
    }
    
    /**
     * @return The matrix's column number // TODO: other way around?
     */
    public int width(){
        return data.length;
    }
    
    /**
     * @return The matrix's row number // TODO: other way around?
     */
    public int height(){
        return data[0].length;
    }
    
    public String toString(){
        String str = "";
        
        for (int j = 0; j < this.height(); j++){
            for (int i = 0; i < this.width(); i++){
                str += this.get(i,j).toString();
            }
            str += "\n";
        }
        
        return str;
    }
}
