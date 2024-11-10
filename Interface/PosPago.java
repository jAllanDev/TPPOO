import Externos.Chamada;


import java.text.SimpleDateFormat;
import java.util.Date;

public class PosPago extends Assinante {
    private double assinatura;
    private Chamada[] chamadas;
    private int numChamadas;

    public PosPago(long cpf, String nome, int numero, int numChamadas, double assinatura) {
        super(cpf, nome, numero, numChamadas);
        this.assinatura = assinatura;
        this.chamadas = new Chamada[100];
        this.numChamadas = 0;
    }

    public void fazerChamada(Date data, int duracao) {
        if (numChamadas < chamadas.length) {
            double valorChamada = duracao * 1.04;
            Chamada chamada = new Chamada(data, duracao);
            chamadas[numChamadas] = chamada;
            numChamadas++;
            System.out.println("Externos.Chamada registrada com sucesso!");
        } else {
            System.out.println("Número máximo de chamadas atingido!");
        }
    }

    public void imprimirFatura(int mes) {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");
        double totalChamadas = 0;

        System.out.println("Fatura do Assinante Pós-Pago");
        System.out.println("CPF: " + getCpf());
        System.out.println("Nome: " + getNome());
        System.out.println("Número: " + getNumero());
        System.out.println("Mês da fatura: " + mes);
        System.out.println();

        System.out.println("Chamadas:");
        for (Chamada chamada : chamadas) {
            if (chamada != null && (chamada.getDate().getMonth() + 1) == mes) {
                double custoChamada = chamada.getDuracao() * 1.04;
                totalChamadas += custoChamada;
                System.out.println("Data: " + formatoData.format(chamada.getDate()));
                System.out.println("Duração: " + chamada.getDuracao() + " minutos");
                System.out.println("Custo: R$ " + String.format("%.2f", custoChamada));
                System.out.println();
            }
        }

        double totalFatura = assinatura + totalChamadas;
        System.out.println("Resumo:");
        System.out.println("Custo total das chamadas: R$ " + String.format("%.2f", totalChamadas));
        System.out.println("Valor da assinatura: R$ " + String.format("%.2f", assinatura));
        System.out.println("Total da sua fatura: R$ " + String.format("%.2f", totalFatura));
    }

    public double getAssinatura() {
        return this.assinatura;
    }

    public Chamada[] getChamadas() {
        return this.chamadas;
    }

    public int getNumChamadas() {
        return this.numChamadas;
    }

    public void setAssinatura(double assinatura) {
        this.assinatura = assinatura;
    }
}