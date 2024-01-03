package threads;

import gestionerisorsa.Buffer;

public class Scrittore extends Thread {
    private Buffer buffer;

    public Scrittore(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        // Inizio scrittura dei dati condivisi (su buffer)
        System.out.println("Scrittura dati condivisi");
        // Concludi scrittura dei dati condivisi (su buffer)         
    }
}
