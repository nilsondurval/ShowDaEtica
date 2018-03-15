package nilsondurval.com.br.showdeetica.modelo;

public class Pergunta {

    private int id;
    private String pergunta;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;
    private String resposta;

    public Pergunta() {
        id = 0;
        pergunta = "";
        opt1 = "";
        opt2 = "";
        opt3 = "";
        opt4 = "";
        resposta = "";
    }

    public int getId() {
        return id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getOpt1() {
        return opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public String getResposta() {
        return resposta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

}