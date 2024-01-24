
/*package gestionerisorsa;

import java.util.concurrent.Semaphore;

public class rbacheserveperpresentazione {
	private String msg;
	private int cont;
	
	/*
	 * dato che devono essere vriabili condivise, le seguenti variabili sono
	 * qua all'interno della classe buffer  per tenere traccia del suo stato 
	 * condivisov e quindi delle attivita in corso.
	
	
	
	 private int nLettori;
	 
	 private Boolean accedi;
	 private int lettoriAmmessi;
	 private int lettoriInAttesa;
	 private int scrittoriAmmessi;
	 private int scrittoriInAttesa;
	 
	 	    
	public Buffer(int num) {
		
		this.msg = " ";
		this.nLettori= num;
		this.cont = 0;
		
		this.lettoriAmmessi= 0;
		this.lettoriInAttesa = 0;
		this.scrittoriAmmessi = 0;
		this.scrittoriInAttesa = 0;
		
	}
	
	
    private Semaphore mutex = new Semaphore(1);
    private Semaphore sLettori = new Semaphore(nLettori); 
    private Semaphore sScrittori = new Semaphore(1);			
    
    //synchornized perche devono essere usati in maniera mutualmente esclusiva
	public String getMsg() {
		return msg;		
	}

	public synchronized void setMsg(String msg) {
		this.msg = msg;
	}

	
	public  void lettura() throws InterruptedException {
		
		mutex.acquire();
		accedi = false;
		// Controlli tramite variabili condivise 		
		mutex.release();

		if (!accedi) {
		    sLettori.acquire();
		}

		//Accesso in lettura

		mutex.acquire();
		//Operazioni di rilascio e modifica variabili condivise
		mutex.release();		
	}
	
	public synchronized void scrittura() throws InterruptedException {
		
		mutex.acquire();
		accedi = false;
		//Controlli tramite variabili condivise 	
		mutex.release();

		if (!accedi) {
		    sScrittori.acquire();
		}

		//Accesso in scrittura

		mutex.acquire();
		//Operazioni di rilascio e modifica variabili condivise		
		mutex.release();
	}
	
}

*/