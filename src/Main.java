import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gioco gioco = new Gioco();
        GestioneFile gestioneFile = new GestioneFile();
        Lingua lingua = new Lingua();

        String nomeGiocatore;
        String linguaScelta;
        boolean esci = false;

        do {
            System.out.print("\nSeleziona l'azione:");
            System.out.print("\n1. Gioca");
            System.out.print("\n2. Mostra Statistiche");
            System.out.print("\n3. Cambia Lingua");
            System.out.print("\n4. Esci");
            System.out.print("\n");
            System.out.print("\nScelta: ");

            int scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    System.out.print("Inserisci il tuo nome: ");
                    scanner.nextLine(); // Consuma il carattere new line rimasto dopo nextInt()
                    nomeGiocatore = scanner.nextLine();
                    gioco.gioca();
                    gestioneFile.salvaNomeGiocatore(nomeGiocatore);
                    gestioneFile.salvaProgressoGiocatore(nomeGiocatore, gioco.getPunteggio());
                    break;

                case 2:
                    System.out.print("Inserisci il tuo nome: ");
                    scanner.nextLine(); // Consuma il carattere new line rimasto dopo nextInt()
                    nomeGiocatore = scanner.nextLine();
                    int punteggio = gestioneFile.caricaProgressoGiocatore(nomeGiocatore);
                    System.out.println("Il tuo punteggio: " + punteggio);
                    break;

                case 3:
                    System.out.println("Lingue disponibili: " + gestioneFile.caricaLingueDisponibili());
                    System.out.print("Inserisci la lingua scelta: ");
                    linguaScelta = scanner.next();

                    // Verifica se la lingua scelta è disponibile
                    if (gestioneFile.caricaLingueDisponibili().contains(linguaScelta)) {
                        lingua.cambiaLingua(linguaScelta);
                        System.out.println("Lingua cambiata in: " + linguaScelta);
                    } else {
                        System.out.println("Lingua non disponibile. Riprova.");
                    }
                    break;

                case 4:
                    esci = true;
                    break;

                default:
                    System.out.println("Scelta non valida, riprova.");
                    break;
            }

        } while (!esci);

        System.out.println("Grazie per aver giocato! Arrivederci.");
        scanner.close();
    }
}
