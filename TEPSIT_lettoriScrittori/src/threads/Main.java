package threads;


import gestionerisorsa.Buffer;

public class Main {
	public static void main(String[] args) {
        Buffer buffer = new Buffer();

        // Creazione e avvio dei thread lettori e scrittori
        for (int i = 0; i < 5; i++) {
        	
        	 Lettore lettore = new Lettore(buffer);
             lettore.start();
             
             Scrittore scrittore = new Scrittore(buffer);
             scrittore.start();
            
        }
    }
}
