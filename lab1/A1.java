import java.util.Scanner;

public class A1{
    public static void main(String[] argv){
        // compute the sum of 1 ~ n

        // compute the S1 ~ Sn

        // print the inquired Sk

        long[] sum = new long[(int) 1e6];
        sum[0] = 1;
        for (int i = 1; i < 1e6; i++){
            sum[i] = sum[i - 1] + (i + 1);
        }

        long[] S = new long[(int) 1e6];
        S[0] = sum[0];
        for (int i = 1; i < 1e6; i++){
            S[i] = S[i - 1] + sum[i];
        }

        Scanner scanner = new Scanner(System.in);
        int case_num = scanner.nextInt();

        int[] index = new int[case_num];
        for (int i = 0; i < case_num; i++){
            index[i] = scanner.nextInt() - 1;
        }
        scanner.close();

        for (int i = 0; i < case_num; i++){
            System.out.println(S[index[i]]);
        }
    }
}