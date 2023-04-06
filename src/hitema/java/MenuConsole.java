package hitema.java;

import hitema.java.morpion.Morpion;

import java.util.Scanner;

public class MenuConsole {
    public static void main(String[] args) {
        int i=0;
        int ligne;
        int collone;
        int etat;
        int nbre = 1;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bonjour, le morpion se joue a deux joueur, chaqu'un son tour.");
        System.out.println("Pour choisir l'emplacement que vous voulez jouer, vous devez donnez :");
        System.out.println("- le numéro de la ligne (1,2 et 3");
        System.out.println("- le numéro de la collone (1,2 et 3)");
        System.out.println("Par exemple pour jouer en bas milieu je vais dire 3 puis 2\n");
        while(nbre == 1) {
            while(true){
                System.out.println("Combien de collone et de ligne ( > 3 ) : ");
                var rep = scanner.nextLine();
                try {
                    ligne = Integer.parseInt(rep);
                } catch (NumberFormatException e) {
                    System.err.println("Entrez une valeur numérique ");
                    continue;
                }
                if (ligne < 3) {
                    System.err.println("Entrée une valeur supérieur a 3");
                } else {
                    break;
                }
            }
            Morpion morpion = new Morpion(ligne);

            while (morpion.enCour) {
                morpion.afficherPlateau();
                System.out.println("C'est le tour du joueur numéro " + (i % 2 + 1));

                while (true) {
                    System.out.println("Sur quelle ligne voulez vous jouer : ");
                    var rep = scanner.nextLine();
                    try {
                        ligne = Integer.parseInt(rep);
                    } catch (NumberFormatException e) {
                        System.err.println("Entrez une valeur numérique ");
                        continue;
                    }
                    if (ligne > morpion.getNbLigne() || ligne < 1) {
                        System.err.println("Entrée une valeur entre 1 et "+morpion.getNbLigne()+" compris");
                    } else {
                        break;
                    }
                }

                while (true) {
                    System.out.println("Sur quelle collone voulez vous jouer : ");
                    var rep = scanner.nextLine();
                    try {
                        collone = Integer.parseInt(rep);
                    } catch (NumberFormatException e) {
                        System.err.println("Entrez une valeur numérique ");
                        continue;
                    }
                    if (collone > 3 || collone < 1) {
                        System.err.println("Entrée une valeur entre 1 et 3 compris");
                    } else {
                        break;
                    }
                }
                etat = morpion.jouer(i % 2 + 1, ligne - 1, collone - 1);
                if (etat == 0) {
                    morpion.win(i % 2 + 1, ligne - 1, collone - 1);
                    if (morpion.enCour) {
                        i++;
                    }
                } else {
                    System.err.println("Un joueur a déja jouer dans cette case, rejouer");
                }
                if(i== morpion.getNbCase()){
                    System.out.println("Toute les cases ont été jouées, égalité, fin de la partie");
                    morpion.setEnCour(false);
                }
            }
            morpion.afficherPlateau();
            if(i< morpion.getNbCase()){
                System.out.println("Bravo. Le joueur numéro " + (i % 2 + 1) + " a gagné");
            }

            while(true){
                System.out.println("Voulez vous rejouer :");
                System.out.println("1/ oui");
                System.out.println("2/ non");
                var rep = scanner.nextLine();
                try {
                    nbre = Integer.parseInt(rep);
                } catch (NumberFormatException e) {
                    System.err.println("Entrez une valeur numérique ");
                    continue;
                }
                if (nbre >= 1 && nbre <= 2) {
                    if(nbre==1){
                        i=0;
                        morpion.reinitialiser();
                    }
                    break;
                }else {
                    System.err.println("Rentrer en valeur 1 ou 2 !!!");
                }
            }
        }
    }
}