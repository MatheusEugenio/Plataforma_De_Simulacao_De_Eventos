package service;

import domain.Palestra;
import domain.Participante;
import entity.IntervaloDeTempo;
import repository.EventsRepository;

public class EventoService {

    private EventsRepository repository;
    private NotificationService notifier;

    public EventoService(EventsRepository repository, NotificationService notificationService){
        this.repository = repository;
        this.notifier = notificationService;
    }

    public void confirmarEvento(Long idEvento){

    }

    public void cancelarEvento(Long idEvento, String motivo){

    }

    public void inscreverParticipanteNoEvento(Long IDEvento, Participante participante){

    }

    public void cancelarInscricaoNoEvento(Long IDEvento, Participante participante){

    }

    public void adicionarPalestra(Palestra palestra){

    }

    public void alterarDataDoEvento(Long IDEvento, IntervaloDeTempo novaDataDoEvento){

    }
}
