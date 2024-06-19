package com.udesc;

public enum Option {
    CREATE_EMPLOYEE("Criar funcionário"),
    UPDATE_EMPLOYEE("Editar funcionário"),
    DELETE_EMPLOYEE("Remover funcionário"),
    SELECT_EMPLOYEE("Selecionar funcionário"),
    READ_EMPLOYEE("Ver funcionário selecionado"),
    ADD_CERTIFICATE("Adicionar atestado para o funcionário selecionado"),
    REMOVE_CERTIFICATE("Remover atestado do funcionário selecionado"),
    READ_CERTIFICATES("Ver atestados do funcionário selecionado"),
    ADD_ABSENCE("Adicionar falta para o funcionário selecionado"),
    REMOVE_ABSENCE("Remover falta do funcionário selecionado"),
    READ_ABSENCES("Ver faltas do funcionário selecionado"),
    TRACK_TIME("Realizar apontamento para o funcionário selecionado"),
    QUIT("Sair");

    private final String name;

    private Option(String name) {
        this.name = name;
    }

    public static Option at(Integer position) throws IndexOutOfBoundsException {
        return position == null ? null : values()[position];
    }

    public String getName() {
        return name;
    }
}
