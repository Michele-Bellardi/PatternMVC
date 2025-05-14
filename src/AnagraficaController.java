import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnagraficaController {
    private AnagraficaView view;
    private Anagrafico model;

    public AnagraficaController(AnagraficaView view, Anagrafico model) {
        this.view = view;
        this.model = model;

        // Aggiungi listener ai pulsanti
        this.view.addCercaListener(new CercaListener());
        this.view.addAggiungiListener(new AggiungiListener());
        this.view.addModificaListener(new ModificaListener());
        this.view.addCancellaListener(new CancellaListener());
        this.view.addResetListener(new ResetListener());
    }

    class CercaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String matricola = view.getMatricola();
            if (matricola.isEmpty()) {
                view.appendOutput("Inserire una matricola da cercare");
                return;
            }

            int pos = model.cercaMatricola(matricola);
            if (pos >= 0) {
                Record record = model.recPosizione(pos);
                view.displayRecord(record);
            } else {
                view.appendOutput("Nessun record trovato con matricola: " + matricola);
                view.clearFields();
            }
        }
    }

    class AggiungiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String matricola = view.getMatricola();
            String cognome = view.getCognome();
            String nome = view.getNome();
            String citta = view.getCitta();
            String indirizzo = view.getIndirizzo();

            if (matricola.isEmpty() || cognome.isEmpty() || nome.isEmpty()) {
                view.appendOutput("Matricola, Cognome e Nome sono campi obbligatori");
                return;
            }

            // Verifica se la matricola esiste già
            int pos = model.cercaMatricola(matricola);
            if (pos >= 0) {
                view.appendOutput("Matricola già esistente: " + matricola);
                return;
            }

            model.aggiungi(matricola, cognome, nome, citta, indirizzo);
            view.appendOutput("Record aggiunto con successo: " + matricola);
            view.clearFields();
        }
    }

    class ModificaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String matricola = view.getMatricola();
            String citta = view.getCitta();
            String indirizzo = view.getIndirizzo();

            if (matricola.isEmpty()) {
                view.appendOutput("Selezionare un record da modificare (cercare per matricola)");
                return;
            }

            int pos = model.cercaMatricola(matricola);
            if (pos >= 0) {
                model.modifica(pos, citta, indirizzo);
                view.appendOutput("Record modificato con successo: " + matricola);
            } else {
                view.appendOutput("Record non trovato: " + matricola);
            }
        }
    }

    class CancellaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String matricola = view.getMatricola();
            if (matricola.isEmpty()) {
                view.appendOutput("Selezionare un record da cancellare (cercare per matricola)");
                return;
            }

            int pos = model.cercaMatricola(matricola);
            if (pos >= 0) {
                boolean success = model.cancella(pos);
                if (success) {
                    view.appendOutput("Record cancellato con successo: " + matricola);
                    view.clearFields();
                } else {
                    view.appendOutput("Errore nella cancellazione del record: " + matricola);
                }
            } else {
                view.appendOutput("Record non trovato: " + matricola);
            }
        }
    }

    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int response = JOptionPane.showConfirmDialog(view,
                    "Vuoi davvero reimpostare il database ai valori iniziali?",
                    "Conferma Reset",
                    JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                model.reInizializzaFile();
                view.clearFields();
                view.appendOutput("Database reimpostato ai valori iniziali");
            }
        }
    }
}