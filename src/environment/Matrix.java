package environment;

public class Matrix<T> {
    
    T[][] data;
    
    public Matrix(T[][] data){
        this.data = data;
    }
    
    /**
     * Nota: Se empieza a contar desde cero.
     * @param x Número de columna(posición horizontal).
     * @param y Número de fila (posición vertical).
     * @return Elemento en la posición x,y.
     */
    public T get(int x, int y){
        return data[y][x];
    }
    
    /**
     * Nota: Se empieza a contar desde cero.
     * @param x Número de columna(posición horizontal).
     * @param y Número de fila (posición vertical).
     * @param value Elemento a escribir en la posición x,y.
     */
    public void set(int x, int y, T value){
        data[y][x] = value;
    }
    
    /**
     * @return El número de columnas en esta matriz.
     */
    public int width(){
        return data.length;
    }
    
    /**
     * @return El número de filas en esta matriz.
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
