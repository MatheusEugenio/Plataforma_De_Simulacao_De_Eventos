package domain.model;

import domain.valueobject.AgendaDeDisponibilidade;
import domain.model.enums.Especialidade;
import domain.valueobject.IntervaloDeTempo;
import domain.valueobject.Name;

import java.util.List;
import java.util.Objects;

public class Palestrante {

    private Long ID;
    private Name name;
    private Especialidade especialidade;
    private String bio;
    private AgendaDeDisponibilidade agenda;
    private List<Palestra> palestrasAgendadas;

    public Palestrante(Long ID, Name name, Especialidade especialidade, String bio, AgendaDeDisponibilidade agenda,
                       List<Palestra> palestras) {
        validateID(ID);
        validateName(name);
        validateEspecialidade(especialidade);
        validateBio(bio);
        validateAgenda(agenda);
        this.palestrasAgendadas = palestras;
    }

    public boolean estaDisponivel(IntervaloDeTempo intervaloSolicitado){
        Objects.requireNonNull(intervaloSolicitado, "Erro: o intervalo solicitado não pode ser nulo.");

       boolean disponivel = agenda.palestranteDisponivel(intervaloSolicitado);

        if (!disponivel) {
            return false;
        }

        boolean conflitoComAgenda = this.agenda.temConflitoComAgenda(this.palestrasAgendadas, intervaloSolicitado);

        return !conflitoComAgenda;
    }

    private void validateAgenda(AgendaDeDisponibilidade agenda){
        Objects.requireNonNull(agenda,"Erro: agenda não pode ser nula.");
        this.agenda = agenda;
    }
    private void validateID(Long id) {
        Objects.requireNonNull(id, "Erro, ID é não pode ser nulo.");
        this.ID = id;}
    private void validateName(Name name) {
        Objects.requireNonNull(name, "Erro: nome passado para participante é nulo.");
        this.name = name;}
    private void validateBio(String bio) {
        Objects.requireNonNull(bio, "Erro: bio do participante não pode ser nula.");
        this.bio = bio;}
    private void validateEspecialidade(Especialidade especialidade) {
        Objects.requireNonNull(especialidade, "Erro: especialidade do palestrante não pode ser nula.");
        this.especialidade = especialidade;}
}
