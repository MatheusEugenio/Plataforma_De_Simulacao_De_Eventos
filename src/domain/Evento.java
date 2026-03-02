package domain;

import domain.objects.IntervaloDeTempo;
import domain.objects.Name;
import domain.objects.StatusEvento;

import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class Evento {

    private Long ID;
    private Name nomeEvento;
    private String descricao;
    private String tipo;// futuramente pode ser criado uma abstração ou enum para isso
    private IntervaloDeTempo dataDeDuracaoDoEvento;
    private StatusEvento status;
    private int capacidadeMaxima;

    private Set<Participante> listaDeInscritos;
    private Queue<Participante> listaDeEspera;
    private List<Palestra> listaDePalestras;

    public Evento(int capacidadeMaxima, StatusEvento status, IntervaloDeTempo dataDeDuracaoDoEvento,
                  String tipo, String descricao, Name nomeEvento, Long ID) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.status = status;
        this.dataDeDuracaoDoEvento = dataDeDuracaoDoEvento;
        this.tipo = tipo;
        this.descricao = descricao;
        this.nomeEvento = nomeEvento;
        this.ID = ID;
    }

    public void confirmar(){
        if (this.status == StatusEvento.FINALIZADO){
            throw new IllegalArgumentException("Erro: o evento já finalizado.");
        }

        if (this.status == StatusEvento.CANCELADO){
            throw new IllegalArgumentException("Erro: o evento está cancelado.");
        }

        this.status = StatusEvento.CONFIRMADO;
        notificar();
    }

    public void cancelar(){
        if (this.status == StatusEvento.FINALIZADO){
            throw new IllegalArgumentException("Erro: o evento já finalizado.");
        }

        this.status = StatusEvento.CANCELADO;
        //politicas de cancelamento
        notificar();
    }

    public void inscreverParticipante(Participante participante){
        Objects.requireNonNull(participante, "Erro: o participante é nulo");

        if (this.listaDePalestras.isEmpty()) {
            throw new IllegalArgumentException("Erro: não haverá palestras neste evento.");
        }

        if (this.listaDeInscritos.size() >= this.capacidadeMaxima){
            System.out.println("A lista de participantes já está lotada! O participante '"+participante+"' foi movido para a lista de espera.");
            promoverParaListaDeEspera(participante);
            return;
        }

        //verificar se tem horarios de palestra disponivel

        if (this.listaDeInscritos.add(participante)) {
            notificar();
        } else {
            System.out.println("Não pode inscrever participantes repetidos!");
        }
    }

    public void cancelarInscricao(Participante participante){
        Objects.requireNonNull(participante, "Erro: o participante é nulo.");

        if (!this.listaDeInscritos.contains(participante)){
            throw new IllegalArgumentException("Erro: particpante não está inscrito.");
        }

        this.listaDeInscritos.remove(participante);
        promoverParaListaDeEspera(participante);
        notificar();
    }

    public void promoverParaListaDeEspera(Participante participante){
        if (this.listaDeEspera.contains(participante)){
            throw new IllegalArgumentException("Erro: participante '"+participante+"' já está na lista de espera.");
        }

        this.listaDeEspera.add(participante);
    }

    public void adicionarPalestra(Palestra palestra){
        Objects.requireNonNull(palestra, "Erro: a palestra não pode ser nula.");

        if (this.listaDePalestras.contains(palestra)){
            throw new IllegalArgumentException("Erro: a palestra já está na cadastrada.");
        }

        boolean possuiPalestraNoMesmoHorario = this.listaDePalestras.stream()
                .anyMatch(p -> p.conflitaComAlgumaPalestra(palestra.getHorario()));

        if (possuiPalestraNoMesmoHorario){
            throw new IllegalArgumentException("Erro: já possui uma palestra nesse horário.");
        }

        this.listaDePalestras.add(palestra);
    }

    public void alterarData(IntervaloDeTempo novaData){
        Objects.requireNonNull(novaData, "Erro: a nova data é nula.");

        //** lógica de alterarção de data

        notificar();
    }

    //**MÉTODOS PRIVADOS

    private boolean cabe(Set<Participante> participantes) {
        validateListaDeParticipantes(participantes);

        int quantidadeDeVagasDesocupadas = this.capacidadeMaxima - this.listaDeInscritos.size();

        if (participantes.size() > quantidadeDeVagasDesocupadas) {
            return false;
        }

        return true;
    }

    private void notificar(){
        //notificar(this);
    }

    private void validateListaDeParticipantes(Set<Participante> participantes){
        Objects.requireNonNull(participantes, "Erro: lista de participantes não pode ser nulo.");

        if (participantes.isEmpty()){
            throw new IllegalArgumentException("Erro: lista de participantes vazia.");
        }
    }

}
