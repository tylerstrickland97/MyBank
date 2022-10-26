package mybank.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import mybank.data.Account;
import mybank.data.Transaction;
import mybank.user.Depositor;
import mybank.user.Employee;

public class EmployeeWriterTest {

    @Test
    public void testWriteValidEmployees () {
        Transaction t1 = new Transaction( -75.64, "Walmart", "12/20/2021" );
        Transaction t2 = new Transaction( -43.78, "Amazon", "11/02/2021" );
        Transaction t3 = new Transaction( 100.00, "Deposit", "11/06/2021" );
        Transaction t4 = new Transaction( -50.00, "Withdrawal", "11/12/2021" );
        Transaction[] transactions1 = new Transaction[2];
        Transaction[] transactions2 = new Transaction[2];
        transactions1[0] = t1;
        transactions1[1] = t2;
        transactions2[0] = t3;
        transactions2[1] = t4;
        Account a1 = new Account( "Checking", "01203000", 723.45, true, false, transactions1 );
        Account a2 = new Account( "Savings", "01920340", 805.65, false, true, transactions2 );
        Account[] accounts1 = new Account[2];
        accounts1[0] = a1;
        accounts1[1] = a2;
        Depositor d1 = new Depositor( "Hubert", "Jackson", "hubertjackson@gmail.com", "password", "hjackson6",
                "11/08/1993", accounts1 );

        Transaction t5 = new Transaction( -18.99, "Netflix", "12/01/2021" );
        Transaction t6 = new Transaction( -27.88, "Walmart", "12/03/2021" );
        Transaction[] transactions3 = new Transaction[2];
        transactions3[0] = t5;
        transactions3[1] = t6;
        Account a3 = new Account( "My Checking", "03040129", 230.89, true, false, transactions3 );
        Transaction t7 = new Transaction( 150.00, "Deposit", "12/02/2021" );
        Transaction t8 = new Transaction( 25.00, "Deposit", "12/23/2021" );
        Transaction t9 = new Transaction( 50.00, "Deposit", "01/12/2022" );
        Transaction[] transactions4 = new Transaction[3];
        transactions4[0] = t7;
        transactions4[1] = t8;
        transactions4[2] = t9;
        Account a4 = new Account( "My Savings", "10293040", 198.34, false, true, transactions4 );
        Account[] accounts2 = new Account[2];
        accounts2[0] = a3;
        accounts2[1] = a4;
        Depositor d2 = new Depositor( "Angel", "Morley", "angelmorley@yahoo.com", "password", "amorley4", "08/04/2000",
                accounts2 );

        Transaction t10 = new Transaction( -15.61, "McDonald's", "12/22/2021" );
        Transaction t11 = new Transaction( -78.99, "Dick's Sporting Goods", "12/27/2021" );
        Transaction[] transactions5 = new Transaction[2];
        transactions5[0] = t10;
        transactions5[1] = t11;
        Account a5 = new Account( "Account", "01203000", 789.32, true, false, transactions5 );
        Account[] accounts3 = new Account[1];
        accounts3[0] = a5;
        Depositor d3 = new Depositor( "Peter", "Stone", "peterstone2@gmail.com", "password", "pstone8", "07/16/1981",
                accounts3 );

        Transaction t12 = new Transaction( 125.00, "Deposit", "11/29/2021" );
        Transaction t70 = new Transaction( -98.76, "Costco", "11/30/2021" );
        Transaction[] transactions6 = new Transaction[2];
        transactions6[0] = t12;
        transactions6[1] = t70;
        Account a6 = new Account( "Checking", "01920304", 989.33, true, false, transactions6 );
        Account[] accounts4 = new Account[1];
        accounts4[0] = a6;
        Depositor d4 = new Depositor( "Gabriel", "Coates", "coatesgabriel@yahoo.com", "password", "gcoates2",
                "08/02/1959", accounts4 );
        Depositor[] deps1 = new Depositor[4];
        deps1[0] = d1;
        deps1[1] = d2;
        deps1[2] = d3;
        deps1[3] = d4;
        Employee e1 = new Employee( "Richard", "Quinn", "richardquinn@mybankmail.com", "password", "rquinn3",
                "09/18/1978", deps1 );

        Transaction t13 = new Transaction( -82.13, "Kroger", "12/15/2021" );
        Transaction t14 = new Transaction( 500.00, "Deposit", "12/23/2021" );
        Transaction[] transactions7 = new Transaction[2];
        transactions7[0] = t13;
        transactions7[1] = t14;
        Account a7 = new Account( "Account", "01020304", 1023.56, true, false, transactions7 );
        Account[] accounts5 = new Account[1];
        accounts5[0] = a7;
        Depositor d5 = new Depositor( "Conner", "Watts", "conner.watts3@gmail.com", "password", "cwatts3", "02/17/1997",
                accounts5 );
        Transaction t15 = new Transaction( 800.00, "Deposit", "12/31/2021" );
        Transaction t16 = new Transaction( -11.22, "Walgreens", "01/03/2022" );
        Transaction[] transactions8 = new Transaction[2];
        transactions8[0] = t15;
        transactions8[1] = t16;
        Account a8 = new Account( "Checking Account", "09201030", 567.49, true, false, transactions8 );

        Account[] accounts6 = new Account[1];
        accounts6[0] = a8;
        Depositor d6 = new Depositor( "Johnathan", "Byrd", "johnbyrd@gmail.com", "password", "jbyrd8", "02/05/1997",
                accounts6 );

        Transaction t17 = new Transaction( -48.76, "Target", "12/21/2021" );
        Transaction t18 = new Transaction( -22.13, "Amazon", "12/22/2021" );
        Transaction[] transactions9 = new Transaction[2];
        transactions9[0] = t17;
        transactions9[1] = t18;
        Account a9 = new Account( "My Checking Account", "02930405", 389.23, true, false, transactions9 );
        Account[] accounts7 = new Account[1];
        accounts7[0] = a9;
        Depositor d7 = new Depositor( "Milo", "Tucker", "milotucker@yahoo.com", "password", "mtucker3", "03/08/1960",
                accounts7 );
        Depositor[] deps2 = new Depositor[3];
        deps2[0] = d5;
        deps2[1] = d6;
        deps2[2] = d7;
        Employee e2 = new Employee( "Monica", "Johnson", "monicajohnson@mybankmail.com", "password", "mjohnson8",
                "08/08/1985", deps2 );

        Transaction t19 = new Transaction( -127.88, "Home Depot", "01/03/2022" );
        Transaction t20 = new Transaction( -12.33, "Lowe's", "01/11/2022" );
        Transaction[] transactions10 = new Transaction[2];
        transactions10[0] = t19;
        transactions10[1] = t20;
        Account a10 = new Account( "Alex's Checking Account", "29304000", 67.89, true, false, transactions10 );
        Transaction t21 = new Transaction( 900.00, "Deposit", "12/19/2021" );
        Transaction t22 = new Transaction( -400.00, "Withdrawal", "01/01/2022" );
        Transaction t23 = new Transaction( -200.00, "Withdrawal", "01/20/2022" );
        Transaction[] transactions11 = new Transaction[3];
        transactions11[0] = t21;
        transactions11[1] = t22;
        transactions11[2] = t23;
        Account a11 = new Account( "Savings", "29102939", 560.32, false, true, transactions11 );
        Account[] accounts8 = new Account[2];
        accounts8[0] = a10;
        accounts8[1] = a11;
        Depositor d8 = new Depositor( "Alexander", "Maxwell", "maxwellalexander2@yahoo.com", "password", "amaxwell9",
                "12/11/1993", accounts8 );

        Transaction t24 = new Transaction( -42.33, "Best Buy", "11/30/2021" );
        Transaction t25 = new Transaction( -19.76, "Hardee's", "12/02/2021" );
        Transaction[] transactions12 = new Transaction[2];
        transactions12[0] = t24;
        transactions12[1] = t25;
        Account a12 = new Account( "Checking", "49304950", 201.98, true, false, transactions12 );
        Account[] accounts9 = new Account[1];
        accounts9[0] = a12;
        Depositor d9 = new Depositor( "Timothy", "Knight", "timothyknight4@gmail.com", "password", "tknight5",
                "12/29/1994", accounts9 );

        Transaction t26 = new Transaction( -69.22, "Aldi", "12/20/2021" );
        Transaction t27 = new Transaction( -8.90, "Five Below", "12/22/2021" );
        Transaction[] transactions13 = new Transaction[2];
        transactions13[0] = t26;
        transactions13[1] = t27;
        Account a13 = new Account( "Account", "01920300", 67.23, true, false, transactions13 );
        Account[] accounts10 = new Account[1];
        accounts10[0] = a13;
        Depositor d10 = new Depositor( "Joel", "Fisher", "joelfisher8@gmail.com", "password", "jfisher3", "03/15/1980",
                accounts10 );

        Depositor[] deps3 = new Depositor[3];
        deps3[0] = d8;
        deps3[1] = d9;
        deps3[2] = d10;
        Employee e3 = new Employee( "Deborah", "Houser", "deborahhouser@mybankmail.com", "password", "dhouser2",
                "06/15/1967", deps3 );

        Transaction t28 = new Transaction( -45.78, "Verizon Wireless", "11/12/2021" );
        Transaction t29 = new Transaction( -65.23, "Dollar Tree", "11/14/2021" );
        Transaction[] transactions14 = new Transaction[2];
        transactions14[0] = t28;
        transactions14[1] = t29;
        Account a14 = new Account( "Checking", "03029304", 10.22, true, false, transactions14 );
        Account[] accounts11 = new Account[1];
        accounts11[0] = a14;
        Depositor d11 = new Depositor( "Anthony", "Kelley", "anthonykelley1@gmail.com", "password", "akelley7",
                "09/21/1994", accounts11 );

        Transaction t30 = new Transaction( 100.00, "Deposit", "12/01/2021" );
        Transaction t31 = new Transaction( 200.00, "Deposit", "12/08/2021" );
        Transaction t32 = new Transaction( -75.00, "Withdrawal", "12/20/2021" );
        Transaction[] transactions15 = new Transaction[3];
        transactions15[0] = t30;
        transactions15[1] = t31;
        transactions15[2] = t32;
        Account a15 = new Account( "Savings", "01029304", 89.45, false, true, transactions15 );
        Account[] accounts40 = new Account[1];
        accounts40[0] = a15;
        Depositor d40 = new Depositor( "Nathan", "Burke", "nathanburke2@yahoo.com", "password", "nburke3", "08/23/1963",
                accounts40 );
        Transaction t33 = new Transaction( -59.22, "Macy's", "01/17/2022" );
        Transaction t34 = new Transaction( -31.22, "Five Guys", "01/20/2022" );
        Transaction[] transactions16 = new Transaction[2];
        transactions16[0] = t33;
        transactions16[1] = t34;
        Account a16 = new Account( "Checking", "82930492", 902.44, true, false, transactions16 );
        Account[] accounts12 = new Account[1];
        accounts12[0] = a16;
        Depositor d12 = new Depositor( "Kevin", "Whittle", "kevinwhittle2@gmail.com", "password", "kwhittle3",
                "05/15/2002", accounts12 );

        Transaction t35 = new Transaction( -21.09, "Starbucks", "12/19/2021" );
        Transaction t36 = new Transaction( -35.66, "AT&T Wireless", "12/20/2021" );
        Transaction[] transactions17 = new Transaction[2];
        transactions17[0] = t35;
        transactions17[1] = t36;
        Account a17 = new Account( "My Checking", "60392040", 86.54, true, false, transactions17 );
        Account[] accounts13 = new Account[1];
        accounts13[0] = a17;
        Depositor d13 = new Depositor( "Derrick", "Hunt", "derrick.hunt@yahoo.com", "password", "dhunt9", "09/24/1986",
                accounts13 );
        Depositor[] deps4 = new Depositor[4];
        deps4[0] = d11;
        deps4[1] = d40;
        deps4[2] = d12;
        deps4[3] = d13;
        Employee e4 = new Employee( "Martin", "Hooke", "martinhooke@mybankmail.com", "password", "mhooke2",
                "11/08/1991", deps4 );

        Transaction t37 = new Transaction( -15.97, "Rite Aid", "01/02/2022" );
        Transaction t38 = new Transaction( -109.52, "Ace Hardware", "01/04/2022" );
        Transaction[] transactions18 = new Transaction[2];
        transactions18[0] = t37;
        transactions18[1] = t38;
        Account a18 = new Account( "Checking Account", "10200405", 900.01, true, false, transactions18 );
        Transaction t39 = new Transaction( 100.00, "Deposit", "11/28/2021" );
        Transaction t40 = new Transaction( -20.00, "Withdrawal", "12/01/2021" );
        Transaction t41 = new Transaction( -15.00, "Withdrawal", "12/14/2021" );
        Transaction[] transactions19 = new Transaction[3];
        transactions19[0] = t39;
        transactions19[1] = t40;
        transactions19[2] = t41;
        Account a19 = new Account( "Savings Account", "92010305", 459.02, false, true, transactions19 );
        Account[] accounts14 = new Account[2];
        accounts14[0] = a18;
        accounts14[1] = a19;
        Depositor d14 = new Depositor( "Garrett", "Medina", "garrett.medina@gmail.com", "password", "gmedina2",
                "05/16/2003", accounts14 );

        Transaction t42 = new Transaction( -15.66, "7-Eleven", "11/20/2021" );
        Transaction t43 = new Transaction( -22.08, "Walmart", "11/25/2021" );
        Transaction[] transactions20 = new Transaction[2];
        transactions20[0] = t42;
        transactions20[1] = t43;
        Account a20 = new Account( "Checking", "20304050", 903.21, true, false, transactions20 );
        Account[] accounts15 = new Account[1];
        accounts15[0] = a20;
        Depositor d15 = new Depositor( "Ryder", "Strickland", "ryderstrickland@gmail.com", "password", "rstrickland9",
                "06/04/1976", accounts15 );
        Depositor[] deps5 = new Depositor[2];
        deps5[0] = d14;
        deps5[1] = d15;
        Employee e5 = new Employee( "Nicole", "Reynolds", "nicolereynolds@mybankmail.com", "password", "nreynolds3",
                "02/19/1997", deps5 );

        Transaction t44 = new Transaction( -49.88, "Target", "12/21/2021" );
        Transaction t45 = new Transaction( -92.33, "Kohl's", "12/22/2021" );
        Transaction[] transactions21 = new Transaction[2];
        transactions21[0] = t44;
        transactions21[1] = t45;
        Account a21 = new Account( "My Account", "01030450", 93.23, true, false, transactions21 );
        Account[] accounts16 = new Account[1];
        accounts16[0] = a21;
        Depositor d16 = new Depositor( "Brody", "King", "brody.king@yahoo.com", "password", "bking3", "06/16/1976",
                accounts16 );

        Transaction t46 = new Transaction( -31.44, "Belk", "01/13/2022" );
        Transaction t47 = new Transaction( -74.99, "American Eagle", "01/15/2022" );
        Transaction[] transactions22 = new Transaction[2];
        transactions22[0] = t46;
        transactions22[1] = t47;
        Account a22 = new Account( "Account", "10203050", 703.56, true, false, transactions22 );
        Account[] accounts17 = new Account[1];
        accounts17[0] = a22;
        Depositor d17 = new Depositor( "Laura", "Mccormick", "laura.mccormick3@yahoo.com", "password", "lmccormick2",
                "07/10/1981", accounts17 );

        Depositor[] deps6 = new Depositor[2];
        deps6[0] = d16;
        deps6[1] = d17;
        Employee e6 = new Employee( "Walter", "Overby", "walteroverby@mybankmail.com", "password", "woverby4",
                "08/16/1968", deps6 );

        Transaction t48 = new Transaction( 500.00, "Deposit", "12/26/2021" );
        Transaction t49 = new Transaction( 100.00, "Deposit", "12/29/2021" );
        Transaction[] transactions23 = new Transaction[2];
        transactions23[0] = t48;
        transactions23[1] = t49;
        Account a23 = new Account( "Savings", "49204950", 2450.75, false, true, transactions23 );
        Account[] accounts18 = new Account[1];
        accounts18[0] = a23;
        Depositor d18 = new Depositor( "Natalia", "Hopkins", "natalia.hopkins@gmail.com", "password", "nhopkins8",
                "10/07/1987", accounts18 );

        Transaction t50 = new Transaction( 900.00, "Deposit", "11/17/2021" );
        Transaction t51 = new Transaction( -100.00, "Withdrawal", "11/21/2021" );
        Transaction[] transactions24 = new Transaction[2];
        transactions24[0] = t50;
        transactions24[1] = t51;
        Account a24 = new Account( "Checking", "20307839", 289.56, true, false, transactions24 );
        Account[] accounts19 = new Account[1];
        accounts19[0] = a24;
        Depositor d19 = new Depositor( "Mariam", "Nelson", "nelsonmariam@gmail.com", "password", "mnelson8",
                "07/05/1985", accounts19 );
        Depositor[] deps7 = new Depositor[2];
        deps7[0] = d18;
        deps7[1] = d19;
        Employee e7 = new Employee( "Nolan", "Thompson", "nolanthompson@mybankmail.com", "password", "nthompson2",
                "06/11/1983", deps7 );

        Transaction t52 = new Transaction( -25.66, "Bed Bath and Beyond", "01/11/2022" );
        Transaction t53 = new Transaction( -15.42, "Chick-fil-a", "01/13/2022" );
        Transaction[] transactions25 = new Transaction[2];
        transactions25[0] = t52;
        transactions25[1] = t53;
        Account a25 = new Account( "My Checking", "20306829", 902.44, true, false, transactions25 );
        Account[] accounts20 = new Account[1];
        accounts20[0] = a25;
        Depositor d20 = new Depositor( "Baila", "Bush", "bailabush@gmail.com", "password", "bbush5", "03/29/1960",
                accounts20 );

        Transaction t54 = new Transaction( -70.99, "Walmart", "12/09/2021" );
        Transaction t55 = new Transaction( -11.21, "Burger King", "12/11/2021" );
        Transaction[] transactions26 = new Transaction[2];
        transactions26[0] = t54;
        transactions26[1] = t55;
        Account a26 = new Account( "Alina's Checking", "10294059", 78.56, true, false, transactions26 );
        Account[] accounts21 = new Account[1];
        accounts21[0] = a26;
        Depositor d21 = new Depositor( "Alina", "Curry", "alina.curry@yahoo.com", "password", "acurry2", "01/14/1962",
                accounts21 );

        Transaction t56 = new Transaction( -3.24, "Dunkin", "11/29/2021" );
        Transaction t57 = new Transaction( -8.08, "Wendy's", "12/01/2021" );
        Transaction[] transactions27 = new Transaction[2];
        transactions27[0] = t56;
        transactions27[1] = t57;
        Account a27 = new Account( "My Checking", "93049560", 297.44, true, false, transactions27 );
        Account[] accounts22 = new Account[1];
        accounts22[0] = a27;
        Depositor d22 = new Depositor( "Zelda", "Gibson", "zelda.gibson2@gmail.com", "password", "zgibson9",
                "02/25/1980", accounts22 );

        Depositor[] deps8 = new Depositor[3];
        deps8[0] = d20;
        deps8[1] = d21;
        deps8[2] = d22;
        Employee e8 = new Employee( "Brooke", "Lopez", "brookelopez@mybankmail.com", "password", "blopez4",
                "04/01/1998", deps8 );

        Transaction t58 = new Transaction( -15.66, "AutoZone", "11/20/2021" );
        Transaction t59 = new Transaction( -80.76, "Walmart", "11/23/2021" );
        Transaction[] transactions28 = new Transaction[2];
        transactions28[0] = t58;
        transactions28[1] = t59;
        Account a28 = new Account( "Checking Account", "10493027", 641.22, true, false, transactions28 );
        Account[] accounts23 = new Account[2];
        accounts23[0] = a28;
        Depositor d23 = new Depositor( "Madison", "Ferguson", "fergusonmadison@gmail.com", "password", "mferguson7",
                "02/03/1973", accounts23 );

        Transaction t60 = new Transaction( 1900.00, "Deposit", "12/23/2021" );
        Transaction t61 = new Transaction( 500.00, "Deposit", "12/26/2021" );
        Transaction[] transactions29 = new Transaction[2];
        transactions29[0] = t60;
        transactions29[1] = t61;
        Account a29 = new Account( "Checking", "29301748", 98.76, true, false, transactions29 );
        Account[] accounts24 = new Account[1];
        accounts24[0] = a29;
        Depositor d24 = new Depositor( "Lia", "West", "west.lia@gmail.com", "password", "lwest3", "09/08/1977",
                accounts24 );

        Depositor[] deps9 = new Depositor[2];
        deps9[0] = d23;
        deps9[1] = d24;
        Employee e9 = new Employee( "Ursula", "Stewart", "ursulastewart@mybankmail.com", "password", "ustewart8",
                "08/21/1981", deps9 );

        Transaction t62 = new Transaction( 500.00, "Deposit", "01/02/2022" );
        Transaction t63 = new Transaction( -75.00, "Withdrawal", "01/04/2022" );
        Transaction[] transactions30 = new Transaction[2];
        transactions30[0] = t62;
        transactions30[1] = t63;
        Account a30 = new Account( "Savings", "93029568", 45.23, false, true, transactions30 );
        Account[] accounts25 = new Account[1];
        accounts25[0] = a30;
        Depositor d25 = new Depositor( "Morgan", "Rodriguez", "morganrodriguez@gmail.com", "password", "mrodriguez7",
                "02/18/2003", accounts25 );

        Transaction t64 = new Transaction( -54.66, "Sears", "12/20/2021" );
        Transaction t65 = new Transaction( -88.90, "Dick's Sporting Goods", "12/22/2021" );
        Transaction[] transactions31 = new Transaction[2];
        transactions31[0] = t64;
        transactions31[1] = t65;
        Account a31 = new Account( "Checking Account", "19204589", 1097.67, true, false, transactions31 );
        Account[] accounts26 = new Account[1];
        accounts26[0] = a31;
        Depositor d26 = new Depositor( "Francesca", "Delgado", "francescadelgado@yahoo.com", "password", "fdelgado3",
                "05/27/1964", accounts26 );

        Transaction t66 = new Transaction( -31.22, "Tractor Supply Company", "01/12/2022" );
        Transaction t67 = new Transaction( -309.23, "Big Lots", "01/14/2022" );
        Transaction t68 = new Transaction( -209.88, "Walmart", "01/16/2022" );
        Transaction[] transactions32 = new Transaction[3];
        transactions32[0] = t66;
        transactions32[1] = t67;
        transactions32[2] = t68;
        Account a32 = new Account( "Checking", "20395618", 85.33, true, false, transactions32 );
        Account[] accounts27 = new Account[1];
        accounts27[0] = a32;
        Depositor d27 = new Depositor( "Arielle", "Richardson", "richardson.arielle@gmail.com", "password",
                "arichardson6", "01/11/1972", accounts27 );

        Depositor[] deps10 = new Depositor[3];
        deps10[0] = d25;
        deps10[1] = d26;
        deps10[2] = d27;
        Employee e10 = new Employee( "Pablo", "Norris", "pablonorris@mybankmail.com", "password", "pnorris6",
                "06/29/1977", deps10 );

        ArrayList<Employee> emps = new ArrayList<Employee>();
        emps.add( e1 );
        emps.add( e2 );
        emps.add( e3 );
        emps.add( e4 );
        emps.add( e5 );
        emps.add( e6 );
        emps.add( e7 );
        emps.add( e8 );
        emps.add( e9 );
        emps.add( e10 );

        try {
            EmployeeWriter.writeEmployeeRecords( emps, "input/output_file_1.txt" );
        }
        catch ( IllegalArgumentException e ) {
            fail( "Cannot write to file" );
        }

        checkFiles( "input/output_file_1.txt", "input/io_test_file.txt" );

    }

    private void checkFiles ( String expFile, String actFile ) {
        try (
                Scanner expScanner = new Scanner( new FileInputStream( expFile ) );
                Scanner actScanner = new Scanner( new FileInputStream( actFile ) ); ) {

            while ( expScanner.hasNextLine() ) {
                assertEquals( expScanner.nextLine(), actScanner.nextLine() );
            }

            expScanner.close();
            actScanner.close();
        }
        catch ( IOException e ) {
            fail( "Error reading files." );
        }
    }

}
