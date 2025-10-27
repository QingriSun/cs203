import java.util.Random;

public class TestF{
    public static void main(String[] argv){
        // create 10,000 correct output to a file
        String str = "wbsz";
        Random random = new Random();
        int c;
        int n;

        System.out.println(1000000);
        for (int j = 0; j < 1000000; j++){
            for (int i = 0; i < 12; i++){
                c = random.nextInt(4);
                if (c == 3){
                    n = random.nextInt(7) + 1;
                }else{
                    n = random.nextInt(9) + 1;
                }
                System.out.printf("%d%c", n, str.charAt(c));
            }
            // print quote
            c = random.nextInt(4);
            if (c == 3){
                n = random.nextInt(7) + 1;
            }else{
                n = random.nextInt(9) + 1;
            }
            System.out.printf("%d%c%d%c", n, str.charAt(c), n, str.charAt(c));
            System.out.printf("\n");
        }



    }
}