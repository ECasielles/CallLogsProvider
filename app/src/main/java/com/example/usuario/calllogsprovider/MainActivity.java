package com.example.usuario.calllogsprovider;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Guarda información de las llamadas del teléfono
 */
public class MainActivity extends AppCompatActivity implements CallLogContract.View {

    private CallLogContract.Presenter presenter;
    private CallLogAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(android.R.id.list);
        adapter = new CallLogAdapter(this);
        listView.setAdapter(adapter);

        presenter = new CallLogsPresenter(this);
        presenter.getCallLogs();
    }

    @Override
    public void swap(Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    /**
     * Método que inicializa el adapter con un cursor
     */
    @Override
    public void setCursor(Cursor cursor) {
        //adapter.bindView(listView, this, cursor);
    }

    @Override
    public Context getContext() {
        return this;
    }

    //TODO: Filtrar por tipo llamada, formato fecha, cargar datos
}
