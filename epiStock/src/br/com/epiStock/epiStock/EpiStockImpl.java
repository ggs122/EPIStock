package br.com.epiStock.epiStock;

import br.com.employee.employee.EmployeeImpl;
import br.com.epiStockInterface.epiStockInterface.EpiStockInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class EpiStockImpl implements EpiStockInterface {

    private int ppeId;
    private static int ppeIdStatic = 1;
    private String ppeProductCode;
    private String ppeName;
    private String ppeType;
    private String ppeCa;
    private String ppeDescriotion;
    private int ppeAmount;
    private LocalDateTime ppeDeliveryDate = LocalDateTime.of(0001, 01, 01, 00, 00);
    private Locale localeBr = Locale.forLanguageTag("pt-BR");
    DateTimeFormatter dateFormated = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", localeBr);

    private long employeeEnrollmentNumber;

    private EpiStockImpl(int ppeId, String ppeProductCode, String ppeName, String ppeType, String ppeCa, String ppeDescriotion, int ppeAmount, long employeeEnrollmentNumber, LocalDateTime ppeDeliveryDate) {
        this.ppeId = ppeId;
        this.ppeProductCode = ppeProductCode;
        this.ppeName = ppeName;
        this.ppeType = ppeType;
        this.ppeCa = ppeCa;
        this.ppeDescriotion = ppeDescriotion;
        this.ppeAmount = ppeAmount;
        this.employeeEnrollmentNumber = employeeEnrollmentNumber;
        this.ppeDeliveryDate = ppeDeliveryDate;
    }

    private EpiStockImpl(EpiStockImpl other) {
        this.ppeId = other.ppeId;
        this.ppeProductCode = other.ppeProductCode;
        this.ppeName = other.ppeName;
        this.ppeType = other.ppeType;
        this.ppeCa = other.ppeCa;
        this.ppeDescriotion = other.ppeDescriotion;
        this.ppeAmount = other.ppeAmount;
        this.employeeEnrollmentNumber = other.employeeEnrollmentNumber;
        this.ppeDeliveryDate = other.ppeDeliveryDate;
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
    static List<EpiStockImpl>epiStockRegisterUsedsList = new ArrayList<>();


    public void addPpeToStock(String ppeProductCode, String ppeName, String ppeType, String ppeCa, String ppeDescriotion, int ppeAmount) {
       boolean isPpeProductCode  = ppeProductCode.matches("[0-9]{2}\\.[0-9]{3}\\.[0-9]{2}");
       boolean isppeCa = ppeCa.matches("([0-9])+");

       if (isPpeProductCode) {
           if (isppeCa) {
               if (isPpeProductCode && isppeCa && chechingIfSameAddPpeToStock(ppeProductCode, ppeCa) == false) {
                   EpiStockImpl epiStock = new EpiStockImpl(ppeIdStatic++, ppeProductCode, ppeName, ppeType, ppeCa, ppeDescriotion, ppeAmount);
                   epiStockList.add(epiStock);
               } else {
                   IO.println(String.format("Código do produto %s e número do C.A %s -> Inválidos.", ppeProductCode, ppeCa));
               }
           } else {
               IO.println(String.format("Número do C.A %s -> Inválido", ppeCa));
           }
       } else {
           IO.println(String.format("Código do produto %s -> Inválido", ppeProductCode));
       }
    }

    @Override
    public void deleteAllEpiStock() {
        epiStockList.clear();
        IO.println("Todos os epi's foram deletados do estoque com sucesso!");
    }

    @Override
    public void deleteSpecificEpiForCodRef(String codRef) {
      boolean isProductCode = epiStockList
                .stream()
                .anyMatch(e -> e.ppeProductCode.equalsIgnoreCase(codRef));

      if (isProductCode) {
          epiStockList
                  .removeIf(e -> e.ppeProductCode.equalsIgnoreCase(codRef));
          IO.println(String.format(localeBr, "Epi, Ref: %s -> deletado com sucesso!", codRef));
      } else {
          IO.println(String.format(localeBr, "Código de produto: %s -> inválido", codRef));
      }

    }

    private static boolean chechingIfSameAddPpeToStock(String ppeProductCode,  String ppeCa) {
        Locale localeBr = Locale.forLanguageTag("pt-BR");
       boolean isSameEpi = epiStockList
                .stream()
                .anyMatch(e -> e.ppeProductCode.equalsIgnoreCase(ppeProductCode) && e.ppeCa.equalsIgnoreCase(ppeCa));

       if (isSameEpi) {
           IO.println("-------------------------------------------------------------------------------------------------------");
           IO.println(String.format(localeBr, "Epi Cód: Ref.%s | CA: %s -> já consta no estoque.", ppeProductCode, ppeCa));
           IO.println("-------------------------------------------------------------------------------------------------------");
       } else {
           IO.println("---------------------------------------------------------------------------------------------------------");
           IO.println(String.format(localeBr, "Epi Cód: Ref.%s | CA: %s -> cadastrado com sucesso!", ppeProductCode, ppeCa));
           IO.println("-------------------------------------------------------------------------------------------------------");
       }
       return isSameEpi;
    }

    @Override
    public void personalProtectiveEquipmentDelivery(String ppeProductCode, int ppeAmount, long employeeEnrollmentNumber, String ppeDeliveryDate){
    LocalDateTime ppeDeliveryDateFormated = LocalDateTime.parse(ppeDeliveryDate, dateFormated);
       boolean isEpi = epiStockList
               .stream()
               .anyMatch(e -> e.ppeProductCode.equals(ppeProductCode));

       boolean isEmployee = employeeList
               .stream()
               .anyMatch(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber && !e.getIs_Active().equals(EmployeeImpl.Status.Inativo));

       if (isEpi) {
           if (isEmployee) {
               epiStockList
                       .stream()
                       .filter( e -> e.ppeProductCode.equals(ppeProductCode))
                       .forEach(e -> {
                           if (ppeAmount > 0) {
                               if (e.ppeAmount > 0) {
                                   e.ppeAmount -= ppeAmount;
                                   EpiStockImpl epiStock = new EpiStockImpl(e.ppeId, e.ppeProductCode, e.ppeName, e.ppeType, e.ppeCa, e.ppeDescriotion, ppeAmount, employeeEnrollmentNumber, ppeDeliveryDateFormated);
                                   EpiStockImpl epiStockCopy = new EpiStockImpl(epiStock);
                                   epiStockRegisterUsedsList.add(epiStockCopy);
                               } else {
                                   IO.println(String.format(localeBr, "Produto código: %s -> saldo insuficiente!", ppeProductCode));
                               }
                           } else {
                               IO.println(String.format(localeBr, "Quantidade de epi (%d), não pode ser menor do que 0.", ppeAmount));
                           }
                       });



           } else {
               IO.println(String.format(localeBr, "Matrícula %d -> Inválida ou funcionário demitido!", employeeEnrollmentNumber));
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
    public void printEpiStockUseds () {
        if (!epiStockRegisterUsedsList.isEmpty()) {
            IO.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            IO.println("> Epis Entregues: <");

          List<Long> employeeEnrollmentNumberLongList = epiStockRegisterUsedsList
                    .stream()
                    .mapToLong(e -> e.employeeEnrollmentNumber)
                            .distinct()
                                    .boxed()
                                            .toList();

          employeeEnrollmentNumberLongList
                  .forEach(emll -> {
                      IO.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                      IO.println("Funcionário:");
                      employeeList
                              .stream()
                              .filter(e -> e.getEmployeeEnrollmentNumber() == emll && !e.getIs_Active().equals(EmployeeImpl.Status.Inativo))
                              .forEach(e -> IO.println(String.format(localeBr, "Matrícula: %d | Nome: %s %s %s | Cargo: %s", e.getEmployeeEnrollmentNumber(), e.getEmployeeFirstName(), e.getEmployeeMiddleName(), e.getEmployeeLastname(), e.getJobe_title())));
                      IO.println();
                      IO.println("EPI retirado:");
                      epiStockRegisterUsedsList
                              .stream()
                              .filter(epis -> epis.employeeEnrollmentNumber == emll)
                              .forEach(e -> IO.println(String.format(localeBr, "Id: %d | Cód: %s | Epi: %-25s | Tipo: %-15s | C.A %s | Descrição: %-35s | Qtde: %d | Date e hora da retirada: %s", e.ppeId, e.ppeProductCode, e.ppeName, e.ppeType, e.ppeCa, e.ppeDescriotion, e.ppeAmount, e.ppeDeliveryDate.format(dateFormated))));
                      IO.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                  });
            IO.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            IO.println("Nenhum epi foi usado ainda");
        }
    }

    @Override
    public void printSpecificEpiUsed(long employeeEnrollmentNumber) {
      boolean isEmployee = employeeList
                .stream()
                .anyMatch(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber);

     boolean isEpiUsedWithEmployeeEnrollmentNumber = epiStockRegisterUsedsList
              .stream()
              .anyMatch(e -> e.employeeEnrollmentNumber == employeeEnrollmentNumber);

     if (isEmployee) {
         if (isEpiUsedWithEmployeeEnrollmentNumber) {
             IO.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
             IO.println("Epi's retirados pelo funcionário:");
             IO.println();
             IO.println("Dados do funcionário:");
             employeeList
                     .stream()
                             .filter(e -> e.getEmployeeEnrollmentNumber() == employeeEnrollmentNumber)
                                     .forEach(e -> IO.println(String.format(localeBr, "Id: %d | Mat: %s | Nome: %s %s %s | Cargo: %s", e.getEmployeeId(), e.getEmployeeEnrollmentNumber(), e.getEmployeeFirstName(), e.getEmployeeMiddleName(), e.getEmployeeLastname(), e.getJobe_title())));
             IO.println();
             IO.println("Epi's retirados:");
             epiStockRegisterUsedsList
                     .stream()
                     .filter(e -> e.employeeEnrollmentNumber == employeeEnrollmentNumber)
                     .forEach(e -> IO.println(e + " | Data: " + e.ppeDeliveryDate.format(dateFormated)));
             IO.println();
             IO.println("Total de Epi's retirados:");
           int EpiTotal = epiStockRegisterUsedsList
                     .stream()
                     .filter(e -> e.employeeEnrollmentNumber == employeeEnrollmentNumber)
                     .mapToInt(e -> e.ppeAmount)
                     .sum();
             System.out.println(EpiTotal);
             IO.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
         } else {
             IO.println(String.format(localeBr, "Não houve nenhuma entrega de EPI com a matrícula: %d", employeeEnrollmentNumber));
         }
     } else {
         IO.println(String.format(localeBr, "Funcionário com a matrícula: %d -> inexistente!", employeeEnrollmentNumber));
     }

    }

    @Override
    public String toString() {
        return String.format(localeBr, "Id: %d | Cód: -> Ref.%s | EPI: %-25s | Tipo: %-25s | C.A: %s | Descrição: %-45s | Qtde: %d", ppeId, ppeProductCode, ppeName, ppeType, ppeCa, ppeDescriotion, ppeAmount);
    }
}
