
public class QueueUnderflowException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QueueUnderflowException() {
		super("not enough in queue");
	}

}
