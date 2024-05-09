package br.com.pedrohenrique.atividadeavaliativafase2;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class TarefasAdapter extends SimpleCursorAdapter {

    public TarefasAdapter(Context context, Cursor cursor) {
        super(context, android.R.layout.simple_list_item_2, cursor,
            new String[]{"descricao", "prioridade"},
            new int[]{android.R.id.text1, android.R.id.text2}, 0);
    }
}

