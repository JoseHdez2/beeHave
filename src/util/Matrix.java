package util;

public class Matrix<T> {
    
    T[][] data;
    
    public Matrix(T[][] data){
        this.data = data;
    }
    
    /**
     * Note: uses 'zeroth' notation.
     * @param x Column number (horizontal position).
     * @param y Row number (vertical position).
     * @return Element in the x,y position.
     */
    public T get(int x, int y){
        return data[y][x];
    }
    
    /**
     * Note: uses 'zeroth' notation.
     * @param x Column number (horizontal position).
     * @param y Row number (vertical position).
     * @param value Element to be written in the x,y position.
     */
    public void set(int x, int y, T value){
        data[y][x] = value;
    }
    
    /**
     * @return The number of columns of this matrix.
     */
    public int width(){
        return data.length;
    }
    
    /**
     * @return The number of rows of this matrix.
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
