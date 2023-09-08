import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gioco gioco = new Gioco();
        GestioneFile gestioneFile = new GestioneFile();
        Lingua lingua = new Lingua();

        String nomeGiocatore;
        String linguaScelta;
        int scelta;

        do {
            System.out.print("\nSeleziona l'azione:");
            System.out.print("\n1. Gioca");
            System.out.print("\n2. Mostra Statistiche");
            System.out.print("\n3. Cambia Lingua");
            System.out.print("\n4. Esci");
            System.out.print("\n");
            System.out.print("\nScelta: ");

            while (!scanner.hasNextInt()) {
                System.out.print("\nInserire un numero valido: ");
                scanner.nextLine(); // Consuma l'input non numerico
            }
            scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    System.out.print("\nInserisci il tuo nome: ");
                    scanner.nextLine(); // Consuma il carattere new line rimasto dopo nextInt()
                    nomeGiocatore = scanner.nextLine();
                    gioco.gioca();
                    gestioneFile.salvaNomeGiocatore(nomeGiocatore);
                    gestioneFile.salvaProgressoGiocatore(nomeGiocatore, gioco.getPunteggio());
                    break;

                case 2:
                    System.out.print("\nInserisci il tuo nome: ");
                    scanner.nextLine(); // Consuma il carattere new line rimasto dopo nextInt()
                    nomeGiocatore = scanner.nextLine();
                    int punteggio = gestioneFile.caricaProgressoGiocatore(nomeGiocatore);
                    System.out.print("\nIl tuo punteggio: " + punteggio);
                    break;

                case 3:
                    System.err.print("\nFunzione non presente, in arrivo...");
//                    gestioneFile.creaFileLingue();
//                    System.out.print("\nLingue disponibili: " + gestioneFile.caricaLingueDisponibili());
//                    System.out.print("\nInserisci la lingua scelta: ");
//                    linguaScelta = scanner.next();
//
//                    // Verifica se la lingua scelta è disponibile
//                    if (gestioneFile.caricaLingueDisponibili().contains(linguaScelta)) {
//                        lingua.cambiaLingua(linguaScelta);
//                        System.out.print("\nLingua cambiata in: " + linguaScelta);
//                    } else {
//                        System.out.print("\nLingua non disponibile. Riprova.");
//                    }
                    break;

                default:
                    System.out.print("\nScelta non valida, riprova.\n");
                    break;
            }

        } while (scelta == 4);
        System.out.print("\n\nGrazie per aver giocato! Arrivederci.");
    }
}
