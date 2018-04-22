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

/**
 * Class for the register window
 * @author Aitor Sanmartin Ferreira
 *
 */
public class Register_Window extends JDialog {

	//Serial version id
	private static final long serialVersionUID = 1L;
  
	//Name and surname JTextFields
	private static final int TEXT_WIDTH = 25;
	//age JTextfields size
	private static final int TEXT_EDAD_WIDTH = 2;
	/*Window size*/
	private static final int MIN_WIDTH=500;
	private static final int MIN_HEIGHT=200;
	
	//Confirm and cancel JButtons
	JButton aceptar;
	JButton cancelar;
	//JFormattedTextFields
	JTextField nombreYApellidos;
	JFormattedTextField edad;
	MaskFormatter mascaraEdad;
	
	//JCombobox
	JComboBox<String> sexo;
	JComboBox<String>prioridad;
	//JString options
	String []opcionSexo;
	String[] opcionesPrioridad;
	//JLabels
	JLabel l_nombreApellidos;
	JLabel l_edad;
	JLabel l_sexo;
	JLabel l_prioridad;
	

	
	/**Constructor */
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
	
	/**
	 * Method to initialize the components
	 */
	private void initComponents() {
		
		/* Main layout configuration*/
		Container windowPane = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		windowPane.setLayout(springLayout);
		
		/* Mask format for the age*/
		try {
			mascaraEdad = new MaskFormatter("##");//well we assume there is nobody 100 years old or more
		
			edad = new JFormattedTextField(mascaraEdad);
			edad.setColumns(TEXT_EDAD_WIDTH);
			

		} catch (ParseException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Error inicializar la mascara: "+e.getMessage(), 
					"Error",JOptionPane.ERROR_MESSAGE);
		}
		//JLables 
		 l_nombreApellidos = new  JLabel ("Nombre y Apellidos");
		 l_edad= new  JLabel ("Edad");
		 l_sexo= new  JLabel ("Sexo");
		 l_prioridad= new  JLabel("Prioridad");
		 
		//TextField name and surnames
		 nombreYApellidos = new JTextField(TEXT_WIDTH);
		 
		 //JCombobox options
		 opcionSexo = new String[] {"Hombre","Mujer"};	
		 opcionesPrioridad = new String[] {"Ninguna","Discapacitado/a","Embarazada","Tercera Edad"};
		 
		 //JCombobox
		 sexo = new JComboBox<String>(opcionSexo);
		 prioridad = new JComboBox<String>(opcionesPrioridad);
		 
		 //JButtons
		 aceptar = new JButton("Aceptar");
		 cancelar = new JButton("Cancelar");
		
		//Positioning configuration
		
		//JLabels
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
	   
		//Formatted Text fields for the age
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
	 * @return the confirm operation button
	 */
	public JButton getAceptar() {
		return aceptar;
	}

	/**
	 * @return abort operation button
	 */
	public JButton getCancelar() {
		return cancelar;
	}

	/**
	 * Getter that returns the textField containing the name and surname of a client
	 * @return nombreYapellidos
	 */
	public JTextField getNombreYApellidosTextField() {
		return nombreYApellidos;
	}

	/**
	 * Getter that gets the age of the client
	 * @return edad
	 */
	public int getEdad() {
		return Integer.parseInt(edad.getText());
	}

	/**
	 * Getter that returns the gender of a client
	 * @return Gender
	 */
	public String getSexo() {
		return sexo.getSelectedItem().toString();
	}

	/**
	 * Getter that returns the level of priority of the client
	 * @return prioridad
	 */
	public String getPrioridad() {
		return prioridad.getSelectedItem().toString();
	}

	/**
	 * reset text fields
	 */
	public void resetFields() {	
		nombreYApellidos.setText("");
		edad.setText("");
		
	}
	

}
