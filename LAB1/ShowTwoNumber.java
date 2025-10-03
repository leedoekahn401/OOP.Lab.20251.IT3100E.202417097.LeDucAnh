import javax.swing.JOptionPane;

public class ShowTwoNumber{
    public static void main(String[] args){
        String nOne; 
        nOne = JOptionPane.showInputDialog("Enter first number: ");
        String nTwo; 
        nTwo = JOptionPane.showInputDialog("Enter second number: ");
        String message = nOne+ " and " + nTwo;
        JOptionPane.showMessageDialog(null,message);
    }

}