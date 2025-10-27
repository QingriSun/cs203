import java.util.Scanner;

public class A{
    public static void main(String[] argv){

        Scanner scanner = new Scanner(System.in);
        int case_num = scanner.nextInt();

        // an array to store the output
        int[] output = new int[case_num];

        for (int _case = 0; _case < case_num; _case++){
            int element_num = scanner.nextInt();
            int sum = 0;
            for (int i = 1; i <= element_num; i++){
                sum += i * (i + 1) / 2;
            }
            output[_case] = sum;
        }

        for (int i = 0; i < case_num; i++){
            System.out.println(output[i]);
        }
    }
}