package cl.lillo.produccionmanzanos.Modelo;

/**
 * Created by Usuario on 30/12/2016.
 */

public class Producto {
    private static Producto instance;

    private String ID_Producto;
    private String Nombre;
    private String TipoEnvase;
    private float KilosNetoEnvase;

    public Producto() {
    }

    public String getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(String ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public float getKilosNetoEnvase() {
        return KilosNetoEnvase;
    }

    public void setKilosNetoEnvase(float kilosNetoEnvase) {
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

    //auto-instancia (creo)
    public static synchronized Producto getInstance() {
        if (instance == null) {
            instance = new Producto();
        }
        return instance;
    }
}
