package Lab3.src;
public class DigitalVideoDisc {
    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;

    // --- NEW CONSTRUCTOR ---
    // This is the constructor you need to add.
    // The TestPassingParameter class calls new DigitalVideoDisc("Jungle"),
    // which matches this constructor.
    public DigitalVideoDisc(String title) {
        this.title = title;
    }

    // This is your original constructor
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDirector() {
        return director;
    }

    // I renamed this from length() to getLength() to follow Java conventions
    public int getLength() {
        return length;
    }

    public float getCost() {
        return cost;
    }
}