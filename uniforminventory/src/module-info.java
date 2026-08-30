import br.com.uniforminventory.UniformInventoryImpl;
import br.com.uniforminventoryinterface.UniformInventoryInterface;

module uniforminventory {

    requires uniforminventoryinterface;
    requires employeeInterface;
    requires loginInterface;
    requires epiStockInterface;
    requires transitive employee;
    requires login;
    requires epiStock;

    provides UniformInventoryInterface with UniformInventoryImpl;

}