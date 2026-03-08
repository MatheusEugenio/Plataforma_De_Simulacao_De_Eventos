package infra.repository;

import domain.model.Participante;

import java.util.*;

public class ParticipanteRepository {

    private Map<Long, Participante> participantesCadastrados;

    public ParticipanteRepository() {
        this.participantesCadastrados = new HashMap<>();
    }

    public void save(Participante participante){

        Objects.requireNonNull(participante);

        participantesCadastrados.put(participante.getID(), participante);
    }

    public Participante findById(Long id){

        if (id <= 0){
            throw new IllegalArgumentException("Erro: o id não pode ser negativo.");
        }

        if (!participantesCadastrados.containsKey(id)){
            throw new IllegalArgumentException("Erro: esse Id não existe no banco.");
        }

        return participantesCadastrados.get(id);
    }

    public List<Participante> findAll(){
        return new ArrayList<>(participantesCadastrados.values());
    }

}
