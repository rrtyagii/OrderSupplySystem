import java.io.*;
import java.util.*;

public class UserInterface extends LocalDate {
    public static OrderManager orderManager=new OrderManager();

    private static boolean compareTo(LocalDate date1, LocalDate date2){
        if(date1.getYear()>date2.getYear())
            return true;
        else if(date1.getMonth()>date2.getMonth())
            return true;
        else if(date1.getDay()>date2.getDay())
            return true;
        else
            return false;
    }

    public static void main(String[] args) throws OrderManagerException {
        while(true){
            Scanner scan =new Scanner(System.in);
            System.out.println("Enter which way you want to start");
            System.out.println("\n 1. Load by file name \n " +
                    "2. Add Order \n " +
                    "3. Delete order by ID \n " +
                    "4. View Orders for Customer ID \n " +
                    "5. Order inventory report \n " +
                    "6. Quit");
           int input=scan.nextInt();
            switch(input){
                case 1:
                    fileLoading(); break;
                case 2:
                    addOrder(); break;
                case 3:
                    deleteOrder();
                    break;
                case 4:
                    sortPrintbyCustomerID(); break;
                case 5:
                    inventoryReport(); break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("[!!] Unknown input [!!]");

            }
        }
    }

    public static void fileLoading(){
        ArrayList<String> fileInput=new ArrayList<String>();
        try{
            Scanner forFileName=new Scanner(System.in);
            System.out.println("Enter the file name, por favor!");
                File file = new File(forFileName.nextLine());
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null)
            fileInput.add(st);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for(String ordering:fileInput){
            String[] clf=ordering.split(",");
            if(clf[0].equals("O")){
            orderManager.addOrder(clf[1], clf[2], new LocalDate((Integer.parseInt(clf[3].split("/")[0])),
                    (Integer.parseInt(clf[3].split("/")[1])), (Integer.parseInt(clf[3].split("/")[2]))),
                    Integer.parseInt(clf[4]));
            }
            else{
            orderManager.addOrder(clf[1], clf[2], new LocalDate((Integer.parseInt(clf[3].split("/")[0])),
                    (Integer.parseInt(clf[3].split("/")[1])), (Integer.parseInt(clf[3].split("/")[2] ) ) ),
                    Integer.parseInt(clf[4]), Integer.parseInt(clf[5]), new LocalDate((Integer.parseInt(clf[6].split("/")[0] ) ),
                            (Integer.parseInt(clf[6].split("/")[1]) ) , (Integer.parseInt(clf[6].split("/")[2] ) ) ) );
            }
        }
    }

    public static void addOrder() {
        try {
            Scanner scanning = new Scanner(System.in);
            System.out.println("Enter an order \n"  +
                    "(O, customerID, productID, date, amount) \n" +
                    "or if imply:-\n" +
                    "R, customerID, productID, date, amount, period, endDate");
            //add validations for checking the user input
            String ordr = scanning.nextLine();
            if (ordr != null) {
                String[] adding = ordr.split(",");

                if (adding[0].equals("O")) {
                    orderManager.addOrder(adding[1], adding[2], new LocalDate((Integer.parseInt(adding[3].split("/")[0])),
                            (Integer.parseInt(adding[3].split("/")[1])), (Integer.parseInt(adding[3].split("/")[2]))), Integer.parseInt(adding[4]));
                } else {
                    orderManager.addOrder(adding[1], adding[2], new LocalDate((Integer.parseInt(adding[3].split("/")[0])),
                            (Integer.parseInt(adding[3].split("/")[1])), (Integer.parseInt(adding[3].split("/")[2]))), Integer.parseInt(adding[4]),
                            Integer.parseInt(adding[5]), new LocalDate((Integer.parseInt(adding[6].split("/")[0])), (Integer.parseInt(adding[6].split("/")[1])),
                                    (Integer.parseInt(adding[6].split("/")[2]))));
                }
            }
            else{
                throw new UserInterfaceException("May be an empty input");
            }
        } catch (UserInterfaceException e) {
            e.getMessage();
        }
    }


    public static void deleteOrder() throws OrderManagerException {
     Scanner sc=new Scanner(System.in);
        System.out.println("Enter the orderID to delete: ");
        int order=Integer.parseInt(sc.nextLine());
        boolean result = orderManager.removeOrder(order);
        if (result) {
            System.out.println("Order ID: " +order+ " deleted successfully");
        }
        else
        {
            System.out.println("Invalid Order ID");
            return;
        }
    }

    public static void sortPrintbyCustomerID(){
        Scanner scan=new Scanner (System.in);
        System.out.println("Enter the customerID to be searched");
        String customerID=scan.nextLine();
        ArrayList<SupplyOrder> forSorting = new ArrayList<SupplyOrder>();
        if(customerID!=null){
        for(int i=0; i<orderManager.getListedOrder().size(); i++){
            if(orderManager.getListedOrder().get(i).getCustomerID().equals(customerID)){
                forSorting.add(orderManager.getListedOrder().get(i));
            }
        }
        SupplyOrder temporary;
        for(int j=0; j<forSorting.size()-1; j++){
            if(UserInterface.compareTo(forSorting.get(j).getOrderDate(),forSorting.get(j+1).getOrderDate())){
                Collections.swap(forSorting, forSorting.indexOf(forSorting.get(j)),forSorting.indexOf(forSorting.get(j+1)));
            }
        }

        for(int k=0; k<forSorting.size(); k++){
            System.out.println(forSorting.get(k));
        }
            System.out.println("");
            System.out.println("Total number of orders for Customer ID: " + customerID + " equals ------> " +forSorting.size());
       }
    }

    public static void inventoryReport() {
//        for (int i = 0; i < orderManager.getListedOrder().size()-1; i++) {
//            if (orderManager.getListedOrder().get(i).getOrderDate().getYear() > orderManager.getListedOrder().get(i + 1).getOrderDate().getYear()) {
//                if (orderManager.getListedOrder().get(i).getOrderDate().getMonth() > orderManager.getListedOrder().get(i + 1).getOrderDate().getMonth()) {
//                    if (orderManager.getListedOrder().get(i).getProductID().compareTo(orderManager.getListedOrder().get(i + 1).getProductID()) > 1) {
//                        Collections.swap(orderManager.getListedOrder(), orderManager.getListedOrder().indexOf(orderManager.getListedOrder().get(i)), orderManager.getListedOrder().indexOf(orderManager.getListedOrder().get(i + 1)));
//                    }
//                }
//            }
//        }
////        for(int i=1; i<=12; i++){
//            for(int j=0; j<orderManager.getListedOrder().size()-1; j++){
//                System.out.println(orderManager.toString());
//        }

        List<SupplyOrder> inventoryList = new ArrayList<SupplyOrder>();
        inventoryList = orderManager.getListedOrder();
        Comparator<SupplyOrder> yearComparator = (o1, o2) -> (int) o1.getOrderDate().getYear() - (o2.getOrderDate().getYear());
        inventoryList.sort(yearComparator);
        Set<Integer> yearSet = new HashSet<>();
        for(SupplyOrder so: inventoryList){
            yearSet.add(so.getOrderDate().getYear());
        }
     for(int year: yearSet){
         System.out.println(year);
         //for(SupplyOrder so: inventoryList){
             //if(so.getOrderDate().getYear() == year){
                 for(int mon=1;mon<=12;mon++){
                     Hashtable<String, Integer> monthOrder = new Hashtable<String, Integer>();
                     monthOrder.clear();
                     ArrayList<String> monthOrderIds = new ArrayList<String>();
                     monthOrderIds.clear();
                     for(SupplyOrder so: inventoryList) {
                         if (so.getOrderDate().getYear() == year) {
                             if (so.getOrderDate().getMonth() == mon) {
                                 if (monthOrder.containsKey(so.getProductID())) {
                                     monthOrder.put(so.getProductID(), monthOrder.get(so.getProductID()) + 1);
                                 } else {
                                     monthOrder.put(so.getProductID(), 1);
                                     monthOrderIds.add(so.getProductID());
                                 }
                             }
                         }
                     }
                     if (!monthOrderIds.isEmpty()) {
                         monthOrderIds.sort(String::compareToIgnoreCase);
                         for (String ordId: monthOrderIds) {
                             System.out.println("Product Id: "+ordId+" Amount: "+monthOrder.get(ordId)+"\n");
                         }
                     }
                 }
             }
        // }
    // }

    }

}
