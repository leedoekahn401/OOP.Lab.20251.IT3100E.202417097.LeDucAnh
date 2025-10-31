package Lab3.src;

public class StoreTest {

    public static void main(String[] args) {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King","Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin","Animation", 18.99f);

        System.out.println(" Testing Add DVD ");
        store.addDVD(dvd1);
        store.addDVD(dvd2);
        store.addDVD(dvd3);
        store.printStore();
        System.out.println("\n Testing Remove DVD ");
        store.removeDVD(dvd2);
        store.printStore();
        store.removeDVD(dvd2);
    }
}