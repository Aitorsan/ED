package asf.exceptions;
/**
 * Exception to handle bad access indices for containers 
 * @author Aitor sanmartin ferreira
 *
 */
public class OutOfBoundsException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	
	public OutOfBoundsException(String message) {
		super(message);
	}
	

}
