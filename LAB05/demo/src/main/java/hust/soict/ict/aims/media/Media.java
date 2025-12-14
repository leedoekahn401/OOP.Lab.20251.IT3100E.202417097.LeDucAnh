package hust.soict.ict.aims.media;

import java.util.Comparator;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST =
            new MediaComparator(MediaComparator.BY_TITLE_COST);

    public static final Comparator<Media> COMPARE_BY_COST_TITLE =
            new MediaComparator(MediaComparator.BY_COST_TITLE);

    public Media() {
    }

    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Media)) return false;
        return ((Media) o).getTitle().equals(this.title);
    }

    @Override
    public String toString() {
        return "Media - " + title + " - " + category + " - " + cost + "$";
    }
}