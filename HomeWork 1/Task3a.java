
import java.util.Scanner;

public class Task3a {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        
        //if the power n=0 it wont enter the for loop and the answer will be equal to 1
        ans=1;
        
        //calculates 2 to the power of n
        for(int i=1 ; i<=n ; i=i+1)
        {
        ans= ans*2;
        }
        //---------------write your code ABOVE this line only!--------------
        
        System.out.println(ans);
    }
}