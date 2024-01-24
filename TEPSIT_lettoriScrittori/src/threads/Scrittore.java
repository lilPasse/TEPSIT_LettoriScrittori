
package threads;
import gestionerisorsa.Buffer;

public class Scrittore extends Thread {
    private Buffer buffer;
    
    public Scrittore(Buffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
    	//while(true)
    	for(int i = 0; i<3; i++){
    	
    		try {
				buffer.scrittura(this.getId());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
            try {
				this.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}  
    }
}

