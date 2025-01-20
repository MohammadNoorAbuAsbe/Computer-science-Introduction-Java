
public class NumberAsBits {

    private Bit[] bits;

    //the constructor 
    public NumberAsBits(Bit[] bits) {
    	//Task 3.4
    	//throws an exception if the given Bit is null 
    	if(bits == null) {
    		throw new IllegalArgumentException("the Given Bit is null");
    	}
    	//creates a new Bit array to store the given Bit array
    	this.bits = new Bit[bits.length]; 
    	for(int index = 0; index < bits.length; index = index + 1) {
    		//throws an exception if one of the bits is null 
    		if(bits[index] == null) {
    			throw new IllegalArgumentException("the bit at index " + index + " is null");
    		}
    		//stores the given bits in the new Bit
    		this.bits[index] = bits[index];
    	}
    }

    public String toString() { 
    	String ans ="";
    	//Task 3.5
    	//get a string representing the decimal value of the given bits
    	ans = Decimal();
    	return ans;
    }
    
    //return the binary value of the bits
    public String base2() {
        String ans ="";
        //Task 3.6
        //returns the "1" and "0" value of the bits in order
        for(int index = 0; index < bits.length; index = index + 1){
        	ans= ans + bits[index].toInt();
        }
        return ans;
    }
    
    //private method
    private String Decimal() {
    	String sum = "0";
    	boolean start_of_number_flag = false;
    	int multipliedValue = 0;
    	int initialLength = 0;
    	for(int index = 0 ; index < bits.length ; index++) 
    	{
    		// we skip the zeros on the most significant bits.
    		if(!start_of_number_flag) 
    		{
    			if(bits[index].toInt() == 1) 
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
    				int ValueOfSum = Integer.valueOf(sum) * 2 + bits[index].toInt();
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
    				sum = sum.substring(0, sum.length()-1)+ String.valueOf(Integer.valueOf(sum.substring(sum.length()-1, sum.length())) + bits[index].toInt());
    			}
    		}
    	}
    	return sum;
    }
}


