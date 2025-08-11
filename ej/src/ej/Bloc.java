package ej;

public class Bloc {

    String description;

    Bloc() {
        this.description = "Ceci est un bloc du jeu Epicraft’s Journey";
    }
    
    void setTestDescription() {
        this.description = "Test git";
    }

    void afficherDescription() {
        System.out.println(this.description);
    }
}