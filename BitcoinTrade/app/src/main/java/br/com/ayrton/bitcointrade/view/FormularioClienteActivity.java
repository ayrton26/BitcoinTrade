package br.com.ayrton.bitcointrade.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import br.com.ayrton.bitcointrade.R;
import br.com.ayrton.bitcointrade.model.Cliente;
import br.com.ayrton.bitcointrade.model.TipoCliente;
import br.com.ayrton.bitcointrade.persistence.adapters.ClienteDBAdapter;

public class FormularioClienteActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextTelefone;
    private TipoCliente tipo;
    private ClienteDBAdapter clienteDBAdapter;
    private Cliente clienteExistente;
    private RadioButton radioButtonVIP;
    private RadioButton radioButtonPREMIUM;
    private RadioButton radioButtonREGULAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clienteExistente = (Cliente) getIntent().getSerializableExtra("Cliente");

        clienteDBAdapter = new ClienteDBAdapter(getApplicationContext());

        setContentView(R.layout.activity_formulario_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSalvar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarCliente();
            }
        });

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextTelefone = (EditText) findViewById(R.id.editTextTelefone);
        radioButtonVIP = (RadioButton) findViewById(R.id.radioButtonVIP);
        radioButtonPREMIUM = (RadioButton) findViewById(R.id.radioButtonPremium);
        radioButtonREGULAR = (RadioButton) findViewById(R.id.radioButtonRegular);
        tipo = TipoCliente.REGULAR;

        if (clienteExistente != null){
            editTextNome.setText(clienteExistente.getNome());
            editTextEmail.setText(clienteExistente.getEmail());
            editTextTelefone.setText(clienteExistente.getTelefone());
            tipo = clienteExistente.getTipo();
            switch (tipo){
                case VIP:
                    radioButtonVIP.setChecked(true);
                break;
                case PREMIUM:
                    radioButtonPREMIUM.setChecked(true);
                    break;

            }
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioButtonPremium:
                if (checked) {
                    Log.d("FormularioCliente", "PREMIUM");
                    tipo = TipoCliente.PREMIUM;
                }
                break;
            case R.id.radioButtonVIP:
                if (checked) {
                    Log.d("FormularioCliente", "VIP");
                    tipo = TipoCliente.VIP;
                }
                break;
            case R.id.radioButtonRegular:
                if (checked) {
                    Log.d("FormularioCliente", "Regular");
                    tipo = TipoCliente.REGULAR;
                }
                break;
        }
    }

    public void salvarCliente() {
        if (editTextNome.getText().toString().trim().isEmpty()){
            Snackbar message = Snackbar.make(findViewById(R.id.formularioClienteCoordinatorLayout), "Nome Vazio", Snackbar.LENGTH_SHORT);
            message.show();
            Log.d("FormularioCliente", "Nome Vazio");
            return;
        }
        if (editTextEmail.getText().toString().trim().isEmpty()){
            Snackbar message = Snackbar.make(findViewById(R.id.formularioClienteCoordinatorLayout), "Email Vazio", Snackbar.LENGTH_SHORT);
            message.show();
            Log.d("FormularioCliente", "Email Vazio");
            return;
        }
        Cliente clienteNovo = new Cliente(
                editTextNome.getText().toString(),
                editTextEmail.getText().toString(),
                editTextTelefone.getText().toString(),
                tipo
        );
        if (clienteExistente != null){
            clienteNovo.setId(clienteExistente.getId());
            clienteDBAdapter.update(clienteNovo);
        } else {
            clienteDBAdapter.insert(clienteNovo);
        }
        finish();
    }
}
