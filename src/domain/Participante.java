package domain;

import domain.objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Participante {

    private Long ID;
    private Name name;
    private Email email;
    private Cargo cargo;
    private NiveisDeAcesso nivelDeAcesso;
    private List<Evento> historicoDeEventos= new ArrayList<>();
    private StatusParticipante status;

    public Participante(Long ID, Name name, Email email, Cargo cargo, NiveisDeAcesso nivelDeAcesso, List<Evento> historicoDeEventos ) {
        validateID(ID);
        validateName(name);
        validateEmail(email);
        validateCargo(cargo);
        validateNivelDeAcesso(nivelDeAcesso);
        this.historicoDeEventos = historicoDeEventos;
        this.status = StatusParticipante.ATIVO;
    }

    public void adicionarEventoAoHistorico(Evento evento) {
        Objects.requireNonNull(evento, "Erro: evento não pode ser nulo.");
        historicoDeEventos.add(evento);
    }

    public void mudarStatus(StatusParticipante statusREF) {
        this.status = statusREF;
    }

    private void validateID(Long id) {Objects.requireNonNull(id, "Erro, ID é não pode ser nulo.");this.ID = id;}
    private void validateEmail(Email email) {Objects.requireNonNull(email, "Erro: o email do participante é nulo.");this.email = email;}
    private void validateName(Name name) {Objects.requireNonNull(name, "Erro: nome passado para participante é nulo.");this.name = name;}
    private void validateNivelDeAcesso(NiveisDeAcesso nivelDeAcesso) {Objects.requireNonNull(nivelDeAcesso, "Erro: nível de acesso do participante é nulo.");this.nivelDeAcesso = nivelDeAcesso;}
    private void validateCargo(Cargo cargo) {Objects.requireNonNull(cargo, "Erro: cargo do participante é nulo.");this.cargo = cargo;}
}
