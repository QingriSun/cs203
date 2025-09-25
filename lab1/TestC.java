import java.util.Random;

public class TestC{
    public static void main(String[] argv){
        // build a array of length 10
        int[] arr = new int[10];

        System.out.printf("%d %d\n", 10, 100);

        // generate none-decreased array
        Random random = new Random();
        arr[0] = 0; // initial number
        System.out.printf("%d ", arr[0]);
        for (int i = 1; i < 10; i++){
            arr[i] = arr[i - 1] + random.nextInt(2);
            System.out.printf("%d ", arr[i]);
        }
        System.out.printf("\n");

        // get 10 sets two random number within the array
        int m;
        int n;
        for (int i = 0; i < 100; i++){
            m = random.nextInt(arr[9] + 1);
            n = random.nextInt(arr[9] + 1);
            System.out.printf("%d %d\n", Math.min(m, n), Math.max(m, n));
        }

    }
}


