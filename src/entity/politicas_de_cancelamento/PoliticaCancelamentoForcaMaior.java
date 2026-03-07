package entity.politicas_de_cancelamento;

import domain.Evento;
import entity.PoliticaCancelamento;

public class PoliticaCancelamentoForcaMaior implements PoliticaCancelamento {
    @Override
    public void executarPolitica(Evento evento) {
        System.out.println("Cancelamento por Força Maior. Reembolso total emitido para todos os participantes.");

        // Disparar notificações urgentes para participantes
    }
}
