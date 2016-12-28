package cl.lillo.produccionmanzanos;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TABS
        Resources res = getResources();

        TabHost tabs = (TabHost) findViewById(R.id.tabHost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("mitab1");
        spec.setContent(R.id.linearLayout);
        spec.setIndicator("", res.getDrawable(R.drawable.bin));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("mitab2");
        spec.setContent(R.id.linearLayout2);
        spec.setIndicator("", res.getDrawable(R.drawable.card));
        tabs.addTab(spec);
        //deshabilitar un tab (tab de tarjetas)
        tabs.getTabWidget().getChildTabViewAt(1).setEnabled(false);

        spec = tabs.newTabSpec("mitab3");
        spec.setContent(R.id.linearLayout3);
        spec.setIndicator("", res.getDrawable(R.drawable.sync));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("mitab4");
        spec.setContent(R.id.linearLayout4);
        spec.setIndicator("", res.getDrawable(R.drawable.menu));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
        //fin tabs
    }
}
