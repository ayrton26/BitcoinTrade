package br.com.ayrton.bitcointrade.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ayrton on 31/07/2017.
 */

public class BitcoinTradeDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BitcoinTrade.db";

    public BitcoinTradeDbHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(BitcoinTradeContract.SQL_CREATE_CLIENTES);
        db.execSQL(BitcoinTradeContract.SQL_CREATE_CARTEIRAS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(BitcoinTradeContract.SQL_DROP_CARTEIRAS);
        db.execSQL(BitcoinTradeContract.SQL_DROP_CLIENTES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

}
