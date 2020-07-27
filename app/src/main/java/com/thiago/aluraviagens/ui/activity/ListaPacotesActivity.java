package com.thiago.aluraviagens.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thiago.aluraviagens.R;
import com.thiago.aluraviagens.dao.PacoteDAO;
import com.thiago.aluraviagens.model.Pacote;
import com.thiago.aluraviagens.ui.adapter.ListaPacotesAdapter;

import java.util.List;

import static com.thiago.aluraviagens.ui.activity.PacoteActivityConstantes.PACOTE;

public class ListaPacotesActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Pacotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacotes);
        setTitle(TITULO_APP_BAR);
        configuraLista();

    }

    private void configuraLista() {
        ListView listaDePacotes = findViewById(R.id.lista_pacotes_listview);
        final List<Pacote> pacotes = new PacoteDAO().lista();
        listaDePacotes.setAdapter(new ListaPacotesAdapter(pacotes, this));
        listaDePacotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Pacote pacoteClicado = pacotes.get(posicao);
                vaiParaResumoPacote(pacoteClicado);
            }

            private void vaiParaResumoPacote(Pacote pacoteClicado) {
                Intent intent = new Intent(ListaPacotesActivity.this,
                        ResumoPacoteActivity.class);
                intent.putExtra(PACOTE, pacoteClicado);
                startActivity(intent);
            }
        });
    }
}