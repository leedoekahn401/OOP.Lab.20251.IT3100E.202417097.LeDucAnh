package hust.soict.ict.aims.screen;

import hust.soict.ict.aims.media.Media;
import hust.soict.ict.aims.media.Playable;
import hust.soict.ict.aims.cart.Cart;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;
    
    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(media.getTitle());
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 20));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCartBtn = new JButton("Add to cart");
        addToCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart.addMedia(media);
                JOptionPane.showMessageDialog(MediaStore.this, 
                    "Added \"" + media.getTitle() + "\" to cart!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        container.add(addToCartBtn);
        
        if (media instanceof Playable) {
            JButton playBtn = new JButton("Play");
            playBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playMedia();
                }
            });
            container.add(playBtn);
        }
        
        this.add(Box.createVerticalGlue());
        this.add(titleLabel);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    private void playMedia() {
        if (media instanceof Playable) {
            Playable playableMedia = (Playable) media;
            JDialog dialog = new JDialog();
            dialog.setTitle("Playing: " + media.getTitle());
            dialog.setSize(400, 300);
            dialog.setLocationRelativeTo(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            
            JTextArea textArea = new JTextArea();
            textArea.append("Now playing: " + media.getTitle() + "\n");
            textArea.append("===============================\n");
            try {
                playableMedia.play();
            } catch (hust.soict.ict.aims.exception.PlayerException e) {
                textArea.append("Error: " + e.getMessage() + "\n");
            }
            textArea.setEditable(false);
            textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
            
            JScrollPane scrollPane = new JScrollPane(textArea);
            dialog.add(scrollPane);
            dialog.setVisible(true);
        }
    }
}
