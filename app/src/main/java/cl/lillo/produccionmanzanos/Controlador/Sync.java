package cl.lillo.produccionmanzanos.Controlador;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Usuario on 31/08/2016.
 */
public class Sync {
    private static final String TAG = "Sync";

    private GestionPesaje gestionPesaje;
    private GestionTablaVista gestionTablaVista;
    private GestionTara gestionTara;
    private GestionTrabajador gestionTrabajador;

    private String sync(boolean syncCompleta, Context context) {
        try {
            if (syncCompleta) {
                //instancia
                gestionPesaje = new GestionPesaje(context);
                gestionTablaVista = new GestionTablaVista(context);
                gestionTara = new GestionTara(context);
                gestionTrabajador = new GestionTrabajador(context);
                //sync todas las tablas
                gestionTablaVista.selectServerInsertLocal();
                gestionTara.selectServerInsertLocal();
                gestionTrabajador.selectServerInsertLocal();
                //sync pesajes
                //if (gestionPesaje.selectLocalInsertServer()) {
                //gestionPesaje.deleteLocalSync();

                //respaldo
                //gestionPesaje.insertServerTEST();
                //}
                return gestionPesaje.selectLocalInsertServer();

            } else {
                //instancia
                gestionPesaje = new GestionPesaje(context);
                //sync pesaje
                //if (gestionPesaje.selectLocalInsertServer()) {
                //gestionPesaje.deleteLocalSync();
                //}
                return gestionPesaje.selectLocalInsertServer();
            }
        } catch (Exception ex) {
            Log.w(TAG, "...Error al sincronizar: " + ex.getMessage());
            return "Error al intentar sincronizar";
        }
    }

    public boolean eventoSyncAll(Context context, boolean syncCompleta) {
        //llamar servicio que sincroniza "bajo cuerda"
        if (conectado(context)) {
            ProgressDialog progress = new ProgressDialog(context);
            progress.setMessage("Sincronizando, por favor espere...");
            new ServicioCompleto(progress, syncCompleta, context).execute();
            return true;
        } else {
            Toast.makeText(context, "Atención! No hay conexión de Red Local", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private class ServicioCompleto extends AsyncTask<String, Void, String> {

        private String msj;
        private boolean syncCompleta;
        private Context context;
        ProgressDialog progress;

        ServicioCompleto(ProgressDialog progress, boolean syncCompleta, Context context) {
            this.progress = progress;
            this.syncCompleta = syncCompleta;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {
            msj = sync(syncCompleta, context);
            return msj;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progress.dismiss();
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean eventoSyncPesaje(Context context, boolean syncCompleta) {
        //llamar servicio que sincroniza "bajo cuerda"
        if (conectado(context)) {
            ProgressDialog progress = new ProgressDialog(context);
            progress.setMessage("Sincronizando, por favor espere...");
            new ServicioPesaje(progress, syncCompleta, context).execute();
            return true;
        } else {
            //Toast.makeText(view.getContext(), "Atención! No hay conexión a Internet", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private class ServicioPesaje extends AsyncTask<String, Void, String> {

        private String msj;
        private boolean syncCompleta;
        private Context context;
        ProgressDialog progress;


        ServicioPesaje(ProgressDialog progress, boolean syncCompleta, Context context) {
            this.progress = progress;
            this.syncCompleta = syncCompleta;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {
            msj = sync(syncCompleta, context);
            return msj;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progress.dismiss();
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            System.out.println("------------------->SYNC PESAJE:" + result);
        }
    }

    public static boolean conectado(Context context) {
        boolean connected = false;
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        for (NetworkInfo red : redes) {
            // Si alguna red tiene conexión, se devuelve true
            if (red.getState() == NetworkInfo.State.CONNECTED) {
                if (red.getExtraInfo().contains("W_Fosforos"))
                    connected = true;
                if (red.getExtraInfo().contains("W_Alamo"))
                    connected = true;
                if (red.getExtraInfo().contains("Gimnasio"))
                    connected = true;
                if (red.getExtraInfo().contains("Arandanos"))
                    connected = true;
                if (red.getExtraInfo().contains("W_Temsa"))
                    connected = true;
                if (red.getExtraInfo().contains("Fosforos"))
                    connected = true;
                //MOMENTANO POR DESARROLLO
                //connected = true;
            }
        }
        return connected;
    }
}
