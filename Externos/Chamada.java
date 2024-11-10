package Externos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Chamada {
    private Date date;
    private int duracao;

    public Chamada(Date date, int duracao) {
        this.date = date;
        this.duracao = duracao;
    }

    public Date getDate() {
        return this.date;
    }

    public int getDuracao() {
        return this.duracao;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dataFormatada = simpleDateFormat.format(getDate());
        return "Chamda realizada no dia: " + dataFormatada
                + " com duração de: " + getDuracao();
    }
}