package threads;

import gestionerisorsa.Buffer;

public class Scrittore extends Thread {
    private Buffer buffer;

    public Scrittore(Buffer buffer) {
        this.buffer = buffer;

    }

    
    @Override
    public void run() {
    	for(int i = 0; i<5; i++){
    		// Inizio scrittura dei dati condivisi (su buffer)
    	
    		buffer.scrittura(this.getId());
            System.out.println("Scrittura dati condivisi");
            // Concludi scrittura dei dati condivisi (su buffer) 
            //buffer.stopScrittura();
            //accedi = true;
            //accedi=true;
    	}  
    	
    }
}
