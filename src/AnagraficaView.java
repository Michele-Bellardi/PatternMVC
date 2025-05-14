import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AnagraficaView extends JFrame {
    private JTextField matricolaField, cognomeField, nomeField, cittaField, indirizzoField;
    private JButton cercaBtn, aggiungiBtn, modificaBtn, cancellaBtn, resetBtn;
    private JTextArea outputArea;

    public AnagraficaView() {
        setTitle("Gestione Anagrafica");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Pannello input
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Dati Anagrafici"));

        inputPanel.add(new JLabel("Matricola:"));
        matricolaField = new JTextField();
        inputPanel.add(matricolaField);

        inputPanel.add(new JLabel("Cognome:"));
        cognomeField = new JTextField();
        inputPanel.add(cognomeField);

        inputPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);

        inputPanel.add(new JLabel("Città:"));
        cittaField = new JTextField();
        inputPanel.add(cittaField);

        inputPanel.add(new JLabel("Indirizzo:"));
        indirizzoField = new JTextField();
        inputPanel.add(indirizzoField);

        // Pannello pulsanti
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        cercaBtn = new JButton("Cerca");
        aggiungiBtn = new JButton("Aggiungi");
        modificaBtn = new JButton("Modifica");
        cancellaBtn = new JButton("Cancella");
        resetBtn = new JButton("Reset DB");

        buttonPanel.add(cercaBtn);
        buttonPanel.add(aggiungiBtn);
        buttonPanel.add(modificaBtn);
        buttonPanel.add(cancellaBtn);
        buttonPanel.add(resetBtn);

        // Area output
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Aggiungi componenti al frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    // Metodi per ottenere i valori dei campi
    public String getMatricola() {
        return matricolaField.getText().trim();
    }

    public String getCognome() {
        return cognomeField.getText().trim();
    }

    public String getNome() {
        return nomeField.getText().trim();
    }

    public String getCitta() {
        return cittaField.getText().trim();
    }

    public String getIndirizzo() {
        return indirizzoField.getText().trim();
    }

    // Metodi per impostare i valori dei campi
    public void setMatricola(String matricola) {
        matricolaField.setText(matricola);
    }

    public void setCognome(String cognome) {
        cognomeField.setText(cognome);
    }

    public void setNome(String nome) {
        nomeField.setText(nome);
    }

    public void setCitta(String citta) {
        cittaField.setText(citta);
    }

    public void setIndirizzo(String indirizzo) {
        indirizzoField.setText(indirizzo);
    }

    // Metodi per aggiungere listener ai pulsanti
    public void addCercaListener(ActionListener listener) {
        cercaBtn.addActionListener(listener);
    }

    public void addAggiungiListener(ActionListener listener) {
        aggiungiBtn.addActionListener(listener);
    }

    public void addModificaListener(ActionListener listener) {
        modificaBtn.addActionListener(listener);
    }

    public void addCancellaListener(ActionListener listener) {
        cancellaBtn.addActionListener(listener);
    }

    public void addResetListener(ActionListener listener) {
        resetBtn.addActionListener(listener);
    }

    // Metodi per visualizzare messaggi
    public void displayRecord(Record record) {
        if (record != null) {
            setMatricola(record.getMatricola());
            setCognome(record.getCognome());
            setNome(record.getNome());
            setCitta(record.getCitta());
            setIndirizzo(record.getIndirizzo());
            appendOutput("Record trovato: " + record.getMatricola() + " - " + record.getCognome() + " " + record.getNome());
        } else {
            appendOutput("Record non trovato");
        }
    }

    public void appendOutput(String message) {
        outputArea.append(message + "\n");
    }

    public void clearOutput() {
        outputArea.setText("");
    }

    public void clearFields() {
        setMatricola("");
        setCognome("");
        setNome("");
        setCitta("");
        setIndirizzo("");
    }
}