package aec2.metro.implementacion.undergroundapp;


import javax.swing.JPasswordField;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class SecurityManager extends JDialog {
	private static final long serialVersionUID = 1L;
	
	
	//variables
	private final int width = 300;
	private final int height = 100;
	final String autrizedPassword="udima2";

	private JPasswordField passwordField;
	
	private JLabel labelPassword;
	private JButton confirm;
	private JButton exit;
	
	//validation
	boolean valid = false;
	
	//constructor
	public SecurityManager() {
		
		inicializarComponentes();
	    this.setModal(true);
		this.setPreferredSize(new Dimension(width,height));
		this.setLocationRelativeTo(null);
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public String getAutrizedPassword() {
		return autrizedPassword;
	}

	public JButton getConfirm() {
		return confirm;
	}

	public JButton getExit() {
		return exit;
	}

	private void inicializarComponentes() {
	
		
           this.getContentPane().setLayout(new FlowLayout());
		
		confirm = new JButton("Aceptar");
		exit = new JButton("Salir");
	
		
	    passwordField = new JPasswordField(20);
		passwordField.setEchoChar('*');
		labelPassword = new JLabel("Password");

	
	   this.getContentPane().add(labelPassword);
	   this.getContentPane().add(passwordField);
	   this.getContentPane().add(confirm);
	   this.getContentPane().add(exit);
	
	}	
	

	
	public char[] getpassField() {
		return passwordField.getPassword();
	}
	public void reset() {
		passwordField.setText("");
	}
	public boolean verifyPassword() {
		return valid;
	}
}
