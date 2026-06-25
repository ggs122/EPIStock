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

    public static String returIdDefault(String id) {
        String idIntern = "";
       boolean isId = id.trim().matches("^\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}?");
       if (isId) {
           idIntern = id;
       } else {
           IO.println("------------------------------");
           IO.println("Número de identidade inválido");
           idIntern = "Inválido";
           IO.println("------------------------------");
       }
       return idIntern;
    }

    public static String returnCpfDefault(String cpf) {
        String cpfIntern = "";
       boolean isCpf = cpf.trim().matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}?");
       if (isCpf) {
           cpfIntern = cpf;
       } else {
           IO.println("----------------------");
           IO.println("Número de CPF inválido");
           cpfIntern = "Inválido";
           IO.println("----------------------");
       }
       return cpfIntern;
    }

//    static void main(String[] args) {
//        System.out.println(returnCpfDefault("102.286.867-50"));
//    }

}
