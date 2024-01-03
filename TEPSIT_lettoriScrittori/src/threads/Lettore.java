package threads;

import gestionerisorsa.Buffer;

public class Lettore extends Thread {
    private Buffer buffer;
    private Boolean accedi;

    public Lettore(Buffer buffer) {
        this.buffer = buffer;
        this.accedi = false;
    }

    public void leggi() {
    	/*
    	 * acquire mutex 
    	 * if(scrittoriinattesa == 0 && scrittoriAmmessi == 0){
    	 * 		lettoriAmmessi++;
    	 * 		accedi = true; (release mutex)
    	 * }else{
    	 * 		lettoriInAttesa +++;
    	 * }
    	 * 
    	 * while(!accedi){ this.wait();}
    	 * 
    	 * si entra in lettura
    	 * 
    	 */
    };
    
    public void rilascia() {
    	/*
    	 * acquire mutex
    	 * lettoriAmmessi--;
    	 * if(lettoriAmmessi == 0 && scrittoriInAttesa > 0){
    	 * 		scrittoriAmmessi = 1;
    	 * 		scrittoriInAttesa --;
    	 * 		notify();
    	 * 		(release mutex)
    	 * }else if(lettoriAmmessi == 0 && scrittoriInAttesa == 0){
    	 * 		CASO 1
    	 * 		accedi = false;
    	 * }
    	 */
    };
    
    @Override
    public void run() {
    	while(true) {
    		// Inizio lettura dei dati condivisi (su buffer)
        	leggi();
            System.out.println("Scrittura dati condivisi");
            rilascia(); 
            //accedi = true;
    	}	
    }
}

