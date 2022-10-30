package mybank.io;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import mybank.data.Account;
import mybank.data.Transaction;
import mybank.user.Depositor;
import mybank.user.Employee;

public class EmployeeWriter {

    public static void writeEmployeeRecords ( ArrayList<Employee> emps, String name ) {
        try {
            PrintStream fileWriter = new PrintStream( new File( name ) );
            for ( int i = 0; i < emps.size(); i++ ) {
                fileWriter.println( "# " + emps.get( i ).toString() );
                ArrayList<Depositor> deps = emps.get( i ).getDepositors();
                for ( int j = 0; j < deps.size(); j++ ) {
                    fileWriter.println( "* " + deps.get( j ).toString() );
                    ArrayList<Account> accounts = deps.get( j ).getAccounts();
                    for ( int k = 0; k < accounts.size(); k++ ) {
                        fileWriter.println( "! " + accounts.get( k ).toString() );
                        ArrayList<Transaction> transactions = accounts.get( k ).getTransactions();
                        for ( int l = 0; l < transactions.size(); l++ ) {
                            fileWriter.println( "$ " + transactions.get( l ).toString() );
                        }
                    }
                }
            }
            fileWriter.close();

        }
        catch ( Exception e ) {
            throw new IllegalArgumentException( "Unable to write to file: " + name );
        }
    }
}
