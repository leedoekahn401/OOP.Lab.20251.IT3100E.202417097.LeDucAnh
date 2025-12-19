package hust.soict.ict.aims.screen;

import hust.soict.ict.aims.store.Store;
import hust.soict.ict.aims.media.Book;

import javax.swing.*;

/**
 * AddBookToStoreScreen - Screen to add Book to store
 * Input fields: Title, Category, Author, Cost
 */
public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle;
    private JTextField tfCategory;
    private JTextField tfAuthor;
    private JTextField tfCost;

    public AddBookToStoreScreen(Store store) {
        super(store);
        this.setTitle("Add Book to Store");
    }

    @Override
    protected JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Title
        tfTitle = new JTextField(30);
        panel.add(createLabeledInput("Title:", tfTitle));
        
        // Category
        tfCategory = new JTextField(30);
        panel.add(createLabeledInput("Category:", tfCategory));
        
        // Author
        tfAuthor = new JTextField(30);
        panel.add(createLabeledInput("Author:", tfAuthor));
        
        // Cost
        tfCost = new JTextField(30);
        panel.add(createLabeledInput("Cost:", tfCost));
        
        return panel;
    }

    @Override
    protected void addItemToStore() {
        try {
            String title = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();
            String author = tfAuthor.getText().trim();
            
            if (title.isEmpty() || category.isEmpty()) {
                throw new IllegalArgumentException("Title and Category cannot be empty");
            }
            
            float cost = Float.parseFloat(tfCost.getText().trim());
            
            // Create and add Book to store
            // Constructor signature: Book(title, category, cost)
            Book book = new Book(title, category, cost);
            book.addAuthor(author);
            store.addMedia(book);
            
            JOptionPane.showMessageDialog(this, 
                "Book added successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Clear fields
            tfTitle.setText("");
            tfCategory.setText("");
            tfAuthor.setText("");
            tfCost.setText("");
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Invalid numeric input! Please check cost.", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, 
                "Validation Error: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, 
                "Error: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
