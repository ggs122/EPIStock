package br.com.unifominventory.uniforminventory;

import java.util.Arrays;

public enum UniformInventoryImplEnum {

    UNIFORME_OFICINA(1),
    UNIFORME_ENCARREGADO(2),
    UNIFORME_INEXISTENTE(3);

    private final int uniformType;

    UniformInventoryImplEnum(int uniformType) {
        this.uniformType = uniformType;
    }

    public int getUniformType() {
        return uniformType;
    }

    public static UniformInventoryImplEnum returnUniformType (int chooseUniformType) {

        return switch (chooseUniformType) {
            case 1 -> UNIFORME_OFICINA;
            case 2 -> UNIFORME_ENCARREGADO;
            default -> UNIFORME_INEXISTENTE;
        };

    }



}
