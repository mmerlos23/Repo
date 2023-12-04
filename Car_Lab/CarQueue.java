package carLabs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
/**
 * 
 * @author Moises Merlos
 * @date 12/02/2023
 *
 */
public class CarQueue {
	private Queue<Integer> que;
	private Random random = new Random();

	public CarQueue() {
		que = new LinkedList<>();
		
		que.add(random.nextInt(4));
		que.add(random.nextInt(4));
		que.add(random.nextInt(4));
		que.add(random.nextInt(4));
		que.add(random.nextInt(4));
		que.add(random.nextInt(4));

	}
	
	public void addToQueue() {
		class AddRandom implements Runnable{

			@Override
			public void run() {
				while (true) {
                    que.add(random.nextInt(4));
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        new Thread(new AddRandom()).start();
    }

    public Integer deleteQueue() {
        return que.remove();
    }
}