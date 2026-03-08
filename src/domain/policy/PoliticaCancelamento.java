package domain.policy;

import domain.model.Evento;

public interface PoliticaCancelamento {
    void executarPolitica(Evento evento);
}
