package cl.lillo.produccionmanzanos.Controlador;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cl.lillo.produccionmanzanos.Modelo.ConexionHelperSQLServer;
import cl.lillo.produccionmanzanos.Modelo.ConexionHelperSQLite;
import cl.lillo.produccionmanzanos.Modelo.Trabajador;

/**
 * Created by Usuario on 31/08/2016.
 */
public class GestionTrabajador {

    private static final String TAG = "gestionTrabajador";

    private ConexionHelperSQLite helper;
    private ConexionHelperSQLServer helperSQLServer;

    public GestionTrabajador(Context context) {
        helper = new ConexionHelperSQLite(context);
        helperSQLServer = new ConexionHelperSQLServer();
    }

    private boolean insertLocal(Trabajador trabajador) {
        try {
            SQLiteDatabase data = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("Rut", trabajador.getRut());
            cv.put("Nombre", trabajador.getNombre());
            cv.put("Apellido", trabajador.getApellido());
            cv.put("QRrut", trabajador.getQRrut());
            cv.put("FechaNacimiento", trabajador.getFechaNacimiento());
            cv.put("FechaIngreso", trabajador.getFechaIngreso());
            cv.put("Ficha", trabajador.getFicha());
            cv.put("Importado", trabajador.getImportado());
            data.insertWithOnConflict("Trabajador", null, cv, SQLiteDatabase.CONFLICT_IGNORE);
            data.close();
            return true;
        } catch (Exception ex) {
            Log.w(TAG, "...Error al insertar tabla Trabajador local: " + ex.getMessage());
            return false;
        }
    }

    public String getNombre(String rut){
        String nombre = "";
        String apellido = "";
        try {
            SQLiteDatabase data = helper.getReadableDatabase();
            Cursor cursor = data.rawQuery("select Nombre, Apellido from Trabajador where rut ='" + rut + "'", null);
            while (cursor.moveToNext()) {
                String[] cadenaNombre = cursor.getString(0).split(" ");
                nombre = cadenaNombre[0];
                String[] cadenaApellido = cursor.getString(1).split(" ");
                apellido = cadenaApellido[0];
            }
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al leer pesaje local SYNC: " + ex.getMessage());
        }
        return nombre + " " + apellido;
    }

    private boolean deleteLocal() {
        try {
            SQLiteDatabase data = helper.getWritableDatabase();
            data.delete("Trabajador", null, null);
            data.close();
        } catch (Exception ex) {
            Log.w(TAG, "...Error al vaciar tabla Trabajador: " + ex.getMessage());
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
                String query = "select * from Trabajador";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Trabajador trabajador = new Trabajador();
                    trabajador.setRut(rs.getString("Rut"));
                    trabajador.setNombre(rs.getString("Nombre"));
                    trabajador.setApellido(rs.getString("Apellido"));
                    trabajador.setQRrut(rs.getString("QRrut"));
                    trabajador.setFechaNacimiento(rs.getString("FechaNacimiento"));
                    trabajador.setFechaIngreso(rs.getString("FechaIngreso"));
                    trabajador.setFicha(rs.getInt("Ficha"));
                    trabajador.setImportado(rs.getString("Importado"));
                    insertLocal(trabajador);
                }
                con.close();
            }
        } catch (Exception ex) {
            Log.w(TAG, "...Error al seleccionar tabla Trabajador del servidor: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean existe(String qrrut) {
        try {
            SQLiteDatabase data = helper.getReadableDatabase();
            Cursor cursor = data.rawQuery("select QRrut from Trabajador where QRrut = '" + qrrut + "'", null);
            if (cursor.moveToNext()) return true;
            else return false;
        } catch (Exception ex) {
            Log.w(TAG, "...Error al comprobarsi existe trabajador: " + ex.getMessage());
            return false;
        }
    }

    public String resumenHistorico(String rut, int mapeo) {
        String nombre = "S/D";
        String bandejasDia = "S/D";
        String kilosDia = "S/D";
        String bandejasTotal = "S/D";
        String kilosTotal = "S/D";

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        String dia = "" + day;
        int month = c.get(Calendar.MONTH) + 1;
        String mes = "" + month;
        int year = c.get(Calendar.YEAR);
        String año = "" + year;
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        String hora = hour + ":" + min;

        try {
            Connection con = helperSQLServer.CONN();
            if (con == null) {
                return nombre + "-" + bandejasTotal + "-" + kilosTotal;
            } else {
                //Consulta SQL
                String query = "Select Nombre, Apellido, PesoNeto, Cantidad from VistaConsulta where RutTrabajador = '" + rut + "' and ID_Map =" + mapeo + " and Anio = '" + año + "' and Mes = '" + mes + "' ";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    nombre = rs.getString("Nombre") + " " + rs.getString("Apellido");
                    bandejasTotal = rs.getString("Cantidad");
                    kilosTotal = rs.getString("PesoNeto");
                }
            }
        } catch (Exception ex) {
            Log.w(TAG, "...Error al seleccionar Resumen del servidor: " + ex.getMessage());
            return nombre + "-" + bandejasTotal + "-" + kilosTotal;
        }
        return nombre + "-" + bandejasTotal + "-" + kilosTotal;
    }

    public String resumenDia(String rut, int mapeo) {
        String nombre = "S/D";
        String bandejasDia = "S/D";
        String kilosDia = "S/D";
        String bandejasTotal = "S/D";
        String kilosTotal = "S/D";

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        String dia = "" + day;
        int month = c.get(Calendar.MONTH) + 1;
        String mes = "" + month;
        int year = c.get(Calendar.YEAR);
        String año = "" + year;
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        String hora = hour + ":" + min;

        try {
            Connection con = helperSQLServer.CONN();
            if (con == null) {
                return bandejasDia + "-" + kilosDia;
            } else {
                //Consulta SQL
                String query = "Select Nombre, Apellido, PesoNeto, Cantidad from VistaConsultaDia where RutTrabajador = '" + rut + "' and ID_Map =" + mapeo + " and Anio = '" + año + "' and Mes = '" + mes + "' and Dia = '" + dia + "' ";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    bandejasDia = rs.getString("Cantidad");
                    kilosDia = rs.getString("PesoNeto");
                }
            }
        } catch (Exception ex) {
            Log.w(TAG, "...Error al seleccionar Resumen del servidor: " + ex.getMessage());
            return bandejasDia + "-" + kilosDia;
        }

        return bandejasDia + "-" + kilosDia;
    }

    public String getServerDate() {
        String fecha = "S/D";
        try {
            Connection con = helperSQLServer.CONN();
            if (con == null) {
                return fecha;
            } else {
                //Consulta SQL
                String query = "Select GETDATE() as fecha";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    fecha = new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("fecha"));
                }
            }
        } catch (Exception ex) {
            Log.w(TAG, "...Error al traer Fecha del servidor: " + ex.getMessage());
            return fecha;
        }
        return fecha;
    }

    public String getServerTime() {
        String fecha = "S/D";
        try {
            Connection con = helperSQLServer.CONN();
            if (con == null) {
                return fecha;
            } else {
                //Consulta SQL
                String query = "Select GETDATE() as fecha";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    fecha = new SimpleDateFormat("HH:mm").format(rs.getTime("fecha"));
                }
            }
        } catch (Exception ex) {
            Log.w(TAG, "...Error al traer Fecha del servidor: " + ex.getMessage());
            return fecha;
        }
        return fecha;
    }
}
