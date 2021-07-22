public class User {

    private Integer Identifier;
    private String Name;
    private Integer Balance;

    public User(Integer identifier, String name, Integer balance) {
        Identifier = identifier;
        Name = name;
        Balance = balance;
        if (Balance < 0) {
            System.out.println("Error: balance cannot be negative. Set balance to 0");
            Balance = 0;
        }
        printUser();
    }

    public void printUser() {
        System.out.println("User [Identifier: " + Identifier + ", Name: " + Name + ", Balance: " + Balance + "]");
    }

    public Integer getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(Integer identifier) {
        Identifier = identifier;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getBalance() {
        return Balance;
    }

    public void setBalance(Integer balance) {
        Balance = balance;
        if (Balance < 0) {
            System.out.println("Error: balance cannot be negative. Set balance to 0");
            Balance = 0;
        }
    }
}
