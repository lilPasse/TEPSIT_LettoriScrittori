package threads;

import gestionerisorsa.Buffer;

public class Lettore extends Thread {
    private Buffer buffer;

    public Lettore(Buffer buffer) {
        this.buffer = buffer;
    }

   
    
    @Override
    public void run() {
    	while(true) {
    		// Inizio lettura dei dati condivisi (su buffer)
        	buffer.lettura();
            System.out.println("Scrittura dati condivisi");
            // Concludi lettura dei dati condivisi (su buffer) 
            buffer.stopLettura(); 
            //accedi = true;
    	}    	    	
   
    }
    
}

