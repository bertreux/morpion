package hitema.java.morpion;

public class Morpion {
    private int nbCase;
    private int nbLigne;
    private char[][] grille;
    private char joueur1 = 'O';
    private char joueur2 = 'X';
    public boolean enCour;

    public char getJoueur1() {
        return joueur1;
    }

    public Morpion(int n) {
        this.nbLigne=n;
        this.nbCase=n*n;
        this.grille = new char[n][n];
        this.reinitialiser();
    }

    public void reinitialiser(){
        enCour = true;
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbLigne; j++) {
                grille[i][j] = ' ';
            }
        }
    }

    public void setJoueur(char joueur1, int joueur) {
        if(joueur == 1){
            this.joueur1 = joueur1;
        }else {
            this.joueur2 = joueur2;
        }

    }

    public void viewGrille(){
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

    public int play(int joueur, int ligne, int collone){
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

    public boolean testHorizontal(int ligne, char symbol){
        boolean win=true;
        for(int i=0;i<nbLigne;i++){
            if (grille[ligne][i] != symbol) {
                win = false;
                break;
            }
        }
        return win;
    }

    public boolean testVerticale(int collone, char symbol){
        boolean win=true;
        for(int i=0;i<nbLigne;i++){
            if (grille[i][collone] != symbol) {
                win = false;
                break;
            }
        }
        return win;
    }

    public boolean testDiagonale(int collone, int ligne, char symbol){
        boolean win=true;

        if(ligne == collone){
            for(int i=0;i<nbLigne;i++){
                if (grille[i][i] != symbol) {
                    win = false;
                    break;
                }
            }
        }

        if(collone + ligne == nbLigne-1 && win){
            for(int i=0;i<nbLigne;i++){
                if (grille[i][nbLigne-i-1] != symbol) {
                    win = false;
                    break;
                }
            }
        }

        return win;
    }

    public void win(int joueur, int ligne, int collone){
        boolean win;
        char symbol;

        if(joueur == 1){
            symbol = this.joueur1;
        }else{
            symbol = this.joueur2;
        }

        win=this.testHorizontal(ligne, symbol);

        if(!win) {
            win=this.testVerticale(collone, symbol);
        }

        if(!win && (ligne == collone || collone+ligne == nbLigne-1)){
            win=this.testDiagonale(collone, ligne, symbol);
        }

        if (win){
            this.enCour = false;
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
