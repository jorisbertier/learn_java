package ej;

public abstract class Bloc implements IBloc {
    
    protected int longueur;
    protected int largeur;
    protected int hauteur;
    protected Couleur couleur;
    
    Bloc(final int longueur, final int largeur, final int hauteur) throws IllegalBlocException {
        if(longueur < MIN_LONGUEUR || largeur < MIN_LARGEUR || hauteur < MIN_HAUTEUR) {
            throw new IllegalBlocException();
        }
        
        this.longueur = longueur;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public void afficherDescription() {
        System.out.println("Je suis un bloc !");
    }
    
    public void setCouleur(Couleur couleur) {
    	this.couleur = couleur;
    }
}