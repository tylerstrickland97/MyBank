package mybank.user;


public abstract class User {

	private String first;
	
	private String last;
	
	private String email;
	
	private String password;
	
	private String id;
	
	private String birthday;
	
	private final int PASSWORD_MIN_LENGTH = 8;
	
	private final int FEBRUARY = 2;
	
	private final int FEBRUARY_MAX_DAYS = 29;
	
	private final int LEAP_YEAR_OCCURENCE = 4;
	
	private final int APRIL = 4;
	
	private final int JUNE = 6;
	
	private final int SEPTEMBER = 9;
	
	private final int NOVEMBER = 11;
	
	private final int DECEMBER = 12;
	
	private final int MAX_MONTH_DAYS = 31;
	
	private final int CURR_YEAR = 2022;
	
	private final int MIN_YEAR = 1900;
	
	public User(String first, String last, String email, String password, String id, String birthday) {
		setFirst(first);
		setLast(last);
		setEmail(email);
		setPassword(password);
		setId(id);
		setBirthday(birthday);
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		if (first == null || first.length() == 0 || first.equals(" ")) {
			throw new IllegalArgumentException("First name cannot be empty");
		}
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		if (last == null || last.length() == 0 || last.equals(" ")) {
			throw new IllegalArgumentException("Last name cannot be empty");
		}
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String e) {
		if (e == null || e.length() == 0 || e.equals(" ")) {
			throw new IllegalArgumentException("Email cannot be empty");
		}
		int lastDot = e.lastIndexOf(".");
		int atDot = e.indexOf("@");
		if (lastDot < atDot || lastDot == -1 || atDot == -1) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = e;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null || password.length() < PASSWORD_MIN_LENGTH) {
			throw new IllegalArgumentException("Password must be at least 8 characters");
		}
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id == null || id.length() == 0 || id.equals(" ")) {
			throw new IllegalArgumentException("ID cannot be empty");
		}
		this.id = id;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		
		if (birthday == null || birthday.equals(" ") || birthday.length() != 10) {
			throw new IllegalArgumentException("Enter a valid date in the format MM/DD/YYYY");
		}
		
		String month = birthday.substring(0,2);
		String day = birthday.substring(3, 5);
		String year = birthday.substring(6);
		int monthInt = Integer.parseInt(month);
		int dayInt = Integer.parseInt(day);
		int yearInt = Integer.parseInt(year);
		
		if (yearInt > CURR_YEAR) {
			throw new IllegalArgumentException("Invalid year");
		}
		if (yearInt < MIN_YEAR) {
			throw new IllegalArgumentException("Enter a year after 1900");
		}
		if (dayInt < 1 || dayInt > MAX_MONTH_DAYS) {
			throw new IllegalArgumentException("Invalid day");
		}
		if (monthInt < 1 || monthInt > DECEMBER) {
			throw new IllegalArgumentException("Invalid month");
		}
		
		if (monthInt == APRIL || monthInt == JUNE || monthInt == SEPTEMBER || monthInt == NOVEMBER) {
			if (dayInt > MAX_MONTH_DAYS - 1) {
				throw new IllegalArgumentException("Invalid date");
			}
		}
		
		if (monthInt == FEBRUARY && dayInt > FEBRUARY_MAX_DAYS) {
			throw new IllegalArgumentException("Invalid date");
		}
		
		if (monthInt == FEBRUARY && dayInt > FEBRUARY_MAX_DAYS - 1 && yearInt % LEAP_YEAR_OCCURENCE != 0) {
			throw new IllegalArgumentException("Invalid date");
		}
		this.birthday = birthday;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	public String toString() {
		return first + "," + last + "," + email + "," + password + "," + id + "," + birthday;
	}
	
	
}
