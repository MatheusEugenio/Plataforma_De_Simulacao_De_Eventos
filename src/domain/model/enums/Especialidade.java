package domain.model.enums;


public class Especialidade {

    private String especialidade;

    public Especialidade(String especialidade) {
        validate(especialidade);
        this.especialidade = especialidade;
    }

    private void validate(String especialidade) {
        if (especialidade == null || especialidade.isBlank()){
            throw new IllegalArgumentException("Erro: impossível ter uma especialidade vazia.");
        }

        if (especialidade.length() < 3) {
            throw new IllegalArgumentException("Erro: especialidade inválida.");
        }

    }

    public String getValue() {
        return especialidade;
    }
}
