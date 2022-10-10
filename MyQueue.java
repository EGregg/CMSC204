import java.util.ArrayList;

import java.util.ArrayList;
/**
 * Author: Edward Gregg
 * Class: CMSC 204 FAL2022
 * 
 * 
 * 
 * 
 * 
 */

import java.util.Objects;
import java.lang.StringBuilder;


public class MyQueue<T> implements QueueInterface<T> {
	private ArrayList<T> queue;

	private Object[] elements;
	private T[] data;
	private int index;
	private int size;
	private int front;
	private int back;
	private int maxCapacity;

	public MyQueue() {
		maxCapacity = 100;
		elements = new Object[maxCapacity];
	}

	/**
	 * constructor
	 */
	public MyQueue(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		this.front = 0;
		this.back = 0;
		this.size = 0;
		elements = new Object[maxCapacity];
	}

	/**
	 * checks if empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == maxCapacity;
	}

	/**
	 * Algorithm was given by professor Monshi, careful when touching
	 */

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		@SuppressWarnings("unchecked")
		T output = (T) elements[front];
		elements[front] = null;
		front++;
		size--;
		return output;
	}

	public int size() {
		return size;
	}

	/**
	 * Algorithm was given by professor Monshi, careful when touching
	 * takes the queue and adds to the front of the queue, throws an exception if full
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}

		if(isEmpty()) {
			front = 0;
			back = 0;
		} else {
			back++;
		}
		size++;
		elements[back] = e;
		return true;
	}

	/**
	 * stringbuilder with no arguments
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = front; i <= back; i++) {
			sb.append(elements[i]);
		}
		return sb.toString();
	}

	/**
	 * stringbuilder with delimiter argument
	 */
	public String toString(String delimiter) {
		StringBuilder sb = new StringBuilder();

		for (int i =front; i<back; i++) {
			sb.append(elements[i] + delimiter);
		}
		sb.append(elements[back]);
		return sb.toString();
	}

	/**
	 * fills the queue
	 */
	public void fill(ArrayList<T> list) {
		for (T element : list) {
			enqueue(element);
		}
	}
}
