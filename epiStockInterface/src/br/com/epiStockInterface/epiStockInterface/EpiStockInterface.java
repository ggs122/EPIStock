package br.com.epiStockInterface.epiStockInterface;

public interface EpiStockInterface {

    void addPpeToStock(String ppeProductCode, String ppeName, String ppeType, String ppeCa, String ppeDescriotion, int ppeAmount);
    void personalProtectiveEquipmentDelivery(String ppeProductCode, int ppeAmount, long employeeEnrollmentNumber);
    void printEpiStockUseds ();
    void printEpiStock ();

}
