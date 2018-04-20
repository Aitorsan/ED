package aec1.vista;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.ParseException;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.text.MaskFormatter;
public class Register_Window extends JDialog {

	//Serial version id
	private static final long serialVersionUID = 1L;
  
	//Nombre y apellidos textField size
	private static final int TEXT_WIDTH = 25;
	//edad textfielsize
	private static final int TEXT_EDAD_WIDTH = 2;
	/*Window size*/
	private static final int MIN_WIDTH=500;
	private static final int MIN_HEIGHT=200;
	
	//Boton de aceptar y cancelar
	JButton aceptar;
	JButton cancelar;
	//JFormattedTextFields
	JTextField nombreYApellidos;
	JFormattedTextField edad;
	MaskFormatter mascaraEdad;
	
	//JCombobox
	JComboBox<String> sexo;
	JComboBox<String>prioridad;
	//JString opciones 
	String []opcionSexo;
	String[] opcionesPrioridad;
	//JLabels
	JLabel l_nombreApellidos;
	JLabel l_edad;
	JLabel l_sexo;
	JLabel l_prioridad;
	

	
	/**Constructor del frame*/
	public Register_Window() {
	    	      
		setTitle("Registro");      
		setModal(true);      
		initComponents();
		setPreferredSize(new Dimension(MIN_WIDTH,MIN_HEIGHT));
		setResizable(false);
		pack();
	    setLocationRelativeTo(null);
	    setVisible(false);
	    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	     
	}
	
	/**Inicializa los componentes de la ventana*/
	private void initComponents() {
		
		/* Configuracion del layout principal de la ventana */
		Container windowPane = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		windowPane.setLayout(springLayout);
		
		/* Mascara para la edad*/
		try {
			mascaraEdad = new MaskFormatter("##");//well we assume there is nobody 100 years old or more
		
			edad = new JFormattedTextField(mascaraEdad);
			edad.setColumns(TEXT_EDAD_WIDTH);
			

		} catch (ParseException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Error inicializar la mascara: "+e.getMessage(), 
					"Error",JOptionPane.ERROR_MESSAGE);
		}
		//Labels donde se indica el nombre del campo
		 l_nombreApellidos = new  JLabel ("Nombre y Apellidos");
		 l_edad= new  JLabel ("Edad");
		 l_sexo= new  JLabel ("Sexo");
		 l_prioridad= new  JLabel("Prioridad");
		 
		//TextField para nombre y apellidos
		 nombreYApellidos = new JTextField(TEXT_WIDTH);
		 
		 //JCombobox opciones	
		 opcionSexo = new String[] {"Hombre","Mujer"};	
		 opcionesPrioridad = new String[] {"Ninguna","Discapacitado/a","Embarazada","Tercera Edad"};
		 
		 //JCombobox
		 sexo = new JComboBox<String>(opcionSexo);
		 prioridad = new JComboBox<String>(opcionesPrioridad);
		 
		 //JButtons
		 aceptar = new JButton("Aceptar");
		 cancelar = new JButton("Cancelar");
		
		//Configuracion de la posicion de los diferentes elementos en la ventana
		
		//Lables
		springLayout.putConstraint(SpringLayout.WEST,  l_nombreApellidos, 10, SpringLayout.WEST,this);
		springLayout.putConstraint(SpringLayout.NORTH, l_nombreApellidos, 10, SpringLayout.NORTH,this);
		
		springLayout.putConstraint(SpringLayout.WEST,  l_edad, 10, SpringLayout.WEST,this);
		springLayout.putConstraint(SpringLayout.NORTH, l_edad, 10, SpringLayout.SOUTH,l_nombreApellidos);
		
		
		springLayout.putConstraint(SpringLayout.WEST,  l_sexo, 10, SpringLayout.WEST,this);
		springLayout.putConstraint(SpringLayout.NORTH, l_sexo, 10, SpringLayout.SOUTH,l_edad);
		
		springLayout.putConstraint(SpringLayout.WEST, l_prioridad, 10, SpringLayout.WEST,this);
		springLayout.putConstraint(SpringLayout.NORTH,l_prioridad, 10, SpringLayout.SOUTH,l_sexo);
		
		//TextField
		springLayout.putConstraint(SpringLayout.WEST, nombreYApellidos, 10, SpringLayout.EAST,l_nombreApellidos);
		springLayout.putConstraint(SpringLayout.NORTH, nombreYApellidos, 5, SpringLayout.NORTH,this);
	   
		//Formatted Text fiel para la edad
		springLayout.putConstraint(SpringLayout.WEST, edad, 104, SpringLayout.EAST,l_edad);
		springLayout.putConstraint(SpringLayout.NORTH,edad, 2, SpringLayout.SOUTH, nombreYApellidos);  
		//ComboBoxes
		springLayout.putConstraint(SpringLayout.WEST, sexo, 104, SpringLayout.EAST,l_sexo);
		springLayout.putConstraint(SpringLayout.NORTH,sexo, 2, SpringLayout.SOUTH, edad); 
		
		springLayout.putConstraint(SpringLayout.WEST, prioridad, 77, SpringLayout.EAST,l_prioridad);
		springLayout.putConstraint(SpringLayout.NORTH,prioridad, 2, SpringLayout.SOUTH,sexo); 
		
		
		//JButtons
		springLayout.putConstraint(SpringLayout.EAST, aceptar, MIN_WIDTH/2, SpringLayout.WEST,this);
		springLayout.putConstraint(SpringLayout.NORTH,aceptar,22, SpringLayout.SOUTH,prioridad); 
		
		springLayout.putConstraint(SpringLayout.WEST, cancelar, 2, SpringLayout.EAST,aceptar);
		springLayout.putConstraint(SpringLayout.NORTH,cancelar, 22, SpringLayout.SOUTH,prioridad); 
		
		//Add components
		add(l_nombreApellidos);
		add(nombreYApellidos);
		add(l_edad);
		add(edad);
		add(l_sexo);
		add(sexo);
		add(l_prioridad);
		add(prioridad);
		add(aceptar);
		add(cancelar);
	
	}

	/**
	 * @return el boton de aceptar
	 */
	public JButton getAceptar() {
		return aceptar;
	}

	/**
	 * @return el boton de cancelar
	 */
	public JButton getCancelar() {
		return cancelar;
	}

	/**
	 * @return EL textField donde el usuario introduce el nombre y apellidos
	 */
	public JTextField getNombreYApellidosTextField() {
		return nombreYApellidos;
	}

	/**
	 * @return La edad del cliente registrado
	 */
	public int getEdad() {
		return Integer.parseInt(edad.getText());
	}

	/**
	 * @return retorna informacion sobre el sexo del cliente
	 */
	public String getSexo() {
		return sexo.getSelectedItem().toString();
	}

	/**
	 * @return retorna informacion sobre si el cliente tiene o no algun tipo de prioridad
	 */
	public String getPrioridad() {
		return prioridad.getSelectedItem().toString();
	}

	/**
	 * resetea los campos de informacion de la ventana
	 */
	public void resetFields() {	
		nombreYApellidos.setText("");
		edad.setText("");
		
	}
	

}
