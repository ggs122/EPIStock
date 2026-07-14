package br.com.epiStock.epiStock;

public class EpiStock {

    private String productCode;
    private String epiName;
    private String epiCa;
    private String epiDescriotion;
    private int epiAmount;

    private int employeeEnrollmentNumber;

    private EpiStock(String productCode, String epiName, String epiCa, String epiDescriotion, int epiAmount, int employeeEnrollmentNumber) {
        this.productCode = productCode;
        this.epiName = epiName;
        this.epiCa = epiCa;
        this.epiDescriotion = epiDescriotion;
        this.epiAmount = epiAmount;
        this.employeeEnrollmentNumber = employeeEnrollmentNumber;
    }

    private EpiStock(String productCode, String epiName, String epiCa, String epiDescriotion, int epiAmount) {
        this.productCode = productCode;
        this.epiName = epiName;
        this.epiCa = epiCa;
        this.epiDescriotion = epiDescriotion;
        this.epiAmount = epiAmount;
    }


    public void addPpeToStock(String productCode, String epiName, String epiCa, String epiDescriotion, int epiAmount) {

    }

    public void personalProtectiveEquipmentDelivery() {

    }
}
