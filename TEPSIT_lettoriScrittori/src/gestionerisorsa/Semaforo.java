package gestionerisorsa;

//da rifinire
public class Semaforo {
	
	private int molteplicita;

	public Semaforo() {}
	public Semaforo(int n) {
		this.molteplicita= n ;
	}
	
	public synchronized void acquire() {
	     while (molteplicita != 0) {		
	        try {
	            wait();
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	     }
	     molteplicita --;
	 }

	 public synchronized void release() {	 
		molteplicita ++;;
	    notify();
	 }

}
