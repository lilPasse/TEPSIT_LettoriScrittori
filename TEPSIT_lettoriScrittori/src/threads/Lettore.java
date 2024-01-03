package threads;
import gestionerisorsa.Buffer;

public class Lettore extends Thread {
    private Buffer buffer;

    public Lettore(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
    	// Inizio lettura dei dati condivisi (su buffer)
        System.out.println("Scrittura dati condivisi");
        // Concludi lettura dei dati condivisi (su buffer)
    }
}
