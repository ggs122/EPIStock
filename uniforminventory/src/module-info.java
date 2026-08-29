import br.com.uniforminventory.UniformInventoryImpl;
import br.com.uniforminventoryinterface.UniformInventoryInterface;

module uniforminventory {

    requires uniforminventoryinterface;
    requires employeeInterface;
    requires loginInterface;
    requires epiStockInterface;

    provides UniformInventoryInterface with UniformInventoryImpl;

}