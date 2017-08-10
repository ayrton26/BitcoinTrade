package br.com.ayrton.bitcointrade.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by ayrton on 28/07/2017.
 */

public class BitcoinTradeContract {

    public  static class Clientes implements BaseColumns{
        public static final String TABLE_NAME = "clientes";
        public static final String COLLUMN_NAME_NOME = "nome";
        public static final String COLLUMN_NAME_EMAIL = "email";
        public static final String COLLUMN_NAME_TELEFONE = "telefone";
        public static final String COLLUMN_NAME_TIPO = "tipo";
    }

    public static String[] clienteProjection = {
            Clientes._ID,
            Clientes.COLLUMN_NAME_NOME,
            Clientes.COLLUMN_NAME_EMAIL,
            Clientes.COLLUMN_NAME_TELEFONE,
            Clientes.COLLUMN_NAME_TIPO
    };

    static final String SQL_CREATE_CLIENTES =
            "CREATE TABLE " + Clientes.TABLE_NAME + " (" +
                    Clientes._ID + " INTEGER PRIMARY KEY," +
                    Clientes.COLLUMN_NAME_NOME + " TEXT," +
                    Clientes.COLLUMN_NAME_EMAIL + " TEXT," +
                    Clientes.COLLUMN_NAME_TELEFONE + " TEXT," +
                    Clientes.COLLUMN_NAME_TIPO + " INTEGER)";

    static final String SQL_DROP_CLIENTES =
            "DROP TABLE IF EXISTS" + Clientes.TABLE_NAME;


}