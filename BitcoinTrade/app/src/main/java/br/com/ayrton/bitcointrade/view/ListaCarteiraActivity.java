package br.com.ayrton.bitcointrade.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import br.com.ayrton.bitcointrade.R;
import br.com.ayrton.bitcointrade.model.Cliente;
import br.com.ayrton.bitcointrade.persistence.adapters.CarteiraDBAdapter;

public class ListaCarteiraActivity extends AppCompatActivity {
    private CarteiraDBAdapter carteiraDBAdapter;
    private Cliente clienteExistente;
    private ListView carteiraListView;
    private Activity myself;
    //private CarteiraListViewAdapter carteiraListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carteira);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clienteExistente = (Cliente) getIntent().getSerializableExtra("Cliente");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myself, FormularioCarteiraActivity.class);
                startActivity(intent);
            }
        });
    }

}
