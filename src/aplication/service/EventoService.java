package aplication.service;

import domain.model.Evento;
import domain.model.Palestra;
import domain.model.Participante;
import domain.valueobject.IntervaloDeTempo;
import domain.model.enums.StatusEvento;
import infra.notification.NotificationService;
import infra.repository.EventsRepository;

public class EventoService {

    private EventsRepository eventsRepository;
    private NotificationService notificador;

    public EventoService(EventsRepository eventsRepository, NotificationService notificationService){
        this.eventsRepository = eventsRepository;
        this.notificador = notificationService;
    }

    public void confirmarEvento(Long idEvento){

        Evento event = this.eventsRepository.buscarPorID(idEvento);

        event.confirmar();

        eventsRepository.save(event);
        this.notificador.notificar(event);
    }

    public void cancelarEvento(Long idEvento){

        Evento event = this.eventsRepository.buscarPorID(idEvento);

        event.cancelar();

        event.setStatus(StatusEvento.CANCELADO);

        eventsRepository.save(event);
        this.notificador.notificar(event);

        System.out.println("O evento '"+event.getNomeEvento()+"' - "+event.getID()+" foi cancelado!");
    }

    public void inscreverParticipanteNoEvento(Long IDEvento, Participante participante){

        Evento event = this.eventsRepository.buscarPorID(IDEvento);

        event.inscreverParticipanteNoEvento(participante);

        eventsRepository.save(event);
        this.notificador.notificar(event);
    }

    public void cancelarInscricaoNoEvento(Long IDEvento, Participante participante){

        Evento event = this.eventsRepository.buscarPorID(IDEvento);

        event.cancelarInscricaoDoParticipanteNoEvento(participante);

        this.eventsRepository.save(event);
        this.notificador.notificar(event);
    }

    public void adicionarPalestra(Long IdEvento, Palestra palestra){

        Evento event = this.eventsRepository.buscarPorID(IdEvento);

        event.adicionarPalestra(palestra);

        eventsRepository.save(event);
        this.notificador.notificar(event);
    }

    public void alterarDataDoEvento(Long IDEvento, IntervaloDeTempo novaDataDoEvento){

        Evento event = this.eventsRepository.buscarPorID(IDEvento);

        event.alterarData(novaDataDoEvento);

        this.eventsRepository.save(event);
        this.notificador.notificar(event);
    }

    public void excluirEvento(Long IDEvento){

        Evento event = eventsRepository.buscarPorID(IDEvento);

        event.cancelar();

        notificador.notificar(event);

        eventsRepository.delete(IDEvento);
    }

}
