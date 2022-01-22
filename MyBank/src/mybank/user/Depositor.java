package mybank.user;

import java.util.ArrayList;

import mybank.data.Account;

public class Depositor extends User{

	
	ArrayList<Account> accounts;
	
	public Depositor(String first, String last, String email, String password, String id, String birthday, Account[] a) {
		super(first, last, email, password, id, birthday);
		accounts = new ArrayList<Account>();
		addAccounts(a);
	}
	
	private void addAccounts(Account[] a) {
		if (a == null) {
			throw new IllegalArgumentException("Accounts cannot be null");
		}
		for (int i = 0; i < a.length; i++) {
			if (a[i] != null) {
				accounts.add(a[i]);
			}
		}
	}
	
	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Depositor other = (Depositor) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		return true;
	}
	
	
}
