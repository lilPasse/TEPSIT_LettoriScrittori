package threads;

import gestionerisorsa.Buffer;

public class Lettore extends Thread {
    private Buffer buffer;

    public Lettore(Buffer buffer) {
        this.buffer = buffer;
    }

   
    
    @Override
    public void run() {
    	for(int i = 0; i<5; i++) {
    		// Inizio lettura dei dati condivisi (su buffer)
        	buffer.lettura(this.getId());
            System.out.println("Scrittura dati condivisi");
            // Concludi lettura dei dati condivisi (su buffer) 
            //buffer.stopLettura(); 
            //accedi = true;
    	}    	    	
   
    }
    
}

