import java.util.UUID;

enum Category {
    debits,
    credits
}

public class Transaction {

    private UUID Identifier;
    private User Recipient;
    private User Sender;
    private Category TransferCategory;
    private Integer TransferAmount;

    public Transaction(User recipient, User sender, Category transferCategory, Integer transferAmount) {
        Identifier = UUID.randomUUID();
        Recipient = recipient;
        Sender = sender;
        TransferCategory = transferCategory;
        TransferAmount = transferAmount;
        if (TransferCategory == Category.debits && transferAmount < 0) {
            System.out.println("Debit (incoming transaction) can't be negative, set to 0");
            TransferAmount = 0;
        } else if (TransferCategory == Category.credits && transferAmount > 0) {
            System.out.println("Credit (outgoing transaction) can't be positive, set to 0");
            TransferAmount = 0;
        }
    }

    public UUID getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(UUID identifier) {
        Identifier = identifier;
    }

    public User getRecipient() {
        return Recipient;
    }

    public void setRecipient(User recipient) {
        Recipient = recipient;
    }

    public User getSender() {
        return Sender;
    }

    public void setSender(User sender) {
        Sender = sender;
    }

    public Category getTransferCategory() {
        return TransferCategory;
    }

    public void setTransferCategory(Category transferCategory) {
        TransferCategory = transferCategory;
    }

    public Integer getTransferAmount() {
        return TransferAmount;
    }

    public void setTransferAmount(Integer transferAmount) {
        TransferAmount = transferAmount;
        if (TransferCategory == Category.debits && transferAmount < 0) {
            System.out.println("Debit (incoming transaction) can't be negative, set to 0");
            TransferAmount = 0;
        } else if (TransferCategory == Category.credits && transferAmount > 0) {
            System.out.println("Credit (outgoing transaction) can't be positive, set to 0");
            TransferAmount = 0;
        }
    }
}
