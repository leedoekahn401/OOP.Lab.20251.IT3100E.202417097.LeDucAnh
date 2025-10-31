package Lab3.src;

public class Store {
    public static final int MAX_ITEMS_IN_STORE = 1000;
    private DigitalVideoDisc itemsInStore[] = new DigitalVideoDisc[MAX_ITEMS_IN_STORE];
    private int itemCount = 0;

    public void addDVD(DigitalVideoDisc dvd) {
        if (itemCount >= MAX_ITEMS_IN_STORE) {
            System.out.println("The store is full. Cannot add the disc '" + dvd.getTitle() + "'.");
        } else {
            itemsInStore[itemCount] = dvd;
            itemCount++;
            System.out.println("The disc '" + dvd.getTitle() + "' has been added to the store.");
        }
    }

    public void removeDVD(DigitalVideoDisc dvd) {
        boolean found = false;
        for (int i = 0; i < itemCount; i++) {
            if (itemsInStore[i].equals(dvd)) {
                found = true;
                for (int j = i; j < itemCount - 1; j++) {
                    itemsInStore[j] = itemsInStore[j + 1];
                }
                itemsInStore[itemCount - 1] = null;
                itemCount--;
                System.out.println("The disc '" + dvd.getTitle() + "' has been removed from the store.");
                break;
            }
        }

        if (!found) {
            System.out.println("The disc '" + dvd.getTitle() + "' is not in the store.");
        }
    }

    public void printStore() {
        System.out.println("STORE INVENTORY");
        if (itemCount == 0) {
            System.out.println("The store is empty.");
        } else {
            for (int i = 0; i < itemCount; i++) {
                System.out.println((i + 1) + ". " + itemsInStore[i].toString());
            }
        }
        System.out.println("---");
    }
}