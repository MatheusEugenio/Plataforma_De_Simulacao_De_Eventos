package domain.policy;

import domain.model.Evento;

public class PoliticaCancelamentoLivre implements PoliticaCancelamento {
    @Override
    public void executarPolitica(Evento evento) {
        System.out.println("Cancelamento realizado com sucesso.");
    }
}
