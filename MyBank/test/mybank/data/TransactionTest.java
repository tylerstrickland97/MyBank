package mybank.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TransactionTest {

    @Test
    public void testConstructor () {
        Transaction t1 = null;
        try {
            t1 = new Transaction( 578.93, "Weekly Deposit", "11/12/2021" );
            assertEquals( 578.93, t1.getAmount(), 0.00 );
            assertEquals( "Weekly Deposit", t1.getDescription() );
            assertEquals( "11/12/2021", t1.getDate() );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }

        Transaction t2 = null;
        try {
            t2 = new Transaction( -239.88, "Car payment", "12/01/2021" );
            assertEquals( -239.88, t2.getAmount(), 0.00 );
            assertEquals( "Car payment", t2.getDescription() );
            assertEquals( "12/01/2021", t2.getDate() );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }

    }

    @Test
    public void testSetAmount () {
        Transaction t1 = new Transaction( -41.35, "Amazon", "01/02/2022" );
        try {
            t1.setAmount( -100.82 );
            assertEquals( -100.82, t1.getAmount(), 0.00 );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }

        try {
            t1.setAmount( 100.82 );
            assertEquals( 100.82, t1.getAmount(), 0.00 );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }

        try {
            t1.setAmount( 0.00 );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Amount cannot be $0.00", e.getMessage() );
            assertEquals( 100.82, t1.getAmount(), 0.00 );
        }
    }

    @Test
    public void testSetDescription () {
        Transaction t1 = new Transaction( -5987.23, "NCSU Tuition", "01/12/2022" );
        assertEquals( "NCSU Tuition", t1.getDescription() );
        try {
            t1.setDescription( null );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Description cannot be empty", e.getMessage() );
            assertEquals( "NCSU Tuition", t1.getDescription() );
        }

        try {
            t1.setDescription( "" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Description cannot be empty", e.getMessage() );
            assertEquals( "NCSU Tuition", t1.getDescription() );
        }

        try {
            t1.setDescription( " " );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Description cannot be empty", e.getMessage() );
            assertEquals( "NCSU Tuition", t1.getDescription() );
        }

        try {
            t1.setDescription( "North Carolina State University Tuition" );
            assertEquals( "North Carolina State University Tuition", t1.getDescription() );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }
    }

    @Test
    public void testSetDate () {
        Transaction t1 = new Transaction( 236.49, "Nike refund", "10/31/2021" );
        assertEquals( "10/31/2021", t1.getDate() );

        try {
            t1.setDate( "1/31/2021" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Enter a valid date in the format MM/DD/YYYY", e.getMessage() );
        }

        try {
            t1.setDate( "01/2/2021" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Enter a valid date in the format MM/DD/YYYY", e.getMessage() );
        }

        try {
            t1.setDate( "01/31/21" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Enter a valid date in the format MM/DD/YYYY", e.getMessage() );
        }

        try {
            t1.setDate( "00/31/2021" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Invalid month", e.getMessage() );
        }

        try {
            t1.setDate( "13/31/2021" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Invalid month", e.getMessage() );
        }

        try {
            t1.setDate( "01/00/2021" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Invalid day", e.getMessage() );
        }

        try {
            t1.setDate( "01/32/2021" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Invalid day", e.getMessage() );
        }

        try {
            t1.setDate( "01/31/2019" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Cannot record transactions from more than two years ago", e.getMessage() );
        }

        try {
            t1.setDate( "01/31/2023" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Invalid year", e.getMessage() );
        }

        try {
            t1.setDate( "02/30/2021" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Invalid date", e.getMessage() );
        }

        try {
            t1.setDate( "02/29/2021" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Invalid date", e.getMessage() );
        }

        try {
            t1.setDate( "04/31/2021" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Invalid date", e.getMessage() );
        }

        try {
            t1.setDate( "06/31/2021" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Invalid date", e.getMessage() );
        }

        try {
            t1.setDate( "09/31/2021" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Invalid date", e.getMessage() );
        }

        try {
            t1.setDate( "11/31/2021" );
            fail();
        }
        catch ( IllegalArgumentException e ) {
            assertEquals( "Invalid date", e.getMessage() );
        }

        try {
            t1.setDate( "11/01/2021" );
            assertEquals( "11/01/2021", t1.getDate() );
        }
        catch ( IllegalArgumentException e ) {
            fail( e.getMessage() );
        }
    }

    @Test
    public void testHashCode () {
        Transaction t1 = new Transaction( -56.98, "Walmart", "12/05/2021" );
        Transaction t2 = new Transaction( 56.98, "Walmart", "12/05/2021" );
        Transaction t3 = new Transaction( -56.98, "Amazon", "12/05/2021" );
        Transaction t4 = new Transaction( -56.98, "Walmart", "11/16/2021" );
        Transaction t5 = new Transaction( -56.98, "Walmart", "12/05/2021" );

        assertNotEquals( t1.hashCode(), t2.hashCode() );
        assertNotEquals( t1.hashCode(), t3.hashCode() );
        assertNotEquals( t1.hashCode(), t4.hashCode() );
        assertEquals( t1.hashCode(), t5.hashCode() );

        assertNotEquals( t2.hashCode(), t3.hashCode() );
        assertNotEquals( t2.hashCode(), t4.hashCode() );
        assertNotEquals( t2.hashCode(), t5.hashCode() );

        assertNotEquals( t3.hashCode(), t4.hashCode() );
        assertNotEquals( t3.hashCode(), t5.hashCode() );

        assertNotEquals( t4.hashCode(), t5.hashCode() );
    }

    @Test
    public void testEquals () {
        Transaction t1 = new Transaction( -56.98, "Walmart", "12/05/2021" );
        Transaction t2 = new Transaction( 56.98, "Walmart", "12/05/2021" );
        Transaction t3 = new Transaction( -56.98, "Amazon", "12/05/2021" );
        Transaction t4 = new Transaction( -56.98, "Walmart", "11/16/2021" );
        Transaction t5 = new Transaction( -56.98, "Walmart", "12/05/2021" );

        assertFalse( t1.equals( t2 ) );
        assertFalse( t1.equals( t3 ) );
        assertFalse( t1.equals( t4 ) );
        assertTrue( t1.equals( t5 ) );

        assertFalse( t2.equals( t3 ) );
        assertFalse( t2.equals( t4 ) );
        assertFalse( t2.equals( t5 ) );

        assertFalse( t3.equals( t4 ) );
        assertFalse( t3.equals( t5 ) );

        assertFalse( t4.equals( t5 ) );

    }

    @Test
    public void testToString () {
        Transaction t1 = new Transaction( -902.34, "House payment", "11/28/2021" );
        Transaction t2 = new Transaction( -102.37, "Groceries", "12/14/2021" );
        Transaction t3 = new Transaction( -34.67, "Sheetz", "01/18/2022" );
        Transaction t4 = new Transaction( 1045.69, "Weekly Deposit", "12/01/2021" );
        Transaction t5 = new Transaction( 15.83, "Refund", "10/27/2021" );

        assertEquals( "House payment,-902.34,11/28/2021", t1.toString() );
        assertEquals( "Groceries,-102.37,12/14/2021", t2.toString() );
        assertEquals( "Sheetz,-34.67,01/18/2022", t3.toString() );
        assertEquals( "Weekly Deposit,1045.69,12/01/2021", t4.toString() );
        assertEquals( "Refund,15.83,10/27/2021", t5.toString() );
    }

}
