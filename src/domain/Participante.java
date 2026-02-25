package domain;

import domain.objects.Email;
import domain.objects.Name;
import domain.objects.NiveisDeAcesso;
import domain.objects.StatusParticipante;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Participante {

    private Long ID;
    private Name name;
    private Email email;
    private String cargo;
    private NiveisDeAcesso nivelDeAcesso;
    private List<Evento> historicoDeEventos= new ArrayList<>();
    private StatusParticipante status;

    public Participante(Long ID, Name name, Email email, String cargo, NiveisDeAcesso nivelDeAcesso, List<Evento> historicoDeEventos ) {
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
    private void validateCargo(String cargo) {
        Objects.requireNonNull(cargo, "Erro: o cargo do participante é nulo.");

        if (cargo.trim().isBlank()){
            throw new IllegalArgumentException("Erro: o cargo ficou vazio.");
        }

        if (!cargo.matches("^[a-zA-ZÀ-ÿ ]+$")){
            throw new IllegalArgumentException("Erro: o cargo só deve conter letras e espaços!");
        }

        this.cargo = cargo;
    }
}
