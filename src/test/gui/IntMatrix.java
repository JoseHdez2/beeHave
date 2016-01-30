package test.gui;

// TODO: IntMatrix is a misleading name for a matrix of doubles :p

final public class IntMatrix extends Matrix<Double>{
    private final int M;             // number of rows
    private final int N;             // number of columns

    /*
     * create IntMatrix from data
     */
    public IntMatrix(Double[][] data) {
        super(data);
        this.M = this.width;
        this.N = this.height;
    }
    
    // create M-by-N IntMatrix of 0's
    public IntMatrix(int M, int N) {
        this(new Double[M][N]);
    }  
    
    // copy constructor
    private IntMatrix(IntMatrix A) { this(A.data); }

    // create and return a random M-by-N IntMatrix with values between 0 and 1
    public static IntMatrix random(int M, int N) {
        IntMatrix A = new IntMatrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

    // swap rows i and j
    private void swap(int i, int j) {
        Double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // does A = B exactly?
    public boolean eq(IntMatrix B) {
        IntMatrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal IntMatrix dimensions.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) return false;
        return true;
    }   

    public String toString() {
        String str = "";
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) 
                str += String.format("%9.4f ", data[i][j]);
            str += "\n";
        }
        return str;
    }
    
    public void show(){
        System.out.print(this.toString());
    }
    
    public void set(int x, int y, double value){
    	this.data[x][y] = value;
    }
    
    public Double get(int x, int y){
    	return data[x][y];
    }
    
    public void infinityFill(double infinityValue){
    	for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
               set(i, j, infinityValue);
    }
}