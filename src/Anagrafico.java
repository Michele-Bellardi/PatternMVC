import java.io.*;

public class Anagrafico {
    private RandomAccessFile dati;

    public Anagrafico() {
        apriFile();
    }

    public void apriFile() {
        try {
            dati = new RandomAccessFile("./dati.dat", "rw");
        } catch (IOException e) {
            System.err.println("Errore nell'apertura del file: " + e.getMessage());
        }
    }

    public void chiudiFile() {
        try {
            if (dati != null) {
                dati.close();
            }
        } catch (IOException e) {
            System.err.println("Errore nella chiusura del file: " + e.getMessage());
        }
    }

    public void aggiungi(String matricola, String cognome, String nome, String citta, String indirizzo) {
        try {
            Record r = new Record(matricola, cognome, nome, citta, indirizzo);
            String s = r.toString();
            byte[] b = s.getBytes();
            dati.seek(dati.length());
            dati.write(b);
        } catch (IOException e) {
            System.err.println("Errore nell'aggiunta del record: " + e.getMessage());
        }
    }

    public Record recPosizione(int pos) {
        try {
            byte[] b = new byte[Record.L];
            dati.seek(pos);
            dati.read(b);
            String s = new String(b);
            return new Record(
                    s.substring(0, 20),
                    s.substring(20, 40),
                    s.substring(40, 60),
                    s.substring(60, 80),
                    s.substring(80, 100)
            );
        } catch (IOException e) {
            System.err.println("Errore nella lettura del record: " + e.getMessage());
            return null;
        }
    }

    public void vediTutti() {
        try {
            byte[] b = new byte[Record.L];
            dati.seek(0);
            int ok = dati.read(b);
            while (ok > 0) {
                String s = new String(b);
                System.out.println(s);
                ok = dati.read(b);
            }
        } catch (IOException e) {
            System.err.println("Errore nella lettura dei record: " + e.getMessage());
        }
    }

    public int cercaMatricola(String mat) {
        try {
            byte[] b = new byte[20];
            int pos = 0;
            dati.seek(pos);
            int ok = dati.read(b);
            while (ok > 0) {
                String s = new String(b);
                s = s.trim();
                if (s.equals(mat)) return pos;
                pos += Record.L;
                dati.seek(pos);
                ok = dati.read(b);
            }
        } catch (IOException e) {
            System.err.println("Errore nella ricerca della matricola: " + e.getMessage());
        }
        return -1;
    }

    public void modifica(int pos, String citta, String indirizzo) {
        try {
            citta = Record.normalizza(citta);
            indirizzo = Record.normalizza(indirizzo);
            String s = citta + indirizzo;
            byte[] b = s.getBytes();
            dati.seek(pos + 60);
            dati.write(b);
        } catch (IOException e) {
            System.err.println("Errore nella modifica del record: " + e.getMessage());
        }
    }

    public void reInizializzaFile() {
        String[][] vet = {
                {"AZ120", "Rozzi", "Giancarlo", "Castelfranco", "Lungo Brenta 3"},
                {"AZ122", "Rotti", "Gianluca", "Castelbardo", "Lungo Piave 5"},
                {"AZ124", "Rizzi", "Gianluigi", "Castellibero", "Lungo Po 7"},
                {"AZ125", "Ruzza", "Gianmaria", "Castelsicuro", "Lungo Sile 8"},
                {"AZ126", "Razzi", "Gianandrea", "Castelvecchio", "Lungo Adige 9"}
        };

        try {
            chiudiFile();
            File f = new File("./dati.dat");
            f.delete();
            apriFile();

            for (int i = 0; i < 5; i++) {
                Record r = new Record(vet[i][0], vet[i][1], vet[i][2], vet[i][3], vet[i][4]);
                String s = r.toString();
                byte[] b = s.getBytes();
                dati.seek(dati.length());
                dati.write(b);
            }
        } catch (IOException e) {
            System.err.println("Errore nella reinizializzazione del file: " + e.getMessage());
        }
    }

    public boolean cancella(int pos) {
        try {
            // Sovrascriviamo il record con spazi (soft delete)
            dati.seek(pos);
            byte[] b = new byte[Record.L];
            for (int i = 0; i < b.length; i++) {
                b[i] = ' ';
            }
            dati.write(b);
            return true;
        } catch (IOException e) {
            System.err.println("Errore nella cancellazione del record: " + e.getMessage());
            return false;
        }
    }
}