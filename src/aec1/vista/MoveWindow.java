package aec1.vista;

import javax.swing.JComboBox;

/**
 * Move Window class 
 * @author Aitor Sanmartin Ferreira
 *
 */
public class MoveWindow extends AuxiliarWindow{
	private static final long serialVersionUID = 1L;
	public final static char ENTRADA=1;
	public final static char PROYECCION = 2;
	//Options where we can move around
	JComboBox<String> options;
	
    //Options
	String[] option_string;

	/**
	 * Method that sets the configuration of the frame components
	 * @param frameName
	 * @param buttonName
	 * @param flag
	 */
	public MoveWindow(String frameName, String buttonName,char flag) {
		super(frameName, buttonName);
		
		if( (flag & ENTRADA) != 0) {
			option_string = new String[5];
			option_string[0] = "Lista Entrada";
		    option_string[1] = "Taquillas";
		    option_string[2] = "Comercio";
		    option_string[3] = "Control";
		    option_string[4] = "Control prioritario";
		    		
		}if((flag & PROYECCION) != 0) {
			option_string = new String[5];
			option_string[0] = "Proyeccion";
		    option_string[1] = "Aseo mujeres";
		    option_string[2] = "Aseo hombres";
		    option_string[3] = "Sala cine";
		    option_string[4] = "Salida";
		    
		}			
		options = new JComboBox<String>(option_string);
		
		add(options);
	}

  /**
   * Getter, that returns the selected option
   * @return String
   */
	public String getSelectedItem() {
		return options.getSelectedItem().toString();
	}
	

}//End of the class
