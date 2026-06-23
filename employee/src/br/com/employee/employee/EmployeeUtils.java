package br.com.employee.employee;

public final class EmployeeUtils {

    private EmployeeUtils () {}

    public static EmployeeImpl.Jobe_Title returnJoble_Title (int chooseJobe_title) {
        return switch (chooseJobe_title) {
            case 1 -> EmployeeImpl.Jobe_Title.LANTERNAGEM;
            case 2 -> EmployeeImpl.Jobe_Title.PINTURA;
            case 3 -> EmployeeImpl.Jobe_Title.ESTOFADOR;
            case 4 -> EmployeeImpl.Jobe_Title.ELETRICISTA;
            case 5 -> EmployeeImpl.Jobe_Title.MECANICO;
            case 6 -> EmployeeImpl.Jobe_Title.TEC_REFRI;
            case 7 -> EmployeeImpl.Jobe_Title.MANOBRISTA;
            case 8 -> EmployeeImpl.Jobe_Title.JATISTA;
            case 9 -> EmployeeImpl.Jobe_Title.BORRACHEIRO;
            case 10 -> EmployeeImpl.Jobe_Title.ABASTECEDOR;
            default -> EmployeeImpl.Jobe_Title.INEXISTENTE;
        };
    }

    public static EmployeeImpl.Status returnStatus (int chooeseStatus) {
        return switch (chooeseStatus) {
          case 1 -> EmployeeImpl.Status.Ativo;
          case 2 -> EmployeeImpl.Status.Inativo;
            default -> EmployeeImpl.Status.INEXISTENTE;
        };
    }

}
