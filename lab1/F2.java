import java.util.Scanner;

public class F2{
    public static void main(String[] argv){
        // read the input 
        Scanner scanner = new Scanner(System.in);
        // coordinate of Eve
        long x1 = scanner.nextLong();
        long y1 = scanner.nextLong();
        // coordinate of Neko
        long x2 = scanner.nextLong();
        long y2 = scanner.nextLong();
        long trace_len = scanner.nextLong();
        String trace = scanner.next();
        scanner.close();

        // find the number of step that is larger or equal to the smallest required step
        long L = 0;
        long R = (long) 5e13 + 1; // the max step Eve needs to catch Neko is 5e13, or she can't catch it
        long mid;
        boolean caught = false;
        long[] displace = NekoDisplace(trace_len, trace); // the distance Neko moved in one loop
        long dx = displace[0];
        long dy = displace[1];
        while (L < R){
            mid = (L + R) / 2;
            caught = checkCaught(mid, x1, y1, x2, y2, dx, dy, trace_len, trace);
            if (caught == true) R = mid; // mid larger than or equal to needed steps
            else L = mid + 1; // mid less than needed steps
         }

         if (L != 5e13 + 1) System.out.println(L);
         else System.out.println(-1);
    }

    private static boolean checkCaught(long mid, long x1, long y1, long x2, long y2, long dx, long dy, long trace_len, String trace){
        // the position of Neko after mid time units
        long loop_num = mid / trace_len;
        x2 += loop_num * dx;
        y2 += loop_num * dy;

        // the displace of Neko after the final loop
        char direction;
        for (int i = 0; i < mid % trace_len; i++){
            direction = trace.charAt(i);
            switch (direction) {
                case 'U':
                    y2++;
                    break;
                case 'D':
                    y2--;
                    break;
                case 'R':
                    x2++;
                    break;
                case 'L':
                    x2--;
                    break;
            }
        }

        // compute the distance between initial Eve and moved Neko
        long distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
        if (distance <= mid) return true; // Eve doesn't wait or Eve waits for one minute
        else return false;
    }

    private static long[] NekoDisplace(long trace_len, String trace){ // the displace of Neko in one loop
        long dx = 0;
        long dy = 0;
        char direction;
        for (int i = 0; i < trace_len; i++){
            direction = trace.charAt(i);
            switch (direction) {
                case 'U':
                    dy++;
                    break;
                case 'D':
                    dy--;
                    break;
                case 'R':
                    dx++;
                    break;
                case 'L':
                    dx--;
                    break;
            }
        }
        long[] result = {dx, dy};
        return result;
    }
}