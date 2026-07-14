package br.com.epiStock.epiStock;

public class EpiStock {

    private int ppeId;
    private String ppeProductCode;
    private String ppeName;
    private String ppeCa;
    private String ppeDescriotion;
    private int ppeAmount;

    private long employeeEnrollmentNumber;

    private EpiStock(int ppeId, String productCode, String ppeName, String ppeCa, String ppaDescriotion, int ppaAmount, long employeeEnrollmentNumber) {
        this.ppeId = ppeId;
        this.ppeProductCode = productCode;
        this.ppeName = ppeName;
        this.ppeCa = ppeCa;
        this.ppeDescriotion = ppaDescriotion;
        this.ppeAmount = ppaAmount;
        this.employeeEnrollmentNumber = employeeEnrollmentNumber;
    }

    private EpiStock(int ppeId, String productCode, String ppeName, String ppeCa, String ppaDescriotion, int ppaAmount) {
        this.ppeId = ppeId;
        this.ppeProductCode = productCode;
        this.ppeName = ppeName;
        this.ppeCa = ppeCa;
        this.ppeDescriotion = ppaDescriotion;
        this.ppeAmount = ppaAmount;
    }


    public void addPpeToStock(String ppeProductCode, String ppeName, String ppeCa, String ppeDescriotion, int ppeAmountAmount) {
       boolean isPpeProductCode  = ppeProductCode.matches("Ref\\.[0-9]{2}\\.[0-9]{3}\\.[0-9]{2}");
       boolean isppeCa = ppeCa.matches("CA - ([0-9])+");

    }

    public void personalProtectiveEquipmentDelivery() {

    }
}
