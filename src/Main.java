

import asf.data_structures.lists.LinkedList;

public class Main {

	public static void main(String[] args) {
		LinkedList<String> l = new LinkedList<String>();

	
			l.insert("Aitor");
			l.insert("Carolin");
			l.insert("rono");
			l.insert("John Deere");
		
	
		System.out.println(l.getElementAtIndex(0));
		System.out.println(l.getElementAtIndex(1));
		System.out.println(l.getElementAtIndex(2));
		System.out.println(l.getElementAtIndex(3));

		
	
			
			l.insert_at_index("Substitute", 3);
			System.out.println(l.getElementAtIndex(3));
			
	
			
			System.out.println(l.search("Aitor"));
			System.out.println(l.search("John Deere"));
			
		
		   
	
			l.delete_element("Aitor");
			
			l.print_list();

			
		

	}

}
