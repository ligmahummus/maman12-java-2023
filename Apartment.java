/**
 * Apartment class represents an apartment, with number of rooms, area, pric
 * and a current resident.
 * 
 * @version 12/2023
 * @author Ariel Aharon 20441
 */
public class Apartment {
    // Object attributes declaration
    private int _noOfRooms; // Must be int and positive
    private double _area; // Must be double and positive
    private double _price; // Must be double and positive
    private Person _tenant;
    private Date _rentalStartDate;
    private Date _rentalEndDate; // Must be after rentalStartDate

    // Default values
    private final int DEFAULT_NO_OF_ROOMS = 3;
    private final double DEFAULT_AREA = 80;
    private final double DEFAULT_PRICE = 5000;

    private final int MAX_RENTAL_DIFF_PERIOD = 90;
    private final int DEFAULT_RENTAL_YEARS_GAP = 1;
    private final int DEFAULT_NEW_TENANT_LEASE_YEARS = 1;

    private final double MIN_PRICE = 0;
    private final double MIN_AREA = 0;
    private final int MIN_NO_OF_ROOMS = 0;
    private final int MIN_RENTAL_PREIOD_EXTENSION = 0;

    /**
     * Apartment constructor accepts number of rooms, area, price, tenant, rental
     * dates.
     * 
     * @param noOfRooms
     * @param area
     * @param price
     * @param tenant
     * @param rentalStartDate
     * @param rentalEndDate
     */
    public Apartment(int noOfRooms, double area, double price, Person p, int startDay, int startMonth, int startYear,
            int endDay, int endMonth, int endYear) {
        // Set number of rooms
        setAndValidateNoOfRooms(noOfRooms);

        // Set area
        setAndValidateArea(area);

        // Set price
        setAndValidatePrice(price);

        // Set tenant
        setTenant(p);

        // Create new rental start date
        Date rentalStartDate = new Date(startDay, startMonth, startYear);

        // Create new rental end date
        Date rentalEndDate = new Date(endDay, endMonth, endYear);

        // Set rental dates
        setAndValidateRentalDates(rentalStartDate, rentalEndDate);
    }

    /**
     * Apartment constructor accepts another Apartment object and copies its values.
     * 
     * @param other
     */
    public Apartment(Apartment other) {
        _noOfRooms = other._noOfRooms;
        _area = other._area;
        _price = other._price;
        _tenant = new Person(other._tenant);
        _rentalStartDate = new Date(other._rentalStartDate);
        _rentalEndDate = new Date(other._rentalEndDate);
    }

    /**
     * Sets the number of rooms in the apartment.
     * 
     * @param num
     */
    public void setNoOfRooms(int num) {
        if (isNoOfRoomsValid(num)) {
            _noOfRooms = num;
        }
    }

    /**
     * Sets the number of rooms in the apartment if valid, else sets to default
     * value.
     * 
     * @param num
     */
    private void setAndValidateNoOfRooms(int num) {
        if (isNoOfRoomsValid(num)) {
            _noOfRooms = num;
        } else {
            _noOfRooms = DEFAULT_NO_OF_ROOMS;
        }
    }

    /**
     * Returns true if the number of rooms is valid, false otherwise.
     * 
     * @param num
     * @return true if valid, false otherwise
     */
    private boolean isNoOfRoomsValid(int num) {
        return num > MIN_NO_OF_ROOMS;
    }

    /**
     * Sets the area of the apartment.
     * 
     * @param area
     */
    public void setArea(double area) {
        if (isAreaValid(area)) {
            _area = area;
        }
    }

    /**
     * Sets the area of the apartment if valid, else sets to default value.
     * 
     * @param area
     */
    private void setAndValidateArea(double area) {
        if (isAreaValid(area)) {
            _area = area;
        } else {
            _area = DEFAULT_AREA;
        }
    }

    /**
     * Returns true if the area is valid, false otherwise.
     * 
     * @param area
     * @return true if valid, false otherwise
     */
    private boolean isAreaValid(double area) {
        return area > MIN_AREA;
    }

    /**
     * Sets the price of the apartment.
     * 
     * @param price
     */
    public void setPrice(double price) {
        if (isPriceValid(price)) {
            _price = price;
        }
    }

    /**
     * Sets the price of the apartment if valid, else sets to default value.
     * 
     * @param price
     */
    private void setAndValidatePrice(double price) {
        if (isPriceValid(price)) {
            _price = price;
        } else {
            _price = DEFAULT_PRICE;
        }
    }

    /**
     * Returns true if the price is valid, false otherwise.
     * 
     * @param price
     * @return true if valid, false otherwise
     */
    private boolean isPriceValid(double price) {
        return price > MIN_PRICE;
    }

    /**
     * Sets the tenant of the apartment.
     * 
     * @param p
     */
    public void setTenant(Person p) {
        _tenant = new Person(p);
    }

    /**
     * Sets the rental start date of the apartment if dates are valid.
     * 
     * @param d
     */
    public void setRentalStartDate(Date d) {
        if (isRentalDatesValid(d, _rentalEndDate)) {
            _rentalStartDate = new Date(d);
        }
    }

    /**
     * Sets the rental end date of the apartment.
     * 
     * @param d
     */
    public void setRentalEndDate(Date d) {
        if (isRentalDatesValid(_rentalStartDate, d)) {
            _rentalEndDate = new Date(d);
        }
    }

    /**
     * Sets the rental start and end dates of the apartment if valid, else sets to
     * default values.
     * 
     * @param startDate
     * @param endDate
     */
    private void setAndValidateRentalDates(Date startDate, Date endDate) {
        _rentalStartDate = startDate;

        if (isRentalDatesValid(startDate, endDate)) {
            _rentalEndDate = endDate;
        } else {
            _rentalEndDate = startDate.addYearsToDate(DEFAULT_RENTAL_YEARS_GAP);
        }
    }

    /**
     * Returns true if the rental dates are valid, false otherwise.
     * 
     * @param startDate
     * @param endDate
     * @return true if valid, false otherwise
     */
    private boolean isRentalDatesValid(Date startDate, Date endDate) {
        return startDate.before(endDate) && !startDate.equals(endDate);
    }

    /**
     * Extend the rental period of the apartment by years, updates end of rental
     * date.
     * 
     * @param years
     */
    public void extendRentalPeriod(int years) {
        if (years > MIN_RENTAL_PREIOD_EXTENSION) {
            // Extend rental period by years
            _rentalEndDate = _rentalEndDate.addYearsToDate(years);
        }
    }

    /**
     * Accepts an apartment and returns if the apartment objects are equal.
     * 
     * @param other
     * @return true if equal, false otherwise
     */
    public boolean equals(Apartment other) {
        boolean isNoOfRoomsEqual = _noOfRooms == other._noOfRooms;

        boolean isRentalStartDateEqual = _rentalStartDate.equals(other._rentalStartDate);
        boolean isRentalEndDateEqual = _rentalEndDate.equals(other._rentalEndDate);

        boolean isTenantEqual = _tenant.equals(other._tenant);

        boolean isAreaEqual = _area == other._area;
        boolean isPriceEqual = _price == other._price;

        if (isNoOfRoomsEqual && isAreaEqual && isPriceEqual && isTenantEqual && isRentalStartDateEqual
                && isRentalEndDateEqual) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Accepts a date and returns the number of days left until the end of the
     * rental period.
     * If the date is after the end of the rental period, return -1.
     * 
     * @param d
     * @return daysLeft
     */
    public int daysLeft(Date d) {
        if (d.after(_rentalEndDate)) {
            return -1;
        } else {
            return _rentalEndDate.difference(d);
        }
    }

    /**
     * Accepts a date, person and price
     * Appartment updated if start date is after and less than or equal 90 days
     * before the end of the rental period, and price is greater than or equal to
     * the current price, and future tenant is younger.
     * 
     * @param startDate
     * @param p
     * @param price
     * @return true if tenant was changed, false otherwise
     */
    public boolean changeTenant(Date startDate, Person p, double price) {
        boolean isStartDateAfter = startDate.after(_rentalStartDate);
        boolean isNinetyDaysBeforeRentalEnd = _rentalEndDate.difference(startDate) <= MAX_RENTAL_DIFF_PERIOD;

        boolean isPriceGreaterOrEqual = price >= _price;
        boolean isNewTenantYounger = p.compareTo(_tenant) == -1;

        if (isStartDateAfter && isNinetyDaysBeforeRentalEnd && isPriceGreaterOrEqual && isNewTenantYounger) {
            _tenant = new Person(p);
            _rentalStartDate = new Date(startDate);
            _rentalEndDate = _rentalStartDate.addYearsToDate(DEFAULT_NEW_TENANT_LEASE_YEARS);
            _price = price;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the number of rooms in the apartment.
     * 
     * @return noOfRooms
     */
    public int getNoOfRooms() {
        return _noOfRooms;
    }

    /**
     * Returns the area of the apartment.
     * 
     * @return area
     */
    public double getArea() {
        return _area;
    }

    /**
     * Returns the price of the apartment.
     * 
     * @return price
     */
    public double getPrice() {
        return _price;
    }

    /**
     * Returns the tenant of the apartment.
     * 
     * @return tenant
     */
    public Person getTenant() {
        return new Person(_tenant);
    }

    /**
     * Returns the rental start date of the apartment.
     * 
     * @return rentalStartDate
     */
    public Date getRentalStartDate() {
        return new Date(_rentalStartDate);
    }

    /**
     * Returns the rental end date of the apartment.
     * 
     * @return rentalEndDate
     */
    public Date getRentalEndDate() {
        return new Date(_rentalEndDate);
    }

    /**
     * Returns a string representation of the apartment.
     */
    public String toString() {
        return "Number of rooms: " + _noOfRooms + "\nArea: " + _area + "\nPrice: " + _price + " NIS" + "\nTenant name: "
                + _tenant.getName()
                + "\nRental start date: " + _rentalStartDate.toString() + "\nRental end date: "
                + _rentalEndDate.toString();
    }
}
