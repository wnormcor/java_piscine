public class Program {

    public static void main(String args[]) throws UserNotFoundException {
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

        UsersArrayList arrayOfUsers = new UsersArrayList();

        for (int i = 0; i < 22; ++i) {
            arrayOfUsers.addUser(new User(String.valueOf(i), i));
        }

        System.out.println("Num of users: " + arrayOfUsers.getNumberOfUsers());

        User user = arrayOfUsers.getUserByIndex(5);
        user.printUser();

        user = arrayOfUsers.getUserByIndex(10);
        user.printUser();

        for (int i = 5; i < 13; ++i) {
            user = arrayOfUsers.getUserById(i);
            if (user != null) {
                user.printUser();
            }

        }

    }
}
