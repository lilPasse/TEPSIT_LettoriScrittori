package threads;

import gestionerisorsa.Buffer;

public class Lettore extends Thread {
    private Buffer buffer;
    private Boolean accedi;
    private int lettoriAmmessi=0;
    private int lettoriInAttesa=0;

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
    	/* gaia 
    	 * while(true) {
    		// Inizio lettura dei dati condivisi (su buffer)
        	leggi();
            System.out.println("Scrittura dati condivisi");
            // Concludi lettura dei dati condivisi (su buffer) 
            rilascia(); 
            //accedi = true;
    	}*/ 
    	
    	
    	
    /* matteo	
     *
    	//acquisire mutex x modifica lettoriammessi
    	accedi=false; 
    	if(scrittoriInAttesa==0 && scrittoriAmmessi==0) {
    		lettoriAmmessi++;
    		accedi=true;
    	}else {
    		lettoriInAttesa++;
    	}
    	if(!accedi) { //se è uguale a 0
    		this.wait();
    	}
    	//acquisire buffer
		
		// rilasciare mutex x modifica lettoriammessi 
		
		//fare operazioni di lettura 
		
		//acquisisco mutex x modifica lettoriammessi
    	
    	lettoriAmmessi--;
    	if(lettoriAmmessi == 0 && scrittoriInAttesa > 0){
    		//rilascio buffer
    		scrittoriAmmessi = 1;
       	 	scrittoriInAttesa --;
       	 	notify();
       	 	//(release mutex x modifica lettoriammessi)
       	 }else if(lettoriAmmessi == 0 && scrittoriInAttesa == 0){
       		 CASO 1
       		 accedi = false;
       	 }
    
    */
    	
    }
}

