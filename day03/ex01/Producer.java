public class Producer extends Thread {

    enum enumOrder {
        orderEgg,
        orderHen
    }

    enumOrder orderThread = enumOrder.orderEgg;

    public void egg(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            synchronized (this) {
                if (orderThread == enumOrder.orderHen)
                    wait();
                System.out.println("Egg");
                orderThread = enumOrder.orderHen;
                notify();
                Thread.sleep(300);
            }
        }
    }

    public void hen(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            synchronized (this) {
                if ( orderThread == enumOrder.orderEgg)
                    wait();
                System.out.println("Hen");
                notify();
                orderThread = enumOrder.orderEgg;
                Thread.sleep(300);
            }
        }
    }
}
