import java.util.Scanner;

public class Task4f {

    public static void main(String[] args) {
        
		Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        int n=scanner.nextInt();
        int s=scanner.nextInt();
        int d=scanner.nextInt();
        int k=scanner.nextInt();
		int bPowDModN=1;
        int powD=d;
        int powI=0;
        int i=0;
        boolean part1=true;
    	boolean part2=true;
    	
    	
    	for(int j=0;j<k;j=j+1) 
    	{
    		double ran= Math.random();
        	//take the random number that sets between 0 and 1 and converts it to a number that sets in the chosen group of numbers
        	int b= (int)(ran*(n-2))+2;
    	
    		//checks if b meets the first part of the condition
			while(powD>0) 
			{
				bPowDModN=((bPowDModN%n)*(b%n))%n;
				powD=powD-1;
			}
        	if(bPowDModN==1) 
        	{
        		part1=false;
        	}
        
        	while((i<=s-1)&&(part1)&&(part2)) 
        	{
        		powI=i;
        		//calculates 2 to the power of i
        		int twoPowI=1;
        		while(powI>0) 
        		{
        			twoPowI=twoPowI*2;
        			powI=powI-1;
        		}
        
        		//checks if b meets the second part of the condition
        		//we already have b to the power of d, now we just multiply it by (2 to the power of i) times
        		int bPowDTwoPowIModN=bPowDModN;
        		while(twoPowI>1)
        		{
        			bPowDTwoPowIModN=((bPowDTwoPowIModN%n)*(bPowDModN%n))%n;
        			twoPowI=twoPowI-1;
        		}
        		if(bPowDTwoPowIModN==(n-1)) 
        		{
        			part2=false;
        		}
        		i=i+1;
        	}
            
        	//if b meets the condition then we got a result and the program stops
        	if(part1&&part2) 
        	{
        		ans=false;
        		j=k;
        	}
        	else
        	{
        		ans=true;
        	}
    	}
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}