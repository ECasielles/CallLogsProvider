package com.example.usuario.calllogsprovider;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class CallLogAdapter extends CursorAdapter {

    public CallLogAdapter(Context context) {
        //Se le pasa el flag para que sea observador contra observable
        //(cambios en cascada).
        //Cursor nulo.
        super(context, null, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_call, null);
        CallLogHolder holder = new CallLogHolder();
        holder.txvNumber = view.findViewById(R.id.txvNumber);
        holder.txvDate = view.findViewById(R.id.txvDate);
        holder.txvDuration = view.findViewById(R.id.txvDuration);
        holder.txvType = view.findViewById(R.id.txvType);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CallLogHolder holder = (CallLogHolder) view.getTag();
        //En el orden del projection

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(cursor.getLong(2));

        DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        String duracion = formatter.format(cursor.getLong(3));

        String tipo = "";
        switch(cursor.getInt(4)) {
            case CallLog.Calls.MISSED_TYPE:
                tipo = "Llamada perdida";
                break;
            case CallLog.Calls.OUTGOING_TYPE:
                tipo = "Llamada saliente";
                break;
            case CallLog.Calls.INCOMING_TYPE:
                tipo = "Llamada entrante";
                break;
        }

        holder.txvNumber.setText("Número: " + cursor.getString(1));
        holder.txvDate.setText("Fecha: " + simpleDateFormat.format(date));
        holder.txvDuration.setText("Duración : " + duracion);
        holder.txvType.setText("Tipo : " + tipo);
    }

    public class CallLogHolder {
        TextView txvNumber, txvDuration, txvDate, txvType;
    }

}
