package br.com.unifominventory.uniforminventory;

import br.com.employeInterface.employeeInterface.EmployeeInterface;
import br.com.epiStockInterface.epiStockInterface.EpiStockInterface;
import br.com.loginInterface.loginInterface.LoginInterface;
import br.com.uniforminventoryinterface.uniforminventoryinterface.UniformInventoryInterface;

import java.util.ArrayList;
import java.util.List;

public class UniformInventoryImpl implements UniformInventoryInterface {

    private long uniformId = uniformStaticId;
    private static long uniformStaticId = 1;


    private long employeeEnrollmentNumber;

    private long uniformAmountG;
    private String uniformGCode;

    private long uniformAmountGg;
    private String uniformGgCode;

    private long uniformAmountM;
    private String uniformMCode;

    private long uniformAmountP;
    private String uniformPCode;

    private long uniformAmountPp;
    private String uniformPpCode;

    private UniformInventoryImplEnum uniformType;

    private EmployeeInterface EmployeeService;
    private LoginInterface loginService;
    private EpiStockInterface epiService;

    static List<UniformInventoryImpl> uniformInventoryList = new ArrayList<>();

    private UniformInventoryImpl(long uniformId, long uniformAmountG, String uniformGCode, long uniformAmountGg, String uniformGgCode, long uniformAmountM, String uniformMCode, long uniformAmountP, String uniformPCode, long uniformAmountPp, String uniformPpCode, UniformInventoryImplEnum uniformType) {
        this.uniformId = uniformId;
        this.uniformAmountG = uniformAmountG;
        this.uniformGCode = uniformGCode;
        this.uniformAmountGg = uniformAmountGg;
        this.uniformGgCode = uniformGgCode;
        this.uniformAmountM = uniformAmountM;
        this.uniformMCode = uniformMCode;
        this.uniformAmountP = uniformAmountP;
        this.uniformPCode = uniformPCode;
        this.uniformAmountPp = uniformAmountPp;
        this.uniformPpCode = uniformPpCode;
        this.uniformType = uniformType;
    }

    public void createUniformInventory(long uniformAmountG, String uniformGCode, long uniformAmountGg, String uniformGgCode, long uniformAmountM, String uniformMCode, long uniformAmountP, String uniformPCode, long uniformAmountPp, String uniformPpCode, int uniformType) {

        UniformInventoryImpl uniformInventory = new UniformInventoryImpl(uniformStaticId++, uniformAmountG, uniformGCode, uniformAmountGg, uniformGgCode, uniformAmountM, uniformMCode, uniformAmountP, uniformPCode, uniformAmountPp, uniformPpCode, UniformInventoryImplEnum.returnUniformType(uniformType));

    }
}
