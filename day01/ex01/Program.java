public class Program {

    public static void main(String[] args) {
        User dima = new User( "Dima", 100);
        User pavel = new User("Pavel", 200);
        User iren = new User("Iren", 500);
        System.out.println("Get balance of user 1: " + dima.getBalance());
        System.out.println("Get balance of user 2: " + pavel.getBalance());
        System.out.println("Get balance of user 3: " + iren.getBalance());

        dima.setName("Dima The First");
        dima.setBalance(-5);
        dima.setBalance(1000);
        dima.printUser();
    }
}
