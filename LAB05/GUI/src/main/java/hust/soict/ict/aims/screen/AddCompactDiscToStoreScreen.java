package hust.soict.ict.aims.screen;

import hust.soict.ict.aims.store.Store;
import hust.soict.ict.aims.media.CompactDisc;

import javax.swing.*;

/**
 * AddCompactDiscToStoreScreen - Screen to add CD to store
 * Input fields: Title, Category, Artist, Cost
 */
public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle;
    private JTextField tfCategory;
    private JTextField tfArtist;
    private JTextField tfCost;

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
        this.setTitle("Add CD to Store");
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
        
        // Artist
        tfArtist = new JTextField(30);
        panel.add(createLabeledInput("Artist:", tfArtist));
        
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
            String artist = tfArtist.getText().trim();
            
            if (title.isEmpty() || category.isEmpty()) {
                throw new IllegalArgumentException("Title and Category cannot be empty");
            }
            
            float cost = Float.parseFloat(tfCost.getText().trim());
            
            // Create and add CD to store
            CompactDisc cd = new CompactDisc(title, category, artist, cost);
            store.addMedia(cd);
            
            JOptionPane.showMessageDialog(this, 
                "CD added successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Clear fields
            tfTitle.setText("");
            tfCategory.setText("");
            tfArtist.setText("");
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
