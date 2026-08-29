package br.com.unifominventory.uniforminventory;

import br.com.employeInterface.employeeInterface.EmployeeInterface;
import br.com.epiStockInterface.epiStockInterface.EpiStockInterface;
import br.com.loginInterface.loginInterface.LoginInterface;
import br.com.uniforminventoryinterface.uniforminventoryinterface.UniformInventoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UniformInventoryImpl implements UniformInventoryInterface {

    private long uniformId = uniformStaticId;
    private static long uniformStaticId = 1;


    private long employeeEnrollmentNumber;

    private long uniformAmount;
    private String uniformCode;
    private UniformInventoryImplEnumsUtils.UniformType uniformType;
    private UniformInventoryImplEnumsUtils.UniformSize uniformSize;

    private Locale localeBr = Locale.forLanguageTag("pt-BR");

    private EmployeeInterface EmployeeService;
    private LoginInterface loginService;
    private EpiStockInterface epiService;

    static List<UniformInventoryImpl> uniformInventoryList = new ArrayList<>();

    private UniformInventoryImpl(long uniformId, long uniformAmount, String uniformCode, UniformInventoryImplEnumsUtils.UniformType uniformType, UniformInventoryImplEnumsUtils.UniformSize uniformSize) {
        this.uniformId = uniformId;
        this.uniformAmount = uniformAmount;
        this.uniformCode = uniformCode;
        this.uniformType = uniformType;
        this.uniformSize = uniformSize;

    }

    @Override
    public void addSpecificUniformInTheInventory(long uniformAmount, String uniformCode, int uniformType, int uniformSize) {
        UniformInventoryImpl uniformInventory = new UniformInventoryImpl(uniformStaticId++, uniformAmount, uniformCode, UniformInventoryImplEnumsUtils.UniformType.returnUniformType(uniformType), UniformInventoryImplEnumsUtils.UniformSize.returnUniformSize(uniformSize));
        uniformInventoryList.add(uniformInventory);
    }

    @Override
    public void printUniformInventory() {
        if (!uniformInventoryList.isEmpty()) {
            uniformInventoryList
                    .stream()
                    .forEach(u -> IO.println(u));
        } else {
            IO.println(" Ops! Estoque vazio -> Adicione uniformes");
        }
    }

    @Override
    public String toString() {
        return String.format(localeBr, "ID: %d | Ref - %s | Tipo: %s | Tamanho: %s | Qtde: %d", uniformId, uniformCode ,uniformType, uniformSize, uniformAmount);
    }
}
