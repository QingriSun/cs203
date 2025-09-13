public class testC{
    public static void main(String[] argv){
        // // test if I can use an element to find the index in string
        //     // Yes you can
        // String str = "abc";
        // System.out.println(str.indexOf('c'));

        // // test what is (int) char
        //     // output the ascii value
        // System.out.println((int) 'a');

        // char[] empty = new char[3];
        // empty[2] = 'a';
        // for (int i = 0; i < 3; i++){
        //     System.out.printf("%c", empty[i]);
        // }

        int[] arr = new int[3];
        arr[0]++;
        for (int i = 0; i < 3; i++){
            System.out.println(arr[i]);
        }
    }
}