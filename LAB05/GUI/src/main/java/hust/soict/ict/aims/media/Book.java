package hust.soict.ict.aims.media;

import java.util.ArrayList;

public class Book extends Media {

    // ===== Authors field =====
    private ArrayList<String> authors = new ArrayList<String>();

    // ===== Constructor =====
    public Book() {
        super();
    }

    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }

    // ===== Methods for authors =====
    public void addAuthor(String authorName) throws IllegalArgumentException {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be null or empty");
        }
        if (authors.contains(authorName)) {
            throw new IllegalArgumentException("Author already exists: " + authorName);
        }
        authors.add(authorName);
    }

    public void removeAuthor(String authorName) throws IllegalArgumentException {
        if (authorName == null || !authors.contains(authorName)) {
            throw new IllegalArgumentException("Author not found: " + authorName);
        }
        authors.remove(authorName);
    }

    @Override
    public String toString() {
        return "Book[id=" + this.getId() + ", title=" + this.getTitle() + ", category=" + this.getCategory() + 
               ", cost=" + this.getCost() + ", authors=" + authors + "]";
    }
}