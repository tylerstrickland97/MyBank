package mybank.data;

// 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

public class Transaction {

    private final int FEBRUARY            = 2;

    private final int FEBRUARY_MAX_DAYS   = 29;

    private final int LEAP_YEAR_OCCURENCE = 4;

    private final int APRIL               = 4;

    private final int JUNE                = 6;

    private final int SEPTEMBER           = 9;

    private final int NOVEMBER            = 11;

    private final int DECEMBER            = 12;

    private final int MAX_MONTH_DAYS      = 31;

    private final int CURR_YEAR           = 2022;

    private final int MIN_YEAR            = 2020;

    private double    amount;

    private String    description;

    private String    date;

    public Transaction ( double amount, String description, String date ) {
        setAmount( amount );
        setDescription( description );
        setDate( date );
    }

    public double getAmount () {
        return amount;
    }

    public void setAmount ( double amount ) {
        if ( amount == 0 ) {
            throw new IllegalArgumentException( "Amount cannot be $0.00" );
        }
        this.amount = amount;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription ( String description ) {
        if ( description == null || description.equals( " " ) || description.length() == 0 ) {
            throw new IllegalArgumentException( "Description cannot be empty" );
        }
        this.description = description;
    }

    public String getDate () {
        return date;
    }

    public void setDate ( String date ) {
        if ( date == null || date.equals( " " ) || date.length() != 10 ) {
            throw new IllegalArgumentException( "Enter a valid date in the format MM/DD/YYYY" );
        }

        String month = date.substring( 0, 2 );
        String day = date.substring( 3, 5 );
        String year = date.substring( 6 );
        int monthInt = Integer.parseInt( month );
        int dayInt = Integer.parseInt( day );
        int yearInt = Integer.parseInt( year );

        if ( yearInt > CURR_YEAR ) {
            throw new IllegalArgumentException( "Invalid year" );
        }
        if ( yearInt < MIN_YEAR ) {
            throw new IllegalArgumentException( "Cannot record transactions from more than two years ago" );
        }
        if ( dayInt < 1 || dayInt > MAX_MONTH_DAYS ) {
            throw new IllegalArgumentException( "Invalid day" );
        }
        if ( monthInt < 1 || monthInt > DECEMBER ) {
            throw new IllegalArgumentException( "Invalid month" );
        }

        if ( monthInt == APRIL || monthInt == JUNE || monthInt == SEPTEMBER || monthInt == NOVEMBER ) {
            if ( dayInt > MAX_MONTH_DAYS - 1 ) {
                throw new IllegalArgumentException( "Invalid date" );
            }
        }

        if ( monthInt == FEBRUARY && dayInt > FEBRUARY_MAX_DAYS ) {
            throw new IllegalArgumentException( "Invalid date" );
        }

        if ( monthInt == FEBRUARY && dayInt > FEBRUARY_MAX_DAYS - 1 && yearInt % LEAP_YEAR_OCCURENCE != 0 ) {
            throw new IllegalArgumentException( "Invalid date" );
        }

        this.date = date;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits( amount );
        result = prime * result + (int) ( temp ^ ( temp >>> 32 ) );
        result = prime * result + ( ( date == null ) ? 0 : date.hashCode() );
        result = prime * result + ( ( description == null ) ? 0 : description.hashCode() );
        return result;
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        Transaction other = (Transaction) obj;
        if ( Double.doubleToLongBits( amount ) != Double.doubleToLongBits( other.amount ) ) {
            return false;
        }
        if ( date == null ) {
            if ( other.date != null ) {
                return false;
            }
        }
        else if ( !date.equals( other.date ) ) {
            return false;
        }
        if ( description == null ) {
            if ( other.description != null ) {
                return false;
            }
        }
        else if ( !description.equals( other.description ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString () {
        return description + "," + String.format( "%.2f", amount ) + "," + date;
    }

}
