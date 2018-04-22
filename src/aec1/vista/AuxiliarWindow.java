package aec1.vista;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AuxiliarWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	

	/*Window size*/
	private static final int MIN_WIDTH=400;
	private static final int MIN_HEIGHT=120;
		
	/*Label for the name of the client we want to delete*/
	JLabel nombre;
	
	/*JTextField to introduce the name for the client we wan to delete*/
	JTextField tNombre;
	
	/*confirmation and cancel JButtons*/
	JButton confirmationButton;
	JButton botonCancelar;
	
	/**Constructor**/
	public AuxiliarWindow(String frameName, String buttonName){
		
		setTitle(frameName);      
		setModal(true);      
		initComponents(buttonName);
		setPreferredSize(new Dimension(MIN_WIDTH,MIN_HEIGHT));
		setResizable(false);
		pack();
	    setLocationRelativeTo(null);
	    setVisible(false);	
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

   /**Method to initialize the Frame components**/
	private void initComponents(String buttonName) {
         getContentPane().setLayout(new FlowLayout());
         //label y JTextfields
		nombre = new JLabel("Nombre y Apellidos");
		tNombre = new JTextField(25);
		//Buttons
		confirmationButton = new JButton(buttonName);
		 botonCancelar= new JButton("Cancelar");
		//add components
		add(nombre);
		add(tNombre);
		add(confirmationButton);
		add(botonCancelar);
	}

	/**return the value contained into the text field**/
	public String getTextNombre() {
		return tNombre.getText();
	}
	
	/**
	 * Return the JButton to delete a client
	 */
	public JButton getConfirmationButton() {
		
		return confirmationButton;
	}

   /**
    * Return the cancel operation JButton
    */
    public JButton getCancelButton() {
    	return botonCancelar;
    }
    
    /**Reset the text fields **/
    public void resetTextFields() {
    	tNombre.setText("");
    }

}













