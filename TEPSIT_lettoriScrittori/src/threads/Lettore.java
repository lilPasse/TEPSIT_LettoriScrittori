package threads;

import gestionerisorsa.Buffer;

public class Lettore extends Thread {
    private Buffer buffer;
    private Boolean accedi;

    public Lettore(Buffer buffer) {
        this.buffer = buffer;
        this.accedi = false;
    }

   
    
    @Override
    public void run() {
    	while(true) {
    		// Inizio lettura dei dati condivisi (su buffer)
        	buffer.lettura(accedi);
            System.out.println("Scrittura dati condivisi");
            // Concludi lettura dei dati condivisi (su buffer) 
            buffer.stopLettura(accedi); 
            //accedi = true;
    	}    	    	
   
    }
    
}

