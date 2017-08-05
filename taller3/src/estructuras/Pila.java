package estructuras;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The <tt>Stack</tt> class represents a last-in-first-out (LIFO) stack of generic items.
 *  It supports the usual <em>push</em> and <em>pop</em> operations, along with methods
 *  for peeking at the top item, testing if the stack is empty, getting the number of
 *  items in the stack, and iterating over the items in LIFO order.
 *  <p>
 *  This implementation uses a singly-linked list with a nested class for
 *  linked-list nodes.
 *  The <em>push</em>, <em>pop</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="http://introcs.cs.princeton.edu/43stack">Section 4.3</a> of
 *  <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by Robert Sedgewick and Kevin Wayne. 
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  @param <Item> the generic type of an item in this stack
 */
public class Pila<T> implements Iterable<T> {
	private int n;          // size of the stack
	private Nodo primero;     // top of stack

	// helper linked list class
	private class Nodo {
		private T elem;
		private Nodo siguiente;
	}

	/**
	 * Initializes an empty stack.
	 */
	public Pila() {
		primero = null;
		n = 0;
	}

	/**
	 * Returns true if this stack is empty.
	 *
	 * @return true if this stack is empty; false otherwise
	 */
	public boolean isEmpty() {
		return primero == null;
	}

	/**
	 * Returns the number of items in this stack.
	 *
	 * @return the number of items in this stack
	 */
	public int size() {
		return n;
	}

	/**
	 * Adds the item to this stack.
	 *
	 * @param  item the item to add
	 */
	public void push(T item) {
		Nodo anteriorPrimero = primero;
		primero = new Nodo();
		primero.elem = item;
		primero.siguiente = anteriorPrimero;
		n++;
	}

	/**
	 * Removes and returns the item most recently added to this stack.
	 *
	 * @return the item most recently added
	 * @throws NoSuchElementException if this stack is empty
	 */
	public T pop() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		T elem = primero.elem;        // save item to return
		primero = primero.siguiente;            // delete first node
		n--;
		return elem;                   // return the saved item
	}


	/**
	 * Returns (but does not remove) the item most recently added to this stack.
	 *
	 * @return the item most recently added to this stack
	 * @throws NoSuchElementException if this stack is empty
	 */
	public T peek() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		return primero.elem;
	}

	/**
	 * Returns a string representation of this stack.
	 *
	 * @return the sequence of items in this stack in LIFO order, separated by spaces
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (T item : this)
			s.append(item + " ");
		return s.toString();
	}


	/**
	 * Returns an iterator to this stack that iterates through the items in LIFO order.
	 *
	 * @return an iterator to this stack that iterates through the items in LIFO order
	 */
	public Iterator<T> iterator()  { return new ListIterator();  }

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<T> {
		private Nodo actual = primero;
		public boolean hasNext()  { return actual != null;                     }
		public void remove()      { throw new UnsupportedOperationException();  }

		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			T item = actual.elem;
			actual = actual.siguiente; 
			return item;
		}
	}
}
