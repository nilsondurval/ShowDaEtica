package nilsondurval.com.br.showdeetica.modelo;


import java.io.Serializable;

public class Jogador implements Serializable {

    private int id;
    private String nome;
    private float pontuacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPontuacao() { return pontuacao; }

    public void setPontuacao(float pontuacao) {
        this.pontuacao = pontuacao;
    }

    @Override
    public String toString() {
        return nome + " / " + pontuacao + " pts";
    }
}
