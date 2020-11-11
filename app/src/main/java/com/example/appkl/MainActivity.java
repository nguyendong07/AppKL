package com.example.appkl;
import androidx.appcompat.app.AppCompatActivity;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.widget.Button;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Toast;
import com.example.appkl.modelANN;
//import com.example.appkl.changeData;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;



import weka.gui.Main;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private  static final  String TAG = "MainActivity";
    private  SensorManager sensorManager;
    private Sensor sensor;
    private Sensor accelerometer;
    private ViewGroup ViewGroup;
    private View View;
    public float x,y,z;
    //File file_result = new File("C://Users//Admin//Desktop//Predict1.txt");
    TextView xValue;
    TextView yValue;
    TextView zValue;
    Button button;
    Context context;
   
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);

        try {
            modelANN.main();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onCreate: ");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        button = findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    System.out.println(x);
                    System.out.println(y);
                    System.out.println(z);
                    String data = "abc";
                    writeToFile(data, context);
            }
        });
        Log.d(TAG, "OnCreate : Register accelerometer Listener");

        };

    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("filename.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    public void onSensorChanged(SensorEvent sensorEvent) {
        //Log.d(TAG, "OnSensorChanged: X :" + sensorEvent.values[0] + "Y: " + sensorEvent.values[1] + "Z: "+ sensorEvent.values[2]);

        xValue.setText("xValue: "+ sensorEvent.values[0]);
        yValue.setText("yValue: "+ sensorEvent.values[1]);
        zValue.setText("zValue: "+ sensorEvent.values[2]);
        x = sensorEvent.values[0];
        y = sensorEvent.values[1];
        z = sensorEvent.values[2];
//
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}