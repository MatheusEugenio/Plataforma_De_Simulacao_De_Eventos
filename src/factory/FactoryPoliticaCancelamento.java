package factory;

import entity.PoliticaCancelamento;
import entity.TipoEvento;
import politicas_de_cancelamento.PoliticaCancelamentoAtePrazo;
import politicas_de_cancelamento.PoliticaCancelamentoComMulta;
import politicas_de_cancelamento.PoliticaCancelamentoLivre;
import politicas_de_cancelamento.PoliticaDeCancelamentoRestrito;

import java.util.Map;
import java.util.Objects;

public class FactoryPoliticaCancelamento {

    private static final Map<TipoEvento, PoliticaCancelamento> STRATEGY_politicaCancelamentos = Map.of(
        TipoEvento.EVENTO_GRATUITO, new PoliticaCancelamentoLivre(),
            TipoEvento.EVENTO_PAGO, new PoliticaCancelamentoAtePrazo(),
            TipoEvento.EVENTO_CORPORATIVO, new PoliticaDeCancelamentoRestrito(),
            TipoEvento.EVENTO_PREMIUM, new PoliticaCancelamentoComMulta()
    );

    public static PoliticaCancelamento createPoliticaCancelamento(TipoEvento tipoEvento) {
        Objects.requireNonNull(tipoEvento, "Erro: tipo de evento é nulo.");

        PoliticaCancelamento politica =  STRATEGY_politicaCancelamentos.get(tipoEvento);

        if (politica == null) {
            throw new IllegalArgumentException("Erro: a política encontrada é nula!");
        }

        return politica;
    }
}
