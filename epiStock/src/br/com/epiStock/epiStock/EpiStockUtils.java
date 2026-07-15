package br.com.epiStock.epiStock;

public final class EpiStockUtils {

    private EpiStockUtils() {}

    public static boolean chechingRegex(String ppeProductCode, String ppeCa) {

        boolean isSucessifull;

        boolean isPpeProductCode  = ppeProductCode.matches("Ref\\.[0-9]{2}\\.[0-9]{3}\\.[0-9]{2}");
        boolean isppeCa = ppeCa.matches("CA - ([0-9])+");

        if (isPpeProductCode) {
            isSucessifull = true;
            if (isppeCa) {
                isSucessifull = true;
                if (isPpeProductCode && isppeCa) {
                    IO.println(String.format("EPI código %s com C.A %s cadastrado com sucesso! ", ppeProductCode, ppeCa));
                    isSucessifull = true;
                } else {
                    IO.println(String.format("Código do produto %s e número do C.A %s -> Inválidos", ppeProductCode, ppeCa));
                    isSucessifull = false;
                }
            } else {
                IO.println(String.format("Número do C.A %s -> Inválido", ppeCa));
                isSucessifull = false;
            }
        } else {
            IO.println(String.format("Código do produto %s -> Inválido", ppeProductCode));
            isSucessifull = false;
        }

        return isSucessifull;

    }
}
