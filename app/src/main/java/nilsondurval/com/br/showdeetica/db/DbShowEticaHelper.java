package nilsondurval.com.br.showdeetica.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nilsondurval.com.br.showdeetica.modelo.Jogador;
import nilsondurval.com.br.showdeetica.modelo.Pergunta;


public class DbShowEticaHelper extends SQLiteOpenHelper {

    // Informações do banco de dados
    private static final String NOME_DO_BANCO = "ShowEtica";
    private static final int VERSAO_DO_BANCO = 1;

    // Tabelas
    private static final String TABELA_PERGUNTAS = "Perguntas";
    private static final String TABELA_JOGADORES = "Jogadores";

    // Coluna generica
    private static final String COLUNA_ID = "id";

    // Colunas da tabela perguntas
    private static final String COLUNA_PERGUNTA = "pergunta";
    private static final String COLUNA_RESPOSTA = "resposta";
    private static final String COLUNA_OPT1 = "opt1";
    private static final String COLUNA_OPT2 = "opt2";
    private static final String COLUNA_OPT3 = "opt3";
    private static final String COLUNA_OPT4 = "opt4";

    // Scripts de criação da tabela Perguntas
    private static final String CRIAR_TABELA_PERGUNTAS = "CREATE TABLE IF NOT EXISTS " + TABELA_PERGUNTAS
            + " ( "
            + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUNA_PERGUNTA + " TEXT, "
            + COLUNA_RESPOSTA + " TEXT, "
            + COLUNA_OPT1 + " TEXT, "
            + COLUNA_OPT2 + " TEXT, "
            + COLUNA_OPT3 + " TEXT, "
            + COLUNA_OPT4 + " TEXT "
            + " ) ";

    // Colunas da tabela jogadores
    private static final String COLUNA_NOME= "nome";
    private static final String COLUNA_PONTUACAO = "pontuacao";

    // Scripts de criação da tabela Jogadores
    private static final String CRIAR_TABELA_JOGADORES = "CREATE TABLE IF NOT EXISTS " + TABELA_JOGADORES
            + " ( "
            + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUNA_NOME + " TEXT, "
            + COLUNA_PONTUACAO + " INTEGER "
            + " ) ";

    // Instancia unica do helper para toda aplicação
    private static DbShowEticaHelper instanciaUnica;

    // Para utilizar a instancia de criação para inserir as perguntas (gambi)
    private SQLiteDatabase dbOncreate;

    public DbShowEticaHelper(Context context) {
        super(context, NOME_DO_BANCO, null, VERSAO_DO_BANCO);
    }

    public static synchronized DbShowEticaHelper pegarInstancia(Context context) {
        if (instanciaUnica == null) {
            instanciaUnica = new DbShowEticaHelper(context.getApplicationContext());
        }

        return instanciaUnica;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRIAR_TABELA_PERGUNTAS);
        db.execSQL(CRIAR_TABELA_JOGADORES);

        dbOncreate = db;
        adicionarPerguntas();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PERGUNTAS);
        onCreate(db);
    }

    private void adicionarPerguntas() {
        Pergunta pergunta = new Pergunta();

        pergunta.setPergunta("Você é ético quando:");
        pergunta.setOpt1("Não estuda e cola na prova.");
        pergunta.setOpt2("Pula a catraca/roleta do ônibus.");
        pergunta.setOpt3("Não mexe no que não é seu.");
        pergunta.setOpt4("Fala palavrão e desrespeita o professor.");
        pergunta.setResposta("Não mexe no que não é seu.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Você não é corrupto quando:");
        pergunta.setOpt1("Dá um jeitinho para levar vantagem.");
        pergunta.setOpt2("Faz compras sem a nota fiscal.");
        pergunta.setOpt3("Devolve o lápis do colega que caiu na sala.");
        pergunta.setOpt4("Faz gato de energia e/ou tv por assinatura.");
        pergunta.setResposta("Devolve o lápis do colega que caiu na sala.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Você é solidário quando:");
        pergunta.setOpt1("Ajuda uma idosa a atravessar a rua.");
        pergunta.setOpt2("Não estuda e pede cola ao colega na prova.");
        pergunta.setOpt3("Finge doença para não ajudar a sua mãe.");
        pergunta.setOpt4("Vê uma pessoa caindo e vai filmar.");
        pergunta.setResposta("Ajuda uma idosa a atravessar a rua.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Você é cidadão quando:");
        pergunta.setOpt1("Sonega impostos.");
        pergunta.setOpt2("Tem certidão de nascimento e vota em quem pagar mais.");
        pergunta.setOpt3("Não participa da política e tem acesso à saúde e educação.");
        pergunta.setOpt4("Participa de um multirão e incentiva outros a participarem.");
        pergunta.setResposta("Participa de um multirão e incentiva outros a participarem.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("São atitudes antiéticas:");
        pergunta.setOpt1("Separar/reciclar o lixo.");
        pergunta.setOpt2("Fechar o cruzamento da rua com seu carro.");
        pergunta.setOpt3("Respeitar os sinais de trânsito.");
        pergunta.setOpt4("Economizar água e cuidar do meio ambiente.");
        pergunta.setResposta("Fechar o cruzamento da rua com seu carro.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Não basta ao cidadão, se recusar a subornar um agente da lei deve-se também:");
        pergunta.setOpt1("Denunciar na corregedoria policial para parar esse mal.");
        pergunta.setOpt2("Fazer de conta que não viu nada.");
        pergunta.setOpt3("Vender a informação para corregedoria cobrando um valor alto.");
        pergunta.setOpt4("Cobrar do agente um valor para não denunciar ele.");
        pergunta.setResposta("Denunciar na corregedoria policial para parar esse mal.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Não basta comprar tudo com nota fiscal, tem que:");
        pergunta.setOpt1("Dar um jeitinho para levar vantagem.");
        pergunta.setOpt2("Denunciar contrabando e pirataria.");
        pergunta.setOpt3("Comprar produtos sem nota fiscal.");
        pergunta.setOpt4("Não fazer nada, pois não é da sua conta.");
        pergunta.setResposta("Denunciar contrabando e pirataria.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Quando você tem lixo na sua mão, você:");
        pergunta.setOpt1("Joga no meio da rua.");
        pergunta.setOpt2("Coloca em um cantinho onde ninguém irá ver.");
        pergunta.setOpt3("Joga na rua, pois tem pessoas que limpam.");
        pergunta.setOpt4("Guarda com você até chegar em uma lixeira.");
        pergunta.setResposta("Guarda com você até chegar em uma lixeira.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Quando alguém cai na rua o que eu faço?");
        pergunta.setOpt1("Fico dando risada.");
        pergunta.setOpt2("Ajudo a pessoa a se levantar e vejo se ela está bem.");
        pergunta.setOpt3("Passo direto, pois outra pessoa irá ajudar-la.");
        pergunta.setOpt4("Não faço nada e fico olhando.");
        pergunta.setResposta("Ajudo a pessoa a se levantar e vejo se ela está bem.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Em dia de prova, o que devo fazer?");
        pergunta.setOpt1("Colar do amigo que sabe mais.");
        pergunta.setOpt2("Inventar que está doente para não ir na prova.");
        pergunta.setOpt3("Estudar e ir preparado.");
        pergunta.setOpt4("Chutar todas as questões.");
        pergunta.setResposta("Estudar e ir preparado.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Quando vejo alguém dirigindo falando ao celular:");
        pergunta.setOpt1("Não faço nada.");
        pergunta.setOpt2("Chamo atenção e falo que não se deve fazer isso.");
        pergunta.setOpt3("Olho para outro lado e ignoro.");
        pergunta.setOpt4("Começo a chorar para tirar a concentração dele.");
        pergunta.setResposta("Chamo atenção e falo que não se deve fazer isso.");


        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Quando eu for votar eu vou:");
        pergunta.setOpt1("Votar no político que pagar mais.");
        pergunta.setOpt2("Votar no político que tem melhores ideias e cobra-lo pelo que ele prometeu.");
        pergunta.setOpt3("Votar no político do papel que achei no chão.");
        pergunta.setOpt4("Votar em qualquer um, mas não cobro melhorias para meu país.");
        pergunta.setResposta("Votar no político que tem melhores ideias e cobra-lo pelo que ele prometeu.");


        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Não basta dar esmola, devemos:");
        pergunta.setOpt1("Cobrar do governo para que ajude as pessoas desalojadas.");
        pergunta.setOpt2("Dar a esmola já é suficiente.");
        pergunta.setOpt3("Não ajudar, pois aquilo não é sua culpa.");
        pergunta.setOpt4("Não fazer nada.");
        pergunta.setResposta("Cobrar do governo para que ajude as pessoas desalojadas.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Para viver com as pessoas, não devo:");
        pergunta.setOpt1("Ser alegre.");
        pergunta.setOpt2("Ser amigo.");
        pergunta.setOpt3("Respeitar os mais velhos.");
        pergunta.setOpt4("Ser intolerante.");
        pergunta.setResposta("Ser intolerante.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("São regras da higiene, menos:");
        pergunta.setOpt1("Lavar as mãos antes das refeições.");
        pergunta.setOpt2("Escovar os dentes.");
        pergunta.setOpt3("Não tomar banho.");
        pergunta.setOpt4("Andar calçado e com roupas limpas.");
        pergunta.setResposta("Não tomar banho.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Devemos sempre:");
        pergunta.setOpt1("Respeitar o próximo.");
        pergunta.setOpt2("Jogar lixo no chão.");
        pergunta.setOpt3("Roubar.");
        pergunta.setOpt4("Mentir.");
        pergunta.setResposta("Respeitar o próximo.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Sobre os mandamentos da Cidadania, marque a alternativa errada:");
        pergunta.setOpt1("Economizar água.");
        pergunta.setOpt2("Cuidar do meio ambiente.");
        pergunta.setOpt3("Mentir para tirar vantagem.");
        pergunta.setOpt4("Pedir por favor.");
        pergunta.setResposta("Mentir para tirar vantagem.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Quando eu chego em um lugar, eu devo:");
        pergunta.setOpt1("Cumprimentar as pessoas e manter a educação.");
        pergunta.setOpt2("Não Falar com ninguém e pedir o que eu quero.");
        pergunta.setOpt3("Chegar gritando.");
        pergunta.setOpt4("Não manter a educação e fazer bagunça no local.");
        pergunta.setResposta("Cumprimentar as pessoas e manter a educação.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Quando eu uso a internet, eu devo:");
        pergunta.setOpt1("Xingar todo mundo, pois na internet pode.");
        pergunta.setOpt2("Procurar maneiras de tirar vantagem dos outros.");
        pergunta.setOpt3("Copiar o trabalho dos outros e falar que é meu.");
        pergunta.setOpt4("Usar o computador de forma responsável, respeitando outros usuários.");
        pergunta.setResposta("Usar o computador de forma responsável, respeitando outros usuários.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Quando devo mentir?");
        pergunta.setOpt1("Nunca.");
        pergunta.setOpt2("Para não me dar mal.");
        pergunta.setOpt3("Para ganhar algo que não é meu.");
        pergunta.setOpt4("Para prejudicar meu amigo.");
        pergunta.setResposta("Nunca.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Comprar DVD pirata é:");
        pergunta.setOpt1("Legal, pois é barato.");
        pergunta.setOpt2("Errado, mas como todo mundo faz eu também devo fazer.");
        pergunta.setOpt3("Errado, pois estamos prejudicando os autores.");
        pergunta.setOpt4("Nenhuma das respostas acima.");
        pergunta.setResposta("Errado, pois estamos prejudicando os autores.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Se você vê alguém roubando o que você faz?");
        pergunta.setOpt1("Fica olhando e não faz nada.");
        pergunta.setOpt2("Chama a policia.");
        pergunta.setOpt3("Rouba também, por que é divertido.");
        pergunta.setOpt4("Faz de conta que não viu.");
        pergunta.setResposta("Chama a policia.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Se alguém me oferece dinheiro para fazer algo de errado, eu:");
        pergunta.setOpt1("Aceito o dinheiro e faço o que foi pedido.");
        pergunta.setOpt2("Aceito, mas não faço o foi pedido.");
        pergunta.setOpt3("Me recuso e falo que não irei fazer.");
        pergunta.setOpt4("Negócio o valor do dinheiro.");
        pergunta.setResposta("Me recuso e falo que não irei fazer.");

        adicionarPergunta(pergunta);
        pergunta = new Pergunta();

        pergunta.setPergunta("Escrever o meu nome no banco do ônibus é:");
        pergunta.setOpt1("Errado e ainda é um crime.");
        pergunta.setOpt2("Certo, posso pichar os muros também.");
        pergunta.setOpt3("Errado, mas posso pichar o muro.");
        pergunta.setOpt4("Certo, pois admiro meu nome.");
        pergunta.setResposta("Errado e ainda é um crime.");

        adicionarPergunta(pergunta);
    }

    private void adicionarPergunta(Pergunta pergunta) {
        ContentValues values = new ContentValues();

        values.put(COLUNA_PERGUNTA, pergunta.getPergunta());
        values.put(COLUNA_OPT1, pergunta.getOpt1());
        values.put(COLUNA_OPT2, pergunta.getOpt2());
        values.put(COLUNA_OPT3, pergunta.getOpt3());
        values.put(COLUNA_OPT4, pergunta.getOpt4());
        values.put(COLUNA_RESPOSTA, pergunta.getResposta());

        dbOncreate.insert(TABELA_PERGUNTAS, null, values);
    }

    public List<Pergunta> pegarTodasPerguntas() {
        List<Pergunta> perguntas = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABELA_PERGUNTAS;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {

            if (cursor.moveToFirst()) {
                do {

                    Pergunta pergunta = new Pergunta();

                    pergunta.setId(cursor.getInt(0));
                    pergunta.setPergunta(cursor.getString(1));
                    pergunta.setResposta(cursor.getString(2));
                    pergunta.setOpt1(cursor.getString(3));
                    pergunta.setOpt2(cursor.getString(4));
                    pergunta.setOpt3(cursor.getString(5));
                    pergunta.setOpt4(cursor.getString(6));

                    perguntas.add(pergunta);

                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.d("ERRO", "Erro ao pegar todas as perguntas do banco de dados");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return perguntas;
    }

    public void adicionarJogador(Jogador jogador) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        try {

            ContentValues values = new ContentValues();

            values.put(COLUNA_NOME, jogador.getNome());
            values.put(COLUNA_PONTUACAO, jogador.getPontuacao());

            db.insert(TABELA_JOGADORES, null, values);

            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d("ERRO", "Erro ao adicionar jogador");
        } finally {
            db.endTransaction();
        }
    }

    public List<Jogador> pegarTodosJogadores() {
        List<Jogador> jogadores = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABELA_JOGADORES + " ORDER BY " + COLUNA_PONTUACAO + " DESC LIMIT 10";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {

            if (cursor.moveToFirst()) {
                do {

                    Jogador jogador = new Jogador();

                    jogador.setId(cursor.getInt(0));
                    jogador.setNome(cursor.getString(1));
                    jogador.setPontuacao(cursor.getInt(2));

                    jogadores.add(jogador);

                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.d("ERRO", "Erro ao pegar todos as jogadores do banco de dados");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return jogadores;
    }

}