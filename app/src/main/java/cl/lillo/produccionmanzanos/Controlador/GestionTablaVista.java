package cl.lillo.produccionmanzanos.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import cl.lillo.produccionmanzanos.Modelo.ConexionHelperSQLServer;
import cl.lillo.produccionmanzanos.Modelo.ConexionHelperSQLite;
import cl.lillo.produccionmanzanos.Modelo.TablaVista;

/**
 * Created by Usuario on 31/08/2016.
 */
public class GestionTablaVista {

    private static final String TAG = "gestionTablaVista";

    private ConexionHelperSQLite helper;
    private ConexionHelperSQLServer helperSQLServer;

    public GestionTablaVista(Context context) {
        helper = new ConexionHelperSQLite(context);
        helperSQLServer = new ConexionHelperSQLServer();
    }

    private boolean insertLocal(TablaVista tablaVista) {
        try {
            SQLiteDatabase data = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("ID_Fundo", tablaVista.getID_Fundo());
            cv.put("nombreFundo", tablaVista.getNombreFundo());
            cv.put("ID_Potrero", tablaVista.getID_Potrero());
            cv.put("nombrePotrero", tablaVista.getNombrePotrero());
            cv.put("ID_Sector", tablaVista.getID_Sector());
            cv.put("nombreSector", tablaVista.getNombreSector());
            cv.put("ID_Variedad", tablaVista.getID_Variedad());
            cv.put("nombreVariedad", tablaVista.getNombreVariedad());
            cv.put("ID_Cuartel", tablaVista.getID_Cuartel());
            cv.put("nombreCuartel", tablaVista.getNombreCuartel());
            cv.put("ID_Mapeo", tablaVista.getID_Mapeo());
            cv.put("id_Producto", tablaVista.getID_Producto());
            data.insertWithOnConflict("TablaVista", null, cv, SQLiteDatabase.CONFLICT_IGNORE);
            data.close();
            return true;
        } catch (Exception ex) {
            Log.w(TAG, "...Error al insertar TablaVista local: " + ex.getMessage());
            return false;
        }
    }

    private boolean deleteLocal() {
        try {
            SQLiteDatabase data = helper.getWritableDatabase();
            data.delete("TablaVista", null, null);
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al vaciar tabla TablaVista: " + ex.getMessage());
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
                //ID_Producto = '25' para manzanos
                String query = "select * from VistaApkPesaje where ID_Producto = '25'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    TablaVista tablaVista = new TablaVista();
                    tablaVista.setID_Fundo(rs.getString("ID_Fundo"));
                    tablaVista.setNombreFundo(rs.getString("nombreFundo"));
                    tablaVista.setID_Potrero(rs.getString("ID_Potrero"));
                    tablaVista.setNombrePotrero(rs.getString("nombrePotrero"));
                    tablaVista.setID_Sector(rs.getString("ID_Sector"));
                    tablaVista.setNombreSector(rs.getString("nombreSector"));
                    tablaVista.setID_Variedad(rs.getString("ID_Variedad"));
                    tablaVista.setNombreVariedad(rs.getString("nombreVariedad"));
                    tablaVista.setID_Cuartel(rs.getString("ID_Cuartel"));
                    tablaVista.setNombreCuartel(rs.getString("nombreCuartel"));
                    tablaVista.setID_Mapeo(rs.getInt("ID_Mapeo"));
                    tablaVista.setID_Producto(rs.getString("ID_Producto"));

                    insertLocal(tablaVista);
                }
                con.close();
            }
        } catch (Exception ex) {
            Log.w(TAG, "...Error al seleccionar TablaVista del servidor: " + ex.getMessage());
            return false;
        }
        return true;
    }

    //select para spinners
    public ArrayList<String> selectFundo() {
        ArrayList<String> lista = new ArrayList<>();
        try {
            SQLiteDatabase data = helper.getReadableDatabase();
            Cursor cursor = data.rawQuery("select distinct ID_Fundo, nombreFundo from TablaVista", null);
            if (cursor.getCount() > 1) {
                lista.add("Seleccione...");
            }
            while (cursor.moveToNext()) {
                lista.add(cursor.getString(1) + " - " + cursor.getString(0));
            }
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al seleccionar Fundo de TablaVista: " + ex.getMessage());
        }
        return lista;
    }

    public ArrayList<String> selectPotrero(String id_fundo) {
        ArrayList<String> lista = new ArrayList<>();
        try {
            SQLiteDatabase data = helper.getReadableDatabase();
            Cursor cursor = data.rawQuery("select distinct ID_Potrero, nombrePotrero from TablaVista where ID_Fundo = '" + id_fundo + "'", null);
            if (cursor.getCount() > 1) {
                lista.add("Seleccione...");
            }
            while (cursor.moveToNext()) {
                lista.add(cursor.getString(0));
                //listaFundos.add(cursor.getString(1) + " - " + cursor.getString(0));
            }
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al seleccionar Potrero de TablaVista: " + ex.getMessage());
        }
        return lista;
    }

    public ArrayList<String> selectSector(String id_fundo, String id_potrero) {
        ArrayList<String> lista = new ArrayList<>();
        try {
            SQLiteDatabase data = helper.getReadableDatabase();
            Cursor cursor = data.rawQuery("select distinct ID_Sector, nombreSector from TablaVista where ID_Fundo = '" + id_fundo + "' and ID_Potrero = '" + id_potrero + "'", null);
            if (cursor.getCount() > 1) {
                lista.add("Seleccione...");
            }
            while (cursor.moveToNext()) {
                lista.add(cursor.getString(0));
                //listaFundos.add(cursor.getString(1) + " - " + cursor.getString(0));
            }
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al seleccionar Sector de TablaVista: " + ex.getMessage());
        }
        return lista;
    }

    public ArrayList<String> selectVariedad(String id_fundo, String id_potrero, String id_sector) {
        ArrayList<String> lista = new ArrayList<>();
        try {
            SQLiteDatabase data = helper.getReadableDatabase();
            Cursor cursor = data.rawQuery("select distinct ID_Variedad, nombreVariedad from TablaVista where ID_Fundo = '" + id_fundo + "' and ID_Potrero = '" + id_potrero + "' and ID_Sector = '" + id_sector + "'", null);
            if (cursor.getCount() > 1) {
                lista.add("Seleccione...");
            }
            while (cursor.moveToNext()) {
                lista.add(cursor.getString(1) + " - " + cursor.getString(0));
            }
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al seleccionar Variedad de TablaVista: " + ex.getMessage());
        }
        return lista;
    }

    public ArrayList<String> selectCuartel(String id_fundo, String id_potrero, String id_sector, String id_variedad) {
        ArrayList<String> lista = new ArrayList<>();
        try {
            SQLiteDatabase data = helper.getReadableDatabase();
            Cursor cursor = data.rawQuery("select distinct ID_Cuartel, nombreCuartel from TablaVista  where ID_Fundo = '" + id_fundo + "' and ID_Potrero = '" + id_potrero + "' and ID_Sector = '" + id_sector + "' and ID_Variedad = '" + id_variedad + "'", null);
            if (cursor.getCount() > 1) {
                lista.add("Seleccione...");
            }
            while (cursor.moveToNext()) {
                lista.add(cursor.getString(1) + " - " + cursor.getString(0));
            }
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al seleccionar Cuartel de TablaVista: " + ex.getMessage());
        }
        return lista;
    }

    public int lastMapeo() {
        int mapeo = 0;
        try {
            SQLiteDatabase data = helper.getReadableDatabase();
            Cursor cursor = data.rawQuery("select distinct max(ID_Mapeo) from TablaVista", null);
            while (cursor.moveToNext()) {
                mapeo = cursor.getInt(0);
            }
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al seleccionar maximo Mapeo de TablaVista: " + ex.getMessage());
        }
        return mapeo;
    }
}
