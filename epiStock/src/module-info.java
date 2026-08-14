import br.com.epiStock.epiStock.EpiStockImpl;
import br.com.epiStockInterface.epiStockInterface.EpiStockInterface;

module epiStock {

    requires epiStockInterface;
    requires employee;
    requires java.logging;
    requires org.slf4j;
    requires serializationsutils;
    provides EpiStockInterface with EpiStockImpl;

}