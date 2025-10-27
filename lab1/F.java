import java.util.Scanner;

public class F{
    public static void main(String[] argv){
        // read the input 
        Scanner scanner = new Scanner(System.in);
        // coordinate of Eve
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        // coordinate of Neko
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();
        int trace_len = scanner.nextInt();
        String trace = scanner.next();
        scanner.close();

        // while the line connecting Neko-moved and Eve-origin is not vertical or horizontal
        int stepN = -1; // to make stepN can start from 0 in the loop
        int stepE = 0;
        int[] movedNeko = new int[2]; // the new coordinate of Neko
        do{
            stepN++;
            stepE = Math.abs(x2 - x1) + Math.abs(y2 - y1);
            movedNeko = moveNeko(trace.charAt(stepN % trace_len), x2, y2);
            // update the coordinate of Neko
            x2 = movedNeko[0];
            y2 = movedNeko[1];
        }while (stepE != stepN && (x2 != 0 || y2 != 0));

        if (stepN == stepE){
            System.out.printf("Eve had taken %d steps", stepE);
            System.out.printf("Neko had taken %d steps", stepN);
            System.out.printf("Neko and Eve met at (%d, %d)", x2, y2);
        }else{
            System.out.println(-1);
        }
    }

    private static int[] moveNeko(char direction, int x, int y){
        switch (direction){
            case 'U':
                y++;
                break;
            case 'D':
                y--;
                break;
            case 'L':
                x--;
                break;
            case 'R':
                x++;
                break;
        }

        int[] movedNeko = {x, y};
        return movedNeko;
    }
}

// did not consider the condition that can is moving straight upwards, left, right...