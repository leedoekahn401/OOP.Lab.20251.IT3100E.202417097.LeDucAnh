package hust.soict.ict.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton penRadioButton;

    @FXML
    private RadioButton eraserRadioButton;

    @FXML
    private ToggleGroup tools; // This corresponds to the ToggleGroup in FXML

    @FXML
    void clearButtonPressed(ActionEvent event) {
        // Clear the canvas by removing all children (Shapes)
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        // Default color is Black (Pen)
        Color inkColor = Color.BLACK;

        // If Eraser is selected, paint with White (Background color)
        if (eraserRadioButton.isSelected()) {
            inkColor = Color.WHITE;
        }

        // Draw a circle at the mouse cursor position
        // We ensure the circle is not too large for the eraser to feel precise
        double radius = eraserRadioButton.isSelected() ? 10.0 : 4.0;

        Circle newCircle = new Circle(event.getX(), event.getY(), radius, inkColor);
        drawingAreaPane.getChildren().add(newCircle);
    }
}