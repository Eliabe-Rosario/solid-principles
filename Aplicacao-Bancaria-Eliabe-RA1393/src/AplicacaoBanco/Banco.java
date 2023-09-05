package AplicacaoBanco;

import java.util.ArrayList;
import java.util.List;

class Banco {
    private List<Pessoa> clientes;
    private List<ContaCorrente> contasCorrente;
    private List<ContaPoupanca> contasPoupanca;

    public Banco() {
        clientes = new ArrayList<>();
        contasCorrente = new ArrayList<>();
        contasPoupanca = new ArrayList<>();
    }

    public void cadastrarCliente(String nome, String cpf) {
        Pessoa cliente = new Pessoa(nome, cpf);
        clientes.add(cliente);
    }

    public void cadastrarContaCorrente(String agencia, String senha, String cpfCliente) {
        Pessoa cliente = encontrarClientePorCpf(cpfCliente);
        if (cliente != null) {
            ContaCorrente conta = new ContaCorrente(agencia, senha, cliente);
            contasCorrente.add(conta);
            System.out.println("Conta corrente criada com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void cadastrarContaPoupanca(String agencia, String senha, String cpfCliente) {
        Pessoa cliente = encontrarClientePorCpf(cpfCliente);
        if (cliente != null) {
            ContaPoupanca conta = new ContaPoupanca(agencia, senha, cliente);
            contasPoupanca.add(conta);
            System.out.println("Conta poupança criada com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void realizarDeposito(String cpfCliente, String tipoConta, String agencia, double valor, String senha) {
        Pessoa cliente = encontrarClientePorCpf(cpfCliente);
        if (cliente != null) {
            Conta conta = encontrarConta(cliente, tipoConta, agencia);
            if (conta != null) {
                conta.depositar(valor);
                System.out.println("Depósito realizado com sucesso.");
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void realizarSaque(String cpfCliente, String tipoConta, String agencia, double valor, String senha) {
        Pessoa cliente = encontrarClientePorCpf(cpfCliente);
        if (cliente != null) {
            Conta conta = encontrarConta(cliente, tipoConta, agencia);
            if (conta != null) {
                if (conta.sacar(valor, senha)) {
                    System.out.println("Saque realizado com sucesso.");
                } else {
                    System.out.println("Senha incorreta ou saldo insuficiente.");
                }
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void realizarTransferencia(String cpfClienteOrigem, String tipoContaOrigem, String agenciaOrigem,
                                      String cpfClienteDestino, String tipoContaDestino, String agenciaDestino,
                                      double valor, String senha) {
        Pessoa clienteOrigem = encontrarClientePorCpf(cpfClienteOrigem);
        Pessoa clienteDestino = encontrarClientePorCpf(cpfClienteDestino);

        if (clienteOrigem != null && clienteDestino != null) {
            Conta contaOrigem = encontrarConta(clienteOrigem, tipoContaOrigem, agenciaOrigem);
            Conta contaDestino = encontrarConta(clienteDestino, tipoContaDestino, agenciaDestino);

            if (contaOrigem != null && contaDestino != null) {
                if (contaOrigem.sacar(valor, senha)) {
                    contaDestino.depositar(valor);
                    System.out.println("Transferência realizada com sucesso.");
                } else {
                    System.out.println("Senha incorreta ou saldo insuficiente.");
                }
            } else {
                System.out.println("Conta de origem ou destino não encontrada.");
            }
        } else {
            System.out.println("Cliente de origem ou destino não encontrado.");
        }
    }

    Pessoa encontrarClientePorCpf(String cpf) {
        for (Pessoa cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    Conta encontrarConta(Pessoa cliente, String tipoConta, String agencia) {
        if (tipoConta.equalsIgnoreCase("Conta-Corrente")) {
            for (ContaCorrente conta : contasCorrente) {
                if (conta.getAgencia().equals(agencia) && conta.getCliente() == cliente) {
                    return conta;
                }
            }
        } else if (tipoConta.equalsIgnoreCase("Conta-Poupanca")) {
            for (ContaPoupanca conta : contasPoupanca) {
                if (conta.getAgencia().equals(agencia) && conta.getCliente() == cliente) {
                    return conta;
                }
            }
        }
        return null;
    }
}




