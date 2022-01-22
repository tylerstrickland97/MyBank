package mybank.user;

import static org.junit.Assert.*;

import org.junit.Test;

import mybank.data.Account;
import mybank.data.Transaction;

public class DepositorTest {

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
		Account[] accs1 = new Account[2];
		accs1[0] = a1;
		accs1[1] = a2;
		Depositor d1 = null;
		try {
			d1 = new Depositor("John", "Smith", "johnsmith@gmail.com", "password", "jsmith2", "12/21/1988", accs1);
			assertEquals("John", d1.getFirst());
			assertEquals("Smith", d1.getLast());
			assertEquals("johnsmith@gmail.com", d1.getEmail());
			assertEquals("password", d1.getPassword());
			assertEquals("jsmith2", d1.getId());
			assertEquals("12/21/1988", d1.getBirthday());
			assertEquals(2, d1.getAccounts().size());
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
		Account[] accs2 = new Account[2];
		accs2[0] = a3;
		accs2[1] = a4;
		Depositor d2 = null;
		try {
			d2 = new Depositor("Mary", "Lane", "marylane@yahoo.com", "password", "mlane4", "05/06/1970", accs2);
			assertEquals("Mary", d2.getFirst());
			assertEquals("Lane", d2.getLast());
			assertEquals("marylane@yahoo.com", d2.getEmail());
			assertEquals("password", d2.getPassword());
			assertEquals("mlane4", d2.getId());
			assertEquals("05/06/1970", d2.getBirthday());
			assertEquals(2, d2.getAccounts().size());
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
		Account[] accs2 = new Account[2];
		Depositor d2 = new Depositor("Mary", "Lane", "marylane@yahoo.com", "password", "mlane4", "05/06/1970", accs2);
		
		assertEquals("Mary", d2.getFirst());
		try {
			d2.setFirst(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("First name cannot be empty", e.getMessage());
			assertEquals("Mary", d2.getFirst());
		}
		
		try {
			d2.setFirst("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("First name cannot be empty", e.getMessage());
			assertEquals("Mary", d2.getFirst());
		
		}
		
		try {
			d2.setFirst(" ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("First name cannot be empty", e.getMessage());
			assertEquals("Mary", d2.getFirst());
		}
		
		try {
			d2.setFirst("Jamie");
			assertEquals("Jamie", d2.getFirst());
			d2.setFirst("Lauren");
			assertEquals("Lauren", d2.getFirst());
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
		accs1[1] = a2;
		Depositor d1 = new Depositor("Richard", "Hill", "richard.hill@gmail.com", "password", "rhill2", "03/14/1976", accs1);
		assertEquals("Hill", d1.getLast());
		try {
			d1.setLast(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Last name cannot be empty", e.getMessage());
			assertEquals("Hill", d1.getLast());
		}
		try {
			d1.setLast("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Last name cannot be empty", e.getMessage());
			assertEquals("Hill", d1.getLast());
		}
		try {
			d1.setLast(" ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Last name cannot be empty", e.getMessage());
			assertEquals("Hill", d1.getLast());
		}
		
		try {
			d1.setLast("Davis");
			assertEquals("Davis", d1.getLast());
			d1.setLast("Hamilton");
			assertEquals("Hamilton", d1.getLast());
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
		assertEquals("lizziethompson4@yahoo.com", d1.getEmail());
		
		try {
			d1.setEmail(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Email cannot be empty", e.getMessage());
			assertEquals("lizziethompson4@yahoo.com", d1.getEmail());
		}
		
		try {
			d1.setEmail("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Email cannot be empty", e.getMessage());
			assertEquals("lizziethompson4@yahoo.com", d1.getEmail());
		}
		
		try {
			d1.setEmail(" ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Email cannot be empty", e.getMessage());
			assertEquals("lizziethompson4@yahoo.com", d1.getEmail());
		}
		
		try {
			d1.setEmail("elizabeththompson.@gmailcom");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid email", e.getMessage());
			assertEquals("lizziethompson4@yahoo.com", d1.getEmail());
		}
		
		try {
			d1.setEmail("elizabeththompsongmail.com");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid email", e.getMessage());
			assertEquals("lizziethompson4@yahoo.com", d1.getEmail());
		}
		
		try {
			d1.setEmail("elizabeththompson@gmailcom");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid email", e.getMessage());
			assertEquals("lizziethompson4@yahoo.com", d1.getEmail());
		}
		
		try {
			d1.setEmail("elizabeththompson@gmail.com");
			assertEquals("elizabeththompson@gmail.com", d1.getEmail());
			d1.setEmail("elizabeththompson@yahoo.com");
			assertEquals("elizabeththompson@yahoo.com", d1.getEmail());
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
		Account a1 = new Account("Checking Account", "00001111", 450.92, true, false, trans);
		Account[] accs = new Account[1];
		accs[0] = a1;
		Depositor d1 = new Depositor("Mason", "Edwards", "masonedwards@gmail.com", "password", "medwards6", "04/19/2000", accs);
		assertEquals("password", d1.getPassword());
		try {
			d1.setPassword(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Password must be at least 8 characters", e.getMessage());
			assertEquals("password", d1.getPassword());
		}
		
		try {
			d1.setPassword("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Password must be at least 8 characters", e.getMessage());
			assertEquals("password", d1.getPassword());
		}
		
		try {
			d1.setPassword(" ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Password must be at least 8 characters", e.getMessage());
			assertEquals("password", d1.getPassword());
		}
		
		try {
			d1.setPassword("passwor");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Password must be at least 8 characters", e.getMessage());
			assertEquals("password", d1.getPassword());
		}
		
		try {
			d1.setPassword("Password");
			assertEquals("Password", d1.getPassword());
			d1.setPassword("PassWord");
			assertEquals("PassWord", d1.getPassword());
			d1.setPassword("passwordpassword");
			assertEquals("passwordpassword", d1.getPassword());
			d1.setPassword("thisisapassword");
			assertEquals("thisisapassword", d1.getPassword());
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
		Account a1 = new Account("My Checking Account", "01294839", 897.22, true, false, trans);
		Account[] accs = new Account[1];
		accs[0] = a1;
		Depositor d1 = new Depositor("Henry", "Underwood", "underwood.henry@gmail.com", "password", "hunderwood3", "08/21/1981", accs);
		assertEquals("hunderwood3", d1.getId());
		
		try {
			d1.setId(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("ID cannot be empty", e.getMessage());
			assertEquals("hunderwood3", d1.getId());
		}
		
		try {
			d1.setId("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("ID cannot be empty", e.getMessage());
			assertEquals("hunderwood3", d1.getId());
		}
		
		try {
			d1.setId(" ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("ID cannot be empty", e.getMessage());
			assertEquals("hunderwood3", d1.getId());
		}
		
		try {
			d1.setId("hunderwood4");
			assertEquals("hunderwood4", d1.getId());
			d1.setId("hunderwoo2");
			assertEquals("hunderwoo2", d1.getId());
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
		Account[] accs = new Account[2];
		accs[0] = a1;
		accs[1] = a2;
		Depositor d1 = new Depositor("Daniel", "May", "danielmay2@gmail.com", "password", "dmay7", "09/30/1990", accs);
		assertEquals("09/30/1990", d1.getBirthday());
		
		try {
			d1.setBirthday(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Enter a valid date in the format MM/DD/YYYY", e.getMessage());
		}
		
		try {
			d1.setBirthday("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Enter a valid date in the format MM/DD/YYYY", e.getMessage());
		}
		
		try {
			d1.setBirthday(" ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Enter a valid date in the format MM/DD/YYYY", e.getMessage());
		}
		
		try {
			d1.setBirthday("1/01/2021");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Enter a valid date in the format MM/DD/YYYY", e.getMessage());
		}
		
		try {
			d1.setBirthday("01/1/2021");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Enter a valid date in the format MM/DD/YYYY", e.getMessage());
		}
		
		try {
			d1.setBirthday("01/01/21");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Enter a valid date in the format MM/DD/YYYY", e.getMessage());
		}
		
		try {
			d1.setBirthday("00/01/2021");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid month", e.getMessage());
		}
		
		try {
			d1.setBirthday("13/01/2021");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid month", e.getMessage());
		}
		
		try {
			d1.setBirthday("01/00/2021");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid day", e.getMessage());
		}
		
		try {
			d1.setBirthday("01/32/2021");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid day", e.getMessage());
		}
		
		try {
			d1.setBirthday("01/01/1899");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Enter a year after 1900", e.getMessage());
		}
		
		try {
			d1.setBirthday("01/01/2023");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid year", e.getMessage());
		}
		
		try {
			d1.setBirthday("02/30/1978");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		
		try {
			d1.setBirthday("02/29/1981");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		
		try {
			d1.setBirthday("04/31/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		
		try {
			d1.setBirthday("06/31/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		
		try {
			d1.setBirthday("09/31/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		
		try {
			d1.setBirthday("11/31/2000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date", e.getMessage());
		}
		
		
		
		
		try {
			d1.setBirthday("03/12/1997");
			assertEquals("03/12/1997", d1.getBirthday());
			d1.setBirthday("08/19/2001");
			assertEquals("08/19/2001", d1.getBirthday());
			d1.setBirthday("02/14/1978");
			assertEquals("02/14/1978", d1.getBirthday());
			d1.setBirthday("11/12/1969");
			assertEquals("11/12/1969", d1.getBirthday());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage()); 
		} 
	}
	
	@Test
	public void testSetAccounts() {
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
		Transaction[] trans2 = new Transaction[2];
		Transaction[] trans3 = new Transaction[2];
		Transaction[] trans4 = new Transaction[2];
		Transaction[] trans5 = new Transaction[2];
		trans1[0] = t1;
		trans1[1] = t2;
		trans2[0] = t3;
		trans2[1] = t4;
		trans3[0] = t5;
		trans3[1] = t6;
		trans4[0] = t7;
		trans4[1] = t8;
		trans5[0] = t9;
		trans5[1] = t10;
		Account a1 = new Account("Checking Account", "01992030", 450.67, true, false, trans1);
		Account a2 = new Account("Checking Account 2", "01990020", 198.77, true, false, trans2);
		Account a3 = null;
		Account a4 = new Account("Savings Account", "01920300", 350.00, false, true, trans3);
		Account a5 = null;
		Account[] accs = new Account[5];
		accs[0] = a1;
		accs[1] = a2;
		accs[2] = a3;
		accs[3] = a4;
		accs[4] = a5;
		Depositor d = new Depositor("Jerry", "Strickland", "jerrystrickland1@yahoo.com", "password", "jstrickland2", "04/02/1991", accs);
		assertEquals(3, d.getAccounts().size());
		a3 = new Account("Checking Account 3", "01002000", 678.12, true, false, trans4);
		accs[2] = a3;
		d = new Depositor("Jerry", "Strickland", "jerrystrickland1@yahoo.com", "password", "jstrickland2", "04/02/1991", accs);
		assertEquals(4, d.getAccounts().size());
		a5 = new Account("Savings Account 2", "04901900", 909.21, false, true, trans5);
		accs[4] = a5;
		d = new Depositor("Jerry", "Strickland", "jerrystrickland1@yahoo.com", "password", "jstrickland2", "04/02/1991", accs);
		assertEquals(5, d.getAccounts().size());
		
		
	}
	
	@Test
	public void testToString() {
		Transaction t1 = new Transaction(500.00, "Weekly Deposit", "11/29/2021");
		Transaction t2 = new Transaction(-2.34, "McDonald's", "01/03/2022");
		Transaction t3 = new Transaction(-114.02, "Nike", "01/02/2022");
		Transaction t4 = new Transaction(-87.65, "Dillard's", "12/12/2021");
		Transaction t5 = new Transaction(-28.97, "Walmart", "01/08/2022");
		Transaction t6 = new Transaction(-50.00, "Withdrawal", "12/03/2021");
		Transaction t7 = new Transaction(100.00, "Deposit", "01/03/2022");
		Transaction t8 = new Transaction(-635.09, "Ticketmaster", "01/21/2022");
		Transaction t9 = new Transaction(-15.66, "Amazon", "01/05/2022");
		Transaction t10 = new Transaction(-7.26, "Starbucks", "01/18/2022");
		Transaction t11 = new Transaction(-12.40, "Cookout", "01/13/2022");
		Transaction t12 = new Transaction(-80.00, "Withdrawal", "12/23/2021");
		Transaction t13 = new Transaction(-40.00, "Withdrawal", "01/04/2022");
		Transaction t14 = new Transaction(500.00, "Deposit", "01/15/2022");
		Transaction t15 = new Transaction(200.00, "Deposit", "01/18/2022");
		Transaction t16 = new Transaction(-5.39, "Coldstone", "01/06/2022");
		Transaction t17 = new Transaction(-9.80, "Wendy's", "01/07/2022");
		Transaction t18 = new Transaction(-40.23, "American Eagle", "12/23/2021");
		Transaction t19 = new Transaction(-5.98, "Dollar General", "12/22/2021");
		Transaction t20 = new Transaction(-19.24, "Sam's Club", "12/27/2021");
		Transaction[] trans1 = new Transaction[4];
		trans1[0] = t1;
		trans1[1] = t2;
		trans1[2] = t3;
		trans1[3] = t4;
		
		Transaction[] trans2 = new Transaction[4];
		trans2[0] = t5;
		trans2[1] = t6;
		trans2[2] = t7;
		trans2[3] = t8;
		
		Transaction[] trans3 = new Transaction[4];
		trans3[0] = t9;
		trans3[1] = t10;
		trans3[2] = t11;
		trans3[3] = t16;
		
		Transaction[] trans4 = new Transaction[4];
		trans4[0] = t13;
		trans4[1] = t14;
		trans4[2] = t15;
		trans4[3] = t12;
		
		Transaction[] trans5 = new Transaction[4];
		trans5[0] = t17;
		trans5[1] = t18;
		trans5[2] = t19;
		trans5[3] = t20;
		
		Account a1 = new Account("Checking Account", "01990000", 56.23, true, false, trans1);
		Account a2 = new Account("My Other Account", "19203000", 123.45, true, false, trans2);
		Account a3 = new Account("Side Account", "91203485", 98.75, true, false, trans3);
		Account a4 = new Account("Savings Account", "10293998", 500.00, false, true, trans4);
		Account a5 = new Account("My Checking Account", "10293829", 234.56, true, false, trans5);
		Account[] accs1 = new Account[2];
		Account[] accs2 = new Account[2];
		Account[] accs3 = new Account[1];
		accs1[0] = a1;
		accs1[1] = a3;
		accs2[0] = a5;
		accs2[1] = a2;
		accs3[0] = a4;
		Depositor d1 = new Depositor("Tristan", "Johnson", "tristanjohnson@gmail.com", "password", "tjohnson2", "03/16/1998", accs1);
		Depositor d2 = new Depositor("Lauren", "Hill", "laurenhill@yahoo.com", "password", "lhill4", "05/30/1999", accs2);
		Depositor d3 = new Depositor("Jeff", "Yates", "jeffyates3@gmail.com", "password", "jyates3", "01/17/1987", accs3);
		
		assertEquals("Tristan,Johnson,tristanjohnson@gmail.com,password,tjohnson2,03/16/1998", d1.toString());
		assertEquals("Lauren,Hill,laurenhill@yahoo.com,password,lhill4,05/30/1999", d2.toString());
		assertEquals("Jeff,Yates,jeffyates3@gmail.com,password,jyates3,01/17/1987", d3.toString());
		
		
	}
	
	@Test
	public void testHashCode() {
		Transaction t1 = new Transaction(500.00, "Weekly Deposit", "11/29/2021");
		Transaction t2 = new Transaction(-2.34, "McDonald's", "01/03/2022");
		Transaction t3 = new Transaction(-114.02, "Nike", "01/02/2022");
		Transaction t4 = new Transaction(-87.65, "Dillard's", "12/12/2021");
		Transaction t5 = new Transaction(-30.45, "Walmart", "01/08/2022");
		Transaction t6 = new Transaction(-20.00, "Withdrawal", "01/03/2022");
		Transaction t7 = new Transaction(800.00, "Deposit", "01/03/2022");
		Transaction t8 = new Transaction(-635.09, "Ticketmaster", "01/21/2022");
		Transaction t9 = new Transaction(-20.56, "Amazon", "01/03/2022");
		Transaction t10 = new Transaction(-7.26, "Starbucks", "01/18/2022");
		Transaction t11 = new Transaction(-12.40, "Cookout", "01/13/2022");
		Transaction t12 = new Transaction(-200.00, "Withdrawal", "01/03/2022");
		Transaction t13 = new Transaction(-100.00, "Withdrawal", "01/03/2022");
		Transaction t14 = new Transaction(300.00, "Deposit", "01/03/2022");
		Transaction t15 = new Transaction(150.00, "Deposit", "01/03/2022");
		Transaction t16 = new Transaction(-5.39, "Coldstone", "01/06/2022");
		Transaction t17 = new Transaction(-9.80, "Wendy's", "01/07/2022");
		Transaction t18 = new Transaction(-40.23, "American Eagle", "12/23/2021");
		Transaction t19 = new Transaction(-5.98, "Dollar General", "12/22/2021");
		Transaction t20 = new Transaction(-19.24, "Sam's Club", "12/27/2021");
		Transaction[] trans1 = new Transaction[4];
		trans1[0] = t1;
		trans1[1] = t2;
		trans1[2] = t3;
		trans1[3] = t4;
		
		Transaction[] trans2 = new Transaction[4];
		trans2[0] = t5;
		trans2[1] = t6;
		trans2[2] = t7;
		trans2[3] = t8;
		
		Transaction[] trans3 = new Transaction[4];
		trans3[0] = t9;
		trans3[1] = t10;
		trans3[2] = t11;
		trans3[3] = t16;
		
		Transaction[] trans4 = new Transaction[4];
		trans4[0] = t13;
		trans4[1] = t14;
		trans4[2] = t15;
		trans4[3] = t12;
		
		Transaction[] trans5 = new Transaction[4];
		trans5[0] = t17;
		trans5[1] = t18;
		trans5[2] = t19;
		trans5[3] = t20;
		
		Account a1 = new Account("Checking Account", "01029900", 398.23, true, false, trans1);
		Account a2 = new Account("Checking Account", "01920000", 690.12, true, false, trans2);
		Account a3 = new Account("My Account", "01002030", 903.14, true, false, trans3);
		Account a4 = new Account("Savings", "09102000", 850.00, false, true, trans4);
		Account a5 = new Account("Spending Account", "92001948", 67.23, true, false, trans5);
		Account[] accs1 = new Account[2];
		Account[] accs2 = new Account[2];
		Account[] accs3 = new Account[1];
		accs1[0] = a1;
		accs1[1] = a5;
		accs2[0] = a2;
		accs2[1] = a4;
		accs3[0] = a3;
		Depositor d1 = new Depositor("James", "Michaels", "jamesmichaels2@gmail.com", "password", "jmichaels2", "06/07/2000", accs1);
		Depositor d2 = new Depositor("Mary", "Henderson", "mary.henderson@gmail.com", "password", "mhenderson3", "09/18/1988", accs2);
		Depositor d3 = new Depositor("Barry", "Roberts", "barryroberts@gmail.com", "password", "broberts8", "09/08/1971", accs3);
		Depositor d4 = new Depositor("James", "Michaels", "jamesmichaels2@gmail.com", "password", "jmichaels2", "06/07/2000", accs1);
		Depositor d5 = new Depositor("James", "Michaels", "jamesmichaels2@gmail.com", "password", "jmichaels2", "06/07/2000", accs2);
		assertNotEquals(d1.hashCode(), d2.hashCode());
		assertNotEquals(d1.hashCode(), d3.hashCode());
		assertEquals(d1.hashCode(), d4.hashCode());
		assertNotEquals(d1.hashCode(), d5.hashCode());
		assertNotEquals(d2.hashCode(), d3.hashCode());
		assertNotEquals(d2.hashCode(), d4.hashCode());
		assertNotEquals(d2.hashCode(), d5.hashCode());
		assertNotEquals(d3.hashCode(), d4.hashCode());
		assertNotEquals(d3.hashCode(), d5.hashCode());
		assertNotEquals(d4.hashCode(), d5.hashCode());
		
		
	}
	
	@Test
	public void testEquals() {
		Transaction t1 = new Transaction(-6.35, "Starbucks", "11/29/2021");
		Transaction t2 = new Transaction(213.59, "Deposit", "01/14/2022");
		Transaction t3 = new Transaction(-114.02, "Nike", "01/02/2022");
		Transaction t4 = new Transaction(-11.37, "Chipotle", "12/12/2021");
		Transaction t5 = new Transaction(-374.37, "Deposit", "12/30/2021");
		Transaction t6 = new Transaction(-2.34, "Withdrawal", "01/03/2022");
		Transaction t7 = new Transaction(362.86, "Deposit", "11/20/2021");
		Transaction t8 = new Transaction(-26.59, "Staples", "01/10/2022");
		Transaction t9 = new Transaction(-75.39, "Cheesecake Factory", "01/03/2022");
		Transaction t10 = new Transaction(-29.87, "Urban Outfitters", "12/31/2021");
		Transaction t11 = new Transaction(-12.40, "Cookout", "01/13/2022");
		Transaction t12 = new Transaction(-200.00, "Withdrawal", "01/03/2022");
		Transaction t13 = new Transaction(-100.00, "Withdrawal", "01/03/2022");
		Transaction t14 = new Transaction(300.00, "Deposit", "01/03/2022");
		Transaction t15 = new Transaction(150.00, "Deposit", "01/03/2022");
		Transaction t16 = new Transaction(-5.39, "Coldstone", "01/06/2022");
		Transaction t17 = new Transaction(-9.80, "Wendy's", "01/07/2022");
		Transaction t18 = new Transaction(-40.23, "American Eagle", "12/23/2021");
		Transaction t19 = new Transaction(-5.98, "Dollar General", "12/22/2021");
		Transaction t20 = new Transaction(-19.24, "Sam's Club", "12/27/2021");
		Transaction[] trans1 = new Transaction[4];
		trans1[0] = t1;
		trans1[1] = t2;
		trans1[2] = t3;
		trans1[3] = t4;
		
		Transaction[] trans2 = new Transaction[4];
		trans2[0] = t5;
		trans2[1] = t6;
		trans2[2] = t7;
		trans2[3] = t8;
		
		Transaction[] trans3 = new Transaction[4];
		trans3[0] = t9;
		trans3[1] = t10;
		trans3[2] = t11;
		trans3[3] = t16;
		
		Transaction[] trans4 = new Transaction[4];
		trans4[0] = t13;
		trans4[1] = t14;
		trans4[2] = t15;
		trans4[3] = t12;
		
		Transaction[] trans5 = new Transaction[4];
		trans5[0] = t17;
		trans5[1] = t18;
		trans5[2] = t19;
		trans5[3] = t20;
		
		Account a1 = new Account("Checking Account", "01029900", 398.23, true, false, trans1);
		Account a2 = new Account("Checking Account", "01920000", 690.12, true, false, trans2);
		Account a3 = new Account("My Account", "01002030", 903.14, true, false, trans3);
		Account a4 = new Account("Savings", "09102000", 850.00, false, true, trans4);
		Account a5 = new Account("Spending Account", "92001948", 67.23, true, false, trans5);
		Account[] accs1 = new Account[2];
		Account[] accs2 = new Account[2];
		Account[] accs3 = new Account[1];
		accs1[0] = a1;
		accs1[1] = a5;
		accs2[0] = a2;
		accs2[1] = a4;
		accs3[0] = a3;
		Depositor d1 = new Depositor("James", "Michaels", "jamesmichaels2@gmail.com", "password", "jmichaels2", "06/07/2000", accs1);
		Depositor d2 = new Depositor("Mary", "Henderson", "mary.henderson@gmail.com", "password", "mhenderson3", "09/18/1988", accs2);
		Depositor d3 = new Depositor("Barry", "Roberts", "barryroberts@gmail.com", "password", "broberts8", "09/08/1971", accs3);
		Depositor d4 = new Depositor("James", "Michaels", "jamesmichaels2@gmail.com", "password", "jmichaels2", "06/07/2000", accs1);
		Depositor d5 = new Depositor("James", "Michaels", "jamesmichaels2@gmail.com", "password", "jmichaels2", "06/07/2000", accs2);
	
		assertFalse(d1.equals(d2));
		assertFalse(d1.equals(d3));
		assertTrue(d1.equals(d4));
		assertFalse(d1.equals(d5));
		assertFalse(d2.equals(d3));
		assertFalse(d2.equals(d4));
		assertFalse(d2.equals(d5));
		assertFalse(d3.equals(d4));
		assertFalse(d3.equals(d5));
		assertFalse(d4.equals(d5));
	}
	

}
