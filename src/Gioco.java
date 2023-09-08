import java.io.*;
import java.util.*;

public class Gioco {
    private String parolaSegreta;
    private Set<Character> lettereIndovinate;
    private Set<Character> lettereErrate;
    private int tentativiRimasti;
    private int punteggio;

    public Gioco() {
        inizializzaGioco();
    }

    private void inizializzaGioco() {
        File fileParole = new File("src/dati/parole.txt");
        List<String> parole = new ArrayList<>();

        if (!fileParole.exists()) {
            // Se il file non esiste, genera casualmente 10 parole e scrivile nel file
            parole = generaParoleCasuali();
            scriviParoleSuFile(fileParole, parole);
        } else {
            // Se il file esiste, leggi le parole dal file
            parole = leggiParoleDaFile(fileParole);
        }

        // Seleziona una parola casualmente dalla lista
        Random random = new Random();
        parolaSegreta = parole.get(random.nextInt(parole.size())).toLowerCase();

        // Inizializza le strutture dati per le lettere indovinate e le lettere errate
        lettereIndovinate = new HashSet<>();
        lettereErrate = new HashSet<>();

        // Imposta il numero massimo di tentativi consentiti
        tentativiRimasti = 7;

        // Inizializza il punteggio del giocatore
        punteggio = 0;
    }

    private List<String> generaParoleCasuali() {
        List<String> paroleCasuali = new ArrayList<>();
        // Puoi aggiungere altre parole a questa lista o caricarle da un file se lo desideri
        List<String> paroleDisponibili = Arrays.asList("ciao", "gatto", "casa", "mare", "albero", "sole", "computer", "libro", "tavolo");

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int indiceCasuale = random.nextInt(paroleDisponibili.size());
            paroleCasuali.add(paroleDisponibili.get(indiceCasuale));
        }

        return paroleCasuali;
    }

    private void scriviParoleSuFile(File file, List<String> parole) {
        try (PrintWriter writer = new PrintWriter(file)) {
            for (String parola : parole) {
                writer.println(parola);
            }
        } catch (IOException e) {
            System.err.print("\nErrore durante la scrittura del file delle parole: " + e.getMessage());
        }
    }

    private List<String> leggiParoleDaFile(File file) {
        List<String> parole = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                parole.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return parole;
    }

    public void gioca() {
        Scanner scanner = new Scanner(System.in);
        boolean giocoFinito = false;

        while (!giocoFinito) {
            mostraStatoGioco();

            System.out.print("\nInserisci una lettera: ");
            char lettera = scanner.next().toLowerCase().charAt(0);

            if (!Character.isLetter(lettera)) {
                System.err.print("\nInserire solo lettere valide.");
                continue;
            }

            if (lettereIndovinate.contains(lettera) || lettereErrate.contains(lettera)) {
                System.err.print("\nHai già inserito questa lettera. Riprova.\n");
                continue;
            }

            if (parolaSegreta.contains(String.valueOf(lettera))) {
                lettereIndovinate.add(lettera);
                System.out.print("\nBravo! Hai indovinato una lettera.\n");
            } else {
                lettereErrate.add(lettera);
                System.err.print("\nSpiacente, la lettera non è presente nella parola.\n");
                tentativiRimasti--;
            }

            // Verifica se il gioco è finito
            if (haVinto()) {
                giocoFinito = true;
                System.out.print("\nComplimenti, hai indovinato la parola!\n");
            } else if (haPerso()) {
                giocoFinito = true;
                System.err.print("\nSpiacente, hai esaurito i tentativi. Sei impiccato!");
            }
        }

        scanner.close();
    }

    private void mostraStatoGioco() {
        StringBuilder parolaCorrente = new StringBuilder();
        for (char carattere : parolaSegreta.toCharArray()) {
            if (lettereIndovinate.contains(carattere)) {
                parolaCorrente.append(carattere).append(" ");
            } else {
                parolaCorrente.append("_ ");
            }
        }

        System.out.print("\nParola da indovinare: " + parolaCorrente);
        System.out.print("\nTentativi rimasti: " + tentativiRimasti);
        System.out.print("\nLettere indovinate: " + lettereIndovinate);
        System.out.print("\nLettere errate: " + lettereErrate);
    }

    private boolean haVinto() {
        for (char carattere : parolaSegreta.toCharArray()) {
            if (!lettereIndovinate.contains(carattere)) {
                return false;
            }
        }
        punteggio += 10;
        return true;
    }

    private boolean haPerso() {
        if (tentativiRimasti <= 0) {
            punteggio -= 5;
            return true;
        }
        return false;
    }

    public int getPunteggio() {
        return punteggio;
    }
}
