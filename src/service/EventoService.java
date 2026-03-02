package service;

import repository.EventsRepository;

//RASCUNHO DE CLASSE
public class EventoService {

    private EventsRepository repository;
    private NotificationService notifier;

    public EventoService(EventsRepository repository, NotificationService notificationService){
        this.repository = repository;
        this.notifier = notificationService;
    }

    public void criarEvento(){

        notifier.notificar();
    }

}
