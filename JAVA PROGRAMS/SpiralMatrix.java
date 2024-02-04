import java.util.*;

public class SpiralMatrix {

    public static void main(String[] args) {
    	System.out.println("Enter size of the matrix");
    	Scanner sc=new Scanner(System.in);
    	
    	int n=sc.nextInt();
        int a[][] = new int[n][n];
        int c = 0;
        

        for (int i = 0; i < n; i++) {
            for (int k = i; k < n - i; k++) {
                c = c + 1;
                a[i][k] = c;
            }

            for (int k = i + 1; k < n - i; k++) {
                c = c + 1;
                a[k][n - i - 1] = c;
            }

            for (int k = n - i - 2; k >= i; k--) {
                c = c + 1;
                a[n-i-1][k] = c;
            }

            for (int k = n - i - 2; k > i; k--) {
                c = c + 1;
                a[k][i] = c;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
