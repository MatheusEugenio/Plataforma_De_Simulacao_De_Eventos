package infra.notification;

import domain.model.Evento;

//quem implementar estará "observando/esperando" ações de Evento
public interface Observers {
    void reagir(Evento evento);
}
