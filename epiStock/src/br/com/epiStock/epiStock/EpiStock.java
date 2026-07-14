package br.com.epiStock.epiStock;

public class EpiStock {

    private int ppeId;
    private String ppeProductCode;
    private String ppeName;
    private String ppeCa;
    private String ppaDescriotion;
    private int ppaAmount;

    private long employeeEnrollmentNumber;

    private EpiStock(int ppeId, String productCode, String ppeName, String ppeCa, String ppaDescriotion, int ppaAmount, long employeeEnrollmentNumber) {
        this.ppeId = ppeId;
        this.ppeProductCode = productCode;
        this.ppeName = ppeName;
        this.ppeCa = ppeCa;
        this.ppaDescriotion = ppaDescriotion;
        this.ppaAmount = ppaAmount;
        this.employeeEnrollmentNumber = employeeEnrollmentNumber;
    }

    private EpiStock(int ppeId, String productCode, String ppeName, String ppeCa, String ppaDescriotion, int ppaAmount) {
        this.ppeId = ppeId;
        this.ppeProductCode = productCode;
        this.ppeName = ppeName;
        this.ppeCa = ppeCa;
        this.ppaDescriotion = ppaDescriotion;
        this.ppaAmount = ppaAmount;
    }


    public void addPpeToStock(String productCode, String epiName, String epiCa, String epiDescriotion, int epiAmount) {
       boolean isProductCode  = productCode.matches("Ref\\.[0-9]{2}\\.[0-9]{3}\\.[0-9]{2}");
    }

    public void personalProtectiveEquipmentDelivery() {

    }
}
