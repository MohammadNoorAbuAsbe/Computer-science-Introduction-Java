
import java.util.LinkedList;

import java.util.Iterator;

public class BitList extends LinkedList<Bit> {
    private int numberOfOnes;

    // Do not change the constructor
    public BitList() {
        numberOfOnes = 0;
    }

    // Do not change the method
    public int getNumberOfOnes() {
        return numberOfOnes;
    }


//=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.1 ================================================
    
    //add the given element to the last place in the list and increase the numberOfOnes if needed
    public void addLast(Bit element) {
        if (element == null) {
        	throw new IllegalArgumentException("the given Bit is null");			
		}
        super.addLast(element);
        numberOfOnes = numberOfOnes + element.toInt();
    }

    //add the given element to the first place in the list and increase the numberOfOnes if needed
    public void addFirst(Bit element) {
        if (element == null) {
        	throw new IllegalArgumentException("the given Bit is null");			
		}
        super.addFirst(element);
        numberOfOnes = numberOfOnes + element.toInt();
    }

	//remove the last Bit from the list and return it, and reduce the numberOfOnes if needed
    public Bit removeLast() {
    	Bit removedBit = super.removeLast();
    	numberOfOnes = numberOfOnes - removedBit.toInt();
    	return removedBit;
    }

	//remove the first Bit from the list and return it, and reduce the numberOfOnes if needed
    public Bit removeFirst() {
    	Bit removedBit = super.removeFirst();
    	numberOfOnes = numberOfOnes - removedBit.toInt();
    	return removedBit;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.2 ================================================
    
    //returns a string representing the list
    public String toString() {
        String listAsString = "";
        Iterator<Bit> iterator = this.iterator();
        //check if there is more bits in the list and adds them to the string
        while (iterator.hasNext()!= false) {
			listAsString = iterator.next() + listAsString;
		}
        listAsString = "<" + listAsString +">";
        return listAsString;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.3 ================================================
    
    // copy the given Bit list
    public BitList(BitList other) {
    	if (other == null) {
    		throw new IllegalArgumentException("the given Bit list is null");		
		}
    	numberOfOnes = 0;
    	Iterator<Bit> iterator = other.iterator();
    	//checks if there is more bits in the given list and copies them
    	while (iterator.hasNext()!= false) {
			this.addLast(iterator.next());
		}	
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.4 ================================================
    
    //checks if the list represents a number
    public boolean isNumber() {
        boolean isNumber = false;
        //make sure the list is not empty
        if(size()>0) 
        {
        	//check if the last bit is zero , or there is at least two one's in the list
        	if (getLast().equals(Bit.ZERO) | getNumberOfOnes() > 1) {
				isNumber = true;
			}
        }
        return isNumber;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.5 ================================================
    
    //checks if the list represents a number in a minimal way
    public boolean isReduced() {
        boolean isReduced = false; 
        //checks if the list represents a number
        if (isNumber()) {
        	//checks if the list represents one of the numbers -1,0,1 in a minimal way (method is below)
			if(equalToBasicNumbers()) {
				isReduced = true;
			}
			//checks if the list ends with 01 or 10 (method is below)
			else if(MsbIsDiffrent()){
				isReduced = true;
			}
			//checks if there is only two bits that are "1" and if they are the MSB (method is below)
			else if (MsbIs_11_AndOnlyIt()) {
				isReduced = true;
			}
		}
        return isReduced;
    }
    
    //Private Methods
    
    //checks if the list represents one of the numbers -1,0,1 in a minimal way
    private boolean equalToBasicNumbers() {
    	boolean equalToBasicNumbers = false;
    	//check if this is the number 0
    	if (this.size() == 1) {
			if(getFirst()== Bit.ZERO) {
				equalToBasicNumbers = true;
			}
		}
    	//check if the number is -1 or 1
    	else if(size() == 2) {
			if (getFirst() == Bit.ONE) {
				equalToBasicNumbers = true;
			}
		}
		return equalToBasicNumbers;
    }
    
    //checks if the list ends with 01 or 10
    private boolean MsbIsDiffrent() {
		boolean MsbIsDiffrent = false;
		if (size()>2) {
        	//get the last bit in the list by removing it
        	Bit lastBit = removeLast();
        	//checks if the MSB that we removed is different from the new MSB
        	if (lastBit != getLast()) {
        		MsbIsDiffrent = true;
			}
        	//add the removed MSB back
        	addLast(lastBit);
		}
		return MsbIsDiffrent;
	}
    
    //checks if there is only two bits that are "1" and if they are the MSB
    private boolean MsbIs_11_AndOnlyIt() {
    	boolean MsbIs_11_AndOnlyIt = false;
    	if (size()>2) {
			if (numberOfOnes == 2) {
				if (getLast() == Bit.ONE) {
					//remove the MSB to check the bit before it, add it back later 
					removeLast();
					if (getLast() == Bit.ONE) {
						MsbIs_11_AndOnlyIt = true;
					}
					addLast(Bit.ONE);
				}
			}
		}
    	return MsbIs_11_AndOnlyIt;
	}

    //end of private methods
    
    //makes the list represent a number if so in a minimal way
    public void reduce() {
    	//makes sure that the list we want to reduce is representing a number
    	if (!isNumber()) {
    		throw new IllegalArgumentException("the list is'nt representing a number to be able to reduce it");
    	}
    	//reduce the list to represent the number in a minimal way, by removing the unnecessary Most Significant Bits 
    	while (!isReduced()) {
			removeLast();
		}
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.6 ================================================
    
    //returns a complemented list,
    public BitList complement() {
    	BitList complementedBitList = new BitList();
    	Iterator<Bit> iterator = this.iterator();
    	//checks if there is more bits in the list , flips them from zeros to ones and the other way, and adds them to a new list
    	while (iterator.hasNext()!= false) {
    		complementedBitList.addLast(iterator.next().negate());
		}	
    	return complementedBitList;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.7 ================================================
    
    //shifts the number to the right
    public Bit shiftRight() {
    	Bit shiftedBit = null;
    	//remove the first Bit if there is any
    	if (size()>0) {
    		shiftedBit = removeFirst();
		}
    	return shiftedBit;
    }

    //shifts the number to the left
    public void shiftLeft() {
    	//add zero to the first place
    	addFirst(Bit.ZERO);
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.8 ================================================
    
    // pads the number by adding bits in the end of the number
    public void padding(int newLength) {
    	if (isEmpty()) {
			throw new IllegalArgumentException("cant pad an empty bitlist");
		}
    	Bit lastBit = getLast();
    	//add the last bit over and over to the end of the number until he becomes from the needed length 
    	while (newLength > size()) {
    		addLast(lastBit);
		}
    }

    

    //----------------------------------------------------------------------------------------------------------
    // The following overriding methods must not be changed.
    //----------------------------------------------------------------------------------------------------------
    public boolean add(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public void add(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit remove(int index) {
        throw new UnsupportedOperationException("Do not use this method!");
        
    }

    public boolean offer(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerFirst(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerLast(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit set(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Do not use this method!");
    }
}
