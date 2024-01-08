package gestionerisorsa;
//MIO

//da rifinire
public class Buffer {
	
	private String msg;
	private int cont;
	
	/*
	 * dato che devono essere vriabili condivise, le seguenti variabili sono
	 * qua all'interno della classe buffer  per tenere traccia del suo stato 
	 * condivisov e quindi delle attivita in corso.
	*/
	
	 private int lettoriAmmessi;
	 private int lettoriInAttesa;
	 private int scrittoriAmmessi;
	 private int scrittoriInAttesa;
	 private Boolean accedi;
	 private int nLettori;
	 	    
	public Buffer(int num) {
		this.nLettori= num;
		this.lettoriAmmessi= 0;
		this.lettoriInAttesa = 0;
		this.scrittoriAmmessi = 0;
		this.scrittoriInAttesa = 0;
		this.accedi = false;
		this.cont = 0;
	}
	
	//semaforo mutex sulla risorsa
    private Semaforo mutex = new Semaforo();
    private Semaforo sLettori = new Semaforo(nLettori);
    private Semaforo sScrittori = new Semaforo();			//mh
    
    
    //synchornized perche devono essere usati in maniera mutualmente esclusiva
	public synchronized String getMsg() {
		return msg;		
	}

	public synchronized void setMsg(String msg) {
		msg = msg;
	}

	public synchronized void lettura(long id){
		
		System.out.println("metodo lettura");
		
		accedi = false;
	    mutex.acquire();
 	 
	    //se l'accesso è sicuramente libero, allora accedi
	    if(scrittoriInAttesa == 0 && scrittoriAmmessi == 0){
	    	lettoriAmmessi++;
	    	accedi = true;
	    	
	    //altrimenti attendi	
	    }else{
	    	lettoriInAttesa ++;
	    	//sLettori.acquire();
	    }	   
	    	 
	    mutex.release();
	    sLettori.release();
	    
	    //se l'accesso non è stato fatto, sospendi
	    if(!accedi){ 			
	    	sLettori.acquire();
	    }
	    
	    //accesso in lettura
	    System.out.flush();
        System.out.println("Lettore " + id + " sta leggendo " + this.getMsg());
	    	 
        
        mutex.acquire();	    	
	    lettoriAmmessi--;
	    
	    //se non la stanno usando i lettori e ci sono scrittori in attesa
	    //fai accedere scrittori
	    if(lettoriAmmessi == 0 && scrittoriInAttesa > 0){
	    	scrittoriAmmessi = 1;
	    	scrittoriInAttesa --;
	    	sScrittori.release();
	    	 
	    //se nessuno sta usando la risorsa/nessuno è in coda 
	    //risveglia tutti
	    }else if(lettoriAmmessi == 0 && scrittoriInAttesa == 0){
	    	sScrittori.release();
	    	sLettori.release();
	    }

	    mutex.release();
	 };
	    
	 /*public synchronized void stopLettura() {
	    	
	    System.out.println("metodo stop lettura");
	    	
	    mutex.acquire();	    	
	    lettoriAmmessi--;
	    
	    //se non la stanno usando i lettori e ci sono scrittori in attesa
	    //fai accedere scrittori
	    if(lettoriAmmessi == 0 && scrittoriInAttesa > 0){
	    	scrittoriAmmessi = 1;
	    	scrittoriInAttesa --;
	    	sScrittori.release();
	    	 
	    //se nessuno sta usando la risorsa/nessuno è in coda 
	    //risveglia tutti
	    }else if(lettoriAmmessi == 0 && scrittoriInAttesa == 0){
	    	sScrittori.release();
	    	sLettori.release();
	    }

	    mutex.release();
	 };*/
	    
	    
	public synchronized void scrittura(long id) {
		  
		System.out.println("metodo scrittura");
		  
		accedi = false;	    	
	   	mutex.acquire();
	    	
	   	//se nessuno sta usando la risorsa, fai accedere uno scrittore
	    if(lettoriAmmessi == 0 && scrittoriAmmessi == 0){
	    	scrittoriAmmessi = 1;
	    	accedi = true; 
	    	
	    //atrimenti attendi	
	    }else{
	    	scrittoriInAttesa ++;
	    	//sScrittori.acquire();
	    }
		
	    mutex.release();
   	  
	  //se l'accesso non è stato fatto, sospendi
	    if(!accedi){		
	    	sScrittori.acquire();
	    }
	    
	    //si entra in scrittura
	    cont++;
	    this.setMsg("mess-" + String.valueOf(cont));
        System.out.flush();
        System.out.println("Scrittore " + id +" sta scrivendo " + this.getMsg());
        
        mutex.acquire();
	    scrittoriAmmessi = 0;
	    
	    //se ci sono dei lettori in attesa, risvegliali tutti
    	if(lettoriInAttesa > 0 ){
	    	sLettori.release();	    	  		
	    	lettoriAmmessi = lettoriInAttesa;
	    	lettoriInAttesa = 0;
	    
	    	//altrimenti se ci sono scrittori in attesa, risvegliane uno
	    }else if(scrittoriInAttesa > 0){
	    	scrittoriAmmessi ++;
	    	scrittoriInAttesa --;
	    	sLettori.release();
	    }
	    	 
	    mutex.release();
 
	};
	    
	/*public synchronized void stopScrittura() {
		
		System.out.println("metodo stop scrittura");
	    	
	    mutex.acquire();
	    scrittoriAmmessi = 0;
	    
	    //se ci sono dei lettori in attesa, risvegliali tutti
    	if(lettoriInAttesa > 0 ){
	    	sLettori.release();	    	  		
	    	lettoriAmmessi = lettoriInAttesa;
	    	lettoriInAttesa = 0;
	    
	    	//altrimenti se ci sono scrittori in attesa, risvegliane uno
	    }else if(scrittoriInAttesa > 0){
	    	scrittoriAmmessi ++;
	    	scrittoriInAttesa --;
	    	sLettori.release();
	    }
	    	 
	    mutex.release();	    	
	};*/

}
