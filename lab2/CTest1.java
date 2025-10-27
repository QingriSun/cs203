import java.util.Random;

public class CTest1{
    public static void main(String[] argv){
        Random random = new Random();
        arr_gen(random);
    }

    private static void arr_gen(Random random){
        int arr_len = random.nextInt(1000000 - 2) + 3;
        System.out.println(arr_len);
        for (int i = 0; i < arr_len; i++){
            System.out.printf("%d ", random.nextInt(1000000000 + 1));
        }
        System.out.printf("\n");
    }
}