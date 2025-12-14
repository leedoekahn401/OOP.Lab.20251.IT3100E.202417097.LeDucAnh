package hust.soict.ict.swing;

import javax.swing.*;
import java.awt.event.*;

class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        NumberGrid frame = (NumberGrid) SwingUtilities.getWindowAncestor((JButton) e.getSource());
        
        if (button.charAt(0) >= '0' && button.charAt(0) <= '9') {
            frame.tfDisplay.setText(frame.tfDisplay.getText() + button);
        } else if (button.equals("DEL")) {
            String text = frame.tfDisplay.getText();
            if (text.length() > 0) {
                frame.tfDisplay.setText(text.substring(0, text.length() - 1));
            }
        } else if (button.equals("C")) {
            frame.tfDisplay.setText("");
        }
    }
}



