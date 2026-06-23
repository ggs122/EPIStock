package br.com.employee.employee;

public final class EmployeeUtils {

    private EmployeeUtils () {}

    public static Employee.Jobe_Title returnJoble_Title (int chooseJobe_title) {
        return switch (chooseJobe_title) {
            case 1 -> Employee.Jobe_Title.LANTERNAGEM;
            case 2 -> Employee.Jobe_Title.PINTURA;
            case 3 -> Employee.Jobe_Title.ESTOFADOR;
            case 4 -> Employee.Jobe_Title.ELETRICISTA;
            case 5 -> Employee.Jobe_Title.MECANICO;
            case 6 -> Employee.Jobe_Title.TEC_REFRI;
            case 7 -> Employee.Jobe_Title.MANOBRISTA;
            case 8 -> Employee.Jobe_Title.JATISTA;
            case 9 -> Employee.Jobe_Title.BORRACHEIRO;
            case 10 -> Employee.Jobe_Title.ABASTECEDOR;
            default -> Employee.Jobe_Title.INEXISTENTE;
        };
    }

}
