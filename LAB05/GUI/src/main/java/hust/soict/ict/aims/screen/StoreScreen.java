package hust.soict.ict.aims.screen;

import hust.soict.ict.aims.store.Store;
import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.media.Media;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;
    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }
    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        
        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(e -> {
            this.dispose();
            new AddBookToStoreScreen(store);
        });
        smUpdateStore.add(addBook);
        
        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(e -> {
            this.dispose();
            new AddCompactDiscToStoreScreen(store);
        });
        smUpdateStore.add(addCD);
        
        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(e -> {
            this.dispose();
            new AddDigitalVideoDiscToStoreScreen(store);
        });
        smUpdateStore.add(addDVD);

        menu.add(smUpdateStore);
        
        JMenuItem viewStore = new JMenuItem("View Store");
        viewStore.addActionListener(e -> {
            // Refresh current store view
            this.dispose();
            new StoreScreen(store, cart);
        });
        menu.add(viewStore);
        
        JMenuItem viewCart = new JMenuItem("View Cart");
        viewCart.addActionListener(e -> {
            this.dispose();
            new CartScreen(cart);
        });
        menu.add(viewCart);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }
    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cartButton = new JButton("View Cart");
        cartButton.setPreferredSize(new Dimension(100, 50));
        cartButton.setMaximumSize(new Dimension(100, 50));
        cartButton.addActionListener(e -> {
            this.dispose();
            new CartScreen(cart, store);
        });

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cartButton);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }
    JPanel createCenter() {
        JPanel center = new JPanel();
        
        ArrayList<Media> mediaInStore = store.getItemsInStore();
        int itemCount = mediaInStore.size();
        int columns = 3;
        int rows = (itemCount + columns - 1) / columns;
        
        center.setLayout(new GridLayout(rows, columns, 2, 2));

        for (Media media : mediaInStore) {
            MediaStore cell = new MediaStore(media, cart);
            center.add(cell);
        }

        JScrollPane scrollPane = new JScrollPane(center);
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(scrollPane, BorderLayout.CENTER);
        return wrapper;
    }
    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        setTitle("Store");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Store store = new Store();
            Cart cart = new Cart();
            new StoreScreen(store, cart);
        });
    }
}
