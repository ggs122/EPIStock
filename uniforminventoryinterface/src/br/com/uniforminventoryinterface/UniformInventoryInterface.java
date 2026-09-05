package br.com.uniforminventoryinterface;

public interface UniformInventoryInterface {

    void addSpecificUniformInTheInventory(long uniformAmount, String uniformCode, int uniformType, int uniformSize);
    void printUniformInventory();
    public void printUniformsUsed();
    void uniformDelivery(long employeeEnrollmentNumber, String uniformCode, long uniformAmount);

}
