package service;

import domain.Evento;
import entity.ObserverNotification;

import java.util.List;
import java.util.Objects;

public class NotificationService {

    private final List<ObserverNotification> observadores;

    public NotificationService(List<ObserverNotification> observers) {
        this.observadores = observers;
    }

    public void addObserver(ObserverNotification o) {
        observadores.add(Objects.requireNonNull(o, "Erro: observer não pode ser nulo!"));
    }

    public void removerObserver(ObserverNotification o){
        Objects.requireNonNull(o, "Erro: observer não pode ser nulo!");

        if (!this.observadores.contains(o)) {
            throw new IllegalArgumentException("Erro: esse observer não está na lista.");
        }
        observadores.remove(o);
    }

    public void notificar(Evento evento) {
        this.observadores.forEach(obs -> obs.reagir(evento));
    }
}
