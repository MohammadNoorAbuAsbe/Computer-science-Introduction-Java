/*---------------------------------------
 Genuine author: Mohammad Noor, I.D.: 315226472
 Date: xx-xx-2020 
---------------------------------------*/
import java.util.Comparator;

public class AccountComparatorByNumber implements Comparator<BankAccount>{

	@Override
	public int compare(BankAccount account1, BankAccount account2) {
		// task 2b
		int comparedTo = 0;
		if (account1 == null | account2 == null) {
			throw new IllegalArgumentException("one of the accounts is null");
		}
		//gets the accounts numbers
		int account1Number = account1.getAccountNumber();
		int account2Number = account2.getAccountNumber();
		//checks which number is bigger
		if (account1Number != account2Number) {
			if (account1Number > account2Number) {
				comparedTo = 1;
			}
			else {
				comparedTo = -1;
			}
		}
		return comparedTo;
	}

}
