package cl.lillo.produccionmanzanos.Modelo;

/**
 * Created by Usuario on 31/08/2016.
 */
public class TablaVista {
    private static TablaVista instance;

    //Datos
    private String ID_Fundo;
    private String nombreFundo;
    private String ID_Potrero;
    private String nombrePotrero;
    private String ID_Sector;
    private String nombreSector;
    private String ID_Variedad;
    private String nombreVariedad;
    private String ID_Cuartel;
    private String nombreCuartel;
    private int ID_Mapeo;
    private String ID_Producto;
    private String TipoEnvase;
    private float KilosNetoEnvase;

    //constructor
    public TablaVista() {
    }

    //getter setter


    public float getKilosNetoEnvase() {
        return KilosNetoEnvase;
    }

    public void setKilosNetoEnvase(float kilosNetoEnvase) {
        KilosNetoEnvase = kilosNetoEnvase;
    }

    public String getTipoEnvase() {
        return TipoEnvase;
    }

    public void setTipoEnvase(String tipoEnvase) {
        TipoEnvase = tipoEnvase;
    }

    public String getID_Cuartel() {
        return ID_Cuartel;
    }

    public void setID_Cuartel(String ID_Cuartel) {
        this.ID_Cuartel = ID_Cuartel;
    }

    public String getID_Fundo() {
        return ID_Fundo;
    }

    public void setID_Fundo(String ID_Fundo) {
        this.ID_Fundo = ID_Fundo;
    }

    public int getID_Mapeo() {
        return ID_Mapeo;
    }

    public void setID_Mapeo(int ID_Mapeo) {
        this.ID_Mapeo = ID_Mapeo;
    }

    public String getID_Potrero() {
        return ID_Potrero;
    }

    public void setID_Potrero(String ID_Potrero) {
        this.ID_Potrero = ID_Potrero;
    }

    public String getID_Sector() {
        return ID_Sector;
    }

    public void setID_Sector(String ID_Sector) {
        this.ID_Sector = ID_Sector;
    }

    public String getID_Variedad() {
        return ID_Variedad;
    }

    public void setID_Variedad(String ID_Variedad) {
        this.ID_Variedad = ID_Variedad;
    }

    public String getNombreCuartel() {
        return nombreCuartel;
    }

    public void setNombreCuartel(String nombreCuartel) {
        this.nombreCuartel = nombreCuartel;
    }

    public String getNombreFundo() {
        return nombreFundo;
    }

    public void setNombreFundo(String nombreFundo) {
        this.nombreFundo = nombreFundo;
    }

    public String getNombrePotrero() {
        return nombrePotrero;
    }

    public void setNombrePotrero(String nombrePotrero) {
        this.nombrePotrero = nombrePotrero;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public void setNombreSector(String nombreSector) {
        this.nombreSector = nombreSector;
    }

    public String getNombreVariedad() {
        return nombreVariedad;
    }

    public void setNombreVariedad(String nombreVariedad) {
        this.nombreVariedad = nombreVariedad;
    }

    public String getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(String ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    //auto-instancia (creo)
    public static synchronized TablaVista getInstance() {
        if (instance == null) {
            instance = new TablaVista();
        }
        return instance;
    }
}
