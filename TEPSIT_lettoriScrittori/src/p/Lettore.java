package p;
import java.util.concurrent.Semaphore;
public class Lettore extends Thread {

	private Buffer buffer;
	private Semaphore  mutex, sLettori, sScrittori; 
	private String msg;
	private Boolean accedi;

	
	public Lettore(Buffer b, Semaphore mutex, Semaphore sLettori, Semaphore sScrittori){
		this.buffer = b;	
		this.mutex = mutex;
		this.sLettori = sLettori;
		this.sScrittori = sScrittori;
		this.accedi = false;
	}
	
	public void run(){
		for(int i= 0; i<5; i++) {
			
			try {
				this.leggi(this.getId());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public synchronized void leggi(long id) throws InterruptedException{
		
		this.mutex.acquire();
		
		if(p.Accessi.scrittoriInAttesa == 0 && p.Accessi.scrittoriAmmessi == 0) {
			p.Accessi.lettoriAmmessi ++;
			accedi = true;			
		}else {
			p.Accessi.lettoriInAttesa ++;
		}
		
		if(!accedi) {
			this.sLettori.acquire();
		}
		
		System.out.flush();
        System.out.println("Lettore " + id + " sta leggendo " + buffer.getMsg());
	    
        //rilascio
        
        this.mutex.release();
		this.mutex.acquire();

        p.Accessi.lettoriAmmessi --;
        if(p.Accessi.lettoriAmmessi == 0 && p.Accessi.scrittoriInAttesa > 0) {
        	p.Accessi.scrittoriAmmessi  = 1;
        	p.Accessi.scrittoriInAttesa--;
        	this.sScrittori.release();
        }
        
        this.mutex.release();

        	
        
	}
}
