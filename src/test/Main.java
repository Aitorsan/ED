package test;
import listas_exp.ListaSinCabecera;
public class Main {

	public static void main(String[] args) {
  
		
		ListaSinCabecera<String> s = new ListaSinCabecera<String>();
		
       s.insertar("Aitor");
       s.insertar("Carolin");
       s.insertar("javier");
       s.insertar("Paco");
       
      System.out.println( s.buscar("Aitor"));

	}
	
	
}