import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {

    private int id;
    private final Lock lock;

    public Chopstick(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public boolean pickUp(Person person, State state) throws InterruptedException{
        if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println(person + " picked up " + this);
            return true;
        }

        return false;
    }

    public void putDown(Person person, State state){
        lock.unlock();
        System.out.println(person + " put down " + this);
    }

    @Override
    public String toString() {
        return "Chopstick " + this.id;
    }
}
