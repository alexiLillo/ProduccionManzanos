package cl.lillo.produccionmanzanos.Modelo;

/**
 * Created by Usuario on 31/08/2016.
 */
public class Pesaje {
    private static Pesaje instance;

    //datos
    private String Producto;
    private String QRenvase;
    private String Cuadrilla;
    private String RutTrabajador;
    private String RutPesador;
    private String Fundo;
    private String Potrero;
    private String Sector;
    private String Variedad;
    private String Clase;
    private String Cuartel;
    private String FechaHora;       /* yyyy/MM/dd hh:mm:ss */
    private double PesoNeto;
    private double Tara;
    private String Formato;
    private double TotalCantidad;   /* 1 */
    private double Factor;          /* 1 */
    private double Cantidad;        /* 1 */
    private String Lectura_SVAL;    /* no or null */
    private int ID_Map;             /* last */
    private String TipoRegistro;
    private String FechaHoraModificacion;
    private String UsuarioModificaion;

    //constructor
    public Pesaje() {
    }

    //getter setter


    public String getCuadrilla() {
        return Cuadrilla;
    }

    public void setCuadrilla(String cuadrilla) {
        Cuadrilla = cuadrilla;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double cantidad) {
        Cantidad = cantidad;
    }

    public String getCuartel() {
        return Cuartel;
    }

    public void setCuartel(String cuartel) {
        Cuartel = cuartel;
    }

    public double getFactor() {
        return Factor;
    }

    public void setFactor(double factor) {
        Factor = factor;
    }

    public String getFechaHora() {
        return FechaHora;
    }

    public void setFechaHora(String fechaHora) {
        FechaHora = fechaHora;
    }

    public String getFormato() {
        return Formato;
    }

    public void setFormato(String formato) {
        Formato = formato;
    }

    public String getFundo() {
        return Fundo;
    }

    public void setFundo(String fundo) {
        Fundo = fundo;
    }

    public int getID_Map() {
        return ID_Map;
    }

    public void setID_Map(int ID_Map) {
        this.ID_Map = ID_Map;
    }

    public String getLectura_SVAL() {
        return Lectura_SVAL;
    }

    public void setLectura_SVAL(String lectura_SVAL) {
        Lectura_SVAL = lectura_SVAL;
    }

    public double getPesoNeto() {
        return PesoNeto;
    }

    public void setPesoNeto(double pesoNeto) {
        PesoNeto = pesoNeto;
    }

    public String getPotrero() {
        return Potrero;
    }

    public void setPotrero(String potrero) {
        Potrero = potrero;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        Producto = producto;
    }

    public String getQRenvase() {
        return QRenvase;
    }

    public void setQRenvase(String QRenvase) {
        this.QRenvase = QRenvase;
    }

    public String getRutPesador() {
        return RutPesador;
    }

    public void setRutPesador(String rutPesador) {
        RutPesador = rutPesador;
    }

    public String getRutTrabajador() {
        return RutTrabajador;
    }

    public void setRutTrabajador(String rutTrabajador) {
        RutTrabajador = rutTrabajador;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String sector) {
        Sector = sector;
    }

    public double getTara() {
        return Tara;
    }

    public void setTara(double tara) {
        Tara = tara;
    }

    public double getTotalCantidad() {
        return TotalCantidad;
    }

    public void setTotalCantidad(double totalCantidad) {
        TotalCantidad = totalCantidad;
    }

    public String getVariedad() {
        return Variedad;
    }

    public void setVariedad(String variedad) {
        Variedad = variedad;
    }

    public String getClase() {
        return Clase;
    }

    public void setClase(String clase) {
        Clase = clase;
    }

    public String getTipoRegistro() {
        return TipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        TipoRegistro = tipoRegistro;
    }

    public String getUsuarioModificaion() {
        return UsuarioModificaion;
    }

    public void setUsuarioModificaion(String usuarioModificaion) {
        UsuarioModificaion = usuarioModificaion;
    }

    public String getFechaHoraModificacion() {
        return FechaHoraModificacion;
    }

    public void setFechaHoraModificacion(String fechaHoraModificacion) {
        FechaHoraModificacion = fechaHoraModificacion;
    }

    //auto-instancia (creo)
    public static synchronized Pesaje getInstance() {
        if (instance == null) {
            instance = new Pesaje();
        }
        return instance;
    }
}
