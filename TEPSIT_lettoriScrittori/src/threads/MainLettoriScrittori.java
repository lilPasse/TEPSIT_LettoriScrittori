package threads;


import gestionerisorsa.Buffer;

public class MainLettoriScrittori {
	public static void main(String[] args) {
		
		final int NUM = 5;
        Buffer buffer = new Buffer(NUM);

        // Creazione e avvio dei thread lettori e scrittori
        for (int i = 0; i < NUM; i++) {
        	
        	 Lettore lettore = new Lettore(buffer);
             lettore.start();
             
             Scrittore scrittore = new Scrittore(buffer);
             scrittore.start();
            
        }
    }
}
