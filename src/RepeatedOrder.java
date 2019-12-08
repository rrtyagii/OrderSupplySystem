public class RepeatedOrder extends SupplyOrder {
    private int period;
    private LocalDate endDate;

    /**
     * Default constructor
     */
    public RepeatedOrder() {
        super();
        this.setPeriod(0);
        this.setEndDate(new LocalDate());
    }

    /**
     * Constructor with the following parameters
     * @param newCustomerID
     * @param newProdctID
     * @param newDate
     * @param newAmount
     * @param period
     * @param endDate
     * Usefull for the orders which are repeated.
     */

    public RepeatedOrder(String newCustomerID, String newProdctID, LocalDate newDate, double newAmount, int period, LocalDate endDate) {
        super(newCustomerID, newProdctID, newDate, newAmount);
        this.setPeriod(period);
        this.setEndDate(endDate);
    }

    /**
     * Copy constructor with a
     * @param newReapeatedOrder
     */
    public RepeatedOrder(RepeatedOrder newReapeatedOrder) {
        super(newReapeatedOrder.getCustomerID(), newReapeatedOrder.getProductID(), newReapeatedOrder.getEndDate(), newReapeatedOrder.getAmount());
        if (newReapeatedOrder != null) {
            this.setPeriod(newReapeatedOrder.getPeriod());
            this.setEndDate(newReapeatedOrder.getEndDate());
        }
    }

    /**
     * Accesor method for the period of the repeated Order
     * @return period
     * between the orderDate and endDate
     */
    public int getPeriod() {
        return this.period;
    }

    /**
     *Accesor method  for the endDate of the object
     * @return the endDate of the object(of the Order)
     */
    public LocalDate getEndDate() {
        return new LocalDate(this.endDate.getMonth(), this.endDate.getDay(), this.endDate.getYear());
    }

    /**
     * Mutator for the setting up the period between the date of the order
     * and the end date of the order
     * @param period
     * check if it is greater than 0
     * resets it to 1 otherwise
     */
    public void setPeriod(int period) {
        try {
            if (period > 0) {
                this.period = period;
            } else {
                this.period = 1;
                throw new RepeatedOrderException("Period should be greateer than 0");
            }
        } catch (RepeatedOrderException rpe) {
            rpe.getMessage();
        }
    }

    /**
     * Mutator for setting up the endDate of the order
     * @param enddate
     * checks for the valid endDate
     * resets it otherwise
     */
    public void setEndDate(LocalDate enddate) {
        try {  if(enddate!=null) {
            try {
                if (!compareTo(enddate, super.getOrderDate())) {
                    this.endDate = enddate;
                } else  {
                    this.endDate=new LocalDate();
                    throw new RepeatedOrderException("End date should be after the orderDate");
                }
            } catch(RepeatedOrderException roe){
                roe.getMessage();
            }
        }
            else{ this.endDate=new LocalDate();
                 throw new RepeatedOrderException("Date is null");
                }
        } catch(RepeatedOrderException roe){
            roe.getMessage();
        }
    }

    /**
     * Method to help checking us the difference between date of order and end date of the order
     * @param date1
     * @param date2
     * @return true if date1 is before date2
     *         false, otherwise
     */
    private boolean compareTo(LocalDate date1, LocalDate date2){
        if(date1.getYear()>date2.getYear())
            return true;
        else if(date1.getMonth()>date2.getMonth())
            return true;
        else if(date1.getDay()>date2.getDay())
            return true;
        else
            return false;
    }

    @Override
    public boolean repeats(){
        return true;
    }

    @Override
    public String toString(){
        return super.toString() + " | Period=" + this.period + " | End Date =" + this.endDate;
    }



}
