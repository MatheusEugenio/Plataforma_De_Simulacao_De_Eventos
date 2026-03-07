package politicas_de_cancelamento;

import domain.Evento;

public interface PoliticaCancelamento {
    void executarPolitica(Evento evento);
}
