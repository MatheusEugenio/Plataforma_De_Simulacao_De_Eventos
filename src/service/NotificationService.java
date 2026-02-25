package service;

import domain.Evento;
import domain.ObserverNotification;

import java.util.List;
import java.util.Objects;

public class NotificationService {

    private final List<ObserverNotification> observadores;

    public NotificationService(List<ObserverNotification> observers) {
        this.observadores = observers;
    }

    //* Metodo de rascunho
    public void addObserver(ObserverNotification o) {
        Objects.requireNonNull(o, "Erro: observer não pode ser nulo!");
        observadores.add(o);
    }

    private void notificar(Evento evento) {
        this.observadores.forEach(obs -> obs.reagir(evento));
    }
}
