package ej;

public abstract class Bloc implements IBloc {
    
    protected int longueur;
    protected int largeur;
    protected int hauteur;
    
    public Bloc(final int longueur, final int largeur, final int hauteur) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public void afficherDescription() {
        System.out.println("Je suis un bloc !");
    }
    
}