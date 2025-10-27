import java.io.*;
import java.util.*;

public class F{
    private static int[] merge_arr;
    private static int[] merge_index;

    public static void main(String[] argv){
        // read the input 
        QReader reader = new QReader();
        int n = reader.nextInt();
        int p = reader.nextInt();
        int q = reader.nextInt();
        int[] h = new int[n];
        int[] s = new int[n];
        int[] index = new int[n];
        int[] h_minus_s = new int[n];

        long total_s = 0;
        int pos_num = 0;
        for (int i = 0; i < n; i++){
            h[i] = reader.nextInt();
            s[i] = reader.nextInt();
            total_s += s[i];
            h_minus_s[i] = h[i] - s[i];
            index[i] = i;

            if (h[i] > s[i]){
                pos_num++;
            }
        }

        if (q == 0){
            System.out.println(total_s);
            return;
        }

        merge_arr = new int[n];
        merge_index = new int[n];
        sort(h_minus_s, index, 0, h_minus_s.length - 1);

        long max_increase = 0;
        long increase;
        if (pos_num < q){ // there is unused strength potion
            for (int i = 0; i < pos_num; i++){
                increase = ((long) pow_two(p)) * h[index[i]] - h[index[i]];
                total_s += h_minus_s[i];
                if (increase > max_increase){
                    max_increase = increase;
                }
            }
            for (int i = pos_num; i < h_minus_s.length; i++){
                increase = ((long) pow_two(p)) * h[index[i]] - s[index[i]];
                if (increase > max_increase){
                    max_increase = increase;
                }
            }
            total_s += max_increase;
        }else{ // the strength poison is used up
            for (int i = 0; i < q; i++){
                increase = ((long) pow_two(p)) * h[index[i]] - h[index[i]];
                total_s += h_minus_s[i];
                if (increase > max_increase){
                    max_increase = increase;
                }
            }
            for (int i = q; i < h_minus_s.length; i++){
                increase = ((long) pow_two(p)) * h[index[i]] - s[index[i]] - h_minus_s[q - 1];
                if (increase > max_increase){
                    max_increase = increase;
                }
            }
            total_s += max_increase;
        }

        System.out.println(total_s);

    }

    private static void sort(int[] arr, int[] index, int L, int R){
        if (R - L == 0){
            return;
        }else{
            int mid = R - (R - L + 1) / 2;
            sort(arr, index, L, mid);
            sort(arr, index, mid + 1, R);
            merge(arr, index, L, mid, mid + 1, R);
        }
    }

    private static void merge(int[] arr, int[] index, int L1, int R1, int L2, int R2){
        int L_pnt = L1;
        int R_pnt = L2;
        int sorted = L1;
        while (L_pnt <= R1 && R_pnt <= R2){
            if (arr[L_pnt] >= arr[R_pnt]){
                merge_index[sorted] = index[L_pnt];
                merge_arr[sorted++] = arr[L_pnt++];
            } 
            else{
                merge_index[sorted] = index[R_pnt];
                merge_arr[sorted++] = arr[R_pnt++];
            }
        }

        for (; L_pnt <= R1;){
            merge_index[sorted] = index[L_pnt];
            merge_arr[sorted++] = arr[L_pnt++];
        }
        for (; R_pnt <= R2;){
            merge_index[sorted] = index[R_pnt];
            merge_arr[sorted++] = arr[R_pnt++];
        }

        for (int i = L1; i <= R2; i++){
            arr[i] = merge_arr[i];
            index[i] = merge_index[i];
        }
    }

    private static int pow_two(int n){
        return 1 << n;
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