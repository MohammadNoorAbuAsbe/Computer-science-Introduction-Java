
import java.util.Scanner;

public class Task4d {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans1 = 0;
        int ans2 = 0;

        //---------------write your code BELOW this line only!--------------
        int n=scanner.nextInt();
        int s=0;
        int d=n-1;
        
        //calculates the numbers that will represent the power to the number 2 and the odd number
        //that when multiplied by each another will represent the (given number-1)
        while((d)%2==0) 
        {
        	d=d/2;
        	s=s+1;
        }
        ans1=s;
        ans2=d;
        
        //---------------write your code ABOVE this line only!--------------
        
        System.out.println(ans1);
        System.out.println(ans2);
    }
}