
public class Aims
{
    public static void main(String[] args) {
        {
            Cart anOrder = new Cart();
            DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King","Animation","Roger Allers",87,19.95f);
            DigitalVideoDisc dvd2 = new DigitalVideoDisc("title", "category" , "director", 90, 10.05f);
            DigitalVideoDisc dvd3 = new DigitalVideoDisc("boom boom");
            anOrder.addDigitalVideoDisc(dvd1);
            anOrder.addDigitalVideoDisc(dvd2);
            System.out.println("Total cost is: " + anOrder.totalCost());
            anOrder.removeDigitalVideoDisc(dvd1);
            System.out.println("Total cost is: " + anOrder.totalCost());    

        }
    }
}