import javax.swing.JOptionPane;

public class Operation {
    public static void main(String[] args){
        String number1 = JOptionPane.showInputDialog("Enter first number: ");
        String number2 = JOptionPane.showInputDialog("Enter second number: ");
        double num1 = Double.parseDouble(number1);
        double num2 = Double.parseDouble(number2);
        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;
        double quotient = num1/num2;
        
        JOptionPane.showMessageDialog(null, sum + difference + product + quotient);
    }
}
