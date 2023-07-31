import java.util.HashMap;

public class Lingua {
    private String linguaAttiva;
    private HashMap<String, HashMap<String, String>> traduzioni;

    public Lingua() {
        linguaAttiva = "italiano"; // Lingua di default
        inizializzaTraduzioni();
    }

    private void inizializzaTraduzioni() {
        traduzioni = new HashMap<>();

        // Traduzioni per l'italiano
        HashMap<String, String> italiano = new HashMap<>();
        italiano.put("benvenuto", "Benvenuto nel gioco dell'impiccato!");
        italiano.put("inserisci_nome", "Inserisci il tuo nome: ");
        italiano.put("giocatore", "Giocatore: ");
        italiano.put("tentativi_rimasti", "Tentativi rimasti: ");
        italiano.put("inserisci_lettera", "Inserisci una lettera: ");
        italiano.put("gioco_finito", "Il gioco è finito!");
        italiano.put("vincitore", "Hai indovinato la parola, complimenti!");
        italiano.put("sconfitta", "Hai esaurito i tentativi, sei impiccato!");
        italiano.put("punteggio", "Il tuo punteggio: ");
        traduzioni.put("italiano", italiano);

        // Traduzioni per l'inglese
        HashMap<String, String> inglese = new HashMap<>();
        inglese.put("benvenuto", "Welcome to Hangman!");
        inglese.put("inserisci_nome", "Enter your name: ");
        inglese.put("giocatore", "Player: ");
        inglese.put("tentativi_rimasti", "Tries left: ");
        inglese.put("inserisci_lettera", "Enter a letter: ");
        inglese.put("gioco_finito", "Game over!");
        inglese.put("vincitore", "Congratulations, you guessed the word!");
        inglese.put("sconfitta", "You ran out of tries, you are hanged!");
        inglese.put("punteggio", "Your score: ");
        traduzioni.put("inglese", inglese);

        // Traduzioni per il francese
        HashMap<String, String> francese = new HashMap<>();
        francese.put("benvenuto", "Bienvenue dans le jeu du pendu !");
        francese.put("inserisci_nome", "Entrez votre nom : ");
        francese.put("giocatore", "Joueur : ");
        francese.put("tentativi_rimasti", "Tentatives restantes : ");
        francese.put("inserisci_lettera", "Entrez une lettre : ");
        francese.put("gioco_finito", "Fin du jeu !");
        francese.put("vincitore", "Félicitations, vous avez deviné le mot !");
        francese.put("sconfitta", "Vous avez épuisé vos tentatives, vous êtes pendu !");
        francese.put("punteggio", "Votre score : ");
        traduzioni.put("francese", francese);

    }

    public String traduci(String lingua, String chiave) {
        HashMap<String, String> traduzioneLingua = traduzioni.get(lingua);
        if (traduzioneLingua != null) {
            return traduzioneLingua.getOrDefault(chiave, "Traduzione non disponibile");
        } else {
            return "Lingua non disponibile";
        }
    }

    public void cambiaLingua(String linguaScelta) {
        // Verifica se la lingua scelta è tra quelle supportate
        if (traduzioni.containsKey(linguaScelta)) {
            linguaAttiva = linguaScelta;
        } else {
            System.out.println("Lingua non supportata. La lingua attuale rimane invariata.");
        }
    }
}
