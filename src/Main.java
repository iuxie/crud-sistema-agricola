import br.com.sistemaagricola.controller.MenuController;
import br.com.sistemaagricola.services.InventoryManager;

public class Main {
    public static void main(String[] args) {

        InventoryManager manager = new InventoryManager();
        MenuController menuController = new MenuController(manager);
        menuController.start();

    }
}
