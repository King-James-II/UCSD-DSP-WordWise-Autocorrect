package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 *
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	@Override
	public boolean add(E element )
	{
		// TODO: Implement this method
		int prevSize = size;
		LLNode<E> newNode = new LLNode<E>(element);
		if(size == 0) {
			head.next = newNode;
			tail.prev = newNode;
			newNode.prev = head;
			newNode.next = tail;
			size++;
		}
		else {
			LLNode<E> lastNode = tail.prev;
			lastNode.next = newNode;
			newNode.next = tail;
			newNode.prev = lastNode;
			tail.prev = newNode;
			size++;
		}
		if (tail.prev == newNode && newNode.next == tail && size == prevSize + 1) {
			return true;
		}
		return false;
	}

	/** Get the element at position index
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method.
		if (index < 0 || index >= size	) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> currNode = head;
		for (int i=0; i <= index; i++) {
				currNode = currNode.next;
		}
		return currNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	@Override
	public void add(int index, E element ) throws IndexOutOfBoundsException, NullPointerException
	{
		if (element == null) {
			throw new NullPointerException();
		}
		// TODO: Implement this method
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> currNode = head;
		if (index ==0) {
			newNode.next = currNode.next;
			newNode.prev = head;
			head.next = newNode;
		}
		else {
			for (int i=0; i <=index; i++) {
				currNode = currNode.next;
			}
			 newNode.prev = currNode.prev;
			 currNode.prev = newNode;
			 newNode.prev.next = newNode;
			 newNode.next = currNode;
		}
		size++;
	}


	/** Return the size of the list */
	@Override
	public int size()
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 *
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> currNode = head;
		if (index ==0) {
			currNode = head.next;
			LLNode<E> nextNode = currNode.next;
			nextNode.prev = head;
			head.next = nextNode;
		}
		else {
			for (int i=0; i <=index; i++) {
				currNode = currNode.next;
			}
			LLNode<E> nextNode = currNode.next;
			LLNode<E> prevNode = currNode.prev;
			prevNode.next = nextNode;
			nextNode.prev = prevNode;
		}
		size--;
		return currNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	@Override
	public E set(int index, E element)throws IndexOutOfBoundsException, NullPointerException
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> currNode = head;
		for (int i=0; i <=index; i++) {
			currNode = currNode.next;
		}
		E replacedData = currNode.data;
		currNode.data = element;
		return replacedData;
	}

	@Override
	public String toString() {
	    StringBuilder listString = new StringBuilder("[");
	    LLNode<E> currNode = head.next;

	    while (currNode.data != null) {
	        listString.append(currNode.toString());
	        if (currNode.next.data != null) {
	            listString.append("  ⇆  "); // Add a comma between node representations
	        }
	        currNode = currNode.next; // Move to the next node
	    }

	    listString.append("]"); // Close the list representation
	    return listString.toString(); // Return the final string
	}
}

class LLNode<E>
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e)
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E data,  LLNode<E> prev) {
		this(data);
		this.next = prev.next;
		this.prev = prev;
		prev.next = this;
		this.next.prev = this;
	}

	public LLNode(E data,  LLNode<E> prev, LLNode<E> next) {
		this(data);
		this.prev = prev;
		this.next = next;
		prev.next = this;
		next.prev = this;
	}

	@Override
	public String toString() {
		return data.toString();
	}

}
