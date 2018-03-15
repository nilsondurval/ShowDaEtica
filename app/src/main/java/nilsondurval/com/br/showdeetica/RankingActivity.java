package nilsondurval.com.br.showdeetica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import nilsondurval.com.br.showdeetica.adapter.RankingAdapter;
import nilsondurval.com.br.showdeetica.db.DbShowEticaHelper;
import nilsondurval.com.br.showdeetica.modelo.Jogador;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        ListView listRanking = (ListView) findViewById(R.id.listViewRanking);

        DbShowEticaHelper dbShowEticaHelper = DbShowEticaHelper.pegarInstancia(this);

        List<Jogador> jogadores = dbShowEticaHelper.pegarTodosJogadores();

        RankingAdapter adapter = new RankingAdapter(this, jogadores);

        listRanking.setAdapter(adapter);
    }

    public void jogarNovamente(View view) {
        Intent perguntasActivity = new Intent(this, PerguntasActivity.class);
        startActivity(perguntasActivity);
        finish();
    }

    public void irParaTelaInicial(View view) {
        Intent inicialActivity = new Intent(this, InicialActivity.class);
        startActivity(inicialActivity);
        finish();
    }
}
