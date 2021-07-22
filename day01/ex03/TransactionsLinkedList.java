import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList{
    private Transaction head;
    private Transaction tail;

    TransactionsLinkedList() {
        head = tail = null;
    }

    public Transaction getHead() {
        return head;
    }

    public Transaction getTail() {
        return tail;
    }

    public void addTransaction(Transaction transaction) {
        if (head == tail && head == null) {
            head = tail = transaction;
        }
        else if (head != null && head == tail) {
            tail = transaction;
            head.nextTransaction = transaction;
            tail.prevTransaction = head;
        }
        else {
            tail.nextTransaction = transaction;
            transaction.prevTransaction = tail;
            tail = transaction;
        }
    }

    public void removeTransaction(UUID ID) throws TransactionNotFoundException{
        Transaction tmp;
        tmp = head;
        while (tmp != null) {
            if (tmp.getIdentifier().equals(ID)) {
                if (tmp == head && tmp == tail) {
                    head = null;
                    tail = null;
                    tmp.prevTransaction = null;
                    tmp.nextTransaction = null;
                    return;
                }
                if (tmp == head) {
                    head.nextTransaction.prevTransaction = null;
                    head = head.nextTransaction;
                    tmp.nextTransaction = null;
                    return;
                }
                if (tmp == tail) {
                    tail.prevTransaction.nextTransaction = null;
                    tail = tail.prevTransaction;
                    tmp.prevTransaction = null;
                    return;
                }
                tmp.prevTransaction.nextTransaction = tmp.nextTransaction;
                tmp.nextTransaction.prevTransaction = tmp.prevTransaction;
                tmp.nextTransaction = tmp.prevTransaction = null;
                return;
            }
            tmp = tmp.nextTransaction;
        }
        throw new TransactionNotFoundException();
    }

    public Transaction[] toArray(){
        int count = 0;
        Transaction tmp;
        tmp = head;
        while (tmp != null) {
            count++;
            tmp = tmp.nextTransaction;
        }
        if (count == 0) {
            return null;
        }
        Transaction []transArr = new Transaction[count];
        tmp = head;
        for (int i = 0; i < count; i++) {
            transArr[i] = tmp;
            tmp = tmp.nextTransaction;
        }
        return transArr;
    }
}
