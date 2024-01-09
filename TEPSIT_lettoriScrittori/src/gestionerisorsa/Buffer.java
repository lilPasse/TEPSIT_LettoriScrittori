package gestionerisorsa;
import java.util.concurrent.Semaphore;
//OFFICIAL

//mutua wesclusione su variabili globali

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
		this.msg = " ";
		this.nLettori= num;
		this.lettoriAmmessi= 0;
		this.lettoriInAttesa = 0;
		this.scrittoriAmmessi = 0;
		this.scrittoriInAttesa = 0;
		this.cont = 0;
	}
	
	//semaforo mutex sulla risorsa
    private Semaphore mutex = new Semaphore(1);
    private Semaphore sLettori = new Semaphore(nLettori); //rivedere
    private Semaphore sScrittori = new Semaphore(1);			
    
    //synchornized perche devono essere usati in maniera mutualmente esclusiva
	public String getMsg() {
		return msg;		
	}

	public synchronized void setMsg(String msg) {
		this.msg = msg;
	}

	
	public void lettura(long id) throws InterruptedException {
		
		mutex.acquire();
		accedi = false;
		
		if(scrittoriInAttesa == 0 && scrittoriAmmessi == 0) {
			lettoriAmmessi ++;
			accedi = true;
			System.out.flush();
	    	System.out.println("L:\t lettori ammessi:\t " + lettoriAmmessi);
		}else {
			lettoriInAttesa++;
			System.out.flush();
	    	System.out.println("L:\t lettori in attesa:\t " + lettoriInAttesa);
		}
		
		mutex.release();
		
		if(!accedi) {
			sLettori.acquire();
		}
				
		//accesso in lettura
		System.out.flush();
        System.out.println("Lettore\t " + id + " sta leggendo\t " + this.getMsg());
		
		//rilascio
		mutex.acquire();
		lettoriAmmessi--;
		System.out.flush();
    	System.out.println("L:\t lettori ammessi:\t " + lettoriAmmessi);
		
		if(lettoriAmmessi == 0&& scrittoriInAttesa > 0) {
			scrittoriAmmessi = 1;
			scrittoriInAttesa --;
			sScrittori.release();
			System.out.flush();
	    	System.out.println("L:\t lettori in attesa:\t " + lettoriInAttesa);
		}
		
		mutex.release();
		
	}
	
	public synchronized void scrittura(long id) throws InterruptedException {
		
		mutex.acquire();
		accedi = false;
		
		if(lettoriAmmessi == 0 && scrittoriInAttesa == 0) {
			scrittoriAmmessi = 1;
			accedi = true;
			System.out.flush();
			System.out.println("S:\t scrittori ammessi:\t " + scrittoriAmmessi);
			
		}else {
			scrittoriInAttesa ++;
			System.out.flush();
	    	System.out.println("S:\t scrittori in attesa:\t " + scrittoriInAttesa);
		}
		
		mutex.release();
		
		if(!accedi) {
			sScrittori.acquire();
		}
		
		//accesso in scrittura
		cont++;
		this.setMsg("mess-" + String.valueOf(cont));
	    System.out.flush();
	    System.out.println("Scrittore " + id +" sta scrivendo\t " + this.getMsg());
		
		//rilascio
	    mutex.acquire();
		scrittoriAmmessi = 0;		

		if(lettoriInAttesa > 0) {
			lettoriAmmessi = lettoriInAttesa;
			lettoriInAttesa = 0;
			sLettori.release();			
			
		}else if(scrittoriInAttesa > 0) {
			scrittoriAmmessi ++;
			scrittoriInAttesa --;
			sScrittori.release();
			System.out.flush();
			System.out.println("S:\t scrittori ammessi:\t " + scrittoriAmmessi);
	    	System.out.println("S:\t scrittori in attesa:\t " + scrittoriInAttesa);
		}
		
		mutex.release();
	}
	
	
};

