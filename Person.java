/**
 * Person class represents a person with a name, id and birth date.
 * 
 * @version 12/2023
 * @author Ariel Aharon 20441
 */
public class Person {
    // Object attributes declaration
    private String _name;
    private String _id;
    private Date _birthDate;

    // Default values
    private final String DEFAULT_NAME = "Someone";
    private final String DEFAULT_ID = "000000000";

    private final int PERSON_IS_OLDER = 1;
    private final int PERSON_IS_YOUNGER = -1;
    private final int PERSON_IS_SAME_AGE = 0;

    /**
     * Person accepts a name, birth date and id and sets the attributes.
     * 
     * @param name
     * @param day
     * @param month
     * @param year
     * @param id
     */
    public Person(String name, int day, int month, int year, String id) {

        // Set Person's name
        setAndValidateName(name);

        // Set Person's id
        setAndValidateID(id);

        // Set Person's birth date
        setDateOfBirth(new Date(day, month, year));
    }

    /**
     * Person accepts another Person object and copies its attributes.
     * 
     * @param other
     */
    public Person(Person other) {
        _name = other._name;
        _id = other._id;
        _birthDate = new Date(other._birthDate);
    }

    /**
     * Returns the name of the person.
     * 
     * @return name
     */
    public String getName() {
        return _name;
    }

    /**
     * Returns the id of the person.
     * 
     * @return
     */
    public String getId() {
        return _id;
    }

    /**
     * Returns the birth date of the person.
     * 
     * @return birthDate
     */
    public Date getDateOfBirth() {
        return _birthDate;
    }

    /**
     * Sets the name of the person.
     * 
     * @param name
     */
    public void setName(String name) {
        if (isNameValid(name)) {
            _name = name;
        }
    }

    /**
     * Sets the name of the person if valid, else sets a default value.
     * 
     * @param name
     */
    private void setAndValidateName(String name) {
        if (isNameValid(name)) {
            _name = name;
        } else {
            _name = DEFAULT_NAME;
        }
    }

    /**
     * Checks if the name provided is valid.
     * 
     * @param name
     * @return true if valid, false otherwise
     */
    private boolean isNameValid(String name) {
        if (name == "") {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Sets the id of the person.
     * If the ID provided is invalid, set the default ID (000000000)
     * 
     * @param id
     */
    public void setId(String id) {
        if (isIDValid(id)) {
            _id = id;
        }
    }

    /**
     * Sets the id of the person if valid, else sets a default value.
     * 
     * @param id
     */
    private void setAndValidateID(String id) {
        if (isIDValid(id)) {
            _id = id;
        } else {
            _id = DEFAULT_ID;
        }
    }

    /**
     * Checks if the ID provided is valid.
     * 
     * @param id
     * @return true if valid, false otherwise
     */
    private boolean isIDValid(String id) {
        if (id.length() != 9) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Set the birth date of the person.
     * 
     * @param date
     */
    public void setDateOfBirth(Date date) {
        _birthDate = new Date(date);
    }

    /**
     * Returns a string representation of the person.
     * 
     * @return string
     */
    public String toString() {
        return "Name: " + _name + "\nID: " + _id + "\nDate of birth: " + _birthDate.toString();
    }

    /**
     * Accepts another Person object and compares their ages.
     * 
     * @param other
     * @return 0 if equal, 1 if older, -1 if younger
     */
    public int compareTo(Person other) {
        if (_birthDate.before(other._birthDate)) {
            return PERSON_IS_OLDER;
        } else if (_birthDate.equals(other._birthDate)) {
            return PERSON_IS_SAME_AGE;
        } else {
            return PERSON_IS_YOUNGER;
        }
    }

    /**
     * Accepts another Person object and checks if they are equal.
     * 
     * @param other
     * @return boolean
     */
    public boolean equals(Person other) {
        if (_name == other._name && _id == other._id && _birthDate.equals(other._birthDate)) {
            return true;
        } else {
            return false;
        }
    }
}
