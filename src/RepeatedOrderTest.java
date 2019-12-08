public class RepeatedOrderTest extends SupplyOrder{
    public static void main(String[] args){
        LocalDate l=new LocalDate(1,2,2000);
        LocalDate l2=new LocalDate(1,3,2000);


        System.out.println(l2.getYear());
      RepeatedOrder repeatedOrder=new RepeatedOrder("a", "P#01",l ,3,5, l2);
        System.out.println(
                repeatedOrder.getAmount()
        );

    }
}
