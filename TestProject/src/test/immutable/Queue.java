/*
 * Interface class for implementing queue
 * */
package test.immutable;

public interface Queue<T> {
	// Adds the element at the end of the immutable queue
	public Queue<T> enQueue(T t);

	// Removes the element at the beginning of the immutable queue, and returns the
	// new queue.
	public Queue<T> deQueue();

	public T head();

	public boolean isEmpty();
}
