/*---------------------------------------
 Genuine author: Mohammad Noor, I.D.: 315226472
 Date: xx-xx-2020 
---------------------------------------*/

/**
 * This class represents a bank,
 * it has a names tree and accounts number tree,
 * and it supports various operations like looking up accounts, adding accounts and removing accounts,
 * and depositing money and withdrawing from accounts.
 * 
 * @author Mohammad Noor AbuAsbe
 */

public class Bank {

	private BankAccountsBinarySearchTree namesTree;
	private BankAccountsBinarySearchTree accountNumbersTree;

	/**
	 * A constructor for the bank, constructs an empty bank,
	 * and assigns the correct comparators to the trees.
	 */
	public Bank() {
		namesTree = new BankAccountsBinarySearchTree(new AccountComparatorByName());
		accountNumbersTree = new BankAccountsBinarySearchTree(new AccountComparatorByNumber());
	}

	/**
	 * A method to look up accounts in the bank by the names.
	 * 
	 * @param the name of the account to look up.
	 * @return the account if founded in the bank,
	 * else if the account wasn't founded returns null.
	 */
	public BankAccount lookUp(String name){
		// create an Entry with the given name, a "dummy" accountNumber (1) and zero balance
		// This "dummy" accountNumber will be ignored when executing getData
		BankAccount lookFor = new BankAccount(name, 1, 0);
		return (BankAccount)namesTree.findData(lookFor);
	}

	/**
	 * A method to look up accounts in the bank by the account number.
	 * 
	 * @param the number of the account to look up.
	 * @return the account if founded in the bank,
	 * else if the account wasn't founded returns null.
	 */
	public BankAccount lookUp(int accountNumber){
		// create an Entry with a "dummy" name, zero balance and the given accountNumber
		// This "dummy" name will be ignored when executing getData
		BankAccount lookFor = new BankAccount("dummy", accountNumber,0);
		return (BankAccount)accountNumbersTree.findData(lookFor);
	}

	// END OF Given code -----------------------------------

	// Complete the methods from here on

	/**
	 * This method adds the given account to the bank.
	 * 
	 * @param a bank account to add to the bank.
	 * @return true if the account was added successfully to the bank,
	 * else returns false if the account name or number was already in the bank,
	 * and the account wasn't added.
	 */
	public boolean add(BankAccount newAccount) {
		// task 6a
		boolean added = false;
		//checks if the account name or number was added to the to the bank before
		if (lookUp(newAccount.getAccountNumber())== null && lookUp(newAccount.getName()) == null) {
			//if not add the account to the bank, to both the accounts trees 
			namesTree.insert(newAccount);
			accountNumbersTree.insert(newAccount);
			added = true;
		}
		return added;
	}

	/**
	 * This method deletes the given account from the bank.
	 * 
	 * @param the name of the account to be deleted.
	 * @return true if the account was found and deleted successfully from the bank,
	 * else returns false if the account was not found.
	 */
	public boolean delete(String name){
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(name);
		// complete this:

		// task 6b
		boolean removed = false;
		//checks if the account was found in the bank
		if (toRemove != null) {
			//if the account was found , remove the account from both the trees
			namesTree.remove(toRemove);
			accountNumbersTree.remove(toRemove);
			removed = true;
		}
		return removed;
	}

	/**
	 * This method deletes the given account from the bank.
	 * 
	 * @param the number of the account to be deleted.
	 * @return true if the account was found and deleted successfully from the bank,
	 * else returns false if the account was not found.
	 */
	public boolean delete(int accountNumber){
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(accountNumber);
		// complete this:

		// task 6c
		boolean removed = false;
		//checks if the account was found in the bank
		if (toRemove != null) {
			//if the account was found , remove the account from both the trees
			namesTree.remove(toRemove);
			accountNumbersTree.remove(toRemove);
			removed = true;
		}
		return removed;
	}

	/**
	 * This method deposits money into the given bank account.
	 *
	 * @param the amount of money to deposit.
	 * @param the number of the account to deposit money into.
	 * @return true if the account was founded in the bank and the deposit was done successfully,
	 * else returns false if the account was not found or the deposit was not successful.
	 */
	public boolean depositMoney(int amount, int accountNumber){
		// task 6d
		//tries to find the account in the bank by the account number
		BankAccount bankAccount = lookUp(accountNumber);
		boolean legalDeposit = false;
		//if the account was found, try to deposit the given amount of money
		if (bankAccount != null) {
			//checks if the given amount to deposit is valid and positive, if yes, deposit the money into the account
			legalDeposit = bankAccount.depositMoney(amount);
		}
		return legalDeposit;
	}

	/**
	 * This method withdraws money from the given bank account.
	 *
	 * @param the amount of money to withdraw.
	 * @param the number of the account to withdraw the money from.
	 * @return true if the account was founded in the bank and the withdraw was done successfully,
	 * else returns false if the account was not found or the withdraw was not successful.
	 */
	public boolean withdrawMoney(int amount, int accountNumber){
		// task 6e
		//tries to find the account in the bank by the account number
		BankAccount bankAccount = lookUp(accountNumber);
		boolean legalWithDraw = false;
		//if the account was found, try to withdraw money from the account equal to the given amount of money
		if (bankAccount != null) {
			//checks if the given amount to withdraw is valid and that the account have enough money to be able to withdraw, if yes, withdraw the money from the account
			legalWithDraw = bankAccount.withdrawMoney(amount);
		}
		return legalWithDraw;
	}	
}
