package br.com.ayrton.bitcointrade;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ayrton.bitcointrade.model.Cliente;
import br.com.ayrton.bitcointrade.model.TipoCliente;
import br.com.ayrton.bitcointrade.persistence.BitcoinTradeContract;
import br.com.ayrton.bitcointrade.persistence.BitcoinTradeDbHelper;
import br.com.ayrton.bitcointrade.persistence.adapters.ClienteDBAdapter;
import br.com.ayrton.bitcointrade.view.ClientListViewAdapter;
import br.com.ayrton.bitcointrade.view.FormularioClienteActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ClienteDBAdapter clienteDBAdapter;
    private Activity myself;
    private ListView clientListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myself = this;
        clienteDBAdapter = new ClienteDBAdapter(getApplicationContext());


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myself, FormularioClienteActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        clientListView = (ListView) findViewById(R.id.clienteListView);

    }
    @Override
    protected void onResume(){
        super.onResume();

        clientListView.setAdapter(new ClientListViewAdapter(getApplicationContext(), clienteDBAdapter));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Cliente cliente = new Cliente("Catapobio", "cata@gmail", "8799319990", TipoCliente.PREMIUM);
            clienteDBAdapter.insert(cliente);
        } else if (id == R.id.nav_gallery) {
            List<Cliente> list = clienteDBAdapter.list();
            for (Cliente c : list){
                Log.d("MainActivity",
                        "ID: " + c.getId() +
                        " Nome: " + c.getNome() +
                        " Email: " + c.getEmail() +
                        " Telefone: " + c.getTelefone() +
                        " Tipo: " + c.getTipo());
            }

        } else if (id == R.id.nav_slideshow) {
            Cliente cliente = new Cliente(5,"Catapobio", "cata@gmail", "8799319990", TipoCliente.PREMIUM);
            clienteDBAdapter.delete(cliente);

        } else if (id == R.id.nav_manage) {
            Cliente cliente = new Cliente(5,"Catapobio", "cata@gmail", "8799319990", TipoCliente.PREMIUM);
            cliente.setNome("Jose");
            clienteDBAdapter.update(cliente);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
