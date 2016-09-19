package com.example.samuel.bancosangue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samuel on 17/09/2016.
 */
public class BancoTela extends SQLiteOpenHelper {
    public static String NOME_BD = "bd_cadastro";
    public static int VERSAO = 1;
    public BancoTela(Context context) {
        super(context, NOME_BD, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS cadastro_tbl (" + "id_cadastros INTEGER PRIMARY KEY autoincrement," +
                " hospital varchar(45) NOT NULL ," + " paciente varchar(45) NOT NULL," + "" +
                " tipoSangue varchar(3) NOT NULL" + ");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean inserirCadastroBD(String hospital, String paciente, String tipoSangue) {
        SQLiteDatabase sqlDB = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("hospital", hospital);
        valores.put("paciente", paciente);
        valores.put("tipoSangue", tipoSangue);
        long result = sqlDB.insert("cadastro_tbl", null, valores);
        //int result2 = sqlDB.delete("cadastro_tbl", "hospital=?", new String[]{""});

        sqlDB.close();
        if (result!=-1) {
            return true;
        }
        return false;

    }


      public List<String> getListaCadastrosBD() {
        SQLiteDatabase sqlDB = getReadableDatabase();
        List<String> cadastros = new ArrayList<String>();
        Cursor cursor = sqlDB.query("cadastro_tbl", new String[]{"hospital", "paciente", "tipoSangue"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            cadastros.add("\nHospital: "+cursor.getString(0) + "\nPaciente: " + cursor.getString(1) + "\nTipo Sanguíneo: " + cursor.getString(2)+"\n");
        }
        cursor.close();
        sqlDB.close();

        return cadastros;
    }
}
