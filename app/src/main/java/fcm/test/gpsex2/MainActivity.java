package fcm.test.gpsex2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    ToggleButton tb;
    private Intent serviceIntent;
    private LocationVO locationVO = new LocationVO();

    LocationManage locationManage = new LocationManage();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /////////////////////////////////////////////////////////////
        if (RealService.serviceIntent == null) {
            serviceIntent = new Intent(this, RealService.class);
            startService(serviceIntent);
        } else {
            serviceIntent = RealService.serviceIntent;//getInstance().getApplication();
            Toast.makeText(getApplicationContext(), "already", Toast.LENGTH_LONG).show();
        }

        ////////////////////////////////////////////////////////////////
        tv = (TextView) findViewById(R.id.textView2);
        tv.setText("위치정보 미수신중");
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManage.onLocation(lm);
        locationVO =locationManage.getVoData();
        Log.d("VoDataMain ", Double.toString(locationVO.getLongitude()));

    //    tv.setText("위치정보 : " + locationVO.getProvider() + "\n위도 : " + locationVO.getLongitude() + "\n경도 : " + locationVO.getLongitude()
   //             + "\n고도 : " + locationVO.getAltitude() + "\n정확도 : "  + locationVO.getAccuracy());



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceIntent!=null) {
            stopService(serviceIntent);
            serviceIntent = null;
        }
    }//서비스를 사용하기 위함
}
