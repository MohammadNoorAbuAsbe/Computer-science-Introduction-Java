/*---------------------------------------
 Genuine author: <Mohammad Noor>, I.D.: <315226472>
 Date: xx-xx-2020 
---------------------------------------*/
import java.util.Iterator;

public class PrimeIterator implements Iterator<Integer> {

	private List<Integer> primes;

	//Complete the following methods
	public PrimeIterator(){
		// task 0
		//Creates a new DynamicArray list , adds the first prime number to it, number 2;
		primes = new DynamicArray<Integer>();
		primes.add(2);
	}


	public boolean hasNext(){
		// task 0
		boolean hasNext = true;
		//checks if we have reached the last possible prime in integer
		if (primes.get(primes.size()-1) == -1) {
			hasNext = false;
		}
		return hasNext;
	}

	//returns the next prime
	public Integer next(){
		// task 0
		int primeNumber = -1;
		// checks if there is more primes
		if (hasNext()) 
		{
			//gets the last prime
			primeNumber = primes.get(primes.size()-1);
			//calculates the next prime and adds him to the list
			calculateTheNextPrime();
		}
		//returns the last prime
		return primeNumber;

	}

	//private method

	//calculates the next prime and adds him to the list
	private void calculateTheNextPrime() {
		boolean isPrime = false;
		//gets the last prime in the list
		int potintialPrime = primes.get(primes.size()-1);
		//works until finding a new prime
		while(! isPrime) {
			isPrime = true;
			//checks each number till finding a prime number
			potintialPrime = potintialPrime + 1;
			//first checks if we have reached the last prime, if yes , adds -1 to the end of the primes list
			if (potintialPrime < 0) {
				primes.add(-1);
			}
			else {
				//checks if the number is prime or not
				for (int i = 0; i < primes.size() && primes.get(i)*primes.get(i) <= potintialPrime & isPrime ;i = i + 1 )
					if (potintialPrime%primes.get(i) == 0)
						isPrime = false;
				//if is prime, adds the number to the list
				if(isPrime) {
					primes.add(potintialPrime);
				} 
			}
		}
	}
}