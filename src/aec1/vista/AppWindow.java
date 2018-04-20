package aec1.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SpringLayout;
import aec1.implementacion.CineUdima;
import aec1.implementacion.Cliente;
import colas.Cola;
import colas.ColaEnlazada;
import excepciones.DesbordamientoInferior;
import listas.Lista;


import javax.swing.BoxLayout;
import javax.swing.JButton;

public class AppWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	/*Scroll bar number*/
	private static final int SCROLLBAR_SIZE = 11;
	/*width and height of the main frame*/
	private static final int APP_WIDTH  = 910;
	private static final int APP_HEIGHT = 700;

	/*variables de posicionamiento*/
	private static final int UP_DISTANCE      = 30;
	private static final int SPACE_BETWEEN    = -5;
	private static final int  BUTTOM_DISTANCE = -100;
	private static final int UP_LABELS = 10;

	/* flags to set visible different frames*/
	public static final int ZONA_PROYECCION_VISIBLE = 0;
	public static final int ZONA_ENTRADA_VISIBLE    = 1;

	/*Indexes de las scrollbars zona entrada*/
	private static final int SCROLL_ENTRADA      = 0;
	private static final int SCROLL_TAQUILLA_UNO = 1;
	private static final int SCROLL_TAQUILLA_DOS = 2;
	private static final int SCROLL_COMERCIO     = 3;
	private static final int SCROLL_CONTROL      = 4;
	private static final int SCROLL_CONTROL_PRIORITARIO = 5;

	/*Indexes de las scrollbars zona proyeccion*/
	private static final int SCROLL_POYECCION = 6;
	private static final int SCROLL_ASEOS_H   = 7;
	private static final int SCROLL_ASEOS_M   = 8;
	private static final int SCROLL_SALA      = 9;
	private static final int SCROLL_SALIDA    = 10;


	/*JButtons indexes for the toolbar*/
	public static final int ADD_BUTTON        = 0;
	public static final int REMOVE_BUTTON     = 1;
	public static final int ABANDONAR_COLA_BUTTON       = 2;
	public static final int ZONA_ENTRADA      = 3;
	public static final int ZONA_PROYECCION   = 4;
	private static final int BUTTON_ARRAY_SIZE = 5;

	/*JToolbar*/
	JToolBar toolbar;

	/*JButtons for the toolbar*/
	JButton [] buttons;

	/*Scrollbar */
	JScrollPane[] scroll;

	/*Jpanels */
	JPanel panelAplicacion;
	JPanel zona_Entrada;
	JPanel zona_Proyeccion;

	/*JTextArea*/
	JTextArea text_Entrada;
	JTextArea text_Taquilla_uno;
	JTextArea text_Taquilla_dos;
	JTextArea text_Cola_Comercio;
	JTextArea text_Cola_Control;
	JTextArea text_Cola_Control_Prioritario;

	//JTextArea 
	JTextArea text_Proyeccion;
	JTextArea text_Aseo_Caballero;
	JTextArea text_Aseo_Mujeres;
	JTextArea text_Sala_Proyeccion;
	JTextArea text_Salida;


	//JLables para lazona Entrada
	JLabel l_zonaEntrada;
	JLabel l_taquilla_uno;
	JLabel l_taquilla_dos;
	JLabel l_comercio;
	JLabel l_control;
	JLabel l_control_prioritario;

	//JLables para la zona Proyeccion
	JLabel l_zonaProyeccion;
	JLabel l_aseoHombre;
	JLabel l_aseoMujer;
	JLabel l_salaCine;
	JLabel l_salida;

	//JButons zona Entrada
	JButton abandonar_cliente_zonaEntrada;
	JButton abandonar_cola_zonaEntrada;
	JButton siguiente_taquilla_uno;
	JButton siguiente_taquilla_dos;
	JButton siguiente_comercio;
	//este boton vale para las dos colas ya que cuando se atienda al siguiente solo se atendera 
	//a la cola sin prioridad cuando la otra este vacia
	JButton siguiente_control;

	//JButtons zona Proyeccion

	
	//Si se decide abandonar una cola y no la entrada de cine se vuelve a la lista zona proyeccion
	JButton abandonar_cola;
	JButton siguiente_aseo_H;
	JButton siguiente_aseo_M;
	JButton siguiente_salida;
	JButton desapilar_sala;

	/**Constructor de la ventana principal*/
	public AppWindow() {
		super("Cine Udima");
		initComponents();
		setResizable(false);
		setPreferredSize(new Dimension(APP_WIDTH,APP_HEIGHT));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	/**Metodo que inicializa todo los componentes del frame*/
	private void initComponents() {

		// set the layout
		this.getContentPane().setLayout(new BorderLayout());

		//JPanel aplicacion
		panelAplicacion = new JPanel();
		panelAplicacion.setLayout(new BoxLayout(panelAplicacion, getDefaultCloseOperation()));

		//JTextArea zona proyeccion
		text_Proyeccion = new JTextArea();
		text_Aseo_Caballero= new JTextArea();
		text_Aseo_Mujeres= new JTextArea();
		text_Sala_Proyeccion= new JTextArea();
		text_Salida= new JTextArea();


		//Scrollbars
		scroll = new JScrollPane[SCROLLBAR_SIZE];


		//JButtons Del tool bar
		buttons = new JButton[BUTTON_ARRAY_SIZE];
		for( int i  =0 ; i < BUTTON_ARRAY_SIZE; ++i ) {
			buttons[i] = new JButton();
		}

		buttons[ADD_BUTTON].setText("registrar cliente");
		buttons[REMOVE_BUTTON].setText("Eliminar cliente");
		buttons[ABANDONAR_COLA_BUTTON].setText("abandonar cola");
		buttons[ZONA_ENTRADA].setText("Zona-Entrada");
		buttons[ZONA_PROYECCION].setText("Zona-Proyeccion");

		//toolbar
		toolbar = new JToolBar();
		for( int i = 0 ; i < buttons.length;++i) {	
			toolbar.add(buttons[i]);
		}
		toolbar.setFloatable(false);


		/*-*******************************
		 * Configuracion zona entrada
		 ********************************/

		// Jpanel zona entrada
		zona_Entrada = new JPanel(); 
		SpringLayout spring_entrada= new SpringLayout(); 
		zona_Entrada.setLayout(spring_entrada);
		zona_Entrada.setBackground(Color.RED);
		zona_Entrada.setVisible(true);


		//JButtons zona entrada

		//Ventana de abandonar cola
		abandonar_cola_zonaEntrada = new JButton("Abandonar cola");
		siguiente_taquilla_uno=new JButton("siguiente 1");
		siguiente_taquilla_dos = new JButton("siguiente 2");
		siguiente_comercio = new JButton("Siguiente c.");
		siguiente_control = new JButton("Siguiente Control");

		//Poscionamiento de los Botones

		spring_entrada.putConstraint(SpringLayout.NORTH,abandonar_cola_zonaEntrada,-100, SpringLayout.SOUTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST, abandonar_cola_zonaEntrada, 10, SpringLayout.WEST, zona_Entrada);

		

		spring_entrada.putConstraint(SpringLayout.NORTH,siguiente_taquilla_uno,-100, SpringLayout.SOUTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST, siguiente_taquilla_uno, 20, SpringLayout.EAST, abandonar_cola_zonaEntrada);

		spring_entrada.putConstraint(SpringLayout.NORTH,siguiente_taquilla_dos,-100, SpringLayout.SOUTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST, siguiente_taquilla_dos, 40, SpringLayout.EAST, siguiente_taquilla_uno);

		spring_entrada.putConstraint(SpringLayout.NORTH,siguiente_comercio,-100, SpringLayout.SOUTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST, siguiente_comercio, 30, SpringLayout.EAST, siguiente_taquilla_dos);

		spring_entrada.putConstraint(SpringLayout.NORTH,siguiente_control,-100, SpringLayout.SOUTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST, siguiente_control, 100, SpringLayout.EAST, siguiente_comercio);

		//JLables para lazona Entrada
		l_zonaEntrada = new JLabel("Lista Zona Entrada");
		l_taquilla_uno = new JLabel("Cola Taquilla 1");
		l_taquilla_dos = new JLabel("Cola Taquilla 2");
		l_comercio = new JLabel("Cola Comercio");
		l_control = new JLabel("Cola control");
		l_control_prioritario = new JLabel("Cola Control Prioritario");

		//Poscionamiento de los labels

		spring_entrada.putConstraint(SpringLayout.NORTH,l_zonaEntrada, UP_LABELS, SpringLayout.NORTH, zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST, l_zonaEntrada, 20, SpringLayout.WEST, zona_Entrada);

		spring_entrada.putConstraint(SpringLayout.NORTH,l_taquilla_uno, UP_LABELS, SpringLayout.NORTH, zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST, l_taquilla_uno, 30, SpringLayout.EAST, l_zonaEntrada);


		spring_entrada.putConstraint(SpringLayout.NORTH,l_taquilla_dos, UP_LABELS, SpringLayout.NORTH, zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST, l_taquilla_dos, 50, SpringLayout.EAST, l_taquilla_uno);

		spring_entrada.putConstraint(SpringLayout.NORTH,l_comercio, UP_LABELS, SpringLayout.NORTH, zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST, l_comercio, 60, SpringLayout.EAST, l_taquilla_dos);

		spring_entrada.putConstraint(SpringLayout.NORTH,l_control, UP_LABELS, SpringLayout.NORTH, zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST, l_control, 70, SpringLayout.EAST,  l_comercio);

		spring_entrada.putConstraint(SpringLayout.NORTH,l_control_prioritario, UP_LABELS, SpringLayout.NORTH, zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST, l_control_prioritario, 54, SpringLayout.EAST,  l_control);

		//JTextArea zona entrada
		text_Entrada = new JTextArea();
		text_Taquilla_uno = new JTextArea();
		text_Taquilla_dos= new JTextArea();
		text_Cola_Comercio= new JTextArea();
		text_Cola_Control= new JTextArea();
		text_Cola_Control_Prioritario= new JTextArea();

		//Scrollbars zona Entrada

		scroll[SCROLL_ENTRADA] = new JScrollPane(text_Entrada,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll[SCROLL_TAQUILLA_UNO]= new JScrollPane(text_Taquilla_uno,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll[SCROLL_TAQUILLA_DOS]= new JScrollPane(text_Taquilla_dos,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll[SCROLL_COMERCIO]= new JScrollPane(text_Cola_Comercio,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll[SCROLL_CONTROL]= new JScrollPane(text_Cola_Control,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll[SCROLL_CONTROL_PRIORITARIO]= new JScrollPane(text_Cola_Control_Prioritario,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


		// Posicionar los elementos en su sitio
		spring_entrada.putConstraint(SpringLayout.NORTH,scroll[SCROLL_CONTROL_PRIORITARIO], UP_DISTANCE,SpringLayout.NORTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.EAST,scroll[SCROLL_CONTROL_PRIORITARIO], SPACE_BETWEEN ,SpringLayout.EAST,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.SOUTH,scroll[SCROLL_CONTROL_PRIORITARIO],BUTTOM_DISTANCE,SpringLayout.SOUTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST,scroll[SCROLL_CONTROL_PRIORITARIO],APP_WIDTH-150 ,SpringLayout.WEST,zona_Entrada);

		spring_entrada.putConstraint(SpringLayout.NORTH,scroll[SCROLL_CONTROL], UP_DISTANCE,SpringLayout.NORTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.EAST,scroll[SCROLL_CONTROL], SPACE_BETWEEN,SpringLayout.WEST,scroll[SCROLL_CONTROL_PRIORITARIO]);
		spring_entrada.putConstraint(SpringLayout.SOUTH,scroll[SCROLL_CONTROL],BUTTOM_DISTANCE,SpringLayout.SOUTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST,scroll[SCROLL_CONTROL], APP_WIDTH-300,SpringLayout.WEST,zona_Entrada);

		spring_entrada.putConstraint(SpringLayout.NORTH,scroll[SCROLL_COMERCIO ], UP_DISTANCE,SpringLayout.NORTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.EAST,scroll[SCROLL_COMERCIO ],SPACE_BETWEEN,SpringLayout.WEST,scroll[SCROLL_CONTROL]);
		spring_entrada.putConstraint(SpringLayout.SOUTH,scroll[SCROLL_COMERCIO ],BUTTOM_DISTANCE,SpringLayout.SOUTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST,scroll[SCROLL_COMERCIO ], APP_WIDTH-450,SpringLayout.WEST,zona_Entrada);


		spring_entrada.putConstraint(SpringLayout.NORTH,scroll[SCROLL_TAQUILLA_DOS], UP_DISTANCE,SpringLayout.NORTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.EAST,scroll[SCROLL_TAQUILLA_DOS], SPACE_BETWEEN,SpringLayout.WEST,scroll[SCROLL_COMERCIO]);
		spring_entrada.putConstraint(SpringLayout.SOUTH,scroll[SCROLL_TAQUILLA_DOS],BUTTOM_DISTANCE,SpringLayout.SOUTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST,scroll[SCROLL_TAQUILLA_DOS], APP_WIDTH-600,SpringLayout.WEST,zona_Entrada);


		spring_entrada.putConstraint(SpringLayout.NORTH,scroll[SCROLL_TAQUILLA_UNO], UP_DISTANCE,SpringLayout.NORTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.EAST,scroll[SCROLL_TAQUILLA_UNO], SPACE_BETWEEN,SpringLayout.WEST,scroll[SCROLL_TAQUILLA_DOS]);
		spring_entrada.putConstraint(SpringLayout.SOUTH,scroll[SCROLL_TAQUILLA_UNO],BUTTOM_DISTANCE,SpringLayout.SOUTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST,scroll[SCROLL_TAQUILLA_UNO], APP_WIDTH-750,SpringLayout.WEST,zona_Entrada);


		spring_entrada.putConstraint(SpringLayout.NORTH,scroll[SCROLL_ENTRADA], UP_DISTANCE,SpringLayout.NORTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.EAST,scroll[SCROLL_ENTRADA], SPACE_BETWEEN,SpringLayout.WEST,scroll[SCROLL_TAQUILLA_UNO]);
		spring_entrada.putConstraint(SpringLayout.SOUTH,scroll[SCROLL_ENTRADA],BUTTOM_DISTANCE,SpringLayout.SOUTH,zona_Entrada);
		spring_entrada.putConstraint(SpringLayout.WEST,scroll[SCROLL_ENTRADA], APP_WIDTH-900,SpringLayout.WEST,zona_Entrada);




		/*-*******************************
		 * Configuracion zona proyeccion
		 ********************************/

		//Jpanel zona proyeccion
		zona_Proyeccion = new JPanel();
		SpringLayout spring_proyeccion = new SpringLayout();
		zona_Proyeccion.setLayout(spring_proyeccion);
		zona_Proyeccion.setBackground(Color.YELLOW);
		zona_Proyeccion.setVisible(false);

		// JButtons zona proyeccon los cuales contienen la logica de movimiento
		//TODO:getters
		//TODO:Ventana con JComboBox para elegir que cola abandonar nueva clase

		
		abandonar_cola   = new JButton ("Abandonar cola");
		siguiente_aseo_H = new JButton ("siguiente H"); 
		siguiente_aseo_M = new JButton ("siguiente M"); 
		desapilar_sala   = new JButton ("Desapilar sala");
		siguiente_salida = new JButton ("Salir siguiente"); 


		//Posicionamiento de los botones
		spring_proyeccion.putConstraint(SpringLayout.NORTH,abandonar_cola,-100, SpringLayout.SOUTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST,abandonar_cola,50, SpringLayout.WEST,zona_Proyeccion);


		spring_proyeccion.putConstraint(SpringLayout.NORTH,siguiente_aseo_H,-100, SpringLayout.SOUTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST,siguiente_aseo_H,100, SpringLayout.EAST,abandonar_cola);

		spring_proyeccion.putConstraint(SpringLayout.NORTH,siguiente_aseo_M,-100, SpringLayout.SOUTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST,siguiente_aseo_M,30, SpringLayout.EAST,siguiente_aseo_H);

		spring_proyeccion.putConstraint(SpringLayout.NORTH,desapilar_sala,-100, SpringLayout.SOUTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST,desapilar_sala,25, SpringLayout.EAST,siguiente_aseo_M);

		spring_proyeccion.putConstraint(SpringLayout.NORTH,siguiente_salida,-100, SpringLayout.SOUTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST,siguiente_salida,25, SpringLayout.EAST,desapilar_sala);



		//JLables para la zona Proyeccion
		l_zonaProyeccion = new JLabel ("Lista zona Proyeccion");
		l_aseoHombre     = new JLabel ("Cola aseo caballero");
		l_aseoMujer      = new JLabel ("Cola aseo mujeres");
		l_salaCine       = new JLabel ("Pila sala cine");
		l_salida         = new JLabel ("Cola salida");

		//Posicionamiento JLabels zona Proyeccion

		spring_proyeccion.putConstraint(SpringLayout.NORTH,l_zonaProyeccion, UP_LABELS, SpringLayout.NORTH, zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST, l_zonaProyeccion, 60, SpringLayout.WEST, zona_Proyeccion);


		spring_proyeccion.putConstraint(SpringLayout.NORTH,l_aseoHombre, UP_LABELS, SpringLayout.NORTH, zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST, l_aseoHombre, 70, SpringLayout.EAST, l_zonaProyeccion);

		spring_proyeccion.putConstraint(SpringLayout.NORTH,l_aseoMujer, UP_LABELS, SpringLayout.NORTH, zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST, l_aseoMujer, 30, SpringLayout.EAST, l_aseoHombre);



		spring_proyeccion.putConstraint(SpringLayout.NORTH,l_salaCine, UP_LABELS, SpringLayout.NORTH, zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST, l_salaCine, 30, SpringLayout.EAST, l_aseoMujer);


		spring_proyeccion.putConstraint(SpringLayout.NORTH,l_salida, UP_LABELS, SpringLayout.NORTH, zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST, l_salida, 70, SpringLayout.EAST, l_salaCine);

		//JTextArea zona proyeccion
		text_Proyeccion = new JTextArea();
		text_Aseo_Caballero = new JTextArea();
		text_Aseo_Mujeres= new JTextArea();
		text_Sala_Proyeccion= new JTextArea();
		text_Salida= new JTextArea();


		//Scrollbars zona Proyeccion

		scroll[SCROLL_POYECCION ] = new JScrollPane(text_Proyeccion ,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll[SCROLL_ASEOS_H]= new JScrollPane(text_Aseo_Caballero,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll[SCROLL_ASEOS_M]= new JScrollPane(text_Aseo_Mujeres,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll[SCROLL_SALA ]= new JScrollPane(text_Sala_Proyeccion,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll[ SCROLL_SALIDA]= new JScrollPane(text_Salida,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


		// Posicionar los elementos en su sitio
		spring_proyeccion.putConstraint(SpringLayout.NORTH,scroll[SCROLL_SALIDA], UP_DISTANCE,SpringLayout.NORTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.EAST,scroll[SCROLL_SALIDA], SPACE_BETWEEN-40 ,SpringLayout.EAST,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.SOUTH,scroll[SCROLL_SALIDA],BUTTOM_DISTANCE,SpringLayout.SOUTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST,scroll[SCROLL_SALIDA],APP_WIDTH-200 ,SpringLayout.WEST,zona_Proyeccion);

		spring_proyeccion.putConstraint(SpringLayout.NORTH,scroll[SCROLL_SALA], UP_DISTANCE,SpringLayout.NORTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.EAST,scroll[SCROLL_SALA], SPACE_BETWEEN,SpringLayout.WEST,scroll[SCROLL_SALIDA]);
		spring_proyeccion.putConstraint(SpringLayout.SOUTH,scroll[SCROLL_SALA],BUTTOM_DISTANCE,SpringLayout.SOUTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST,scroll[SCROLL_SALA], APP_WIDTH-350,SpringLayout.WEST,zona_Proyeccion);

		spring_proyeccion.putConstraint(SpringLayout.NORTH,scroll[SCROLL_ASEOS_M ], UP_DISTANCE,SpringLayout.NORTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.EAST,scroll[SCROLL_ASEOS_M ],SPACE_BETWEEN,SpringLayout.WEST,scroll[SCROLL_SALA]);
		spring_proyeccion.putConstraint(SpringLayout.SOUTH,scroll[SCROLL_ASEOS_M ],BUTTOM_DISTANCE,SpringLayout.SOUTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST,scroll[SCROLL_ASEOS_M ], APP_WIDTH-500,SpringLayout.WEST,zona_Proyeccion);



		spring_proyeccion.putConstraint(SpringLayout.NORTH,scroll[SCROLL_ASEOS_H], UP_DISTANCE,SpringLayout.NORTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.EAST,scroll[SCROLL_ASEOS_H], SPACE_BETWEEN,SpringLayout.WEST,scroll[SCROLL_ASEOS_M]);
		spring_proyeccion.putConstraint(SpringLayout.SOUTH,scroll[SCROLL_ASEOS_H],BUTTOM_DISTANCE,SpringLayout.SOUTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST,scroll[SCROLL_ASEOS_H], APP_WIDTH-650,SpringLayout.WEST,zona_Proyeccion);


		spring_proyeccion.putConstraint(SpringLayout.NORTH,scroll[SCROLL_POYECCION ], UP_DISTANCE,SpringLayout.NORTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.EAST,scroll[SCROLL_POYECCION ], SPACE_BETWEEN,SpringLayout.WEST,scroll[SCROLL_ASEOS_H]);
		spring_proyeccion.putConstraint(SpringLayout.SOUTH,scroll[SCROLL_POYECCION ],BUTTOM_DISTANCE,SpringLayout.SOUTH,zona_Proyeccion);
		spring_proyeccion.putConstraint(SpringLayout.WEST,scroll[SCROLL_POYECCION ], APP_WIDTH-850,SpringLayout.WEST,zona_Proyeccion);


		//Add JButtons de la zona de proyeccion
	
		zona_Proyeccion.add(abandonar_cola);
		zona_Proyeccion.add(siguiente_aseo_H);
		zona_Proyeccion.add(siguiente_aseo_M);
		zona_Proyeccion.add(desapilar_sala );
		zona_Proyeccion.add(siguiente_salida );


		//Add JLables a la zona proyeccion
		zona_Proyeccion.add(l_zonaProyeccion);
		zona_Proyeccion.add(l_aseoHombre);
		zona_Proyeccion.add(l_aseoMujer);
		zona_Proyeccion.add(l_salaCine);
		zona_Proyeccion.add(l_salida);


		//Add Componentes zona de proyeccion
		zona_Proyeccion.add(scroll[10]);
		zona_Proyeccion.add(scroll[9]);
		zona_Proyeccion.add(scroll[8]);
		zona_Proyeccion.add(scroll[7]);
		zona_Proyeccion.add(scroll[6]);


		//Add JButton zona entrada
		zona_Entrada.add(abandonar_cola_zonaEntrada);
		zona_Entrada.add(siguiente_taquilla_uno);
		zona_Entrada.add(siguiente_taquilla_dos);
		zona_Entrada.add(siguiente_comercio);
		zona_Entrada.add(siguiente_control);

		//Add JLables a la zona entrada
		zona_Entrada.add(l_zonaEntrada );
		zona_Entrada.add(l_taquilla_uno);
		zona_Entrada.add(l_taquilla_dos);
		zona_Entrada.add(l_comercio);
		zona_Entrada.add(l_control);
		zona_Entrada.add(l_control_prioritario);

		//Add Componentes zona entrada
		zona_Entrada.add(scroll[5]);
		zona_Entrada.add(scroll[4]);
		zona_Entrada.add(scroll[3]);
		zona_Entrada.add(scroll[2]);
		zona_Entrada.add(scroll[1]);
		zona_Entrada.add(scroll[0]);



		//Add componentes to the main frame
		panelAplicacion.add(zona_Entrada);
		panelAplicacion.add(zona_Proyeccion);
		//Add panel aplicacion to the main frame
		add(toolbar,BorderLayout.NORTH);
		add(panelAplicacion,BorderLayout.CENTER);
	}


	/**
	 * @return the abandonar_cola_zonaEntrada
	 */
	public JButton getAbandonar_cola_zonaEntrada_boton() {
		return abandonar_cola_zonaEntrada;
	}

	/**
	 * @return the siguiente_taquilla_uno
	 */
	public JButton getSiguiente_taquilla_uno_boton() {
		return siguiente_taquilla_uno;
	}

	/**
	 * @return the siguiente_taquilla_dos
	 */
	public JButton getSiguiente_taquilla_dos_boton() {
		return siguiente_taquilla_dos;
	}

	/**
	 * @return the siguiente_comercio
	 */
	public JButton getSiguiente_comercio_boton() {
		return siguiente_comercio;
	}

	/**
	 * @return the siguiente_control
	 */
	public JButton getSiguiente_control_boton() {
		return siguiente_control;
	}


	/**
	 * @return the abandonar_cola
	 */
	public JButton getAbandonar_cola_boton_zonaProyeccion() {
		return abandonar_cola;
	}

	/**
	 * @return the siguiente_aseo_H
	 */
	public JButton getSiguiente_aseo_H_boton() {
		return siguiente_aseo_H;
	}

	/**
	 * @return the siguiente_aseo_M
	 */
	public JButton getSiguiente_aseo_M_boton() {
		return siguiente_aseo_M;
	}

	/**
	 * @return the siguiente_salida
	 */
	public JButton getSiguiente_salida_boton() {
		return siguiente_salida;
	}

	/**
	 * @return the desapilar_sala
	 */
	public JButton getDesapilar_sala_boton() {
		return desapilar_sala;
	}

	/**
	 * Retorna todos los botones del tool bar de la ventana principal, en un array
	 * @return
	 */
	public JButton[] getButtonsToolBar() {
		return buttons;
	}

	/**Metodo que cambia los paneles con la informacion referente a las Dos zonas principales
	 * Si queremos ver la informacion de la zona de proyeccion la otra sera invisible
	 * @param flag ZONA_ENTRADA_VISIBLE , ZONA_PROYECCION_VISIBLE
	 */
	public void changeArea(int flag) {
		switch(flag) {
		case ZONA_ENTRADA_VISIBLE:
			zona_Entrada.setVisible(true);
			zona_Proyeccion.setVisible(false);
			break;
		case ZONA_PROYECCION_VISIBLE:
			zona_Entrada.setVisible(false);
			zona_Proyeccion.setVisible(true);
			break;
		}
	}

	

	/**
	 * Actualiza la informacion de la zona de proyeccion
	 * @param info
	 */
	public void actualizarInformacion(CineUdima cine,int flag ) {


		

	
		if( (flag & CineUdima.CONTROL) != 0) {
			
			text_Cola_Control.setText(getInfo(cine.getControl()));
			
		}

		if( (flag & CineUdima.CONTROL_P) != 0) {
		
			text_Cola_Control_Prioritario.setText(getInfo(cine.getControl_prioritario()));
			
		}

		if( (flag & CineUdima.TAQUILLA_UNO) != 0) {
			String info= getInfo(cine.getTaquillas_ventanilla_uno());
			text_Taquilla_uno.setText(info);
			
		}

		if( (flag & CineUdima.TAQUILLA_DOS) != 0) {
			
			text_Taquilla_dos.setText(getInfo(cine.getTaquillas_ventanilla_dos()));
			
		}


		if ((flag & CineUdima.ASEOSH)!= 0) {
		
			text_Aseo_Caballero.setText( getInfo(cine.getAseo_h()));
		}
	
	
		 if( (flag &CineUdima.ASEOSM) != 0) {
			 
			 text_Aseo_Mujeres.setText(getInfo(cine.getAseo_m())); 
		 }
			
		
		if((flag&  CineUdima.COMERCIO) != 0) {
			text_Cola_Comercio.setText(getInfo(cine.getComercio()));

		}

		if( (CineUdima.ENTRADA & flag )!= 0) {
			
			text_Entrada.setText(getInfo(cine.getZonaEntrada()));
		}
		if((CineUdima.LISTA_ZONA_PROYECCION&flag) != 0){
			
			text_Proyeccion.setText(getInfo(cine.getZonaProyeccion()));

		}
		
	      if((CineUdima.SALIDA&flag) != 0){
			
			text_Proyeccion.setText(getInfo(cine.getSalida()));

		}
		
	}


	/**
	 * Funcion auxiliar para recoger la información de una cola
	 */

	private String getInfo(Cola c) {
		//TODO: 
		String info = "";
		Cola colaAuxi = new ColaEnlazada();
		try {
			while(!c.esVacia()) {


				info+= ((Cliente)c.primero()).getNombre()+" ";
				info+= ((Cliente)c.primero()).getPrimerApellido()+"";
				info+= ((Cliente)c.primero()).getSegundoApellido()+"\n";
				colaAuxi.insertar(c.primero());
				c.quitarPrimero();

			}
			while(!colaAuxi.esVacia()) {

				c.insertar(colaAuxi.primero());
				colaAuxi.quitarPrimero();

			}

		} catch (DesbordamientoInferior e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
	/**
	 * Reset tht text areas of the main frame
	 */
	public void resetTextFields() {
		text_Entrada.setText("");
		text_Taquilla_uno.setText("");
		text_Taquilla_dos.setText("");
		text_Cola_Comercio.setText("");
		text_Cola_Control.setText("");
		text_Cola_Control_Prioritario.setText("");

		//JTextArea 
		text_Proyeccion.setText("");
		text_Aseo_Caballero.setText("");
		text_Aseo_Mujeres.setText("");
		text_Sala_Proyeccion.setText("");
		text_Salida.setText("");

	}
	
	/**
	 * Funcion auxiliar para recoger informacion de una lista
	 */
	private String getInfo(Lista lista) {
		
	         lista.primero();
	         String info = "";
	         while(lista.estaDentro()) {
	        	    info+= ((Cliente)lista.recuperar()).getNombre()+" ";
					info+= ((Cliente)lista.recuperar()).getPrimerApellido()+" ";
					info+= ((Cliente)lista.recuperar()).getSegundoApellido()+"\n---------------------------------\n";
	        	       lista.avanzar();
	         }
		    lista.primero();
		
		return info;
	}
}
