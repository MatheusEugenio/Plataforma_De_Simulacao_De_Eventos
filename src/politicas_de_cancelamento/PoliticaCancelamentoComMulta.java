package politicas_de_cancelamento;

import domain.Evento;
import entity.PoliticaCancelamento;
import exception.CancelamentoNaoPermitidoException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PoliticaCancelamentoComMulta implements PoliticaCancelamento {

    @Override
    public void executarPolitica(Evento evento) {
        BigDecimal valMulta = new BigDecimal("0");

        LocalDateTime agora = LocalDateTime.now();

        long horasQueFaltamAteEvento = ChronoUnit.HOURS.between(agora, evento.getHorarioDeComecoDoEvento());

        if (horasQueFaltamAteEvento < 12) {
            throw new CancelamentoNaoPermitidoException("Cancelamento não permitido nas últimas 12 horas antes do evento.");
        }

        if (horasQueFaltamAteEvento < 48 ) {
            valMulta = BigDecimal.valueOf(0.20); // 20% de multa
        }

        if (valMulta.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Multa aplicada: " + (valMulta.multiply(new BigDecimal(100))) + "%");
        }
    }
}
