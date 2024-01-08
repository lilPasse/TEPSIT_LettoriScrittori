package p;

import java.util.concurrent.Semaphore;

public class Scrittore extends Thread{
	private Buffer buffer;
	private Semaphore  mutex, sLettori, sScrittori; 
	private String msg;
	private int cont;
	private Boolean accedi;

	
	public Scrittore(Buffer b, Semaphore mutex, Semaphore sLettori, Semaphore sScrittori){
		this.buffer = b;	
		this.mutex = mutex;
		this.sLettori = sLettori;
		this.sScrittori = sScrittori;
		this.cont = 0;
		this.accedi = false;
	}
	
	public void run(){
		for(int i= 0; i<5; i++) {
			
			try {
				this.scrivi(this.getId());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public synchronized void scrivi(long id) throws InterruptedException{
		
		this.mutex.acquire();
		
		if(p.Accessi.lettoriAmmessi == 0 && p.Accessi.scrittoriAmmessi == 0) {
			p.Accessi.scrittoriAmmessi = 1;
			accedi = true;			
		}else {
			p.Accessi.scrittoriInAttesa ++;
		}
		
		if(!accedi) {
			this.sScrittori.acquire();
		}
		
		cont++;
	    buffer.setMsg("mess-" + String.valueOf(cont));
        System.out.flush();
        System.out.println("Scrittore " + id +" sta scrivendo " + buffer.getMsg());
	    
        //rilascio
        
        this.mutex.release();
        this.mutex.acquire();

        p.Accessi.scrittoriAmmessi = 0;
        if(p.Accessi.lettoriInAttesa == 0 ) {
        	this.sLettori.release();
        	p.Accessi.lettoriAmmessi = p.Accessi.lettoriInAttesa;
        	p.Accessi.lettoriInAttesa = 0;
        	
        }else if(p.Accessi.scrittoriInAttesa <0) {
        	p.Accessi.scrittoriAmmessi ++;
        	p.Accessi.scrittoriInAttesa--;
        	this.sScrittori.release();
        }
        
        this.mutex.release();

        	
        
	}
}
