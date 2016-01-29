package test.gui;


public class Matrix<T> {
    

    private T[][] data;


    
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
    public int getWidth(){
        return data.length;
    }
    
    /**
     * @return El número de filas en esta matriz.
     */
    public int getHeight(){
        return data[0].length;
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
