package com.example.androidappremotecontroljoystick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.androidappremotecontroljoystick.model.Model;
import com.example.androidappremotecontroljoystick.viewmodel.ViewModel;
import com.example.androidappremotecontroljoystick.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private EditText  eIP;
    private EditText ePort;
    private Button eConnect;
    SeekBar rudder;
    private String asafIP = "10.0.0.16";
    private String morIP = "192.168.1.35";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //  ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ViewModel  vm = new ViewModel(new Model());

        eIP = findViewById(R.id.etIP);
        ePort = findViewById(R.id.etPort);
        eConnect = findViewById(R.id.btnConnect);
        rudder = findViewById(R.id.rudder);
        eConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IP = eIP.getText().toString();
                String port = ePort.getText().toString();// to int
                if (IP.isEmpty() || port.isEmpty()){
                    Toast.makeText(MainActivity.this, "please enter valid IP and Port", Toast.LENGTH_SHORT).show();
                } else {
                    vm.connect(morIP, Integer.parseInt(port));
                    eConnect.setBackgroundColor(Color.GRAY);
                    eConnect.setClickable(false);
                }
            }
        });
        rudder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float rudderVal = ((float) progress - 50) / 50;
                vm.VM_setRudder(rudderVal);
                Log.d(null, "Rudder: " + rudderVal);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
    }
}