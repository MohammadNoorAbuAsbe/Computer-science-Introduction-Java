import java.util.Scanner;

public class Task4e {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans1 = true;
        int ans2 = 0;

        //---------------write your code BELOW this line only!--------------
        int n=scanner.nextInt();
        int b=scanner.nextInt();
        int s=scanner.nextInt();
        int d=scanner.nextInt();
		int bPowDModN=1;
        int powD=d;
        int powI=0;
        int i=0;
        boolean part1=true;
    	boolean part2=true;
    	
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
        	//calculates 2 to the power of i
        	powI=i;
        	int twoPowI=1;
        	while(powI>0) 
        	{
        		twoPowI=twoPowI*2;
        		powI=powI-1;
        	}
        
        	//checks if b meets the second part of the condition
        	//we already have b to the power of d, now we just multiply it (2 to the power of i) times
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
        
        if(part1&&part2) 
        {
        	ans1=false;
        	ans2=b;
        }
        else
        {
        	ans1=true;
        	ans2=-1;
        }
        //---------------write your code ABOVE this line only!--------------
        System.out.println(ans1);
        System.out.println(ans2);
    }
}