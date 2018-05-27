package aec2.metro.implementacion.undergroundapp;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;


public class NewStationWindow extends JDialog{

	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = -3064909844178564384L;
	
	
	//Jlabels
	private JLabel nameLabel;
	//TextField
	private JTextField textField;
	//JButtons
	private JButton clearButton;
	private JButton confirmButton;
	private JButton exitButton;
	//Jpanel for the gridbaglayout

	//Constructor
	public NewStationWindow(String name) {
		this.setModal(true);
		this.setTitle(name);
		initializeComponents();
		this.setResizable(false);
		this.pack();
		this.setPreferredSize(new Dimension(100,200));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	//Initialize components method
	private void initializeComponents() {
		//Layout
		GridBagConstraints constraints = new GridBagConstraints();
		this.setLayout(new GridBagLayout());	
		constraints.insets= new Insets(5,1,1,1);

		
		//labels
		nameLabel = new JLabel("Station name: ");
		constraints.gridx=0;
		constraints.gridy=0;
		this.add(nameLabel,constraints);
		
		//Jbuttons
		confirmButton=new JButton("Aceptar");
		constraints.gridx=0;
		constraints.gridy=1;
		this.add(confirmButton,constraints);
		
		clearButton=new JButton("Borrar texto");
		constraints.gridx=1;
		constraints.gridy=1;
		this.add(clearButton,constraints);
		
		exitButton= new JButton("Salir");
		constraints.gridx=2;
		constraints.gridy=1;
		this.add(exitButton,constraints);
		
		//textfield
		textField = new JTextField(20);
		constraints.gridx=1;
		constraints.gridy=0;
		constraints.gridwidth = 3;
		this.add(textField,constraints);
		
	
	}
	
	
	
	//Method to reset the textfield
	public void reset() {
		textField.setText("");
	}
	
	
	
	//JBUtton getters to set the action listeners in other class
	public JButton getExitButton() {
		return exitButton;
	}
	
	public JButton getConfirmButton() {
		return confirmButton;
	}
	public JButton getClearButton(){
		return clearButton;
	}

}
