package nilsondurval.com.br.showdeetica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class InicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        TextView link = (TextView) findViewById(R.id.icons8);
        String linkText = "<a href='https://icons8.com'>Icon pack by Icons8</a>";
        link.setText(Html.fromHtml(linkText));
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void iniciarQuiz(View view) {
        Intent perguntasActivity = new Intent(this, PerguntasActivity.class);
        startActivity(perguntasActivity);
    }

    public void irParaORanking(View view) {
        Intent rankingActivity = new Intent(this, RankingActivity.class);
        startActivity(rankingActivity);
    }
}
