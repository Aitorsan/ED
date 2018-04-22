package aec1.implementacion.control;
/*Java core imports*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import colas.Cola;
import excepciones.DesbordamientoInferior;
import pilas.Pila;
import aec1.implementacion.CineUdima;
import aec1.implementacion.Cliente;
import aec1.implementacion.vista.AppWindow;
import aec1.implementacion.vista.AuxiliarWindow;
import aec1.implementacion.vista.MoveWindow;
import aec1.implementacion.vista.Register_Window;

/**
 * CLass Control_Handler, this class is the intermediary between the view and the actual application<br>
 * Handles all the relationships between the window and the program.It is responsable for all the events<br>
 * happened during the execution of the program.
 * @author Aitor Sanmartin Ferreira
 *
 */
public class Control_Handler {

	/*main application window*/
	private AppWindow window;
	private Register_Window  registerWindow;
	private AuxiliarWindow deleteWindow;
	private MoveWindow moveWindow_entrada;
	private MoveWindow moveWindow_proyeccion;
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
		moveWindow_entrada = new MoveWindow("Mover","Mover",MoveWindow.ENTRADA);
		moveWindow_proyeccion = new MoveWindow("Mover","Mover",MoveWindow.PROYECCION);
		buttons = window.getButtonsToolBar();
		cine = new CineUdima();
		addListeners();
	}

	/**
	 * Method that add listener to the elements
	 */
	private void addListeners() {
		// main window buttons listener
		for( int i = 0; i < buttons.length;++i) {
			buttons[i].addActionListener(b_listener);
		}
		window.getAbandonar_cola_boton_zonaProyeccion().addActionListener(b_listener);
		window.getAbandonar_cola_zonaEntrada_boton().addActionListener(b_listener);
		window.getSiguiente_taquilla_uno_boton().addActionListener(b_listener);
		window.getSiguiente_taquilla_dos_boton().addActionListener(b_listener);
		window.getSiguiente_control_boton().addActionListener(b_listener);
		window.getSiguiente_comercio_boton().addActionListener(b_listener);
		window.getSiguiente_aseo_H_boton().addActionListener(b_listener);
		window.getSiguiente_aseo_M_boton().addActionListener(b_listener);
		window.getDesapilar_sala_boton().addActionListener(b_listener);
		window.getSiguiente_salida_boton().addActionListener(b_listener);
		//Register window buttons listener
		registerWindow.getAceptar().addActionListener(b_listener);
		registerWindow.getCancelar().addActionListener(b_listener);
		//Delete window buttons listener
		deleteWindow.getConfirmationButton().addActionListener(b_listener);
		deleteWindow.getCancelButton().addActionListener(b_listener);
		//move window buttons entry area listener
		moveWindow_entrada.getConfirmationButton().addActionListener(b_listener);
		//move window buttons projection area listeners
		moveWindow_proyeccion.getConfirmationButton().addActionListener(b_listener);
	}


	/**
	 * class buttonListener,is the listener for all the buttons in the application
	 * @author aitorSf
	 *
	 */
	class ButtonListener implements ActionListener{

		/**
		 * Action peroformed method
		 */
		@Override
		public void actionPerformed(ActionEvent event) {

			//************************************************************
			//             Main Window JButtons ( AppWindow )
			//************************************************************/
			if( event.getSource() == buttons[AppWindow.ADD_BUTTON]) {
				registerWindow.setVisible(true);

			}

			if( event.getSource() == buttons[AppWindow.ABANDONAR_COLA_BUTTON] 

					|| event.getSource() == window.getAbandonar_cola_zonaEntrada_boton()) {
				moveWindow_entrada.setVisible(true);
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


			//next taquilla uno and next taquilla dos
			if( event.getSource() == window.getSiguiente_taquilla_uno_boton() || event.getSource() == window.getSiguiente_taquilla_dos_boton()) {

				Cola c = null;
				Cliente client = null;
				if(event.getSource() == window.getSiguiente_taquilla_uno_boton())
					c = cine.getTaquillas_ventanilla_uno();
				if(event.getSource() == window.getSiguiente_taquilla_dos_boton())
					c = cine.getTaquillas_ventanilla_dos();

				try {
					if( !c.esVacia()) {

						client =(Cliente) c.primero();
						client.comprarEntrada();

						JOptionPane.showMessageDialog(window, "El cliente: "+ client.getNombre()+" "+client.getPrimerApellido()+" ha comprado una entrada con éxito");					
						c.quitarPrimero();
						cine.move(client, "Lista Entrada");

					}else {
						JOptionPane.showMessageDialog(window, "la cola esta vacia");
					}
				}catch(DesbordamientoInferior e) {
					JOptionPane.showMessageDialog(window, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}catch(Exception e_2) {
					JOptionPane.showMessageDialog(window, e_2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				finally {
					window.actualizarInformacion(cine, CineUdima.ALL);	
				}
			}
			//Next control queue
			if( event.getSource() == window.getSiguiente_control_boton()) {

				// variables to perform the task
				Cola p_control = null;
				Cola control = null; 
				try {
					// first we look for people in the priority queue
					p_control = cine.getControl_prioritario();
					control = cine.getControl();
					if( !p_control.esVacia()) {

						Cliente c = (Cliente)p_control.primero();
						p_control.quitarPrimero();
						// second we check if the client already has a ticket
						// if the client do not have a ticket, then we move it to the entry list
						// if the client has a ticket we move it to the projection area of the Cinema and decrease the number of tickets.
						if( c.clienteTieneEntrada()) {
							c.gastarEntrada();
							cine.move(c, "Proyeccion");
							JOptionPane.showMessageDialog(window, "EL cliente ha accedido a la zona de Proyeccion");
						}else {
							cine.move(c,"Lista Entrada");
							JOptionPane.showMessageDialog(window, "EL cliente no tiene entrada");
						}

					}else if(!control.esVacia()) {

						// third we check for clients in the control queue
						// fourth we check again if the client already has a ticket
						// if the client do not have a ticket, then we move it to the entry list
						// if the client has a ticket we move it to the projection area of the Cinema and decrease the number of tickets.
						Cliente c = (Cliente)control.primero();
						control.quitarPrimero();
						if( c.clienteTieneEntrada()) {
							c.gastarEntrada();
							cine.move(c, "Proyeccion");
							JOptionPane.showMessageDialog(window, "EL cliente ha accedido a la zona de Proyeccion");
						}else {
							cine.move(c,"Lista Entrada");
							JOptionPane.showMessageDialog(window, "EL cliente no tiene entrada");
						}
					}else {
						JOptionPane.showMessageDialog(window, "No hay clientes en la cola");
					}

				}catch(DesbordamientoInferior e) {
					JOptionPane.showMessageDialog(window, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				} catch (Exception e_2) {
					JOptionPane.showMessageDialog(window, e_2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

				}finally {

					window.actualizarInformacion(cine, CineUdima.ALL);
				}		

			}


			//next comercio JButton
			if(event.getSource() == window.getSiguiente_comercio_boton()) {
				try {
					if(!cine.getComercio().esVacia()) {
						cine.move(((Cliente)(cine.getComercio().primero())),"Lista Entrada");
						cine.getComercio().quitarPrimero();
						JOptionPane.showMessageDialog(window, "EL cliente ha sido atendido en el comercio y ha comprado palomitas y refrescos");
					}else {
						JOptionPane.showMessageDialog(window, "No existen clientes que antender");
					}
				}catch(DesbordamientoInferior e) {
					JOptionPane.showMessageDialog(window,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);           
				}catch(Exception e_2) {
					JOptionPane.showMessageDialog(window,e_2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}finally {
					window.actualizarInformacion(cine, CineUdima.ALL);
				}
			}


			//--------------------
			// PROJECTION AREA
			//-------------------
			if( event.getSource() == window.getAbandonar_cola_boton_zonaProyeccion()){
				moveWindow_proyeccion.setVisible(true);

			}
			//next man toilets
			if( event.getSource() == window.getSiguiente_aseo_H_boton() ) {

				try {
					if(!cine.getAseo_h().esVacia()) {
						cine.move(((Cliente)(cine.getAseo_h().primero())),"Proyeccion");
						cine.getAseo_h().quitarPrimero();
						JOptionPane.showMessageDialog(window, "El cliente ha terminado en el aseo de caballeros");
					}else {
						JOptionPane.showMessageDialog(window, "No hay clientes en el aseo de caballeros");
					}
				}catch(DesbordamientoInferior e) {
					JOptionPane.showMessageDialog(window,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}catch(Exception e_2) {
					JOptionPane.showMessageDialog(window,e_2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}finally {
					window.actualizarInformacion(cine, CineUdima.ALL);
				}


			}

			//next women toilets
			if( event.getSource() ==window.getSiguiente_aseo_M_boton() ) {
				try {
					if(!cine.getAseo_m().esVacia()) {
						cine.move(((Cliente)(cine.getAseo_m().primero())),"Proyeccion");
						cine.getAseo_m().quitarPrimero();
						JOptionPane.showMessageDialog(window, "El cliente ha terminado en el aseo de mujeres");
					}else {
						JOptionPane.showMessageDialog(window, "No hay clientes en el aseo de mujeres");
					}
				}catch(DesbordamientoInferior e) {
					JOptionPane.showMessageDialog(window,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}catch(Exception e_2) {
					JOptionPane.showMessageDialog(window,e_2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}finally {
					window.actualizarInformacion(cine, CineUdima.ALL);
				}
			}


			//Salida JButton
			if( event.getSource() == window.getSiguiente_salida_boton() ) {
				String infoCliente = "";
				cine.getSalida().primero();
				if( cine.getSalida().estaDentro()) {
					infoCliente += ((Cliente)cine.getSalida().recuperar()).getNombre() +" ";
					infoCliente += ((Cliente)cine.getSalida().recuperar()).getPrimerApellido() +" ";
					infoCliente += ((Cliente)cine.getSalida().recuperar()).getSegundoApellido() +" ";
					cine.getSalida().eliminar(cine.getSalida().recuperar());
					JOptionPane.showMessageDialog(window, "El cliente: "+ infoCliente+ " se ha marchado del cine");
					window.actualizarInformacion(cine, CineUdima.ALL);
				}else {
					JOptionPane.showMessageDialog(window, "La cola de salida esta vacia");
				}

			}
			//Desapilar projection room JButton
			if( event.getSource() == window.getDesapilar_sala_boton() ) {


				if( !cine.getSala_proyeccion().esVacia()) {

					try {
						cine.getZonaProyeccion().insertar(cine.getSala_proyeccion().cima());
						cine.getSala_proyeccion().desapilar();
					}catch(DesbordamientoInferior e) {
						JOptionPane.showMessageDialog(window, e.getMessage(), "Error", JOptionPane.ERROR);
					}finally {
						window.actualizarInformacion(cine, CineUdima.ALL);
					}

				}else {
					JOptionPane.showMessageDialog(window, "La sala esta vacia");
				}

			}			
			//************************************************************
			//               move Window Projection area JButtons
			//************************************************************/

			if(event.getSource() == moveWindow_proyeccion.getCancelButton()) {
				moveWindow_proyeccion.resetTextFields();

			}

			//move jButton
			if(event.getSource() == moveWindow_proyeccion.getConfirmationButton()) {

				//check if the client is inside the projection room cause if its there the only way to go out is to move out the other clients

				Pila stack = null;
				Cliente cl = null;
				try{

					stack=cine.getSala_proyeccion();
					if(!stack.esVacia()) {

						if(cine.buscarCLienteEnSalaCine(moveWindow_proyeccion.getTextNombre())) {

							throw new Exception("El cliente no puede salir usando la forma convencional hay que desapilar la sala");    
						}

					}


					cl = cine.buscarClienteEnCine(moveWindow_proyeccion.getTextNombre());
					if(cl != null) {
						cine.move(cl,moveWindow_proyeccion.getSelectedItem() );	
					}else {
						JOptionPane.showMessageDialog(window, "El cliente no esta registrado en el cine");
					}


				}catch(Exception e) {
					JOptionPane.showMessageDialog(moveWindow_proyeccion,e.getMessage());
				}finally {
					window.actualizarInformacion(cine, CineUdima.ALL);
				}

			}


			//***********************************************************
			//               RegisterWindow JButtons
			//************************************************************/

			//if the confirmation button it's press we execute this statements
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
						JOptionPane.showMessageDialog(registerWindow, "El cliente puede : \n1.no estar registrado\n2.estar en la zona de proyeccion\nPor este motivo no se puede eliminar de esta forma", "Informacion", JOptionPane.INFORMATION_MESSAGE);
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
			//            Move client entry area window JButtons
			//************************************************************/
			if( event.getSource() == moveWindow_entrada.getConfirmationButton()) {


				try {

					Cliente client = null;
					//check whether the client is in the cinema or not
					// and get the client name and surname
					client= cine.buscarClienteEnCineDesdeEntrada(moveWindow_entrada.getTextNombre()); 
					if( client != null){
						//check whether the client is on the same list where it wants to go
						// move it to the selected place
						cine.move(client,moveWindow_entrada.getSelectedItem());

						///reset the fields
						window.resetTextFields();

					}else {
						throw new Exception ("El cliente no esta registrado en la base de datos o ha pasado a la zona de proyeccion");
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
			//Abort operation JButton reset the text fields of the move window on the Entry area
			if( event.getSource() == moveWindow_entrada.getCancelButton()) {
				moveWindow_entrada.resetTextFields();
			}

		}//method end

	}//End of nested class actionListener

	//End of Control_handler class
}
