package com.example.usuario.calllogsprovider;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;


public class CallLogsPresenter implements LoaderManager.LoaderCallbacks<Cursor>,
    CallLogContract.Presenter {

    private CallLogContract.View view;
    private static final int CALLOG = 0;

    public CallLogsPresenter(CallLogContract.View view) {
        this.view = view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        CursorLoader loader = null;
        switch (id) {
            case CALLOG:
                String sortOrder = CallLog.Calls.DATE + " DESC";
                String[] projection = new String[] {
                        CallLog.Calls._ID,      //ID <--- CUIDAO
                        CallLog.Calls.NUMBER,   //Número
                        CallLog.Calls.DATE,     //Fecha
                        CallLog.Calls.DURATION, //Duración
                        CallLog.Calls.TYPE      //Tipo de la llamada
                };
                //Se pone calls por la cara
                Uri uri = Uri.withAppendedPath(CallLog.CONTENT_URI, "calls");
                loader = new CursorLoader(view.getContext(), uri, projection,
                        null, null, sortOrder);
                break;
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        view.swap(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.swap(null);
    }

    /**
     * Carga de forma asíncrona
     */
    @Override
    public void getCallLogs() {
        ((Activity)view.getContext()).getLoaderManager()
                .restartLoader(CALLOG, null, this);
    }

}
