import br.com.epiStock.epiStock.EpiStockImpl;
import br.com.epiStockInterface.epiStockInterface.EpiStockInterface;

module epiStock {

    requires epiStockInterface;
    requires employee;
    provides EpiStockInterface with EpiStockImpl;

}