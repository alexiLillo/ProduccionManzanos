package cl.lillo.produccionmanzanos.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import cl.lillo.produccionmanzanos.Modelo.ConexionHelperSQLServer;
import cl.lillo.produccionmanzanos.Modelo.ConexionHelperSQLite;
import cl.lillo.produccionmanzanos.Modelo.Producto;

/**
 * Created by Usuario on 30/12/2016.
 */

public class GestionProducto {

    private static final String TAG = "gestionProducto";

    private ConexionHelperSQLite helper;
    private ConexionHelperSQLServer helperSQLServer;

    public GestionProducto(Context context) {
        helper = new ConexionHelperSQLite(context);
        helperSQLServer = new ConexionHelperSQLServer();
    }

    private boolean insertLocal(Producto producto) {
        try {
            SQLiteDatabase data = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("ID_Producto", producto.getID_Producto());
            cv.put("Nombre", producto.getNombre());
            cv.put("TipoEvase", producto.getTipoEnvase());
            cv.put("KilosNetoEnvase", producto.getKilosNetoEnvase());
            data.insertWithOnConflict("Tara", null, cv, SQLiteDatabase.CONFLICT_IGNORE);
            data.close();
            return true;
        } catch (Exception ex) {
            Log.w(TAG, "...Error al insertar tabla Producto local: " + ex.getMessage());
            return false;
        }
    }

    public Producto selectLocal() {
        Producto producto = new Producto();
        try {
            SQLiteDatabase data = helper.getReadableDatabase();
            Cursor cursor = data.rawQuery("select * from Producto where ID_Producto = '25'", null);
            while (cursor.moveToNext()) {
                producto.setID_Producto(cursor.getString(0));
                producto.setNombre(cursor.getString(1));
                producto.setTipoEnvase(cursor.getString(2));
                producto.setKilosNetoEnvase(cursor.getDouble(3));
            }
        } catch (Exception ex) {
            Log.w(TAG, "...Error al seleccionar desde tabla Producto: " + ex.getMessage());
        }
        return producto;
    }

    private boolean deleteLocal() {
        try {
            SQLiteDatabase data = helper.getWritableDatabase();
            data.delete("Producto", null, null);
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al vaciar tabla Producto: " + ex.getMessage());
            return false;
        }
        return true;
    }

    boolean selectServerInsertLocal() {
        try {
            Connection con = helperSQLServer.CONN();
            if (con == null) {
                return false;
            } else if (deleteLocal()) {
                //Consulta SQL
                String query = "select * from Producto";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Producto producto = new Producto();
                    producto.setID_Producto(rs.getString("ID_Producto"));
                    producto.setNombre(rs.getString("Nombre"));
                    producto.setTipoEnvase(rs.getString("TipoEnvase"));
                    producto.setKilosNetoEnvase(rs.getDouble("KilosNetoEnvase"));

                    insertLocal(producto);
                }
                con.close();
            }
        } catch (Exception ex) {
            Log.w(TAG, "...Error al seleccionar tabla Producto del servidor: " + ex.getMessage());
            return false;
        }
        return true;
    }
}
