
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        double ran= Math.random();
        //take the random number that sets between 0 and 1 and converts it to a number that sets in the chosen group of numbers
        ans= (int)(ran*(b-a+1))+a;
        //---------------write your code ABOVE this line only!--------------
        
        System.out.println(ans);
        
    }
}