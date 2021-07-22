import java.util.UUID;

public class Transaction {

    private UUID identifier;
    private User recipient;
    private User sender;
    private String transferCategory;
    private int transferAmount;
    Transaction prevTransaction;
    Transaction nextTransaction;

    Transaction(UUID newIdentifier, User newRecipient, User newSender, String newTransferCategory, int newTransferAmount) {
        if (newTransferCategory.equals("debit") && newTransferAmount < 0) {
            System.err.println("Error: negative debit transfer amount");
            System.exit(-1);
        }
        if (newTransferCategory.equals("credit") && newTransferAmount > 0) {
            System.err.println("Error: positive credit transfer amount");
            System.exit(-1);
        }
        if (!(newTransferCategory.equals("debit") || newTransferCategory.equals("credit"))) {
            System.err.println("Error: invalid transfer category");
            System.exit(-1);
        }
        identifier = newIdentifier;
        recipient = newRecipient;
        sender = newSender;
        transferCategory = newTransferCategory;
        transferAmount = newTransferAmount;
        prevTransaction = null;
        nextTransaction = null;
    }

    void setTransferAmount(int newTransferAmount) {
        if (transferCategory.equals("debit") && newTransferAmount < 0){
            System.err.println("Error: negative debit transfer amount");
            System.exit(-1);
        }
        if (transferCategory.equals("credit") && newTransferAmount > 0){
            System.err.println("Error: positive credit transfer amount");
            System.exit(-1);
        }
        transferAmount = newTransferAmount;
    }

    UUID getIdentifier() {
        return identifier;
    }

    User getRecipient() {
        return recipient;
    }

    User getSender() {
        return sender;
    }

    String getTransferCategory() {
        return transferCategory;
    }

    int getTransferAmount() {
        return transferAmount;
    }
}
