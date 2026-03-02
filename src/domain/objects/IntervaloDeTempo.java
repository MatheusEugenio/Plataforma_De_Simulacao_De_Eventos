package domain.objects;

import java.time.LocalDateTime;

public class IntervaloDeTempo {

    private LocalDateTime inicio;
    private LocalDateTime fim;

    public IntervaloDeTempo (LocalDateTime dataDeInicio, LocalDateTime dataDeFim) {
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
}
