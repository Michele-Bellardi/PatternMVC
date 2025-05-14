public class Record {
    public String Matricola;
    public String Cognome;
    public String Nome;
    public String Citta;
    public String Indirizzo;
    public static final int L = 100;

    public static String normalizza(String s) {
        if (s == null) s = "";
        int i = s.length();
        for (; i < 20; i++) s = s + " ";
        return s;
    }

    public Record() {
        Matricola = normalizza(Matricola);
        Cognome = normalizza(Cognome);
        Nome = normalizza(Nome);
        Citta = normalizza(Citta);
        Indirizzo = normalizza(Indirizzo);
    }

    public Record(String matricola, String cognome, String nome, String citta, String indirizzo) {
        Matricola = normalizza(matricola);
        Cognome = normalizza(cognome);
        Nome = normalizza(nome);
        Citta = normalizza(citta);
        Indirizzo = normalizza(indirizzo);
    }

    public String toString() {
        return Matricola + Cognome + Nome + Citta + Indirizzo;
    }

    public String getMatricola() {
        return Matricola.trim();
    }

    public String getCognome() {
        return Cognome.trim();
    }

    public String getNome() {
        return Nome.trim();
    }

    public String getCitta() {
        return Citta.trim();
    }

    public String getIndirizzo() {
        return Indirizzo.trim();
    }
}