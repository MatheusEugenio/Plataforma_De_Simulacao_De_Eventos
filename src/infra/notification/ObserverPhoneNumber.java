package infra.notification;

import domain.model.Evento;

public class ObserverPhoneNumber implements Observer {
    @Override
    public void reagir(Evento evento) {
        System.out.println("Notificação via número de telefone, do evento "+evento.getNomeEvento()+", envida com sucesso!");

    }
}
