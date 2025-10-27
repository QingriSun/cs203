import java.io.*;
import java.util.*;

public class EAI {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        String[] tokens = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }
        
        Arrays.sort(arr);
        
        // The optimal k is arr[n/2 - 1] for even n, or similar pattern
        // But let's use a more reliable method
        int k = findOptimalK(arr);
        int[] arrangement = findOptimalArrangement(arr, k);
        
        pw.println(k);
        for (int i = 0; i < n; i++) {
            pw.print(arrangement[i]);
            if (i < n - 1) pw.print(" ");
        }
        pw.println();
        pw.close();
    }
    
    private static int findOptimalK(int[] sorted) {
        int n = sorted.length;
        return sorted[n / 2 - 1];
    }
    
    private static int[] findOptimalArrangement(int[] sorted, int k) {
        int n = sorted.length;
        int[] result = new int[n];
        
        // Create the optimal arrangement pattern
        int left = 0, right = n - 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                result[i] = sorted[left++];
            } else {
                result[i] = sorted[right--];
            }
        }
        
        // For circular table, we might need to rotate to get better lex order
        return optimizeLexOrder(result, sorted, k);
    }
    
    private static int[] optimizeLexOrder(int[] arr, int[] sorted, int k) {
        int n = arr.length;
        int[] best = arr.clone();
        
        // Try different starting points for circular arrangement
        for (int start = 0; start < n; start++) {
            int[] candidate = new int[n];
            for (int i = 0; i < n; i++) {
                candidate[i] = arr[(start + i) % n];
            }
            
            if (isValidArrangement(candidate, k) && isLexSmaller(candidate, best)) {
                best = candidate;
            }
        }
        
        return best;
    }
    
    private static boolean isValidArrangement(int[] arr, int k) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int a = arr[i];
            int b = arr[(i + 1) % n];
            int c = arr[(i + 2) % n];
            
            int median = medianOfThree(a, b, c);
            if (median < k) return false;
        }
        return true;
    }
    
    private static int medianOfThree(int a, int b, int c) {
        if (a > b) {
            if (c > a) return a;
            return (c > b) ? c : b;
        } else {
            if (c > b) return b;
            return (c > a) ? c : a;
        }
    }
    
    private static boolean isLexSmaller(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] < b[i]) return true;
            if (a[i] > b[i]) return false;
        }
        return false;
    }
}