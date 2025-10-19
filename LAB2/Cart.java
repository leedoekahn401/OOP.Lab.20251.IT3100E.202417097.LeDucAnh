import java.util.LinkedList;
public class Cart
{
    public static final int MAX_NUMBERS_ORDERED = 20;
    LinkedList<DigitalVideoDisc> itemsOrdered =  new LinkedList<>();
    private int qtyOrdered = 0;
    public void addDigitalVideoDisc(DigitalVideoDisc a)
    {
        if(qtyOrdered < MAX_NUMBERS_ORDERED)
        {
            itemsOrdered.add(a);
            qtyOrdered++;
            System.out.println("The disc has been added.");
            System.out.println("Cost: "+ totalCost());
        }
        if(MAX_NUMBERS_ORDERED - qtyOrdered < 3)
        {
            int b = MAX_NUMBERS_ORDERED - qtyOrdered;
            System.out.println("The cart is almost full, there are "+ b +" slots left." );
        }
        if(qtyOrdered >= MAX_NUMBERS_ORDERED )
        {
            System.out.println("The cart is full!!!");
        }
    }
    public double totalCost()
    {
        double sum =0;
        for(int i =0;i<qtyOrdered;i++)
        {
            sum+= itemsOrdered.get(i).getCost();
        }
        return sum;
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc a)
    {
        if(qtyOrdered <=0)
        {
            System.out.println("Cannot remove.");
        }
        if(qtyOrdered >0)
        {
            itemsOrdered.remove(a);
            System.out.println("Successfully removed!!!");
        }
    }

    


}