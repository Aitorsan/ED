package aec2.metro.implementacion.undergroundapp;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class DeleteConnectionWindow extends ConnectionWindow{
	
	/**Serial version uid */
	private static final long serialVersionUID = -3303996617047260395L;
	
	public DeleteConnectionWindow(String[] infoStations, String name) {
		super();
		this.setTitle(name);
		this.initComponents(infoStations);
		this.setModal(true);
		this.setPreferredSize(new Dimension(300,200));
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// TODO Auto-generated constructor stub
		
	}


	@Override
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
		
		
		//JButton
		acceptConnectionButton= new JButton("Desconectar");
		gridConstraints.gridx=1;
	    gridConstraints.gridy=2;
	    this.add(acceptConnectionButton,gridConstraints);

		
	}

}
