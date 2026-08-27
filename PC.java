import java.util.LinkedList;

public class PC {
    private LinkedList<Integer> buffer;
    private int capacity;
    private int itemCounter;

    public PC(int capacity) {
        this.buffer = new LinkedList<>();
        this.capacity = capacity;
        this.itemCounter = 0;
    }

    // Producer method
    public void produce() {
        while (true) {
            synchronized (this) {
                // If buffer is full, wait
                if (buffer.size() == capacity) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Add item to buffer
                buffer.add(itemCounter);
                System.out.println("Producer produced-" + itemCounter);
                itemCounter++;

                // Notify consumer
                notify();

                // Sleep for 1 second
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Consumer method
    public void consume() {
        while (true) {
            synchronized (this) {
                // If buffer is empty, wait
                if (buffer.size() == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Remove item from buffer
                int val = buffer.removeFirst();
                System.out.println("Consumer consumed-" + val);

                // Notify producer
                notify();

                // Sleep for 1 second
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create an instance of PC with capacity 2
        PC pc = new PC(2);

        // Create producer thread
        Thread producerThread = new Thread(() -> {
            pc.produce();
        });

        // Create consumer thread
        Thread consumerThread = new Thread(() -> {
            pc.consume();
        });

        // Start both threads
        producerThread.start();
        consumerThread.start();

        // Join both threads (they run indefinitely, so this will wait forever)
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
