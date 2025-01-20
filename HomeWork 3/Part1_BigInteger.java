
import java.math.BigInteger;
import java.util.Random;

class Part1_BigInteger{

    public static BigInteger sumSmaller(BigInteger n){
        BigInteger sum =  new BigInteger("0");
        //Task 1.1
        //checks if the BigInteger n value is bigger than zero
        while(n.compareTo(BigInteger.ZERO)==1) 
        {
        	//adds the next number which is smaller than n by one to the sum
        	sum=sum.add(n.subtract(BigInteger.ONE));
        	n=n.subtract(BigInteger.ONE);
        }
    	return sum;
    }

    public static void printRandoms(int n){
        //Task 1.2
    	//Creates a new random number generator
    	Random rnd =new Random();
    	if(n!=0)
    	{
    		//Generates a pseudorandom integer number and prints it
    		System.out.println(rnd.nextInt());
    		printRandoms(n-1);
    	}
    }

    public static boolean isPrime(BigInteger n){
        boolean ans = true;
        //Task 1.3
        //checks if the BigInteger is bigger or equal to 2
        if(n.compareTo(BigInteger.TWO)>=0) 
        {
        	boolean isPrime = true;
        	BigInteger divisor =  BigInteger.TWO;
        	//checks if the BigInteger Is a Prime number
			while (divisor.pow(2).compareTo(n)<1 & isPrime) 
			{
				//checks if there is any divider for the BigInteger
				if (n.mod(divisor).compareTo(BigInteger.ZERO)==0) 
				{
					isPrime = false; 
				} 
				//moves to the next number to check if it divides the BigInteger
				divisor = divisor.add(BigInteger.ONE);
			}
			ans = isPrime;
        }
        else ans = false;
        return ans;
    }

    public static BigInteger randomPrime(int n){
        BigInteger randBig = new BigInteger("0");
        //Task 1.4
    	//Creates a new random number generator
    	Random rnd =new Random();
    	boolean Prime = false;
    	//if the number is not prime
    	while(Prime==false) 
    	{
    		//generates a new BigInteger number
    		randBig = new BigInteger(n,rnd);
    		//checks if its prime
    		Prime = isPrime(randBig);
    	}
        return randBig;
    }
    

    public static void main(String[] args) {
    	
    }
}