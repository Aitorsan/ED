package aec2.metro.implementacion.undergroundapp;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JTextArea;
import aec2.metro.implementacion.MetroMadrid;

public class UndergroundSystem extends JFrame{
	private static final long serialVersionUID = 1L;
	private final int Width=800;
	private final int Height = 500;
	//Images
	private ImageIcon image;
	private ImageIcon image2;
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
    
	JComboBox<String> originStation;
	JComboBox<String> endStation;
	//JTextArea for the information to be displayed
	JTextArea infoArea;
	//JButtons to select panel views
	JButton userButton;
	JButton stuffButton;
    //JButtons
	JButton consultButton;
	JButton exitButton;
	//Jlabels
	JLabel originLabel;
	JLabel destinyLabel;
	//Jbuttons for add new stations or delete
	JButton addStationButton;
	JButton addConectionButton;
	JButton deleteConectionButton;
	JButton isolateStationButton;
	JButton deleteStationButton;
	
	 public UndergroundSystem(String name) {
		 this.setTitle(name);
		 metro = Loader.getMetroData();
		 security= new SecurityManager();
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
		 initialPanel.setBackground(new Color(50,100,90));
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
		 springLayout.putConstraint(SpringLayout.SOUTH, auxiliarImage, Height,SpringLayout.SOUTH, container);
	     initialPanel.add(auxiliarImage);
		 springLayout.putConstraint(SpringLayout.EAST, stuffButton, (Width/4),SpringLayout.WEST, container);
		 springLayout.putConstraint(SpringLayout.NORTH, stuffButton, 20,SpringLayout.NORTH, container);
		 springLayout.putConstraint(SpringLayout.NORTH, userButton, 50,SpringLayout.NORTH, stuffButton);
		 springLayout.putConstraint(SpringLayout.WEST, userButton, 0,SpringLayout.WEST, stuffButton);
		 springLayout.putConstraint(SpringLayout.EAST, userButton, 0,SpringLayout.EAST, stuffButton);
		 initialPanel.add(userButton);
		 initialPanel.add(stuffButton);

		 
		 //userPanel
		 SpringLayout layoutUsers = new SpringLayout();
		 userPanel = new JPanel();
		 userPanel.setLayout(layoutUsers);
		 userPanel.setBackground(new Color(50,100,90));
		 infoArea = new JTextArea();
		 infoArea.setEditable(false);
		 consultButton= new JButton("Consultar");
		 exitButton = new JButton("Salir");
		 originLabel = new JLabel("Origen:");
		 destinyLabel = new JLabel("Destino:");
		 
		 
		 
		
		 originStation = new JComboBox<String>(metro.getStations());
		 endStation = new JComboBox<String>(metro.getStations());
		 
		 
		 //information text area
		 layoutUsers.putConstraint(SpringLayout.WEST,infoArea, 5, SpringLayout.WEST,userPanel);
		 layoutUsers.putConstraint(SpringLayout.EAST,infoArea, -5,SpringLayout.EAST,userPanel);
		 layoutUsers.putConstraint(SpringLayout.NORTH,infoArea,5,SpringLayout.NORTH, userPanel);
		 layoutUsers.putConstraint(SpringLayout.SOUTH, infoArea, -Height/2,SpringLayout.SOUTH,userPanel);
		 //label origin position
		 layoutUsers.putConstraint(SpringLayout.NORTH,originLabel, 10, SpringLayout.SOUTH,infoArea);
		 layoutUsers.putConstraint(SpringLayout.EAST,originLabel, Width/3, SpringLayout.WEST,userPanel);
		 //combobox origin
		 layoutUsers.putConstraint(SpringLayout.NORTH,originStation, 10, SpringLayout.SOUTH,infoArea);
		 layoutUsers.putConstraint(SpringLayout.WEST, originStation,5, SpringLayout.EAST, originLabel);
		 layoutUsers.putConstraint(SpringLayout.EAST,originStation,100, SpringLayout.EAST,originLabel);
 
		 //label destiny position
		 layoutUsers.putConstraint(SpringLayout.NORTH,destinyLabel, 20, SpringLayout.SOUTH,  originLabel);
		 layoutUsers.putConstraint(SpringLayout.EAST, destinyLabel, 0, SpringLayout.EAST,originLabel);
		 
		 //combobox destiny
		 layoutUsers.putConstraint(SpringLayout.NORTH,endStation,10,SpringLayout.SOUTH,originStation);
		 layoutUsers.putConstraint(SpringLayout.WEST, endStation, 5, SpringLayout.EAST, destinyLabel);
		 layoutUsers.putConstraint(SpringLayout.EAST,endStation,100, SpringLayout.EAST,destinyLabel);
		 //buttons
         layoutUsers.putConstraint(SpringLayout.EAST,consultButton,Width/3,SpringLayout.WEST,userPanel);
         layoutUsers.putConstraint(SpringLayout.NORTH, consultButton, 20, SpringLayout.SOUTH, destinyLabel);
		 
         layoutUsers.putConstraint(SpringLayout.WEST, exitButton, 10, SpringLayout.EAST, consultButton);
         layoutUsers.putConstraint(SpringLayout.NORTH, exitButton, 20, SpringLayout.SOUTH, destinyLabel);
		 
         userPanel.add(infoArea);
		 userPanel.add(originLabel);
		 userPanel.add(originStation);
		 userPanel.add(destinyLabel);
		 userPanel.add(endStation);
		 userPanel.add(consultButton);
		 userPanel.add(exitButton);
		 
		 //stuff panel
		 SpringLayout stuffLayout = new SpringLayout();
		 stuffPanel = new JPanel();
		 stuffPanel.setLayout(stuffLayout);
		 stuffPanel.setBackground(Color.BLUE);
		 addStationButton = new JButton("Nueva estacion");
		 addConectionButton = new JButton("Nueva conexion");
		 deleteConectionButton = new JButton("eliminar estacion");
		 isolateStationButton = new JButton("Aislar estacion");
	     deleteStationButton= new JButton("Eliminar estacion");
	     addConectionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 JLabel imageStuffpanel = new JLabel(image);
		 
		 stuffLayout.putConstraint(SpringLayout.WEST, imageStuffpanel, 140,SpringLayout.WEST, container);
		 stuffLayout.putConstraint(SpringLayout.SOUTH, imageStuffpanel, Height,SpringLayout.SOUTH, container);
	     stuffPanel.add(imageStuffpanel);
	     stuffLayout.putConstraint(SpringLayout.EAST, addStationButton, (Width/4),SpringLayout.WEST, container);
	     stuffLayout.putConstraint(SpringLayout.NORTH, addStationButton, 20,SpringLayout.NORTH, container);
	     stuffLayout.putConstraint(SpringLayout.NORTH, addConectionButton, 50,SpringLayout.NORTH, addStationButton);
	     stuffLayout.putConstraint(SpringLayout.WEST, addConectionButton, 0,SpringLayout.WEST, addStationButton);
	     stuffLayout.putConstraint(SpringLayout.EAST, addConectionButton, 10,SpringLayout.EAST, addStationButton);
	    d
		 
		 stuffPanel.add(addStationButton);
		 stuffPanel.add(addConectionButton);
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 container.add(initialPanel,"0");
		 container.add(userPanel,"1");
		 container.add(stuffPanel,"2");
		 clLayout.show(container,"0");
		 
		 this.getContentPane().add(container);
		 
	 }
	 
	 private void registerListeners() {
		 
		 userButton.addActionListener(new ConsultAction());
		 stuffButton.addActionListener(new AutorizedAction());
		 exitButton.addActionListener(new GoBackAction());
		 security.getConfirm().addActionListener(new PasswordActionListener());
		 security.getExit().addActionListener(new PasswordActionListener());
		 
	 }
	 
	 
	 /**
	  * Inner class actionListener
	  * @author Aitor
	  *
	  */
	 class GoBackAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				clLayout.previous(container);
				
			}
			 
		 }
	 
	 
	 
	 
	 /**
	  * Inner class actionListener
	  * @author Aitor
	  *
	  */
	 class ConsultAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				clLayout.show(container,"1");
				
			}
			 
		 }
	 /**
	  * Inner class actionListener
	  * @author Aitor
	  *
	  */
	 class PasswordActionListener implements ActionListener{
		 
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 
			 
			 if( e.getSource() == security.getConfirm()) {
				boolean valid = false;
				char[]pass = security.getpassField();
				for(int i = 0;i < pass.length;++i) {
				
				     if( security.getAutrizedPassword().charAt(i)!= pass[i]) {
				          valid = false;
				          break;
				     }
				
				     valid = true;
				}
				
			  if( valid) {
			
				JOptionPane.showMessageDialog(security, "Bienvenido!! puede continuar", "Metro management", JOptionPane.INFORMATION_MESSAGE);
				security.dispose();
				clLayout.show(container, "2");
				security.reset();
				 
			  }else {
					
					JOptionPane.showMessageDialog(security, "EL password introducido no es correcto", "Error", JOptionPane.ERROR_MESSAGE);
					security.reset();
					valid = false;
			   }
		 
			 }else {
				
				 security.dispose();
			     security.reset();
			 }
		 }
	 }
	 /**
	  * Inner class ActionListener
	  * @author Aitor
	  *
	  */
	 class AutorizedAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			security.setVisible(true);	
		
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
