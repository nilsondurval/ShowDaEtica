package nilsondurval.com.br.showdeetica.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import nilsondurval.com.br.showdeetica.R;
import nilsondurval.com.br.showdeetica.modelo.Jogador;


public class RankingAdapter extends BaseAdapter {

    private final List<Jogador> jogadores;
    private final Activity contexto;

    public RankingAdapter(Activity contexto, List<Jogador> jogadores) {
        this.jogadores = jogadores;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return jogadores.size();
    }

    @Override
    public Object getItem(int position) {
        return jogadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return jogadores.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = contexto.getLayoutInflater().inflate(R.layout.list_view_raking_personalizada, parent, false);
        Jogador jogador = jogadores.get(position);

        TextView posicao = (TextView) view.findViewById(R.id.lista_jogadores_posicao);
        TextView nome = (TextView) view.findViewById(R.id.lista_jogadores_nome);
        RatingBar pontuacao = (RatingBar) view.findViewById(R.id.lista_jogadores_pontuacao);
        ImageView medalha = (ImageView) view.findViewById(R.id.lista_jogadores_medalha);

        posicao.setText(position + 1 + "º");
        nome.setText(jogador.getNome());
        pontuacao.setRating( (jogador.getPontuacao()/2) );

        switch (position) {
            case 0:
                medalha.setImageResource(R.drawable.medal_first_place);
                break;
            case 1:
                medalha.setImageResource(R.drawable.medal_second_place);
                break;
            case 2:
                medalha.setImageResource(R.drawable.medal_third_place);
                break;
            default:
                medalha.setImageResource(R.drawable.star);
        }

        return view;
    }
}
