package p;

import java.util.concurrent.Semaphore;

public class MainP {
public static void main(String[] args) {
		
		final int NUM = 5;
        Buffer buffer = new Buffer();

        
         Semaphore mutex = new Semaphore(1);
         Semaphore sLettori = new Semaphore(NUM);
         Semaphore sScrittori = new Semaphore(1);			//mh
        
        
        // Creazione e avvio dei thread lettori e scrittori
        for (int i = 0; i < NUM; i++) {
        	 
            Scrittore scrittore = new Scrittore(buffer, mutex, sLettori, sScrittori);
            scrittore.start();
            
        	 Lettore lettore = new Lettore(buffer, mutex, sLettori, sScrittori);
             lettore.start();
            
            
        }
    }
}



