public class User {
    private int identifier;
    private String name;
    private int balance;
    private TransactionsList transList;

    User(String newName, int newBalance) {
        if (newBalance < 0) {
            System.err.println("Error: negative balance");
            System.exit(-1);
        }
        identifier = UserIdsGenerator.getInstance().generateId();
        name = newName;
        balance = newBalance;
        transList = null;
    }

    void setBalance(int newBalance) {
        balance = newBalance;
    }

    void setTransList(TransactionsList newTransList) {
        transList = newTransList;
    }

    TransactionsList getTransList() {
        return transList;
    }

    int getIdentifier() {
        return identifier;
    }

    String getName() {
        return name;
    }

    int getBalance() {
        return balance;
    }
}
