package br.com.epiStock.epiStock;

import java.util.ArrayList;
import java.util.List;

public class EpiStock {

    private int ppeId;
    private static int ppeIdStatic = 1;
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

    static List<EpiStock> epiStockList = new ArrayList<>();


    public void addPpeToStock(String ppeProductCode, String ppeName, String ppeCa, String ppeDescriotion, int ppeAmountAmount) {
       boolean isPpeProductCode  = ppeProductCode.matches("Ref\\.[0-9]{2}\\.[0-9]{3}\\.[0-9]{2}");
       boolean isppeCa = ppeCa.matches("CA - ([0-9])+");

       if (isPpeProductCode) {
           if (isppeCa) {
               if (isPpeProductCode && isppeCa) {
                   EpiStock epiStock = new EpiStock(ppeIdStatic++, ppeProductCode, ppeName, ppeCa, ppeDescriotion, ppeAmount);
                   epiStockList.add(epiStock);
               } else {
                   IO.println(String.format("Código do produto %s e número do C.A %s -> Inválidos", ppeProductCode, ppeCa));
               }
           } else {
               IO.println(String.format("Número do C.A %s -> Inválido", ppeCa));
           }
       } else {
           IO.println(String.format("Código do produto %s -> Inválido", ppeProductCode));
       }

    }

    public void personalProtectiveEquipmentDelivery() {

    }
}
