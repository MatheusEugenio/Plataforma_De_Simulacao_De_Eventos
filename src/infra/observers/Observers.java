package infra.observers;

import domain.Evento;

//quem implementar estará "observando/esperando" ações de Evento
public interface Observers {
    void reagir(Evento evento);
}
