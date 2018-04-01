package aec1.control;
/*Java core imports*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/*project imports*/
import aec1.vista.AppWindow;
import aec1.vista.Register_Window;
import aec1.implementacion.CineUdima;
import aec1.implementacion.Cliente;
import aec1.vista.DeleteCliente;

public class Control_Handler {

	/*main aplication window*/
	private AppWindow window;
	private Register_Window  registerWindow;
	private DeleteCliente deleteWindow;
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
		deleteWindow = new DeleteCliente();
		buttons = window.getButtonsToolBar();
		cine = new CineUdima();
		addListeners();
	}

	/**
	 * Funcion que registra los listeners en ls diferentes elementos
	 */
	private void addListeners() {
		for( int i = 0; i < buttons.length;++i) {
			buttons[i].addActionListener(b_listener);
		}
		registerWindow.getAceptar().addActionListener(b_listener);
		registerWindow.getCancelar().addActionListener(b_listener);
	}

	/**
	 * Este metodo recoge toda la informacion que proviene del registro
	 * de un nuevo cliente
	 * @param s
	 */

	public void infoClienteParaRegistrar(String s) {




		//cine.registarCliente(cliente);

	}


	/**
	 * class buttonListener representa el escuchador de los botones de la aplicacion
	 * @author aitorSf
	 *
	 */
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {


			if( event.getSource() == buttons[AppWindow.ADD_BUTTON]) {

				registerWindow.setVisible(true);


			}

			if( event.getSource() == buttons[AppWindow.MOVE_BUTTON]) {

			}

			if( event.getSource() == buttons[AppWindow.REMOVE_BUTTON]) {

				deleteWindow.setVisible(true);

			}

			if( event.getSource() == buttons[AppWindow.ZONA_ENTRADA]) {
				window.changeArea(AppWindow.ZONA_ENTRADA_VISIBLE);
				// Zona_Entrada zona.actualizarInfo() y lo pasamos como parametor

			}

			if( event.getSource() == buttons[AppWindow.ZONA_PROYECCION]) {
				window.changeArea(AppWindow.ZONA_PROYECCION_VISIBLE);
				// Zona_Entrada zona.actualizarInfo() y lo pasamos como parametor



			}

			//Botones de la ventana de registro

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

					nuevo.setNombre(token.nextToken());
					nuevo.setPrimerApellido(token.nextToken());
					nuevo.setSegundoApellido(token.nextToken());
					nuevo.setEdad(Integer.valueOf(token.nextToken()));
					nuevo.setSexo(token.nextToken());
					nuevo.setPrioridad(token.nextToken());

					if(cine.buscarClienteEnCine(nuevo)) {
						JOptionPane.showMessageDialog(registerWindow, "El cliente ya esta registrado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
					}else {
						cine.registarCliente(nuevo);
						JOptionPane.showMessageDialog(registerWindow, "El cliente ha sido registrado correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						registerWindow.resetFields();
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


			//Botones de la ventana de eliminacion

			if( deleteWindow != null) {


			}


		}


	}

}
