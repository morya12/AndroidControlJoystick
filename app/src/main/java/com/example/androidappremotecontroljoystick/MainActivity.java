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
import com.example.androidappremotecontroljoystick.view.Joystick;
import com.example.androidappremotecontroljoystick.viewmodel.ViewModel;
import com.example.androidappremotecontroljoystick.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Joystick.JoystickListener {

    private ViewModel  vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.vm = new ViewModel(new Model());
        setContentView(R.layout.activity_main);
        Joystick joystick = (Joystick)findViewById(R.id.joystick);
        EditText  eIP = findViewById(R.id.etIP);
        EditText ePort = findViewById(R.id.etPort);
        Button eConnect = findViewById(R.id.btnConnect);
        SeekBar rudder = findViewById(R.id.rudder);
        SeekBar throttle = findViewById(R.id.throttle);

        eConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IP = eIP.getText().toString();
                String port = ePort.getText().toString();
                if (IP.isEmpty() || port.isEmpty()){
                    Toast.makeText(MainActivity.this, "please enter valid IP and Port", Toast.LENGTH_SHORT).show();
                } else {
                    vm.connect(IP, Integer.parseInt(port));
                    eConnect.setBackgroundColor(Color.GRAY);
                    eConnect.setText("Connected");
                    eConnect.setClickable(false);
                }
            }
        });
        rudder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float rudderVal = ((float) progress - 50) / 50;
                vm.VM_setRudder(rudderVal);
               // Log.d(null, "Rudder: " + rudderVal);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        throttle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float throttleVal = (float) progress / 100;
                vm.VM_setThrottle(throttleVal);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    @Override
    public void onJoystickMoved(float x, float y) {
        if(vm != null) {
            this.vm.VM_setElevator(y);
            this.vm.VM_setAileron(x);
        }
    }

}