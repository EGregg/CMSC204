
public class QueueOverflowException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QueueOverflowException() {
		super ("exceeds capacity of stack");
	}

}
