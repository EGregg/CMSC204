
public class StackUnderflowException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StackUnderflowException(){
		super("Pop or the stack is empty");
	}

}
