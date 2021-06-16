import java.util.LinkedList;
import java.util.List;

public class BlockingQueue<T> {

    List<T> queue = new LinkedList<>();
    int limit = 10;

    public synchronized void put(T t) {
        if (queue.size() == limit) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (queue.isEmpty()) {
            notifyAll();
        }

        queue.add(t);
    }

    public synchronized T take() {
        if (queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (queue.size() == limit) {
            notifyAll();
        }

        return queue.remove(0);
    }
}
