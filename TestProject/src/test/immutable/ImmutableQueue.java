/*
 * This class is the implementation of Immutable Queue using 2 Immutable stacks.
 * It has stacks named 'front' which manages the data for deQueue operation
 * and 'back' which manages data for enQueue operation
 * The 4 method implemented from Queue interface are as below:
 * 1. head() :	returns the first element in the queue
 * 2. isEmpty() : returns boolean value true if queue is empty or false if queue is not empty
 * 3. enQueue() : returns a new Queue object adding the passed element at the end of queue
 * 4. deQueue()	: returns a new Queue object removing the element at the front of the queue
 * 		*deQueue() method has a time complexity of O(n) where n is the number of elements present in the queue 
 * 		when performing the operation. This is due to reversing the 'back' stack since a stacks is a reverse of queue.
 * 		In best case scenario, it is O(1) along with other methods.
 * 		
 * */
package test.immutable;

class ImmutableQueue<T> implements Queue<T> {

	// implementation of immutable stack
	private class ImmutableStack {
		private final T head;
		private final ImmutableStack tail;

		// public constructor create empty stack
		public ImmutableStack() {
			this.head = null;
			this.tail = null;
		}

		// private constructor to create new stack
		private ImmutableStack(T head, ImmutableStack tail) {
			this.head = head;
			this.tail = tail;
		}

		public ImmutableStack push(T t) {
			return new ImmutableStack(t, this);
		}

		public ImmutableStack pop() {
			return this.tail;
		}

		public T top() {
			return this.head;
		}

		public boolean isEmpty() {
			return this.head == null && this.tail == null;
		}

		// a function to reverse the queue data maintained in stack for removing the
		// element (LIFO-FIFO)
		public ImmutableStack reverse() {
			ImmutableStack result = new ImmutableStack();
			ImmutableStack tmp = this;
			while (!tmp.isEmpty()) {
				result = result.push(tmp.top());
				tmp = tmp.pop();
			}
			return result;
		}
	}

	// Create 2 stacks to handle data insert and delete from queue
	private final ImmutableStack front;
	private final ImmutableStack back;

	// empty queue constructor using two empty stacks having public access to create
	// object
	public ImmutableQueue() {
		this.front = new ImmutableStack();
		this.back = new ImmutableStack();
	}

	// private constructor to be used by methods internally to create new object
	private ImmutableQueue(ImmutableStack front, ImmutableStack back) {
		this.front = front;
		this.back = back;
	}

	public Queue<T> enQueue(T t) {
		// if front is empty, add element in back stack and create a queue with it's
		// front
		if (this.front.isEmpty()) {
			return new ImmutableQueue<T>(this.back.push(t), new ImmutableStack());
		}
		return new ImmutableQueue<T>(this.front, this.back.push(t));
	}

	public Queue<T> deQueue() {
		ImmutableStack newFront = this.front.pop();
		// if case front stack is empty after removing the element, reverse the back
		// stack as
		// front stack
		if (newFront.isEmpty()) {
			// if the back stack is empty too, then just return a empty queue
			if (this.back.isEmpty())
				return new ImmutableQueue<>();

			return new ImmutableQueue<T>(this.back.reverse(), new ImmutableStack());
		}
		return new ImmutableQueue<T>(newFront, this.back);
	}

	public T head() {
		return this.front.top();
	}

	public boolean isEmpty() {
		return this.front.isEmpty() && this.back.isEmpty();
	}
}
