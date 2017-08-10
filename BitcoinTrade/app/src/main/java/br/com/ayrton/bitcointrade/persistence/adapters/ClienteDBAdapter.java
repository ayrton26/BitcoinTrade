package br.com.ayrton.bitcointrade.persistence.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.ayrton.bitcointrade.model.Cliente;
import br.com.ayrton.bitcointrade.model.TipoCliente;
import br.com.ayrton.bitcointrade.persistence.BitcoinTradeContract;
import br.com.ayrton.bitcointrade.persistence.BitcoinTradeDbHelper;

/**
 * Created by ayrton on 01/08/2017.
 */

public class ClienteDBAdapter {
    BitcoinTradeDbHelper dbHelper;
    SQLiteDatabase db;

    public ClienteDBAdapter (Context context){
        dbHelper = new BitcoinTradeDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void insert(Cliente cliente){
        ContentValues values = new ContentValues();
        values.put(BitcoinTradeContract.Clientes.COLLUMN_NAME_NOME, cliente.getNome());
        values.put(BitcoinTradeContract.Clientes.COLLUMN_NAME_EMAIL, cliente.getEmail());
        values.put(BitcoinTradeContract.Clientes.COLLUMN_NAME_TELEFONE, cliente.getTelefone());
        values.put(BitcoinTradeContract.Clientes.COLLUMN_NAME_TIPO, cliente.getTipo().getId());
        db.insert(BitcoinTradeContract.Clientes.TABLE_NAME, null, values);
        Log.d("MainActivity", "Cliente Inserido");
    }

    public void  update(Cliente cliente){
        ContentValues values = new ContentValues();
        values.put(BitcoinTradeContract.Clientes.COLLUMN_NAME_NOME, cliente.getNome());
        values.put(BitcoinTradeContract.Clientes.COLLUMN_NAME_EMAIL, cliente.getEmail());
        values.put(BitcoinTradeContract.Clientes.COLLUMN_NAME_TELEFONE, cliente.getTelefone());
        values.put(BitcoinTradeContract.Clientes.COLLUMN_NAME_TIPO, cliente.getTipo().getId());
        String selection = BitcoinTradeContract.Clientes._ID + " = ?";
        String[] selectionArgs = { "" + cliente.getId() };
        db.update(BitcoinTradeContract.Clientes.TABLE_NAME, values, selection, selectionArgs);
        Log.d("MainActivity", "Cliente Atualizado");
    }

    public void  delete(Cliente cliente){
        String selection = BitcoinTradeContract.Clientes._ID + " = ?";
        String[] selectionArgs = { "" + cliente.getId() };
        db.delete(BitcoinTradeContract.Clientes.TABLE_NAME, selection, selectionArgs);
        Log.d("MainActivity", "Cliente Deletado");
    }

    public List<Cliente> list(){
        List<Cliente> list = new ArrayList<>();
        Cursor c = db.query(
                BitcoinTradeContract.Clientes.TABLE_NAME,
                BitcoinTradeContract.clienteProjection,
                null,
                null,
                null,
                null,
                null
        );
        int idIndex = c.getColumnIndex(BitcoinTradeContract.Clientes._ID);
        int nomeIndex = c.getColumnIndex(BitcoinTradeContract.Clientes.COLLUMN_NAME_NOME);
        int emailIndex = c.getColumnIndex(BitcoinTradeContract.Clientes.COLLUMN_NAME_EMAIL);
        int telefoneIndex = c.getColumnIndex(BitcoinTradeContract.Clientes.COLLUMN_NAME_TELEFONE);
        int tipoIndex = c.getColumnIndex(BitcoinTradeContract.Clientes.COLLUMN_NAME_TIPO);

        while (c.moveToNext()){
            Cliente cliente = new Cliente(
                    c.getInt(idIndex),
                    c.getString(nomeIndex),
                    c.getString(emailIndex),
                    c.getString(telefoneIndex),
                    TipoCliente.mapping(c.getInt(tipoIndex)));
            list.add(cliente);

        }
        c.close();
        return list;
    }

}
