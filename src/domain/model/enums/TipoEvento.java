package domain.model.enums;

public enum TipoEvento {

    EVENTO_GRATUITO(1),
    EVENTO_PAGO(2),
    EVENTO_CORPORATIVO(3),
    EVENTO_PREMIUM(4);

    private final int peso;

    TipoEvento(int peso) {
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }
}

