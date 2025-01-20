
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int q = scanner.nextInt();
        int r = scanner.nextInt();

        
 		//checks if any of the conditions isn't true and stops immediately to give an answer

        if(b!=0 && b>r && a==q*b+r) 
        {
        	ans = true;
        }
        else ans = false;

        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}