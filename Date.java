/**
 * Sets the date to a given input.
 * If one of the values are invalid, sets the date to the default date
 * (1/1/2000).
 * 
 * @version 02/2023
 * @author Ariel Aharon 20441
 */
public class Date {
    // Object attributes declaration
    private int _day;
    private int _month;
    private int _year;

    // Month valid max days (1-31)
    private final int MAX_MONTH_DAYS = 31;
    private final int MAX_MONTH_THIRTY_DAYS = 30;
    private final int MIN_MONTH_DAYS = 1;

    // February valid max days for leap and non-leap years (28-29)
    private final int MAX_FEB_LEAP_YEAR = 29;
    private final int MAX_FEB_NON_LEAP_YEAR = 28;

    // Month valid range (1-12)
    private final int MAX_MONTH = 12;
    private final int MIN_MONTH = 1;

    // Year valid range (1000-9999)
    private final int MAX_YEAR = 9999;
    private final int MIN_YEAR = 1000;

    // Maxmimum standard days / month number.
    private final int MAX_STD_SINGLE_DIGIT = 9;

    // Default 1/1/2000 values.
    private final int DEFAULT_YEAR = 2000;
    private final int DEFAULT_MONTH = 1;
    private final int DEFAULT_DAY = 1;

    // Relevant months that are used to calculate and validate the object.
    private final int FEBRUARY = 2;
    private final int APRIL = 4;
    private final int JUNE = 6;
    private final int SEPTEMBER = 9;
    private final int NOVEMBER = 11;

    /**
     * Date object takes day, month and year as input.
     * If one of the values are invalid, sets the date to the default date
     * (1/1/2000)
     * 
     * @param day
     * @param month
     * @param year
     */
    public Date(int day, int month, int year) {
        // Make sure to leave these functions in the right order where day is the last.
        // This is because the day setter function validates the day according to given
        // month and year.

        // Check if month is valid (1-12)
        setMonth(month);

        // Check if year is valid (1-9999)
        setYear(year);

        // Check if day is valid (1-31)
        setDay(day);

        // If any of the values are invalid, set the default date (1/1/2000)
        if (_day == 0 || _month == 0 || _year == 0) {
            setDefaultDateValues();
        }
    }

    /**
     * Date object takes a different Date object as input and return's a deep copy
     * of it.
     */
    public Date(Date other) {
        // Copy the values from the input date, we don't need to use the setter methods
        // and validate the values, because we know they are valid.
        _day = other.getDay();
        _month = other.getMonth();
        _year = other.getYear();
    }

    /**
     * Sets the current date to the default date (1/1/2000)
     */
    private void setDefaultDateValues() {
        _day = DEFAULT_DAY;
        _month = DEFAULT_MONTH;
        _year = DEFAULT_YEAR;
    }

    /**
     * Check if the year is a leap year.
     * 
     * @param year
     * @return boolean
     */
    private boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the day of the object's date.
     * 
     * @return day
     */
    public int getDay() {
        return _day;
    }

    /**
     * Returns the month of the object's date.
     * 
     * @return month
     */
    public int getMonth() {
        return _month;
    }

    /**
     * Returns the year of the object's date.
     * 
     * @return year
     */
    public int getYear() {
        return _year;
    }

    /**
     * Sets the month of the object's date.
     * 
     * @param month
     */
    public void setMonth(int monthToSet) {
        if (monthToSet >= MIN_MONTH && monthToSet <= MAX_MONTH) {
            _month = monthToSet;
        }
    }

    /**
     * Sets the year of the date.
     * 
     * @param year
     */
    public void setYear(int yearToSet) {
        if (yearToSet >= MIN_YEAR && yearToSet <= MAX_YEAR) {
            _year = yearToSet;
        }
    }

    /**
     * Sets the day of the date.
     * 
     * @param day
     */
    public void setDay(int dayToSet) {
        // Validates the day according to the current set month
        setDay(dayToSet, _month);
    }

    private void setDay(int dayToSet, int month) {
        boolean isLeapYear = isLeapYear(_year);

        // Check if day is valid (1-31)
        if (dayToSet >= MIN_MONTH_DAYS && dayToSet <= MAX_MONTH_DAYS) {
            // Check the case for the months that has 30 days
            if (month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) {
                if (dayToSet <= MAX_MONTH_THIRTY_DAYS) {
                    _day = dayToSet;
                }
            } else if (month == 2) {
                // Check the case for February and if it's a leap year
                if (isLeapYear) {

                    // Expected value is between 1-29
                    if (dayToSet <= MAX_FEB_LEAP_YEAR) {
                        _day = dayToSet;
                    }
                } else {
                    // If year is not a leap year, the expected value is between 1-28
                    if (dayToSet <= MAX_FEB_NON_LEAP_YEAR) {
                        _day = dayToSet;
                    }
                }
            } else {
                // If the month is not February, April, June, September or November, the
                // expected value is between 1-31
                _day = dayToSet;
            }
        }
    }

    /**
     * Check if date input is equal to the current object's date.
     * 
     * @param other
     * @return boolean
     */
    public boolean equals(Date other) {
        int otherDay = other.getDay(); // Get the day of the input date
        int otherMonth = other.getMonth(); // Get the month of the input date
        int otherYear = other.getYear(); // Get the year of the input date

        // Check if the date is equal to the current object's date
        if (_day == otherDay && _month == otherMonth && _year == otherYear) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if date input is before the current object's date.
     * 
     * @param other
     * @return boolean
     */
    public boolean before(Date other) {
        int otherDay = other.getDay(); // Get the day of the input date
        int otherMonth = other.getMonth(); // Get the month of the input date
        int otherYear = other.getYear(); // Get the year of the input date

        // Firstly check if the year is before the current object's year
        if (_year < otherYear) {
            return true;

            // If the year is equal, check if the month is before the current object's month
        } else if (_year == otherYear) {

            // If the month is before the current object's month, return true
            if (_month < otherMonth) {
                return true;

                // If the month is equal, check if the day is before the current object's day
            } else if (_month == otherMonth) {

                // If the day is before the current object's day, return true
                if (_day < otherDay) {
                    return true;
                }
            }
        }

        // If none of the above conditions are true, return false
        return false;
    }

    /**
     * Check if date input is after the current object's date.
     * 
     * @param other
     * @return boolean
     */
    public boolean after(Date other) {
        // Before method excludes the case where the dates are equal.
        // Therefore, if the date is not before the current object's date, it must be
        // after.
        if (before(other) && other.getDay() != _day && other.getMonth() != _month && other.getYear() != _year) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns the difference in days between the current object's date and the
     * input date.
     * 
     * @param other
     * @return int
     */
    public int difference(Date other) {
        int otherDay = other.getDay();
        int otherMonth = other.getMonth();
        int otherYear = other.getYear();

        // Using the calculateDate function to calculate the difference in days between
        // the two dates.
        int difference = calculateDate(otherDay, otherMonth, otherYear) - calculateDate(_day, _month, _year);

        // Always return a positive days number.
        return Math.abs(difference);

    }

    // computes the day number since the beginning of the Christian counting of
    // years.
    private int calculateDate(int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
    }

    /**
     * Returns the day of the week that the date occurs on.
     * 
     * @param num
     * @return
     */
    private String standardizeNumber(int num) {
        return num <= MAX_STD_SINGLE_DIGIT ? "0" + num : "" + num;
    }

    /**
     * Returns a string representation of the date.
     * 
     * @return String
     */
    public String toString() {
        return standardizeNumber(_day) + "/" + standardizeNumber(_month) + "/" + _year;
    }

    /**
     * Adds days to the current date.
     * 
     * @param num
     * @return Date
     */
    public Date addYearsToDate(int num) {

        // Calculate the new year with the input number of years
        int newYear = _year + num;

        // Check if the new year is a leap year
        boolean isNewYearLeapYear = isLeapYear(newYear);

        // Assume the new day is the current set day
        int newDay = _day;

        // Consider the case of leap year in February and the current day is the last
        // day of the month.
        if (isNewYearLeapYear && _month == FEBRUARY && (_day == MAX_FEB_NON_LEAP_YEAR || _day == MAX_FEB_LEAP_YEAR)) {
            // Set the new day to the last day of February in a leap year or a non-leap
            // year.
            newDay = isNewYearLeapYear ? MAX_FEB_LEAP_YEAR : MAX_FEB_NON_LEAP_YEAR;
        }

        return new Date(newDay, _month, newYear);
    }
}
