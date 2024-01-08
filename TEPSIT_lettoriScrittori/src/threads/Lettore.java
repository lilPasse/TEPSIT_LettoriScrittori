package threads;

import gestionerisorsa.Buffer;

public class Lettore extends Thread {
    private Buffer buffer;

    public Lettore(Buffer buffer) {
        this.buffer = buffer;
    }

   
    
    @Override
    public void run() {
    	for(int i = 0; i<3; i++) {
    		
        	buffer.lettura(this.getId());
        	 try {
 				this.sleep(500);
 			} catch (InterruptedException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
    	}    	    	
   
    }
    
}

