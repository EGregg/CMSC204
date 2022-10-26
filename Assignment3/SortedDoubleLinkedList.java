/**
 * Author: Edward Gregg
 * Class: CMSC204
 * Assignment: Assignment 3 Doubly Linked List
 * Due Date: 25OCT2022
 * Ref Materials Used:
 * https://www.javatpoint.com/java-program-to-sort-the-elements-of-the-doubly-linked-list
 * https://www.geeksforgeeks.org/doubly-linked-list/
 * 
 * 
 */

import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	private Comparator<T> comparator1;
	
	public SortedDoubleLinkedList(Comparator<T> comparator2){
		super();
		head = null;
		tail = null;
		size = 0;
		comparator1 = comparator2;
	}
	

	public SortedDoubleLinkedList<T> add(T data) {
		
		Node genNode = new Node(data);
		Node current = head;
		
		
		/**
		 * basic 3 parts of the node comparison, next, head and tail. If the current node doesn't exist then the genNode is the curent
		 */
		if (current == null) {
			genNode.next = genNode;
			head = genNode; 
			tail = genNode;
		}
		
		
		else if(comparator1.compare(data, current.data)<=0) {
			while(current.next != head) {
				current = current.next;
			}
			current.next = genNode;
			genNode.next = head;
			head = genNode;
			tail = current;
		}
		
		
		else {
			while (current.next != head && comparator1.compare(data, current.next.data)>0) {
				current = current.next;
			}
			if(current.next==head) {
				current.next = genNode;
				genNode.next = head;
				tail = genNode;
			}else {
				genNode.next = current.next;
				current.next = genNode;
			}
		}
		
		size++;
		return this;
	}
	

	public BasicDoubleLinkedList<T> addToEnd(T data){
		throw new UnsupportedOperationException();
	}
	

	public BasicDoubleLinkedList<T> addToFront(T data){
		throw new UnsupportedOperationException();
	}
	

	public ListIterator<T> iterator(){
		return super.iterator();
	}
	

	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
		return (SortedDoubleLinkedList<T>) super.remove(data, comparator);
	}
}