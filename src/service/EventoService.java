package service;

import domain.Evento;
import domain.Palestra;
import domain.Participante;
import entity.IntervaloDeTempo;
import entity.status.StatusEvento;
import repository.EventsRepository;

public class EventoService {

    private EventsRepository eventsRepository;
    private NotificationService notifier;

    public EventoService(EventsRepository eventsRepository, NotificationService notificationService){
        this.eventsRepository = eventsRepository;
        this.notifier = notificationService;
    }

    public void confirmarEvento(Long idEvento){

        Evento event = this.eventsRepository.buscarPorID(idEvento);

        event.confirmar();

        eventsRepository.save(event);
        this.notifier.notificar(event);
    }

    public void cancelarEvento(Long idEvento){

        Evento event = this.eventsRepository.buscarPorID(idEvento);

        event.cancelar();

        event.setStatus(StatusEvento.CANCELADO);
        eventsRepository.save(event);
        this.notifier.notificar(event);

        System.out.println("O evento '"+event.getNomeEvento()+"' - "+event.getID()+" foi cancelado!");
    }

    public void inscreverParticipanteNoEvento(Long IDEvento, Participante participante){

        Evento event = this.eventsRepository.buscarPorID(IDEvento);

        event.inscreverParticipante(participante);

        eventsRepository.save(event);
        this.notifier.notificar(event);
    }

    public void cancelarInscricaoNoEvento(Long IDEvento, Participante participante){

        Evento event = this.eventsRepository.buscarPorID(IDEvento);

        event.cancelarInscricao(participante);

        this.eventsRepository.save(event);
        this.notifier.notificar(event);
    }

    public void adicionarPalestra(Long IdEvento, Palestra palestra){

        Evento event = this.eventsRepository.buscarPorID(IdEvento);

        event.adicionarPalestra(palestra);

        eventsRepository.save(event);
        this.notifier.notificar(event);
    }

    public void alterarDataDoEvento(Long IDEvento, IntervaloDeTempo novaDataDoEvento){

        Evento event = this.eventsRepository.buscarPorID(IDEvento);

        event.alterarData(novaDataDoEvento);

        this.eventsRepository.save(event);
        this.notifier.notificar(event);
    }

    public void excluirEvento(Long IDEvento){

        Evento event = eventsRepository.buscarPorID(IDEvento);

        event.cancelar();

        notifier.notificar(event);

        eventsRepository.delete(IDEvento);
    }

}
