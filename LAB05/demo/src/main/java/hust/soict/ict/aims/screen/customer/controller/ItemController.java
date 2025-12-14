package hust.soict.ict.aims.screen.customer.controller;
import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.media.Media;
import hust.soict.ict.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
public class ItemController {
    private Media media;
    private Cart cart;
    @FXML
    private Button btnAddToCart;
    @FXML
    private Button btnPlay;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblCost;
    public ItemController(Cart cart) {
        this.cart = cart;
    }
    public void setData(Media media) {
        this.media = media;
        lblTitle.setText(media.getTitle());
        lblCost.setText(media.getCost() + " $");
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60));
        }
    }
    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        cart.addMedia(media);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cart Update");
        alert.setHeaderText(null);
        alert.setContentText(media.getTitle() + " has been added to cart.");
        alert.showAndWait();
    }
    @FXML
    void btnPlayClicked(ActionEvent event) {
        if (media instanceof Playable) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Playing Media");
            alert.setHeaderText("Playing " + media.getTitle());
            alert.setContentText("The media is currently playing...");
            alert.showAndWait();
            ((Playable) media).play();
        }
    }
}