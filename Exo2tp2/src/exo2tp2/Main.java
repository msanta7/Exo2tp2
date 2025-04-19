package exo2tp2;

public class Main {
    public static void main(String[] args) {
        
            Annuaire model = new Annuaire();
            View view = new View();
            Controller controller = new Controller(model, view);
            controller.demarrer();
    }
}
