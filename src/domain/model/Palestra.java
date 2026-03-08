package domain.model;

import domain.valueobject.IntervaloDeTempo;
import domain.valueobject.Name;
import domain.model.enums.StatusPalestra;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Palestra {

    private Long ID;
    private Name nome;
    private Palestrante palestrante;
    private IntervaloDeTempo horario;
    private Sala salaDaPalestra;
    private int limiteDeParticipantes;
    private StatusPalestra status;
    private Set<Participante> inscritosNaPalestra;

    public Palestra(Sala salaDaPalestra, IntervaloDeTempo horario, Palestrante palestrante, Name nome, Long ID) {
        validateSala(salaDaPalestra);
        validateDuracao(horario);
        validatePalestrante(palestrante);
        validateID(ID);
        validateName(nome);
        validateCapacidade(limiteDeParticipantes);
        this.salaDaPalestra = salaDaPalestra;
        this.horario = horario;
        this.palestrante = palestrante;
        this.ID = ID;
        this.nome = nome;
        this.limiteDeParticipantes = salaDaPalestra.getCapacidadeDaSala();
        this.status = StatusPalestra.VAZIA;
        this.inscritosNaPalestra = new HashSet<>();
    }

    public void usarSala(){
        mudarStatus(StatusPalestra.EM_ANDAMENTO);
    }

    public void inscreverNaPalestra(Participante participante){
        Objects.requireNonNull(participante, "Erro: o participante é nulo.");

        if (this.inscritosNaPalestra.size() == limiteDeParticipantes){
            throw new IllegalArgumentException("A palestra já está lotada!");
        }

        if (!this.inscritosNaPalestra.add(participante)) {
            throw new IllegalArgumentException("O participante já está inscrito na palestra!");
        }
    }

    public void mudarStatus(StatusPalestra status){this.status = status;}

    public boolean conflitaComAlgumaPalestra(IntervaloDeTempo intervaloNovo){
        return horario.conflitaCom(intervaloNovo);
    }

    public boolean horariEstaDisponivel(IntervaloDeTempo horarioEstabelecido){
        return horario.contem(horarioEstabelecido);
    }

    private void validateSala(Sala sala){Objects.requireNonNull(sala, "Erro: o objeto de sala é nulo.");}
    private void validateDuracao(IntervaloDeTempo tempo){Objects.requireNonNull(tempo, "Erro: o tempo é nulo.");}
    private void validatePalestrante(Palestrante palestrante){Objects.requireNonNull(palestrante, "Erro: o palestrante é nulo.");}
    private void validateID(Long id) {Objects.requireNonNull(id, "Erro, ID é não pode ser nulo.");}
    private void validateName(Name name) {Objects.requireNonNull(name, "Erro: nome passado para participante é nulo.");}
    private void validateCapacidade(int capacidadeDaSala) {if (capacidadeDaSala <= 0){ throw new IllegalArgumentException("Erro: o número máximo de participantes da palestra não pode ser menor ou igual a 0."); }}

    public Set<Participante> getInscritosNaPalestra() {return inscritosNaPalestra;}
    public IntervaloDeTempo getHorario() {return horario;}
}