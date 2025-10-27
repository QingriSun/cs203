import java.util.Scanner;

public class FTest{
    public static void main(String[] argv){
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()){
            scanner.next();
            System.out.println("eat");
        }else{
            System.out.println("good");
        
        }
        
    }
}