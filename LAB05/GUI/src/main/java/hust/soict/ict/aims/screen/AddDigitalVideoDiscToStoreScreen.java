package hust.soict.ict.aims.screen;

import hust.soict.ict.aims.store.Store;
import hust.soict.ict.aims.media.DigitalVideoDisc;

import javax.swing.*;

/**
 * AddDigitalVideoDiscToStoreScreen - Screen to add DVD to store
 * Input fields: Title, Category, Director, Length, Cost
 */
public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle;
    private JTextField tfCategory;
    private JTextField tfDirector;
    private JTextField tfLength;
    private JTextField tfCost;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);
        this.setTitle("Add DVD to Store");
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
        
        // Director
        tfDirector = new JTextField(30);
        panel.add(createLabeledInput("Director:", tfDirector));
        
        // Length
        tfLength = new JTextField(30);
        panel.add(createLabeledInput("Length (minutes):", tfLength));
        
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
            String director = tfDirector.getText().trim();
            
            if (title.isEmpty() || category.isEmpty()) {
                throw new IllegalArgumentException("Title and Category cannot be empty");
            }
            
            int length = Integer.parseInt(tfLength.getText().trim());
            float cost = Float.parseFloat(tfCost.getText().trim());
            
            // Create and add DVD to store
            // Constructor signature: DigitalVideoDisc(title, category, cost, length, director)
            DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, cost, length, director);
            store.addMedia(dvd);
            
            JOptionPane.showMessageDialog(this, 
                "DVD added successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Clear fields
            tfTitle.setText("");
            tfCategory.setText("");
            tfDirector.setText("");
            tfLength.setText("");
            tfCost.setText("");
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Invalid numeric input! Please check length and cost.", 
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
