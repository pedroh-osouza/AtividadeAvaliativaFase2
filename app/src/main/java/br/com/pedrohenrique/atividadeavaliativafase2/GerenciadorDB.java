package br.com.pedrohenrique.atividadeavaliativafase2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class GerenciadorDB extends SQLiteOpenHelper
{
    private static final String DB_NAME = "tarefas.db";

    private static final int DB_VERSION = 1;
    public GerenciadorDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tarefas (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "descricao TEXT, " +
                "prioridate TEXT, " +
                "data_conclusao DATETIME, " +
                "notificacacoes INTEGER" +
                ");");
    }

    public boolean insertData(String descricao, String prioridade, String dataConclusao, Integer notificacoes)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("descricao", descricao);
        contentValues.put("prioridade", prioridade);
        contentValues.put("dataConclusao", dataConclusao);
        contentValues.put("notificacoes", notificacoes);
        long result = db.insert("tarefas", null, contentValues);
        return result != 1;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM tarefas", null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tarefas");
    }
}