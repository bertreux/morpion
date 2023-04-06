package hitema.java.morpion;

public class Morpion {
    private int nbCase;
    private int nbLigne;
    private char[][] grille;
    private char joueur1 = 'O';
    private char joueur2 = 'X';
    public boolean enCour = true;

    public Morpion(int n) {
        this.nbLigne=n;
        this.nbCase=n*n;
        this.grille = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grille[i][j] = ' ';
            }
        }
    }

    public void afficherPlateau(){
        System.out.print(" |");
        String lseparator = "-".repeat(2*nbLigne+2);
        for(int a=0;a<nbLigne;a++){
            System.out.print((a+1)+"|");
        }
        System.out.println("\n"+lseparator);
        for(int i=0;i<nbLigne;i++){
            System.out.print(i+1+"|");
            for(int j=0;j<nbLigne;j++){
                    System.out.print(grille[i][j]);
                    System.out.print("|");
            }
            System.out.println("\n"+lseparator);
        }
    }

    public int jouer(int joueur, int ligne, int collone){
        if(grille[ligne][collone] == ' '){
            if(joueur == 1){
                grille[ligne][collone] = this.joueur1;
            }else{
                grille[ligne][collone] = this.joueur2;
            }
            return 0;
        }else {
            return -1;
        }
    }

    public boolean testHorizontal(int collone, int ligne, char symbol){
        boolean win=false;
        if(collone == 0){
            if(grille[ligne][collone+1] == symbol && grille[ligne][collone+2]== symbol){
                win = true;
            }
        }else if(collone == 1){
            if(grille[ligne][collone-1] == symbol && grille[ligne][collone+1]== symbol){
                win = true;
            }
        }else if (collone == 2){
            if(grille[ligne][collone-1] == symbol && grille[ligne][collone-2]== symbol){
                win = true;
            }
        }
        return win;
    }

    public boolean testVerticale(int collone, int ligne, char symbol){
        boolean win=false;
        if (ligne == 0) {
            if (grille[ligne + 1][collone] == symbol && grille[ligne + 2][collone] == symbol) {
                win = true;
            }
        } else if (ligne == 1) {
            if (grille[ligne - 1][collone] == symbol && grille[ligne + 1][collone] == symbol) {
                win = true;
            }
        } else if (ligne == 2) {
            if (grille[ligne - 1][collone] == symbol && grille[ligne - 2][collone] == symbol) {
                win = true;
            }
        }
        return win;
    }

    public boolean testDiagonale(int collone, int ligne, char symbol){
        boolean win=false;
        if(ligne == 0 && collone == 0){
            if(grille[ligne+1][collone+1] == symbol && grille[ligne+2][collone+2] == symbol){
                win = true;
            }
        }else if(ligne == 0 && collone == 2){
            if(grille[ligne+1][collone-1] == symbol && grille[ligne+2][collone-2] == symbol){
                win = true;
            }
        }else if(ligne == 1 && collone == 1){
            if(grille[ligne-1][collone-1] == symbol && grille[ligne+1][collone+1] == symbol){
                win = true;
            }else if(grille[ligne-1][collone+1] == symbol && grille[ligne+1][collone-1] == symbol){
                win = true;
            }
        }else if(ligne == 2 && collone == 0){
            if(grille[ligne-1][collone+1] == symbol && grille[ligne-2][collone+2] == symbol){
                win = true;
            }
        }else if(ligne == 2 && collone == 2){
            if(grille[ligne-1][collone-1] == symbol && grille[ligne-2][collone-2] == symbol){
                win = true;
            }
        }
        return win;
    }

    public void win(int joueur, int ligne, int collone){
        boolean win = false;
        char symbol;

        if(joueur == 1){
            symbol = this.joueur1;
        }else{
            symbol = this.joueur2;
        }

        win=this.testHorizontal(collone, ligne, symbol);

        if(!win) {
            win=this.testVerticale(collone, ligne, symbol);
        }

        if(!win){
            win=this.testDiagonale(collone, ligne, symbol);
        }

        if (win){
            this.enCour = false;
        }
    }

    public void reinitialiser(){
        enCour = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grille[i][j] = ' ';
            }
        }
    }

    public void setEnCour(boolean etat){
        enCour = etat;
    }

    public int getNbCase(){
        return nbCase;
    }

    public int getNbLigne(){
        return nbLigne;
    }
}
