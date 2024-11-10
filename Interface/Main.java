import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Telefonia sistema = new Telefonia();
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("Menu:");
            System.out.println("1 Cadastro de assinante");
            System.out.println("2 Lista de assinantes");
            System.out.println("3 Realizar Externos.Chamada");
            System.out.println("4 Fazer Externos.Recarga");
            System.out.println("5 Impressão de faturas");
            System.out.println("6 Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    sistema.cadastrarAssinante();
                    break;
                case 2:
                    sistema.listarAssinante();
                    break;
                case 3:
                    sistema.fazerChamada();
                    break;
                case 4:
                    sistema.fazerRecarga();
                    break;
                case 5:
                    sistema.imprimirFaturas();
                    break;
                case 6:
                    System.out.println("VivoDibra agradece sua atenção");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 6);
    }
}