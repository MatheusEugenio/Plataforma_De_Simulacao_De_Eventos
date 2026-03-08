package infra.notification;

import domain.model.Evento;

public class ObserverWhatsapp implements Observer {
    @Override
    public void reagir(Evento evento) {
        System.out.println("Notificação via Whatsapp, do evento "+evento.getNomeEvento()+", envida com sucesso!");
    }
}
