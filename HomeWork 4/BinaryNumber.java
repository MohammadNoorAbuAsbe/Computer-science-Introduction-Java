
import java.util.Iterator;

public class BinaryNumber implements Comparable<BinaryNumber>{
    private static final BinaryNumber ZERO = new BinaryNumber(0);
    private static final BinaryNumber ONE = new BinaryNumber(1);

    private BitList bits;

    // Copy constructor
    //Do not change this constructor
    public BinaryNumber(BinaryNumber number) {
        bits = new BitList(number.bits);
    }

    //Do not change this constructor
    private BinaryNumber(int i) {
        bits = new BitList();
        bits.addFirst(Bit.ZERO);
        if (i == 1)
            bits.addFirst(Bit.ONE);
        else if (i != 0)
            throw new IllegalArgumentException("This Constructor may only get either zero or one.");
    }

    //Do not change this method
    public int length() {
        return bits.size();
    }

    //Do not change this method
    public boolean isLegal() {
        return bits.isNumber() & bits.isReduced();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.1 ================================================
    
    //returns a binary number representing the given character (character must be from 0-9)
    public BinaryNumber(char c) {
    	//check if the given character represents a number between 0 to 9
        if (c >= '0' & c <= '9') {
        	bits = new BitList();
        	int charToDecimal = Character.getNumericValue(c);
        	//convert the given number to a binary number 
        	while (charToDecimal != 0) {
        		Bit nextBit = Bit.ZERO;
        		if (charToDecimal % 2 == 1) {
					nextBit = Bit.ONE;
				}
        		bits.addLast(nextBit);
        		charToDecimal = charToDecimal / 2;
        	}
    		//add a zero in the end to represent the number a a positive number
           	bits.addLast(Bit.ZERO);
		}
        else {
			throw new IllegalArgumentException("the given character doesn't represent a number between 0 to 9");
		}
    }

  //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.2 ================================================
    
    //return the binary number as a string
    public String toString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //get the bits as a string, and remove the two angle brackets <>
        String numberAsString = bits.toString();
        numberAsString = numberAsString.substring(1, numberAsString.length()-1);
        return numberAsString;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.3 ================================================
    
    //checks if the given object is equal to our binary number
    public boolean equals(Object other) {
    	boolean isEqual = false;
    	//check if the given object is a binary number first, then check if he represents the same number
        if (other instanceof BinaryNumber) {
			isEqual = bits.equals(((BinaryNumber) other).bits);
		}
        return isEqual;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.4 ================================================
    
    //returns a binary number representing the sum after adding a given number to the current number
    public BinaryNumber add(BinaryNumber addMe) {
    	if (addMe == null || (!this.bits.isNumber() | !addMe.bits.isNumber())) {
			throw new IllegalArgumentException("one of the given argument sides is not a number, check them carefully");
		}
    	BinaryNumber original = new BinaryNumber(this);
    	//takes the numbers, reduce them and pad them if needed (makes them the same length)
    	reduceAndPad(original, addMe);
    	//adds the two given binary numbers and returns the sum;
    	BinaryNumber sum = adding(original,addMe);
    	addMe.bits.reduce(); 
        return sum;
    }
    
    //private methods
    
    //takes the numbers, reduce them and pad them if needed (makes them the same length)
    private void reduceAndPad(BinaryNumber original, BinaryNumber addMe) {
    	//first, reduce the numbers to make them minimal
		original.bits.reduce();
		addMe.bits.reduce();
		//make sure they are the same length
        if (original.length() > addMe.length()) {
        	addMe.bits.padding(original.length());
		}
        else {
			original.bits.padding(addMe.length());
		}
	}
    
    //adds the two given binary numbers and returns the sum;
    private static BinaryNumber adding(BinaryNumber original,BinaryNumber addMe){
        BinaryNumber sum = new BinaryNumber(0);
        //in case of adding the number zero, nothing changes
        if (sum.equals(original)) {
			sum = addMe;
		}else if (sum.equals(addMe)) {
			sum = original;
		}
        //adds the two numbers
		else {
			sum.bits.remove();
        	Bit carryBit = Bit.ZERO;
        	Iterator<Bit> originalIterator = original.bits.iterator();
        	Iterator<Bit> addMeIterator = addMe.bits.iterator();
        	//use fullAdderSum and fullAdderCarry to get the sum of the numbers
        	while (addMeIterator.hasNext()!= false) {
        		Bit currentOriginalBit = originalIterator.next();
        		Bit currentAddMeBit = addMeIterator.next();
        		//returns the sum of two bits / three bits without the carry
        		sum.bits.addLast(Bit.fullAdderSum(currentOriginalBit, currentAddMeBit, carryBit));
        		//returns the carry of the sum
        		carryBit = Bit.fullAdderCarry(currentOriginalBit, currentAddMeBit, carryBit);
			}
        	//add zero or one when needed to the end of the number to indicate that the sum is positive or negative
        	if (original.bits.getLast() == addMe.bits.getLast()) {
				sum.bits.addLast(original.bits.getLast());
			}
        	//reduce the number to its minimal form
        	sum.bits.reduce();
		}
        return sum;
    }
    
    //end of private methods

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.5 ================================================
    // returns the negative of the given number
    public BinaryNumber negate() {
        BinaryNumber negate = new BinaryNumber(0);
        //checks if the given number is not zero
        if (!this.equals(ZERO)) {
        	//flips the number's '1's and '0's
        	negate.bits = this.bits.complement(); 
        	BinaryNumber numOne = new BinaryNumber(1);
        	//pads if needed
        	if (numOne.length() < negate.length() ) {
            	numOne.bits.padding(negate.length());
			}else {
				negate.bits.padding(numOne.length());
			}
        	//adds one to the first bit
        	negate = adding(negate, numOne);
        }
        return negate;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.6 ================================================
    //subtract the given number from the current number
    public BinaryNumber subtract(BinaryNumber subtractMe) {
    	if (subtractMe == null) {
    		throw new IllegalArgumentException("the given number to subtract is null");
		}
    	//gets the negative of the given number
    	BinaryNumber theNegative = subtractMe.negate();
    	//adds the negative
    	return this.add(theNegative);
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.7 ================================================
    //checks if the number bigger, equal or smaller than zero
    public int signum() {
    	int signOfTheNum = 0;
    	//check if the given number is a legal number
    	if(!bits.isNumber()) {
    		throw new IllegalArgumentException("Can't do a signum check, the given variable is not a number");
    	}
    	//checks based on the last bit and the length to determine if bigger,equal or smaller  
    	if (this.length() > 1) {
    		if(bits.getLast().toInt() == 0) {
    			signOfTheNum = 1;
    		}
    		else {
				signOfTheNum = -1;
			}
		}
    	return signOfTheNum;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.8 ================================================
    public int compareTo(BinaryNumber other) {
    	if ( other == null||(!this.isLegal() || !other.isLegal())) {
			throw new IllegalArgumentException("one of the numbers is not legal or null, can't compare them");
		}
    	int compared = 0;
    	Bit ourRemovedBit = null;
    	Bit otherRemovedBit = null;
    	BinaryNumber ourBinaryNumber = new BinaryNumber(this);
    	BinaryNumber otherBinaryNumber = new BinaryNumber(other);
    	int ourSignum = this.signum();
    	int otherSignum = other.signum();
    	//check if one of them have more bits then the other
		if (this.length() > other.length()) {
			compared = 1 * ourSignum;
		}
		else if (other.length() > this.length()) {
			compared = -1 * otherSignum;
		}
		//if they are the same length, check if they are both positive or negative
		else if (ourSignum == otherSignum) {
			//check if they are not equal
			if (! this.equals(other)) {
				//if they are positive
				if (ourSignum == 1) {
					//keep removing the last bit of both the temporary numbers until finding a deference in bits, determines who is bigger (method down below)
					compared = removeTheLastBitsAndCheck(compared, ourRemovedBit, otherRemovedBit, ourBinaryNumber, otherBinaryNumber);
				}else {
					//get the negative of the negatives, which is positive
					ourBinaryNumber = ourBinaryNumber.negate();
					otherBinaryNumber = otherBinaryNumber.negate();
					//keep removing the last bit of both the temporary numbers until finding a deference in bits, determines who is bigger (method down below)
					compared = removeTheLastBitsAndCheck(compared, ourRemovedBit, otherRemovedBit, ourBinaryNumber, otherBinaryNumber);
					//returns the comparison to the negative form
					compared = compared * -1;
				}
			}
		}
		//checks which number is positive and which one is negative, to determine who is bigger
		else if (ourSignum > otherSignum) {
			compared = 1;
		}
		else if (otherSignum > ourSignum) {
			compared = -1;
		}
		return compared;
    }
    
    //private methods
    
    //keep removing the last bit of both the numbers until finding a deference in bits, determines who is bigger  
    private static int removeTheLastBitsAndCheck(int compared, Bit ourRemovedBit, Bit otherRemovedBit, BinaryNumber ourBinaryNumber, BinaryNumber otherBinaryNumber) {
		while (compared == 0) {
			//remove the last bit from both the numbers
			ourRemovedBit = ourBinaryNumber.bits.removeLast();
			otherRemovedBit = otherBinaryNumber.bits.removeLast();
			//check which bit is bigger
			if (ourRemovedBit.toInt() > otherRemovedBit.toInt()) {
				compared = 1;
			}else if (otherRemovedBit.toInt() > ourRemovedBit.toInt()) {
				compared = -1;
			}
		}
		return compared;
	}
    
    //end of private methods
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.9 ================================================
    public int toInt() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        BinaryNumber checkBinaryNumber = new BinaryNumber(this);
        if (signum() == -1) {
			checkBinaryNumber = checkBinaryNumber.negate();
			checkBinaryNumber.bits.reduce();
		}
        //checks if the number sets in the integer range
        if ( (length() > 32 & signum()== 1)|(signum() == -1 & (checkBinaryNumber.length()> 33 | checkBinaryNumber.length() == 33 & checkBinaryNumber.bits.getNumberOfOnes()>1))) {
        	throw new RuntimeException("the number can't be shown as an integer");
		}
        int sum = 0;
        int bitValue = 1;
        //iterates on the bits, adding each ones value to the sum
        Iterator<Bit> iterator = this.bits.iterator();
        while (iterator.hasNext()) {
        	sum = sum + iterator.next().toInt() * bitValue ;
			bitValue = bitValue *2;
		}
        //if the number is negative, subtract the value of the last bit times two
        if (signum() == -1) {
			sum = sum - bitValue;
		}
        return sum;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.10 ================================================
    // Do not change this method
    public BinaryNumber multiply(BinaryNumber multiplyMe) {
    	//multiplies the numbers
    	if (multiplyMe == null || !this.isLegal() || !multiplyMe.isLegal()) {
			throw new IllegalArgumentException("one of the numbers is not legal, can't multiply them");
		}
    	BinaryNumber multiplyNumber = new BinaryNumber(multiplyMe);
    	boolean isNegative = false;
    	if (multiplyMe.signum() == -1) {
			multiplyNumber = multiplyNumber.negate();
			isNegative = true;
		}
    	BinaryNumber answer = multiplyPositive(multiplyNumber);
    	if (isNegative) {
			answer = answer.negate();
		}
    	return answer;
    }

    //multiplies the numbers
    private BinaryNumber multiplyPositive(BinaryNumber multiplyMe) {
    	BinaryNumber originalNumber = new BinaryNumber(this);
    	BinaryNumber multiplyNumber = new BinaryNumber(multiplyMe);
    	boolean isNegative = false;
    	if (originalNumber.signum() == -1) {
			isNegative = true;
			originalNumber = originalNumber.negate(); 
		}
    	BinaryNumber multiplyanswer = numbersMultipling(multiplyNumber, originalNumber);
    	if (isNegative) {
			multiplyanswer = multiplyanswer.negate();
		}
    	//takes the numbers , adds the number multiple times (shifting method), shifting the number each time (method down below)
    	return multiplyanswer;
    }
    
    //private method
    
    //takes the numbers , adds the number multiple times (shifting method), shifting the number each time
    private static BinaryNumber numbersMultipling(BinaryNumber multiplyNumber, BinaryNumber originalNumber) {
    	BinaryNumber multiplied = new BinaryNumber(ZERO);
    	//checks if the we still didn't multiply to the end
		if (!multiplyNumber.bits.isEmpty()) {
			//checks if the first bit is '1' while removing it, adds the number to the current binary number ...
			//... shifts the number afterwards 
			if (multiplyNumber.bits.removeFirst() == Bit.ONE) {
				multiplied = multiplied.add(originalNumber);
				originalNumber.bits.shiftLeft();
				//re-add the number for the next bit
				multiplied= multiplied.add(numbersMultipling(multiplyNumber, originalNumber));
			}
			//the bit was '0' , just shift the number and move to the next bit
			else {
				originalNumber.bits.shiftLeft();
				multiplied= multiplied.add(numbersMultipling(multiplyNumber, originalNumber));
			}
		}
		return multiplied;
	}
    
    //end of private method

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.11 ================================================
    // Do not change this method
    public BinaryNumber divide(BinaryNumber divisor) {
    	//checks if the two numbers are legal
    	if (divisor == null || !this.isLegal() || !divisor.isLegal()) {
			throw new IllegalArgumentException("one of the numbers is not legal, can't divide them");
    	}
    	// Do not remove or change the next two lines
    	if (divisor.equals(ZERO)) // Do not change this line
            throw new RuntimeException("Cannot divide by zero."); // Do not change this line
    	
    	BinaryNumber divisionNumber = new BinaryNumber(divisor);
    	BinaryNumber divisionResult = new BinaryNumber(ZERO);
    	boolean wasNegative = false;
    	//shortcuts the division if the divisor is equal to 2
    	if (divisor.equals(new BinaryNumber('2'))) {
    		divisionResult = new BinaryNumber(this).divBy2();
		}
    	else {
        	//checks if the divisor is negative, if so make him positive and remember that
        	if (divisor.signum() == -1) {
        		divisionNumber = divisionNumber.negate();
        		wasNegative = true;
        	}
        	//divide with a positive number
    		divisionResult = dividePositive(divisionNumber);
    		//if the divisor was negative at first, flip the answer from positive to negative or vice versa 
        	if (wasNegative) {
        		divisionResult = divisionResult.negate();
    		}
        	//reduce the answer
        	divisionResult.bits.reduce();
		}
    	return divisionResult;
    }

    //divide with a positive number 
    private BinaryNumber dividePositive(BinaryNumber divisor) {
    	BinaryNumber originalNumber = new BinaryNumber(this);
    	boolean wasMinus = false;
    	//checks if the main number is negative, if so make him positive and remember that
    	if (originalNumber.signum() == -1) {
			originalNumber = originalNumber.negate();
			wasMinus = true;
		}
    	BinaryNumber divisionNumber = new BinaryNumber(divisor);
    	BinaryNumber tempDivision  = new BinaryNumber(ZERO);
    	tempDivision.bits.removeFirst();
    	//divide the positive numbers, using Long Division method (method is below)
    	BinaryNumber divisionResult = longDivision(originalNumber, divisionNumber, tempDivision);
    	//if the main number was negative at first, flip the answer from positive to negative or vice versa
    	if (wasMinus) {
			divisionResult = divisionResult.negate();
		}
    	return divisionResult;
    }
    
    //private methods
    
    //divide the numbers using the long Division method
    private static BinaryNumber longDivision(BinaryNumber originalNumber, BinaryNumber divisionNumber, BinaryNumber tempDivision) {
    	BinaryNumber divideResult  = new BinaryNumber(ZERO);
    	divideResult.bits.removeFirst();
    	//checks if there is more bits to divide
    	if (originalNumber.length()>0) {
    		//adds the first bit of the remaining bits to the temporary number to be divided
    		tempDivision.bits.addFirst(originalNumber.bits.removeLast());
    		//checks if we can divide, using UNSAFE METHOD USED ONLY FOR THIS DIVISION (method down below)
    		//follows the long division method rules
   			if (tempDivision.comparingWithNoRestriction(divisionNumber) > -1) {
   				tempDivision = tempDivision.subtract(divisionNumber);
   				divideResult = new BinaryNumber(longDivision(originalNumber, divisionNumber, tempDivision));
   				divideResult.bits.addLast(Bit.ONE);
   			}
   			else {
    			divideResult = new BinaryNumber(longDivision(originalNumber, divisionNumber, tempDivision));
    			divideResult.bits.addLast(Bit.ZERO);
    		}
		}
		return divideResult;
	}
     
     //exactly the same as the public compareTo method, but without checking if the numbers are legal (NOT SAFE, USE CAREFULLY)
     private int comparingWithNoRestriction(BinaryNumber other) {
    	int compared = 0;
     	Bit ourRemovedBit = null;
     	Bit otherRemovedBit = null;
     	BinaryNumber ourBinaryNumber = new BinaryNumber(this);
     	BinaryNumber otherBinaryNumber = new BinaryNumber(other);
     	int ourSignum = this.signum();
     	int otherSignum = other.signum();
     	//check if one of them have more bits then the other
 		if (this.length() > other.length()) {
 			compared = 1 * ourSignum;
 		}
 		else if (other.length() > this.length()) {
 			compared = -1 * otherSignum;
 		}
 		//if they are the same length, check if they are both positive or negative
 		else if (ourSignum == otherSignum) {
 			//check if they are not equal
 			if (! this.equals(other)) {
 				//if they are positive
 				if (ourSignum == 1) {
 					//keep removing the last bit of both the temporary numbers until finding a deference in bits, determines who is bigger (method is in top)
 					compared = removeTheLastBitsAndCheck(compared, ourRemovedBit, otherRemovedBit, ourBinaryNumber, otherBinaryNumber);
 				}else {
 					//get the negative of the negatives, which is positive
 					ourBinaryNumber = ourBinaryNumber.negate();
 					otherBinaryNumber = otherBinaryNumber.negate();
 					//keep removing the last bit of both the temporary numbers until finding a deference in bits, determines who is bigger (method is in top)
 					compared = removeTheLastBitsAndCheck(compared, ourRemovedBit, otherRemovedBit, ourBinaryNumber, otherBinaryNumber);
 					//returns the comparison to the negative form
 					compared = compared * -1;
 				}
 			}
 		}
 		//checks which number is positive and which one is negative, to determine who is bigger
 		else if (ourSignum > otherSignum) {
 			compared = 1;
 		}
 		else if (otherSignum > ourSignum) {
 			compared = -1;
 		}
 		return compared;
	}
     
     //end of private methods

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.12 ================================================
    public BinaryNumber(String s) {
    	//checks if the input is valid
    	if( s == null|| (s.charAt(0)!='-' & !(s.charAt(0)>= '0' & s.charAt(0) <= '9')) | (s.charAt(0) == '-' & s.length() == 1))
    	{
    		throw new IllegalArgumentException("the given string doesn't represent a number");
    	}
    	for (int i = 1; i < s.length(); i++) {
    		if (!(s.charAt(i)>= '0' & s.charAt(i) <= '9')) {
				throw new IllegalArgumentException("the given string doesn't represent a number");
			}
    	}
    	BinaryNumber converted = new BinaryNumber('0');
    	BinaryNumber currentplacevlaue = new BinaryNumber('1');
    	boolean wasNegative = false;
    	//checks if the input is a negative number
    	if (s.charAt(0) == '-') {
    		s = s.substring(1,s.length());
    		wasNegative = true;
		}
    	//converts the number
    	converted = convert(s, currentplacevlaue);
    	//return the number to its negative form
    	if (wasNegative) {
			converted = converted.negate();
		}
    	//the new bits are the bits we got from the conversion
    	bits = new BitList(converted.bits);
    }
    
    //private method
    
    // converts any string representing a number to a binary number
    private static BinaryNumber convert(String s, BinaryNumber currentplacevlaue) {
    	//binary number representing the number 10 for multiplication
    	BinaryNumber numberTen = new BinaryNumber('9').add(ONE);
    	BinaryNumber finalBinaryNumber = new BinaryNumber(ZERO);
    	//takes each char of the string , gives it the correct value and adds it the the final binary
    	for (int i = s.length()-1 ; i >= 0; i--) {
    		BinaryNumber charInString = new BinaryNumber(s.charAt(i));
    		finalBinaryNumber = finalBinaryNumber.add(charInString.multiply(currentplacevlaue));
    		currentplacevlaue = currentplacevlaue.multiply(numberTen);
		}
    	return finalBinaryNumber;
	}
    
    //end of private method

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.13 ================================================
    public String toIntString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
    	String ans ="";
    	BinaryNumber test = new BinaryNumber(this);
    	boolean isNegative = false;
    	//checks if the number is negative, if yes, make him positive
    	if (test.signum() == -1) {
			isNegative = true;
			test = test.negate();
		}
    	//get a string representing the decimal value of the given bits
    	ans = DicemalString(test);
    	if (isNegative) {
    		ans = "-" + ans; 
		}
    	return ans;
    }
    
    //private methods
    
    private String DicemalString(BinaryNumber test) {
    	String sum = "0";
    	boolean start_of_number_flag = false;
    	int multipliedValue = 0;
    	int initialLength = 0;
    	while(test.length()>0)
    	{
    		// we skip the zeros on the most significant bits.
    		if(!start_of_number_flag) 
    		{
    			if(test.bits.removeLast().toInt() == 1) 
    			{
    				start_of_number_flag=true;
    				sum = "1";
    			}
    		}
    		else
    		{
    			// it's not a big integer, calculate normally 
    			if(sum.length() < 10) 
    			{
    				int ValueOfSum = Integer.valueOf(sum) * 2 + test.bits.removeLast().toInt();
    				sum = String.valueOf(ValueOfSum);
    			}
    			else 
    			{
    				int startingLengthOfSum = sum.length();
    				// go over the parts of the string, every part is of the length 9 or less if needed 
    				for(int part = 0; part <  sum.length() ; part = part + 9) 
    				{
    					// the length of the part can be equal to 9
    					if(part + 9 <= sum.length()) {
    						initialLength = sum.substring(part, part + 9).length();
    						multipliedValue = Integer.valueOf(sum.substring(part, part + 9)) * 2;
    					}
    					else 
    					{
    						initialLength = sum.substring(part, sum.length()).length();
    						multipliedValue = Integer.valueOf(sum.substring(part, sum.length())) * 2;
    					}
    					// we are in the middle part of the string or the last part
    					if(part > 0) 
    					{
    						if(String.valueOf(multipliedValue).length() > initialLength)
    						{
    							sum = sum.substring(0,part-1)+ String.valueOf(Integer.valueOf(sum.substring(part-1, part)) + 1) + String.valueOf(multipliedValue).substring(1,initialLength+1) + sum.substring(part+initialLength,sum.length());
    						}
    						else
    						{
    							String StringOfMultipliedValue = String.valueOf(multipliedValue); 
    							// We need to add the deleted zeros to the start of the string, if so..
    							for(int m = 0 ; m < initialLength - String.valueOf(multipliedValue).length(); m++ )
    							{
    								StringOfMultipliedValue= "0"+ StringOfMultipliedValue;
    							}
    							// we are in the middle part of the string or the last part
    							if(part + initialLength< sum.length()) 
    							{
    								sum = sum.substring(0,part)+ StringOfMultipliedValue + sum.substring(part + initialLength, sum.length());
    							}
    							else sum = sum.substring(0,part)+ StringOfMultipliedValue;
    						}
    					}
    					// if we are on the first part, we need the string only, no need to add one of the numbers to
    					// a previous part.
    					else 
    					{
    						sum = Integer.toString(multipliedValue) + sum.substring(part+9,sum.length());
    						if(sum.length() > startingLengthOfSum) 
    						{
    							part++;
    						}
    					}
    				}
    				//adds the "1" or "0" value of the bit we are currently working with to the sum string
    				sum = sum.substring(0, sum.length()-1)+ String.valueOf(Integer.valueOf(sum.substring(sum.length()-1, sum.length())) + test.bits.removeLast().toInt());
    			}
    		}
    	}
    	return sum;
    }
    
    //end of private methods
    


    // Returns this * 2
    public BinaryNumber multBy2() {
        BinaryNumber output = new BinaryNumber(this);
        output.bits.shiftLeft();
        output.bits.reduce();
        return output;
    }

    // Returens this / 2;
    public BinaryNumber divBy2() {
        BinaryNumber output = new BinaryNumber(this);
        if (!equals(ZERO)) {
            if (signum() == -1) {
                output.negate();
                output.bits.shiftRight();
                output.negate();
            } else output.bits.shiftRight();
        }
        return output;
    }
}
