package mybank.user;

import java.util.ArrayList;

//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

public class Employee extends User{

	
	private ArrayList<Depositor> depositors;
	
	public Employee(String first, String last, String email, String password, String id, String birthday, Depositor[] d) {
		super(first, last, email, password, id, birthday);
		depositors = new ArrayList<Depositor>();
		addDepositors(d);
	}
	
	private void addDepositors(Depositor[] d) {
		if (d == null) {
			throw new IllegalArgumentException("Depositors cannot be null");
		}
		for (int i = 0; i < d.length; i++) {
			if (d[i] != null) {
				depositors.add(d[i]);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((depositors == null) ? 0 : depositors.hashCode());
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
		Employee other = (Employee) obj;
		if (depositors == null) {
			if (other.depositors != null)
				return false;
		} else if (!depositors.equals(other.depositors))
			return false;
		return true;
	}

	public ArrayList<Depositor> getDepositors() {
		return depositors;
	}
	
	
}
