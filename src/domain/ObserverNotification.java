package domain;

//quem implementar estará "observando/esperando" ações de Evento
public interface ObserverNotification {
    void reagir(Evento evento);
}
