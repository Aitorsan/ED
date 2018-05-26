package aec2.metro.implementacion.undergroundapp;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
public class SecurityManager extends JFrame {
	private static final long serialVersionUID = 1L;
	
	
	//variables
	private final int width = 400;
	private final int height = 300;
	private Employee[]employeData;

	private String user;
	private String password;
	private JPasswordField passwordField;
	private JTextField userField;
	private JLabel labelUsuario;
	private JLabel labelPassword;
	private JButton resetFields;
	private JButton confirm;
	private JButton exit;
	
	//constructor
	public SecurityManager(String password) {
		this.password = password;
		inicializarComponentes();
	
		this.setPreferredSize(new Dimension(width,height));
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void inicializarComponentes() {
	
		
		employeeData = Loader.getEmployeesData();
		SpringLayout layout = new SpringLayout();
		
		this.getContentPane().setLayout(layout);
	    	
		confirm = new JButton("Aceptar");
		exit = new JButton("Salir");
		resetFields = new JButton("Clear");
		
		userField = new JTextField();
	    passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		labelUsuario= new JLabel("Usuario:");
		labelPassword = new JLabel("Password");
		layout.putConstraint(SpringLayout.NORTH, labelUsuario, 10, SpringLayout.NORTH,this);
		layout.putConstraint(SpringLayout.WEST,labelUsuario, 20,SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH,userField,10,SpringLayout.SOUTH,labelUsuario);
		layout.putConstraint(SpringLayout.NORTH, labelPassword, 10, SpringLayout.NORTH, userField);
		layout.putConstraint(SpringLayout.NORTH, passwordField, 10, SpringLayout.SOUTH,labelPassword);
	
		
		
		
	
	   this.getContentPane().add(labelUsuario);
	   this.getContentPane().add(userField);
	   this.getContentPane().add(labelPassword);
	   this.getContentPane().add(passwordField);
	   
	
	
	}	
	
	public boolean searchEmployee(String employee) {
		
		
		return true;
	}
	
	
	class ButtonPressed implements ActionListener{
	
	

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(searchEmployee(userField.getText())) {
			
				
			}else {
				throw new Exception("Employee is not autorized");
			}
			
		}
		
		
	}
	
	
	public void askPassword() {
		 JOptionPane.showInputDialog(this,passwordField.getPassword());
	}
	
	public boolean verifyPassword() {
		return (this.password.equals(password)) ;
	}
}
