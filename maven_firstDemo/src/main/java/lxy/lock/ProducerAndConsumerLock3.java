package lxy.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumerLock3 {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition cA = lock.newCondition();
    private static Condition cB = lock.newCondition();
    private static Condition cC = lock.newCondition();

    private static boolean flg;
//    private static CountDownLatch latchB = new CountDownLatch(1);
//    private static CountDownLatch latchC = new CountDownLatch(1);

    public static void main(String[] args) {

        String s ="哈哈哈";
        int i1 = s.hashCode();
        System.out.println(i1);

        String s1 = new String("哈哈哈");
        int i2 = s1.hashCode();
        System.out.println(i1);

        Thread aThread = new Thread(() -> {
            flg = true;
            lock.lock();
            try {
                if (flg) {
                    for (int i = 0; i <= 10; i++) {
//                        System.out.print("A");
                        System.out.println("11111"+flg);
                        cB.signal();
                        cA.await();
                    }
//                    cB.signal();
                }
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }, "Thread A");

        Thread bThread = new Thread(() -> {

            lock.lock();
            try {
                if (flg) {
                    for (int i = 0; i <= 10; i++) {
//                        System.out.print("B");

                        System.out.println("2222"+flg);
                        cC.signal();
                        cB.await();
                    }
//                    cC.signal();
                }
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }, "Thread B");

        Thread cThread = new Thread(() -> {
            lock.lock();
            try {
                if (flg) {
                    for (int i = 0; i <= 10; i++) {
//                        System.out.print("C");
                        System.out.println("3333"+flg);

                        cA.signal();
                        cC.await();
                    }
//                    cA.signal();
                }
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


