package mybank.io;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import mybank.user.Employee;

public class EmployeeReaderTest {

    @Test
    public void testReadValidEmployees () {
        EmployeeReader read = new EmployeeReader();
        ArrayList<Employee> emps = read.readEmployeeFile( "input/io_test_file.txt" );
        assertEquals( 10, emps.size() );
        assertEquals( "Richard", emps.get( 0 ).getFirst() );
        assertEquals( "Quinn", emps.get( 0 ).getLast() );
        assertEquals( "richardquinn@mybankmail.com", emps.get( 0 ).getEmail() );
        assertEquals( "password", emps.get( 0 ).getPassword() );
        assertEquals( "rquinn3", emps.get( 0 ).getId() );
        assertEquals( "09/18/1978", emps.get( 0 ).getBirthday() );
        assertEquals( 4, emps.get( 0 ).getDepositors().size() );
        assertEquals( 2, emps.get( 0 ).getDepositors().get( 0 ).getAccounts().get( 0 ).getTransactions().size() );

        assertEquals( "Monica", emps.get( 1 ).getFirst() );
        assertEquals( "Johnson", emps.get( 1 ).getLast() );
        assertEquals( "monicajohnson@mybankmail.com", emps.get( 1 ).getEmail() );
        assertEquals( "password", emps.get( 1 ).getPassword() );
        assertEquals( "mjohnson8", emps.get( 1 ).getId() );
        assertEquals( "08/08/1985", emps.get( 1 ).getBirthday() );
        assertEquals( 3, emps.get( 1 ).getDepositors().size() );

        assertEquals( "Deborah", emps.get( 2 ).getFirst() );
        assertEquals( "Houser", emps.get( 2 ).getLast() );
        assertEquals( "deborahhouser@mybankmail.com", emps.get( 2 ).getEmail() );
        assertEquals( "password", emps.get( 2 ).getPassword() );
        assertEquals( "dhouser2", emps.get( 2 ).getId() );
        assertEquals( "06/15/1967", emps.get( 2 ).getBirthday() );
        assertEquals( 3, emps.get( 2 ).getDepositors().size() );

        assertEquals( "Martin", emps.get( 3 ).getFirst() );
        assertEquals( "Hooke", emps.get( 3 ).getLast() );
        assertEquals( "martinhooke@mybankmail.com", emps.get( 3 ).getEmail() );
        assertEquals( "password", emps.get( 3 ).getPassword() );
        assertEquals( "mhooke2", emps.get( 3 ).getId() );
        assertEquals( "11/08/1991", emps.get( 3 ).getBirthday() );
        assertEquals( 4, emps.get( 3 ).getDepositors().size() );

        assertEquals( "Nicole", emps.get( 4 ).getFirst() );
        assertEquals( "Reynolds", emps.get( 4 ).getLast() );
        assertEquals( "nicolereynolds@mybankmail.com", emps.get( 4 ).getEmail() );
        assertEquals( "password", emps.get( 4 ).getPassword() );
        assertEquals( "nreynolds3", emps.get( 4 ).getId() );
        assertEquals( "02/19/1997", emps.get( 4 ).getBirthday() );
        assertEquals( 2, emps.get( 4 ).getDepositors().size() );

        assertEquals( "Walter", emps.get( 5 ).getFirst() );
        assertEquals( "Overby", emps.get( 5 ).getLast() );
        assertEquals( "walteroverby@mybankmail.com", emps.get( 5 ).getEmail() );
        assertEquals( "password", emps.get( 5 ).getPassword() );
        assertEquals( "woverby4", emps.get( 5 ).getId() );
        assertEquals( "08/16/1968", emps.get( 5 ).getBirthday() );
        assertEquals( 2, emps.get( 5 ).getDepositors().size() );

        assertEquals( "Nolan", emps.get( 6 ).getFirst() );
        assertEquals( "Thompson", emps.get( 6 ).getLast() );
        assertEquals( "nolanthompson@mybankmail.com", emps.get( 6 ).getEmail() );
        assertEquals( "password", emps.get( 6 ).getPassword() );
        assertEquals( "nthompson2", emps.get( 6 ).getId() );
        assertEquals( "06/11/1983", emps.get( 6 ).getBirthday() );
        assertEquals( 2, emps.get( 6 ).getDepositors().size() );

        assertEquals( "Brooke", emps.get( 7 ).getFirst() );
        assertEquals( "Lopez", emps.get( 7 ).getLast() );
        assertEquals( "brookelopez@mybankmail.com", emps.get( 7 ).getEmail() );
        assertEquals( "password", emps.get( 7 ).getPassword() );
        assertEquals( "blopez4", emps.get( 7 ).getId() );
        assertEquals( "04/01/1998", emps.get( 7 ).getBirthday() );
        assertEquals( 3, emps.get( 7 ).getDepositors().size() );

        assertEquals( "Ursula", emps.get( 8 ).getFirst() );
        assertEquals( "Stewart", emps.get( 8 ).getLast() );
        assertEquals( "ursulastewart@mybankmail.com", emps.get( 8 ).getEmail() );
        assertEquals( "password", emps.get( 8 ).getPassword() );
        assertEquals( "ustewart8", emps.get( 8 ).getId() );
        assertEquals( "08/21/1981", emps.get( 8 ).getBirthday() );
        assertEquals( 2, emps.get( 8 ).getDepositors().size() );

        assertEquals( "Pablo", emps.get( 9 ).getFirst() );
        assertEquals( "Norris", emps.get( 9 ).getLast() );
        assertEquals( "pablonorris@mybankmail.com", emps.get( 9 ).getEmail() );
        assertEquals( "password", emps.get( 9 ).getPassword() );
        assertEquals( "pnorris6", emps.get( 9 ).getId() );
        assertEquals( "06/29/1977", emps.get( 9 ).getBirthday() );
        assertEquals( 3, emps.get( 9 ).getDepositors().size() );

    }

}
