package aec2.metro.implementacion.undergroundapp;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JTextArea;
import aec2.metro.implementacion.Estacion;
import aec2.metro.implementacion.MetroMadrid;
import aec2.metro.implementacion.TuplaCaminoValor;


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
	private ConnectionWindow Wconnection;
	private DeleteConnectionWindow WdeleteConnection;
	private DeleteStationWindow WdeleteStation;
    private DeleteStationWindow WisolateStation;
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
	//actionlistener for creation of the a new station
	private AddStationListener creationListener;
	//Stations data,
	String[] stations;
	//Constructor
	 public UndergroundSystem(String name) {
		 this.setTitle(name);
		 listen = new StuffPanelListener();
		 creationListener = new AddStationListener();
		 WnewStation = new NewStationWindow("Nueva Estacion");
		 metro = Loader.getMetroData();
		 stations = metro.getStations();
		 WisolateStation = new DeleteStationWindow(stations,"Aislar Estacion");
		 Wconnection = new ConnectionWindow(stations,"Nueva conexion");
		 WdeleteConnection= new DeleteConnectionWindow(stations,"Eliminar conexion");
		 WdeleteStation = new DeleteStationWindow(stations,"Eliminar Estacion");
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
		 initialPanel.setBackground(new Color(109,168,196));
		 SpringLayout springLayout = new SpringLayout();
		 initialPanel.setLayout(springLayout);
		 userButton =new  JButton("Realizar Consulta");
		 userButton.setBackground(new Color(170,206,226));
		 userButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		 stuffButton = new JButton("Personal autorizado");
		 stuffButton.setBackground(new Color(170,206,226));
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
		 userPanel.setBackground(new Color(101,168,196));
		 
		 infoArea = new JTextArea();
		 infoArea.setEditable(false);
		 
		 consultButton= new JButton("Consultar");
		 consultButton.setBackground(new Color(170,206,226));
		
		 exitButton = new JButton("Salir");
		 exitButton.setBackground(new Color(170,206,226));
		 
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
			 imageGoback = new ImageIcon(getClass().getResource("../../../resources/back.png"));
 
		 }catch(Exception e) {
			 System.out.println("no se ha podido cargar la imagen");
		 }
		 
		 SpringLayout stuffLayout = new SpringLayout();
		 stuffPanel = new JPanel();
		 stuffPanel.setLayout(stuffLayout);
		 stuffPanel.setBackground(new Color(101,168,196));
		
		 addStationButton = new JButton("Nueva estacion",imageAddStation);
		 addStationButton.setBackground(new Color(170,206,226));
	
		 addConnectionButton = new JButton("Nueva conexion",imageAddConection);
		 addConnectionButton.setBackground(new Color(170,206,226));
	
		 deleteConnectionButton = new JButton("Eliminar conexion",imageRemoveStation);
		 deleteConnectionButton.setBackground(new Color(170,206,226));
		 
		 deleteStationButton= new JButton("Eliminar estacion",imageRemoveConnection);
		 deleteStationButton.setBackground(new Color(170,206,226));
		 
		 isolateStationButton = new JButton("Aislar estacion",imageIsolateStation);
		 isolateStationButton.setBackground(new Color(170,206,226));
		 
		 goBackButton = new JButton("Volver",imageGoback);
		 goBackButton.setBackground(new Color(170,206,226));
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
		 
		 this.addWindowListener(new SaveOnCloseListener());
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
		 
		 //add new station window buttons listeners
		 WnewStation.getConfirmButton().addActionListener(creationListener);
		 WnewStation.getClearButton().addActionListener(creationListener);
		 //add new conection window buttons listeners
		 Wconnection.getConfirmationButton().addActionListener(new ConnectionListener());
		 //add delete connections window buttons listener
		 WdeleteConnection.getConfirmationButton().addActionListener(new DelConnectionListener());
		 //add delete station windows buttons listeners
		 WdeleteStation.getConfirmationButton().addActionListener(new DelStationListener());
		 //add isolate station windows button listener
		 WisolateStation.getConfirmationButton().addActionListener(new IsolateStationListener());
		 
		 //consult button listener
		 consultButton.addActionListener(new MakeConsult());
	 }
	 
	 
	 
	 /**
	  * Inner class action listner for the  consult button
	  * @author Aitor
	  *
	  */
	 private class MakeConsult implements ActionListener{
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 String ostring=(String) originStation.getSelectedItem();
		     String dstring=(String)endStation.getSelectedItem();
		    		 
		     if(ostring != null && dstring != null) {
		     
		    	 Estacion origen = new Estacion(ostring);
		    	 Estacion destino = new Estacion(dstring);
		  
		    	 TuplaCaminoValor tuplaCaminoMenosEstaciones = metro.caminoMenosEstaciones(origen, destino);
		    	 TuplaCaminoValor tuplaCaminoMasRapido = metro.caminoMasRapido(origen, destino);
		    	 infoArea.setText(	String.format(
				"Estimado usuario,\n" + "El camino mas rapido para ir de la estacion %s a la estacion %s "
						+ "tiene una duracion de %s minutos y el camino a seguir es %s.\nSi "
						+ "por el contrario desea conocer el camino con menos estaciones "
						+ "debe saber que el camino a seguir es %s y usted pasara solamente por %s estaciones.\n"
						+ "Reciba un cordial saludo.\n" + "Metro de Madrid.\n",origen, destino, tuplaCaminoMasRapido.getValor(), 
						tuplaCaminoMasRapido.getCamino(),tuplaCaminoMenosEstaciones.getCamino(), tuplaCaminoMenosEstaciones.getValor()));
		     }else {
		    	 JOptionPane.showMessageDialog(WdeleteConnection, "Lo sentimos, pero no se existen Estaciones disponibles para consultar\n"
		    	 		+ "Reciba un cordial saludo.\nMetro de Madrid!.", "Error", JOptionPane.ERROR_MESSAGE);
		     }
		 }
	 }
	 
	 
	 /**
	  * Inner class for the isolate station window
	  * @author Aitor
	  *
	  */
	 private class IsolateStationListener implements ActionListener{
		 
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 
			 Estacion st =WisolateStation.getOriginStation();
			 if( st.getNombre() != null) {
				 
				 metro.aislarEstacion(st);
				 JOptionPane.showMessageDialog(WdeleteConnection, "La estacion:"+st.getNombre()
                 +" ha sido aislada con éxito!", "Info", JOptionPane.INFORMATION_MESSAGE);
			 }else {
				 JOptionPane.showMessageDialog(WdeleteConnection, "No se existen Estaciones", "Error", JOptionPane.ERROR_MESSAGE);
			 }
			 
			 
		 }
	 }
	 
	 
	 
	 /**
	  * Inner class for delete Station window
	  * @author egevi
	  *
	  */
	 private class DelStationListener implements ActionListener{
		 
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 
			 Estacion sto =  WdeleteConnection.getOriginStation();
			 
			 if( sto.getNombre() !=  null ) {
				 
				 metro.eliminarEstacion(sto);
				deleteItem(sto.getNombre());
				JOptionPane.showMessageDialog(WdeleteConnection, "La estacion:"+sto.getNombre()
				                              +" ha sido eliminada con éxito!", "Info", JOptionPane.INFORMATION_MESSAGE);
			 }else {
				 JOptionPane.showMessageDialog(WdeleteConnection, "No se existen Estaciones", "Error", JOptionPane.ERROR_MESSAGE);
			 }
		 }
		 
		 
		 
	 }
	 
	 
	 
	 
	 /**
	  * Inner listener for the delete connections window
	  * @author Aitor
	  */
	 private class DelConnectionListener implements ActionListener{
		 
		 @Override
		 public void actionPerformed(ActionEvent e) {
			  Estacion sto =  WdeleteConnection.getOriginStation();
		      Estacion std= WdeleteConnection.getDestinyConection();
			  
		      if( sto.getNombre() !=  null  || std.getNombre() !=  null) {
				  metro.eliminarConexion(sto,std);
			  }else {
				  JOptionPane.showMessageDialog(WdeleteConnection, "No se existe conexion", "Error", JOptionPane.ERROR_MESSAGE);
			  }
		 
		 }
	 }
	 
	 /**
	  * Inner action listener for the new connection window
	  * @author Aitor
	  *
	  */
	 class ConnectionListener implements ActionListener{
		 
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 
			 Estacion originSName = Wconnection.getOriginStation();
			 Estacion destinySName = Wconnection.getDestinyConection();
			 if(originSName.getNombre() != null || destinySName.getNombre()!=null) {
				 
				 int time = Wconnection.getDistanceBetween();
				 metro.anadirConexion(originSName, destinySName, time);
				 
				 JOptionPane.showMessageDialog(Wconnection, "Conexion establecida con exito entre: ["
						 + originSName.getNombre()
						 + "]--->["
						 + destinySName.getNombre()+"] tiempo:"
						 + time, "Informacion", JOptionPane.INFORMATION_MESSAGE);
			 }else {
				 JOptionPane.showMessageDialog(Wconnection, "No es posible conectar la estacion", "Error", JOptionPane.ERROR_MESSAGE);
			 }

		 }
	 }
	 
	 /**
	  * 
	  * @author Aitor
	  *
	  */
	 class SaveOnCloseListener extends WindowAdapter{

		@Override
		public void windowClosing(WindowEvent e) {

			Loader.saveUndergroundData(metro);
		}

		@Override
		public void windowClosed(WindowEvent e) {
			
			Loader.saveUndergroundData(metro);
			
		}
		
	 }
	 
	 
	 
	 /**
	  * Inner class action listener for the new station creation window
	  * @author Aitor
	  *
	  */
	class AddStationListener implements ActionListener{
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if( e.getSource()== WnewStation.getConfirmButton()) {
	
			   String sName=WnewStation.getnewStationName();
				if( sName != null) {
	                 
					Estacion newStation = new Estacion(sName);
					if(metro.yaExiste(newStation)) {

						JOptionPane.showMessageDialog(WnewStation, "La estacion: "+sName+" ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
						
					}else {
						
						metro.anadirEstacion(new Estacion(sName));
						upDateAllInformation(sName);
						JOptionPane.showMessageDialog(WnewStation, "La estacion: "+sName+" ha sido añadida con éxito", "Succed", JOptionPane.INFORMATION_MESSAGE);
					}
					WnewStation.reset();
				}else {
					JOptionPane.showMessageDialog(WnewStation, "El campo nombre no puede estar vacio", "Warning", JOptionPane.INFORMATION_MESSAGE);
				}
							
				
			}	
			if( e.getSource()== WnewStation.getClearButton()) {
					WnewStation.reset();
			}
			
			
		}
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
				 WdeleteStation.setVisible(true);
			 }
			 
			 if( e.getSource() ==goBackButton ) {
				 clLayout.show(container, "0");
				 infoArea.setText("");
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
	 
	 
 
	 /**
	  * Update the information on the GUI
	  * @param sName
	  */
	 private void upDateAllInformation(String sName) {
		 originStation.addItem(sName);
		 endStation.addItem(sName);
		 Wconnection.updateComboBoxes(sName);
		 WisolateStation.updateComboBoxes(sName);
		 WdeleteConnection.updateComboBoxes(sName);
	     WdeleteStation.updateComboBoxes(sName);
			
	 }
	 /**
	  * Remove deleted station from the list of posibilities showed on the GUI
	  * @param sname
	  */
	 private void deleteItem(String sname) {
		 originStation.removeItem(sname);
		 endStation.removeItem(sname);
		 Wconnection.removeItemComboBoxes(sname);
		 WisolateStation.removeItemComboBoxes(sname);
		 WdeleteConnection.removeItemComboBoxes(sname);
	     WdeleteStation.removeItemComboBoxes(sname);
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
