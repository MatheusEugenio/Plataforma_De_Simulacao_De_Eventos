package domain.objects;

public class Name {

    private String nome;

    public Name(String nome){
        validate(nome);
        this.nome = nome;
    }

    private void validate(String nome){
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Erro: nome não pode ser nulo.");
        }

        String nomeLimpo = nome.trim();

        if (nome.length() < 3){
            throw new IllegalArgumentException("Erro: nome é muito curto para um nome real.");
        }

        if (!nomeLimpo.matches("^[a-zA-ZÀ-ÿ ]+$")){
            throw new IllegalArgumentException("Erro: o cargo só deve conter letras e espaços!");
        }
    }

    public String getValue(){
        return nome;
    }
}
