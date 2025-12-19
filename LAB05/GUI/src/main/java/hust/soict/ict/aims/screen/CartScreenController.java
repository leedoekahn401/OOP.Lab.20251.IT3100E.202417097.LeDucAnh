package hust.soict.ict.aims.screen;

import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.media.Media;
import hust.soict.ict.aims.media.Playable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;

/**
 * CartScreenController - Controller for Cart Screen
 * Implements MVC pattern with PropertyChangeListener for cart updates
 * Similar to JavaFX controller pattern with @FXML annotations
 */
public class CartScreenController implements PropertyChangeListener {
    private Cart cart;
    
    // UI Components (similar to @FXML annotation in JavaFX)
    private JTable tblMedia;
    private JLabel lblTotal;
    private JTextField tfFilter;
    private JRadioButton rdById, rdByTitle;
    private JButton btnPlay;
    private JButton btnRemove;

    public CartScreenController(Cart cart) {
        this.cart = cart;
    }

    /**
     * Set the TableView component
     * Similar to @FXML private TableView<Media> tblMedia
     */
    public void setTableView(JTable tblMedia) {
        this.tblMedia = tblMedia;
    }

    /**
     * Set the Total Label component
     * Similar to @FXML private Label lblTotal
     */
    public void setTotalLabel(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }

    /**
     * Set the Filter TextField component
     * Similar to @FXML private TextField tfFilter
     */
    public void setFilterTextField(JTextField tfFilter) {
        this.tfFilter = tfFilter;
    }

    /**
     * Set the Filter RadioButtons
     * Similar to @FXML private RadioButton rdById, rdByTitle
     */
    public void setFilterRadioButtons(JRadioButton rdById, JRadioButton rdByTitle) {
        this.rdById = rdById;
        this.rdByTitle = rdByTitle;
    }

    /**
     * Set the Play Button component
     * Similar to @FXML private Button btnPlay
     */
    public void setPlayButton(JButton btnPlay) {
        this.btnPlay = btnPlay;
    }

    /**
     * Set the Remove Button component
     * Similar to @FXML private Button btnRemove
     */
    public void setRemoveButton(JButton btnRemove) {
        this.btnRemove = btnRemove;
    }

    /**
     * Initialize method - Similar to @FXML initialize() in JavaFX
     * Sets up table columns, loads items, and configures listeners
     */
    public void initialize() {
        // Set table items from cart
        updateTable();
        
        // Disable Play and Remove buttons initially (line 73–74)
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
        
        // Add listener for table selection (line 76–86)
        // This uses Swing's ListSelectionListener instead of JavaFX's ChangeListener
        tblMedia.getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = tblMedia.getSelectedRow();
                        Media selectedMedia = null;
                        
                        if (selectedRow >= 0 && selectedRow < cart.getItemsOrdered().size()) {
                            selectedMedia = cart.getItemsOrdered().get(selectedRow);
                        }
                        
                        updateButtonBar(selectedMedia);
                    }
                }
            }
        );
        
        // Listen to cart updates (if cart implements PropertyChangeListener)
        // this.cart.addPropertyChangeListener(this);
        
        // Add listener for filter text field (Figure 45)
        // Re-applies filter every time user makes a change
        tfFilter.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(javax.swing.event.DocumentEvent e) {
                    showFilteredMedia();
                }
                
                @Override
                public void removeUpdate(javax.swing.event.DocumentEvent e) {
                    showFilteredMedia();
                }
                
                @Override
                public void changedUpdate(javax.swing.event.DocumentEvent e) {
                    showFilteredMedia();
                }
            }
        );
    }

    public void updateTable() {
        if (tblMedia == null || lblTotal == null) return;
        
        DefaultTableModel model = (DefaultTableModel) tblMedia.getModel();
        model.setRowCount(0);
        
        double total = 0;
        for (Media media : cart.getItemsOrdered()) {
            Vector<Object> row = new Vector<>();
            row.add(media.getTitle());
            row.add(media.getCategory());
            row.add(String.format("%.2f", media.getCost()));
            model.addRow(row);
            total += media.getCost();
        }
        
        lblTotal.setText(String.format("%.2f $", total));
    }

    /**
     * updateButtonBar() method - Similar to Figure 43 in PDF
     * Updates button visibility based on selected Media item
     */
    public void updateButtonBar(Media media) {
        if (btnPlay == null || btnRemove == null) return;
        
        if (media == null) {
            // No item selected
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            // Only display play button if the media is playable
            btnPlay.setVisible(media instanceof Playable);
        }
    }

    /**
     * btnPlayPressed() method - Event handler for Play button
     * Gets the selected Media from TableView and plays it if it's Playable
     */
    public void btnPlayPressed() {
        // Get the selected item from TableView
        int selectedRow = tblMedia.getSelectedRow();
        
        if (selectedRow >= 0) {
            Media media = cart.getItemsOrdered().get(selectedRow);
            if (media instanceof Playable) {
                try {
                    ((Playable) media).play();
                } catch (hust.soict.ict.aims.exception.PlayerException e) {
                    javax.swing.JOptionPane.showMessageDialog(null, 
                        e.getMessage(), 
                        "Playback Error", 
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void playMedia(int selectedRow) {
        if (selectedRow >= 0 && selectedRow < cart.getItemsOrdered().size()) {
            Media media = cart.getItemsOrdered().get(selectedRow);
            if (media instanceof Playable) {
                try {
                    ((Playable) media).play();
                } catch (hust.soict.ict.aims.exception.PlayerException e) {
                    javax.swing.JOptionPane.showMessageDialog(null, 
                        e.getMessage(), 
                        "Playback Error", 
                        javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * btnRemovePressed() method - Event handler for Remove button
     * Similar to Figure 44 from PDF specification
     * Gets the selected Media from TableView and removes it from the cart
     * Note: TableView automatically updates through ObservableList observation
     */
    public void btnRemovePressed() {
        // Get the selected item from TableView
        int selectedRow = tblMedia.getSelectedRow();
        
        if (selectedRow >= 0) {
            Media media = cart.getItemsOrdered().get(selectedRow);
            try {
                // Remove the media from the cart
                cart.removeMedia(media);
                // Update table display - recalculate total
                updateTable();
                // Deselect to hide buttons
                tblMedia.clearSelection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void removeMedia(int selectedRow) {
        if (selectedRow >= 0 && selectedRow < cart.getItemsOrdered().size()) {
            Media media = cart.getItemsOrdered().get(selectedRow);
            try {
                cart.removeMedia(media);
                updateTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void placeOrder() {
        if (!cart.getItemsOrdered().isEmpty()) {
            double total = calculateTotal();
            cart.getItemsOrdered().clear();
            updateTable();
        }
    }

    private double calculateTotal() {
        double total = 0;
        for (Media media : cart.getItemsOrdered()) {
            total += media.getCost();
        }
        return total;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Refresh table when cart changes
        updateTable();
    }

    /**
     * showFilteredMedia() method - Filter cart items based on text field and radio button selection
     * Similar to FilteredList with Predicate in JavaFX
     * Re-applies every time user changes the filter text field
     */
    public void showFilteredMedia() {
        if (tblMedia == null || tfFilter == null) return;
        
        String filterText = tfFilter.getText().trim();
        DefaultTableModel model = (DefaultTableModel) tblMedia.getModel();
        model.setRowCount(0);
        
        double total = 0;
        
        // Iterate through all items in cart and filter based on criteria
        for (Media media : cart.getItemsOrdered()) {
            boolean matches = false;
            
            // If filter text is empty, show all items
            if (filterText.isEmpty()) {
                matches = true;
            }
            // If filter by ID is selected
            else if (rdById.isSelected()) {
                // Check if media ID (as string) contains the filter text
                matches = String.valueOf(media.getId()).contains(filterText);
            }
            // If filter by Title is selected
            else if (rdByTitle.isSelected()) {
                // Check if media title contains the filter text (case-insensitive)
                matches = media.getTitle().toLowerCase().contains(filterText.toLowerCase());
            }
            
            // If matches the filter, add to table and sum cost
            if (matches) {
                Vector<Object> row = new Vector<>();
                row.add(media.getTitle());
                row.add(media.getCategory());
                row.add(String.format("%.2f", media.getCost()));
                model.addRow(row);
                total += media.getCost();
            }
        }
        
        // Update total label with filtered items sum
        lblTotal.setText(String.format("%.2f $", total));
    }

    public Cart getCart() {
        return cart;
    }
}
