package nilsondurval.com.br.showdeetica;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import nilsondurval.com.br.showdeetica.db.DbShowEticaHelper;
import nilsondurval.com.br.showdeetica.modelo.Pergunta;

import static android.view.Gravity.CENTER;

public class PerguntasActivity extends AppCompatActivity {

    private TextView textPergunta;
    private RadioGroup groupAlternativas;
    private RadioButton radioSelecionado, radioOpt1, radioOpt2, radioOpt3, radioOpt4;
    private Button buttonResponder;

    private List<Pergunta> perguntas;
    private Pergunta perguntaAtual;

    private int contRespondidas = 0;
    private int contAcertos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);

        pegarViewsDoLayout();

        DbShowEticaHelper dbShowEticaHelper = DbShowEticaHelper.pegarInstancia(this);

        perguntas = dbShowEticaHelper.pegarTodasPerguntas();
        perguntaAtual = sortearPergunta(perguntas);

        preencherPerguntaAtualNaView();

        buttonResponder.setTag("responder");
    }

    public void responder(View view) {
        radioSelecionado = (RadioButton) findViewById(groupAlternativas.getCheckedRadioButtonId());

        if (radioSelecionado != null) {

            if (buttonResponder.getTag().equals("responder")) {

                if (perguntaAtual.getResposta().equals(radioSelecionado.getText())) {

                    contAcertos++;
                    destacarOpcaoCorreta();
                    exibirMensagemRespostaCorreta();

                    if (contRespondidas < 10) {

                        buttonResponder.setTag("proxima");
                        buttonResponder.setText("Ir para próxima");

                    } else {

                        buttonResponder.setTag("resultado");
                        buttonResponder.setText("Ver resultado");

                    }

                } else {

                    destacarSelecaoIncorreta();
                    destacarOpcaoCorreta();
                    exibirMensagemRespostaErrada();

                    if (contRespondidas < 10) {

                        buttonResponder.setTag("proxima");
                        buttonResponder.setText("Ir para próxima");

                    } else {

                        buttonResponder.setTag("resultado");
                        buttonResponder.setText("Ver resultado");

                    }

                }

            } else if (buttonResponder.getTag().equals("proxima")) {

                removerDestaques();

                perguntaAtual = sortearPergunta(perguntas);
                preencherPerguntaAtualNaView();

                buttonResponder.setTag("responder");
                buttonResponder.setText("Responder");

            } else if (buttonResponder.getTag().equals("resultado")) {

                Intent resultadoActivity = new Intent(this, ResultadoActivity.class);
                resultadoActivity.putExtra("contAcertos", contAcertos);
                startActivity(resultadoActivity);
                finish();

            }

        } else {
            exibirMensagemSelecionarAlternativa();
        }
    }

    private void pegarViewsDoLayout() {
        textPergunta = (TextView) findViewById(R.id.textViewPergunta);
        groupAlternativas = (RadioGroup) findViewById(R.id.radioGroupAlternativas);
        radioOpt1 = (RadioButton) findViewById(R.id.radioButtonOpt1);
        radioOpt2 = (RadioButton) findViewById(R.id.radioButtonOpt2);
        radioOpt3 = (RadioButton) findViewById(R.id.radioButtonOpt3);
        radioOpt4 = (RadioButton) findViewById(R.id.radioButtonOpt4);
        buttonResponder = (Button) findViewById(R.id.buttonResponder);
    }

    private void preencherPerguntaAtualNaView() {
        textPergunta.setText(perguntaAtual.getPergunta());

        groupAlternativas.clearCheck();

        radioOpt1.setText(perguntaAtual.getOpt1());
        radioOpt2.setText(perguntaAtual.getOpt2());
        radioOpt3.setText(perguntaAtual.getOpt3());
        radioOpt4.setText(perguntaAtual.getOpt4());

        contRespondidas++;
    }

    private void destacarOpcaoCorreta() {
        if (perguntaAtual.getResposta().equals(radioOpt1.getText())) {

            radioOpt1.setBackgroundColor(getResources().getColor(R.color.green));

        } else if (perguntaAtual.getResposta().equals(radioOpt2.getText())) {

            radioOpt2.setBackgroundColor(getResources().getColor(R.color.green));

        } else if (perguntaAtual.getResposta().equals(radioOpt3.getText())) {

            radioOpt3.setBackgroundColor(getResources().getColor(R.color.green));

        } else if (perguntaAtual.getResposta().equals(radioOpt4.getText())) {

            radioOpt4.setBackgroundColor(getResources().getColor(R.color.green));
        }
    }

    private void destacarSelecaoIncorreta() {
        radioSelecionado.setBackgroundColor(getResources().getColor(R.color.red));
    }

    private void removerDestaques() {
        radioOpt1.setBackgroundResource(0);
        radioOpt2.setBackgroundResource(0);
        radioOpt3.setBackgroundResource(0);
        radioOpt4.setBackgroundResource(0);
    }

    private void exibirMensagemRespostaCorreta() {
        ImageView imagem = new ImageView(this);
        imagem.setImageResource(R.drawable.right_aswer);
        Toast toast = new Toast(this);
        toast.setView(imagem);
        toast.setGravity(CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
        MediaPlayer mp = MediaPlayer.create(this, R.raw.applause);
        mp.start();
    }

    private void exibirMensagemRespostaErrada() {
        ImageView imagem = new ImageView(this);
        imagem.setImageResource(R.drawable.wrong_answer);
        Toast toast = new Toast(this);
        toast.setView(imagem);
        toast.setGravity(CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
        MediaPlayer mp = MediaPlayer.create(this, R.raw.wah_wah_wah_wah);
        mp.start();
    }

    private void exibirMensagemSelecionarAlternativa() {
        Toast.makeText(this, "Você deve selecionar um alternativa!", Toast.LENGTH_SHORT).show();
        MediaPlayer mp = MediaPlayer.create(this, R.raw.comedy_boing_flapping_sprong);
        mp.start();
    }

    private Pergunta sortearPergunta(List<Pergunta> perguntas) {
        // Embaralhando a lista de perguntas
        Collections.shuffle(perguntas);
        // pegar qualquer indice. pegamos o primeiro para conveniencia.
        return perguntas.remove(0);
    }

}