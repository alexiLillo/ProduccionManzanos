package cl.lillo.produccionmanzanos.Modelo;

/**
 * Created by Usuario on 30/12/2016.
 */

public class Producto {

    private String ID_Producto;
    private String Nombre;
    private String TipoEnvase;
    private double KilosNetoEnvase;

    public Producto() {
    }

    public String getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(String ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public double getKilosNetoEnvase() {
        return KilosNetoEnvase;
    }

    public void setKilosNetoEnvase(double kilosNetoEnvase) {
        KilosNetoEnvase = kilosNetoEnvase;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTipoEnvase() {
        return TipoEnvase;
    }

    public void setTipoEnvase(String tipoEnvase) {
        TipoEnvase = tipoEnvase;
    }
}
