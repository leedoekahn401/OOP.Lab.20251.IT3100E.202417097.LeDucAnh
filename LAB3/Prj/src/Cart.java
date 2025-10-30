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

}