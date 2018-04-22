package aec1.implementacion;
import aec1.implementacion.control.*;
public class App {
/**
 * Main method to run the application
 * @param args
 */
	public static void main(String[] args) {
	
         java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
		         
				new Control_Handler();
			}

        	 
         });

	}

}
