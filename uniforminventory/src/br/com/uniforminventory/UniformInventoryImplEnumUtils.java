package br.com.uniforminventory;

public final class UniformInventoryImplEnumUtils {

    private UniformInventoryImplEnumUtils() {}

    public enum UniformType {
        UNIFORME_OFICINA,
        UNIFORME_ENCARREGADO,
        UNIFORME_INEXISTENTE;

        public static UniformType returnUniformType (int chooseUniformType) {

            return switch (chooseUniformType) {
                case 1 -> UNIFORME_OFICINA;
                case 2 -> UNIFORME_ENCARREGADO;
                default -> UNIFORME_INEXISTENTE;
            };

        }
    }

    public enum UniformSize {
        G,
        GG,
        M,
        P,
        PP,
        TAMANHO_INEXISTENTE;

        public static UniformSize returnUniformSize(int chooseUniformSize) {
            return switch(chooseUniformSize) {
                case 1 -> G;
                case 2 -> GG;
                case 3 -> M;
                case 4 -> P;
                case 5 -> PP;
                default -> TAMANHO_INEXISTENTE;
            };
        }
    }

}
