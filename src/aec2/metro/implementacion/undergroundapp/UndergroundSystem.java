package aec2.metro.implementacion.undergroundapp;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import aec2.metro.implementacion.MetroMadrid;
import javax.swing.JOptionPane;
public class UndergroundSystem extends JFrame{
	private static final long serialVersionUID = 1L;
	private final int Width=800;
	private final int Height = 700;
	//Images
	private ImageIcon image;
	//Layout
	CardLayout clLayout;
	//Underground graf
	private MetroMadrid metro;
	//private systemLoader datos;
	private SecurityManager security;
	//JPanels 
	JPanel container;
	JPanel initialPanel;
	JPanel userPanel;
	JPanel stuffPanel;
	//JOptionPane para elegir las rutas	

	JOptionPane originStation;
	JOptionPane endStation;
	//JButtons to select panel views
	JButton userButton;
	JButton stuffButton;
    //JButtons
	JButton consultButton;
	JButton exitButton;
	
	 public UndergroundSystem(String name) {
		 this.setTitle(name);
		 security= new SecurityManager("aec2ASF11");
		 initComponents();
		 registerListeners();
		 this.setPreferredSize(new Dimension(Width,Height));
		 this.setMinimumSize(new Dimension (Width,Height));
		 this.pack();
		 this.setLocationRelativeTo(null);
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 this.setVisible(true);
	 }
	 
	 
	 private void initComponents() {
		 
		 //Layout for the contianer we use cardlayout to be able to change prespectives easily
		 clLayout = new CardLayout();
		 container = new JPanel();
		 container.setLayout(clLayout);
	
		 //intial panel setup
		 initialPanel = new JPanel();
		 SpringLayout springLayout = new SpringLayout();
		 initialPanel.setLayout(springLayout);
		 userButton =new  JButton("Realizar Consulta");
		 userButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 stuffButton = new JButton("Personal autorizado");
		 try {
			 image = new ImageIcon(getClass().getResource("../../../resources/Metro.png"));
			 
		 }catch(Exception e) {
			 System.out.println("no se ha podido cargar la imagen");
		 }
		 JLabel auxiliarImage = new JLabel(image);
		 springLayout.putConstraint(SpringLayout.WEST, auxiliarImage, 140,SpringLayout.WEST, container);
		 springLayout.putConstraint(SpringLayout.SOUTH, auxiliarImage, Height/2+60,SpringLayout.SOUTH, container);
	     initialPanel.add(auxiliarImage);
		 springLayout.putConstraint(SpringLayout.WEST, stuffButton, (Width/2)-(Width/8),SpringLayout.WEST, container);
		 springLayout.putConstraint(SpringLayout.SOUTH, stuffButton, Height/2,SpringLayout.NORTH, container);
		 springLayout.putConstraint(SpringLayout.NORTH, userButton, 50,SpringLayout.NORTH, stuffButton);
		 springLayout.putConstraint(SpringLayout.WEST, userButton, 0,SpringLayout.WEST, stuffButton);
		 springLayout.putConstraint(SpringLayout.EAST, userButton, 0,SpringLayout.EAST, stuffButton);
		 initialPanel.add(userButton);
		 initialPanel.add(stuffButton);

		 
		 
		 
		 
		 userPanel = new JPanel();
		 userPanel.setBackground(Color.GREEN);
		 stuffPanel = new JPanel();
		 stuffPanel.setBackground(Color.BLUE);
		   
		 //Jbuttons for the public user area
		 consultButton = new JButton("Consultar");
		 exitButton = new JButton("Salir");
		 
		 container.add(initialPanel,"0");
		 container.add(userPanel,"1");
		 container.add(stuffPanel,"2");
		 clLayout.show(container,"0");
		 
		 this.getContentPane().add(container);
		 
	 }
	 
	 private void registerListeners() {
		 
		 userButton.addActionListener(new ConsultAction());
		 stuffButton.addActionListener(new AutorizedAction());
		 
		 
		 
	 }
	 
	 class ConsultAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				clLayout.show(container,"1");
				
			}
			 
		 }
	 class AutorizedAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			security.setVisible(true);
			security.askPassword();
			//stuff panel only avaliable for autorized employees
			if(security.verifyPassword()) {
				//si the password matches we change the view
				
				clLayout.show(container, "2");
			}else {
				// si no avisamos que es incorrecto y dejamos como estaba el asunto
			
            	JOptionPane.showMessageDialog(null,"Solo personal autorizado, disculpe las molestias", "Informacion",JOptionPane.INFORMATION_MESSAGE);
			
		
			}
		 
	
		}
	 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
       public static void main(String args[]) {
    	   
    	   java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new UndergroundSystem("Metro Madrid");
				
			}
    		   
    		   
    		   
    	   });
    	   
       }
	
	
}
