package ej;

public class Bloc {

    private int largeur;
    private int hauteur;
    private int longueur;

    Bloc(int longueur,int largeur,int hauteur) {
		this.longueur = longueur;
		this.largeur = largeur;
		this.hauteur = hauteur;
    }

    
    int getLargeur() {
    	return this.largeur;
    }    
    int getLongeur() {
    	return longueur;
    }
    
    int getHauteur() {
    	return hauteur;
    }
    
    int setLargeur(int largeur) {
    	return this.largeur = largeur;
    }
   
}