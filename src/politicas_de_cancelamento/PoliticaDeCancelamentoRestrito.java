package politicas_de_cancelamento;

import domain.Evento;
import entity.PoliticaCancelamento;
import exception.CancelamentoNaoPermitidoException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PoliticaDeCancelamentoRestrito implements PoliticaCancelamento {
    @Override
    public void executarPolitica(Evento evento) {

        LocalDateTime tempoExatoDoCancelamento = LocalDateTime.now();

        long horasAteEvento = ChronoUnit.HOURS.between(tempoExatoDoCancelamento, evento.getHorarioDeComecoDoEvento());

        if (horasAteEvento < 24){
            throw new CancelamentoNaoPermitidoException("Cancelamento fora do prazo para reembolso total.");
        }
    }
}
