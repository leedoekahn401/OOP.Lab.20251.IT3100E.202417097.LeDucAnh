package hust.soict.ict.aims;

import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.store.Store;
import hust.soict.ict.aims.media.*;
import hust.soict.ict.aims.exception.PlayerException;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Exercise 13: Complete Console Application
 * This is a menu-driven AIMS (A Simple Store Management System) application
 */
public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize store with sample data
        initializeStore();
        
        // Start main menu loop
        int choice;
        do {
            showMenu();
            choice = getIntInput();
            
            switch (choice) {
                case 1:
                    viewStore();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    viewCart();
                    break;
                case 0:
                    System.out.println("Thank you for using AIMS. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
        
        scanner.close();
    }

    // ===== Initialize Store with Sample Data =====
    private static void initializeStore() {
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Inception", "Thriller", 22.99f);
        
        Book book1 = new Book("1984", "Fiction", 15.99f);
        Book book2 = new Book("The Great Gatsby", "Classic", 12.99f);
        
        CompactDisc cd1 = new CompactDisc("Abbey Road", "Rock", "The Beatles", 14.99f);
        CompactDisc cd2 = new CompactDisc("Thriller", "Pop", "Michael Jackson", 13.99f);
        
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);
        store.addMedia(book1);
        store.addMedia(book2);
        store.addMedia(cd1);
        store.addMedia(cd2);
        
        System.out.println("Store initialized with 7 items.\n");
    }

    // ===== Main Menu =====
    private static void showMenu() {
        System.out.println("\n========== AIMS ==========");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("========================");
        System.out.print("Please choose a number (0-3): ");
    }

    // ===== View Store =====
    private static void viewStore() {
        int choice;
        do {
            System.out.println("\n========== STORE ==========");
            store.printStore();
            
            storeMenu();
            choice = getIntInput();
            
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
                    viewCart();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    // ===== Store Menu =====
    private static void storeMenu() {
        System.out.println("\n========== OPTIONS ==========");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("=============================");
        System.out.print("Please choose a number (0-4): ");
    }

    // ===== See Media Details =====
    private static void seeMediaDetails() {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine().trim();
        
        Media media = store.searchMediaByTitle(title);
        if (media == null) {
            System.out.println("Media not found!");
            return;
        }
        
        System.out.println("\n========== MEDIA DETAILS ==========");
        System.out.println(media.toString());
        System.out.println("===================================");
        
        // Show media details menu
        int choice;
        do {
            mediaDetailsMenu();
            choice = getIntInput();
            
            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    System.out.println("Number of DVDs in cart: " + cart.getQuantity());
                    break;
                case 2:
                    if (media instanceof Playable) {
                        try {
                            ((Playable) media).play();
                        } catch (PlayerException e) {
                            // Print comprehensive error information
                            System.err.println("\n=== PLAYBACK ERROR ===");
                            System.err.println("Error Message: " + e.getMessage());
                            System.err.println("Exception Type: " + e.toString());
                            System.err.println("Stack Trace:");
                            e.printStackTrace();
                            System.err.println("=====================\n");
                            
                            // Build error dialog message
                            String errorMessage = "Playback Error Occurred:\n\n" +
                                    "Message: " + e.getMessage() + "\n" +
                                    "Exception: " + e.getClass().getSimpleName() + "\n" +
                                    "Details: " + e.toString();
                            
                            // Display dialog box to user
                            JOptionPane.showMessageDialog(null, 
                                errorMessage, 
                                "Playback Error", 
                                JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        System.out.println("This media is not playable!");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    // ===== Media Details Menu =====
    private static void mediaDetailsMenu() {
        System.out.println("\n========== OPTIONS ==========");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("=============================");
        System.out.print("Please choose a number (0-2): ");
    }

    // ===== Add Media to Cart =====
    private static void addMediaToCart() {
        System.out.print("Enter media title to add to cart: ");
        String title = scanner.nextLine().trim();
        
        Media media = store.searchMediaByTitle(title);
        if (media == null) {
            System.out.println("Media not found!");
            return;
        }
        
        cart.addMedia(media);
        System.out.println("Number of DVDs in cart: " + cart.getQuantity());
    }

    // ===== Play Media =====
    private static void playMedia() {
        System.out.print("Enter media title to play: ");
        String title = scanner.nextLine().trim();
        
        Media media = store.searchMediaByTitle(title);
        if (media == null) {
            System.out.println("Media not found!");
            return;
        }
        
        if (media instanceof Playable) {
            System.out.println();
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                // Print comprehensive error information
                System.err.println("\n=== PLAYBACK ERROR ===");
                System.err.println("Error Message: " + e.getMessage());
                System.err.println("Exception Type: " + e.toString());
                System.err.println("Stack Trace:");
                e.printStackTrace();
                System.err.println("=====================\n");
                
                // Build error dialog message
                String errorMessage = "Playback Error Occurred:\n\n" +
                        "Media Title: " + media.getTitle() + "\n" +
                        "Message: " + e.getMessage() + "\n" +
                        "Exception: " + e.getClass().getSimpleName() + "\n" +
                        "Details: " + e.toString();
                
                // Display dialog box to user
                JOptionPane.showMessageDialog(null, 
                    errorMessage, 
                    "Playback Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("This media is not playable!");
        }
    }

    // ===== Update Store =====
    private static void updateStore() {
        int choice;
        do {
            System.out.println("\n========== UPDATE STORE ==========");
            System.out.println("1. Add a media to store");
            System.out.println("2. Remove a media from store");
            System.out.println("0. Back");
            System.out.println("==================================");
            System.out.print("Please choose a number (0-2): ");
            
            choice = getIntInput();
            
            switch (choice) {
                case 1:
                    addMediaToStore();
                    break;
                case 2:
                    removeMediaFromStore();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    // ===== Add Media to Store =====
    private static void addMediaToStore() {
        System.out.println("\n1. Add DVD  2. Add Book  3. Add CD");
        System.out.print("Choose media type (1-3): ");
        int type = getIntInput();
        
        System.out.print("Enter title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter category: ");
        String category = scanner.nextLine().trim();
        System.out.print("Enter cost: ");
        float cost = getFloatInput();
        
        Media media = null;
        switch (type) {
            case 1:
                media = new DigitalVideoDisc(title, category, cost);
                break;
            case 2:
                media = new Book(title, category, cost);
                break;
            case 3:
                System.out.print("Enter artist: ");
                String artist = scanner.nextLine().trim();
                media = new CompactDisc(title, category, artist, cost);
                break;
            default:
                System.out.println("Invalid type.");
                return;
        }
        
        if (media != null) {
            store.addMedia(media);
        }
    }

    // ===== Remove Media from Store =====
    private static void removeMediaFromStore() {
        System.out.print("Enter media title to remove: ");
        String title = scanner.nextLine().trim();
        
        Media media = store.searchMediaByTitle(title);
        if (media == null) {
            System.out.println("Media not found!");
            return;
        }
        
        store.removeMedia(media);
    }

    // ===== View Cart =====
    private static void viewCart() {
        if (cart.getQuantity() == 0) {
            System.out.println("\nYour cart is empty!");
            return;
        }
        
        int choice;
        do {
            System.out.println();
            cart.printCart();
            
            cartMenu();
            choice = getIntInput();
            
            switch (choice) {
                case 1:
                    filterCartMedia();
                    break;
                case 2:
                    sortCartMedia();
                    break;
                case 3:
                    removeMediaFromCart();
                    break;
                case 4:
                    playMediaFromCart();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0 && cart.getQuantity() > 0);
    }

    // ===== Cart Menu =====
    private static void cartMenu() {
        System.out.println("\n========== CART OPTIONS ==========");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("===================================");
        System.out.print("Please choose a number (0-5): ");
    }

    // ===== Filter Cart Media =====
    private static void filterCartMedia() {
        System.out.println("\n1. Filter by ID  2. Filter by Title");
        System.out.print("Choose filter type (1-2): ");
        int choice = getIntInput();
        
        if (choice == 1) {
            System.out.print("Enter media ID: ");
            int id = getIntInput();
            Media media = cart.searchById(id);
            if (media != null) {
                System.out.println("Found: " + media.toString());
            } else {
                System.out.println("Media with ID " + id + " not found in cart.");
            }
        } else if (choice == 2) {
            System.out.print("Enter media title: ");
            String title = scanner.nextLine().trim();
            Media media = cart.searchByTitle(title);
            if (media != null) {
                System.out.println("Found: " + media.toString());
            } else {
                System.out.println("Media \"" + title + "\" not found in cart.");
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // ===== Sort Cart Media =====
    private static void sortCartMedia() {
        System.out.println("\n1. Sort by Title  2. Sort by Cost");
        System.out.print("Choose sort type (1-2): ");
        int choice = getIntInput();
        
        if (choice == 1) {
            cart.sortByTitleThenCost();
        } else if (choice == 2) {
            cart.sortByCostThenTitle();
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // ===== Remove Media from Cart =====
    private static void removeMediaFromCart() {
        System.out.print("Enter media title to remove: ");
        String title = scanner.nextLine().trim();
        
        Media media = cart.searchByTitle(title);
        if (media == null) {
            System.out.println("Media not found in cart!");
            return;
        }
        
        cart.removeMedia(media);
    }

    // ===== Play Media from Cart =====
    private static void playMediaFromCart() {
        System.out.print("Enter media title to play: ");
        String title = scanner.nextLine().trim();
        
        Media media = cart.searchByTitle(title);
        if (media == null) {
            System.out.println("Media not found in cart!");
            return;
        }
        
        if (media instanceof Playable) {
            System.out.println();
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                // Print comprehensive error information
                System.err.println("\n=== PLAYBACK ERROR ===");
                System.err.println("Error Message: " + e.getMessage());
                System.err.println("Exception Type: " + e.toString());
                System.err.println("Stack Trace:");
                e.printStackTrace();
                System.err.println("=====================\n");
                
                // Build error dialog message
                String errorMessage = "Playback Error Occurred:\n\n" +
                        "Media Title: " + media.getTitle() + "\n" +
                        "Message: " + e.getMessage() + "\n" +
                        "Exception: " + e.getClass().getSimpleName() + "\n" +
                        "Details: " + e.toString();
                
                // Display dialog box to user
                JOptionPane.showMessageDialog(null, 
                    errorMessage, 
                    "Playback Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("This media is not playable!");
        }
    }

    // ===== Place Order =====
    private static void placeOrder() {
        System.out.println("\n========== ORDER CONFIRMATION ==========");
        System.out.println("Total items: " + cart.getQuantity());
        System.out.println("Total cost: $" + String.format("%.2f", cart.totalCost()));
        System.out.println("========================================");
        System.out.println("Order has been created successfully!");
        System.out.println("Your cart has been emptied.");
        cart.emptyCart();
    }

    // ===== Helper Methods =====
    private static int getIntInput() {
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            return value;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    private static float getFloatInput() {
        try {
            float   value = Float.parseFloat(scanner.nextLine().trim());
            return value;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }
}

