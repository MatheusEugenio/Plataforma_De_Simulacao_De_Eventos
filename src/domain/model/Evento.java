package domain.model;

import domain.exception.ParticipanteInvalidoException;
import domain.model.enums.NiveisDeAcesso;
import domain.valueobject.IntervaloDeTempo;
import domain.valueobject.Name;
import domain.policy.PoliticaCancelamento;
import domain.model.enums.TipoEvento;
import domain.model.enums.StatusEvento;
import domain.exception.ConflitoHorarioException;
import domain.policy.FactoryPoliticaCancelamento;

import java.time.LocalDateTime;
import java.util.*;

public class Evento {

    private static Long contador = 1L;

    private Long ID;
    private Name nomeEvento;
    private String descricao;
    private TipoEvento tipo;
    private IntervaloDeTempo dataDeDuracaoDoEvento;
    private StatusEvento status;
    private int capacidadeMaxima;
    private PoliticaCancelamento politicaCancelamento;

    private Set<Participante> listaDeInscritos;
    private Queue<Participante> listaDeEspera;
    private List<Palestra> listaDePalestras;

    public Evento(
            Name nomeEvento,
            String descricao,
            TipoEvento tipo,
            IntervaloDeTempo dataDeDuracaoDoEvento,
            int capacidadeMaxima
    ) {
        this.ID = contador++;

        this.nomeEvento = Objects.requireNonNull(nomeEvento);
        this.descricao = Objects.requireNonNull(descricao);
        this.tipo = Objects.requireNonNull(tipo);
        this.dataDeDuracaoDoEvento = Objects.requireNonNull(dataDeDuracaoDoEvento);
        this.capacidadeMaxima = capacidadeMaxima;
        this.politicaCancelamento = FactoryPoliticaCancelamento.createPoliticaCancelamento(this.tipo);

        this.status = StatusEvento.PLANEJADO;

        this.listaDeInscritos = new HashSet<>();
        this.listaDePalestras = new ArrayList<>();
        this.listaDeEspera = new LinkedList<>();
    }

    public void confirmar(){
        if (this.status == StatusEvento.FINALIZADO){
            throw new IllegalArgumentException("Erro: o evento já finalizado.");
        }

        if (this.status == StatusEvento.CANCELADO){
            throw new IllegalArgumentException("Erro: o evento está cancelado.");
        }

        this.status = StatusEvento.CONFIRMADO;
    }

    public void cancelar(){
        if (this.status == StatusEvento.FINALIZADO){
            throw new IllegalArgumentException("Erro: o evento já finalizado.");
        }

        if (this.status == StatusEvento.CANCELADO){
            throw new IllegalArgumentException("Erro: o evento já está cancelado.");
        }

        politicaCancelamento.executarPolitica(this);
        this.status = StatusEvento.CANCELADO;
    }

    public synchronized void inscreverParticipanteNoEvento(Participante participante){
        Objects.requireNonNull(participante, "Erro: o participante é nulo");

        if (listaDePalestras.isEmpty()) {
            throw new IllegalArgumentException("Erro: não haverá palestras neste evento.");
        }

        if (listaDeInscritos.contains(participante)){
            throw new ParticipanteInvalidoException("Não pode inscrever participantes repetidos!");
        }

        boolean podeParticipar = validarNivelDeAcessoDoParticipante(participante.getNivelDeAcesso());

        if (!podeParticipar){
            throw new ParticipanteInvalidoException("Erro: o participante não pode participar pois não possui nível de acesso esperado para o evento. "+participante.getNivelDeAcesso()+" < "+ tipo);
        }

        if (listaDeInscritos.size() >= capacidadeMaxima){
            System.out.println("A lista de participantes já está lotada! O participante '"+participante+"' foi movido para a lista de espera.");
            promoverParaListaDeEspera(participante);
            return;
        }

        participante.adicionarEventoAoHistorico(this);
        this.listaDeInscritos.add(participante);
    }

    public void cancelarInscricaoDoParticipanteNoEvento(Participante participante){
        Objects.requireNonNull(participante, "Erro: o participante é nulo.");

        if (!this.listaDeInscritos.contains(participante)){
            throw new IllegalArgumentException("Erro: particpante não está inscrito.");
        }

        participante.removerEventoDoHistorico(this);
        this.listaDeInscritos.remove(participante);
        promoverParaListaDeEspera(participante);
    }

    public void adicionarPalestra(Palestra palestra){
        Objects.requireNonNull(palestra, "Erro: a palestra não pode ser nula.");

        if (this.listaDePalestras.contains(palestra)){
            throw new IllegalArgumentException("Erro: a palestra já está na cadastrada.");
        }

        boolean possuiPalestraNoMesmoHorario = this.listaDePalestras.stream()
                .anyMatch(p -> p.conflitaComAlgumaPalestra(palestra.getHorario()));

        if (possuiPalestraNoMesmoHorario){
            throw new ConflitoHorarioException("Erro: já possui uma palestra nesse horário.");
        }

        this.listaDePalestras.add(palestra);
    }

    public void alterarData(IntervaloDeTempo novaData){
        Objects.requireNonNull(novaData, "Erro: a nova data é nula.");

        this.dataDeDuracaoDoEvento.setInicio(novaData.getInicio());
        this.dataDeDuracaoDoEvento.setFim(novaData.getFim());
    }

    public boolean validarNivelDeAcessoDoParticipante(NiveisDeAcesso nivelDeAcessoDoParticipante){

        if (nivelDeAcessoDoParticipante == NiveisDeAcesso.PARTICIPANTE  && tipo == TipoEvento.EVENTO_PAGO){ //exceção
            return true;
        }

        if (nivelDeAcessoDoParticipante.getPeso() < tipo.getPeso()){
            return false;
        }

        return true;
    }

    private void promoverParaListaDeEspera(Participante participante){
        if (this.listaDeEspera.contains(participante)){
            throw new IllegalArgumentException("Erro: participante '"+participante+"' já está na lista de espera.");
        }

        this.listaDeEspera.add(participante);
    }

    public String getNomeEvento() {return nomeEvento.getValue();}
    public Long getID(){return ID;}
    public void setStatus(StatusEvento status){this.status = status;}
    public LocalDateTime getHorarioDeComecoDoEvento(){return this.dataDeDuracaoDoEvento.getInicio();}
}
