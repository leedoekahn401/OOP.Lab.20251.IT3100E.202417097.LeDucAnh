import javax.swing.JOptionPane;

public class HelloNameDialog{
    public static void main(String[] args){
        String result;
        result = JOptionPane.showInputDialog("Please enter your name: ");
        String message = "Hi "+result+"!";

        JOptionPane.showMessageDialog(null,message);
        System.exit(0);
    }
}