package entity.politicas_de_cancelamento;

import domain.Evento;
import entity.PoliticaCancelamento;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PoliticaCancelamentoRigorosa implements PoliticaCancelamento {
    @Override
    public void executarPolitica(Evento evento) {

        LocalDateTime agora = LocalDateTime.now();
        long diasAteEvento = ChronoUnit.DAYS.between(agora, evento.getHorarioDeComecoDoEvento());

        if (diasAteEvento < 7) {
            System.out.println("Cancelamento não elegível para reembolso devido à política rigorosa.");
            return;
        }

        System.out.println("Cancelamento aprovado com 50% de reembolso.");
        //* service de pagamento para reembolsar 50%
    }
}
