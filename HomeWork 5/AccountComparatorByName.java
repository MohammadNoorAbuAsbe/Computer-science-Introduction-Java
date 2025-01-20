/*---------------------------------------
 Genuine author: Mohammad Noor, I.D.: 315226472
 Date: xx-xx-2020 
---------------------------------------*/
import java.util.Comparator;

public class AccountComparatorByName implements Comparator<BankAccount>{

	@Override
	public int compare(BankAccount account1, BankAccount account2) {
		// task 2a
		int comparedTo = 0;
		if (account1 == null | account2 == null) {
			throw new IllegalArgumentException("one of the accounts is null");
		}
		//gets the names of the accounts
		String account1Name = account1.getName();
		String account2Name = account2.getName();
		//compares the letters in each name
		for (int i = 0; i < account1Name.length() & i < account2Name.length() & comparedTo == 0 ; i++) {
			if (account1Name.charAt(i) > account2Name.charAt(i)) {
				comparedTo = 1;
			}
			else if (account1Name.charAt(i) < account2Name.charAt(i)) {
				comparedTo = -1; 
			}
		}
		//in case one of them was shorter while all the letters were equals, the longer one is the bigger
		if (comparedTo == 0) {
			if (account1Name.length() != account2Name.length()) {
				if (account1Name.length() > account2Name.length()) {
					comparedTo = 1;
				}
				else {
					comparedTo = -1;
				}
			}
		}
		return comparedTo;
	}
}
