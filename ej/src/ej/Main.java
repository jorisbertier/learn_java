package ej;

public class Main {

    public static void main(String[] args) {
        Bloc unBloc = new Mur(10,10,5,true);
        Porte unePorte = new Porte(10, 10, 5, true);
        unBloc.afficherDescription();
        
        unePorte.afficherDescription();
        System.out.println(unePorte.isClosed());
    }
}