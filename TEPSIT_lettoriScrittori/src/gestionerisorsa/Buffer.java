
package gestionerisorsa;
import java.util.concurrent.Semaphore;

public class Buffer {
	
	private String msg;
	private int cont;
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
    
	public String getMsg() { return msg;		}

	public synchronized void setMsg(String msg) { this.msg = msg;}

	public void lettura(long id) throws InterruptedException {
		
		mutex.acquire();
		accedi = false;
		
		if(scrittoriInAttesa == 0 && scrittoriAmmessi == 0) {
			lettoriAmmessi ++;
			accedi = true;
	    //	System.out.println("L.if:\t " + cont + "\tlettori ammessi:\t " + lettoriAmmessi);
		}else {
			lettoriInAttesa++;
	    //	System.out.println("L.else:\t" + cont + "\tlettori in attesa:\t " + lettoriInAttesa);
		}
		
		mutex.release();		
		
		if(!accedi) {
			sLettori.acquire();
		}
		
		System.out.flush();
        System.out.println("Lettore " + id + " sta leggendo " + this.getMsg());
		
		mutex.acquire();
		lettoriAmmessi--;
		
		if(lettoriAmmessi == 0 && scrittoriInAttesa > 0) {
			scrittoriAmmessi = 1;
			scrittoriInAttesa --;
			sScrittori.release();
	    //	System.out.println("L.if2:\t" + cont + "\tlettori in attesa:\t " + lettoriInAttesa);
		}
		mutex.release();		
	}
	
	public synchronized void scrittura(long id) throws InterruptedException {
		
		mutex.acquire();
		accedi = false;
		
		if(lettoriAmmessi == 0 && scrittoriAmmessi == 0) {
			scrittoriAmmessi = 1;
			accedi = true;
			//System.out.println("S.if:\t" + cont + "\tscrittori ammessi:\t " + scrittoriAmmessi);
			
		}else {
			scrittoriInAttesa ++;
	    	//System.out.println("S.else:\t"+ cont + "\tscrittori in attesa:\t " + scrittoriInAttesa);
		}
		
		mutex.release();
		
		if(!accedi) {
			sScrittori.acquire();
		}
		
		cont++;
		this.setMsg("mess-" + String.valueOf(cont));
	    System.out.flush();
	    System.out.println("Scrittore: " + id + " sta scrivendo " + this.getMsg());
		
	    mutex.acquire();
		scrittoriAmmessi = 0;	

		if(lettoriInAttesa > 0) {
			lettoriAmmessi = lettoriInAttesa;
			lettoriInAttesa = 0;
			sLettori.release(lettoriAmmessi);			
			
		}else if(scrittoriInAttesa > 0) {
			scrittoriAmmessi ++;
			scrittoriInAttesa --;
						
			sScrittori.release();
		//	System.out.println("S.eif:\t" + cont + "\tscrittori ammessi:\t" + scrittoriAmmessi);
	    //	System.out.println("S.eif:\t" + cont + "\tscrittori in attesa:\t " + scrittoriInAttesa);
		}
		mutex.release();
	}	
	
}


