package AplicacaoBanco;

interface Conta {
    void depositar(double valor);

    boolean sacar(double valor, String senha);

    boolean transferir(Conta destino, double valor, String senha);

    double getSaldo();
}