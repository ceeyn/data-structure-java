package test_api;


import java.util.LinkedList;
import java.util.Queue;
/**
 * @ClassName WaitTest
 * @Description TODO
 * @Author @hzp
 * @Date 2024/7/17 11:01
 * @Version 1.0
 */
public class WaitTest {


   static class ProducerConsumer {
        private final Queue<Integer> queue = new LinkedList<>();
        private final int MAX_SIZE = 5;

        public void produce() throws InterruptedException {
            int value = 0;
            while (true) {
                synchronized (this) {
                    while (queue.size() == MAX_SIZE) {
                        wait(); // 队列已满，等待消费者消费
                    }
                    queue.offer(value);
                    System.out.println("Produced: " + value);
                    value++;
                    notify(); // 唤醒等待的消费者
                    Thread.sleep(1000); // 模拟生产时间
                }
            }
        }

        public void consume() throws InterruptedException {
            while (true) {
                synchronized (this) {
                    while (queue.isEmpty()) {
                        wait(); // 队列为空，等待生产者生产
                    }
                    int value = queue.poll();
                    System.out.println("Consumed: " + value);
                    notify(); // 唤醒等待的生产者
                    Thread.sleep(1000); // 模拟消费时间
                }
            }
        }

        public static void main(String[] args) {
            ProducerConsumer pc = new ProducerConsumer();

            Thread producerThread = new Thread(() -> {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread consumerThread = new Thread(() -> {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            producerThread.start();
            consumerThread.start();
        }
    }

}
