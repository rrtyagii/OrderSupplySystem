public class LocalDate {

    private int month;
    private int day;
    private int year;

    private static final int DEFAULT_YEAR=2019;
    private static final int DEFAULT_MONTH=1;
    private static final int DEFAULT_DAY=1;

    /**
     * default constructor
     * sets month to 1, day to 1 and year to 2000
     */
    public LocalDate() {

        setDate(DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR);
    }

    /**
     * overloaded constructor
     *
     * @param mm   initial value for month
     * @param dd   initial value for day
     * @param yyyy initial value for year
     *             <p>
     *             passes parameters to setDate method
     */
    public LocalDate(int mm, int dd, int yyyy) {
        setDate(mm, dd, yyyy);
    }

    /* accessor methods */
   public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    /** mutator method */
    /**
     * setDate
     *
     * @param mm   new value for month
     * @param dd   new value for day
     * @param yyyy new value for year
     *             passes parameters to setMonth, setDay, and setYear
     */
    public void setDate(int mm, int dd, int yyyy) {
       if(isLeapYear(yyyy) && dd==29 && mm==2){
           setYear(yyyy);
           setMonth(mm);
           setDay(dd);
       }
       else if (!isLeapYear(yyyy) && dd==29 && mm==2){
           setYear(yyyy);
           setMonth(mm);
           setDay(DEFAULT_DAY);
       }
       else {
           setYear(yyyy);
           setMonth(mm);
           setDay(dd);
       }

    }

    /** helper methods */
    /**
     * setDay
     *
     * @param dd new value for day
     *           if dd is legal day for current month, sets day to dd
     *           otherwise, sets day to 1
     */
    private void setDay(int dd) {

        int[] validDays = {1, 31, 29, 31, 30,
                31, 30, 31, 31, 30,
                31, 30, 31};

        try {

            if (dd >= 1 && dd <= validDays[month]) {
                day=dd;
            }
            else{
                day=DEFAULT_DAY;
                throw new LocalDateInvalid("Invalid day, resetted to DEFAULT DAY");
            }
            //day = (dd >= 1 && dd <= validDays[month] ? dd : 1);
        } catch(LocalDateInvalid die){
            die.getMessage();
        }

    }
    /** setMonth
     *  @param mm new value for month
     *  if mm is between 1 and 12, sets month to mm
     *  otherwise, sets month to 1
     */
    private void setMonth( int mm ) {
        try{      if(mm>=1 && mm<12){
            month=mm;
        }
        else{
            month=DEFAULT_MONTH;
            throw new LocalDateInvalid("Invalid month, resetted to DEFAULT MONTH");
        }

        } catch (LocalDateInvalid e) {
            e.getMessage();
        }
        // month = (mm >= 1 && mm <= 12 ? mm : throw new DateInvalidException("Invlaid mon"); );
    }

    /** setYear
     *  @param yyyy new value for year
     *  sets year to yyyy
     */
    private void setYear( int yyyy ) {
        try{ if(yyyy>=0)
            year = yyyy;
        else{
            year=DEFAULT_YEAR;
            throw new LocalDateInvalid("Invalid Year, only positive/AD years"+
                    " resetted to default year");
        }
        } catch(LocalDateInvalid die){
            die.getMessage();
        }

    }

    /** isLeapYear boolean;
     * @param yyyy a value for year
     * @return true if yyyy is a leap year
     *         false, otherwise
     */
    private boolean isLeapYear(int yyyy){
        if((yyyy%4==0) && ((yyyy%100!=0) || (yyyy%400==0) )){
            return true;
        }
        return false;
    }

    /** toString
     *  @return String
     *  returns date in mm/dd/yyyy format
     */
    public String toString( ) {
        return month + "/" + day + "/" + year;
    }

    /** equals
     *  @param   d  Date object to compare to this
     *  @return  true if d is equal to this
     *           false, otherwise
     */
    public boolean equals( LocalDate d ) {
        if ( month == d.month
                && day == d.day
                && year == d.year )
            return true;
        else
            return false;
    }

}
