package mybank.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import mybank.data.Account;
import mybank.data.Transaction;
import mybank.io.EmployeeReader;
import mybank.io.EmployeeWriter;
import mybank.user.Depositor;
import mybank.user.Employee;
import mybank.user.User;

public class BankManager {

    private EmployeeReader      employeeReader;

    private User                currentUser;

    private ArrayList<Employee> employeesList;

    private boolean             login = false;

    public BankManager () {
        employeeReader = new EmployeeReader();
        currentUser = null;

    }

    public boolean login ( String id, String password ) {
        if ( login ) {
            return false;
        }
        else {
            for ( int i = 0; i < employeesList.size(); i++ ) {
                Employee e = employeesList.get( i );
                if ( e.getId().equals( id ) && e.getPassword().equals( password ) ) {
                    currentUser = e;
                    login = true;
                    return true;
                }
                else {
                    for ( int j = 0; j < e.getDepositors().size(); j++ ) {
                        Depositor d = e.getDepositors().get( j );
                        if ( d.getId().equals( id ) && d.getPassword().equals( password ) ) {
                            currentUser = d;
                            login = true;
                            return true;
                        }
                    }
                }
            }
            return false;

        }
    }

    public void save ( String file ) {

        try {
            EmployeeWriter.writeEmployeeRecords( employeesList, file );
        }
        catch ( Exception e ) {
            throw new IllegalArgumentException( "Unable to write to file: " + file );
        }
    }

    public void addNewDepositor ( String first, String last, String email, String password, String id,
            String birthday ) {
        if ( ! ( currentUser instanceof Employee ) ) {
            throw new IllegalArgumentException( "You do not have access to this feature" );
        }

        Employee emp = (Employee) currentUser;
        Account[] accounts = new Account[0];
        Depositor d = new Depositor( first, last, email, password, id, birthday, accounts );

        for ( int i = 0; i < employeesList.size(); i++ ) {
            Employee curr = employeesList.get( i );
            for ( int j = 0; j < curr.getDepositors().size(); j++ ) {
                if ( curr.getDepositors().get( j ).equals( d ) ) {
                    throw new IllegalArgumentException( "Cannot add duplicate depositor" );
                }
            }
        }

        try {
            emp.getDepositors().add( d );
        }
        catch ( Exception e ) {
            throw new IllegalArgumentException( e.getMessage() );
        }

    }

    public void addNewEmployee ( String first, String last, String email, String password, String id,
            String birthday ) {
        if ( ! ( currentUser instanceof Employee ) ) {
            throw new IllegalArgumentException( "You do not have access to this feature" );
        }

        Employee newEmp = null;
        try {
            newEmp = new Employee( first, last, email, password, id, birthday, new Depositor[0] );
        }
        catch ( Exception e ) {
            throw new IllegalArgumentException( e.getMessage() );
        }

        for ( int i = 0; i < employeesList.size(); i++ ) {
            Employee e = employeesList.get( i );
            if ( e.equals( newEmp ) ) {
                throw new IllegalArgumentException( "Can't add duplicate employee" );
            }
        }

        employeesList.add( newEmp );

    }

    public void removeDepositor ( String depId ) {
        if ( ! ( currentUser instanceof Employee ) ) {
            throw new IllegalArgumentException( "You do not have access to this feature" );
        }

        Employee e = (Employee) currentUser;
        if ( e.getDepositors().size() == 0 ) {
            throw new IllegalArgumentException( "There are no depositors to delete" );
        }
        boolean exists = false;
        for ( int i = 0; i < e.getDepositors().size(); i++ ) {
            Depositor dep = e.getDepositors().get( i );
            if ( dep.getId().equals( depId ) ) {
                exists = true;
                e.getDepositors().remove( dep );
            }
        }

        if ( !exists ) {
            throw new IllegalArgumentException( "Depositor does not exist" );
        }

    }

    public void addTransaction ( String description, double amount, String date, Account a ) {

        if ( ! ( currentUser instanceof Depositor ) ) {
            throw new IllegalArgumentException( "You do not have access to this feature" );
        }

        Depositor d = (Depositor) currentUser;
        boolean exists = false;
        for ( int i = 0; i < d.getAccounts().size(); i++ ) {
            Account act = d.getAccounts().get( i );
            if ( act.equals( a ) ) {
                exists = true;
            }
        }
        if ( !exists ) {
            throw new IllegalArgumentException( "Account doesn't exist" );
        }
        Transaction t = null;
        try {
            t = new Transaction( amount, description, date );
            a.getTransactions().add( t );
            a.setBalance( a.getBalance() + amount );
        }
        catch ( Exception e ) {
            throw new IllegalArgumentException( e.getMessage() );
        }
    }

    public void addNewAccount ( String name, double balance, boolean checking, boolean savings ) {

        if ( ! ( currentUser instanceof Depositor ) ) {
            throw new IllegalArgumentException( "You do not have access to this feature" );
        }

        Depositor d = (Depositor) currentUser;
        boolean exists = false;
        for ( int i = 0; i < d.getAccounts().size(); i++ ) {
            if ( d.getAccounts().get( i ).getName().equals( name ) ) {
                exists = true;
            }
        }
        if ( exists ) {
            throw new IllegalArgumentException( "Can't add a duplicate account" );
        }

        Random r = new Random();
        int newAccountReference = 10000000 + r.nextInt( 90000000 );
        String reference = Integer.toString( newAccountReference );
        try {
            Account a = new Account( name, reference, balance, checking, savings, new Transaction[0] );
            d.getAccounts().add( a );
        }
        catch ( Exception e ) {
            throw new IllegalArgumentException( e.getMessage() );
        }
    }

    public void removeAccount ( Account a ) {

        if ( ! ( currentUser instanceof Depositor ) ) {
            throw new IllegalArgumentException( "You do not have access to this feature" );
        }

        Depositor d = (Depositor) currentUser;
        if ( d.getAccounts().size() == 0 ) {
            throw new IllegalArgumentException( "There are no accounts to remove" );
        }
        boolean exists = false;
        for ( int i = 0; i < d.getAccounts().size(); i++ ) {
            Account act = d.getAccounts().get( i );
            if ( act.getName().equals( a.getName() ) ) {
                exists = true;
            }
        }
        if ( !exists ) {
            throw new IllegalArgumentException( "Account does not exist" );
        }
        else {
            d.getAccounts().remove( a );
        }
    }

    public void loadEmployees ( String file ) {
        try {
            employeesList = employeeReader.readEmployeeFile( file );
        }
        catch ( Exception e ) {
            throw new IllegalArgumentException( e.getMessage() );
        }
    }

    public void makeWithdrawal ( double amount, Account a ) {
        if ( ! ( currentUser instanceof Depositor ) ) {
            throw new IllegalArgumentException( "You do not have access to this feature" );
        }

        Depositor d = (Depositor) currentUser;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDF = new SimpleDateFormat( "MM-dd-yyyy" );
        String date = simpleDF.format( calendar.getTime() );
        boolean exists = false;
        for ( int i = 0; i < d.getAccounts().size(); i++ ) {
            Account act = d.getAccounts().get( i );
            if ( act.equals( a ) ) {
                Transaction t = new Transaction( amount, "Withdrawal", date );
                a.getTransactions().add( t );
                a.setBalance( a.getBalance() - amount );
                exists = true;
            }
        }

        if ( !exists ) {
            throw new IllegalArgumentException( "Account doesn't exist" );
        }
    }

    public void makeDeposit ( double amount, Account a ) {
        if ( ! ( currentUser instanceof Depositor ) ) {
            throw new IllegalArgumentException( "You do not have access to this feature" );
        }

        Depositor d = (Depositor) currentUser;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDF = new SimpleDateFormat( "MM-dd-yyyy" );
        String date = simpleDF.format( calendar.getTime() );
        boolean exists = false;
        for ( int i = 0; i < d.getAccounts().size(); i++ ) {
            Account act = d.getAccounts().get( i );
            if ( act.equals( a ) ) {
                Transaction t = new Transaction( amount, "Deposit", date );
                a.getTransactions().add( t );
                a.setBalance( a.getBalance() + amount );
                exists = true;
            }
        }

        if ( !exists ) {
            throw new IllegalArgumentException( "Account doesn't exist" );
        }
    }

    public boolean editAccount ( String name, Account a ) {

        if ( ! ( currentUser instanceof Depositor ) ) {
            throw new IllegalArgumentException( "You do not have access to this feature" );
        }
        Depositor d = (Depositor) currentUser;
        boolean exists = false;
        for ( int i = 0; i < d.getAccounts().size(); i++ ) {
            Account act = d.getAccounts().get( i );
            if ( act.getName().equals( a.getName() ) ) {
                exists = true;
                try {
                    act.setName( name );
                }
                catch ( Exception e ) {
                    throw new IllegalArgumentException( e.getMessage() );
                }
                return true;
            }
        }
        if ( !exists ) {
            throw new IllegalArgumentException( "Account does not exist" );
        }
        return false;
    }

    public boolean editEmployeeAccount ( String first, String last, String password, String id, String birthday ) {
        if ( ! ( currentUser instanceof Employee ) ) {
            throw new IllegalArgumentException( "You do not have access to this feature" );
        }

        Employee e = (Employee) currentUser;
        try {
            e.setFirst( first );
            e.setLast( last );
            e.setId( id );
            e.setPassword( password );
            e.setBirthday( birthday );
            e.setEmail( first.toLowerCase() + last.toLowerCase() + "@mybankmail.com" );
            return true;
        }
        catch ( Exception ex ) {
            throw new IllegalArgumentException( ex.getMessage() );
        }
    }

    public boolean editDepositorAccount ( String first, String last, String email, String password, String birthday ) {
        if ( ! ( currentUser instanceof Depositor ) ) {
            throw new IllegalArgumentException( "You do not have access to this feature" );
        }

        Depositor d = (Depositor) currentUser;
        try {
            d.setFirst( first );
            d.setLast( last );
            d.setEmail( email );
            d.setPassword( password );
            d.setBirthday( birthday );
            Random r = new Random();
            int idNum = r.nextInt( 9 );
            d.setId( first.toLowerCase().charAt( 0 ) + last + idNum );
            return true;
        }
        catch ( Exception ex ) {
            throw new IllegalArgumentException( ex.getMessage() );
        }
    }

    public ArrayList<Transaction> viewTransactions ( Account a ) {
        if ( ! ( currentUser instanceof Depositor ) ) {
            throw new IllegalArgumentException( "You do not have access to this feature" );
        }
        return a.getTransactions();
    }

    public void logout () {
        currentUser = null;
        login = false;
    }

    public ArrayList<Employee> getEmployees () {
        return employeesList;
    }

    public User getCurrentUser () {
        return currentUser;
    }

}
