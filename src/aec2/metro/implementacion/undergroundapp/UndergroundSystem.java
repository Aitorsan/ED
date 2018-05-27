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
	private ImageIcon imageAddStation;
	private ImageIcon imageAddConection ;
	private ImageIcon imageRemoveStation ;
	private ImageIcon imageRemoveConnection; 
	private ImageIcon imageIsolateStation ;
	private ImageIcon imageGoback;
	//Layout
	CardLayout clLayout;
	//Underground graf
	private MetroMadrid metro;
	//private frames ;
	private SecurityManager security;
	private NewStationWindow WnewStation;
    private NewStationWindow WisolateStation;
    private ConnectionWindow Wconnection;
    private DeleteConnectionWindow WdeleteConnection;
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
	JButton addConnectionButton;
	JButton deleteConnectionButton;
	JButton isolateStationButton;
	JButton deleteStationButton;
	JButton goBackButton;
	//actionlistener for the employees/stuff panel
	private StuffPanelListener listen;
	
	//Constructor
	 public UndergroundSystem(String name) {
		 this.setTitle(name);
		 WnewStation = new NewStationWindow("Nueva Estacion");
		 WisolateStation = new NewStationWindow("Aislar Estacion");
		 listen = new StuffPanelListener();
		 metro = Loader.getMetroData();
		 Wconnection = new ConnectionWindow(metro.getStations(),"Nueva conexion");
		 WdeleteConnection= new DeleteConnectionWindow(metro.getStations(),"Eliminar conexion");
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
	 
	 /**
	  * Method to initialize all the components
	  */
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
		 
		 try {
			 imageAddStation = new ImageIcon(getClass().getResource("../../../resources/station.png"));
			 imageAddConection = new ImageIcon(getClass().getResource("../../../resources/conexion.png"));
			 imageRemoveStation = new ImageIcon(getClass().getResource("../../../resources/cross.png"));
			 imageRemoveConnection = new ImageIcon(getClass().getResource("../../../resources/cross.png"));
			 imageIsolateStation = new ImageIcon(getClass().getResource("../../../resources/isolate.png"));
			 imageGoback = new ImageIcon(getClass().getResource("../../../resources/Metro.pn"));
			
			 
			 
		 }catch(Exception e) {
			 System.out.println("no se ha podido cargar la imagen");
		 }
		 
		 SpringLayout stuffLayout = new SpringLayout();
		 stuffPanel = new JPanel();
		 stuffPanel.setLayout(stuffLayout);
		 stuffPanel.setBackground(new Color(50,100,90));
		 addStationButton = new JButton("Nueva estacion",imageAddStation);
		 addConnectionButton = new JButton("Nueva conexion",imageAddConection);
		 deleteConnectionButton = new JButton("Eliminar conexion",imageRemoveStation);
		 deleteStationButton= new JButton("Eliminar estacion",imageRemoveConnection);
		 isolateStationButton = new JButton("Aislar estacion",imageIsolateStation);
		 goBackButton = new JButton("Volver",imageGoback);
	     //image layout
	     JLabel imageStuffpanel = new JLabel(image);
		 
		 stuffLayout.putConstraint(SpringLayout.WEST, imageStuffpanel, Width/3,SpringLayout.WEST, container);
		 stuffLayout.putConstraint(SpringLayout.SOUTH, imageStuffpanel, Height,SpringLayout.SOUTH, container);
	     //add new station button to the graph layout 
	     stuffLayout.putConstraint(SpringLayout.EAST, addStationButton, (Width/3),SpringLayout.WEST, container);
	     stuffLayout.putConstraint(SpringLayout.NORTH, addStationButton, 20,SpringLayout.NORTH, container);
	     stuffLayout.putConstraint(SpringLayout.WEST, addStationButton, 20, SpringLayout.WEST, container);
	     //add connection button layout
	     stuffLayout.putConstraint(SpringLayout.NORTH, addConnectionButton, 20,SpringLayout.SOUTH, addStationButton);
	     stuffLayout.putConstraint(SpringLayout.WEST, addConnectionButton, 0,SpringLayout.WEST, addStationButton);
	     stuffLayout.putConstraint(SpringLayout.EAST, addConnectionButton, 0,SpringLayout.EAST, addStationButton);
	     //delete connection button layout
	     stuffLayout.putConstraint(SpringLayout.EAST,deleteConnectionButton,0,SpringLayout.EAST,addConnectionButton);
		 stuffLayout.putConstraint(SpringLayout.NORTH, deleteConnectionButton, 20, SpringLayout.SOUTH, addConnectionButton);
		 stuffLayout.putConstraint(SpringLayout.WEST, deleteConnectionButton, 0, SpringLayout.WEST,addConnectionButton);
		 
		 //delete station layout
         stuffLayout.putConstraint(SpringLayout.NORTH,deleteStationButton, 20,SpringLayout.SOUTH, deleteConnectionButton);
		 stuffLayout.putConstraint(SpringLayout.EAST, deleteStationButton, 0, SpringLayout.EAST, deleteConnectionButton);
		 stuffLayout.putConstraint(SpringLayout.WEST, deleteStationButton, 0, SpringLayout.WEST, deleteConnectionButton);
		 //isolate station layout
		 stuffLayout.putConstraint(SpringLayout.NORTH,isolateStationButton, 20,SpringLayout.SOUTH, deleteStationButton);
		 stuffLayout.putConstraint(SpringLayout.EAST, isolateStationButton, 0, SpringLayout.EAST, deleteStationButton);
		 stuffLayout.putConstraint(SpringLayout.WEST, isolateStationButton, 0, SpringLayout.WEST, deleteStationButton);
		 
		 //go back button layout
		 stuffLayout.putConstraint(SpringLayout.EAST,goBackButton, 0,SpringLayout.EAST,stuffPanel);
		 stuffLayout.putConstraint(SpringLayout.WEST,goBackButton, -Width/4,SpringLayout.EAST,stuffPanel);
		 stuffLayout.putConstraint(SpringLayout.SOUTH, goBackButton, 10, SpringLayout.SOUTH, stuffPanel);
		 stuffLayout.putConstraint(SpringLayout.NORTH, goBackButton, -50, SpringLayout.SOUTH, stuffPanel);
		 stuffPanel.add(addStationButton);
		 stuffPanel.add(addConnectionButton);
		 stuffPanel.add(deleteConnectionButton);
		 stuffPanel.add(deleteStationButton);
		 stuffPanel.add(isolateStationButton);
		 stuffPanel.add(goBackButton);
		 stuffPanel.add(imageStuffpanel);
		 
		 
			 
		 container.add(initialPanel,"0");
		 container.add(userPanel,"1");
		 container.add(stuffPanel,"2");
		 clLayout.show(container,"0");
		 
		 this.getContentPane().add(container);
		 
	 }
	 
	 private void registerListeners() {
		 //initial panel buttons
		 userButton.addActionListener(new ConsultAction());
		 stuffButton.addActionListener(new AutorizedAction());
		 //consults panel buttons
		 exitButton.addActionListener(new GoBackAction());
		 //security password confirmation buttons
		 security.getConfirm().addActionListener(new PasswordActionListener());
		 security.getExit().addActionListener(new PasswordActionListener());
		 
		 //autorized employees panel
		 addStationButton.addActionListener(listen);
		 addConnectionButton.addActionListener(listen);	 
		 deleteConnectionButton.addActionListener(listen);
		 isolateStationButton.addActionListener(listen); 
		 deleteStationButton.addActionListener(listen);
		 goBackButton.addActionListener(listen);
		 
	 }
	 /**
	  * Inner class actionListener for employees
	  * @author Aitor
	  *
	  */
	 class StuffPanelListener implements ActionListener{
		 
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 
			
			 if( e.getSource() == addStationButton) {
				 WnewStation.setVisible(true);
			 }
			 
			 if( e.getSource() == addConnectionButton) {
			     Wconnection.setVisible(true);
			 }
			 
			 if( e.getSource() == deleteConnectionButton ) {
				 WdeleteConnection.setVisible(true);
			 }
			 
			 if( e.getSource() == isolateStationButton) {
				 WisolateStation.setVisible(true);
				 
			 }
			 
			 if( e.getSource() == deleteStationButton ) {
				 
			 }
			 
			 if( e.getSource() ==goBackButton ) {
				 clLayout.show(container, "0");
			 }
			 
			 
			 
			 
			 
			 
		 }
		 
		 
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
