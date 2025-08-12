package ej;

public class Main {
    public static void main(String[] args) throws IllegalBlocException {
        try {
            Porte porte = new Porte(1, 1, 1, false);
            porte.afficherDescription();
            porte.verrouiller();
        } catch (PorteVerrouilleException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}