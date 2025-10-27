import java.io.*;
import java.util.*;
 
public class F{
    public static void main(String[] argv){
        QReader reader = new QReader();
        QWriter writer = new QWriter();
        int test_num = reader.nextInt();

        Node heads[] = new Node[test_num];

        // for each rest case
        for (int i = 0; i < test_num; i++){
            int len = reader.nextInt();

            // record the edge of decreasing sequence
            Node edge[] = new Node[len];
            int index = 0;
            int v1 = 0; // the value of the previous Node
            int v2 = 0; // the value of the current Node
            int v3 = 0; // the value of the next Node

            // create a link list
            Node head = new Node(0);
            Node tail = new Node(10_0001);
            Node current = head;
            head.next = tail;
            tail.prev = head;
            if (len == 0){
                heads[i] = head;
                continue;
            }
            v2 = reader.nextInt(); // The first input
            if (len == 1){
                Node temp = new Node(v2);
                temp.next = current.next;
                temp.prev = current;
                current.next.prev = temp;
                current.next = temp;
                heads[i] = head;
                continue;
            }
            for (int j = 1; j < len; j++){
                v3 = reader.nextInt();
                if (v1 <= v2 && v2 <= v3){
                    Node temp = new Node(v2);
                    temp.next = current.next;
                    temp.prev = current;
                    current.next.prev = temp;
                    current.next = temp;
                    current = current.next;
                    v1 = v2;
                    v2 = v3;
                }else{
                    if (!current.in_edge){
                        edge[index++] = current;
                        current.in_edge = true;
                    } 
                    v1 = v2;
                    v2 = v3;
                }

            }
            if (v2 >= v1){
                Node temp = new Node(v2);
                temp.next = current.next;
                temp.prev = current;
                current.next.prev = temp;
                current.next = temp;
            }

            while (true){
                int done = 0;
                for (int j = 0; j < index; j++){
                    if (!edge[j].in_list){
                        done++;
                        continue;
                    }

                    if (edge[j].value == 0){ // edge is the header
                        done++;
                        continue;
                    }

                    Node n1 = edge[j];
                    Node n2 = edge[j].next;

                    // if the next one is larger or equal
                    if (n1.value <= n2.value){
                        done++;
                        continue;
                    } 

                    while (n1.value > n2.value){
                        n1.in_list = false;
                        n2.in_list = false;
                        n1 = n1.next;
                        n2 = n2.next;
                    }
                    edge[j].prev.next = n2;
                    n2.prev = edge[j].prev;

                    edge[j] = edge[j].prev;
                }

                if (done == index) break;
            }

            heads[i] = head;

        }

        Node current;
        for (int i = 0; i < test_num; i++){
            current = heads[i].next;
            while (current.value != 100001){
                writer.print(current.value);
                writer.print(' ');
                current = current.next;
            }

            writer.print('\n');
        }


        writer.close();
    }
}

class Node{
    Node prev;
    int value;
    Node next;
    boolean in_list = true;
    boolean in_edge = false;

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