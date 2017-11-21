import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {
    private int id;
    private final Lock lock;

    public Book(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public boolean checkout(Student student) throws InterruptedException {
        if (lock.tryLock(1, TimeUnit.MINUTES))  {
            System.out.println(student + " checked out book " + id);
            return true;
        }
        return false;
    }

    public void read(Student student) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(student + " read book " + id);
        lock.unlock();
    }

    @Override
    public String toString() {
        return  Integer.toString(id);
    }
}
