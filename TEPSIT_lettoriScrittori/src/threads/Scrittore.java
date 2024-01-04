package threads;

import gestionerisorsa.Buffer;

public class Scrittore extends Thread {
    private Buffer buffer;
    private Boolean accedi;

    public Scrittore(Buffer buffer) {
        this.buffer = buffer;
        this.accedi = false;

    }

    public synchronized void scrivi() {
    	/*
    	 *  acquire mutex 
    	 * if(lettoriAmmessi == 0 && scrittoriAmmessi == 0){
    	 * 		ScrittoriAmmessi = 1;
    	 * 		accedi = true; (release mutex)
    	 * }else{
    	 * 		scrittoriInAttesa +++;
    	 * }
    	 * 
    	 * while(!accedi){ this.wait();}
    	 * 
    	 * si entra in scrittura
    	 * 
    	 */
    };
    
    public synchronized void rilascia() {
    	/*
    	 *  acquire mutex
    	 * scrittoriAmmessi = 0;
    	 * if(lettoriInAttesa > 0 ){
    	 * 		notifyAll();
    	 * 		lettoriAmmessi = lettoriInAttesa;
    	 * 		lettoriInAttesa = 0;
    	 * 		(release mutex)?
    	 * }else if(scrittoriInAttesa > 0){
    	 * 		scrittoriAmmessi ++;
    	 * 		scrittoriInAttesa --;
    	 * 		notify();
    	 * }
    	 */
    };
    
    @Override
    public void run() {
    	while(true) {
    		// Inizio scrittura dei dati condivisi (su buffer)
    		scrivi();
            System.out.println("Scrittura dati condivisi");
            // Concludi scrittura dei dati condivisi (su buffer) 
            rilascia();
            //accedi = true;
    	}  
    	
    	/*matteo
		 * scrivi();
		 * //acquisco buffer 
		 * 
		 * //scrivo msg
		 * 
		 * //rilascio buffer
		 * 
		 * 
		 * rilascia();
		 */
    }
}
