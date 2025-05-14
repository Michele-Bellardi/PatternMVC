public class Test {
    public static void main(String[] args) {
        // Crea il model
        Anagrafico model = new Anagrafico();

        // Crea la view
        AnagraficaView view = new AnagraficaView();
        view.setVisible(true);

        // Crea il controller
        new AnagraficaController(view, model);
    }
}