package lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumerLock4 {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition cA = lock.newCondition();
    private static Condition cB = lock.newCondition();
    private static Condition cC = lock.newCondition();

    private static boolean flg;
    private static CountDownLatch latchB = new CountDownLatch(1);
    private static CountDownLatch latchC = new CountDownLatch(1);

    public static void main(String[] args) {

        Thread aThread = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i <= 10; i++) {
                    System.out.print("A");
                    cB.signal();
                    if (i == 0) latchB.countDown();
                    cA.await();

                }
//                    cB.signal();

            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }, "Thread A");

        Thread bThread = new Thread(() -> {
            try {
                latchB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {

                for (int i = 0; i <= 10; i++) {
                    System.out.print("B");
                    cC.signal();
                    if (i == 0) latchC.countDown();
                    cB.await();
                }
//                    cC.signal();


            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }, "Thread B");

        Thread cThread = new Thread(() -> {
            try {
                latchC.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {

                for (int i = 0; i <= 10; i++) {
                    System.out.print("C");

                    cA.signal();
                    cC.await();
                }
//                    cA.signal();

            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }, "Thread C");

        aThread.start();
        cThread.start();
        bThread.start();

    }
}


