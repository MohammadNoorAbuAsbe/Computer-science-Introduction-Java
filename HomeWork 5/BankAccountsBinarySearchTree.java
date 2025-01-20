/*---------------------------------------
 Genuine author: Mohammad Noor, I.D.: 315226472
 Date: xx-xx-2020 
---------------------------------------*/
import java.util.Comparator;
import java.util.Iterator;


public class BankAccountsBinarySearchTree extends BinarySearchTree<BankAccount>{

	public BankAccountsBinarySearchTree(Comparator<BankAccount> myComparator) {
		super(myComparator);
	}

	// Complete the following methods
	public void balance(){
		// task 5b
		List<BankAccount> list = new LinkedList<BankAccount>();
		Iterator<BankAccount> iterator = this.iterator();
		//adds all the accounts to the list in order from the smallest to the biggest based on the given iterator
		while (iterator.hasNext()) {
			//adds the first account to the list
			if (list.isEmpty()) {
				list.add(iterator.next());
			}
			else {
				BankAccount bankAccount = iterator.next();
				boolean isBigger = true;
				//compares each account with the accounts that have been already added to the list
				for (int i = 0; i < list.size() & isBigger; i++) {
					if (comparator.compare(bankAccount, list.get(i) ) == -1) {
						isBigger = false;
						//if the account is smaller than an account that have been already in the list, puts this account in front of him
						list.add(i,bankAccount);
					}
				}
				//if the account is bigger than all the other accounts in the list , add this account to the end of the list
				if (isBigger) {
					list.add(bankAccount);
				}
			}
		}
		//checks if the binary tree wasn't empty
		if (!list.isEmpty()) {
			//reset the current tree to be empty
			this.root = null;
			//builds a new balanced tree
			buildBalancedTree( list, 0,list.size()-1);
		}
	}

	private void buildBalancedTree(List<BankAccount> list, int low, int high){
		// task 5b
		//each time inserts the account in the middle of the given list, the comparator adds it to the correct place
		insert(list.get((low + high)/2));
		//checks if the given range to work with is correct, if not the method stops
		if (high > low) {
			//Recursive calls, once with right half of the list, and once with the left half (the middle account is not included in any)
			buildBalancedTree(list, low,  ((low + high)/2)-1);
			buildBalancedTree(list, ((low+ high)/2) +1,  high);
		}
	}
}
