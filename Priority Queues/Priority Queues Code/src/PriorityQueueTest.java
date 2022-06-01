import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class PriorityQueueTest {

    public static void main(String[] args) {
        final int N = 4;
        PriorityQueue[] queues = new PriorityQueue[N];
        for(int i = 0; i < N; i++) {
            queues[i] = new PriorityQueue();
        }
        try {
            File queueData = new File("src/data.txt");
            Scanner fileReader = new Scanner(queueData);
            while (fileReader.hasNextLine()) {
                String[] data = fileReader.nextLine().split(" ");
                System.out.printf("Read key " + data[0] + " for queue " + data[1] + ". ");
                queues[parseInt(data[1])].insert(data[0].charAt(0));
                System.out.printf("Q" + data[1] + ": ");
                queues[parseInt(data[1])].printQueue();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Path not found");
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("..Final Queues..");
        for(int i = 0; i < N; i++) {
            if(queues[i].getFront()==null) {
                System.out.println("Q" + i + ": Empty");
            }
            else {
                System.out.printf("Q" + i + ": ");
                queues[i].printQueue();
            }
        }
    }
}
