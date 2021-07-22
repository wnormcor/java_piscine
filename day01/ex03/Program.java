import java.util.UUID;

public class Program {

    private static void printSelUser(User user) {
        System.out.println("User id: " + user.getIdentifier());
        System.out.println("User name: " + user.getName());
        System.out.println("User balance: " + user.getBalance());
    }

    private static void printOneSelTrans(Transaction transaction) {
        System.out.println("Transaction id: " + transaction.getIdentifier());
        System.out.println("Transaction category: " + transaction.getTransferCategory());
        System.out.println("Transaction amount: " + transaction.getTransferAmount());
        System.out.println("Transaction recipient:");
        printSelUser(transaction.getRecipient());
        System.out.println("Transaction sender:");
        printSelUser(transaction.getSender());
    }

    private static void printAllTrans(TransactionsLinkedList tranList) {
        Transaction head = tranList.getHead();
        Transaction tail = tranList.getTail();
        while (head != null) {
            printOneSelTrans(head);
            head = head.nextTransaction;
        }
    }

    public static void main(String[] args) {

		// создаем двух пользователей для теста
        User userOne = new User("User Number 1 (account 100)", 100);
        User userTwo = new User("User Number 2 (account 200)", 200);

        Transaction transOne = new Transaction(UUID.randomUUID(), userOne, userTwo, "debit", 200);
        Transaction transTwo = new Transaction(UUID.randomUUID(), userOne, userTwo, "debit", 200);

		// создаем лист транзакций
        TransactionsLinkedList tranList = new TransactionsLinkedList();

        Transaction transArr[];
        tranList.addTransaction(transOne);
        tranList.addTransaction(transTwo);

		// добавим лист транзакций к первому пользователю
        userOne.setTransList(tranList);

		// распечатаем лист транзакций
        printAllTrans(tranList);

		// вызовем метод .toArray() в нашем классе транзакционных массивов + выведем его
		transArr = tranList.toArray();
		System.out.println();
        System.out.println("Print TransactionsLinkedList as array:");
        for (int i = 0; i < transArr.length; i++) {
            printOneSelTrans(transArr[i]);
        }

		// удалим транзакцию по идентификатору из массива
        tranList.removeTransaction(transTwo.getIdentifier());
		System.out.println();
        System.out.println("TransactionsLinkedList after removing");
        printAllTrans(tranList);

        tranList.removeTransaction(transOne.getIdentifier());
		System.out.println();
        System.out.println("TransactionsLinkedList after removing one more transaction");
        printAllTrans(tranList);
	
		// попробуем удалить несодержащуюся транзакцию
        System.out.println("\nTrying to remove transaction from empty TransactionsLinkedList");
        try {
            tranList.removeTransaction(transOne.getIdentifier());
        } catch (TransactionNotFoundException e) {
            e.printStackTrace();
        }
    }
}
