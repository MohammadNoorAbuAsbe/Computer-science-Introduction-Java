

public class Bit {

    private  boolean value;
    public static  final Bit ONE  = new Bit(true);
    public static  final Bit ZERO = new Bit(false);

    public Bit(boolean value) {
        this.value = value;
    }

    public Bit(int intValue) {
        if (intValue == 0)
            value = false;
        else {
            if (intValue == 1)
                value = true;
            else throw new IllegalArgumentException(value + " is neither 0 nor 1.");
        }
    }

    public String toString() {
        return "" + toInt();
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Bit))
            return false;
        else return value == ((Bit) obj).value;
    }

    public Bit negate() {
        Bit output;
        if (value)
            output = ZERO;
        else output = ONE;
        return output;
    }

    public int toInt() {
        int output;
        if(value)
            output = 1;
        else
            output = 0;
        return output;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 1.1 ================================================
    public static Bit fullAdderSum(Bit bit1, Bit bit2, Bit bit3) {
    	int numberOfOnes = 0;
    	Bit finallSumBit = ZERO;
		numberOfOnes = bit1.toInt() + bit2.toInt() + bit3.toInt();  //adds the Integer value of each bit, being one or zero
		if (numberOfOnes % 2 == 1)  //checks if the number of one's in the given bits to sum is odd or even
		{
			finallSumBit = ONE;
		}
        return finallSumBit;
    }

    public static Bit fullAdderCarry(Bit bit1, Bit bit2, Bit bit3) {
    	int numberOfOnes = 0;
    	Bit finallCarryBit = ZERO;
		numberOfOnes = bit1.toInt() + bit2.toInt() + bit3.toInt();  //adds the Integer value of each bit, being one or zero
		if (numberOfOnes > 1)  //checks if the number of one's in the given bits to sum is bigger than one
		{
			finallCarryBit = ONE;
		}
        return finallCarryBit;
    }

}
