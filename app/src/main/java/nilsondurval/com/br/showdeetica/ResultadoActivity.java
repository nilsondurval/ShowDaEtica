package nilsondurval.com.br.showdeetica;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import nilsondurval.com.br.showdeetica.db.DbShowEticaHelper;
import nilsondurval.com.br.showdeetica.modelo.Jogador;


public class ResultadoActivity extends AppCompatActivity {

    private EditText editNome;
    private TextView textResultado;
    private RatingBar ratingPontuaco;

    private int acertos;

    private Jogador jogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        editNome = (EditText) findViewById(R.id.editTextNome);
        textResultado = (TextView) findViewById(R.id.textViewResultado);
        ratingPontuaco = (RatingBar) findViewById(R.id.ratingBarPontuaco);

        ratingPontuaco.setNumStars(5);
        ratingPontuaco.setStepSize(0.5f);

        Bundle bundle = getIntent().getExtras();

        acertos = bundle.getInt("contAcertos");

        ratingPontuaco.setRating(acertos/2f);

        if (acertos == 0) {

            textResultado.setText("Que pena! Você não acertou nenhuma.");

        } else if (acertos > 0 && acertos <= 3) {

            textResultado.setText("Você acertou apenas " + acertos + ", mais sorte na proxima vez.");

        } else if (acertos > 3 && acertos <= 5){

            textResultado.setText("Legal! você acertou " + acertos + ".");

        } else if (acertos > 5 && acertos <= 7) {

            textResultado.setText("Muito bom! você acertou " + acertos + ".");

        } else if (acertos > 7 && acertos <= 9) {

            textResultado.setText("Ótimo! você acertou " + acertos + ".");

        } else {

            textResultado.setText("Parabéns! você acertou todas.");

        }
    }

    public void irParaORanking(View view) {
        if (!editNome.getText().toString().equals("")) {

            jogador = new Jogador();
            jogador.setNome(editNome.getText().toString());
            jogador.setPontuacao(acertos);

            DbShowEticaHelper dbShowEticaHelper = DbShowEticaHelper.pegarInstancia(this);
            dbShowEticaHelper.adicionarJogador(jogador);

            Intent rankingActivity = new Intent(this, RankingActivity.class);
            startActivity(rankingActivity);
            finish();

        } else {
            exibirMensagemInformarNome();
        }
    }

    private void exibirMensagemInformarNome() {
        Toast.makeText(this, "Informe um nome para o ranking...", Toast.LENGTH_SHORT).show();
        MediaPlayer mp = MediaPlayer.create(this, R.raw.comedy_boing_flapping_sprong);
        mp.start();
    }
}