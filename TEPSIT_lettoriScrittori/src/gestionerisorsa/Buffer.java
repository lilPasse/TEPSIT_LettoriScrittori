package gestionerisorsa;

//da rifinire
public class Buffer {
	
	//se sono
	private String msg;
	
	/*
	 * dato che devono essere vriabili condivise, le seguenti variabili sono
	 * qua all'interno della classe buffer  per tenere traccia del suo stato 
	 * condivisov e quindi delle attivita in corso.
	*/
	
	 private static int lettoriAmmessi;
	 private static int lettoriInAttesa;
	 private static int scrittoriAmmessi;
	 private static int scrittoriInAttesa;
	 private static Boolean accedi;
	 	    
	public Buffer() {
		this.lettoriAmmessi= 0;
		this.lettoriInAttesa = 0;
		this.scrittoriAmmessi = 0;
		this.scrittoriInAttesa = 0;
	}
	
	//semaforo mutex sulla risorsa
    private Semaforo mutex = new Semaforo(1);
    private Semaforo sLettori = new Semaforo(1);
    private Semaforo sScrittori = new Semaforo(1);
    	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	 public void lettura() {
	    	
	    	 mutex.acquire();
	    	 sLettori.acquire();
	    	 
	    	 if(scrittoriInAttesa == 0 && scrittoriAmmessi == 0){
	    	  		lettoriAmmessi++;
	    	  		accedi = true; 
	    	  		
	    	 }else{
	    	 		lettoriInAttesa ++;
	    	 }	   
	    	 
	    	 sLettori.release();
	    	 mutex.release();
	    	 
	    	 if(!accedi){ 			//provare if
	    		sLettori.acquire();
	    	 }	 
	    	 
	 };
	    
	    public void stopLettura() {
	    	
	    	mutex.acquire();
	    	
	    	lettoriAmmessi--;
	    	if(lettoriAmmessi == 0 && scrittoriInAttesa > 0){
	    	  	scrittoriAmmessi = 1;
	    	 	scrittoriInAttesa --;
	    	  	sScrittori.release();
	    	 
	    	//se nessuno sta usando la risorsa/nessuno è in coda  	
	    	}else if(lettoriAmmessi == 0 && scrittoriInAttesa == 0){
	    		sScrittori.release();
	    		sLettori.release();
	    	}

	    	mutex.release();
    		accedi = false;
	    };
	    
	    public synchronized void scrittura() {
	    	
	    	  mutex.acquire();
	    	  accedi = false;
	    	  if(lettoriAmmessi == 0 && scrittoriAmmessi == 0){
	    	  		scrittoriAmmessi = 1;
	    	  		accedi = true; 
	    	  }else{
	    	  		scrittoriInAttesa ++;
	    	  }
		      mutex.release();
   	  
	    	  if(!accedi){		//provare if
	    		  sScrittori.acquire();
	    	  }
	    	  
	    	//si entra in scrittura
	 
	    };
	    
	    public synchronized void stopScrittura() {
	    	
	    	 mutex.acquire();
	    	 
	    	 scrittoriAmmessi = 0;
	    	 if(lettoriInAttesa > 0 ){
	    		 sLettori.release();	    	  		
	    		 lettoriAmmessi = lettoriInAttesa;
	    	  	 lettoriInAttesa = 0;
	    	  	 
	    	 }else if(scrittoriInAttesa > 0){
	    	  		scrittoriAmmessi ++;
	    	  		scrittoriInAttesa --;
	    	  		sLettori.release();
	    	 }
	    	 
	    	 mutex.release();
	    	 accedi = false;
	    	
	    };
	    

}
