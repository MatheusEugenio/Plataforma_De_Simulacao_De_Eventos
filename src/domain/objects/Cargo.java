package domain.objects;

import java.util.Objects;

public class Cargo {

    private String cargo;

    public Cargo(String cargo) {
        validate(cargo);
        this.cargo = cargo;
    }

    private void validate(String cargo) {
        Objects.requireNonNull(cargo, "Erro: o cargo do participante é nulo.");

        if (cargo.trim().isBlank()){
            throw new IllegalArgumentException("Erro: o cargo ficou vazio.");
        }

        if (!cargo.matches("^[a-zA-ZÀ-ÿ ]+$")){
            throw new IllegalArgumentException("Erro: o cargo só deve conter letras e espaços!");
        }
    }

    public String getValue(){
        return cargo;
    }
}
