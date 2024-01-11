package threads;
import gestionerisorsa.Buffer;

public class MainLettoriScrittori {
	public static void main(String[] args) {
		
		final int NUM = 5;
        Buffer buffer = new Buffer(NUM);

        for (int i = 0; i < NUM; i++) {
        	
        	 Scrittore scrittore = new Scrittore(buffer);
             scrittore.start();
             
        	 Lettore lettore = new Lettore(buffer);
             lettore.start();          
        }
    }
}
