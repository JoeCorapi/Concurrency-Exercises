public class Person implements Runnable{
    private int id;
    private Chopstick chopstickLeft;
    private Chopstick chopstickRight;
    private int eatingCounter = 0;
    private volatile boolean isFull = false;

    public Person(int id, Chopstick chopstickLeft, Chopstick chopstickRight) {
        this.id = id;
        this.chopstickLeft = chopstickLeft;
        this.chopstickRight = chopstickRight;
    }

    @Override
    public void run() {

        try {
            while(!isFull) {
                think();
                if (chopstickLeft.pickUp(this, State.LEFT)) {
                    if (chopstickRight.pickUp(this, State.RIGHT)) {
                        eat();
                        chopstickRight.putDown(this, State.RIGHT);
                    }
                    chopstickLeft.putDown(this,State.LEFT);
                }
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void eat() {
        this.eatingCounter++;
        System.out.println(this + " is eating...");
    }

    public void think() throws InterruptedException{
        System.out.println(this + " is thinking...");
        Thread.sleep(1000);
    }

    public void setFull(boolean isFull){
        this.isFull = isFull;
    }

    public int getEatingCounter() {
        return this.eatingCounter;
    }

    @Override
    public String toString() {
        return "Person " + id;
    }
}
