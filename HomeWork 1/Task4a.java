
import java.util.Scanner;

public class Task4a {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        int n=scanner.nextInt();
        boolean isPrime = true;
        
        //checks if the number n is prime
        int p = 2;
        while (p*p <= n & isPrime) 
        {
       		if (n%p == 0) 
       		{
       			isPrime = false;
       		}
       		p=p+1;
       	}
        ans=isPrime;

      
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}