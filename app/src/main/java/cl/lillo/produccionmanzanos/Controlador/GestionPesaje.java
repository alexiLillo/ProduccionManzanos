package cl.lillo.produccionmanzanos.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import cl.lillo.produccionmanzanos.Modelo.ConexionHelperSQLServer;
import cl.lillo.produccionmanzanos.Modelo.ConexionHelperSQLite;
import cl.lillo.produccionmanzanos.Modelo.Pesaje;

/**
 * Created by Usuario on 31/08/2016.
 */
public class GestionPesaje {

    private static final String TAG = "gestionPesaje";

    private ConexionHelperSQLite helper;
    private ConexionHelperSQLServer helperSQLServer;

    public GestionPesaje(Context context) {
        helper = new ConexionHelperSQLite(context);
        helperSQLServer = new ConexionHelperSQLServer();
    }

    public boolean insertLocal(Pesaje pesaje) {
        try {
            SQLiteDatabase data = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("Producto", pesaje.getProducto());
            cv.put("QRenvase", pesaje.getQRenvase());
            cv.put("RutTrabajador", pesaje.getRutTrabajador());
            cv.put("RutPesador", pesaje.getRutPesador());
            cv.put("Fundo", pesaje.getFundo());
            cv.put("Potrero", pesaje.getPotrero());
            cv.put("Sector", pesaje.getSector());
            cv.put("Variedad", pesaje.getVariedad());
            cv.put("Cuartel", pesaje.getCuartel());
            cv.put("FechaHora", pesaje.getFechaHora());
            cv.put("PesoNeto", pesaje.getPesoNeto());
            cv.put("Tara", pesaje.getTara());
            cv.put("Formato", pesaje.getFormato());
            cv.put("TotalCantidad", pesaje.getTotalCantidad());
            cv.put("Factor", pesaje.getFactor());
            cv.put("Cantidad", pesaje.getCantidad());
            cv.put("Lectura_SVAL", pesaje.getLectura_SVAL());
            cv.put("ID_Map", pesaje.getID_Map());
            cv.put("TipoRegistro", pesaje.getTipoRegistro());
            cv.put("FechaHoraModificacion", pesaje.getFechaHoraModificacion());
            cv.put("UsuarioModificacion", pesaje.getUsuarioModificaion());
            data.insertWithOnConflict("Pesaje", null, cv, SQLiteDatabase.CONFLICT_IGNORE);
            data.close();
            return true;
        } catch (Exception ex) {
            Log.w(TAG, "...Error al insertar pesaje local: " + ex.getMessage());
            return false;
        }
    }

    public boolean insertLocalSync(Pesaje pesaje) {
        try {
            SQLiteDatabase data = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("Producto", pesaje.getProducto());
            cv.put("QRenvase", pesaje.getQRenvase());
            cv.put("RutTrabajador", pesaje.getRutTrabajador());
            cv.put("RutPesador", pesaje.getRutPesador());
            cv.put("Fundo", pesaje.getFundo());
            cv.put("Potrero", pesaje.getPotrero());
            cv.put("Sector", pesaje.getSector());
            cv.put("Variedad", pesaje.getVariedad());
            cv.put("Cuartel", pesaje.getCuartel());
            cv.put("FechaHora", pesaje.getFechaHora());
            cv.put("PesoNeto", pesaje.getPesoNeto());
            cv.put("Tara", pesaje.getTara());
            cv.put("Formato", pesaje.getFormato());
            cv.put("TotalCantidad", pesaje.getTotalCantidad());
            cv.put("Factor", pesaje.getFactor());
            cv.put("Cantidad", pesaje.getCantidad());
            cv.put("Lectura_SVAL", pesaje.getLectura_SVAL());
            cv.put("ID_Map", pesaje.getID_Map());
            cv.put("TipoRegistro", pesaje.getTipoRegistro());
            cv.put("FechaHoraModificacion", pesaje.getFechaHoraModificacion());
            cv.put("UsuarioModificacion", pesaje.getUsuarioModificaion());
            data.insertWithOnConflict("PesajeSync", null, cv, SQLiteDatabase.CONFLICT_IGNORE);
            data.close();
            return true;
        } catch (Exception ex) {
            Log.w(TAG, "...Error al insertar pesaje local SYNC: " + ex.getMessage());
            return false;
        }
    }

    private ArrayList<Pesaje> selectLocalSync() {
        ArrayList<Pesaje> listaPesajes = new ArrayList<>();

        try {
            SQLiteDatabase data = helper.getReadableDatabase();
            Cursor cursor = data.rawQuery("select * from PesajeSync", null);
            while (cursor.moveToNext()) {
                Pesaje pesaje = new Pesaje();
                pesaje.setProducto(cursor.getString(0));
                pesaje.setQRenvase(cursor.getString(1));
                pesaje.setRutTrabajador(cursor.getString(2));
                pesaje.setRutPesador(cursor.getString(3));
                pesaje.setFundo(cursor.getString(4));
                pesaje.setPotrero(cursor.getString(5));
                pesaje.setSector(cursor.getString(6));
                pesaje.setVariedad(cursor.getString(7));
                pesaje.setCuartel(cursor.getString(8));
                pesaje.setFechaHora(cursor.getString(9));
                pesaje.setPesoNeto(cursor.getDouble(10));
                pesaje.setTara(cursor.getDouble(11));
                pesaje.setFormato(cursor.getString(12));
                pesaje.setTotalCantidad(cursor.getDouble(13));
                pesaje.setFactor(cursor.getDouble(14));
                pesaje.setCantidad(cursor.getDouble(15));
                pesaje.setLectura_SVAL(cursor.getString(16));
                pesaje.setID_Map(cursor.getInt(17));
                pesaje.setTipoRegistro(cursor.getString(18));
                pesaje.setFechaHoraModificacion(cursor.getString(19));
                pesaje.setUsuarioModificaion(cursor.getString(20));
                listaPesajes.add(pesaje);
            }
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al leer pesaje local SYNC: " + ex.getMessage());
        }
        return listaPesajes;
    }

    private ArrayList<Pesaje> selectTEST() {
        ArrayList<Pesaje> listaPesajes = new ArrayList<>();

        try {
            SQLiteDatabase data = helper.getReadableDatabase();
            Cursor cursor = data.rawQuery("select * from Pesaje", null);
            while (cursor.moveToNext()) {
                Pesaje pesaje = new Pesaje();
                pesaje.setProducto(cursor.getString(0));
                pesaje.setQRenvase(cursor.getString(1));
                pesaje.setRutTrabajador(cursor.getString(2));
                pesaje.setRutPesador(cursor.getString(3));
                pesaje.setFundo(cursor.getString(4));
                pesaje.setPotrero(cursor.getString(5));
                pesaje.setSector(cursor.getString(6));
                pesaje.setVariedad(cursor.getString(7));
                pesaje.setCuartel(cursor.getString(8));
                pesaje.setFechaHora(cursor.getString(9));
                pesaje.setPesoNeto(cursor.getDouble(10));
                pesaje.setTara(cursor.getDouble(11));
                pesaje.setFormato(cursor.getString(12));
                pesaje.setTotalCantidad(cursor.getDouble(13));
                pesaje.setFactor(cursor.getDouble(14));
                pesaje.setCantidad(cursor.getDouble(15));
                pesaje.setLectura_SVAL(cursor.getString(16));
                pesaje.setID_Map(cursor.getInt(17));
                pesaje.setTipoRegistro(cursor.getString(18));
                pesaje.setFechaHoraModificacion(cursor.getString(19));
                pesaje.setUsuarioModificaion(cursor.getString(20));
                listaPesajes.add(pesaje);
            }
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al leer pesaje local SYNC: " + ex.getMessage());
        }
        return listaPesajes;
    }

    boolean deleteLocalSync() {
        try {
            SQLiteDatabase data = helper.getWritableDatabase();
            data.delete("PesajeSync", null, null);
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al vaciar tabla PesajeSync: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteLocalOld() {
        try {
            //System.out.println("TABLA PESAJE ANTES DE BORRAR>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + selectTEST().size());
            SQLiteDatabase data = helper.getWritableDatabase();
            data.delete("Pesaje", "FechaHora < '" + getDateActualToDelete() + "'", null);
            data.close();
            //System.out.println("TABLA PESAJE DESPUES DE BORRAR>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + selectTEST().size());
        } catch (Exception ex) {
            Log.w(TAG, "...Error al vaciar tabla PesajeSync: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public void deleteLocalSyncIndividual(String qr) {
        try {
            SQLiteDatabase data = helper.getWritableDatabase();
            data.delete("PesajeSync", "QRenvase='" + qr + "'", null);
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al eliminar registro de tabla PesajeSync: " + ex.getMessage());
        }
    }

    public String selectLocalInsertServer() {
        ArrayList<Pesaje> listaPesajes = selectLocalSync();
        Iterator iterador = listaPesajes.listIterator();
        int contador = 0;
        int total = listaPesajes.size();
        while (iterador.hasNext()) {
            Pesaje p = (Pesaje) iterador.next();
            try {
                Connection con = helperSQLServer.CONN();
                if (con == null) {
                    Log.w(TAG, "...Error al conectar con el servidor");
                    //return guardado;
                } else {
                    //Consulta SQL
                    String query = "insert into Pesaje values ('" + p.getProducto() + "', '" + p.getQRenvase() + "', '" + p.getRutTrabajador() + "', '" + p.getRutPesador() + "', '" + p.getFundo() + "', '" + p.getPotrero() + "', '" + p.getSector() + "', '" + p.getVariedad() + "', '" + p.getCuartel() + "', '" + p.getFechaHora() + "', " + p.getPesoNeto() + ", " + p.getTara() + ", '" + p.getFormato() + "', " + p.getTotalCantidad() + ", " + p.getFactor() + ", " + p.getCantidad() + ", '" + p.getLectura_SVAL() + "', " + p.getID_Map() + ", '" + p.getTipoRegistro() + "', '" + p.getFechaHoraModificacion() + "', '" + p.getUsuarioModificaion() + "')";
                    Statement stmt = con.createStatement();
                    //executeUpdate devuelve el número de rows afectadas
                    if (stmt.executeUpdate(query) > 0) {
                        deleteLocalSyncIndividual(p.getQRenvase());
                        contador = contador + 1;
                    }
                    con.close();
                }
            } catch (Exception ex) {
                Log.w(TAG, "...Error al insertar pesaje en el servidor: " + ex.getMessage());
                //return guardado;
            }
        }
        return "Se sincronizaron " + contador + " de " + total + " registros";
    }

    public boolean insertServerTEST() {
        ArrayList<Pesaje> listaPesajes = selectTEST();
        Iterator iterador = listaPesajes.listIterator();
        while (iterador.hasNext()) {
            Pesaje p = (Pesaje) iterador.next();
            try {
                Connection con = helperSQLServer.CONN();
                if (con == null) {
                    Log.w(TAG, "...Error al conectar con el servidor");
                    return false;
                } else {
                    //Consulta SQL
                    String query = "insert into PesajeTest values ('" + p.getProducto() + "', '" + p.getQRenvase() + "', '" + p.getRutTrabajador() + "', '" + p.getRutPesador() + "', '" + p.getFundo() + "', '" + p.getPotrero() + "', '" + p.getSector() + "', '" + p.getVariedad() + "', '" + p.getCuartel() + "', '" + p.getFechaHora() + "', " + p.getPesoNeto() + ", " + p.getTara() + ", '" + p.getFormato() + "', " + p.getTotalCantidad() + ", " + p.getFactor() + ", " + p.getCantidad() + ", '" + p.getLectura_SVAL() + "', " + p.getID_Map() + ", '" + p.getTipoRegistro() + "', '" + p.getFechaHoraModificacion() + "', '" + p.getUsuarioModificaion() + "')";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);
                    con.close();
                }
            } catch (Exception ex) {
                Log.w(TAG, "...Error al insertar pesajeRESPALDO en el servidor: " + ex.getMessage());
                return false;
            }
        }
        return true;
    }

    public boolean puedePesar(String rut) {
        boolean bandera = true;
        try {
            SQLiteDatabase data = helper.getReadableDatabase();
            Cursor cursor = data.rawQuery("select FechaHora from Pesaje where RutTrabajador = '" + rut + "' order by FechaHora desc limit 1", null);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            while (cursor.moveToNext()) {
                if (sdf.parse(cursor.getString(0)).getTime() > (Date.parse(getDateActualmmddyyyy()) - (1000 * 60 * 10)))
                    bandera = false;
                System.out.println("RESTADO DE FECHA PARA PESAR ========================================================================== " + sdf.parse(cursor.getString(0)).getTime() + " - " + (Date.parse(getDateActualmmddyyyy()) - (1000 * 60 * 10)));
            }
        } catch (Exception ex) {
            Log.w(TAG, "..........................................................................................................................................................................|..Error al seleccionar pesaje (puede pesar) en el servidor: " + ex.getMessage());
            return true;
        }
        return bandera;
    }

    public int cantBandejas(String rut) {
        try {
            SQLiteDatabase data = helper.getReadableDatabase();
            Cursor cursor = data.rawQuery("select * from Pesaje where RutPesador = '" + rut + "' and FechaHora like '%" + getDateActual() + "%'", null);
            return cursor.getCount();
        } catch (Exception ex) {
            Log.w(TAG, "Error al contar cantidad bandejas: " + ex.getMessage());
            return 0;
        }
    }

    private String getDateActualmmddyyyy() {
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
        String fecha = mes + "/" + dia + "/" + year;
        int hour = c.get(Calendar.HOUR_OF_DAY);
        String hora = "" + hour;
        int min = c.get(Calendar.MINUTE);
        String minu = "" + min;
        if (hour < 10)
            hora = "0" + hour;
        if (min < 10)
            minu = "0" + min;
        String horario = hora + ":" + minu;

        return fecha + " " + horario;
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

    public String getDateActualToDelete() {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        String dia = "" + day;
        int month = c.get(Calendar.MONTH) + 1;
        String mes = "" + month;
        int year = c.get(Calendar.YEAR);

        if (day > 7) {
            day = day - 7;
        } else {
            if (month == 1) {
                year = year - 1;
                month = 12;
                day = day + 22;
            } else {
                month = month - 1;
                day = day + 22;
            }
        }

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
