
import java.util.Scanner;

public class Task4c {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        double b= Math.random();
        //take the random number that sets between 0 and 1 and converts it to a number that sets in the chosen group of numbers
        ans= (int)(b*(n-2))+2;
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}