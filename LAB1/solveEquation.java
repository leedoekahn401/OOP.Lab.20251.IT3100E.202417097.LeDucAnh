import javax.swing.JOptionPane;

public class solveEquation{
    public static void main(String[] args){
        double inp = getDouble("Choose type of equation:\n1:  linear equation\n2:  linear system\n3: quadratic equation\n");
        if(inp==1){
            double a = getDouble("Enter a: ");
            double b = getDouble("Enter b: ");
            String ans = a==0 ? "No solution" :"" + -b/a;
            JOptionPane.showMessageDialog(null,ans);
        }else if(inp==2){
            double a11,a12,a21,a22,b1,b2;
            a11=getDouble("Enter a11: ");
            a12=getDouble("Enter a12: ");
            b1=getDouble("Enter b1: ");
            a21=getDouble("Enter a21: ");
            a22=getDouble("Enter a22: ");
            b2=getDouble("Enter b2: ");

            double D=a11*a22-a21*a12;
            double D1=b1*a22-b2*a12;
            double D2=a11*b2-a21*b1;

            if(D==0){
                if(D1==0&&D2==0){
                    JOptionPane.showMessageDialog(null,"Infinitely many solutions");
                }else{
                    JOptionPane.showMessageDialog(null,"No solution");
                }
            }else{
                JOptionPane.showMessageDialog(null, "x1: "+D1/D+"\nx2: "+D2/D);
            }

        }else if(inp==3){
            double a=getDouble("Enter a: ");
            double b=getDouble("Enter b: ");
            double c=getDouble("Enter c: ");
            if(a==0){
                JOptionPane.showMessageDialog(null, "One solution\nx:  "+(-c/b));
            }else{
                double D = b*b-4*a*c;
                if(D<0){
                    JOptionPane.showMessageDialog(null, "No solution");
                }else if(D==0){
                    JOptionPane.showMessageDialog(null,"One solution\nx: "+(-b/2*a));
                }else{
                    double sqrD=Math.sqrt(D);
                    double x1=(-b+sqrD)/2*a;
                    double x2=(-b-sqrD)/2*a;
                    JOptionPane.showMessageDialog(null,"Two solutions\nx1: "+x1+"\nx2: "+x2);
                }
            }
        }
    }

    private static double getDouble(String message){
        String sn = JOptionPane.showInputDialog(message);
        double n=Double.parseDouble(sn);
        return n;
    }
}