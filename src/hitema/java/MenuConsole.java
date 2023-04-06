package hitema.java;

import hitema.java.morpion.Morpion;

import java.util.Scanner;

public class MenuConsole {

    public static void viewStart(String lseparator){
        System.out.println(lseparator);
        System.out.println("\nBonjour, le morpion se joue a deux joueur, chaqu'un son tour.");
        System.out.println("Pour choisir l'emplacement que vous voulez jouer, vous devez donnez :");
        System.out.println("- le numéro de la ligne (1,2 et 3");
        System.out.println("- le numéro de la collone (1,2 et 3)");
        System.out.println("Par exemple pour jouer en bas milieu je vais dire 3 puis 2\n");
    }

    public static Integer getLigne(String lseparator, Scanner scanner){
        int ligne = 0;
            while (true) {
                System.out.println(lseparator);
                System.out.println("Combien de collone et de ligne ( => 3 et =< 9 ) : ");
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
        return ligne;
    }

    public static void setJoueur(String lseparator, Scanner scanner, Morpion morpion, String symbolBase, int joueur){
        String symbol;
        while(true) {
            System.out.println(lseparator);
            System.out.println("Joueur "+joueur+" choississez votre symbol (CR=default '"+symbolBase+"') : ");
            symbol = scanner.nextLine();
            if(joueur == 2 && symbol.length() > 0 && symbol.charAt(0) == morpion.getJoueur1()){
                System.err.println("Entrer un caractère différent de joueur 1 !!!");
            }else if (symbol.length() == 1) {
                break;
            } else if(symbol.length() == 0){
                symbol = symbolBase;
                break;
            }else {
                System.err.println("Entrer un seul caractère !!!");
            }
        }
        morpion.setJoueur(symbol.charAt(0), joueur);
    }

    public static Integer getLigneOrCollone(int i, String lseparator, Scanner scanner, Morpion morpion, boolean isLigne){
        int ligne;
        while (true) {
            if(isLigne){
                System.out.println("\n"+lseparator);
                System.out.println("C'est le tour du joueur numéro " + (i % 2 + 1));
                System.out.println("Sur quelle ligne voulez vous jouer : ");
            }else {
                System.out.println(lseparator);
                System.out.println("Sur quelle collone voulez vous jouer : ");
            }
            var rep = scanner.nextLine();
            try {
                ligne = Integer.parseInt(rep);
            } catch (NumberFormatException e) {
                System.err.print("Entrez une valeur numérique ");
                continue;
            }
            if (ligne > morpion.getNbLigne() || ligne < 1) {
                System.err.println("Entrée une valeur entre 1 et "+morpion.getNbLigne()+" compris");
            } else {
                break;
            }
        }
        return ligne;
    }

    public static Integer actionAfterPlay(int etat, Morpion morpion, int ligne, int collone, int i){
        if (etat == 0) {
            morpion.win(i % 2 + 1, ligne - 1, collone - 1);
            if (morpion.enCour) {
                i++;
            }
        } else {
            System.err.println("Un joueur a déja jouer dans cette case, rejouer");
        }
        return i;
    }

    public static void endDraw(int i, Morpion morpion, String lseparator){
        if(i == morpion.getNbCase()){
            System.out.println("\n"+lseparator);
            System.out.println("Toute les cases ont été jouées, égalité, fin de la partie");
            morpion.setEnCour(false);
        }
    }

    public static void viewWin(String lseparator, Morpion morpion, int i){
        System.out.println(lseparator+"\n");
        morpion.viewGrille();
        if(i < morpion.getNbCase()){
            System.out.println("\n"+lseparator);
            System.out.println("Bravo. Le joueur numéro " + (i % 2 + 1) + " a gagné");
        }
    }

    public static Integer restart(String lseparator, Scanner scanner, Morpion morpion){
        Integer nbre;
        while(true){
            System.out.println(lseparator);
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
                if(nbre ==1){
                    morpion.reinitialiser();
                }
                break;
            }else {
                System.err.println("Rentrer en valeur 1 ou 2 !!!");
            }
        }
        return nbre;
    }

    public static void main(String[] args) {
        int i=0;
        int ligne;
        int collone;
        int etat;
        int nbre = 1;
        String lseparator = "=".repeat(80);
        Scanner scanner = new Scanner(System.in);

        viewStart(lseparator);

        while(nbre == 1) {

            ligne = getLigne(lseparator, scanner);
            Morpion morpion = new Morpion(ligne);

            setJoueur(lseparator, scanner, morpion, "O" , 1);
            setJoueur(lseparator, scanner, morpion, "X" , 2);

            while (morpion.enCour) {
                System.out.println(lseparator+"\n");
                morpion.viewGrille();

                ligne = getLigneOrCollone(i, lseparator, scanner, morpion, true);

                collone = getLigneOrCollone(i, lseparator, scanner, morpion, false);

                etat = morpion.play(i % 2 + 1, ligne - 1, collone - 1);

                i=actionAfterPlay(etat, morpion, ligne, collone, i);

                endDraw(i, morpion, lseparator);
            }

            viewWin(lseparator, morpion, i);

            nbre = restart(lseparator, scanner, morpion);
            if (nbre == 1){
                i=0;
            }
        }
    }
}