package com.example.usuario.calllogsprovider;

import android.content.Context;
import android.database.Cursor;


public interface CallLogContract {

    interface View {
        void setCursor(Cursor cursor);

        Context getContext();

        void swap(Cursor cursor);
    }

    interface Presenter {
        void getCallLogs();
    }

}
