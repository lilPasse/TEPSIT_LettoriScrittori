package mainPackage;

import gestionerisorsa.Buffer;
import threads.Lettore;
import threads.Scrittore;

public class Main {
	public static void main(String[] args) {
        Buffer buffer = new Buffer();

        // Creazione e avvio dei thread lettori e scrittori
        for (int i = 0; i < 5; i++) {
            new Lettore(buffer).start();
            new Scrittore(buffer).start();
        }
    }
}
