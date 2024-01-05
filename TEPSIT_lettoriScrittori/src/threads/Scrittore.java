package threads;

import gestionerisorsa.Buffer;

public class Scrittore extends Thread {
    private Buffer buffer;

    public Scrittore(Buffer buffer) {
        this.buffer = buffer;

    }

    
    @Override
    public void run() {
    	while(true) {
    		// Inizio scrittura dei dati condivisi (su buffer)
    	
    		buffer.scrittura();
            System.out.println("Scrittura dati condivisi");
            // Concludi scrittura dei dati condivisi (su buffer) 
            buffer.stopScrittura();
            //accedi = true;
            //accedi=true;
    	}  
    	
    }
}
