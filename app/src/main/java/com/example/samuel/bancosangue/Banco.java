package com.example.samuel.bancosangue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Samuel on 17/09/2016.
 */
public class Banco extends SQLiteOpenHelper {
        public static String NOME_BD = "bd_usuarios";
        public static int VERSAO = 1;
        public Banco(Context context) {
            super(context, NOME_BD, null, VERSAO);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS usuarios_tbl (" + "id_usuarios INTEGER PRIMARY KEY autoincrement," +
                    " usuario varchar(45) NOT NULL ," + " senha varchar(45) NOT NULL" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public boolean inserirUsuarioBD(String usuario, String senha) {
            SQLiteDatabase sqlDB = getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put("usuario", usuario);
            valores.put("senha", senha);
            long result = sqlDB.insert("usuarios_tbl", null, valores);
            sqlDB.close();
            if (result!=-1) {
                return true;
            }
            return false;
        }

        public boolean validateUser(String usuario, String senha){
            Cursor c = getReadableDatabase().rawQuery(
                    "SELECT * FROM " + "usuarios_tbl" + " WHERE "
                            + " usuario " + "='" + (usuario.toString()) +"'AND"+
                            " senha "+"='"+(senha.toString())+"'" ,  null);
            if (c.getCount()>0)
                return true;
            return false;
        }
}

