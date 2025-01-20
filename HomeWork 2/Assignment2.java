
public class Assignment2 {
		
	
	/*-----------------------
	 *| Part A - tasks 1-11 |
	 * ----------------------*/
	
	// task 1
	public static boolean isSquareMatrix(boolean[][] matrix) {
		
		boolean SquareMatrix=true;
		if (matrix==null || matrix.length==0 ) 
		{
			SquareMatrix=false;
		}
		else 
		{
			//checks the length of each array in the two-dimensional array to ensure that the matrix is square
			for(int i=0; i<matrix.length & SquareMatrix==true ; i=i+1) 
			{
				if(matrix[i].length!=matrix.length) 
				{
					SquareMatrix=false;
				}
			}
		}
		return SquareMatrix;
	}
	
	// task 2
	public static boolean isSymmetricMatrix(boolean[][] matrix) {
		
		boolean SymmetricMatrix=true;
		for(int i=0;i<matrix.length & SymmetricMatrix==true; i=i+1) 
		{
			for(int j=i+1; j<matrix.length & SymmetricMatrix==true;j=j+1) 
			{
				//checks if values in symmetrical places have the same boolean value
				if(matrix[i][j]!=matrix[j][i])
				{
					SymmetricMatrix=false;
				}
			}
		}
		return SymmetricMatrix;
	}

	// task 3
	public static boolean isAntiReflexiveMatrix(boolean[][] matrix) {
		
		boolean AntiReflexiveMatrix=true;
		//checks if the matrix is anti-reflexive
		for(int i=0;i<matrix.length & AntiReflexiveMatrix==true; i=i+1) 
		{
			if(matrix[i][i]==true) 
			{
				AntiReflexiveMatrix=false;
			}
		}
		return AntiReflexiveMatrix;
	}
	
	// task 4
	public static boolean isLegalInstance(boolean[][] matrix) {
		
		boolean LegalInstance=false;
		//checks if all the terms are true, if thats the case, then thats a legal instance.
		if(isSquareMatrix(matrix)) 
		{
			if(isSymmetricMatrix(matrix))
			{
				if(isAntiReflexiveMatrix(matrix)) 
				{
					LegalInstance=true;
				}
			}
		}
		return LegalInstance;
	}
	
	// task 5
	public static boolean isPermutation(int[] array) {

		int[] array2=new int[array.length];
		boolean Permutation=false;
		if(array.length>0) 
		{
			Permutation=true;
			//do a check for each index in the array
			for(int i=0; i< array.length & Permutation == true ;i=i+1) 
			{
				//checks if the value in the current index sets in the correct range and if the value appears only once 
				if(array[i]>=array.length || array2[array[i]]!=0) 
				{
					Permutation=false;
				}
				else 
				{
					array2[array[i]]=array[i];
				}
			}
		}
		return Permutation;
	}
	
	// task 6
	public static boolean hasLegalSteps(boolean[][] flights, int[] tour) {
		
		boolean LegalSteps=false;
		//checks if there is a flight going back from the last town to the starting town.
		if(flights[tour[tour.length-1]][tour[0]]==true) 
		{
			LegalSteps=true;
		}
		//checks if there is flights for the planed tour between all the town in the route. 
		for(int i=0; i<tour.length-1 & LegalSteps==true; i=i+1) 
		{
			if(flights[tour[i]][tour[i+1]]==false)
			{
				LegalSteps=false;
			} 
		}
		return LegalSteps;
		
	}
	
	// task 7
	public static boolean isSolution(boolean[][] flights, int[] tour) {
		if(tour.length!=flights.length) 
		{
			throw new IllegalArgumentException("tour length is not equal to the number of towns");
		}
		boolean Solution=false;
		//checks if the solution meets all the conditions
		if(tour[0]==0 & isPermutation(tour)==true & hasLegalSteps(flights,tour)==true) 
		{
			Solution=true;
		}
		return Solution;
		
	}
	
	// task 8
	public static boolean evaluate(int[][] cnf, boolean[] assign) {
		boolean result = true;
		//calculates the value of the clauses in the chosen CNF to check if the Assignment that we choose is satisfying 
		for(int clauseNum=0; clauseNum<cnf.length & result==true; clauseNum=clauseNum+1) 
		{
			// the function evaluateClause is called from under this function to calculate the clause value
			result = result & evaluateClause(cnf[clauseNum],assign);
		}
		return result ;
	}
	
	//this function returns the value of a Clause that we choose
	public static boolean evaluateClause(int[] clause, boolean[] assign) {
		boolean result =false;
		//calculates the value of the literals in the chosen clause to calculate the clause's value 
		for(int literalIndx=0; literalIndx< clause.length & result==false; literalIndx=literalIndx+1) 
		{
			int literal= clause[literalIndx];
			//the function evaluateLiteral is called from under this function to calculate the literal value
			result= evaluateLiteral(literal,assign);
		}
		return result;
    }
	
	//this function returns the value of the literal that we choose
	public static boolean evaluateLiteral(int literal, boolean[] assign) {
		boolean literalValue;
		//checks if the literal is with a Not or no and calculates his value based on that
		if(literal>0) 
		{
			literalValue=assign[literal];
		}
		else 
		{
			literalValue= !assign[-literal];
		}
		return literalValue;
	}
	
	
	// task 9
	public static int[][] atLeastOne(int[] lits) {
		//CNF with one clause of all the literals with no change to them
		int numOfLits= lits.length;
		int[][]cnf=new int[1][numOfLits];
		cnf[0]=lits;
		return cnf;
	}

	// task 10
	public static int[][] atMostOne(int[] lits) {
		int numOfLits= lits.length;
		int numOfClauses= numOfLits*(numOfLits-1)/2;
		int currIndex=0;
		int[][]cnf=new int[numOfClauses][numOfLits];
		
		for(int i =0; i<lits.length; i=i+1) 
		{
			for(int j=i+1;j<lits.length;j=j+1, currIndex=currIndex+1) 
			{
				//creates clauses and adds a not to every literal so when none or at Max one literal was true the answer will be true;
				int[]clause= {-lits[i],-lits[j]};
				cnf[currIndex]=clause;
			}
		}
		return cnf;
	}
	
	// task 11
	public static int[][] exactlyOne(int[] lits) {
		int numOfLits= lits.length;
		int numOfClauses= 1+numOfLits*(numOfLits-1)/2;
		int currIndex=0;
		int[][]cnf=new int[numOfClauses][numOfLits];
		
		for(int i =0; i<lits.length; i=i+1) 
		{
			for(int j=i+1;j<lits.length;j=j+1, currIndex=currIndex+1) 
			{
				//creates clauses and adds a not to every literal so only when none or at Max one literal was true the answer will be true;
				int[]clause= {-lits[i],-lits[j]};
				cnf[currIndex]=clause;
			}
		}
		//adds the last clause to ensure that there is at least one literal who is true
		cnf[currIndex]=lits;
		return cnf;
		
	}


	// task 12a
	public static int map(int i, int j, int n) {
		//Maps the numbers based on in which part of the travel and city we chose
		// returns a value k that will represent the literal Xk
		int k=i*n+j+1;
		return k;
	}
	
	// task 12b
	public static int[] reverseMap(int k, int n) {
		// checks the given numbers and returns an array that represents which part of the travel and which city these numbers refers to
		int stepi=(k-1)/n;
		int cityj=(k-1)%n;
		int[] pair= {stepi,cityj};
		return pair;
	}
	
	// task 13
	public static int[][] oneCityInEachStep(int n) {
		//calculates the number of clauses that we will need
		int numOfClauses= n*(1+n*(n-1)/2);
		int[][] cnf= new int[numOfClauses][n];
		int[][] theMap= new int[n][n];
		int currIndex=0;
		for(int stepi =0; stepi<n; stepi=stepi+1) 
		{
			//Creates the map of the cities
			for(int cityj=0; cityj<n; cityj=cityj+1) 
			{
				int k=map(stepi,cityj,n);
				theMap[stepi][cityj]=k;
			}
			//Creates a cnf that makes sure that in every step there is only one city
			int[][] newCnf= exactlyOne(theMap[stepi]);
			for( int index=0 ;index<numOfClauses/n; index=index+1,currIndex=currIndex+1)
			{
				cnf[currIndex]= newCnf[index];
			}
		}
		return cnf;
	}

	// task 14
	public static int[][] eachCityIsVisitedOnce(int n) {
		//calculates the number of clauses that we will need
		int numOfClauses= n*(1+n*(n-1)/2);
		int[][] cnf= new int[numOfClauses][n];
		int[][] theSteps= new int[n][n];
		int currIndex=0;
		for(int cityj =0; cityj<n; cityj=cityj+1) 
		{
			//Creates the steps for each city
			for(int stepi=0; stepi<n; stepi=stepi+1) 
			{
				int k=map(stepi,cityj,n);
				theSteps[cityj][stepi]=k;
			}
			//Creates a cnf that makes sure that for each city there is only one step
			int[][] newCnf= exactlyOne(theSteps[cityj]);
			for( int index=0 ;index<numOfClauses/n; index=index+1,currIndex=currIndex+1)
			{
				cnf[currIndex]= newCnf[index];
			}
			
		}
		return cnf;
	}	
	
	
	// task 15
	public static int[][] fixSourceCity(int n) {
		// makes a cnf that makes sure that the starting city is the city number 0
		int[]clause= {map(0,0,n)};
		int[][] cnf= new int[1][1];
		cnf[0]=clause;
		return cnf;
	}
	
	// task 16
	public static int[][] noIllegalSteps(boolean[][] flights) {
		int currIndex=0;
		int clausesCount=0;
		int cities = flights.length;
		int cnfStartLength =cities*cities*cities;
		int[][] cnfStart= new int[cnfStartLength][2];
		//checks every combination of two cities
		for(int cityJ=0;cityJ <cities-1;cityJ=cityJ+1) 
		{
			for(int cityK=1;cityK <cities;cityK=cityK+1) 
			{
				if(cityJ!=cityK) 
				{	
					//checks if there is no flights between the two cities
					if(flights[cityJ][cityK]==false) 
					{
						//checks for every stage
						for(int stageI=0; stageI<cities; stageI=stageI+1) 
						{
							//Creates clauses to make sure that we don't travel between two cities that have no flights between them
							int[]clause= {-map(stageI,cityJ,cities),-map((stageI+1)%cities,cityK,cities)};
							cnfStart[currIndex]= clause;
							currIndex=currIndex+1;
							int[]clause2= {-map(stageI,cityK,cities),-map((stageI+1)%cities,cityJ,cities)};
							cnfStart[currIndex]= clause2;
							currIndex=currIndex+1;
							clausesCount= clausesCount+2;
						}
					}
				}
			}
		}
		//return the final version of the cnf that has no {0,0} in it
		int[][]cnf= new int[clausesCount][2];
		if(clausesCount!=cnfStartLength) 
		{
			for(int Index=0;Index<clausesCount; Index=Index+1) 
			{
				cnf[Index]= cnfStart[Index];
			}
		}
		else 
		{
			cnf = cnfStart;
		}
		return cnf;
	}
	
	// task 17
	public static int[][] encode(boolean[][] flights) {
		//calculates the returned cnf of all the functions
		int[][] cnfCityStep= oneCityInEachStep(flights.length);
		int[][] cnfCityOnce= eachCityIsVisitedOnce(flights.length);
		int[][] cnfFix= fixSourceCity(flights.length);
		int[][] cnfIllegal= noIllegalSteps(flights);
		//sets the length of the wanted final cnf
		int length= cnfCityStep.length +cnfCityOnce.length +cnfFix.length +cnfIllegal.length;
		int[][] cnf=new int[length][];
		//adds the clauses of every cnf of all the functions to our final cnf
		//the function addClausesToCnf is down below
		addClausesToCnf(cnf,cnfFix,0);
		addClausesToCnf(cnf,cnfCityStep,cnfFix.length);
		addClausesToCnf(cnf,cnfCityOnce,cnfFix.length+cnfCityStep.length);
		addClausesToCnf(cnf,cnfIllegal,cnfFix.length+cnfCityStep.length+cnfCityOnce.length);
		return cnf;
	}
	
	//adds the clauses of a function cnf to the given cnf 
	public static void addClausesToCnf(int[][] cnf,int[][] cnfFounction,int cnfindex) 
	{
		for(int indexFounction=0; indexFounction<cnfFounction.length; indexFounction=indexFounction+1, cnfindex=cnfindex+1) 
		{
			cnf[cnfindex]=cnfFounction[indexFounction];
		}
	}
	
	// task 18
	public static int[] decode(boolean[] assignment, int n) {
		//throws exceptions when the assignment is null or its length is not equal to n square +1
		if(assignment==null)
		{
			throw new IllegalArgumentException("assignment is null");
		}
		else if(assignment.length!=n*n+1) 
		{
			throw new IllegalArgumentException("assignment length is not equal to n square +1");
		}
		else
		{
			//decodes the given assignment and returns an array called tour that represent that assignment
			int[] tour=new int[n];
			for(int index=1; index<assignment.length; index=index+1) 
			{
				if(assignment[index]==true) 
				{
					int[]pair=reverseMap(index,n);
					tour[pair[0]]=pair[1];
				}
			}
			return tour;
		}
	}
	
	// task19
	public static int[] solve(boolean[][] flights) {
		//makes sure that the given flight is a legal instance
		if(isLegalInstance(flights)==false) 
		{
			throw new IllegalArgumentException("the given flights doesn't represent a legal instance");
		}
		else 
		{
			//Initiates the SAT Solver
			int cities= flights.length;
			SATSolver.init(cities*cities);
			//returns a cnf that represents the given flights
			int[][] cnf= encode(flights);
			//adds the cnf that we got to the SAT Solver
			SATSolver.addClauses(cnf);
			//return a solution for the given flights
			boolean[]solution= SATSolver.getSolution();
			//throws an exception if the SAT Solver couldn't find a solution in a respectable time;
			if(solution == null)
			{
				throw new IllegalArgumentException("Timeout, couldn't find a solution in the given time");
			}
			//if there is no solution returns null
			else if(solution.length == 0) 
			{
				return null;
			}
			else
			{
				//checks if the solution that we got is correct, and if its not, tells as that its not
				int[] tour=decode(solution,cities);
				if(isSolution(flights,tour)==true) 
				{
					return tour;
				}
				else
				{
					throw new IllegalArgumentException("the solution that we got is illegal");
				}
			}
		}
	}
	
	// task20
	public static boolean solve2(boolean[][] flights) {
		boolean solved =true;
		//makes sure that the given flight is a legal instance
		if(isLegalInstance(flights)==false) 
		{
			throw new IllegalArgumentException("the given flights doesn't represent a legal instance");
		}
		else 
		{
			//Initiates the SAT Solver
			int cities= flights.length;
			SATSolver.init(cities*cities);
			//returns a cnf that represents the given flights
			int[][] cnf= encode(flights);
			//adds the cnf that we got to the SAT Solver
			SATSolver.addClauses(cnf);
			//return a solution for the given flights
			boolean[]solution= SATSolver.getSolution();
			//throws an exception if the SAT Solver couldn't find a solution in a respectable time;
			if(solution == null)
			{
				throw new IllegalArgumentException("Timeout, couldn't find a solution in the given time");
			}
			//if there is no solution returns false
			else if(solution.length == 0) 
			{
				solved = false;
			}
			else
			{
				//checks if the first city in our tour is the city 0
				int[] tour=decode(solution,cities);
				if(tour[0]!=0) 
				{
					solved = false;
				}
				else 
				{
					//takes the first solution and makes clauses
					//these clauses require to have another solution that is not the same or the flip of the first solution
					int[]notWantedSolution=new int[tour.length-1];
					int[]notWantedFlipedSolution=new int[tour.length-1];
					for(int index=1; index<tour.length;index=index+1) 
					{
						notWantedSolution[index-1]= -map(index,tour[index],tour.length);
						notWantedFlipedSolution[index-1]= -map(tour.length-index,tour[index],tour.length);
					}
					//Initiates the SAT Solver
					SATSolver.init(cities*cities);
					//adds the cnf that we got to the SAT Solver
					SATSolver.addClauses(cnf);
					//adds the clauses to the SAT Solver and checks if there is a second solution
					SATSolver.addClause(notWantedSolution);
					SATSolver.addClause(notWantedFlipedSolution);
					boolean[]solution2= SATSolver.getSolution();
					//throws an exception if the SAT Solver couldn't find a second solution in a respectable time;
					if(solution2 == null)
					{
						throw new IllegalArgumentException("Timeout, couldn't find a second solution in the given time");
					}
					//if there is no second solution that meet the requirements
					else if(solution2.length == 0) 
					{
						solved=false;
					}
					else 
					{
						tour=decode(solution2,cities);
						if(tour[0]!=0) 
						{
							solved = false;
						}
					}
				}
			}
		    return solved;
	    }
    }
		
}
