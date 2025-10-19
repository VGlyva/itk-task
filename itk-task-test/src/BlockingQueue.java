public class BlockingQueue<T> {
    private final T[] queue;
    private int first_element = 0;
    private int next_element = 0;
    private int size_element = 0;
    private final int capacity;
    @SuppressWarnings("unchecked")
    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = (T[]) new Object[capacity];
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (size_element == capacity) {
            wait();
        }
        queue[next_element] = item;
        next_element = (next_element + 1) % capacity;
        size_element++;
        notifyAll();
    }
    public synchronized T dequeue() throws InterruptedException{
        while (size_element == 0) {
            wait();
        }
        T element = queue[first_element];
        queue[first_element] = null;
        first_element = (first_element + 1) % capacity;
        size_element--;
        notifyAll();
        return element;
    }

    public synchronized int size () {
        return size_element;
    }
}
