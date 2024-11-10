import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Telefonia {
    private PrePago[] prePagos;
    private int numPrePagos;
    private PosPago[] posPagos;
    private int numPosPagos;

    public Telefonia() {
        this.prePagos = new PrePago[100];
        this.posPagos = new PosPago[100];
        this.numPrePagos = 0;
        this.numPosPagos = 0;
    }

    public void cadastrarAssinante() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tipo de assinante (1 - Pré-pago, 2 - Pós-pago): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("CPF: ");
        long cpf = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Número: ");
        int numero = scanner.nextInt();

        if (tipo == 1) {
            if (numPrePagos < prePagos.length) {
                prePagos[numPrePagos++] = new PrePago(cpf, nome, numero, 0);
                System.out.println("Assinante pré-pago cadastrado com sucesso!");
            } else {
                System.out.println("Limite de assinantes pré-pagos atingido.");
            }
        } else if (tipo == 2) {
            System.out.print("Valor da assinatura: ");
            double assinatura = scanner.nextDouble();
            if (numPosPagos < posPagos.length) {
                posPagos[numPosPagos++] = new PosPago(cpf, nome, numero, 0, assinatura);
                System.out.println("Assinante pós-pago cadastrado com sucesso!");
            } else {
                System.out.println("Limite de assinantes pós-pagos atingido.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    public void listarAssinante() {
        System.out.println("Assinantes Pré-Pagos:");
        for (int i = 0; i < numPrePagos; i++) {
            System.out.println(prePagos[i]);
        }

        System.out.println("Assinantes Pós-Pagos:");
        for (int i = 0; i < numPosPagos; i++) {
            System.out.println(posPagos[i]);
        }
    }

    public void fazerChamada() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println("Tipo de assinante (1 -> Pré-pago, 2-> Pós-pago): ");
        int tipo = scanner.nextInt();
        System.out.print("CPF: ");
        long cpf = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Data da chamada (dd-MM-yyyy): ");
        String dataStr = scanner.nextLine();
        System.out.print("Duração (minutos): ");
        int duracao = scanner.nextInt();

        try {
            Date data = formatoData.parse(dataStr);
            if (tipo == 1) {
                PrePago assinante = localizarPrePago(cpf);
                if (assinante != null) {
                    assinante.fazerChamada(data, duracao);
                } else {
                    System.out.println("Assinante pré-pago não existe.");
                }
            } else if (tipo == 2) {
                PosPago assinante = localizarPosPago(cpf);
                if (assinante != null) {
                    assinante.fazerChamada(data, duracao);
                } else {
                    System.out.println("Assinante pós-pago não existe.");
                }
            } else {
                System.out.println("Tipo inválido.");
            }
        } catch (ParseException e) {
            System.out.println("Data inválida.");
        }
    }

    public void fazerRecarga() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");

        System.out.print("CPF do assinante pré-pago: ");
        long cpf = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Data da recarga formato: (dd-MM-yyyy): ");
        String dataStr = scanner.nextLine();
        System.out.print("Valor da recarga: ");
        float valor = scanner.nextFloat();

        try {
            Date data = formatoData.parse(dataStr);
            PrePago assinante = localizarPrePago(cpf);
            if (assinante != null) {
                assinante.recarregar(data, valor);
            } else {
                System.out.println("Assinante pré-pago não existe.");
            }
        } catch (ParseException e) {
            System.out.println("Data inválida.");
        }
    }

    public void imprimirFaturas() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Mês da fatura: ");
        int mes = scanner.nextInt();

        System.out.println("Faturas Pré-Pagos:");
        for (int i = 0; i < numPrePagos; i++) {
            prePagos[i].imprimirFatura(mes);
            System.out.println();
        }

        System.out.println("Faturas Pós-Pagos:");
        for (int i = 0; i < numPosPagos; i++) {
            posPagos[i].imprimirFatura(mes);
            System.out.println();
        }
    }

    public PrePago localizarPrePago(long cpf) {
        for (int i = 0; i < numPrePagos; i++) {
            if (prePagos[i].getCpf() == cpf) {
                return prePagos[i];
            }
        }
        return null;
    }

    public PosPago localizarPosPago(long cpf) {
        for (int i = 0; i < numPosPagos; i++) {
            if (posPagos[i].getCpf() == cpf) {
                return posPagos[i];
            }
        }
        return null;
    }
}