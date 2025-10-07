import javax.swing.JOptionPane;

public class ChoosingOption {
    public static void main(String[] args){
        String[] options = {"Yes", "No"};
        int userChoice = JOptionPane.showOptionDialog(
            null,
            "Choose Yes or No",
            "Choose Yes/No", 
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null, 
            options,
            options[0]
        );

        if(userChoice==0){
            JOptionPane.showMessageDialog(null, "You choose Yes");
        }else if(userChoice==1){
            JOptionPane.showMessageDialog(null, "You choose No");
        }else{
            JOptionPane.showMessageDialog(null, "U closed the pane");
        }
        System.exit(0);
    }
}
