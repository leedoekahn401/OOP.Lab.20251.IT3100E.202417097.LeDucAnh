package hust.soict.ict.aims.cart;

import hust.soict.ict.aims.media.Media;
import java.util.ArrayList;

public class Cart {

    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    // ===== Add Media =====
    public void addMedia(Media media) throws IllegalArgumentException {
        if (media == null) {
            throw new IllegalArgumentException("Media cannot be null");
        }
        if (itemsOrdered.contains(media)) {
            throw new IllegalArgumentException("Media already in cart: " + media.getTitle());
        }
        itemsOrdered.add(media);
        System.out.println("Added: " + media.getTitle());
    }

    // ===== Remove Media =====
    public void removeMedia(Media media) throws IllegalArgumentException {
        if (media == null) {
            throw new IllegalArgumentException("Media cannot be null");
        }
        if (!itemsOrdered.contains(media)) {
            throw new IllegalArgumentException("Media not found in cart: " + media.getTitle());
        }
        itemsOrdered.remove(media);
        System.out.println("Removed: " + media.getTitle());
    }

    // ===== Total Cost =====
    public float totalCost() {
        float total = 0;
        for (Media m : itemsOrdered) {
            total += m.getCost();
        }
        return total;
    }

    // ===== Display items =====
    public void showCart() {
        System.out.println("===== Cart Items =====");
        for (Media m : itemsOrdered) {
            System.out.println(m.getTitle() + " - " + m.getCost());
        }
        System.out.println("Total cost: " + totalCost());
    }

    // ===== Sort by Title then Cost =====
    public void sortByTitleThenCost() {
        java.util.Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        System.out.println("Sorted by Title (A-Z), then by Cost (descending)");
    }

    // ===== Sort by Cost then Title =====
    public void sortByCostThenTitle() {
        java.util.Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        System.out.println("Sorted by Cost (descending), then by Title (A-Z)");
    }

    // ===== Print Cart with detailed info =====
    public void printCart() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        float total = 0;
        int i = 1;
        for (Media m : itemsOrdered) {
            System.out.println(i + ". " + m.toString());
            total += m.getCost();
            i++;
        }
        System.out.println("Total cost: " + total + " $");
        System.out.println("***************************************************");
    }

    // ===== Search by Title in Cart =====
    public Media searchByTitle(String title) {
        for (Media m : itemsOrdered) {
            if (m.getTitle().equals(title)) {
                return m;
            }
        }
        return null;
    }

    // ===== Search by ID in Cart =====
    public Media searchById(int id) {
        for (Media m : itemsOrdered) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    // ===== Get number of items in cart =====
    public int getQuantity() {
        return itemsOrdered.size();
    }

    // ===== Empty cart =====
    public void emptyCart() {
        itemsOrdered.clear();
    }

    // ===== Get items list =====
    public ArrayList<Media> getItemsOrdered() {
        return new ArrayList<>(itemsOrdered);
    }
}