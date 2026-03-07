package politicas_de_cancelamento;

import domain.Evento;
import entity.PoliticaCancelamento;

public class PoliticaCancelamentoLivre implements PoliticaCancelamento {
    @Override
    public void executarPolitica(Evento evento) {
        System.out.println("Cancelamento realizado com sucesso.");
    }
}
