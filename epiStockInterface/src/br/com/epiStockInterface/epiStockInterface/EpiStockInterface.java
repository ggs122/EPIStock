package br.com.epiStockInterface.epiStockInterface;

public interface EpiStockInterface {

    void addPpeToStock(String ppeProductCode, String ppeName, String ppeType, String ppeCa, String ppeDescriotion, int ppeAmount);
    void personalProtectiveEquipmentDelivery(String ppeProductCode, int ppeAmount, long employeeEnrollmentNumber, String ppeDeliveryDate);
    void deleteAllEpiStock();
    void deleteSpecificEpiForCodRef(String codRef);
    void printEpiStockUseds ();
    void printSpecificEpiUsed(long employeeEnrollmentNumber);
    void printEpiStock ();

}
