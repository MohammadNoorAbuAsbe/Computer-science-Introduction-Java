
class Part2_Change{

    public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        int ans = 0;
        //Task 2.4
        //checks if we are able to return change and counts the possible ways
        ans = CheckChangeAndCount(coins, n, numOfCoinsToUse, coins.length-1, 0);
        return ans;
    }
    
    public static int CheckChangeAndCount(int[] coins, int money, int numOfCoinsToUse, int index, int counter) 
    {
    	// checks if you were able to return change of the money with a fixed amount of coins ,if thats the case, adds one to the counter
		if(money == 0 & numOfCoinsToUse == 0)
		{
			counter = counter+1;
		}
		//checks if we still didn't run out of options to return change
		else if (!(money < 0 | index < 0 | numOfCoinsToUse < 0))
		{
			//checks possible limited change return ways and counts the successful ones
			counter = CheckChangeAndCount(coins, money - coins[index], numOfCoinsToUse-1, index, counter) +
					CheckChangeAndCount(coins, money, numOfCoinsToUse, index - 1, counter);
		}
		return counter;
    }

    public static void printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        //Task 2.5
    	int[] usedCoins = new int[numOfCoinsToUse];
    	//checks if we can return change with a fixed amount of coins and prints all the possible ways to return the change
    	CheckAndPrintLimited(coins, n, numOfCoinsToUse, coins.length-1, usedCoins);
    }
    
    public static void CheckAndPrintLimited(int[] coins, int money, int numOfCoinsToUse, int index, int[] usedCoins) 
    {
    	// checks if you were able to return change of the money with a fixed amount of coins
		if(money == 0 & numOfCoinsToUse == 0)
		{
			//prints the exact coins that we have to return
			printTheCoins(usedCoins,0);
		}
		//checks if we still didn't run out of options to return change
		else if (!(money < 0 | index < 0 | numOfCoinsToUse < 0))
		{
			//saves the coins that we have to return 
			if(numOfCoinsToUse!=0)
			usedCoins[numOfCoinsToUse-1]=coins[index];
			//tries two ways to return change, the first: return a coin an stay on the same type
			//the second: don't return a coin and move to the next coins type
			CheckAndPrintLimited(coins, money - coins[index], numOfCoinsToUse-1, index, usedCoins);
			CheckAndPrintLimited(coins, money, numOfCoinsToUse, index - 1, usedCoins);
		}
    }
    
    public static void printTheCoins(int[] usedCoins, int usedIndex) 
    {
    	//prints all the coins that we have to return in order without the last coin
    	if(usedIndex < usedCoins.length-1) 
    	{
    	System.out.print(usedCoins[usedIndex] + ",");
    	printTheCoins(usedCoins, usedIndex+1);
    	}
    	//prints the last coin
    	else 
    	{
    		System.out.print(usedCoins[usedIndex]);
    		System.out.println();
    	}
    }

    public static int changeInCuba(int cuc){
        int ans = 0;
        //Task 2.6
        //3CUP = 1CUC , CUP{3,9,15,30,60,150,300} = CUC{1,3,5,10,20,50,100} , totalCUP = CUP + CUC 
        int[] totalCUP = {1,3,5,10,20,50,100,3,9,15,30,60,150,300};
        //converts the money to CUP
        int CucToCup = cuc*3;
        //counts all the possible ways to return change
        ans = allChange(totalCUP, CucToCup, totalCUP.length-1,0);
        return ans;
    }
    
    public static int allChange(int[] coins, int money, int index, int counter) 
    {
    	// checks if you were able to return change of the money and adds one to the counter
    	if(money == 0)
    	{
    		counter = counter+1;
    	}
    	//checks if we still didn't run out of options to return change
    	else if (!(money < 0 | index < 0 ))
    	{
    		//checks possible change return ways and counts the successful ones
    		counter = allChange(coins, money - coins[index], index, counter) +
    				allChange(coins, money, index - 1, counter);
    	}
    	return counter;
    }
    
    public static void main(String[] args){

    }
}