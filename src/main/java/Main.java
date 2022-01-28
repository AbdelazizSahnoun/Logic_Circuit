import vue.Interface;

import java.awt.EventQueue;


public class Main {
	/**
	 * Lancer l'application
	 */
	
	
	public static void main(String[] args) {
	   EventQueue.invokeLater(new Runnable() {
	      @Override
	      public void run() {
	            try {
					Interface gui = new Interface();



	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	       
	      }
	    });
		
	}
}
