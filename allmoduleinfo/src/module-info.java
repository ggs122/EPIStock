module allmoduleinfo {
    requires employee;
    requires employeeInterface;
    requires epiStock;
    requires epiStockInterface;
    requires login;
    requires loginInterface;
    requires uniforminventory;
    requires uniforminventoryinterface;

    exports br.com.allmoduleinfo.allmoduleinfo;
}