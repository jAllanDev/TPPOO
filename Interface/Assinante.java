public class Assinante {
    private long cpf;
    private String nome;
    private int numero;
    protected int numChamadas;

    public Assinante(long cpf, String nome, int numero, int numChamadas) {
        this.cpf = cpf;
        this.nome = nome;
        this.numero = numero;
        this.numChamadas = numChamadas;
    }

    public long getCpf(){return this.cpf;}

    public String getNome() {
        return this.nome;
    }

    public int getNumero() {
        return this.numero;
    }

    public int getNumChamadas() {
        return this.numChamadas;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome()
                + "\nCPF: " + getCpf()
                + "\nNumero: " + getNumero()
                + "\nQtd de ligações: " + getNumChamadas();
    }
}