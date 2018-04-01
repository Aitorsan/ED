package aec1;
import aec1.control.*;
public class App {

	public static void main(String[] args) {
	
         java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
		         
				new Control_Handler();
			}
        	     
        	 
        	 
         });

	}

}
