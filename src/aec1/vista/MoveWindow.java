package aec1.vista;

import javax.swing.JComboBox;

public class MoveWindow extends AuxiliarWindow{
	private static final long serialVersionUID = 1L;
	
	//Options where we can move around
	JComboBox<String> options;
	
    //Options
	String[] option_string;
	
	
	public MoveWindow(String frameName, String buttonName) {
		super(frameName, buttonName);
		
		option_string = new String[6];
		option_string[0] = "Lista Entrada";
	    option_string[1] = "Taquilla 1";
	    option_string[2] = "Taquilla 2";
	    option_string[3] = "Comercio";
	    option_string[4] = "Control";
	    option_string[5] = "Control prioritario";
	    						
		options = new JComboBox<String>(option_string);
		
		
		add(options);
		
		
	}

  /**
   * getter, that returns the selected option
   * @return String
   */
	public String getSelectedItem() {
		return options.getSelectedItem().toString();
	}
	

}
