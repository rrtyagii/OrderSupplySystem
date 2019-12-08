import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManager extends SupplyOrder {
    private static int orderIDD = 1;

  private  List<SupplyOrder> listedOrder = new ArrayList<SupplyOrder>();
  private  List<Integer> ID = new ArrayList<Integer>();

  private  int id;

    public OrderManager() {
        super();
        this.id = 0;
    }

    public void addOrder(String customerID, String productID, LocalDate date, double amount) {
        SupplyOrder order = new SupplyOrder(customerID, productID, date, amount);
        listedOrder.add(order);
        this.ID.add(++this.id);
        System.out.println(order.toString());
    }

    public void addOrder(String customerID, String productID, LocalDate date, double amount, int period,
                         LocalDate endDate) {
        SupplyOrder order = new RepeatedOrder(customerID, productID, date, amount, period, endDate);
        listedOrder.add(order);
        this.ID.add(++this.id);
        System.out.println(order.toString());
    }

    public boolean removeOrder(int orderID) throws OrderManagerException {
        boolean result = false;
        if (listedOrder.size() > 0 && orderID > 0) {
            for (SupplyOrder newOrder : listedOrder) {
                if (newOrder.getOrderID() == orderID) {
                    this.listedOrder.remove(orderID-1);
                    this.ID.remove(orderID-1);
                    result = true;
                    break;
                }
                else{
                    result = false;
                }
            }
        } else {
            throw new OrderManagerException("The list could be empty");
        }
        return result;
    }


    public void listAllOrders(int year, int month) {
        ArrayList<SupplyOrder> comicList = new ArrayList<SupplyOrder>();
        for (int i = 0; i < listedOrder.size(); i++) {
            if (listedOrder.get(i).getOrderDate().getYear() == year) {
                if (listedOrder.get(i).getOrderDate().getMonth() == month) {
                    comicList.add(this.listedOrder.get(i));
                }
            }
        }
        for (int i = 0; i < comicList.size(); i++) {
            System.out.println(comicList.get(i));
        }
    }

    public void listAllOrders(LocalDate startDate, LocalDate endDate) {
        ArrayList<SupplyOrder> comicList = new ArrayList<SupplyOrder>();
        for (int i = 0; i < listedOrder.size(); i++) {
            if (listedOrder.get(i).repeats()) {
                if (listedOrder.get(i).getOrderDate() == startDate) {
                    if (listedOrder.get(i).getEndDate() == endDate) {
                        comicList.add(listedOrder.get(i));
                    }
                }
            }
        }
        for (int i = 0; i < listedOrder.size(); i++) {
            System.out.println(comicList.get(i));
        }
    }

    public void listAllOrders(String productID) {
        ArrayList<SupplyOrder> comicList = new ArrayList<SupplyOrder>();
        for (int i = 0; i < listedOrder.size(); i++) {
            if (listedOrder.get(i).getProductID().equals(productID)) {
                comicList.add(listedOrder.get(i));
            }
        }
        for (int i = 0; i < listedOrder.size(); i++) {
            System.out.println("Products with the product ID: " + productID +
                    " \n" + comicList.get(i));
        }
    }

    public void displayInventory() {
        SupplyOrder[] inventoryArray = new SupplyOrder[listedOrder.size()];
        for (int j = 1; j <= 12; j++) {
            for (int i = 0; i < listedOrder.size(); i++) {
                if (listedOrder.get(i).getOrderDate().getMonth() == j) {
                    inventoryArray[i] = listedOrder.get(i);
                }
            }
        }
    }

    public String toString() {
        String newString = "Order# \t" + "CustomerID \t" + "ProductID \t" + "Amount \t" + "Date \t" + "Period \t" + "End Date \n";
        for (int i = 0; i < listedOrder.size(); i++) {
            newString += this.getID(this.listedOrder.get(i)) + "\t" + listedOrder.get(i).toString() + "\n";
        }
        return newString;
    }

    public int getID(SupplyOrder order) {
        if (this.listedOrder.indexOf(order) == -1) {
            System.out.println("This Order does not exist or has been removed");
            return this.listedOrder.indexOf(order);
        }
        return this.ID.get(this.listedOrder.indexOf(order));
    }

    public int getIdCounter() {

        return this.id;
    }

    /**
     * @return the ArrayList with the orders
     */
    public List<SupplyOrder> getListedOrder() {
        return listedOrder;
    }

    public List<Integer> getIDList() {

        return ID;
    }

}
