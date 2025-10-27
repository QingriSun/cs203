import java.io.*;
import java.util.*;
 
public class D{

    private static Node[] help_sorted_arr;

    public static void main(String[] argv){
        QWriter writer = new QWriter();
        QReader reader = new QReader();
        int test_num = reader.nextInt();

        int[][] final_result = new int[test_num][];

        for (int i = 0; i < test_num; i++){
            int len = reader.nextInt();
            boolean is_even = false;
            if (len % 2 == 0){
                len = len - 1; // deal with even length array
                is_even = true;
            } 
            Node origin_nodes[] = new Node[len];

            // the unsorted Nodes
            for (int j = 0; j < len; j++){
                Node temp = new Node(reader.nextInt());
                origin_nodes[j] =temp;
            }
            if (is_even) reader.nextInt(); // consume the last number

            Node[] sorted_arr = new Node[len];
            help_sorted_arr = new Node[len];
            for (int j = 0; j < len; j++){ // make origin nodes take no part in the sorting
                sorted_arr[j] = origin_nodes[j];
            }
            sort(sorted_arr, 0, len - 1);

            // sorted link list
            Node head = new Node(-1);
            Node tail = new Node(-2);
            head.next = tail;
            tail.prev = head;
            Node current = head;
            for (int j = 0; j < len; j++){
                Node temp = sorted_arr[j];
                temp.next = current.next;
                temp.prev = current;
                current.next.prev = temp;
                current.next = temp;
                current = current.next;
            }

            int nodes_num = len;

            int m_num = (len + 1) / 2; // the number of medians
            int[] result = new int[m_num];
            Node median = sorted_arr[(len + 1) / 2 - 1];
            int m_v = median.value; // median value
            result[--m_num] = median.value;

            // delete the last added node of list
            while (nodes_num >= 3){
                Node last_1 = origin_nodes[nodes_num - 1];
                Node last_2 = origin_nodes[nodes_num - 2];
                int l1_v = last_1.value;
                int l2_v = last_2.value;
                if (!((l1_v > m_v && l2_v < m_v) || (l2_v > m_v && l1_v < m_v))){
                    if (l1_v > m_v || l2_v > m_v){
                        median = median.prev;
                        m_v = median.value;
                        result[--m_num] = m_v;
                    }else if (l1_v < m_v || l2_v < m_v){
                        median = median.next;
                        m_v = median.value;
                        result[--m_num] = m_v;
                    }else{ // l1_v = l2_v = m_v
                        int before = check_prev(median, last_1, last_2);
                        int after = check_next(median, last_1, last_2);
                        if (before > 0 && after == 0){
                            median = median.next;
                            m_v = median.value;
                            result[--m_num] = m_v;
                        }else if (after > 0 && before == 0) {
                            median = median.prev;
                            m_v = median.value;
                            result[--m_num] = m_v;
                        }else{ // before = 1, after = 1
                            result[--m_num] = m_v;
                        }
                    }
                }else{
                    result[--m_num] = m_v;
                }

                last_1.prev.next = last_1.next;
                last_1.next.prev = last_1.prev;

                last_2.prev.next = last_2.next;
                last_2.next.prev = last_2.prev;

                nodes_num -= 2; 

            }
            final_result[i] = result;


        }

        for (int i = 0; i < test_num; i++){
            int len_i = final_result[i].length;
            for (int j = 0; j < len_i; j++){
                writer.print(final_result[i][j]);
                writer.print(' ');
            }
            writer.print('\n');
        }
        writer.close();
    }

    private static int check_prev(Node median, Node last_1, Node last_2){ // check if any node before the median one of the last_1 of last_2
        int n = 0;
        int m_v = median.value;
        while (median.value == m_v){
            median = median.prev;
            if (median == last_1 || median == last_2){
                n++;
            }
        }
        return n;
    }

        private static int check_next(Node median, Node last_1, Node last_2){ // check if any node after the median one of the last_1 of last_2
        int n = 0;
        int m_v = median.value;
        while (median.value == m_v){
            median = median.next;
            if (median == last_1 || median == last_2){
                n++;
            }
        }
        return n;
    }

    private static void sort(Node nodes[], int L, int R){
        if (L == R){
            return;
        }else{
            int mid = (L + R) / 2;
            sort(nodes, L, mid);
            sort(nodes, mid + 1, R);
            merge(nodes, L, mid +1, mid, R);
        }
    }

    private static void merge(Node nodes[], int L1, int L2, int R1, int R2){
        int L = L1; 
        int R = L2;
        int N = L1;

        while (L <= R1 && R <= R2){
            if (nodes[L].value <= nodes[R].value){
                help_sorted_arr[N++] = nodes[L++];
            }else{
                help_sorted_arr[N++] = nodes[R++];
            }
        }


        for (; L <= R1;){
            help_sorted_arr[N++] = nodes[L++];
        }
        for (; R <= R2;){
            help_sorted_arr[N++] = nodes[R++];
        }

        for (int i = L1; i <= R2; i++){
            nodes[i] = help_sorted_arr[i];
        }
    }
}

class Node{
    Node next;
    int value;
    Node prev;

    public Node(int value){
        this.value = value;
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