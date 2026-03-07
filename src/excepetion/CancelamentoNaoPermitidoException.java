package excepetion;

public class CancelamentoNaoPermitidoException extends RuntimeException {
    public CancelamentoNaoPermitidoException(String message) {
        super(message);
    }
}
