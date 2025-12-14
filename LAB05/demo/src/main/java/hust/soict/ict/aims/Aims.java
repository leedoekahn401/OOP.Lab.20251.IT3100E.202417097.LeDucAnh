package hust.soict.ict.aims;

import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.media.Book;
import hust.soict.ict.aims.media.CompactDisc;
import hust.soict.ict.aims.media.DigitalVideoDisc;
import hust.soict.ict.aims.media.Media;
import hust.soict.ict.aims.media.Playable;
import hust.soict.ict.aims.store.Store;

import java.util.Scanner;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King", "Animation", 19.95f, 87, "Roger Allers");
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Star Wars", "Science Fiction", 24.95f, 124, "George Lucas");
        Book book1 = new Book(3, "Effective Java", "Programming", 30.00f);
        book1.addAuthor("Joshua Bloch");

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(book1);

        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    viewStore();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    seeCurrentCart();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    public static void viewStore() {
        store.printStore();
        int choice;
        do {
            storeMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    seeMediaDetails();
                    break;
                case 2:
                    addMediaToCart();
                    break;
                case 3:
                    playMedia();
                    break;
                case 4:
                    seeCurrentCart();
                    break;
                case 0:
                    System.out.println("Back to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4: ");
    }

    public static void seeMediaDetails() {
        System.out.print("Enter title of media: ");
        String title = scanner.nextLine();
        Media media = store.searchMedia(title);
        if (media != null) {
            System.out.println(media.toString());
            mediaDetailsMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                cart.addMedia(media);
            } else if (choice == 2) {
                if (media instanceof Playable) {
                    ((Playable) media).play();
                } else {
                    System.out.println("This media cannot be played.");
                }
            }
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2: ");
    }

    public static void addMediaToCart() {
        System.out.print("Enter title of media: ");
        String title = scanner.nextLine();
        Media media = store.searchMedia(title);
        if (media != null) {
            cart.addMedia(media);
            System.out.println("Current cart size: " + cart.getSize());
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void playMedia() {
        System.out.print("Enter title of media: ");
        String title = scanner.nextLine();
        Media media = store.searchMedia(title);
        if (media != null) {
            if (media instanceof Playable) {
                ((Playable) media).play();
            } else {
                System.out.println("This media cannot be played.");
            }
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void updateStore() {
        System.out.println("1. Add media");
        System.out.println("2. Remove media");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.println("Enter details (ID, Title, Category, Cost):");
            System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("Title: "); String title = scanner.nextLine();
            System.out.print("Category: "); String category = scanner.nextLine();
            System.out.print("Cost: "); float cost = scanner.nextFloat(); scanner.nextLine();
            Book b = new Book(id, title, category, cost);
            store.addMedia(b);
        } else if (choice == 2) {
            System.out.print("Enter title to remove: ");
            String title = scanner.nextLine();
            Media m = store.searchMedia(title);
            if(m!=null) store.removeMedia(m);
        }
    }

    public static void seeCurrentCart() {
        cart.print();
        int choice;
        do {
            cartMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    filterCart();
                    break;
                case 2:
                    sortCart();
                    break;
                case 3:
                    System.out.print("Enter title to remove: ");
                    String title = scanner.nextLine();
                    Media m = cart.searchMedia(title);
                    if(m!=null) cart.removeMedia(m);
                    else System.out.println("Media not found in cart.");
                    break;
                case 4:
                    System.out.print("Enter title to play: ");
                    String playTitle = scanner.nextLine();
                    Media pm = cart.searchMedia(playTitle);
                    if(pm!=null && pm instanceof Playable) ((Playable)pm).play();
                    else System.out.println("Cannot play.");
                    break;
                case 5:
                    System.out.println("Order placed! Cart is now empty.");
                    cart.empty();
                    break;
                case 0:
                    break;
            }
        } while (choice != 0);
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4-5: ");
    }

    public static void filterCart() {
        System.out.println("1. Filter by ID");
        System.out.println("2. Filter by Title");
        int option = scanner.nextInt();
        scanner.nextLine();
        if (option == 1) {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt(); scanner.nextLine();
            cart.searchById(id);
        } else {
            System.out.print("Enter Title: ");
            String title = scanner.nextLine();
            cart.searchByTitle(title);
        }
    }

    public static void sortCart() {
        System.out.println("1. Sort by Title");
        System.out.println("2. Sort by Cost");
        int option = scanner.nextInt();
        scanner.nextLine();
        if (option == 1) {
            cart.sortByTitle();
            cart.print();
        } else {
            cart.sortByCost();
            cart.print();
        }
    }
}