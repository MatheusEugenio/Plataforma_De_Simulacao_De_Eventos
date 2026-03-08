package domain.valueobject;

import java.time.LocalDateTime;
import java.util.Objects;

public class IntervaloDeTempo {

    private LocalDateTime inicio;
    private LocalDateTime fim;

    public IntervaloDeTempo (LocalDateTime dataDeInicio, LocalDateTime dataDeFim) {
        this.validate(dataDeInicio, dataDeFim);
        this.inicio = dataDeInicio;
        this.fim = dataDeFim;
    }

    public boolean conflitaCom(IntervaloDeTempo outroHorario) {
        //TRUE se houve conflito e FALSE caso contrário
        return this.inicio.isBefore(outroHorario.fim) && this.fim.isAfter(outroHorario.inicio);
    }

    //verifica se o intervalo passado cabe dentro do intervalo já estabelecido
    public boolean contem(IntervaloDeTempo intervaloSolicitado) {
        return !inicio.isAfter(intervaloSolicitado.inicio) && !fim.isBefore(intervaloSolicitado.fim);
    }

    public LocalDateTime getInicio() {return inicio;}
    public LocalDateTime getFim() {return fim;}
    public void setInicio(LocalDateTime inicio) {this.inicio = inicio;}
    public void setFim(LocalDateTime fim) {this.fim = fim;}
    private void validate(LocalDateTime dataDeInicio, LocalDateTime dataDeFim){
        Objects.requireNonNull(dataDeInicio, "Erro: a data de início é nula.");
        Objects.requireNonNull(dataDeFim, "Erro: a data de fim é nula.");

        if (dataDeInicio.isAfter(dataDeFim)){
            throw new IllegalArgumentException("Erro: a data de início do evento ou paleastra é inválida pois é sucessora a data de final. ");
        }

        if (dataDeFim.isBefore(dataDeInicio)){
            throw new IllegalArgumentException("Erro: a data de fim do evento ou paleastra é inválida pois é antecessora a data de inicio. ");
        }
    }
}
