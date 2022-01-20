package mybank.data;

import java.util.ArrayList;

public class Account {

	private String name;

	private String reference;

	private double balance;

	private boolean checking;
	
	private boolean savings;
	
	private ArrayList<Transaction> transactions;
	
	public Account(String name, String reference, double balance, boolean checking, boolean savings, Transaction[] t) {
		setName(name);
		setReference(reference);
		if (checking && savings) {
			throw new IllegalArgumentException("Cannot be both a checking and savings account");
		}
		else if (!checking && !savings) {
			throw new IllegalArgumentException("Must be either a checking or savings account");
		}
		this.checking = checking;
		this.savings = savings;
		setBalance(balance);
		transactions = new ArrayList<Transaction>();
		setTransactions(t);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		
		if (name == null || name.equals(" ") || name.length() == 0) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		this.name = name;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		if (reference == null || reference.equals(" ") || reference.length() != 8) {
			throw new IllegalArgumentException("Invalid reference");
		}
		try {
			Integer.parseInt(reference);
			
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid reference");
		}
		this.reference = reference;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		if (isSavings() && balance < 0) {
			throw new IllegalArgumentException("Savings account cannot have negative balance");
		}
		this.balance = balance;
	}

	public boolean isChecking() {
		return checking;
	}


	public boolean isSavings() {
		return savings;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Transaction[] t) {
		if (t == null) {
			throw new IllegalArgumentException("Transactions cannot be null");
		}
		for (int i = 0; i < t.length; i++) {
			if (t[i] != null) {
				this.transactions.add(t[i]);
			}
		}
		
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (checking ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + (savings ? 1231 : 1237);
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (checking != other.checking)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (savings != other.savings)
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		return true;
	}


	public String toString() {
		String checkingStr = "";
		String savingsStr = "";
		if (isChecking()) {
			checkingStr = "true";
		}
		else {
			checkingStr = "false";
		}
		if (isSavings()) {
			savingsStr = "true";
		}
		else {
			savingsStr = "false";
		}
		return name + "," + reference + "," + String.format("%.2f", balance) + "," + checkingStr + "," + savingsStr;
	}
	
	
}
