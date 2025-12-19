package hust.soict.ict.aims.media;

import java.util.Comparator;

public abstract class Media {

    private static int nextId = 1;
    private int id;
    private String title;
    private String category;
    private float cost;

    // Comparators for sorting
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = 
        new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = 
        new MediaComparatorByCostTitle();

    public Media() {
        this.id = nextId++;
    }

    public Media(String title, String category, float cost) {
        this.id = nextId++;
        setCost(cost); // Use setter for validation
        this.title = title;
        this.category = category;
    }

    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        setCost(cost); // Use setter for validation
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public float getCost() { return cost; }
    
    public void setCost(float cost) throws IllegalArgumentException {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative: " + cost);
        }
        this.cost = cost;
    }

    // ===== Override equals =====
    /**
     * Two medias are equal if they have the same title.
     * Implements proper null checking and instanceof operator usage to prevent
     * NullPointerException and ClassCastException.
     * 
     * @param obj the object to compare with this media
     * @return true if both medias have the same title, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // Check if comparing with the same object reference
        if (this == obj) return true;
        
        // Check for null to prevent NullPointerException
        if (obj == null) return false;
        
        // Use instanceof to check type before casting
        if (!(obj instanceof Media)) return false;
        
        // Safe cast after instanceof check
        Media other = (Media) obj;
        
        // Handle null titles - both null or both equal
        if (this.title == null) return other.title == null;
        
        // Compare titles
        return this.title.equals(other.title);
    }

    @Override
    public int hashCode() {
        return (title == null) ? 0 : title.hashCode();
    }

    @Override
    public String toString() {
        return "Media[id=" + id + ", title=" + title + ", category=" + category + ", cost=" + cost + "]";
    }
}