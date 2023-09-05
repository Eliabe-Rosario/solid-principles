package AplicacaoBanco;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Menu Principal =====");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Conta Corrente");
            System.out.println("3. Cadastrar Conta Poupança");
            System.out.println("4. Realizar Depósito");
            System.out.println("5. Realizar Saque");
            System.out.println("6. Realizar Transferência");
            System.out.println("7. Consultar Saldo");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Cliente: ");
                    String nomeCliente = scanner.nextLine();
                    System.out.print("CPF do Cliente: ");
                    String cpfCliente = scanner.nextLine();
                    banco.cadastrarCliente(nomeCliente, cpfCliente);
                    break;
                case 2:
                    System.out.print("CPF do Cliente: ");
                    String cpfContaCorrente = scanner.nextLine();
                    System.out.print("Agência da Conta Corrente: ");
                    String agenciaContaCorrente = scanner.nextLine();
                    System.out.print("Senha da Conta Corrente (4 dígitos): ");
                    String senhaContaCorrente = scanner.nextLine();
                    banco.cadastrarContaCorrente(agenciaContaCorrente, senhaContaCorrente, cpfContaCorrente);
                    break;
                case 3:
                    System.out.print("CPF do Cliente: ");
                    String cpfContaPoupanca = scanner.nextLine();
                    System.out.print("Agência da Conta Poupança: ");
                    String agenciaContaPoupanca = scanner.nextLine();
                    System.out.print("Senha da Conta Poupança (4 dígitos): ");
                    String senhaContaPoupanca = scanner.nextLine();
                    banco.cadastrarContaPoupanca(agenciaContaPoupanca, senhaContaPoupanca, cpfContaPoupanca);
                    break;
                case 4:
                    realizarDeposito(banco, scanner);
                    break;
                case 5:
                    realizarSaque(banco, scanner);
                    break;
                case 6:
                    realizarTransferencia(banco, scanner);
                    break;
                case 7:
                    consultarSaldo(banco, scanner);
                    break;
                case 8:
                    System.out.println("Saindo do programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void realizarDeposito(Banco banco, Scanner scanner) {
        System.out.print("CPF do Cliente: ");
        String cpfDeposito = scanner.nextLine();
        System.out.print("Tipo de Conta: Escreva>>> (Conta-Corrente ou Poupança): ");
        String tipoContaDeposito = scanner.nextLine();
        System.out.print("Agência: ");
        String agenciaDeposito = scanner.nextLine();
        System.out.print("Valor para Depósito: ");
        double valorDeposito = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Senha: ");
        String senhaDeposito = scanner.nextLine();

        banco.realizarDeposito(cpfDeposito, tipoContaDeposito, agenciaDeposito, valorDeposito, senhaDeposito);
    }

    private static void realizarSaque(Banco banco, Scanner scanner) {
        System.out.print("CPF do Cliente: ");
        String cpfSaque = scanner.nextLine();
        System.out.print("Tipo de Conta (Conta-Corrente ou Poupança): ");
        String tipoContaSaque = scanner.nextLine();
        System.out.print("Agência: ");
        String agenciaSaque = scanner.nextLine();
        System.out.print("Valor para Saque: ");
        double valorSaque = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Senha: ");
        String senhaSaque = scanner.nextLine();

        banco.realizarSaque(cpfSaque, tipoContaSaque, agenciaSaque, valorSaque, senhaSaque);
    }

    private static void realizarTransferencia(Banco banco, Scanner scanner) {
        System.out.print("CPF do Cliente de Origem: ");
        String cpfOrigem = scanner.nextLine();
        System.out.print("Tipo de Conta de Origem (Conta-Corrente ou Poupança): ");
        String tipoContaOrigem = scanner.nextLine();
        System.out.print("Agência de Origem: ");
        String agenciaOrigem = scanner.nextLine();
        System.out.print("CPF do Cliente de Destino: ");
        String cpfDestino = scanner.nextLine();
        System.out.print("Tipo de Conta de Destino (Conta-Corrente ou Poupança): ");
        String tipoContaDestino = scanner.nextLine();
        System.out.print("Agência de Destino: ");
        String agenciaDestino = scanner.nextLine();
        System.out.print("Valor para Transferência: ");
        double valorTransferencia = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Senha: ");
        String senhaTransferencia = scanner.nextLine();

        banco.realizarTransferencia(cpfOrigem, tipoContaOrigem, agenciaOrigem, cpfDestino, tipoContaDestino,
                agenciaDestino, valorTransferencia, senhaTransferencia);
    }

    private static void consultarSaldo(Banco banco, Scanner scanner) {
        System.out.print("CPF do Cliente: ");
        String cpfConsulta = scanner.nextLine();
        System.out.print("Tipo de Conta (Conta-Corrente ou Poupança): ");
        String tipoContaConsulta = scanner.nextLine();
        System.out.print("Agência: ");
        String agenciaConsulta = scanner.nextLine();

        Pessoa cliente = banco.encontrarClientePorCpf(cpfConsulta);
        if (cliente != null) {
            ContaBase conta = (ContaBase) banco.encontrarConta(cliente, tipoContaConsulta, agenciaConsulta);
            if (conta != null) {
                System.out.println("Saldo da conta: " + conta.getSaldo());
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}
