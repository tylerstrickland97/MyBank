package mybank.user;

import static org.junit.Assert.*;

import org.junit.Test;

import mybank.data.Account;
import mybank.data.Transaction;

public class EmployeeTest {

	@Test
	public void testConstructor() {
		Transaction t1 = new Transaction(-56.75, "Walmart", "11/11/2021");
		Transaction t2 = new Transaction(-100.98, "Target", "12/20/2021");
		Transaction[] trans1 = new Transaction[2];
		trans1[0] = t1;
		trans1[1] = t2;
		Account a1 = new Account("Checking Account", "10203300", 450.98, true, false, trans1);
		Transaction t3 = new Transaction(-500.00, "Withdrawal", "12/14/2021");
		Transaction t4 = new Transaction(-100.98, "Target", "01/12/2022");
		Transaction[] trans2 = new Transaction[2];
		trans2[0] = t3;
		trans2[1] = t4;
		Account a2 = new Account("Savings account", "10020011", 1209.23, false, true, trans2);
		Account[] accs1 = new Account[1];
		Account[] accs2 = new Account[1];
		accs1[0] = a1;
		accs2[0] = a2;
		Depositor d1 = new Depositor("Megan", "Smith", "megansmith@gmail.com", "password", "msmith2", "11/11/1995", accs1);
		Depositor d2 = new Depositor("Tyler", "Strickland","tylerstrickland97@gmail.com", "password", "tstrickland3", "05/03/2001", accs2);
		Depositor[] deps = new Depositor[2];
		deps[0] = d1;
		deps[1] = d2;
		Employee e1 = null;
		try {
			e1 = new Employee("Harold", "Palmer", "haroldpalmer@mybankmail.com", "password", "hpalmer2", "12/21/1976", deps);
			assertEquals("Harold", e1.getFirst());
			assertEquals("Palmer", e1.getLast());
			assertEquals("haroldpalmer@mybankmail.com", e1.getEmail());
			assertEquals("password", e1.getPassword());
			assertEquals("hpalmer2", e1.getId());
			assertEquals("12/21/1976", e1.getBirthday());
			assertEquals(2, e1.getDepositors().size());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
		Transaction t5 = new Transaction(1000.00, "Weekly Deposit", "01/02/2022");
		Transaction t6 = new Transaction(-50.00, "Withdrawal", "01/12/2022");
		Transaction t7 = new Transaction(-97.22, "Belk", "12/22/2021");
		Transaction t8 = new Transaction(-40.23, "Zaxbys", "12/29/2021");
		Transaction[] trans3 = new Transaction[2];
		trans3[0] = t5;
		trans3[1] = t6;
		Transaction[] trans4 = new Transaction[2];
		trans4[0] = t7;
		trans4[1] = t8;
		Account a3 = new Account("My Checking Account", "01001020", 1345.98, true, false, trans4);
		Account a4 = new Account("My Savings", "01002010", 987.22, false, true, trans3);
		Account[] accs3 = new Account[1];
		Account[] accs4 = new Account[1];
		accs3[0] = a3;
		accs4[0] = a4;
		Depositor d3 = new Depositor("Greg", "Sanders", "greg.sanders@yahoo.com", "password", "gsanders3", "02/13/1984", accs3);
		Depositor d4 = new Depositor("Franklin", "Martin", "frankmartin@gmail.com", "password", "fmartin2", "09/18/1960", accs4);
		Depositor[] deps2 = new Depositor[2];
		deps2[0] = d3;
		deps2[1] = d4;
		Employee e2 = null;
		try {
			e2 = new Employee("Baker", "Crutchfield", "bakercrutchfield@mybankmail.com", "password", "bcrutchfield4", "06/12/1981", deps2);
			assertEquals("Baker", e2.getFirst());
			assertEquals("Crutchfield", e2.getLast());
			assertEquals("bakercrutchfield@mybankmail.com", e2.getEmail());
			assertEquals("password", e2.getPassword());
			assertEquals("bcrutchfield4", e2.getId());
			assertEquals("06/12/1981", e2.getBirthday());
			assertEquals(2, e2.getDepositors().size());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
		
		
	}
	
	@Test
	public void testSetFirst() {
		Transaction t5 = new Transaction(1000.00, "Weekly Deposit", "01/02/2022");
		Transaction t6 = new Transaction(-50.00, "Withdrawal", "01/12/2022");
		Transaction t7 = new Transaction(-97.22, "Belk", "12/22/2021");
		Transaction t8 = new Transaction(-40.23, "Zaxbys", "12/29/2021");
		Transaction[] trans3 = new Transaction[2];
		trans3[0] = t5;
		trans3[1] = t6;
		Transaction[] trans4 = new Transaction[2];
		trans4[0] = t7;
		trans4[1] = t8;
		Account a3 = new Account("My Checking Account", "01001020", 1345.98, true, false, trans4);
		Account a4 = new Account("My Savings", "01002010", 987.22, false, true, trans3);
		Account[] accs1 = new Account[1];
		accs1[0] = a3;
		Account[] accs2 = new Account[1];
		accs2[0] = a4;
		Depositor d1 = new Depositor("James", "Harrison", "jamesharrison@gmail.com", "password", "jharrison2", "04/12/1988", accs1);
		Depositor d2 = new Depositor("Richard", "Coats", "richardcoats@yahoo.com", "password", "rcoats3", "12/13/1991", accs2);
		
		Depositor[] deps = new Depositor[2];
		deps[0] = d1;
		deps[1] = d2;
		Employee e1 = new Employee("Lauren", "Thomas", "laurenthomas@mybankmail.com", "password", "lthomas3", "11/10/1995", deps);
		assertEquals("Lauren", e1.getFirst());
		try {
			e1.setFirst(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("First name cannot be empty", e.getMessage());
			assertEquals("Lauren", e1.getFirst());
		}
		
		try {
			e1.setFirst("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("First name cannot be empty", e.getMessage());
			assertEquals("Lauren", e1.getFirst());
		}
		
		try {
			e1.setFirst(" ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("First name cannot be empty", e.getMessage());
			assertEquals("Lauren", e1.getFirst());
		}
		
		try {
			e1.setFirst("Janie");
			assertEquals("Janie", e1.getFirst());
			e1.setFirst("Nicole");
			assertEquals("Nicole", e1.getFirst());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
			
	}
	
	@Test
	public void testSetLast() {
		Transaction t1 = new Transaction(-56.75, "Walmart", "11/11/2021");
		Transaction t2 = new Transaction(-100.98, "Target", "12/20/2021");
		Transaction[] trans1 = new Transaction[2];
		trans1[0] = t1;
		trans1[1] = t2;
		Account a1 = new Account("Checking Account", "10203300", 450.98, true, false, trans1);
		Transaction t3 = new Transaction(-500.00, "Withdrawal", "12/14/2021");
		Transaction t4 = new Transaction(-100.98, "Target", "01/12/2022");
		Transaction[] trans2 = new Transaction[2];
		trans2[0] = t3;
		trans2[1] = t4;
		Account a2 = new Account("Savings account", "10020011", 1209.23, false, true, trans2);
		Account[] accs1 = new Account[2];
		accs1[0] = a1;
		Account[] accs2 = new Account[2];
		accs2[0] = a2;
		Depositor d1 = new Depositor("Richard", "Hill", "richard.hill@gmail.com", "password", "rhill2", "03/14/1976", accs1);
		Depositor d2 = new Depositor("Karen", "Davis", "karendavis@gmail.com", "password", "kdavis3", "11/12/1969", accs2);
		Depositor[] deps = new Depositor[2];
		deps[0] = d1;
		deps[1] = d2;
		Employee e1 = new Employee("Nancy", "Reynolds", "nancyreynolds@mybankmail.com", "password", "nreynolds2", "01/19/1976", deps);
		assertEquals("Reynolds", e1.getLast());

		try {
			e1.setLast(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Last name cannot be empty", e.getMessage());
			assertEquals("Reynolds", e1.getLast());
		}
		
		try {
			e1.setLast(" ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Last name cannot be empty", e.getMessage());
			assertEquals("Reynolds", e1.getLast());
		}
		
		try {
			e1.setLast("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Last name cannot be empty", e.getMessage());
			assertEquals("Reynolds", e1.getLast());
		}
		
		try {
			e1.setLast("Jernigan");
			assertEquals("Jernigan", e1.getLast());
			e1.setLast("Harris");
			assertEquals("Harris", e1.getLast());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSetEmail() {
		Transaction t1 = new Transaction(-987.54, "House Payment", "11/10/2021");
		Transaction t2 = new Transaction(-650.00, "Car Payment", "12/05/2021");
		Transaction[] trans = new Transaction[2];
		trans[0] = t1;
		trans[1] = t2;
		Account a1 = new Account("Checking Account", "02010000", 409.89, true, false, trans);
		Account[] accs = new Account[1];
		accs[0] = a1;
		Depositor d1 = new Depositor("Elizabeth", "Thompson", "lizziethompson4@yahoo.com", "password", "ethompson5", "07/09/1991", accs);
		Depositor[] deps = new Depositor[1];
		deps[0] = d1;
		Employee e1 = new Employee("Parker", "Ryan", "parkerryan@mybankmail.com", "password", "pryan3", "11/28/1998", deps);
		assertEquals("parkerryan@mybankmail.com", e1.getEmail());
		
		try {
			e1.setEmail(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Email cannot be empty", e.getMessage());
			assertEquals("parkerryan@mybankmail.com", e1.getEmail());
		}
		
		try {
			e1.setEmail("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Email cannot be empty", e.getMessage());
			assertEquals("parkerryan@mybankmail.com", e1.getEmail());
		}
		
		try {
			e1.setEmail(" ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Email cannot be empty", e.getMessage());
			assertEquals("parkerryan@mybankmail.com", e1.getEmail());
		}
		
		try {
			e1.setEmail("parkerryangmailcom");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid email", e.getMessage());
			assertEquals("parkerryan@mybankmail.com", e1.getEmail());
		}
		
		try {
			e1.setEmail("parkerryan.@gmailcom");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid email", e.getMessage());
			assertEquals("parkerryan@mybankmail.com", e1.getEmail());
		}
		
		try {
			e1.setEmail("parkerryangmail.com");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid email", e.getMessage());
			assertEquals("parkerryan@mybankmail.com", e1.getEmail());
		}
		
		try {
			e1.setEmail("parker.ryan@mybankmail.com");
			assertEquals("parker.ryan@mybankmail.com", e1.getEmail());
			e1.setEmail("ryanparker1@mybankmail.com");
			assertEquals("ryanparker1@mybankmail.com", e1.getEmail());
			
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
		
	}
	
	@Test
	public void testSetPassword() {
		Transaction t1 = new Transaction(1000.00, "Paycheck", "01/09/2022");
		Transaction t2 = new Transaction(-21.34, "Food Lion", "12/23/2021");
		Transaction[] trans = new Transaction[2];
		trans[0] = t1;
		trans[1] = t2;
		
		Transaction t3 = new Transaction(-67.89, "Outback Steakhouse", "01/10/2022");
		Transaction t4 = new Transaction(-409.87, "Car Payment", "12/20/2021");
		Transaction[] trans2 = new Transaction[2];
		trans2[0] = t3;
		trans2[1] = t4;
		Account a1 = new Account("Checking Account", "00001111", 450.92, true, false, trans);
		Account a2 = new Account("Other Checking Account", "01020000", 98.75, true, false, trans2);
		Account[] accs = new Account[2];
		accs[0] = a1;
		accs[1] = a2;
		Depositor d1 = new Depositor("Mason", "Edwards", "masonedwards@gmail.com", "password", "medwards6", "04/19/2000", accs);
		Depositor[] deps = new Depositor[1];
		deps[0] = d1;
		Employee e1 = new Employee("Reagan", "Norris", "reagannorris@mybankmail.com", "password", "rnorris2", "08/17/1996", deps);
		assertEquals("password", e1.getPassword());
		try {
			e1.setPassword(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Password must be at least 8 characters", e.getMessage());
			assertEquals("password", e1.getPassword());
		}
		
		try {
			e1.setPassword("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Password must be at least 8 characters", e.getMessage());
			assertEquals("password", e1.getPassword());
		}
		
		try {
			e1.setPassword(" ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Password must be at least 8 characters", e.getMessage());
			assertEquals("password", e1.getPassword());
		}
		
		try {
			e1.setPassword("passwor");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Password must be at least 8 characters", e.getMessage());
			assertEquals("password", e1.getPassword());
		}
		
		
		try {
			e1.setPassword("Password");
			assertEquals("Password", e1.getPassword());
			e1.setPassword("passWord");
			assertEquals("passWord", e1.getPassword());
			e1.setPassword("passwordpassword");
			assertEquals("passwordpassword", e1.getPassword());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testSetId() {
		Transaction t1 = new Transaction(-45.68, "Lowes", "01/13/2022");
		Transaction t2 = new Transaction(-19.87, "McDonalds", "12/01/2021");
		Transaction[] trans = new Transaction[2];
		trans[0] = t1;
		trans[1] = t2;
		Transaction t3 = new Transaction(400.00, "Deposit", "01/14/2022");
		Transaction t4 = new Transaction(-50.00, "Withdrawal", "01/12/2022");
		Transaction[] trans2 = new Transaction[2];
		trans2[0] = t3;
		trans2[1] = t4;
		Account a1 = new Account("My Checking Account", "01294839", 897.22, true, false, trans);
		Account a2 = new Account("Savings Account", "01020000", 900.00, false, true, trans2);
		Account[] accs = new Account[1];
		accs[0] = a1;
		Account[] accs2 = new Account[1];
		accs2[0] = a2;
		Depositor d1 = new Depositor("Henry", "Underwood", "underwood.henry@gmail.com", "password", "hunderwood3", "08/21/1981", accs);
		Depositor d2 = new Depositor("Macy", "Williams", "macywilliams2@yahoo.com", "password", "mwilliams6", "09/30/2000", accs2);
		Depositor[] deps = new Depositor[2];
		deps[0] = d1;
		deps[1] = d2;
		Employee e1 = new Employee("Jeremy", "Carter", "jeremycarter@mybankmail.com", "password", "jcarter3", "02/12/1981", deps);
		assertEquals("jcarter3", e1.getId());
		
		try {
			e1.setId(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("ID cannot be empty", e.getMessage());
			assertEquals("jcarter3", e1.getId());
		}
		
		try {
			e1.setId("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("ID cannot be empty", e.getMessage());
			assertEquals("jcarter3", e1.getId());
		}
		
		try {
			e1.setId(" ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("ID cannot be empty", e.getMessage());
			assertEquals("jcarter3", e1.getId());
		}
		
		try {
			e1.setId("jcarter4");
			assertEquals("jcarter4", e1.getId());
			e1.setId("jcarter7");
			assertEquals("jcarter7", e1.getId());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSetBirthday() {
		Transaction t1 = new Transaction(-78.21, "Longhorn Steakhouse", "01/01/2022");
		Transaction t2 = new Transaction(-34.22, "Walmart", "11/29/2021");
		Transaction[] trans = new Transaction[2];
		trans[0] = t1;
		trans[1] = t2;
		Account a1 = new Account("Checking", "01920300", 762.11, true, false, trans);
		Transaction t3 = new Transaction(-100.00, "Withdrawal", "01/11/2022");
		Transaction t4 = new Transaction(-50.00, "Withdrawal", "12/02/2021");
		Transaction[] trans2 = new Transaction[2];
		trans2[0] = t3;
		trans2[1] = t4;
		Account a2 = new Account("Savings", "83920394", 950.00, false, true, trans2);
		Account[] accs = new Account[1];
		Account[] accs2 = new Account[1];
		accs[0] = a1;
		accs2[0] = a2;
		Depositor d1 = new Depositor("Daniel", "May", "danielmay2@gmail.com", "password", "dmay7", "09/30/1990", accs);
		Depositor d2 = new Depositor("Natalie", "Morris", "nataliemorris@gmail.com", "password", "nmorris8", "09/18/1989", accs2);
		Depositor[] deps = new Depositor[2];
		deps[0] = d1;
		deps[1] = d2;
		Employee e1 = new Employee("Amanda", "Brian", "amandabriam@mybankmail.com", "password", "abrian5", "09/19/1999", deps);
		assertEquals("09/19/1999", e1.getBirthday());
		
		try {
			e1.setBirthday(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Enter a valid date in the format MM/DD/YYYY", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Enter a valid date in the format MM/DD/YYYY", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday(" ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Enter a valid date in the format MM/DD/YYYY", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("1/01/2021");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Enter a valid date in the format MM/DD/YYYY", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("01/1/2021");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Enter a valid date in the format MM/DD/YYYY", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("01/01/21");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Enter a valid date in the format MM/DD/YYYY", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("00/01/2021");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid month", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("13/01/2021");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid month", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("01/00/2021");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid day", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			d1.setBirthday("01/32/2021");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid day", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("01/01/1899");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Enter a year after 1900", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("01/01/2023");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid year", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("02/30/1978");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("02/29/1981");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("04/31/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("06/31/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("09/31/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		try {
			e1.setBirthday("11/31/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
			assertEquals("09/19/1999", e1.getBirthday());
		}
		
		
		
		
		try {
			e1.setBirthday("03/12/1997");
			assertEquals("03/12/1997", e1.getBirthday());
			e1.setBirthday("08/19/2001");
			assertEquals("08/19/2001", e1.getBirthday());
			e1.setBirthday("02/14/1978");
			assertEquals("02/14/1978", e1.getBirthday());
			e1.setBirthday("11/12/1969");
			assertEquals("11/12/1969", e1.getBirthday());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage()); 
		} 
		
	}
	
	@Test
	public void testSetDepositors() {
		Transaction t1 = new Transaction(-200.63, "Pacsun", "10/21/2021");
		Transaction t2 = new Transaction(-60.72, "Food Lion", "12/20/2021");
		Transaction t3 = new Transaction(-20.50, "Speedway", "12/01/2021");
		Transaction t4 = new Transaction(-2000.78, "Louis Vuitton", "11/02/2021");
		Transaction t5 = new Transaction(500.00, "Weekly Deposit", "11/29/2021");
		Transaction t6 = new Transaction(-200.00, "Withdrawal", "01/03/2022");
		Transaction t7 = new Transaction(-6.82, "Bojangles", "10/20/2021");
		Transaction t8 = new Transaction(-2156.46, "Best Buy", "01/20/2022");
		Transaction t9 = new Transaction(50.00, "Deposit", "12/03/2021");
		Transaction t10 = new Transaction(100.00, "Deposit", "01/14/2022");
		Transaction[] trans1 = new Transaction[2];
		trans1[0] =t1;
		trans1[1] =t2;
		Transaction[] trans2 = new Transaction[2];
		trans2[0] =t3;
		trans2[1] =t4;
		Transaction[] trans3 = new Transaction[2];
		trans3[0] =t5;
		trans3[1] =t6;
		Transaction[] trans4 = new Transaction[2];
		trans4[0] =t7;
		trans4[1] =t8;
		Transaction[] trans5 = new Transaction[2];
		trans5[0] =t9;
		trans5[1] =t10;
		Account a1= new Account ("checking account","75349876", 79.50, true, false, trans1);
		Account a2= new Account ("my checking account","98765432", 97.05, true, false, trans2);
		Account a3= new Account ("checking","12345678", 50.25, true, false, trans3);
		Account a4= new Account ("personal account","65748392", 18.71, true, false, trans4);
		Account a5 = new Account ("my account","10293837", 70.82, true, false, trans5);
		
		Account[] accs1 = new Account[1];
		accs1[0] = a1;
		Account[] accs2 = new Account[1];
		accs2[0] = a2;
		Account[] accs3 = new Account[1];
		accs3[0] = a3;
		Account[] accs4 = new Account[1];
		accs4[0] = a4;
		Account[] accs5 = new Account[1];
		accs5[0] = a5;
		
		
		Depositor d1 = new Depositor("James", "Richard", "JamesRichard@gmail.com", "passwords", "jrichards4", "05/12/1988", accs1);
		Depositor d2 = new Depositor("Chris", "Roberts", "ChrisRoberts@gmail.com", "passwords", "croberts5", "09/11/1999", accs2);
		Depositor d3 = new Depositor("Alexander", "Lee", "AlexanderLee@gmail.com", "passwords", "alee6", "10/01/2001", accs3);
		Depositor d4 = new Depositor("Jason", "Davis", "JasonDavis@gmail.com", "passwords", "jdavis10", "01/01/2005", accs4);
		Depositor d5= new Depositor("Jackson", "Strickland", "JacksonStrickland@gmail.com", "passwords", "JStrickland12", "04/22/1980", accs5);
		Depositor d6= null;
		Depositor d7= null;
		Depositor[] deps= new Depositor[7];
		deps[0] = d1;
		deps[1] = d2;
		deps[2] = d3;
		deps[3] = d4;
		deps[4] = d5;
		deps[5] = d6;
		deps[6] = d7;
		Employee e1 = new Employee ("Lilian", "Smith", "LilianSmith@gmail.com", "passwords", "lsmith4", "01/01/2000", deps); 
		assertEquals(5, e1.getDepositors().size());
		Transaction t11 = new Transaction(-80.00, "Withdrawal", "12/31/2021");
		Transaction t12 = new Transaction(100.00, "Deposit", "12/21/2021");
		Transaction t13 = new Transaction(-45.98, "Zaxby's", "12/01/2021");
		Transaction t14 = new Transaction(-52.34, "Adidas", "12/22/2021");
		Transaction[] trans6 = new Transaction[2];
		trans6[0] = t11;
		trans6[1] = t12;
		Transaction[] trans7 = new Transaction[2];
		trans7[0] = t13;
		trans7[1] = t14;
		Account a6 = new Account("Savings account", "09102030", 550.00, false, true, trans6);
		Account[] accs6 = new Account[1];
		accs6[0] = a6;
		Account a7 = new Account("My Checking Account", "01209304", 87.99, true, false, trans7);
		Account[] accs7 = new Account[1];
		accs7[0] = a7;
		d6 = new Depositor("Jessica", "Herring", "herringjessica@gmail.com", "password", "jherring6", "07/19/2000", accs6);
		deps[5] = d6;
		e1 = new Employee ("Lilian", "Smith", "LilianSmith@gmail.com", "passwords", "lsmith4", "01/01/2000", deps); 
		assertEquals(6, e1.getDepositors().size());
		d7 = new Depositor("Max", "Bennett", "maxbennet@yahoo.com", "password", "mbennet8", "12/04/1992", accs7);
		deps[6] = d7;
		e1 = new Employee ("Lilian", "Smith", "LilianSmith@gmail.com", "passwords", "lsmith4", "01/01/2000", deps); 
		assertEquals(7, e1.getDepositors().size());
		
		
		
		

		
		
		
		
		
		
		
	}
	
	@Test
	public void testHashCode() {
		Transaction t1 = new Transaction(-200.63, "Pacsun", "10/21/2021");
		Transaction t2 = new Transaction(-60.72, "Food Lion", "12/20/2021");
		Transaction t3 = new Transaction(-20.50, "Speedway", "12/01/2021");
		Transaction t4 = new Transaction(-2000.78, "Louis Vuitton", "11/02/2021");
		Transaction t5 = new Transaction(500.00, "Weekly Deposit", "11/29/2021");
		Transaction t6 = new Transaction(-200.00, "Withdrawal", "01/03/2022");
		Transaction t7 = new Transaction(-6.82, "Bojangles", "10/20/2021");
		Transaction t8 = new Transaction(-2156.46, "Best Buy", "01/20/2022");
		Transaction t9 = new Transaction(50.00, "Deposit", "12/03/2021");
		Transaction t10 = new Transaction(100.00, "Deposit", "01/14/2022");
		Transaction[] trans1 = new Transaction[2];
		trans1[0] =t1;
		trans1[1] =t2;
		Transaction[] trans2 = new Transaction[2];
		trans2[0] =t3;
		trans2[1] =t4;
		Transaction[] trans3 = new Transaction[2];
		trans3[0] =t5;
		trans3[1] =t6;
		Transaction[] trans4 = new Transaction[2];
		trans4[0] =t7;
		trans4[1] =t8;
		Transaction[] trans5 = new Transaction[2];
		trans5[0] =t9;
		trans5[1] =t10;
		Account a1= new Account ("checking account","75349876", 79.50, true, false, trans1);
		Account a2= new Account ("my checking account","98765432", 97.05, true, false, trans2);
		Account a3= new Account ("checking","12345678", 50.25, true, false, trans3);
		Account a4= new Account ("personal account","65748392", 18.71, true, false, trans4);
		Account a5 = new Account ("my account","10293837", 70.82, true, false, trans5);
		
		Account[] accs1 = new Account[1];
		accs1[0] = a1;
		Account[] accs2 = new Account[1];
		accs2[0] = a2;
		Account[] accs3 = new Account[1];
		accs3[0] = a3;
		Account[] accs4 = new Account[1];
		accs4[0] = a4;
		Account[] accs5 = new Account[1];
		accs5[0] = a5;
		
		
		Depositor d1 = new Depositor("James", "Richard", "JamesRichard@gmail.com", "passwords", "jrichards4", "05/12/1988", accs1);
		Depositor d2 = new Depositor("Chris", "Roberts", "ChrisRoberts@gmail.com", "passwords", "croberts5", "09/11/1999", accs2);
		Depositor d3 = new Depositor("Alexander", "Lee", "AlexanderLee@gmail.com", "passwords", "alee6", "10/01/2001", accs3);
		Depositor d4 = new Depositor("Jason", "Davis", "JasonDavis@gmail.com", "passwords", "jdavis10", "01/01/2005", accs4);
		Depositor d5= new Depositor("Jackson", "Strickland", "JacksonStrickland@gmail.com", "passwords", "JStrickland12", "04/22/1980", accs5);
		Depositor d6= null;
		Depositor d7= null;
		Depositor[] deps1 = new Depositor[1];
		Depositor[] deps2 = new Depositor[2];
		Depositor[] deps3 = new Depositor[2];
		Depositor[] deps4 = new Depositor[1];
		Depositor[] deps5 = new Depositor[1];
		Depositor[] deps6 = new Depositor[2];
		deps1[0] = d1;
		deps2[0] = d2;
		deps2[1] = d3;
		deps3[0] = d4;
		deps3[1] = d5;
		deps4[0] = d6;
		deps5[0] = d7;
		deps6[0] = d1;
		deps6[1] = d2;
		Employee e1 = new Employee ("Lilian", "Smith", "liliansmith@gmail.com", "passwords", "lsmith4", "01/01/2000", deps1);
		Employee e2 = new Employee("Lilian", "Martinez", "lilian.martinez@yahoo.com", "password", "lmartinez2", "09/18/1978", deps2);
		Employee e3 = new Employee("Johnny", "Lawson", "johnnylawson@yahoo.com", "password", "jlawson2", "10/17/1970", deps3);
		Employee e4 = new Employee("Miguel", "Garcia", "miguel.garcia2@gmail.com", "password", "mgarcia4", "12/19/1997", deps4);
		Employee e5 = new Employee("Rachel", "Ingram", "rachelingram@yahoo.com", "password", "ringram1", "03/17/1984", deps5);
		Employee e6 = new Employee("Amanda", "Richards", "amandarichards@yahoo.com", "password", "arichards8", "06/10/1988", deps6);
		Employee e7 = new Employee ("Lilian", "Smith", "liliansmith@gmail.com", "passwords", "lsmith4", "01/01/2000", deps1);
		assertNotEquals(e1.hashCode(), e2.hashCode());
		assertNotEquals(e1.hashCode(), e3.hashCode());
		assertNotEquals(e1.hashCode(), e4.hashCode());
		assertNotEquals(e1.hashCode(), e5.hashCode());
		assertNotEquals(e1.hashCode(), e6.hashCode());
		assertEquals(e1.hashCode(), e7.hashCode());
		
		assertNotEquals(e2.hashCode(), e3.hashCode());
		assertNotEquals(e2.hashCode(), e4.hashCode());
		assertNotEquals(e2.hashCode(), e5.hashCode());
		assertNotEquals(e2.hashCode(), e6.hashCode());
		assertNotEquals(e2.hashCode(), e7.hashCode());
		
		assertNotEquals(e3.hashCode(), e4.hashCode());
		assertNotEquals(e3.hashCode(), e5.hashCode());
		assertNotEquals(e3.hashCode(), e6.hashCode());
		assertNotEquals(e3.hashCode(), e7.hashCode());
		
		assertNotEquals(e4.hashCode(), e5.hashCode());
		assertNotEquals(e4.hashCode(), e6.hashCode());
		assertNotEquals(e4.hashCode(), e7.hashCode());
		
		assertNotEquals(e5.hashCode(), e6.hashCode());
		assertNotEquals(e5.hashCode(), e7.hashCode());
		
		assertNotEquals(e6.hashCode(), e7.hashCode());
	}
	
	@Test
	public void testEquals() {
		Transaction t1 = new Transaction(-200.63, "Pacsun", "10/21/2021");
		Transaction t2 = new Transaction(-60.72, "Food Lion", "12/20/2021");
		Transaction t3 = new Transaction(-20.50, "Speedway", "12/01/2021");
		Transaction t4 = new Transaction(-2000.78, "Louis Vuitton", "11/02/2021");
		Transaction t5 = new Transaction(500.00, "Weekly Deposit", "11/29/2021");
		Transaction t6 = new Transaction(-200.00, "Withdrawal", "01/03/2022");
		Transaction t7 = new Transaction(-6.82, "Bojangles", "10/20/2021");
		Transaction t8 = new Transaction(-2156.46, "Best Buy", "01/20/2022");
		Transaction t9 = new Transaction(50.00, "Deposit", "12/03/2021");
		Transaction t10 = new Transaction(100.00, "Deposit", "01/14/2022");
		Transaction[] trans1 = new Transaction[2];
		trans1[0] =t1;
		trans1[1] =t2;
		Transaction[] trans2 = new Transaction[2];
		trans2[0] =t3;
		trans2[1] =t4;
		Transaction[] trans3 = new Transaction[2];
		trans3[0] =t5;
		trans3[1] =t6;
		Transaction[] trans4 = new Transaction[2];
		trans4[0] =t7;
		trans4[1] =t8;
		Transaction[] trans5 = new Transaction[2];
		trans5[0] =t9;
		trans5[1] =t10;
		Account a1= new Account ("checking account","75349876", 79.50, true, false, trans1);
		Account a2= new Account ("my checking account","98765432", 97.05, true, false, trans2);
		Account a3= new Account ("checking","12345678", 50.25, true, false, trans3);
		Account a4= new Account ("personal account","65748392", 18.71, true, false, trans4);
		Account a5 = new Account ("my account","10293837", 70.82, true, false, trans5);
		
		Account[] accs1 = new Account[1];
		accs1[0] = a1;
		Account[] accs2 = new Account[1];
		accs2[0] = a2;
		Account[] accs3 = new Account[1];
		accs3[0] = a3;
		Account[] accs4 = new Account[1];
		accs4[0] = a4;
		Account[] accs5 = new Account[1];
		accs5[0] = a5;
		
		
		Depositor d1 = new Depositor("James", "Richard", "JamesRichard@gmail.com", "passwords", "jrichards4", "05/12/1988", accs1);
		Depositor d2 = new Depositor("Chris", "Roberts", "ChrisRoberts@gmail.com", "passwords", "croberts5", "09/11/1999", accs2);
		Depositor d3 = new Depositor("Alexander", "Lee", "AlexanderLee@gmail.com", "passwords", "alee6", "10/01/2001", accs3);
		Depositor d4 = new Depositor("Jason", "Davis", "JasonDavis@gmail.com", "passwords", "jdavis10", "01/01/2005", accs4);
		Depositor d5= new Depositor("Jackson", "Strickland", "JacksonStrickland@gmail.com", "passwords", "JStrickland12", "04/22/1980", accs5);
		Depositor d6= null;
		Depositor d7= null;
		Depositor[] deps1 = new Depositor[1];
		Depositor[] deps2 = new Depositor[2];
		Depositor[] deps3 = new Depositor[2];
		Depositor[] deps4 = new Depositor[1];
		Depositor[] deps5 = new Depositor[1];
		Depositor[] deps6 = new Depositor[2];
		deps1[0] = d1;
		deps2[0] = d2;
		deps2[1] = d3;
		deps3[0] = d4;
		deps3[1] = d5;
		deps4[0] = d6;
		deps5[0] = d7;
		deps6[0] = d1;
		deps6[1] = d2;
		Employee e1 = new Employee ("Lilian", "Smith", "liliansmith@gmail.com", "passwords", "lsmith4", "01/01/2000", deps1);
		Employee e2 = new Employee("Lilian", "Martinez", "lilian.martinez@yahoo.com", "password", "lmartinez2", "09/18/1978", deps2);
		Employee e3 = new Employee("Johnny", "Lawson", "johnnylawson@yahoo.com", "password", "jlawson2", "10/17/1970", deps3);
		Employee e4 = new Employee("Miguel", "Garcia", "miguel.garcia2@gmail.com", "password", "mgarcia4", "12/19/1997", deps4);
		Employee e5 = new Employee("Rachel", "Ingram", "rachelingram@yahoo.com", "password", "ringram1", "03/17/1984", deps5);
		Employee e6 = new Employee("Amanda", "Richards", "amandarichards@yahoo.com", "password", "arichards8", "06/10/1988", deps6);
		Employee e7 = new Employee ("Lilian", "Smith", "liliansmith@gmail.com", "passwords", "lsmith4", "01/01/2000", deps1);
		
		assertFalse(e1.equals(e2));
		assertFalse(e1.equals(e3));
		assertFalse(e1.equals(e4));
		assertFalse(e1.equals(e5));
		assertFalse(e1.equals(e6));
		assertTrue(e1.equals(e7));
		
		assertFalse(e2.equals(e3));
		assertFalse(e2.equals(e4));
		assertFalse(e2.equals(e5));
		assertFalse(e2.equals(e6));
		assertFalse(e2.equals(e7));
		
		assertFalse(e3.equals(e4));
		assertFalse(e3.equals(e5));
		assertFalse(e3.equals(e6));
		assertFalse(e3.equals(e7));
		
		assertFalse(e4.equals(e5));
		assertFalse(e4.equals(e6));
		assertFalse(e4.equals(e7));
		
		assertFalse(e5.equals(e6));
		assertFalse(e5.equals(e7));
		
		assertFalse(e6.equals(e7));
	}
	
	@Test
	public void testToString() {
		Transaction t1 = new Transaction(-200.63, "Pacsun", "10/21/2021");
		Transaction t2 = new Transaction(-60.72, "Food Lion", "12/20/2021");
		Transaction t3 = new Transaction(-20.50, "Speedway", "12/01/2021");
		Transaction t4 = new Transaction(-2000.78, "Louis Vuitton", "11/02/2021");
		Transaction t5 = new Transaction(500.00, "Weekly Deposit", "11/29/2021");
		Transaction t6 = new Transaction(-200.00, "Withdrawal", "01/03/2022");
		Transaction t7 = new Transaction(-6.82, "Bojangles", "10/20/2021");
		Transaction t8 = new Transaction(-2156.46, "Best Buy", "01/20/2022");
		Transaction t9 = new Transaction(50.00, "Deposit", "12/03/2021");
		Transaction t10 = new Transaction(100.00, "Deposit", "01/14/2022");
		Transaction[] trans1 = new Transaction[2];
		trans1[0] =t1;
		trans1[1] =t2;
		Transaction[] trans2 = new Transaction[2];
		trans2[0] =t3;
		trans2[1] =t4;
		Transaction[] trans3 = new Transaction[2];
		trans3[0] =t5;
		trans3[1] =t6;
		Transaction[] trans4 = new Transaction[2];
		trans4[0] =t7;
		trans4[1] =t8;
		Transaction[] trans5 = new Transaction[2];
		trans5[0] =t9;
		trans5[1] =t10;
		Account a1= new Account ("checking account","75349876", 79.50, true, false, trans1);
		Account a2= new Account ("my checking account","98765432", 97.05, true, false, trans2);
		Account a3= new Account ("checking","12345678", 50.25, true, false, trans3);
		Account a4= new Account ("personal account","65748392", 18.71, true, false, trans4);
		Account a5 = new Account ("my account","10293837", 70.82, true, false, trans5);
		
		Account[] accs1 = new Account[1];
		accs1[0] = a1;
		Account[] accs2 = new Account[1];
		accs2[0] = a2;
		Account[] accs3 = new Account[1];
		accs3[0] = a3;
		Account[] accs4 = new Account[1];
		accs4[0] = a4;
		Account[] accs5 = new Account[1];
		accs5[0] = a5;
		
		
		Depositor d1 = new Depositor("James", "Richard", "JamesRichard@gmail.com", "passwords", "jrichards4", "05/12/1988", accs1);
		Depositor d2 = new Depositor("Chris", "Roberts", "ChrisRoberts@gmail.com", "passwords", "croberts5", "09/11/1999", accs2);
		Depositor d3 = new Depositor("Alexander", "Lee", "AlexanderLee@gmail.com", "passwords", "alee6", "10/01/2001", accs3);
		Depositor d4 = new Depositor("Jason", "Davis", "JasonDavis@gmail.com", "passwords", "jdavis10", "01/01/2005", accs4);
		Depositor d5= new Depositor("Jackson", "Strickland", "JacksonStrickland@gmail.com", "passwords", "JStrickland12", "04/22/1980", accs5);
		Depositor d6= null;
		Depositor d7= null;
		Depositor[] deps1 = new Depositor[1];
		Depositor[] deps2 = new Depositor[2];
		Depositor[] deps3 = new Depositor[2];
		Depositor[] deps4 = new Depositor[1];
		Depositor[] deps5 = new Depositor[1];
		Depositor[] deps6 = new Depositor[2];
		deps1[0] = d1;
		deps2[0] = d2;
		deps2[1] = d3;
		deps3[0] = d4;
		deps3[1] = d5;
		deps4[0] = d6;
		deps5[0] = d7;
		deps6[0] = d1;
		deps6[1] = d2;
		Employee e1 = new Employee ("Lilian", "Smith", "liliansmith@gmail.com", "passwords", "lsmith4", "01/01/2000", deps1);
		Employee e2 = new Employee("Lilian", "Martinez", "lilian.martinez@yahoo.com", "password", "lmartinez2", "09/18/1978", deps2);
		Employee e3 = new Employee("Johnny", "Lawson", "johnnylawson@yahoo.com", "password", "jlawson2", "10/17/1970", deps3);
		Employee e4 = new Employee("Miguel", "Garcia", "miguel.garcia2@gmail.com", "password", "mgarcia4", "12/19/1997", deps4);
		Employee e5 = new Employee("Rachel", "Ingram", "rachelingram@yahoo.com", "password", "ringram1", "03/17/1984", deps5);
		Employee e6 = new Employee("Amanda", "Richards", "amandarichards@yahoo.com", "password", "arichards8", "06/10/1988", deps6);
		Employee e7 = new Employee ("Lilian", "Smith", "liliansmith@gmail.com", "passwords", "lsmith4", "01/01/2000", deps1);
		
		assertEquals("Lilian,Smith,liliansmith@gmail.com,passwords,lsmith4,01/01/2000", e1.toString());
		assertEquals("Lilian,Martinez,lilian.martinez@yahoo.com,password,lmartinez2,09/18/1978", e2.toString());
		assertEquals("Johnny,Lawson,johnnylawson@yahoo.com,password,jlawson2,10/17/1970", e3.toString());
		assertEquals("Miguel,Garcia,miguel.garcia2@gmail.com,password,mgarcia4,12/19/1997", e4.toString());
		assertEquals("Rachel,Ingram,rachelingram@yahoo.com,password,ringram1,03/17/1984", e5.toString());
		assertEquals("Amanda,Richards,amandarichards@yahoo.com,password,arichards8,06/10/1988", e6.toString());
		assertEquals("Lilian,Smith,liliansmith@gmail.com,passwords,lsmith4,01/01/2000", e7.toString());
	}

}
