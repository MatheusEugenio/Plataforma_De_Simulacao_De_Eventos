package politicas_de_cancelamento;

import domain.Evento;

public class PoliticaCancelamentoLivre implements PoliticaCancelamento {
    @Override
    public void executarPolitica(Evento evento) {
        System.out.println("Cancelamento realizado com sucesso.");
    }
}
