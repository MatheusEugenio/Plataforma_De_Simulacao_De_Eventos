package infra.notification;

import domain.model.Evento;

public class ObserverEmail implements Observer {
    @Override
    public void reagir(Evento evento) {
        System.out.println("Notificação via Email, do evento "+evento.getNomeEvento()+", envida com sucesso!");
    }
}
