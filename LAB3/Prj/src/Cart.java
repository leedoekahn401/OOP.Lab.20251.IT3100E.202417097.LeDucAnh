package Lab3.src;

import java.util.LinkedList;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    LinkedList<DigitalVideoDisc> itemsOrdered = new LinkedList<>();

    public void addDigitalVideoDisc(DigitalVideoDisc a) {
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
            itemsOrdered.add(a);
            System.out.println(a.getTitle() + " has been added.");
            System.out.println("Cost: " + totalCost());
        } else {
            System.out.println("The cart is full!");
        }
    }

    public double totalCost() {
        double sum = 0;
        for (int i = 0; i < itemsOrdered.size(); i++) {
            sum += itemsOrdered.get(i).getCost();
        }
        return sum;
    }


    public void removeDigitalVideoDisc(DigitalVideoDisc a) {
        if (itemsOrdered.remove(a)) {
            System.out.println("The disc has been removed.");
        } else {
            System.out.println("The disc is not in the cart.");
        }
    }
    /*
    public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
        for (int i = 0; i < dvdList.length; i++) {
            addDigitalVideoDisc(dvdList[i]);
        }
    }
    */

    //This work better
    public void addDigitalVideoDisc(DigitalVideoDisc... dvds) {
        for (DigitalVideoDisc dvd : dvds) {
            addDigitalVideoDisc(dvd);
        }
    }

    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        addDigitalVideoDisc(dvd1);
        addDigitalVideoDisc(dvd2);
    }

    public void printCart() {
        System.out.println("CART");
        System.out.println("Ordered Items:");
        int count = 1;
        for (DigitalVideoDisc disc : itemsOrdered) {
            System.out.println((count) + ". DVD - " + disc.toString());
            count++;
        }

        // FIX: Re-used the totalCost() method instead of recalculating it here.
        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("---");
    }

    public void searchById(int id) {
        boolean found = false;
        System.out.println("Searching for DVD with ID: " + id);
        for (DigitalVideoDisc disc : itemsOrdered) {
            if (disc.isIdMatch(id)) {
                System.out.println("Found: " + disc.toString());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No match found for ID: " + id);
        }
    }

    public void searchByTitle(String title) {
        boolean found = false;
        System.out.println("Searching for DVD with title: \"" + title + "\"");

        for (DigitalVideoDisc disc : itemsOrdered) {
            if (disc.isTitleMatch(title)) {
                System.out.println("Found: " + disc.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No match found for title: \"" + title + "\"");
        }
    }
}