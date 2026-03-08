package infra.notification;

import domain.model.Evento;

//* quem implementar estará "observando/esperando" ações de Evento
public interface Observer {
    void reagir(Evento evento);
}
