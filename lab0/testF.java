import java.util.Random;

public class testF{
    public static void main(String[] argv){
        String[] tiles = {
            "1w", "2w", "3w", "4w", "5w", "6w", "7w", "8w", "9w",
            "1b", "2b", "3b", "4b", "5b", "6b", "7b", "8b", "9b",
            "1s", "2s", "3s", "4s", "5s", "6s", "7s", "8s", "9s",
            "1z", "2z", "3z", "4z", "5z", "6z", "7z"
        };
        
        // output 10000 hu case
        Random random = new Random();
        String[] output;
        for (int i = 0; i < 10000; i++){
            int _case = random.nextInt(4); // 4 winning condition in total
            switch (_case){
                case 0:
                    output


            }

            for (int j = 0; j < 14; j++){
                System.out.printf("%s", tiles[random.nextInt(tiles.length)]);
            }
            System.out.printf("\n");
        }
    }
}