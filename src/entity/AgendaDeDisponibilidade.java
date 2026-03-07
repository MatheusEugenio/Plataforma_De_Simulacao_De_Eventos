package entity;

import domain.Palestra;

import java.util.List;

public class AgendaDeDisponibilidade {

    private List<IntervaloDeTempo> intervalosDisponiveis; //lista de intervalos que ele pode palestrar

    public AgendaDeDisponibilidade(List<IntervaloDeTempo> disponibilidades){
        this.intervalosDisponiveis = disponibilidades;
    }

    public boolean palestranteDisponivel(IntervaloDeTempo intervaloSolicitado){
        return this.intervalosDisponiveis.stream()
            .anyMatch(intervalo -> intervalo.contem(intervaloSolicitado));
    }

    public boolean temConflitoComAgenda(List<Palestra> palestrasAgendadas, IntervaloDeTempo intervaloSolicitado){
        return palestrasAgendadas.stream()
                .map(Palestra::getHorario)
                .anyMatch(intervalo -> intervalo.conflitaCom(intervaloSolicitado));
    }
}
