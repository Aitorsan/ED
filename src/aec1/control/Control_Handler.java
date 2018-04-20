package aec1.control;
/*Java core imports*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/*project imports*/
import aec1.vista.AppWindow;
import aec1.vista.Register_Window;
import aec1.implementacion.CineUdima;
import aec1.implementacion.Cliente;
import aec1.vista.AuxiliarWindow;
import aec1.vista.MoveWindow;

public class Control_Handler {

	/*main aplication window*/
	private AppWindow window;
	private Register_Window  registerWindow;
	private AuxiliarWindow deleteWindow;
	private MoveWindow moveWindow;
	/*Buttons*/
	private JButton[] buttons;
	/*Listeners*/
	private ButtonListener b_listener;
	/*cine Udima*/
	private CineUdima cine;

	/**Constructor **/
	public Control_Handler() {
		b_listener = new ButtonListener();
		window = new AppWindow();
		registerWindow = new Register_Window();
		deleteWindow = new AuxiliarWindow("Eliminar","Eliminar");
		moveWindow = new MoveWindow("Mover","Mover");
		buttons = window.getButtonsToolBar();
		cine = new CineUdima();
		addListeners();
	}

	/**
	 * Funcion que registra los listeners en ls diferentes elementos
	 */
	private void addListeners() {
		// main window buttons listener
		for( int i = 0; i < buttons.length;++i) {
			buttons[i].addActionListener(b_listener);
		}
		window.getAbandonar_cola_boton_zonaProyeccion().addActionListener(b_listener);
		window.getAbandonar_cola_zonaEntrada_boton().addActionListener(b_listener);
		//Register window buttons listener
		registerWindow.getAceptar().addActionListener(b_listener);
		registerWindow.getCancelar().addActionListener(b_listener);
		//Delete window buttons listener
		deleteWindow.getConfirmationButton().addActionListener(b_listener);
		deleteWindow.getCancelButton().addActionListener(b_listener);
		//move window buttons listener
		moveWindow.getConfirmationButton().addActionListener(b_listener);
	}


	/**
	 * class buttonListener representa el escuchador de los botones de la aplicacion
	 * @author aitorSf
	 *
	 */
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {


			
            //***********************************************************
			//             Main Window JButtons ( AppWindow )
			//************************************************************/
			if( event.getSource() == buttons[AppWindow.ADD_BUTTON]) {

				registerWindow.setVisible(true);


			}

			if( event.getSource() == buttons[AppWindow.ABANDONAR_COLA_BUTTON] 
					|| event.getSource() == window.getAbandonar_cola_boton_zonaProyeccion()
					|| event.getSource() == window.getAbandonar_cola_zonaEntrada_boton()) {
                 moveWindow.setVisible(true);
			}

			if( event.getSource() == buttons[AppWindow.REMOVE_BUTTON]) {

				deleteWindow.setVisible(true);

			}

			if( event.getSource() == buttons[AppWindow.ZONA_ENTRADA]) {
				window.changeArea(AppWindow.ZONA_ENTRADA_VISIBLE);
			    window.actualizarInformacion(cine,CineUdima.ALL);

			}

			if( event.getSource() == buttons[AppWindow.ZONA_PROYECCION]) {
				window.changeArea(AppWindow.ZONA_PROYECCION_VISIBLE);
				window.actualizarInformacion(cine,CineUdima.ALL);

			}
			
			if( event.getSource() == buttons[AppWindow.ABANDONAR_COLA_BUTTON]) {
				
			}
			
			

            //***********************************************************
			//               RegisterWindow JButtons
			//************************************************************/

			if( event.getSource() == registerWindow.getAceptar()) {

				String info ="";

				try {

					if(registerWindow.getNombreYApellidosTextField().getText().isEmpty()) {
						throw new Exception("El campo nombre y apellidos estan vacios");
					}
					StringTokenizer separaNombreYApellidos  = new StringTokenizer(registerWindow.getNombreYApellidosTextField().getText());

					info += separaNombreYApellidos.nextToken();
					info+= ":";
					if( separaNombreYApellidos.hasMoreTokens()) {
						info+=separaNombreYApellidos.nextToken();
						info+= ":";
						if( separaNombreYApellidos.hasMoreTokens()) {
							info+=separaNombreYApellidos.nextToken();
						}else {
							throw new Exception("Es necesario introudcir el segundo apellido del cliente ( si no tiene indicar escribir null)");
						}
					}else {
						throw new Exception("Es necesario introudcir los apellidos del cliente");
					}
					info+= ":";
					info+= registerWindow.getEdad();
					info+=":";
					info+=registerWindow.getSexo();
					info+=":";
					info+=registerWindow.getPrioridad();

					//crear el nuevo cliente
					StringTokenizer token = new StringTokenizer(info,":");

					Cliente nuevo = new Cliente();

					nuevo.setNombre(token.nextToken().toLowerCase());
					nuevo.setPrimerApellido(token.nextToken().toLowerCase());
					nuevo.setSegundoApellido(token.nextToken().toLowerCase());
					nuevo.setEdad(Integer.valueOf(token.nextToken()));
					nuevo.setSexo(token.nextToken());
					nuevo.setPrioridad(token.nextToken());

					if(cine.buscarClienteEnCine(nuevo)) {
						JOptionPane.showMessageDialog(registerWindow, "El cliente ya esta registrado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
					}else {
						cine.registarCliente(nuevo);
						JOptionPane.showMessageDialog(registerWindow, "El cliente ha sido registrado correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						registerWindow.resetFields();
						
					   window.actualizarInformacion( cine, CineUdima.ENTRADA);
			
					}


				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(registerWindow, "El campo Edad esta vacio", "Informacion", JOptionPane.INFORMATION_MESSAGE);

				}catch(Exception e) {
					JOptionPane.showMessageDialog(registerWindow, e.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);

				}

			}


			if(event.getSource() == registerWindow.getCancelar()) {

				registerWindow.resetFields();
			}

             //***********************************************************
			//               DeleteWindow JButtons
			//************************************************************/
			if( event.getSource() == deleteWindow.getConfirmationButton()) {
               try {
            		if(cine.deleteClient(deleteWindow.getTextNombre())) {
    					JOptionPane.showMessageDialog(registerWindow, "El cliente ha sido eliminado correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
    					window.actualizarInformacion( cine, 
    					CineUdima.ENTRADA|CineUdima.COMERCIO|CineUdima.CONTROL|CineUdima.CONTROL_P|CineUdima.TAQUILLA_UNO|CineUdima.TAQUILLA_DOS);
    				}else {
    					JOptionPane.showMessageDialog(registerWindow, "El cliente no puede salir si no esta en la zona de entrada o en la cola de salida", "Informacion", JOptionPane.INFORMATION_MESSAGE);
    				}
                    
               }catch(NoSuchElementException e) {
            	
            	   JOptionPane.showMessageDialog(registerWindow, "El campo esta vacio o la informaciones incompleta", "Informacion", JOptionPane.INFORMATION_MESSAGE);
               }
			
				deleteWindow.resetTextFields();
			}
			if( event.getSource() == deleteWindow.getCancelButton()) {
				deleteWindow.resetTextFields();
			}

            //***********************************************************
			//            Move client window JButtons
			//************************************************************/
			if( event.getSource() == moveWindow.getConfirmationButton()) {
				

				try {
					
					Cliente client = null;
					//check whether the client is in the cinema or not
					// and get the client name and surname
					client= cine.buscarClienteEnCine(moveWindow.getTextNombre()); 
					if( client != null){
						//check whether the client is on the same list where it wants to go
					   // move it to the selected place
						cine.move(client,moveWindow.getSelectedItem());
				
						///reset the fields
						window.resetTextFields();
					
					}else {
						throw new Exception ("El cliente no esta registrado en la base de datos");
					}
				}catch(NoSuchElementException e) {
					 JOptionPane.showMessageDialog(registerWindow, "El campo esta vacio o la informaciones incompleta", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e) {
					 JOptionPane.showMessageDialog(registerWindow, e.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
					 window.actualizarInformacion(cine, CineUdima.ALL);
				}finally {
					//update the information on the screen
					window.actualizarInformacion(cine, CineUdima.ALL);
				}
				
			}
			if( event.getSource() == moveWindow.getCancelButton()) {
				moveWindow.resetTextFields();
			}

		 
			
			
			
			
			
			
			
			
			
			
			
			
			
		
		
		}//method end


	}//End of nested class actionListener

	
	//End of Control_handler class
}
