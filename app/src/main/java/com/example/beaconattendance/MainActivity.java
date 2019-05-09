package com.example.beaconattendance;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView img0;

    int REQUEST_ENABLE_BT=1;
    BluetoothAdapter myBluetoothAdapter;

    Intent btEnablingIntent;
    int requestCodeForceEnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        btEnablingIntent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        requestCodeForceEnable=1;



        if (myBluetoothAdapter.isEnabled()){

            myBluetoothAdapter.disable();
        }
        if (myBluetoothAdapter==null){
            //Device does not support Bluetooth

            Toast.makeText(getApplicationContext(),"The device does not support Bluetooth",Toast.LENGTH_LONG).show();
        }

        else {
            if(!myBluetoothAdapter.isEnabled()){
                //Code for Bluetooth Enable
                startActivityForResult(btEnablingIntent,REQUEST_ENABLE_BT);

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==requestCodeForceEnable)
        {
            if (requestCode==RESULT_OK)
            {
                //Bluetooth is enabled
                Toast.makeText(getApplicationContext(),"Bluetooth is Enable ",Toast.LENGTH_LONG).show();
            }
        }
        else if (requestCode==RESULT_CANCELED)
        {
            //Bluetooth enabling is Cancelled
            Toast.makeText(getApplicationContext(),"Bluetooth Enabling Cancelled",Toast.LENGTH_LONG).show();

        }
    }
}






