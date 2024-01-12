public class PrivatePersonTester {
    public static void main(String[] args) {
        Person p1 = new Person("David Stampa", 21, 2, 1997, "123123123");
        Person p2 = new Person("Ariel", 20, 2, 1997, "123123123");
        System.out.println(p1);
        System.out.println(p2.compareTo(p1));
    }
}
