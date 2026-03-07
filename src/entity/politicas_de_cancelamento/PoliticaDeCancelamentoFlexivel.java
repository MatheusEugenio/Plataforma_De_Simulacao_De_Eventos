package entity.politicas_de_cancelamento;

import domain.Evento;
import entity.PoliticaCancelamento;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PoliticaDeCancelamentoFlexivel implements PoliticaCancelamento {
    @Override
    public void executarPolitica(Evento evento) {

        LocalDateTime tempoExatoDoCancelamento = LocalDateTime.now();

        long horasAteEvento = ChronoUnit.HOURS.between(tempoExatoDoCancelamento, evento.getHorarioDeComecoDoEvento());

        if (horasAteEvento >= 24){
            System.out.println("Cancelamento fora do prazo para reembolso total.");
            return;
        }

        System.out.println("Cancelamento realizado com sucesso!");

        //* service de pagamento para reembolso
    }
}
