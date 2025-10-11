import java.io.*;
import java.util.*;

public class F{
    public static void main(String[] argv){
        // read the input 
        QReader reader = new QReader();
        int n = reader.nextInt();
        int p = reader.nextInt();
        int q = reader.nextInt();
        long[] h_minus_s = new long[n];
        long[] p_on_h_minus_s = new long[n]; 

        // to find a plant to use p height potion
        int index = 0;
        int h0 = reader.nextInt();
        int s0 = reader.nextInt();
        long max = h0 * (long) Math.pow(2, p) - s0;
        p_on_h_minus_s[0] = max;
        h_minus_s[0] = h0 - s0;
        long total_s = s0;

        long delta_s;
        int h;
        int s;

        for (int i = 1; i < n; i++){
            h = reader.nextInt();
            s = reader.nextInt();
            total_s += s;
            h_minus_s[i] = h - s;
            delta_s = h * (long) Math.pow(2, p) - s;

            // find the max value in arr p_on_h_minus_s
            if (delta_s > max){
                index = i;
                max = delta_s;
            }

            p_on_h_minus_s[i] = delta_s;
        }
        if (q == 0){
            System.out.println(total_s);
            return;
        }

        h_minus_s[index] = 0; // make sure the plant that have p height potion have no other strength potion
        // If max is a negative number than h_minus_s is also a negative number. Zero and negative number will not be add to total s.

        // use p height potion and one strength potion
        if (max > 0) total_s += max;

        // use the remaining strength potion
        h_minus_s = sort(h_minus_s, 0, h_minus_s.length - 1);
        int max_delta = h_minus_s.length - 1; // the index of delta s after applying strength potion
        for (int i = 0; i < q - 1; i++){ // execute #remained strength potion times
            long delta = h_minus_s[max_delta--];
            if (delta > 0) total_s += delta;
            else break; 
        }
        System.out.println(total_s);

    }

        private static long[] sort(long[] arr, int L, int R){
        if (R - L == 0){
            return new long[] {arr[L]};
        }else if (R - L == 1){
            if (arr[L] > arr[R]){
                return new long[] {arr[R], arr[L]};
            } 
            else return new long[] {arr[L], arr[R]}; 
        }else{
            int mid  = R - (R - L) / 2;
            long[] L_arr = sort(arr, L, mid - 1);
            long[] R_arr = sort(arr, mid, R);

            return merge(L_arr, R_arr);
        }
    }

    private static long[] merge(long[] L, long[] R){
        long[] new_arr = new long[L.length + R.length];
        int L_pnt = 0;
        int R_pnt = 0;
        int sorted = 0;
        while (L_pnt < L.length && R_pnt < R.length){
            if (L[L_pnt] <= R[R_pnt]) new_arr[sorted++] = L[L_pnt++];
            else{
                new_arr[sorted++] = R[R_pnt++];
            }
        }

        for (; L_pnt < L.length;){
            new_arr[sorted++] = L[L_pnt++];
        }
        for (; R_pnt < R.length;){
            new_arr[sorted++] = R[R_pnt++];
        }

        return new_arr;
    }
}

 
class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");
 
    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
 
    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }
 
    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }
 
    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }
 
    public int nextInt() {
        return Integer.parseInt(next());
    }
 
    public long nextLong() {
        return Long.parseLong(next());
    }
}
 
class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
 
    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }
 
    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }
 
    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}