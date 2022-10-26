/**
 * Author: Edward Gregg
 * Class: CMSC204
 * Assignment: Assignment 3 Doubly Linked List
 * Due Date: 25OCT2022
 * Ref Materials Used:
 * https://www.javatpoint.com/java-program-to-sort-the-elements-of-the-doubly-linked-list
 * https://www.geeksforgeeks.org/doubly-linked-list/
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
	protected Node head, tail;
	protected int size;
	
/**
 * Sets head and tail to null. I learned later that head and tail are set to null by default.
 */
	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node newNode = new Node(data);
		if (size==0) {
			
			
			
			head = tail = newNode;
			
			
			
		}
		
		
		
		size++;
		tail.next = newNode;
		newNode.next = head;
		tail = newNode;
		return this;
	}
	

	public BasicDoubleLinkedList<T> addToFront(T data) {
		Node newNode = new Node(data);
		if (size==0) {
			
			
			
			head = tail = newNode;
			
			
			
		}
		
		
		
		
		tail.next = newNode;
		newNode.next = head;
		head = newNode;
		size++;
		return this;
	}
	
/**
 * data is the list and gets the head of data
 * @return
 */
	public T getFirst() {
		return head.data;
	}
	
/**
 * data is the list and gets the tail of data
 * @return
 */
	public T getLast() {
		return tail.data;
	}
	
	/**
	 * size is incremented or decremented with each action
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
/**
 * removes the current node. Sets the next node as the current node by setting the tail.next as the head.next and the current head as head.next
 * @param targetData
 * @param comparator
 * @return
 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator){
		Node curr = head;
		Node prev = tail;
		while (comparator.compare(targetData, curr.data)!=0) {
			curr = curr.next;
			prev = prev.next;
		}	
		if (curr==head) {
			tail.next = head.next;
			head = head.next;
		}else if (curr==tail) {
			prev.next = curr.next;
			tail = prev;
		}else {
			prev.next = curr.next;
			curr = null;
		}
		size--;
		return this;
	}
	

	public T retrieveFirstElement() {
		T output = head.data;
		head = head.next;
		size--;
		return output;
	}
	

	public T retrieveLastElement() {
		T output = tail.data;
		Node temp = head;
		for (int steps = 1; steps < size - 1; steps++) {
			temp = temp.next;
		}
		temp.next = head;
		tail = temp;
		size--;
		return output;
	}
	

	public ArrayList<T> toArrayList(){
		ArrayList<T> output = new ArrayList<T>(size);
		Node temp = head;
		for (int steps = 0; steps < size; steps++) {
			output.add(temp.data);
			temp=temp.next;
			if(temp != head) {
				return output;
			}
		}
		return output;
	}
	

	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
		return new listIterator<T>();
	}
	

	/**
	 * iterator method that iterates through updating each node as it moves from element to element.
	 * @author EGREGG
	 *
	 * @param <T>
	 */
	protected class listIterator<T> implements ListIterator<T> {
		private int index;
		private Node curr;
		private Node prev;
		
		listIterator(){
			index = 0;
			curr = head;
			prev = tail;
		}
		
		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public T next() {
			T output = (T) curr.data;
			if (!hasNext()) {
				throw new NoSuchElementException();
			}else {
				curr = curr.next;
				prev = prev.next;
				index++;
			}
			return output;
		}

		@Override
		public boolean hasPrevious() {
			return index > 0;
		}

		
		/**
		 * 
		 */
		@Override
		public T previous() {
			T output = (T) prev.data;
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}else {
				curr = prev;
				Node temp = head;
				while(temp.next != prev) {
					temp = temp.next;
				}
				prev = temp;
				index--;
			}
			return output;
		}

		
		@Override
		public void add(T e) {
			throw new UnsupportedOperationException();
		}
		
		
		@Override
		public void set(T e) {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	protected class Node {
		T data;
		Node next;
		
		Node(T d){
			data = d;
			next = null;
		}
	}
}