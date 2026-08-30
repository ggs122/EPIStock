package br.com.uniforminventory;

import br.com.employeInterface.employeeInterface.EmployeeInterface;
import br.com.employee.employee.EmployeeImpl;
import br.com.epiStockInterface.epiStockInterface.EpiStockInterface;
import br.com.login.login.LoginImpl;
import br.com.loginInterface.loginInterface.LoginInterface;
import br.com.uniforminventoryinterface.UniformInventoryInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class UniformInventoryImpl implements UniformInventoryInterface {

    private long uniformId = uniformStaticId;
    private static long uniformStaticId = 10000;


    private long employeeEnrollmentNumber;

    private long uniformAmount;
    private String uniformCode;
    private UniformInventoryImplEnumUtils.UniformType uniformType;
    private UniformInventoryImplEnumUtils.UniformSize uniformSize;

    private Locale localeBr = Locale.forLanguageTag("pt-BR");

    private EmployeeInterface employeeService = new EmployeeImpl();
    private LoginInterface loginService = new LoginImpl();
    private EpiStockInterface epiService;

    static List<UniformInventoryImpl> uniformInventoryList = new ArrayList<>();
    static List<UniformInventoryImpl> deliveryUniformUsedList = new ArrayList<>();

    private UniformInventoryImpl(long uniformId, long uniformAmount, String uniformCode, UniformInventoryImplEnumUtils.UniformType uniformType, UniformInventoryImplEnumUtils.UniformSize uniformSize) {
        this.uniformId = uniformId;
        this.uniformAmount = uniformAmount;
        this.uniformCode = uniformCode;
        this.uniformType = uniformType;
        this.uniformSize = uniformSize;

    }

    private UniformInventoryImpl(long employeeEnrollmentNumber, String uniformCode, long uniformAmount, UniformInventoryImplEnumUtils.UniformType uniformType, UniformInventoryImplEnumUtils.UniformSize uniformSize) {
        this.employeeEnrollmentNumber = employeeEnrollmentNumber;
        this.uniformCode = uniformCode;
        this.uniformAmount = uniformAmount;
        this.uniformType = uniformType;
        this.uniformSize = uniformSize;
    }

    public UniformInventoryImpl() {}

    @Override
    public void addSpecificUniformInTheInventory(long uniformAmount, String uniformCode, int uniformType, int uniformSize) {

       boolean isUniformCode = uniformCode.matches("[0-9]{2}\\.[0-9]{3}");

       if (isUniformCode) {
           if (uniformAmount > 0) {
               if (checkingSimilarUniformCode(uniformCode)) {
                   IO.println(String.format(localeBr, "Produto Código: %s já foi adicionado ao estoque", uniformCode));
               } else {
                   UniformInventoryImpl uniformInventory = new UniformInventoryImpl(uniformStaticId++, uniformAmount, uniformCode, UniformInventoryImplEnumUtils.UniformType.returnUniformType(uniformType), UniformInventoryImplEnumUtils.UniformSize.returnUniformSize(uniformSize));
                   uniformInventoryList.add(uniformInventory);
               }
           } else {
               IO.println(String.format(localeBr, "Quantidade: %d -> Não pode ser menor ou igual a zero!", uniformAmount));
           }
       } else {
           IO.println(String.format(localeBr, "Código: %s, inválido!", uniformCode));
       }
    }

    private boolean checkingSimilarUniformCode(String uniformCode) {
       return uniformInventoryList
                .stream()
                .anyMatch(u -> u.uniformCode.equalsIgnoreCase(uniformCode));
    }

    @Override
    public void uniformDelivery(long employeeEnrollmentNumber, String uniformCode, long uniformAmount, int uniformType, int uniformSize) {


        if (employeeService.findEmployeeSomeDates(employeeEnrollmentNumber)) {
            if (uniformAmount > 0) {

                if (isUniformeCode(uniformCode)) {
                    if (isSuficienteAmountSpecificUniform(uniformCode, uniformAmount)) {
                        UniformInventoryImpl uniformInventory = new UniformInventoryImpl(employeeEnrollmentNumber, uniformCode, uniformAmount, UniformInventoryImplEnumUtils.UniformType.returnUniformType(uniformType), UniformInventoryImplEnumUtils.UniformSize.returnUniformSize(uniformSize));
                        deliveryUniformUsedList.add(uniformInventory);
                        minusAmountSpecificUniform(uniformCode, uniformAmount);
                    }
                }

            } else {
                IO.println(String.format(localeBr, "Quantidade de uniformes deve ser maior que zero!", uniformAmount));
            }

        } else {
            IO.println(String.format(localeBr, "Matrícula Nº %d -> Não encontrado!", employeeEnrollmentNumber));
        }
    }

    private boolean isUniformeCode(String uniformCode) {
       boolean isUniformCode = uniformInventoryList
                .stream()
                .anyMatch(u -> u.uniformCode.equalsIgnoreCase(uniformCode));

       if (isUniformCode) {
           IO.println(String.format(localeBr, "Código: %s do produto encontrado", uniformCode));
       } else {
           IO.println(String.format(localeBr, "Código: %s do produto não encontrado", uniformCode));
       }
       return isUniformCode;
    }

    private boolean isSuficienteAmountSpecificUniform(String uniformCode, long uniformAmount) {
      boolean specificUniformIsAmount = uniformInventoryList
                .stream()
                .anyMatch(u -> u.uniformCode.equalsIgnoreCase(uniformCode) && u.uniformAmount > 0 && u.uniformAmount > uniformAmount);
      if (specificUniformIsAmount) {
          IO.println(String.format(String.format(localeBr, "Produto de código: %s -> Saldo suficiente!", uniformCode)));
      } else {
          IO.println(String.format(localeBr, "Produto de código %s -> Saldo insuficiente!", uniformCode));
      }
      return specificUniformIsAmount;
    }

    private void minusAmountSpecificUniform(String uniformCode, long uniformAmount) {
        uniformInventoryList
                .stream()
                .filter(u -> u.uniformCode.equalsIgnoreCase(uniformCode))
                .forEach(u -> u.uniformAmount = u.uniformAmount - uniformAmount);
    }

    @Override
    public void printUniformInventory() {
        if (!uniformInventoryList.isEmpty()) {
            IO.println("-----------------------------------------------------------------------");
            IO.println("> Estoque de Uniformes <");
            uniformInventoryList
                    .stream()
                    .sorted(Comparator.comparing(u -> u.uniformSize))
                    .sorted(Comparator.comparing(u -> u.uniformType))
                    .forEach(u -> IO.println(u));
            IO.println("-----------------------------------------------------------------------");
        } else {
            IO.println("-----------------------------------------------------------------------");
            IO.println(" Ops! Estoque vazio -> Adicione uniformes");
            IO.println("-----------------------------------------------------------------------");
        }
    }

    public void deleteSpecificUniform(String uniformCode) {
        if (!uniformInventoryList.isEmpty()) {
            uniformInventoryList
                    .removeIf(u -> u.uniformCode.equals(uniformCode));
            IO.println(String.format(localeBr, "Todos os uniformes desse lote, com código %s -> Foram deletados com sucesso!", uniformCode));
        } else {
            IO.println("Não a uniformes no estoque -> Cadastre!");
        }
    }

    @Override
    public String toString() {
        return String.format(localeBr, "ID: %d | Ref - %s | Tipo: %-20s | Tamanho: %-2s | Qtde: %d", uniformId, uniformCode ,uniformType, uniformSize, uniformAmount);
    }

}
