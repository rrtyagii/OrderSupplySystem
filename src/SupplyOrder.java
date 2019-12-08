public class SupplyOrder extends LocalDate {
    //a static variable to keep account of order created
    //will be used to assign order id

    private static int nextOrderID=1;

  //Non static variable for different things for the order
    private int orderID ;     //final so that it cannot be changed further
    private String customerID;
    private String productID;
    private LocalDate orderDate;
    private double amount;

    //Non-parametrised constructor
    public SupplyOrder(){
        this.orderID=0;
        this.setCustomerID("");
        this.setProductID("");
        this.setOrderDate(new LocalDate());
        this.setAmount(0.0);
    }

    /** Parametrised Constructor with the following parameters:-
     * @param newCustomerID
     * @param newProductID
     * @param newOrderDate
     * @param newAmount
     * used to make an object with all the information
     * It assigns the orderID for the order using the class variable
     * during the object initialization
      */
    public SupplyOrder(String newCustomerID, String newProductID, LocalDate newOrderDate, double newAmount){
        this.orderID=SupplyOrder.nextOrderID;
        nextOrderID++;

        //populating class fields using class methods
        this.setCustomerID(newCustomerID);
        this.setProductID(newProductID);
        this.setOrderDate(newOrderDate);
        this.setAmount(newAmount);
    }

    /**
     * Copy Constructor with the parameter
     * @param otherOrder
     *
     */
    public SupplyOrder(SupplyOrder otherOrder){
     if(otherOrder!=null){
         this.orderID=otherOrder.getOrderID();
         this.setCustomerID(otherOrder.getCustomerID());
         this.setProductID(otherOrder.getProductID());
         this.setOrderDate(otherOrder.getOrderDate());
         this.setAmount(otherOrder.getAmount());
     }
     else{
         this.orderID=0;
         this.setCustomerID("");
         this.setProductID("");
         this.setOrderDate(new LocalDate());
         this.setAmount(0.0);
     }
    }

    /**
     * Accessor for orderID
     * @return orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Accesor for customerID
     * @return the customerID of the object
     */

    public String getCustomerID() {
        return new String (this.customerID);

    }


    /**
     * Accesor for productID
     * @return the productID of the object
     */
    public String getProductID() {
        return new String(this.productID);
    }

    /**
     * Accesor for Date of Order
     * @return LocalDate which is the order date of the object
     */

    public LocalDate getOrderDate() {
        return new LocalDate(this.orderDate.getMonth(), this.orderDate.getDay(), this.orderDate.getYear());

    }

    /**
     * Accesor for the amount
     * @return the amount of the order( of the object)
     */
    public double getAmount() {
        return amount;
    }

    /**Set the customerID after checking for null
     * @param customerID
     * otherwise resets it
     */
    public void setCustomerID(String customerID) {
        try {
            if(!(customerID.equals(null)))
            this.customerID = customerID;
            else {
                this.customerID = "";
                throw new SupplyOrderException("Invalid Customer ID");
            }
        } catch(SupplyOrderException soe){
            soe.getMessage();
        }
    }

    /**
     * Mutator for the productID
     * @param ProductID checks for validation
     *                  reset it otherwise
     */
    public void setProductID(String ProductID) {
        try {
            if(ProductID!=null)
            this.productID = ProductID;
            else {
            this.productID = "";
            throw new SupplyOrderException("Invalid Product ID");
        }
        } catch(SupplyOrderException soe){
            soe.getMessage();
        }
    }

    /**
     * Mutator for the date of the order
     * @param orderDate check for null first and sets it to the @param orderDate
     *                  resets it otherwise
     */
    public void setOrderDate(LocalDate orderDate) {
        try {
            if(orderDate!=null){
            LocalDate newOrderDAte = new LocalDate(orderDate.getMonth(), orderDate.getDay(), orderDate.getYear());
            this.orderDate = newOrderDAte;
            }
            else{
                this.orderDate=new LocalDate();
             throw new SupplyOrderException("Invalid date");
            }
        } catch(SupplyOrderException soe){
            soe.getMessage();
        }
    }

    /**
     * set amount after checking for valid input
     * @param amount
     * amount greater than 0.0 is accepted
     * resetted, otherwise
     */
    public void setAmount(double amount){
        try{
            if(amount>=0.0){
                this.amount=amount;
            }
            else {
                this.amount = 1;
                throw new SupplyOrderException("Invalid amount, resetted to 1");
            }
        } catch(SupplyOrderException soe){
            soe.getMessage();
        }
    }

    // toString Method
    public String toString() {
        return 	"Order ID=" + this.orderID + " | Customer ID=" + this.customerID + " | Product ID=" + this.getProductID() + " | Order Date=" + this.orderDate + " | Amount=" + this.amount;
    }

    public boolean repeats()
    {
        return false;
    }

    public LocalDate getEndDate(){

        return null;
    }

}
