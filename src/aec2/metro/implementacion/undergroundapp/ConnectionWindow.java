package aec2.metro.implementacion.undergroundapp;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import aec2.metro.implementacion.Estacion;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
public class ConnectionWindow extends JDialog{
	/** generated serailVersioinUID*/
	private static final long serialVersionUID = 9026068439105191937L;


	
	//labels
	JLabel lOrigin;
	JLabel lDestiny;
	JLabel lDistance;
	//JComboBox
	JComboBox<String> comboBoxOrigin;
	JComboBox<String> comboBoxDestiny;
	//JButton
	JButton acceptConnectionButton;
	
	//Spinner for select the distance
	JSpinner sdistanceChooser;
	
	//constructor
	public ConnectionWindow(String[] infoStations,String name) {
		this.setTitle(name);
		initComponents(infoStations);
		this.setModal(true);
		this.setPreferredSize(new Dimension(300,200));
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	protected ConnectionWindow() {
		
	}
	
	/**
	 * Initialize all the components of the window
	 */
	protected void initComponents(String[] infoStations) {
		
		//Layout
		GridBagConstraints gridConstraints = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		gridConstraints.insets=new Insets(5,1,1,1);
		//labels
		 lOrigin= new JLabel("Origen");
		 gridConstraints.gridx= 0;
		 gridConstraints.gridy=0;
		 this.add(lOrigin);
		 
		 lDestiny= new JLabel("Destino");
		 gridConstraints.gridx= 0;
		 gridConstraints.gridy=1;
		 this.add(lDestiny,gridConstraints);
		
		 lDistance= new JLabel("Distance");
		 gridConstraints.gridx=0;
		 gridConstraints.gridy=2;
		 
		 this.add(lDistance,gridConstraints);
		 
		 
		 //JComboBox
		comboBoxOrigin= new JComboBox<String>(infoStations);
		comboBoxOrigin.setSize(100, 20);
		comboBoxOrigin.setEditable(false);
		gridConstraints.gridx=1;
		gridConstraints.gridy=0;
		gridConstraints.gridwidth=3;
		gridConstraints.fill= GridBagConstraints.HORIZONTAL;
		this.add(comboBoxOrigin,gridConstraints);

		comboBoxDestiny= new JComboBox<String>(infoStations);
		comboBoxDestiny.setEditable(false);
		gridConstraints.gridx=1;
		gridConstraints.gridy=1;
		gridConstraints.gridwidth=3;
		this.add(comboBoxDestiny,gridConstraints);
		
		//JSpinner
		SpinnerModel model = new SpinnerNumberModel(0,0, 500, 1);
		sdistanceChooser= new JSpinner(model);
	
		gridConstraints.gridx=1;
		gridConstraints.gridy=2;
		gridConstraints.gridwidth=3;
		this.add(sdistanceChooser,gridConstraints);
		
		//JButton
		acceptConnectionButton= new JButton("Conectar");
		gridConstraints.gridx=1;
	    gridConstraints.gridy=3;
	    this.add(acceptConnectionButton,gridConstraints);

		
	}


	public JButton getConfirmationButton() {
		return acceptConnectionButton;
	}
	public Estacion getOriginStation() {
		 return new Estacion((String)comboBoxOrigin.getSelectedItem());
	}
	public int getDistanceBetween() {
	    //We know will be allways an integer value no problemo
		return (int) sdistanceChooser.getValue();
	}
	public Estacion getDestinyConection() {
		
		return new Estacion((String)comboBoxDestiny.getSelectedItem());
	}
	
	public void updateComboBoxes(String newItem) {
		if(comboBoxDestiny != null) {
			comboBoxDestiny.addItem(newItem);			
		}
		comboBoxOrigin.addItem(newItem);
	}
	

	public void removeItemComboBoxes(String item) {
		if(comboBoxDestiny != null) {
			comboBoxDestiny.removeItem(item);			
		}
		comboBoxOrigin.removeItem(item);
	}
		

}
