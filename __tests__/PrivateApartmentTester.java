public class PrivateApartmentTester {
    public static void main(String[] args) {
        Person ariel = new Person("Ariel", 21, 2, 1997, "123123123");
        Person david = new Person("David Stampa", 21, 2, 1997, "123123123");

        Apartment apartment = new Apartment(4, 81, 6500, ariel, 29, 2, 1999, 29, 2, 2004);
        Apartment apartment2 = new Apartment(4, 81, 6500, ariel, 1, 7, 2022, 1, 7, 2022);

        System.out.println("********** Apartment **********");
        System.out.println("ariel:\n" + ariel);
        System.out.println();
        System.out.println("david:\n" + david);
        System.out.println();
        System.out.println("apartment:\n" + apartment);
        System.out.println();
        System.out.println("Equals?:\n" + apartment.equals(apartment2));

        System.out.println();
        System.out.println("apartment:\n" + apartment);
        System.out.println();
        // System.out.println();
        apartment.extendRentalPeriod(3);
        System.out.println("apartment:\n" + apartment);

        System.out.println();
        int daysLeft = apartment.daysLeft(new Date(1, 7, 2006));
        System.out.println("daysLeft:\n" + daysLeft);

    }
}
