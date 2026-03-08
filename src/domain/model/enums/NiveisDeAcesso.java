package domain.model.enums;

public enum NiveisDeAcesso {

    PARTICIPANTE(1), // CARGO BAIXO, PÚBLICO
    FUNCIONARIO(2), // CARGO INTERMEDIÁRIO
    EMPRESARIO(3); // CARGO MAIS ALTO

    private final int peso;

    NiveisDeAcesso(int peso) {
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }
}
