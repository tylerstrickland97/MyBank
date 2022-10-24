package mybank.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import mybank.data.Account;
import mybank.data.Transaction;
import mybank.user.Depositor;
import mybank.user.Employee;

public class BankManagerTest {

    @Test
    public void testLoadEmployee () {
        BankManager manager = new BankManager();
        manager.loadEmployees( "input/io_test_file.txt" );
        assertEquals( 10, manager.getEmployees().size() );
    }

    @Test
    public void testLogin () {
        BankManager manager = new BankManager();
        assertNull( manager.getCurrentUser() );

        manager.loadEmployees( "input/io_test_file.txt" );

        assertTrue( manager.login( "rquinn3", "password" ) );
        assertNotNull( manager.getCurrentUser() );
        assertTrue( manager.getCurrentUser() instanceof Employee );
        assertFalse( manager.login( "mjohnson8", "password" ) );
        assertEquals( "rquinn3", manager.getCurrentUser().getId() );
        manager.logout();
        assertNull( manager.getCurrentUser() );
        assertTrue( manager.login( "mjohnson8", "password" ) );
        assertNotNull( manager.getCurrentUser() );
        assertEquals( "mjohnson8", manager.getCurrentUser().getId() );

        manager.logout();
        assertNull( manager.getCurrentUser() );
        assertTrue( manager.login( "cwatts3", "password" ) );
        assertNotNull( manager.getCurrentUser() );
        assertTrue( manager.getCurrentUser() instanceof Depositor );
    }

    @Test
    public void testAddNewDepositor () {
        BankManager manager = new BankManager();
        manager.loadEmployees( "input/io_test_file.txt" );

        manager.login( "rquinn3", "password" );
        assertEquals( "rquinn3", manager.getCurrentUser().getId() );
        assertTrue( manager.getCurrentUser() instanceof Employee );

        Employee e = (Employee) manager.getCurrentUser();
        assertEquals( 4, e.getDepositors().size() );

        try {
            manager.addNewDepositor( "James", "Martin", "jamesmartin@yahoo.com", "password", "jmartin4", "12/23/1992" );
        }
        catch ( Exception excpt ) {
            fail( excpt.getMessage() );
        }

        assertEquals( 5, e.getDepositors().size() );

        try {
            manager.addNewDepositor( "James", "Martin", "jamesmartin@yahoo.com", "password", "jmartin4", "12/23/1992" );
            fail();
        }
        catch ( IllegalArgumentException excpt ) {
            assertEquals( "Cannot add duplicate depositor", excpt.getMessage() );
        }

        manager.logout();
        assertNull( manager.getCurrentUser() );
        assertTrue( manager.login( "jmartin4", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Depositor );
        try {
            manager.addNewDepositor( "Bobby", "Holland", "bobbyholland@gmail.com", "password", "bholland3",
                    "02/23/1980" );
        }
        catch ( IllegalArgumentException excpt ) {
            assertEquals( "You do not have access to this feature", excpt.getMessage() );
            assertEquals( 5, e.getDepositors().size() );
        }
    }

    @Test
    public void testAddNewEmployee () {
        BankManager manager = new BankManager();
        manager.loadEmployees( "input/io_test_file.txt" );
        assertTrue( manager.login( "pstone8", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Depositor );
        assertEquals( 10, manager.getEmployees().size() );
        try {
            manager.addNewEmployee( "Jackie", "Chan", "jackiechan@mybankmail.com", "password", "jchan4", "01/13/1976" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "You do not have access to this feature", e.getMessage() );
            assertEquals( 10, manager.getEmployees().size() );
        }

        manager.logout();
        assertTrue( manager.login( "dhouser2", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Employee );
        try {
            manager.addNewEmployee( "Jackie", "Chan", "jackiechan@mybankmail.com", "password", "jchan4", "01/13/1976" );
            assertEquals( 11, manager.getEmployees().size() );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }

        try {
            manager.addNewEmployee( "Jackie", "Chan", "jackiechan@mybankmail.com", "password", "jchan4", "01/13/1976" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Can't add duplicate employee", e.getMessage() );
            assertEquals( 11, manager.getEmployees().size() );
        }
    }

    @Test
    public void testRemoveDepositor () {
        BankManager manager = new BankManager();
        manager.loadEmployees( "input/io_test_file.txt" );
        assertTrue( manager.login( "hjackson6", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Depositor );
        try {
            manager.removeDepositor( "amorley4" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "You do not have access to this feature", e.getMessage() );
            assertEquals( 4, manager.getEmployees().get( 0 ).getDepositors().size() );
        }

        manager.logout();
        assertNull( manager.getCurrentUser() );
        assertTrue( manager.login( "rquinn3", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Employee );
        Employee emp = (Employee) manager.getCurrentUser();
        assertEquals( 4, emp.getDepositors().size() );
        try {
            manager.removeDepositor( "amorley4" );
            assertEquals( 3, emp.getDepositors().size() );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }

        try {
            manager.removeDepositor( "tstrick2" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Depositor does not exist", e.getMessage() );
            assertEquals( 3, emp.getDepositors().size() );
        }

        try {
            manager.removeDepositor( "hjackson6" );
            assertEquals( 2, emp.getDepositors().size() );
            manager.removeDepositor( "pstone8" );
            assertEquals( 1, emp.getDepositors().size() );
            manager.removeDepositor( "gcoates2" );
            assertEquals( 0, emp.getDepositors().size() );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }

    }

    @Test
    public void testAddTransaction () {
        BankManager manager = new BankManager();
        manager.loadEmployees( "input/io_test_file.txt" );

        assertTrue( manager.login( "fdelgado3", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Depositor );

        Depositor d = (Depositor) manager.getCurrentUser();
        assertEquals( 1, d.getAccounts().size() );
        Account a = d.getAccounts().get( 0 );
        assertEquals( 2, a.getTransactions().size() );
        try {
            manager.addTransaction( "Food Lion", -23.00, "12/31/2021", a );
            assertEquals( 3, a.getTransactions().size() );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }

        try {
            manager.addTransaction( null, -100.00, "01/02/2022", a );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( 3, a.getTransactions().size() );
        }

        Account other = new Account( "Random Account", "10203303", 20.00, true, false, new Transaction[0] );
        try {
            manager.addTransaction( "Walmart", 12.45, "12/30/2021", other );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Account doesn't exist", e.getMessage() );
        }

        manager.logout();
        manager.login( "rquinn3", "password" );
        assertTrue( manager.getCurrentUser() instanceof Employee );
        try {
            manager.addTransaction( "Best Buy", 500.00, "12/29/2021", a );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "You do not have access to this feature", e.getMessage() );
            assertEquals( 3, a.getTransactions().size() );
        }
    }

    @Test
    public void testAddNewAccount () {
        BankManager manager = new BankManager();
        manager.loadEmployees( "input/io_test_file.txt" );
        assertTrue( manager.login( "jbyrd8", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Depositor );

        Depositor d = (Depositor) manager.getCurrentUser();
        assertEquals( 1, d.getAccounts().size() );
        try {
            manager.addNewAccount( "Savings", 500.00, false, true );
            assertEquals( 2, d.getAccounts().size() );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }

        manager.logout();
        assertTrue( manager.login( "mjohnson8", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Employee );
        Employee e = (Employee) manager.getCurrentUser();

        try {
            manager.addNewAccount( "New", 200.00, true, false );
            fail();
        }
        catch ( IllegalArgumentException excpt ) {
            assertEquals( "You do not have access to this feature", excpt.getMessage() );
        }

    }

    @Test
    public void testRemoveAccount () {
        BankManager manager = new BankManager();
        manager.loadEmployees( "input/io_test_file.txt" );
        assertTrue( manager.login( "amaxwell9", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Depositor );
        Depositor d = (Depositor) manager.getCurrentUser();
        assertEquals( 2, d.getAccounts().size() );

        Account a = new Account( "Account", "12030300", 500.00, true, false, new Transaction[0] );

        try {
            manager.removeAccount( a );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Account does not exist", e.getMessage() );
            assertEquals( 2, d.getAccounts().size() );
        }
        try {
            manager.removeAccount( d.getAccounts().get( 0 ) );
            assertEquals( 1, d.getAccounts().size() );
            manager.removeAccount( d.getAccounts().get( 0 ) );
            assertEquals( 0, d.getAccounts().size() );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }

        try {
            manager.removeAccount( a );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "There are no accounts to remove", e.getMessage() );
        }

        manager.logout();
        assertTrue( manager.login( "mhooke2", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Employee );
        Employee e = (Employee) manager.getCurrentUser();

        try {
            manager.removeAccount( a );
            fail();
        }
        catch ( IllegalArgumentException excpt ) {
            assertEquals( "You do not have access to this feature", excpt.getMessage() );
        }

    }

    @Test
    public void testMakeWithdrawal () {
        BankManager manager = new BankManager();
        manager.loadEmployees( "input/io_test_file.txt" );
        assertTrue( manager.login( "amaxwell9", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Depositor );

        Depositor d = (Depositor) manager.getCurrentUser();
        assertEquals( 67.89, d.getAccounts().get( 0 ).getBalance(), 0.01 );
        assertEquals( 560.32, d.getAccounts().get( 1 ).getBalance(), 0.01 );
        assertEquals( 2, d.getAccounts().get( 0 ).getTransactions().size() );
        assertEquals( 3, d.getAccounts().get( 1 ).getTransactions().size() );

        Account random = new Account( "Random", "12345678", 300.00, true, false, new Transaction[0] );
        try {
            manager.makeWithdrawal( 100.00, random );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Account doesn't exist", e.getMessage() );
            assertEquals( 300.00, random.getBalance(), 0.01 );
        }

        try {
            manager.makeWithdrawal( 30.00, d.getAccounts().get( 0 ) );
            assertEquals( 37.89, d.getAccounts().get( 0 ).getBalance(), 0.01 );
            assertEquals( 3, d.getAccounts().get( 0 ).getTransactions().size() );
            assertEquals( "Withdrawal", d.getAccounts().get( 0 ).getTransactions().get( 2 ).getDescription() );

            manager.makeWithdrawal( 300.00, d.getAccounts().get( 1 ) );
            assertEquals( 260.32, d.getAccounts().get( 1 ).getBalance(), 0.01 );
            assertEquals( 4, d.getAccounts().get( 1 ).getTransactions().size() );
            assertEquals( "Withdrawal", d.getAccounts().get( 1 ).getTransactions().get( 3 ).getDescription() );
        }
        catch ( Exception e ) {
            fail( e.getMessage() );
        }

        manager.logout();
        assertTrue( manager.login( "mhooke2", "password" ) );
        try {
            manager.makeWithdrawal( 100.00, random );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "You do not have access to this feature", e.getMessage() );
        }

    }

    @Test
    public void testMakeDeposit () {
        BankManager manager = new BankManager();
        manager.loadEmployees( "input/io_test_file.txt" );
        assertTrue( manager.login( "amaxwell9", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Depositor );

        Depositor d = (Depositor) manager.getCurrentUser();
        assertEquals( 67.89, d.getAccounts().get( 0 ).getBalance(), 0.01 );
        assertEquals( 560.32, d.getAccounts().get( 1 ).getBalance(), 0.01 );
        assertEquals( 2, d.getAccounts().get( 0 ).getTransactions().size() );
        assertEquals( 3, d.getAccounts().get( 1 ).getTransactions().size() );

        Account random = new Account( "Random", "12345678", 300.00, true, false, new Transaction[0] );
        try {
            manager.makeDeposit( 100.00, random );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Account doesn't exist", e.getMessage() );
            assertEquals( 300.00, random.getBalance(), 0.01 );
        }

        try {
            manager.makeDeposit( 30.00, d.getAccounts().get( 0 ) );
            assertEquals( 97.89, d.getAccounts().get( 0 ).getBalance(), 0.01 );
            assertEquals( 3, d.getAccounts().get( 0 ).getTransactions().size() );
            assertEquals( "Deposit", d.getAccounts().get( 0 ).getTransactions().get( 2 ).getDescription() );

            manager.makeDeposit( 300.00, d.getAccounts().get( 1 ) );
            assertEquals( 860.32, d.getAccounts().get( 1 ).getBalance(), 0.01 );
            assertEquals( 4, d.getAccounts().get( 1 ).getTransactions().size() );
            assertEquals( "Deposit", d.getAccounts().get( 1 ).getTransactions().get( 3 ).getDescription() );
        }
        catch ( Exception e ) {
            fail( e.getMessage() );
        }

        manager.logout();
        assertTrue( manager.login( "mhooke2", "password" ) );
        try {
            manager.makeWithdrawal( 100.00, random );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "You do not have access to this feature", e.getMessage() );
        }
    }

    @Test
    public void testEditAccount () {
        BankManager manager = new BankManager();
        manager.loadEmployees( "input/io_test_file.txt" );
        assertTrue( manager.login( "cwatts3", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Depositor );

        Depositor d = (Depositor) manager.getCurrentUser();
        assertEquals( 1, d.getAccounts().size() );
        assertEquals( "Account", d.getAccounts().get( 0 ).getName() );

        Account random = new Account( "Random", "12345678", 500.00, true, false, new Transaction[0] );

        try {
            manager.editAccount( "Not Random", random );
            fail();
        }
        catch ( Exception e ) {
            assertEquals( "Account does not exist", e.getMessage() );
        }

        try {
            manager.editAccount( "My Account", d.getAccounts().get( 0 ) );
            assertEquals( "My Account", d.getAccounts().get( 0 ).getName() );
            assertEquals( 1, d.getAccounts().size() );

            manager.editAccount( "My Checking Account", d.getAccounts().get( 0 ) );
            assertEquals( "My Checking Account", d.getAccounts().get( 0 ).getName() );
            assertEquals( 1, d.getAccounts().size() );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }

        manager.logout();
        assertTrue( manager.login( "mhooke2", "password" ) );
        try {
            manager.editAccount( "Not Random", random );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "You do not have access to this feature", e.getMessage() );
        }
    }

    @Test
    public void testEditEmployeeAccount () {
        BankManager manager = new BankManager();
        manager.loadEmployees( "input/io_test_file.txt" );
        assertTrue( manager.login( "cwatts3", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Depositor );

        Depositor d = (Depositor) manager.getCurrentUser();
        try {
            manager.editEmployeeAccount( "New", "Name", "newpassword", "newname1", "01/01/1990" );
            fail();
        }
        catch ( Exception e ) {
            assertEquals( "You do not have access to this feature", e.getMessage() );
        }

        manager.logout();
        assertTrue( manager.login( "dhouser2", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Employee );
        Employee e = (Employee) manager.getCurrentUser();
        assertEquals( "Deborah", e.getFirst() );
        assertEquals( "Houser", e.getLast() );
        assertEquals( "deborahhouser@mybankmail.com", e.getEmail() );
        assertEquals( "password", e.getPassword() );
        assertEquals( "dhouser2", e.getId() );
        assertEquals( "06/15/1967", e.getBirthday() );

        try {
            manager.editEmployeeAccount( "Deb", "Williams", "newPassword", "dwilliams2", "06/15/1967" );
            assertEquals( "Deb", e.getFirst() );
            assertEquals( "Williams", e.getLast() );
            assertEquals( "debwilliams@mybankmail.com", e.getEmail() );
            assertEquals( "newPassword", e.getPassword() );
            assertEquals( "dwilliams2", e.getId() );
            assertEquals( "06/15/1967", e.getBirthday() );
        }
        catch ( Exception ex ) {
            fail( ex.getMessage() );
        }

    }

    @Test
    public void testEditDepositorAccount () {
        BankManager manager = new BankManager();
        manager.loadEmployees( "input/io_test_file.txt" );
        assertTrue( manager.login( "cwatts3", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Depositor );

        Depositor d = (Depositor) manager.getCurrentUser();
        assertEquals( "Conner", d.getFirst() );
        assertEquals( "Watts", d.getLast() );
        assertEquals( "conner.watts3@gmail.com", d.getEmail() );
        assertEquals( "password", d.getPassword() );
        assertEquals( "cwatts3", d.getId() );
        assertEquals( "02/17/1997", d.getBirthday() );
        try {
            manager.editDepositorAccount( "Connie", "Watts", "conniewatts14@gmail.com", "newPassword", "02/17/1997" );
            assertEquals( "Connie", d.getFirst() );
            assertEquals( "Watts", d.getLast() );
            assertEquals( "conniewatts14@gmail.com", d.getEmail() );
            assertEquals( "newPassword", d.getPassword() );
            assertEquals( "02/17/1997", d.getBirthday() );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }

        manager.logout();
        manager.login( "rquinn3", "password" );

        try {
            manager.editDepositorAccount( "Connie", "Watts", "conniewatts14@gmail.com", "newPassword", "02/17.1997" );
            fail();
        }
        catch ( Exception e ) {
            assertEquals( "You do not have access to this feature", e.getMessage() );
        }
    }

    @Test
    public void testViewTransactions () {
        BankManager manager = new BankManager();
        manager.loadEmployees( "input/io_test_file.txt" );
        assertTrue( manager.login( "amaxwell9", "password" ) );
        assertTrue( manager.getCurrentUser() instanceof Depositor );
        Depositor d = (Depositor) manager.getCurrentUser();
        try {
            ArrayList<Transaction> t = manager.viewTransactions( d.getAccounts().get( 0 ) );
            assertEquals( 2, t.size() );
            t = manager.viewTransactions( d.getAccounts().get( 1 ) );
            assertEquals( 3, t.size() );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }

        manager.logout();
        manager.login( "rquinn3", "password" );
        assertTrue( manager.getCurrentUser() instanceof Employee );

        try {
            manager.viewTransactions( d.getAccounts().get( 0 ) );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "You do not have access to this feature", e.getMessage() );
        }
    }

    @Test
    public void testSave () {
        BankManager manager = new BankManager();
        manager.loadEmployees( "input/io_test_file.txt" );
        assertTrue( manager.login( "rquinn3", "password" ) );
        assertEquals( 4, manager.getEmployees().get( 0 ).getDepositors().size() );
        manager.removeDepositor( "hjackson6" );
        manager.save( "input/test_save_file.txt" );
        manager.loadEmployees( "input/test_save_file.txt" );
        ArrayList<Employee> emps = manager.getEmployees();
        assertEquals( 3, emps.get( 0 ).getDepositors().size() );
    }

}
