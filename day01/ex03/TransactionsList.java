import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeTransaction(UUID ID) throws TransactionNotFoundException;
    Transaction[] toArray();
}
