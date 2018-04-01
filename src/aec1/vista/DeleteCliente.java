package aec1.vista;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DeleteCliente extends JDialog {
	private static final long serialVersionUID = 1L;
	
	
	/*Window size*/
	private static final int MIN_WIDTH=400;
	private static final int MIN_HEIGHT=120;
	
	
	/*Label para nombre y apellidos del cliente que queremos eliminar*/
	JLabel nombre;
	
	/*JTextField para introducir el nombre del cliente que queremos eliminar*/
	JTextField tNombre;
	
	/*Boton de eliminar, y cancelar operacion*/
	JButton botonEliminar;
	JButton botonCancelar;
	
	/**Constructor**/
	public DeleteCliente(){
		
		setTitle("Eliminar");      
		setModal(true);      
		initComponents();
		setPreferredSize(new Dimension(MIN_WIDTH,MIN_HEIGHT));
		setResizable(false);
		pack();
	    setLocationRelativeTo(null);
	    setVisible(false);
	    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}


   /**Metodo para inicializar todos los componentes del Frame**/
	private void initComponents() {
         getContentPane().setLayout(new FlowLayout());
         //label y textfield
		nombre = new JLabel("Nombre y Apellidos");
		tNombre = new JTextField(25);
		//botones
		 botonEliminar = new JButton("Eliminar");
		 botonCancelar= new JButton("Cancelar");
		//add components
		add(nombre);
		add(tNombre);
		add(botonEliminar);
		add(botonCancelar);
	}

	/**Retorna el valor del text field**/
	public String getTextNombre() {
		return tNombre.getText();
	}
}
