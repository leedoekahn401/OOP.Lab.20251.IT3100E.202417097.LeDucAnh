package Lab3.src;

public class CartTest {
    public static void main(String[] args) {

        Cart cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);
        cart.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 87, 24.95f);
        cart.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
                "Animation", 18.99f);
        cart.addDigitalVideoDisc(dvd3);

        cart.printCart();

        System.out.println("\n===== TEST SEARCH BY ID =====");
        cart.searchById(2);
        cart.searchById(10);

        System.out.println("\n===== TEST SEARCH BY TITLE =====");
        cart.searchByTitle("Aladin");
        cart.searchByTitle("Frozen");
    }
}