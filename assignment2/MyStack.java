/**
 * Author: Edward Gregg
 * Class: CMSC 204 FAL2022
 * 
 * 
 * 
 * 
 * 
 */

import java.util.ArrayList;

 
public class MyStack<T> implements StackInterface<T> {

    private T arr[];
    private int size;
    private int index ;
    
    
    /**
     * constructor
     * @param size
     */
    public MyStack(int size) {
        this.size = size;
        arr =   (T[]) new Object[size];
        index = 0;
    }
    
    /**
     * sets the stack max to 20
     */
    public MyStack( ) {
        this(20);
    }
    
    /**
     * sets the size empty
     */
    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    /**
     * checks if full
     */
    @Override
    public boolean isFull() {
        return index == size;
    }

    @Override
    public T pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException();
        }
        return arr[--index];
    }

    @Override
    public T top() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException();
        }
        return arr[index-1];
    }

    /**
     * 
     */
    @Override
    public int size() {
        return index;
    }

    @Override
    public boolean push(T e) throws StackOverflowException {
        if (isFull()) {
            throw new StackOverflowException();
        }

        arr[index] = e;
        index++;
        return true;
    }

    /**
     * converts list to string
     */
    @Override
    public String toString() {
         StringBuilder s = new StringBuilder();
        for (int i = 0; i <index; i++) {
            s.append(arr[i].toString());
        }
        return s.toString();
    }
    
    /**
     * converts list to string with delimiter
     */
    @Override
    public String toString(String delimiter) {
         StringBuilder s = new StringBuilder();
        for (int i = 0; i <index; i++) {
            s.append(arr[i].toString());
            if(i!=index-1)
            s.append(delimiter);
        }
        return s.toString();
    }
    

    
    /**
     * fills the array
     */
    @Override
    public void fill(ArrayList<T> list) {
        arr =   (T[]) new Object[size];
        index = 0;
        try{
        for(T t:list) {
            push(t);
        }
        }catch(StackOverflowException ex) {
            System.out.println(ex);
        }
    }

}