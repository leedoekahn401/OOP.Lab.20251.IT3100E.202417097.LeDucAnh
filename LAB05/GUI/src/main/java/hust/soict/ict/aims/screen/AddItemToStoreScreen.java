package hust.soict.ict.aims.screen;

import hust.soict.ict.aims.store.Store;
import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.media.Media;

import javax.swing.*;
import java.awt.*;

/**
 * AddItemToStoreScreen - Base class for adding items to store
 * Provides common UI elements: menu bar, header, and input panel
 * Subclasses should implement createInputPanel() for specific media types
 */
public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected JPanel inputPanel;

    public AddItemToStoreScreen(Store store) {
        this.store = store;
        
        this.setTitle("Add Item to Store");
        this.setSize(700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        // Top area - Menu bar
        this.add(createNorth(), BorderLayout.NORTH);
        
        // Center area - Input fields
        this.add(createCenter(), BorderLayout.CENTER);
        
        this.setVisible(true);
    }

    protected JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOptions = new JMenu("Options");
        
        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(e -> {
            this.dispose();
            new StoreScreen(store, new Cart());
        });
        menuOptions.add(viewStore);
        
        JMenu updateStore = new JMenu("Update store");
        
        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(e -> {
            this.dispose();
            new AddDigitalVideoDiscToStoreScreen(store);
        });
        updateStore.add(addDVD);
        
        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(e -> {
            this.dispose();
            new AddCompactDiscToStoreScreen(store);
        });
        updateStore.add(addCD);
        
        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(e -> {
            this.dispose();
            new AddBookToStoreScreen(store);
        });
        updateStore.add(addBook);
        
        menuOptions.add(updateStore);
        menuBar.add(menuOptions);
        menuBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        north.add(menuBar);
        
        // Title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        JLabel lblTitle = new JLabel("Add Item");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 40));
        lblTitle.setForeground(new Color(0, 255, 255)); // AQUA
        titlePanel.add(lblTitle);
        
        north.add(titlePanel);
        
        return north;
    }

    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        inputPanel = createInputPanel();
        center.add(inputPanel);
        
        // Add button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnAdd = new JButton("Add to Store");
        btnAdd.setFont(new Font("Arial", Font.BOLD, 16));
        btnAdd.setPreferredSize(new Dimension(150, 40));
        btnAdd.addActionListener(e -> addItemToStore());
        buttonPanel.add(btnAdd);
        
        center.add(Box.createVerticalStrut(20));
        center.add(buttonPanel);
        center.add(Box.createVerticalGlue());
        
        return center;
    }

    /**
     * Create input panel specific to media type
     * Subclasses must implement this
     */
    protected abstract JPanel createInputPanel();

    /**
     * Add item to store
     * Subclasses must implement this
     */
    protected abstract void addItemToStore();

    protected JPanel createLabeledInput(String label, JComponent input) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(120, 25));
        panel.add(lbl);
        panel.add(input);
        
        return panel;
    }
}
