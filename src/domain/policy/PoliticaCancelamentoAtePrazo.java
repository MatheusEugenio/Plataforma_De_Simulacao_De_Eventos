package domain.policy;

import domain.model.Evento;
import domain.exception.CancelamentoNaoPermitidoException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PoliticaCancelamentoAtePrazo implements PoliticaCancelamento {
    @Override
    public void executarPolitica(Evento evento) {

        LocalDateTime agora = LocalDateTime.now();

        long horasAteEvento = ChronoUnit.HOURS.between(agora, evento.getHorarioDeComecoDoEvento());

        if (horasAteEvento < 48) {
            throw new CancelamentoNaoPermitidoException("Cancelamento não elegível para reembolso devido à política rigorosa.");
        }
    }
}
