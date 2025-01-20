
class Part1_Change{

    public static boolean change(int [] coins, int n){
        boolean ans = false;
        //Task 2.1
        //checks if the money is changeable
        ans=isChangeable(coins, n, coins.length-1);
        return ans;
    }
    
    public static boolean isChangeable(int[] coins, int money, int index) 
    {
    	boolean isChangeable = false;
    	// checks if you were able to return change of the money
		if(money == 0)
			isChangeable = true;
		//checks if we weren't able to return change of the money
		else if(money < 0 | index < 0)
			isChangeable = false;
		else 
			//return the current biggest possible coin and don't change type
			//OR: don't return a coin and move to the next type
			isChangeable = isChangeable(coins, money - coins[index], index) || isChangeable(coins, money, index - 1);
    	return isChangeable;
    }

    public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse){
        boolean ans = false;
        //Task 2.2
        //checks if the money is changeable with a fixed number of coins
        ans = LimitedChangable(coins, n, numOfCoinsToUse, coins.length-1);
        return ans;
    }

    public static boolean LimitedChangable(int[] coins, int money, int numOfCoinsToUse, int index) 
    {
    	boolean limitedChangeable = false;
    	// checks if you were able to return change of the money with a fixed amount of coins
		if(money == 0 & numOfCoinsToUse == 0)
			limitedChangeable = true;
		//checks if we weren't able to return change of the money or we reached the limit of coins
		else if(money < 0 | index < 0 | numOfCoinsToUse < 0)
			limitedChangeable = false;
		else 
			//return the current biggest possible coin and don't change type, reducing the amount of coins that is left for us to use
			//OR: don't return a coin and move to the next type
			limitedChangeable = LimitedChangable(coins, money - coins[index], numOfCoinsToUse-1, index) || LimitedChangable(coins, money, numOfCoinsToUse, index - 1);
    	return limitedChangeable;
    }
    
    public static void printChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        //Task 2.3
    	int[] usedCoins = new int[numOfCoinsToUse];
    	//checks if we can return change with a fixed amount of coins and prints the exact coins that we have to return
    	CheckChangeAndPrint(coins, n, numOfCoinsToUse, coins.length-1, usedCoins);
    }

    public static boolean CheckChangeAndPrint(int[] coins, int money, int numOfCoinsToUse, int index, int[] usedCoins) 
    {
    	boolean CheckChange = false;
    	// checks if you were able to return change of the money with a fixed amount of coins
		if(money == 0 & numOfCoinsToUse == 0)
		{
			CheckChange = true;
			//prints the exact coins that we have to return
			printTheCoins(usedCoins,0);
		}
		//checks if we weren't able to return change of the money or we reached the limit of coins
		else if(money < 0 | index < 0 | numOfCoinsToUse < 0)
			CheckChange = false;
		else 
		{
			//saves the coins that we have to return 
			if(numOfCoinsToUse!=0)
			usedCoins[numOfCoinsToUse-1]=coins[index];
			//return the current biggest possible coin and don't change type, reducing the amount of coins that is left for us to use
			//OR: don't return a coin and move to the next type
			CheckChange = CheckChangeAndPrint(coins, money - coins[index], numOfCoinsToUse-1, index, usedCoins) 
						|| CheckChangeAndPrint(coins, money, numOfCoinsToUse, index - 1, usedCoins);
		}
		return CheckChange;
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
    	}
    }
    public static void main(String[] args){
    	
    	
    }
}
