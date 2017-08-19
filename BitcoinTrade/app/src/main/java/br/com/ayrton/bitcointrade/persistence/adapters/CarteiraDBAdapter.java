package br.com.ayrton.bitcointrade.persistence.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.ayrton.bitcointrade.model.Carteira;
import br.com.ayrton.bitcointrade.model.Cliente;
import br.com.ayrton.bitcointrade.model.TipoCliente;
import br.com.ayrton.bitcointrade.persistence.BitcoinTradeContract;
import br.com.ayrton.bitcointrade.persistence.BitcoinTradeDbHelper;

/**
 * Created by ayrton on 19/08/17.
 */

public class CarteiraDBAdapter {
    BitcoinTradeDbHelper dbHelper;
    SQLiteDatabase db;

    public CarteiraDBAdapter (Context context){
        dbHelper = new BitcoinTradeDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void insert(Carteira carteira){
        ContentValues values = new ContentValues();
        values.put(BitcoinTradeContract.Carteiras.COLLUMN_NAME_DESCRICAO, carteira.getDescricao());
        values.put(BitcoinTradeContract.Carteiras.COLLUMN_NAME_SALDO, carteira.getSaldo());
        values.put(BitcoinTradeContract.Carteiras.COLLUMN_NAME_CLIENTE_ID, carteira.getCliente().getId());
        db.insert(BitcoinTradeContract.Carteiras.TABLE_NAME, null, values);
    }

    public void update(Carteira carteira){
        ContentValues values = new ContentValues();
        values.put(BitcoinTradeContract.Carteiras.COLLUMN_NAME_DESCRICAO, carteira.getDescricao());
        values.put(BitcoinTradeContract.Carteiras.COLLUMN_NAME_SALDO, carteira.getSaldo());
        values.put(BitcoinTradeContract.Carteiras.COLLUMN_NAME_CLIENTE_ID, carteira.getCliente().getId());
        String selection = BitcoinTradeContract.Carteiras._ID + " = ?";
        String[] selectionArgs = {"" + carteira.getId() };
        db.update(BitcoinTradeContract.Carteiras.TABLE_NAME, values, selection, selectionArgs);
    }

    public void delete(Carteira carteira){
        String selection = BitcoinTradeContract.Carteiras._ID + " = ?";
        String[] selectionArgs = {"" + carteira.getId() };
        db.delete(BitcoinTradeContract.Carteiras.TABLE_NAME, selection, selectionArgs);
    }

    public List<Carteira> list(){
        List<Carteira> list = new ArrayList<>();
        Cursor c = db.query(
                BitcoinTradeContract.Carteiras.TABLE_NAME,
                BitcoinTradeContract.carteiraProjection,
                null,
                null,
                null,
                null,
                null
        );

        int idIndex = c.getColumnIndex(BitcoinTradeContract.Carteiras._ID);
        int descricaoIndex = c.getColumnIndex(BitcoinTradeContract.Carteiras.COLLUMN_NAME_DESCRICAO);
        int saldoIndex = c.getColumnIndex(BitcoinTradeContract.Carteiras.COLLUMN_NAME_SALDO);
        int clienteIdIndex = c.getColumnIndex(BitcoinTradeContract.Carteiras.COLLUMN_NAME_CLIENTE_ID);

        while (c.moveToNext()){
            Carteira carteira = new Carteira(
                    c.getInt(idIndex),
                    c.getString(descricaoIndex),
                    c.getDouble(saldoIndex),
                    new Cliente(c.getInt(clienteIdIndex), null, null, null, null)
            );
            list.add(carteira);

        }
        c.close();
        return list;
    }
}
