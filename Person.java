public class Person {
    private String _name;
    private String _id;
    private Date _birthDate;

    final String DEFAULT_NAME = "Someone";
    final String DEFAULT_ID = "000000000";

    /**
     * Constructor for Person.
     */
    public Person(String name, int day, int month, int year, String id) {

        // Set Person's name
        setName(name);

        // Set Person's id
        setId(id);

        // Set Person's birth date
        setDateOfBirth(new Date(day, month, year));
    }

    /**
     * Copy constructor for Person.
     * 
     * @param other
     */
    public Person(Person other) {
        _name = other._name;
        _id = other._id;
        _birthDate = other._birthDate;
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
        if (name != "") {
            _name = name;
        } else {
            _name = DEFAULT_NAME;
        }
    }

    /**
     * Sets the id of the person.
     * If the ID provided is invalid, set the default ID (000000000)
     * 
     * @param id
     */
    public void setId(String id) {
        if (id.length() != 9) {
            _id = DEFAULT_ID;
        } else {
            _id = id;
        }
    }

    /**
     * Set the birth date of the person.
     * 
     * @param date
     */
    public void setDateOfBirth(Date date) {
        _birthDate = date;
    }

    public String toString() {
        return "Name: " + _name + "\nID: " + _id + "\nDate of birth: " + _birthDate.toString();
    }

    public int compareTo(Person other) {
        return 0;
    }

    // REMOVE THIS BEFORE SUBMITTING
    public static void main(String[] args) {
        Person person = new Person("John Doe", 28, 2, 1996, "123456789");
        System.out.println(person.toString());
    }
}
