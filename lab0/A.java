import java.util.Scanner;

public class A{
    public static void main(String[] argv){
        Scanner scanner = new Scanner(System.in);

        int a_len = scanner.nextInt();
        int[] a_arr = new int[a_len]; 

        for (int i = 0; i < a_len; i++)
        {
            a_arr[i] = scanner.nextInt();
        }

        int b_len = scanner.nextInt();
        boolean[] b_arr = new boolean[b_len];

        for (int i = 0; i < b_len; i++)
        {
            boolean in_a = false;
            int b_element = scanner.nextInt();

            for (int j = 0; j < a_len; j++)
            {
                if (b_element == a_arr[j])
                {
                    b_arr[i] = true;
                    in_a = true;
                }
            }
            if (in_a == false)
            {
                b_arr[i] = false;
            }
        }

        for (int i = 0; i < b_len; i++)
        {
            if (b_arr[i])
            {
                System.out.println("yes");
            }
            else
            {
                System.out.println("no");
            }
        }
    }
}