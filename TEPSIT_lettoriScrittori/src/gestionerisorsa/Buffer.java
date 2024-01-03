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
	
	 private int lettoriAmmessi;
	 private int lettoriInAttesa;
	 private int scrittoriAmmessi;
	 private int scrittoriInAttesa;
	    
	public Buffer() {
		this.lettoriAmmessi= 0;
		this.lettoriInAttesa = 0;
		this.scrittoriAmmessi = 0;
		this.scrittoriInAttesa = 0;
	}
	
	//semaforo mutex sulla risorsa
    private Semaforo mutex = new Semaforo(1);
    
	//due semafori (1per lettori, 1 per scrittori)--> manca tutta la gestione negli altri codici
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getLettoriAmmessi() {
		return lettoriAmmessi;
	}

	public void setLettoriAmmessi(int lettoriAmmessi) {
		this.lettoriAmmessi = lettoriAmmessi;
	}

	public int getLettoriInAttesa() {
		return lettoriInAttesa;
	}

	public void setLettoriInAttesa(int lettoriInAttesa) {
		this.lettoriInAttesa = lettoriInAttesa;
	}

	public int getScrittoriAmmessi() {
		return scrittoriAmmessi;
	}

	public void setScrittoriAmmessi(int scrittoriAmmessi) {
		this.scrittoriAmmessi = scrittoriAmmessi;
	}

	public int getScrittoriInAttesa() {
		return scrittoriInAttesa;
	}

	public void setScrittoriInAttesa(int scrittoriInAttesa) {
		this.scrittoriInAttesa = scrittoriInAttesa;
	}

}
