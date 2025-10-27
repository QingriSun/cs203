import java.io.*;
import java.util.*;

public class E {
    private static Node help_sorted_arr[];

    public static void main(String[] argv){
        QWriter writer = new QWriter();
        QReader reader = new QReader();

        int len = reader.nextInt();

        // the unsorted Nodes
        Node origin_nodes[] = new Node[len];
        for (int j = 0; j < len; j++){
            Node temp = new Node(reader.nextInt());
            origin_nodes[j] =temp;
        }

        // sort the array
        Node[] sorted_arr = new Node[len];
        help_sorted_arr = new Node[len];
        for (int j = 0; j < len; j++){ // make origin nodes take no part in the sorting
            sorted_arr[j] = origin_nodes[j];
        }
        sort(sorted_arr, 0, len - 1);

        // create link list from sorted arr
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

        // get the result from front to back
        int diff[] = new int[len - 1];
        for (int i = 0; i < len - 1; i++){
            int d_f = 10_0000_0001; // the difference with the number in front of the current node
            int d_b = 10_0000_0001; // the difference with the number in the back of the current node
            current = origin_nodes[i];
            int v_f = current.prev.value;
            int v_b = current.next.value;
            int v_c = current.value;
            if (v_f > 0){ // if the node in front is not head
                d_f = Math.abs(v_f - v_c);
            }
            if (v_b > 0){ // if the node in back is not tail
                d_b = Math.abs(v_b - v_c);
            }
            if (d_f >= d_b) diff[i] = d_b;
            else diff[i] = d_f;

            // delete the current node
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        for (int i = 0; i < len - 1; i++){
            writer.print(diff[i]);
            writer.print(' ');
        }
        writer.print(System.lineSeparator());
        writer.close();
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