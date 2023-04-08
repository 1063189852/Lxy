package lxy.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumerLock {
    private static int count = 0;
    private int max = 10;
    ReentrantLock lock = new ReentrantLock();
    Condition producerCondition = lock.newCondition();
    Condition consumerCondition = lock.newCondition();

    private static volatile int size;

    public static void main(String[] args) {
//        ProducerAndConsumerLock test = new ProducerAndConsumerLock();

//        String s = Integer.toBinaryString(test.hashCode());
        String s = "12345678abcdefghijk";
        List<String> ss = new ArrayList<>();
        int fast = s.length() % 8;
        for (int i = s.length(); i >= 0; i--) {
            int begin;
            int end;
            if ((i-fast) % 8 == 0) {
                begin = i-fast;
                end = Math.min(begin + 8, s.length());
                String substring = s.substring(begin, end);
                ss.add(substring);
            }
        }


//        StringBuffer sb1 = new StringBuffer();
//        s = sb1.append(s).reverse().toString();
//
//
////        List<String> ss = new ArrayList<>();
//        StringBuffer sb = new StringBuffer();
//
//        for (int i = 0; i < s.length(); i++) {
//            int begin;
//            int end;
//            if (i % 8 == 0) {
//                begin = i;
//                end = Math.min(begin + 8, s.length());
//                String substring = s.substring(begin, end);
//                ss.add(substring);
//            }
//        }
//
//        for (String str : ss) {
//            sb.append(str).append(" ");
//        }
        System.out.println(ss);
//        System.out.println(sb);
//        System.out.println(sb.reverse());

//        new Thread(test.new Producer()).start();
//        new Thread(test.new Producer()).start();
//        new Thread(test.new Consumer()).start();
//        new Thread(test.new Consumer()).start();
    }

    class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    Thread.sleep(500);
                    while (count >= max) {
                        producerCondition.await();
                        System.out.println("生产能力达到上限进入等待状态" + count);
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前共有" + count);
                    consumerCondition.signalAll();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                lock.lock();


                try {
                    Thread.sleep(500);
                    while (count <= 0) {
                        consumerCondition.await();
                        System.out.println("消费达到最大值" + count);
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前共有" + count);
                    producerCondition.signalAll();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
