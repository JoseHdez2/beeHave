package test.gui;


final public class IntMatrix {
    private final int M;             // number of rows
    private final int N;             // number of columns
    private final double[][] data;   // M-by-N array

    // create M-by-N IntMatrix of 0's
    public IntMatrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // create IntMatrix based on 2d array
    public IntMatrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                    this.data[i][j] = data[i][j];
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
        double[] temp = data[i];
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

    // print IntMatrix to standard output
    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) 
                System.out.printf("%9.4f ", data[i][j]);
            System.out.println();
        }
    }
    
    public void set(int x, int y, double value){
    	this.data[x][y] = value;
    }
    
    public double get(int x, int y){
    	return data[x][y];
    }
    
    public void infinityFill(double infinityValue){
    	for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
               set(i, j, infinityValue);
    }
}