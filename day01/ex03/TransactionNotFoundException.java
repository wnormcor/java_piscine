public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException() {
        super("Error: attempt to remove a transaction with non-existent ID");
    }
}
