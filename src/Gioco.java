import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

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
        // Parole segrete per il gioco (puoi aggiungerne altre se desideri)
        String[] paroleSegrete = {
                "ciao", "gatto", "casa", "mare", "albero", "sole", "computer", "libro", "tavolo"
        };

        // Seleziona una parola segreta casualmente dalla lista
        Random random = new Random();
        parolaSegreta = paroleSegrete[random.nextInt(paroleSegrete.length)].toLowerCase();

        // Inizializza le strutture dati per le lettere indovinate e le lettere errate
        lettereIndovinate = new HashSet<>();
        lettereErrate = new HashSet<>();

        // Imposta il numero massimo di tentativi consentiti
        tentativiRimasti = 7;

        // Inizializza il punteggio del giocatore
        punteggio = 0;
    }

    public void gioca() {
        Scanner scanner = new Scanner(System.in);
        boolean giocoFinito = false;

        while (!giocoFinito) {
            mostraStatoGioco();

            System.out.print("Inserisci una lettera: ");
            char lettera = scanner.next().toLowerCase().charAt(0);

            if (!Character.isLetter(lettera)) {
                System.out.println("Inserire solo lettere valide.");
                continue;
            }

            if (lettereIndovinate.contains(lettera) || lettereErrate.contains(lettera)) {
                System.out.println("Hai già inserito questa lettera. Riprova.");
                continue;
            }

            if (parolaSegreta.contains(String.valueOf(lettera))) {
                lettereIndovinate.add(lettera);
                System.out.println("Bravo! Hai indovinato una lettera.");
            } else {
                lettereErrate.add(lettera);
                System.out.println("Spiacente, la lettera non è presente nella parola.");
                tentativiRimasti--;
            }

            // Verifica se il gioco è finito
            if (haVinto()) {
                giocoFinito = true;
                System.out.println("Complimenti, hai indovinato la parola!");
            } else if (haPerso()) {
                giocoFinito = true;
                System.out.println("Spiacente, hai esaurito i tentativi. Sei impiccato!");
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

        System.out.println("\nParola da indovinare: " + parolaCorrente);
        System.out.println("Tentativi rimasti: " + tentativiRimasti);
        System.out.println("Lettere indovinate: " + lettereIndovinate);
        System.out.println("Lettere errate: " + lettereErrate);
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
