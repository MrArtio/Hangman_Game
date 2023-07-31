import java.io.*;
import java.util.*;

public class GestioneFile {
    private final String nomeFileGiocatori = "src/dati/giocatori.txt";
    private final String nomeFileProgressi = "src/dati/progressi.txt";
    private final String nomeFileLingue = "src/dati/lingue.txt";
    public void creaFileLingue() {
        try {
            File file = new File(nomeFileLingue);
            if (file.createNewFile()) {
                System.out.print("\nFile delle lingue creato: " + file.getName());
                // Inizializza il file delle lingue con le lingue disponibili per il gioco
                try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                    writer.println("italiano");
                    writer.println("inglese");
                    writer.println("francese");
                    writer.println("tedesco");
                    writer.println("russo");
                } catch (IOException e) {
                    System.err.print("\nErrore durante l'inizializzazione del file delle lingue: " + e.getMessage());
                }
            } else {
                System.err.print("\nFile delle lingue già esistente.");
            }
        } catch (IOException e) {
            System.err.print("\nErrore durante la creazione del file delle lingue: " + e.getMessage());
        }
    }

    public void salvaNomeGiocatore(String nomeGiocatore) {
        try (FileWriter fileWriter = new FileWriter(nomeFileGiocatori, true)) {
            fileWriter.write(nomeGiocatore + System.lineSeparator());
        } catch (IOException e) {
            System.err.print("\nErrore durante il salvataggio del nome del giocatore: " + e.getMessage());
        }
    }

    public ArrayList<String> caricaNomiGiocatori() {
        ArrayList<String> nomiGiocatori = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nomeFileGiocatori))) {
            String nomeGiocatore;
            while ((nomeGiocatore = bufferedReader.readLine()) != null) {
                nomiGiocatori.add(nomeGiocatore);
            }
        } catch (IOException e) {
            System.err.print("\nErrore durante il caricamento dei nomi dei giocatori: " + e.getMessage());
        }
        return nomiGiocatori;
    }

    public void salvaProgressoGiocatore(String nomeGiocatore, int punteggio) {
        try (FileWriter fileWriter = new FileWriter(nomeFileProgressi, true)) {
            fileWriter.write(nomeGiocatore + " " + punteggio + System.lineSeparator());
        } catch (IOException e) {
            System.err.print("\nErrore durante il salvataggio del progresso del giocatore: " + e.getMessage());
        }
    }

    public int caricaProgressoGiocatore(String nomeGiocatore) {
        int punteggio = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nomeFileProgressi))) {
            String riga;
            while ((riga = bufferedReader.readLine()) != null) {
                String[] parti = riga.split(" ");
                if (parti.length == 2 && parti[0].equals(nomeGiocatore)) {
                    punteggio = Integer.parseInt(parti[1]);
                    break;
                }
            }
        } catch (IOException e) {
            System.err.print("\nErrore durante il caricamento del progresso del giocatore: " + e.getMessage());
        }
        return punteggio;
    }

    public ArrayList<String> caricaLingueDisponibili() {
        ArrayList<String> lingueDisponibili = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nomeFileLingue))) {
            String lingua;
            while ((lingua = bufferedReader.readLine()) != null) {
                lingueDisponibili.add(lingua);
            }
        } catch (IOException e) {
            System.err.print("\nErrore durante il caricamento delle lingue disponibili: " + e.getMessage());
        }
        return lingueDisponibili;
    }
}
