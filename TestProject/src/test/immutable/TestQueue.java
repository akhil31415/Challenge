/*
 * This is a driver/test class to check the implementation of queue.
 * It gives user information regarding the operations available and prompts for input to stdout
 * It also read the user input via stdin and performs the desired operation.
 * */
package test.immutable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestQueue {

	public static void main(String[] args) {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			// print the operations of queue to user on stdout
			System.out.println("This is an implentation of Queue.\n" + "Following are the operations available:\n"
					+ "1. enQueue: To insert element into Queue\n" + "2. deQueue: To remove element from Queue\n"
					+ "3. HeadQueue: To show the elements in the Queue\n" + "To stop the operations enter -1.");

			int n;
			// create empty queue
			Queue<Integer> q = new ImmutableQueue<Integer>();
			do {
				System.out.print("\nEnter the operation no: ");
				n = Integer.parseInt(input.readLine());
				// Check the operation entered by user and perform it accordingly
				if (n == 1) {
					System.out.print("Enter the element to insert: ");
					int a = Integer.parseInt(input.readLine());
					q = q.enQueue(a);
				} else {
					// check if the queue is empty, if not perform the operation otherwise prompt
					// the user
					Integer headElement = q.head();
					if (headElement == null) {
						System.out.println("Queue is empty!");
					} else if (n == 2) {
						System.out.println("Element removed from queue: " + headElement);
						q = q.deQueue();
					} else if (n == 3) {
						System.out.println("The first element in Queue is: " + headElement);
					}else
						System.out.println("Queue is not empty!");
				}
			} while (n != -1);// end the loop when user enters -1
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
