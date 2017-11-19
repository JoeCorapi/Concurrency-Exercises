import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = null;
        Person[] Persons = null;

        try {

            Persons = new Person[Constants.NUMBER_OF_PEOPLE];
            Chopstick[] chopSticks = new Chopstick[Constants.NUMBER_OF_PEOPLE];

            for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; i++) {
                chopSticks[i] = new Chopstick(i);
            }

            executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PEOPLE);

            for (int i = 0; i < Constants.NUMBER_OF_PEOPLE; i++) {
                Persons[i] = new Person(i, chopSticks[i], chopSticks[(i + 1) % Constants.NUMBER_OF_PEOPLE]);
                executorService.execute(Persons[i]);
            }

            Thread.sleep(Constants.SIMULATION_RUN_TIME);

            for (Person Person : Persons) {
                Person.setFull(true);
            }
        } finally {

            executorService.shutdown();

            while (!executorService.isTerminated()) {
                Thread.sleep(500);
            }

            for (Person Person : Persons) {
                System.out.println(Person + " eat #" + Person.getEatingCounter());
            }

        }

    }
}

