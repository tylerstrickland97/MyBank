package mybank.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest {

	@Test
	public void testConstructor() {
		Transaction t1 = new Transaction(-45.98, "Food Lion", "11/14/2021");
		Transaction t2 = new Transaction(-362.11, "Car Payment", "12/02/2021");
		Transaction t3 = new Transaction(897.63, "Weekly Income", "01/03/2022");
		Transaction[] trans1 = new Transaction[3];
		trans1[0] = t1;
		trans1[1] = t2;
		trans1[2] = t3;
		
		
		Transaction t4 = new Transaction(-102.78, "Amazon", "10/11/2021");
		Transaction t5 = new Transaction(-89.55, "Nike", "12/23/2021");
		Transaction t6 = new Transaction(450.00, "Refund", "09/23/2021");
		Transaction[] trans2 = new Transaction[3];
		trans2[0] = t4;
		trans2[1] = t5;
		trans2[2] = t6;
		
		Account a1 = null;
		try {
			a1 = new Account("Checking account", "10203949", 56.78, true, false, trans1);
			assertEquals("Checking account", a1.getName());
			assertEquals("10203949", a1.getReference());
			assertEquals(56.78, a1.getBalance(), 0.00);
			assertTrue(a1.isChecking());
			assertFalse(a1.isSavings());
			assertEquals(3, a1.getTransactions().size());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
		Account a2 = null;
		try {
			a2 = new Account("Savings account", "01920039", 589.23, false, true, trans2);
			assertEquals("Savings account", a2.getName());
			assertEquals("01920039", a2.getReference());
			assertEquals(589.23, a2.getBalance(), 0.00);
			assertFalse(a2.isChecking());
			assertTrue(a2.isSavings());
			assertEquals(3, a2.getTransactions().size());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSetName() {
		Transaction t1 = new Transaction(-92.33, "Amazon", "12/22/2021");
		Transaction t2 = new Transaction(198.45, "Deposit", "11/15/2021");
		Transaction[] trans = new Transaction[2];
		trans[0] = t1;
		trans[1] = t2;
		Account a1 = new Account("Checking account", "10229304", 328.09, true, false, trans);
		assertEquals("Checking account", a1.getName());
		
		try {
			a1.setName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Name cannot be empty", e.getMessage());
			assertEquals("Checking account", a1.getName());
		}
		
		try {
			a1.setName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Name cannot be empty", e.getMessage());
			assertEquals("Checking account", a1.getName());
		}
		
		try {
			a1.setName(" ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Name cannot be empty", e.getMessage());
			assertEquals("Checking account", a1.getName());
		}
	}
	
	@Test
	public void testSetReference() {
		Transaction t1 = new Transaction(-24.56, "Withdrawal", "01/13/2022");
		Transaction t2 = new Transaction(-98.44, "Withdrawal", "12/24/2021");
		Transaction[] trans = new Transaction[2];
		trans[0] = t1;
		trans[1] = t2;
		Account a1 = new Account("Savings", "01001000", 898.22, false, true, trans);
		assertEquals("01001000", a1.getReference());
		
		try {
			a1.setReference(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid reference", e.getMessage());
			assertEquals("01001000", a1.getReference());
		}
		
		try {
			a1.setReference("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid reference", e.getMessage());
			assertEquals("01001000", a1.getReference());
		}
		
		try {
			a1.setReference(" ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid reference", e.getMessage());
			assertEquals("01001000", a1.getReference());
		}
		
		try {
			a1.setReference("0000000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid reference", e.getMessage());
			assertEquals("01001000", a1.getReference());
		}
		
		try {
			a1.setReference("000000000");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid reference", e.getMessage());
			assertEquals("01001000", a1.getReference());
		}
		
		try {
			a1.setReference("abcdefgh");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid reference", e.getMessage());
			assertEquals("01001000", a1.getReference());
		}
		
		try {
			a1.setReference("10290303");
			assertEquals("10290303", a1.getReference());
			assertEquals("Savings", a1.getName());
			assertEquals(898.22, a1.getBalance(), 0.00);
			assertTrue(a1.isSavings());
			assertFalse(a1.isChecking());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testSetBalance() {
		Transaction t1 = new Transaction(-12.56, "Sheetz", "01/17/2022");
		Transaction t2 = new Transaction(98.43, "Nike Refund", "01/01/2022");
		Transaction[] trans = new Transaction[2];
		trans[0] = t1;
		trans[1] = t2;
		
		Transaction t3 = new Transaction(-100.78, "Withdrawal", "10/23/2021");
		Transaction t4 = new Transaction(-98.44, "Withdrawal", "11/29/2021");
		Transaction[] trans2 = new Transaction[2];
		trans2[0] = t3;
		trans2[1] = t4;
		
		Account a1 = new Account("Checking account", "00001111", 124.59, true, false, trans);
		Account a2 = new Account("Savinsg account", "11110000", 397.69, false, true, trans2);
		
		try {
			a1.setBalance(43.55);
			assertEquals(43.55, a1.getBalance(), 0.00);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
		try {
			a1.setBalance(-97.55);
			assertEquals(-97.55, a1.getBalance(), 0.00);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
		try {
			a1.setBalance(400555.55);
			assertEquals(400555.55, a1.getBalance(), 0.00);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
		try {
			a2.setBalance(0.00);
			assertEquals(0.00, a2.getBalance(), 0.00);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
		try {
			a2.setBalance(-87.98);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Savings account cannot have negative balance", e.getMessage());
			assertEquals(0.00, a2.getBalance(), 0.00);
		}
		
		try {
			a2.setBalance(1000000.00);
			assertEquals(1000000.00, a2.getBalance(), 0.00);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSetTransactions() {
		Transaction t1 = null;
		Transaction t2 = new Transaction(-10.00, "Dollar General", "11/24/2021");
		Transaction t3 = new Transaction(-56.02, "Buffalo Wild Wings", "12/31/2021");
		Transaction t4 = new Transaction(800.00, "Weekly Deposit", "01/02/2022");
		Transaction t5 = new Transaction(-67.89, "Macy's", "01/09/2022");
		
		Transaction[] trans = new Transaction[5];
		trans[0] = t1;
		trans[1] = t2;
		trans[2] = t3;
		trans[3] = t4;
		trans[4] = t5;
		Account a1 = null;
		try {
			a1 = new Account("My Checking Account", "02930020", 56.78, true, false, trans);
			assertEquals(4, a1.getTransactions().size());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
		t1 = new Transaction(500.00, "Christmas Bonus", "12/21/2021");
		trans[0] = t1;
		try {
			a1 = new Account("My Checking Account", "02930020", 56.78, true, false, trans);
			assertEquals(5, a1.getTransactions().size());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testHashCode() {
		Transaction t1 = new Transaction(-102.34, "Outback Steakhouse", "11/13/2021");
		Transaction t2 = new Transaction(-14.56, "Walmart", "11/14/2021");
		Transaction[] trans1 = new Transaction[2];
		trans1[0] = t1;
		trans1[1] = t2;
		Account a1 = new Account("Checking Account", "00001111", 207.88, true, false, trans1);
		
		Transaction t3 = new Transaction(-58.34, "Denny's", "12/11/2021");
		Transaction t4 = new Transaction(1098.76, "Deposit", "01/15/2022");
		Transaction[] trans2 = new Transaction[2];
		trans2[0] = t3;
		trans2[1] = t4;
		Account a2 = new Account("Checking Account", "02939499", 890.34, true, false, trans2);
		
		Transaction t5 = new Transaction(-42.33, "Wendy's", "12/15/2021");
		Transaction t6 = new Transaction(-8.98, "McDonald's", "01/02/2022");
		Transaction[] trans3 = new Transaction[2];
		trans3[0] = t5;
		trans3[1] = t6;
		Account a3 = new Account("My Checking", "00203994", 1432.89, true, false, trans3);
		
		Transaction t7 = new Transaction(-35.69, "Target", "01/17/2022");
		Transaction t8 = new Transaction(900.00, "Deposit", "10/31/2021");
		Transaction[] trans4 = new Transaction[2];
		trans4[0] = t7;
		trans4[1] = t8;
		Account a4 = new Account("Jason's Checking Account", "11020111", 765.43, true, false, trans4);
		
		Transaction t9 = new Transaction(100.00, "Deposit", "11/19/2021");
		Transaction t10 = new Transaction(200.00, "Deposit", "12/03/2021");
		Transaction[] trans5 = new Transaction[2];
		trans5[0] = t9;
		trans5[1] = t10;
		Account a5 = new Account("Savings Account", "00221100", 890.00, false, true, trans5);
		
		Transaction t11 = new Transaction(-250.00, "Withdrawal", "01/10/2022");
		Transaction t12 = new Transaction(-61.32, "Dick's Sporting Goods", "10/22/2021");
		Transaction[] trans6 = new Transaction[2];
		trans6[0] = t11;
		trans6[1] = t12;
		Account a6 = new Account("My Checking Account", "65930299", 65.33, true, false, trans6);
		Account a7 = new Account("Checking Account", "00001111", 207.88, true, false, trans1);
		
		assertNotEquals(a1.hashCode(), a2.hashCode());
		assertNotEquals(a1.hashCode(), a3.hashCode());
		assertNotEquals(a1.hashCode(), a4.hashCode());
		assertNotEquals(a1.hashCode(), a5.hashCode());
		assertNotEquals(a1.hashCode(), a6.hashCode());
		assertEquals(a1.hashCode(), a7.hashCode());
		
		assertNotEquals(a2.hashCode(), a3.hashCode());
		assertNotEquals(a2.hashCode(), a4.hashCode());
		assertNotEquals(a2.hashCode(), a5.hashCode());
		assertNotEquals(a2.hashCode(), a6.hashCode());
		assertNotEquals(a2.hashCode(), a7.hashCode());
		
		assertNotEquals(a3.hashCode(), a4.hashCode());
		assertNotEquals(a3.hashCode(), a5.hashCode());
		assertNotEquals(a3.hashCode(), a6.hashCode());
		assertNotEquals(a3.hashCode(), a7.hashCode());
		
		assertNotEquals(a4.hashCode(), a5.hashCode());
		assertNotEquals(a4.hashCode(), a6.hashCode());
		assertNotEquals(a4.hashCode(), a7.hashCode());
		
		assertNotEquals(a5.hashCode(), a6.hashCode());
		assertNotEquals(a5.hashCode(), a7.hashCode());
		
		assertNotEquals(a6.hashCode(), a7.hashCode());
		
	}
	
	@Test
	public void testEquals() {
		Transaction t1 = new Transaction(-102.34, "Outback Steakhouse", "11/13/2021");
		Transaction t2 = new Transaction(-14.56, "Walmart", "11/14/2021");
		Transaction[] trans1 = new Transaction[2];
		trans1[0] = t1;
		trans1[1] = t2;
		Account a1 = new Account("Checking Account", "00001111", 207.88, true, false, trans1);
		
		Transaction t3 = new Transaction(-58.34, "Denny's", "12/11/2021");
		Transaction t4 = new Transaction(1098.76, "Deposit", "01/15/2022");
		Transaction[] trans2 = new Transaction[2];
		trans2[0] = t3;
		trans2[1] = t4;
		Account a2 = new Account("Checking Account", "02939499", 890.34, true, false, trans2);
		
		Transaction t5 = new Transaction(-42.33, "Wendy's", "12/15/2021");
		Transaction t6 = new Transaction(-8.98, "McDonald's", "01/02/2022");
		Transaction[] trans3 = new Transaction[2];
		trans3[0] = t5;
		trans3[1] = t6;
		Account a3 = new Account("My Checking", "00203994", 1432.89, true, false, trans3);
		
		Transaction t7 = new Transaction(-35.69, "Target", "01/17/2022");
		Transaction t8 = new Transaction(900.00, "Deposit", "10/31/2021");
		Transaction[] trans4 = new Transaction[2];
		trans4[0] = t7;
		trans4[1] = t8;
		Account a4 = new Account("Jason's Checking Account", "11020111", 765.43, true, false, trans4);
		
		Transaction t9 = new Transaction(100.00, "Deposit", "11/19/2021");
		Transaction t10 = new Transaction(200.00, "Deposit", "12/03/2021");
		Transaction[] trans5 = new Transaction[2];
		trans5[0] = t9;
		trans5[1] = t10;
		Account a5 = new Account("Savings Account", "00221100", 890.00, false, true, trans5);
		
		Transaction t11 = new Transaction(-250.00, "Withdrawal", "01/10/2022");
		Transaction t12 = new Transaction(-61.32, "Dick's Sporting Goods", "10/22/2021");
		Transaction[] trans6 = new Transaction[2];
		trans6[0] = t11;
		trans6[1] = t12;
		Account a6 = new Account("My Checking Account", "65930299", 65.33, true, false, trans6);
		Account a7 = new Account("Checking Account", "00001111", 207.88, true, false, trans1);
		
		assertFalse(a1.equals(a2));
		assertFalse(a1.equals(a3));
		assertFalse(a1.equals(a4));
		assertFalse(a1.equals(a5));
		assertFalse(a1.equals(a6));
		assertTrue(a1.equals(a7));
		
		assertFalse(a2.equals(a3));
		assertFalse(a2.equals(a4));
		assertFalse(a2.equals(a5));
		assertFalse(a2.equals(a6));
		assertFalse(a2.equals(a7));
		
		assertFalse(a3.equals(a4));
		assertFalse(a3.equals(a5));
		assertFalse(a3.equals(a6));
		assertFalse(a3.equals(a7));
		
		assertFalse(a4.equals(a5));
		assertFalse(a4.equals(a6));
		assertFalse(a4.equals(a7));
		
		assertFalse(a5.equals(a6));
		assertFalse(a5.equals(a7));
		
		assertFalse(a6.equals(a7));
	}
	
	@Test
	public void testToString() {
		Transaction t1 = new Transaction(-102.34, "Outback Steakhouse", "11/13/2021");
		Transaction t2 = new Transaction(-14.56, "Walmart", "11/14/2021");
		Transaction[] trans1 = new Transaction[2];
		trans1[0] = t1;
		trans1[1] = t2;
		Account a1 = new Account("Checking Account", "00001111", 207.88, true, false, trans1);
		
		Transaction t3 = new Transaction(-58.34, "Denny's", "12/11/2021");
		Transaction t4 = new Transaction(1098.76, "Deposit", "01/15/2022");
		Transaction[] trans2 = new Transaction[2];
		trans2[0] = t3;
		trans2[1] = t4;
		Account a2 = new Account("Checking Account", "02939499", 890.34, true, false, trans2);
		
		Transaction t5 = new Transaction(-42.33, "Wendy's", "12/15/2021");
		Transaction t6 = new Transaction(-8.98, "McDonald's", "01/02/2022");
		Transaction[] trans3 = new Transaction[2];
		trans3[0] = t5;
		trans3[1] = t6;
		Account a3 = new Account("My Checking", "00203994", 1432.89, true, false, trans3);
		
		Transaction t7 = new Transaction(-35.69, "Target", "01/17/2022");
		Transaction t8 = new Transaction(900.00, "Deposit", "10/31/2021");
		Transaction[] trans4 = new Transaction[2];
		trans4[0] = t7;
		trans4[1] = t8;
		Account a4 = new Account("Jason's Checking Account", "11020111", 765.43, true, false, trans4);
		
		Transaction t9 = new Transaction(100.00, "Deposit", "11/19/2021");
		Transaction t10 = new Transaction(200.00, "Deposit", "12/03/2021");
		Transaction[] trans5 = new Transaction[2];
		trans5[0] = t9;
		trans5[1] = t10;
		Account a5 = new Account("Savings Account", "00221100", 890.00, false, true, trans5);
		
		Transaction t11 = new Transaction(-250.00, "Withdrawal", "01/10/2022");
		Transaction t12 = new Transaction(-61.32, "Dick's Sporting Goods", "10/22/2021");
		Transaction[] trans6 = new Transaction[2];
		trans6[0] = t11;
		trans6[1] = t12;
		Account a6 = new Account("My Checking Account", "65930299", 65.33, true, false, trans6);
		Account a7 = new Account("Checking Account", "00001111", 207.88, true, false, trans1);
		
		assertEquals("Checking Account,00001111,207.88,true,false", a1.toString());
		assertEquals("Checking Account,02939499,890.34,true,false", a2.toString());
		assertEquals("My Checking,00203994,1432.89,true,false", a3.toString());
		assertEquals("Jason's Checking Account,11020111,765.43,true,false", a4.toString());
		assertEquals("Savings Account,00221100,890.00,false,true", a5.toString());
		assertEquals("My Checking Account,65930299,65.33,true,false", a6.toString());
		assertEquals("Checking Account,00001111,207.88,true,false", a7.toString());
	}

}
