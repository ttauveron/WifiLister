package com.gnut3ll4.wifilister;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {


    private ListView listViewWifi;

    private HashMap<String, List<ScanResult>> mapScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewWifi = (ListView) this.findViewById(R.id.listView_wifi);

        mapScan = new HashMap<>();


        WifiManager wifiMan = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);

        ArrayList<ScanResult> scanResults = (ArrayList<ScanResult>) wifiMan.getScanResults();
        for (ScanResult scanResult : scanResults) {

            ArrayList<ScanResult> listScanResult;

            if((listScanResult = (ArrayList<ScanResult>) mapScan.get(scanResult.SSID))==null){
                listScanResult = new ArrayList<>();
                listScanResult.add(scanResult);
                mapScan.put(scanResult.SSID, listScanResult);
            } else {
                listScanResult.add(scanResult);
                mapScan.put(scanResult.SSID, listScanResult);
            }


        }


        ScanResultAdapter adapter = new ScanResultAdapter(this, R.layout.row_listview, scanResults);

        listViewWifi.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        listViewWifi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                Object o = listViewWifi.getItemAtPosition(position);
                ScanResult scanResult = (ScanResult) o;

                Toast.makeText(getBaseContext(), "Nombre : "+mapScan.get(scanResult.SSID).size(), Toast.LENGTH_SHORT).show();
            }
        });


//        WifiInfo wifiInf = wifiMan.getConnectionInfo();
//        String macAddr = wifiInf.getMacAddress();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
