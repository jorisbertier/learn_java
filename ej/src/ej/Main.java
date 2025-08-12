package ej;

public class Main {
    
	  public static void main(String[] args) {
	        try {
	            Bloc b = new Mur(1, 1, 1, false);
	            b.afficherDescription();
	        } catch (IllegalBlocException e) {
				// TODO Auto-generated catch block
	        	System.out.println("Valeurs pour construire le bloc invalides");
				e.printStackTrace();
			}
	    }
    
}