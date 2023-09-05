package AplicacaoBanco;

import java.util.Random;

abstract class ContaBase implements Conta {
    private String agencia;
    private int numero;
    private double saldo;
    private String senha;
    private Pessoa cliente;

    public ContaBase(String agencia, String senha, Pessoa cliente) {
        this.agencia = agencia;
        this.numero = gerarNumeroContaAleatorio();
        this.senha = senha;
        this.saldo = 0;
        this.cliente = cliente;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public boolean sacar(double valor, String senha) {
        if (this.senha.equals(senha) && valor > 0 && saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public boolean transferir(Conta destino, double valor, String senha) {
        if (this.senha.equals(senha) && valor > 0 && saldo >= valor) {
            saldo -= valor;
            destino.depositar(valor);
            return true;
        }
        return false;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    private int gerarNumeroContaAleatorio() {
        Random random = new Random();
        return random.nextInt(10000); // Gera um número de conta aleatório de 0 a 9999
    }

    public String getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public String getSenha() {
        return senha;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }
}
