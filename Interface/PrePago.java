import Externos.Chamada;
import Externos.Recarga;
import Interface.IAssinante;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrePago extends Assinante implements IAssinante {
    private float creditos;
    private Recarga[] recargas;
    private Chamada[] chamadas;
    private int numRecargas;

    public PrePago(long cpf, String nome, int numero, int numChamadas) {
        super(cpf, nome, numero, numChamadas);
        this.creditos = 0;
        this.chamadas = new Chamada[100];
        this.recargas = new Recarga[100];
        this.numRecargas = 0;
    }

    public void fazerChamada(Date date, int duracao) {
        double valor = duracao * 1.45;
        if (getNumRecargas() < getRecargas().length) {
            if (valor <= getCreditos()) {
                Chamada chamada = new Chamada(date, duracao);
                getChamadas()[getNumRecargas()] = chamada;
                setNumRecargas(getNumRecargas() + 1);
                setCreditos((float) (getCreditos() - valor));
                System.out.println("Externos.Chamada realizada com sucesso!");
            } else {
                System.out.println("Créditos insuficientes");
            }
        } else {
            System.out.println("Número de chamadas permitidas ultrapassado!");
        }
    }

    public void recarregar(Date date, float valor) {
        if (getNumRecargas() < getRecargas().length) {
            Recarga recarga = new Recarga(date, valor);
            getRecargas()[getNumRecargas()] = recarga;
            setNumRecargas(getNumRecargas() + 1);
            setCreditos(getCreditos() + valor);
            System.out.println("Externos.Recarga realizada com sucesso!");
        } else {
            System.out.println("Quantidade de recargas permitida ultrapassada!");
        }
    }

    public void imprimirFatura(int mes) {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");
        double totalChamadas = 0;
        double totalRecargas = 0;

        System.out.println("Fatura do Assinante Pré-pago");
        System.out.println("CPF: " + getCpf());
        System.out.println("Nome: " + getNome());
        System.out.println("Número: " + getNumero());
        System.out.println("Mês da fatura: " + mes);
        System.out.println();

        System.out.println("Chamadas:");
        for (Chamada chamada : chamadas) {
            if (chamada != null && (chamada.getDate().getMonth() + 1) == mes) {
                double custoChamada = chamada.getDuracao() * 1.45;
                totalChamadas += custoChamada;
                System.out.println("Data: " + formatoData.format(chamada.getDate()));
                System.out.println("Duração: " + chamada.getDuracao() + " minutos");
                System.out.println("Custo: R$ " + String.format("%.2f", custoChamada));
                System.out.println();
            }
        }

        System.out.println("Recargas:");
        for (Recarga recarga : recargas) {
            if (recarga != null && (recarga.getDate().getMonth() + 1) == mes) {
                totalRecargas += recarga.getValor();
                System.out.println("Data: " + formatoData.format(recarga.getDate()));
                System.out.println("Valor: R$ " + String.format("%.2f", recarga.getValor()));
                System.out.println();
            }
        }

        System.out.println("Resumo:");
        System.out.println("Total de chamadas: R$ " + String.format("%.2f", totalChamadas));
        System.out.println("Total de recargas: R$ " + String.format("%.2f", totalRecargas));
        System.out.println("Créditos restantes: R$ " + String.format("%.2f", getCreditos()));
    }

    public void setCreditos(float creditos) {
        this.creditos = creditos;
    }

    public void setNumRecargas(int numRecargas) {
        this.numRecargas = numRecargas;
    }

    public Chamada[] getChamadas() {
        return chamadas;
    }

    public float getCreditos() {
        return this.creditos;
    }

    public Recarga[] getRecargas() {
        return this.recargas;
    }

    public int getNumRecargas() {
        return this.numRecargas;
    }
}