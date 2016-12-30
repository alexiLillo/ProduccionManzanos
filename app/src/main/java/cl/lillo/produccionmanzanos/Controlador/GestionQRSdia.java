package cl.lillo.produccionmanzanos.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Calendar;

import cl.lillo.produccionmanzanos.Modelo.ConexionHelperSQLServer;
import cl.lillo.produccionmanzanos.Modelo.ConexionHelperSQLite;

/**
 * Created by Usuario on 29/09/2016.
 */

public class GestionQRSdia {

    private static final String TAG = "GestionQRSdia";

    private ConexionHelperSQLite helper;
    private ConexionHelperSQLServer helperSQLServer;

    public GestionQRSdia(Context context) {
        helper = new ConexionHelperSQLite(context);
        helperSQLServer = new ConexionHelperSQLServer();
    }

    public void insertarLocal(String qr) {
        try {
            SQLiteDatabase data = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("Qr", qr);
            cv.put("Fecha", getDateActual());
            data.insert("QRSdia", null, cv);
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al insertar QRSdia local: " + ex.getMessage());
        }
    }

    public boolean existeLocal(String qr) {
        boolean bandera = false;
        try {
            SQLiteDatabase data = helper.getReadableDatabase();
            Cursor cursor = data.rawQuery("select * from QRSdia where Qr ='" + qr + "' and Fecha = '" + getDateActual() + "'", null);
            while (cursor.moveToNext()) {
                bandera = true;
            }
        } catch (Exception ex) {
            Log.w(TAG, "...Error al seleccionar desde tabla QRSdia: " + ex.getMessage());
            return bandera;
        }
        return bandera;
    }

    public void deleteLocal() {
        try {
            SQLiteDatabase data = helper.getWritableDatabase();
            data.delete("QRSdia", "Fecha != '" + getDateActual() + "'", null);
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al vaciar tabla QRSdia: " + ex.getMessage());
        }
    }

    public String getDateActual() {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        String dia = "" + day;
        int month = c.get(Calendar.MONTH) + 1;
        String mes = "" + month;
        int year = c.get(Calendar.YEAR);
        if (day < 10)
            dia = "0" + day;
        if (month < 10)
            mes = "0" + mes;
        String fecha = dia + "/" + mes + "/" + year;
        int hour = c.get(Calendar.HOUR_OF_DAY);
        String hora = "" + hour;
        int min = c.get(Calendar.MINUTE);
        String minu = "" + min;
        if (hour < 10)
            hora = "0" + hour;
        if (min < 10)
            minu = "0" + min;
        String horario = hora + ":" + minu;

        return fecha;
    }


}
