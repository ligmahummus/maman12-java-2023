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
        if (isNoOfRoomsValid(noOfRooms)) {
            _noOfRooms = noOfRooms;
        } else {
            _noOfRooms = DEFAULT_NO_OF_ROOMS;
        }

        // Set area
        if (isAreaValid(area)) {
            _area = area;
        } else {
            _area = DEFAULT_AREA;
        }

        // Set price
        if (isPriceValid(price)) {
            _price = price;
        } else {
            _price = DEFAULT_PRICE;
        }

        // Set tenant
        _tenant = new Person(p);

        // Set rental start date
        _rentalStartDate = new Date(startDay, startMonth, startYear);

        // Set rental end date
        Date rentalEndDate = new Date(endDay, endMonth, endYear);

        if (isRentalDatesValid(_rentalStartDate, rentalEndDate)) {
            _rentalEndDate = rentalEndDate;
        } else {
            _rentalEndDate = _rentalStartDate.addYearsToDate(DEFAULT_RENTAL_YEARS_GAP);
        }
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

    private boolean isNoOfRoomsValid(int num) {
        if (num <= 0) {
            return false;
        } else {
            return true;
        }
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

    private boolean isAreaValid(double area) {
        if (area <= MIN_AREA) {
            return false;
        } else {
            return true;
        }
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

    private boolean isPriceValid(double price) {
        if (price <= MIN_PRICE) {
            return false;
        } else {
            return true;
        }
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
     * Sets the rental start date of the apartment.
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

    private boolean isRentalDatesValid(Date startDate, Date endDate) {
        if (endDate.before(startDate) || endDate.equals(startDate)) {
            return false;
        } else {
            return true;
        }
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
        return _tenant;
    }

    /**
     * Returns the rental start date of the apartment.
     * 
     * @return rentalStartDate
     */
    public Date getRentalStartDate() {
        return _rentalStartDate;
    }

    /**
     * Returns the rental end date of the apartment.
     * 
     * @return rentalEndDate
     */
    public Date getRentalEndDate() {
        return _rentalEndDate;
    }

    /**
     * Returns a string representation of the apartment.
     */
    public String toString() {
        return "Number of rooms: " + _noOfRooms + "\nArea: " + _area + "\nPrice: " + _price + " NIS" + "\nTenant: "
                + _tenant.getName()
                + "\nRental start date: " + _rentalStartDate.toString() + "\nRental end date: "
                + _rentalEndDate.toString();
    }
}
