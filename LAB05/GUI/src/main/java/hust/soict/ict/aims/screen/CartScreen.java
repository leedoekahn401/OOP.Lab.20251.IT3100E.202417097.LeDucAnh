package hust.soict.ict.aims.screen;

import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.media.Media;
import hust.soict.ict.aims.store.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class CartScreen extends JFrame {
    private Cart cart;
    private Store store;
    private CartScreenController controller;
    private JTable tblMedia;
    private JLabel lblTotal;
    private JTextField tfFilter;
    private JRadioButton rdById, rdByTitle;
    private JButton btnPlay;
    private JButton btnRemove;

    public CartScreen(Cart cart) {
        this(cart, new Store());
    }

    public CartScreen(Cart cart, Store store) {
        this.cart = cart;
        this.store = store;
        this.controller = new CartScreenController(cart);
        
        this.setTitle("Cart");
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        // Top area
        this.add(createNorth(), BorderLayout.NORTH);
        
        // Center area
        this.add(createCenter(), BorderLayout.CENTER);
        
        // Right area
        this.add(createEast(), BorderLayout.EAST);
        
        // Pass UI components to controller
        controller.setTableView(tblMedia);
        controller.setTotalLabel(lblTotal);
        controller.setFilterTextField(tfFilter);
        controller.setFilterRadioButtons(rdById, rdByTitle);
        controller.setPlayButton(btnPlay);
        controller.setRemoveButton(btnRemove);
        
        // Initialize controller (similar to @FXML initialize() in JavaFX)
        // This sets up table listeners and button visibility
        controller.initialize();
        
        this.setVisible(true);
    }

    private JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menuOptions = new JMenu("Options");
        
        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(e -> {
            this.dispose();
            new StoreScreen(store, cart);
        });
        menuOptions.add(viewStore);
        
        JMenu updateStore = new JMenu("Update store");
        
        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(e -> {
            this.dispose();
            new AddBookToStoreScreen(store);
        });
        updateStore.add(addBook);
        
        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(e -> {
            this.dispose();
            new AddCompactDiscToStoreScreen(store);
        });
        updateStore.add(addCD);
        
        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(e -> {
            this.dispose();
            new AddDigitalVideoDiscToStoreScreen(store);
        });
        updateStore.add(addDVD);
        
        menuOptions.add(updateStore);
        menuBar.add(menuOptions);
        menuBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        north.add(menuBar);
        
        // Title with View Store button
        JPanel titlePanel = new JPanel(new BorderLayout(10, 10));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        JLabel lblTitle = new JLabel("CART");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 50));
        lblTitle.setForeground(new Color(0, 255, 255)); // AQUA
        titlePanel.add(lblTitle, BorderLayout.WEST);
        
        JButton btnViewStore = new JButton("View Store");
        btnViewStore.setFont(new Font("Arial", Font.BOLD, 14));
        btnViewStore.setPreferredSize(new Dimension(120, 50));
        btnViewStore.addActionListener(e -> {
            this.dispose();
            new StoreScreen(store, cart);
        });
        titlePanel.add(btnViewStore, BorderLayout.EAST);
        
        north.add(titlePanel);
        
        return north;
    }

    private JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Filter row
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        filterPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        JLabel lblFilter = new JLabel("Filter:");
        filterPanel.add(lblFilter);
        
        tfFilter = new JTextField(15);
        filterPanel.add(tfFilter);
        
        rdById = new JRadioButton("By ID", true);
        rdByTitle = new JRadioButton("By Title");
        
        ButtonGroup group = new ButtonGroup();
        group.add(rdById);
        group.add(rdByTitle);
        
        // Add listeners to radio buttons to trigger filter update
        rdById.addActionListener(e -> controller.showFilteredMedia());
        rdByTitle.addActionListener(e -> controller.showFilteredMedia());
        
        filterPanel.add(rdById);
        filterPanel.add(rdByTitle);
        
        center.add(filterPanel);
        
        // Table
        String[] columnNames = {"Title", "Category", "Cost"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tblMedia = new JTable(model);
        tblMedia.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        JScrollPane scrollPane = new JScrollPane(tblMedia);
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        
        center.add(scrollPane);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        
        btnPlay = new JButton("Play");
        btnPlay.addActionListener(e -> controller.btnPlayPressed());
        buttonPanel.add(btnPlay);
        
        btnRemove = new JButton("Remove");
        btnRemove.addActionListener(e -> controller.btnRemovePressed());
        buttonPanel.add(btnRemove);
        
        center.add(buttonPanel);
        
        // Initialize lblTotal first before updateTable
        lblTotal = new JLabel("0 $");
        updateTable();
        
        return center;
    }

    private JPanel createEast() {
        JPanel east = new JPanel();
        east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
        east.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));
        east.setPreferredSize(new Dimension(220, 768));
        
        // Total panel
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JLabel lblTotalLabel = new JLabel("Total:");
        lblTotalLabel.setFont(new Font("Arial", Font.BOLD, 24));
        totalPanel.add(lblTotalLabel);
        
        lblTotal = new JLabel("0 $");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 24));
        lblTotal.setForeground(new Color(0, 255, 255)); // AQUA
        totalPanel.add(lblTotal);
        
        east.add(totalPanel);
        east.add(Box.createVerticalStrut(30));
        
        // Place order button
        JButton btnPlaceOrder = new JButton("Place Order");
        btnPlaceOrder.setFont(new Font("Arial", Font.BOLD, 24));
        btnPlaceOrder.setForeground(Color.WHITE);
        btnPlaceOrder.setBackground(Color.RED);
        btnPlaceOrder.setOpaque(true);
        btnPlaceOrder.setMaximumSize(new Dimension(200, 60));
        btnPlaceOrder.addActionListener(e -> placeOrder());
        
        east.add(btnPlaceOrder);
        east.add(Box.createVerticalGlue());
        
        return east;
    }

    private void updateTable() {
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

    private void placeOrder() {
        if (cart.getItemsOrdered().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Cart is empty!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } else {
            double total = calculateTotal();
            controller.placeOrder();
            JOptionPane.showMessageDialog(this, 
                "Order placed successfully!\n\n" +
                "Items: " + cart.getItemsOrdered().size() + "\n" +
                "Total: " + String.format("%.2f", total) + " $", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private double calculateTotal() {
        double total = 0;
        for (Media media : cart.getItemsOrdered()) {
            total += media.getCost();
        }
        return total;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Cart cart = new Cart();
            // Add sample data
            new CartScreen(cart);
        });
    }
}
