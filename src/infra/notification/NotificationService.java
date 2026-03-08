package infra.notification;

import domain.model.Evento;

import java.util.List;
import java.util.Objects;

public class NotificationService {

    private final List<Observer> observadores;

    public NotificationService(List<Observer> observers) {
        this.observadores = observers;
    }

    public void addObserver(Observer o) {
        observadores.add(Objects.requireNonNull(o, "Erro: observer não pode ser nulo!"));
    }

    public void removerObserver(Observer o){
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
