package domain.objects;

public class Email {

    private String email;

    public Email(String email) {
        validate(email);
        this.email = email;
    }

    private void validate(String email) {
        if (email == null || email.isBlank()){
            throw new IllegalArgumentException("Erro: email não pode ser nulo.");
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            throw new IllegalArgumentException("Erro: '"+email+"' não possui o formato correto de email.");
        }
    }

    public String getValue(){
        return email;
    }
}
