public class Program {

    public static void main(String[] args) {
        User dima = new User(1, "Dima", 100);
        User pavel = new User(2, "Pavel", 200);
        User iren = new User(2, "Iren", 500);
        System.out.println("Get balance of user 1: " + dima.getBalance());
        System.out.println("Get balance of user 2: " + pavel.getBalance());
        System.out.println("Get balance of user 3: " + iren.getBalance());

        dima.setName("Dima The First");
        dima.setBalance(-5);
        dima.setBalance(1000);
        dima.setIdentifier(5);
        dima.printUser();

        Transaction deb = new Transaction(dima, pavel, Category.debits, 15);
        Transaction cr = new Transaction(dima, pavel, Category.credits, -5);
        System.out.println("transfer amount: " + deb.getTransferAmount());
        System.out.println("transfer amount: " + cr.getTransferAmount());
        System.out.println("trying invalid transactions: ");
        Transaction deb_problem = new Transaction(dima, pavel, Category.debits, -5);
        Transaction credit_problem = new Transaction(dima, pavel, Category.credits, 5);
        System.out.println("transfer amount: " + deb_problem.getTransferAmount());
        System.out.println("transfer amount: " + credit_problem.getTransferAmount());
    }
}
