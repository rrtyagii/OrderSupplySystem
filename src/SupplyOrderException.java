public class SupplyOrderException extends Exception {
    public SupplyOrderException(){
        super();
    }

    public SupplyOrderException(String statement){
        super(statement);
    }
}
