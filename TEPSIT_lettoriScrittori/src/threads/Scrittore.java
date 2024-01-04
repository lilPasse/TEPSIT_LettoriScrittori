package threads;

import gestionerisorsa.Buffer;

public class Scrittore extends Thread {
    private Buffer buffer;
    private Boolean accedi;

    public Scrittore(Buffer buffer) {
        this.buffer = buffer;
        this.accedi = false;

    }

    
    @Override
    public void run() {
    	while(true) {
    		// Inizio scrittura dei dati condivisi (su buffer)
    		buffer.scrittura(accedi);
            System.out.println("Scrittura dati condivisi");
            // Concludi scrittura dei dati condivisi (su buffer) 
            buffer.stopScrittura(accedi);
            //accedi = true;
    	}  
    	
    }
}
