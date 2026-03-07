package repository;

import domain.Evento;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EventsRepository {

    private Map<Long, Evento> eventos;

    public EventsRepository() {
        this.eventos = new HashMap<>();
    }

    public void save(Evento evento){

        //* Atualiza e adiciona só
        eventos.put(evento.getID(), evento);
    }

    public void delete(Long IDDoEvento){

        if (!this.eventos.containsKey(IDDoEvento)){
           throw new IllegalArgumentException("Erro: o evento não pode ser deletado pois não existe no banco.");
        }

        this.eventos.remove(IDDoEvento);
    }

    public Evento buscarPorID(Long idEvento){
        Objects.requireNonNull(idEvento, "Erro: o id passado é nulo.");

        if (idEvento <= 0){
            throw new IllegalArgumentException("Erro: o ID é um número menor ou igual a zero");
        }

        Evento eventRtrn = this.eventos.get(idEvento);

        if (eventRtrn == null){
            throw new IllegalArgumentException("Erro ao acessar evento por ID: o evento não foi achado pelo ID: "+ idEvento);
        }

        return eventRtrn;
    }

}
