package br.com.epiStock.epiStock;

import br.com.employee.employee.EmployeeImpl;
import br.com.epiStockInterface.epiStockInterface.EpiStockInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EpiStockImpl implements EpiStockInterface {

    private int ppeId;
    private static int ppeIdStatic = 1;
    private String ppeProductCode;
    private String ppeName;
    private String ppeType;
    private String ppeCa;
    private String ppeDescriotion;
    private int ppeAmount;
    private Locale localeBr = Locale.forLanguageTag("pt-BR");

    private long employeeEnrollmentNumber;

    private EpiStockImpl(int ppeId, String productCode, String ppeName, String ppeType, String ppeCa, String ppaDescriotion, int ppaAmount, long employeeEnrollmentNumber) {
        this.ppeId = ppeId;
        this.ppeProductCode = productCode;
        this.ppeName = ppeName;
        this.ppeType = ppeType;
        this.ppeCa = ppeCa;
        this.ppeDescriotion = ppaDescriotion;
        this.ppeAmount = ppaAmount;
        this.employeeEnrollmentNumber = employeeEnrollmentNumber;
    }

    private EpiStockImpl(int ppeId, String productCode, String ppeName, String ppeType, String ppeCa, String ppaDescriotion, int ppaAmount) {
        this.ppeId = ppeId;
        this.ppeProductCode = productCode;
        this.ppeName = ppeName;
        this.ppeType = ppeType;
        this.ppeCa = ppeCa;
        this.ppeDescriotion = ppaDescriotion;
        this.ppeAmount = ppaAmount;
    }

    public EpiStockImpl () {}

    static List<EpiStockImpl> epiStockList = new ArrayList<>();
    static List<EmployeeImpl> employeeList = EmployeeImpl.getEmployeeList();


    public void addPpeToStock(String ppeProductCode, String ppeName, String ppeType, String ppeCa, String ppeDescriotion, int ppeAmount) {
       boolean isPpeProductCode  = ppeProductCode.matches("[0-9]{2}\\.[0-9]{3}\\.[0-9]{2}");
       boolean isppeCa = ppeCa.matches("([0-9])+");

       if (isPpeProductCode) {
           if (isppeCa) {
               if (isPpeProductCode && isppeCa) {
                   EpiStockImpl epiStock = new EpiStockImpl(ppeIdStatic++, ppeProductCode, ppeName, ppeType, ppeCa, ppeDescriotion, ppeAmount);
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

    public void personalProtectiveEquipmentDelivery(String ppeProductCode, int ppeAmount, long employeeEnrollmentNumber) {
       boolean isEpi = epiStockList
               .stream()
               .anyMatch(e -> e.ppeProductCode.equals(ppeProductCode));

       boolean isEmployee = employeeList
               .stream()
               .anyMatch(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber);

       if (isEpi) {
           if (isEmployee) {
               epiStockList
                       .stream()
                       .filter( e -> e.ppeProductCode.equals(ppeProductCode))
                       .forEach(e -> {
                           if (ppeAmount > 0) {
                               e.ppeAmount -= ppeAmount;
                               //TODO falta implementar o registro do recebimento no sistema.
//                               EpiStockImpl epiStock = new EpiStockImpl(e.ppeId, );
                           } else {
                               IO.println(String.format(localeBr, "Quantidade de epi (%d), não pode ser menor do que 0.", ppeAmount));
                           }
                       });



           } else {
               IO.println(String.format(localeBr, "Matrícula %d -> Inválida", employeeEnrollmentNumber));
           }
       } else {
           IO.println(String.format(localeBr, "Código do produto %s -> Inválido!", ppeProductCode));
       }

    }

    @Override
    public void printEpiStock () {
        if (!epiStockList.isEmpty()) {
            IO.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            IO.println("> Estoque de EPI <");
            epiStockList
                    .stream()
                    .forEach(e -> IO.println(e));
            IO.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            IO.println("Não existe EPIs cadastrados no sistema.");
        }
    }

    @Override
    public String toString() {
        return String.format(localeBr, "Id: %d | Cód: -> Ref.%s | EPI: %-25s | Tipo: %-25s | C.A: %s | Descrição: %-45s | Qtde: %d", ppeId, ppeProductCode, ppeName, ppeType, ppeCa, ppeDescriotion, ppeAmount);
    }
}
