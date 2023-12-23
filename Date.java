/**
 * Sets the date to a given input.
 * If one of the values are invalid, sets the date to the default date
 * (1/1/2000).
 * 
 * @param day
 * @param month
 * @param year
 */
public class Date {
    // Object attributes declaration
    private int _day;
    private int _month;
    private int _year;

    // Month valid max days (1-31)
    private final int MAX_MONTH_DAYS = 31;
    private final int MIN_MONTH_DAYS = 1;

    // February valid max days for leap and non-leap years (28-29)
    private final int MAX_FEB_LEAP_YEAR = 29;
    private final int MAX_FEB_NON_LEAP_YEAR = 28;

    // Month valid range (1-12)
    private final int MAX_MONTH = 12;
    private final int MIN_MONTH = 1;

    // Default 1/1/2000 values.
    private final int DEFAULT_YEAR = 2000;
    private final int DEFAULT_MONTH = 1;
    private final int DEFAULT_DAY = 1;

    public Date(int day, int month, int year) {
        boolean isLeapYear = isLeapYear(year);

        // Check if month is valid (1-12)
        if (month >= MIN_MONTH && month <= MAX_MONTH) {
            _month = month;
        }

        // Check if day is valid (1-31)
        if (day >= MIN_MONTH_DAYS && day <= MAX_MONTH_DAYS) {
            // Check the case for the months that has 30 days
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day <= 30) {
                    _day = day;
                }
            } else if (month == 2) {
                // Check the case for February and if it's a leap year
                if (isLeapYear) {

                    // Expected value is between 1-29
                    if (day <= MAX_FEB_LEAP_YEAR) {
                        _day = day;
                    }
                } else {
                    // If year is not a leap year, the expected value is between 1-28
                    if (day <= MAX_FEB_NON_LEAP_YEAR) {
                        _day = day;
                    }
                }
            } else {
                // If the month is not February, April, June, September or November, the
                // expected value is between 1-31
                _day = day;
            }
        }

        // Check if year is valid (1-9999)
        if (year >= 1 && year <= 9999) {
            _year = year;
        }

        // If any of the values are invalid, set the default date (1/1/2000)
        if (_day == 0 || _month == 0 || _year == 0) {
            setDefaultDateValues();
        }
    }

    /**
     * Sets the current date to the default date (1/1/2000)
     */
    private void setDefaultDateValues() {
        _day = DEFAULT_DAY;
        _month = DEFAULT_MONTH;
        _year = DEFAULT_YEAR;
    }

    private boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the day of the date.
     * 
     * @return
     */
    public int getDay() {
        return _day;
    }

    /**
     * Returns the month of the date.
     * 
     * @return
     */
    public int getMonth() {
        return _month;
    }

    /**
     * Returns the year of the date.
     * 
     * @return
     */
    public int getYear() {
        return _year;
    }

    /**
     * Sets the day of the date.
     * 
     * @param day
     */
    public void setDay(int day) {
        _day = day;
    }

    /**
     * Sets the month of the date.
     * 
     * @param month
     */
    public void setMonth(int month) {
        _month = month;
    }

    /**
     * Sets the year of the date.
     * 
     * @param year
     */
    public void setYear(int year) {
        _year = year;
    }

    /**
     * Check if date input is equal to the current object's date.
     * 
     * @param other
     * @return boolean
     */
    public boolean equals(Date other) {
        return true;
    }

    /**
     * Check if date input is before the current object's date.
     * 
     * @param other
     * @return boolean
     */
    public boolean before(Date other) {
        return true;
    }

    /**
     * Check if date input is after the current object's date.
     * 
     * @param other
     * @return boolean
     */
    public boolean after(Date other) {
        return true;
    }

    /**
     * Returns the difference in days between the current object's date and the
     * input date.
     * 
     * @param other
     * @return int
     */
    public int difference(Date other) {
        return 0;
    }

    private int calculateDate(Date other) {
        return 0;
    }
}
