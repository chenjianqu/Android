package com.qu.jian.btremote.Aty;


import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qu.jian.btremote.R;

import static android.content.Context.LOCATION_SERVICE;
import static android.content.Context.SENSOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_sensor extends Fragment implements LocationListener, SensorEventListener {

    private TextView textView_gra, textView_ac, textView_lac, textView_gy, textView_geo, textView_ori, textView_li, textView_pre, textView_pro, textView_tem, textView_gps;
    private SensorManager sensorManager;
    private LocationManager locationManager;
    private Sensor sensor_gra, sensor_ac, sensor_lac, sensor_gy, sensor_geo, sensor_ori, sensor_li, sensor_pre, sensor_pro, sensor_tem;
    private LinearLayout layout_gra, layout_ac, layout_lac, layout_gy, layout_geo, layout_ori, layout_li, layout_pre, layout_pro, layout_tem, layout_gps;


    public Fragment_sensor() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor, container, false);
        textView_ac = (TextView) view.findViewById(R.id.textView_sensor_value_ac);
        textView_gra = (TextView) view.findViewById(R.id.textView_sensor_value_gra);
        textView_lac = (TextView) view.findViewById(R.id.textView_sensor_value_lac);
        textView_gy = (TextView) view.findViewById(R.id.textView_sensor_value_gy);
        textView_geo = (TextView) view.findViewById(R.id.textView_sensor_value_geo);
        textView_ori = (TextView) view.findViewById(R.id.textView_sensor_value_ori);
        textView_li = (TextView) view.findViewById(R.id.textView_sensor_value_li);
        textView_pre = (TextView) view.findViewById(R.id.textView_sensor_value_pre);
        textView_pro = (TextView) view.findViewById(R.id.textView_sensor_value_pro);
        textView_tem = (TextView) view.findViewById(R.id.textView_sensor_value_tem);
        textView_gps = (TextView) view.findViewById(R.id.textView_sensor_value_gps);
        layout_ac = (LinearLayout) view.findViewById(R.id.layout_sensor_ac);
        layout_gra = (LinearLayout) view.findViewById(R.id.layout_sensor_gra);
        layout_lac = (LinearLayout) view.findViewById(R.id.layout_sensor_lac);
        layout_gy = (LinearLayout) view.findViewById(R.id.layout_sensor_gy);
        layout_geo = (LinearLayout) view.findViewById(R.id.layout_sensor_geo);
        layout_ori = (LinearLayout) view.findViewById(R.id.layout_sensor_ori);
        layout_li = (LinearLayout) view.findViewById(R.id.layout_sensor_li);
        layout_pre = (LinearLayout) view.findViewById(R.id.layout_sensor_pre);
        layout_pro = (LinearLayout) view.findViewById(R.id.layout_sensor_pro);
        layout_tem = (LinearLayout) view.findViewById(R.id.layout_sensor_tem);
        layout_gps = (LinearLayout) view.findViewById(R.id.layout_sensor_gps);
        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        sensor_gra = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensor_ac = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor_lac = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensor_gy = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensor_geo = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensor_ori = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensor_li = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensor_pre = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sensor_pro = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensor_tem = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (sensor_ac == null) layout_ac.setVisibility(View.GONE);
        if (sensor_gra == null) layout_gra.setVisibility(View.GONE);
        if (sensor_lac == null) layout_lac.setVisibility(View.GONE);
        if (sensor_gy == null) layout_gy.setVisibility(View.GONE);
        if (sensor_geo == null) layout_geo.setVisibility(View.GONE);
        if (sensor_ori == null) layout_ori.setVisibility(View.GONE);
        if (sensor_li == null) layout_li.setVisibility(View.GONE);
        if (sensor_pre == null) layout_pre.setVisibility(View.GONE);
        if (sensor_pro == null) layout_pro.setVisibility(View.GONE);
        if (sensor_tem == null) layout_tem.setVisibility(View.GONE);
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        String best = locationManager.getBestProvider(new Criteria(), true);
        if (best != null) {
            textView_gps.setText("定位信息获取中...");
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(),"未获得定位权限",Toast.LENGTH_LONG).show();
                return null;
            }
            locationManager.requestLocationUpdates(best, 3000, (float) 3.0,Fragment_sensor.this);
        }else textView_gps.setText("未获得定位权限");

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        if(sensor_ac!=null)sensorManager.registerListener((SensorEventListener) this,sensor_ac,SensorManager.SENSOR_DELAY_NORMAL);
        if(sensor_gra!=null)sensorManager.registerListener((SensorEventListener) this,sensor_gra,SensorManager.SENSOR_DELAY_NORMAL);
        if(sensor_lac!=null)sensorManager.registerListener((SensorEventListener) this,sensor_lac,SensorManager.SENSOR_DELAY_NORMAL);
        if(sensor_gy!=null)sensorManager.registerListener((SensorEventListener) this,sensor_gy,SensorManager.SENSOR_DELAY_NORMAL);
        if(sensor_geo!=null)sensorManager.registerListener((SensorEventListener) this,sensor_geo,SensorManager.SENSOR_DELAY_NORMAL);
        if(sensor_ori!=null)sensorManager.registerListener((SensorEventListener) this,sensor_ori,SensorManager.SENSOR_DELAY_NORMAL);
        if(sensor_li!=null)sensorManager.registerListener((SensorEventListener) this,sensor_li,SensorManager.SENSOR_DELAY_NORMAL);
        if(sensor_pre!=null)sensorManager.registerListener((SensorEventListener) this,sensor_pre,SensorManager.SENSOR_DELAY_NORMAL);
        if(sensor_pro!=null)sensorManager.registerListener((SensorEventListener) this,sensor_pro,SensorManager.SENSOR_DELAY_NORMAL);
        if(sensor_tem!=null)sensorManager.registerListener((SensorEventListener) this,sensor_tem,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause(){//当切换到其他界面时
        super.onPause();
        sensorManager.unregisterListener((SensorEventListener) this);//取消监听对象的注册
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                textView_ac.setText(String.format("%1.2f m/s²\n%1.2f m/s²\n%1.2f m/s²",event.values[0],event.values[1],event.values[2]));
                break;
            case Sensor.TYPE_LIGHT:
                textView_li.setText(String.format("%1.2f lx",event.values[0]));
                break;
            case Sensor.TYPE_PRESSURE:
                textView_pre.setText(String.format("%1.2f hPa",event.values[0]));
                break;
            case Sensor.TYPE_PROXIMITY:
                textView_pro.setText(String.format("%1.2f cm",event.values[0]));
                break;
            case Sensor.TYPE_GYROSCOPE:
                textView_gy.setText(String.format("%1.2f r/s\n%1.2f r/s\n%1.2f r/s",event.values[0],event.values[1],event.values[2]));
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                textView_geo.setText(String.format("%1.2f μT\n%1.2f μT\n%1.2f μT",event.values[0],event.values[1],event.values[2]));
                break;
            case Sensor.TYPE_GRAVITY:
                textView_gra.setText(String.format("%1.2f m/s²\n%1.2f m/s²\n%1.2f m/s²",event.values[0],event.values[1],event.values[2]));
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                textView_lac.setText(String.format("%1.2f m/s²\n%1.2f m/s²\n%1.2f m/s²",event.values[0],event.values[1],event.values[2]));
                break;
            case Sensor.TYPE_ORIENTATION:
                textView_ori.setText(String.format("%1.2f °\n%1.2f °\n%1.2f °",event.values[1],event.values[2],event.values[0]));
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                textView_tem.setText(String.format("%1.2f °",event.values[0]));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onLocationChanged(Location location) {
        String str = location.getProvider();
        str += String.format("\n%1.5f    °  \n%1.5f    °  \n%1.2f   m  \n%1.2f m/s ", location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getSpeed());
        textView_gps.setText(str);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
