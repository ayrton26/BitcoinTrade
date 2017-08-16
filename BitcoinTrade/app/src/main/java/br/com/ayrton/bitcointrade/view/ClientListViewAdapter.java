package br.com.ayrton.bitcointrade.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ayrton.bitcointrade.R;
import br.com.ayrton.bitcointrade.model.Cliente;
import br.com.ayrton.bitcointrade.persistence.adapters.ClienteDBAdapter;

/**
 * Created by ayrton on 15/08/17.
 */

public class ClientListViewAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        List<Cliente> clientes = new ArrayList<>();

        Context context;

        public ClientListViewAdapter(Context context, ClienteDBAdapter clienteDBAdapter) {
            super(context, -1);

            this.context = context;
            ArrayList<String> nomes = new ArrayList<>();
            clientes.clear();
            clientes.addAll(clienteDBAdapter.list());
            for (int i = 0; i < clientes.size(); ++i) {
                mIdMap.put(clientes.get(i).getNome(), i);
                nomes.add(clientes.get(i).getNome());
            }
            addAll(nomes);
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        public Cliente getCliente(int position){
            return clientes.get(position);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.simple_list_item, parent, false);
            TextView textViewNome = (TextView) rowView.findViewById(R.id.textViewNome);
            TextView textViewEmail = (TextView) rowView.findViewById(R.id.textViewEmail);
            TextView textViewTelefone = (TextView) rowView.findViewById(R.id.textViewTelefone);
            TextView textViewTipo = (TextView) rowView.findViewById(R.id.textViewTipo);
            Cliente cliente = clientes.get(position);

            textViewNome.setText(cliente.getNome());
            textViewEmail.setText(cliente.getEmail());
            String telefone = (cliente.getTelefone().isEmpty())? "Telefone não setado" : cliente.getTelefone();
            textViewTelefone.setText(telefone);
            textViewTipo.setText(cliente.getTipo().toString());

            return rowView;
        }
}
