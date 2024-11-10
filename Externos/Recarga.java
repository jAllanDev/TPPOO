package Externos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Recarga {
    private Date date;
    private float valor;

    public Recarga(Date date, float valor) {
        this.date = date;
        this.valor = valor;
    }

    public Date getDate() {
        return this.date;
    }

    public float getValor() {
        return this.valor;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dataFormatada = simpleDateFormat.format(getDate());
        return "Externos.Recarga realizada no dia: " + dataFormatada
                + " com valor de: R$ " + String.format("%.2f", getValor());
    }
}