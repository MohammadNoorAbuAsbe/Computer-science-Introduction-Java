
import java.util.Scanner;

public class Task3b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        
        //divide the total power to 30's because 2 to the power of 31 is bigger than the max integer possible 
        int numOfThirties = n/30; 
        int rest = n%30;

        int returnOfThir=1;
        int restPowOfTwo=1;
        
        //calculates 2 to the power of 30
        int maxPow =((Integer.MAX_VALUE-1)/2)+1;
        
        //calculates the return of 2 to the power of 30's when divided by k
        while(numOfThirties!=0) 
        {
        	returnOfThir=((returnOfThir%k)*(maxPow%k))%k;
        	numOfThirties=numOfThirties-1;
        }
        
        //calculates 2 to the power of what left after dividing the total power to 30's
        while(rest!=0) 
        {
        	restPowOfTwo=restPowOfTwo*2;
    		rest=rest-1;
        }
        
        //the answer to n%k
        ans=(returnOfThir*(restPowOfTwo%k))%k;
 
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}