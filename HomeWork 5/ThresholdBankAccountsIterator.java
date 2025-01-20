import java.util.Iterator;
import java.util.NoSuchElementException;


public class ThresholdBankAccountsIterator implements Iterator<BankAccount> {

	private BinaryTreeInOrderIterator<BankAccount> iterator;
	private BankAccount current;
	private int balanceThreshold;

	public ThresholdBankAccountsIterator(BankAccountsBinarySearchTree bankAccountsTree, int balanceThreshold) {
		// task 5c
		//assigns a new BinaryTreeInOrderIterator for the given bankAccountsTree
		this.iterator = new BinaryTreeInOrderIterator<BankAccount>(bankAccountsTree.root);
		//gets the wanted balance Threshold
		this. balanceThreshold = balanceThreshold;

	}

	//Complete the following method
	@Override
	public boolean hasNext() {
		// task 5c
		boolean hasNext = false;
		//checks if there is still more account and checks if they meet the conditions
		while (!hasNext & iterator.hasNext()) {
			//gets the next account in the tree
			current = iterator.next();
			//checks if the account meets the conditions
			if (current.getBalance()>= balanceThreshold) {
				hasNext = true;
			}
		}
		return hasNext;
	}

	//Complete the following method
	@Override
	public BankAccount next() {
		// task 5c
		BankAccount bankAccount = null;
		//checks if we don't have an account stored that meets the conditions 
		if (current == null) {
			//checks if there is more accounts that meet the conditions
			if ( !hasNext()) {
				throw new NoSuchElementException();
			}
		}
		else {
			//gets the account that meets the condition and resets the stored account to null
			bankAccount = current;
			current = null;
		}
		return bankAccount;
	}
}
