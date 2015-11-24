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
}
