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
//import com.example.androidappremotecontroljoystick.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Joystick.JoystickListener {
    private EditText  eIP;
    private EditText ePort;
    private Button eConnect;
    SeekBar rudder;
    ViewModel  vm;
    private String asafIP = "10.0.0.16";
    private String morIP = "192.168.1.35";
    // disconnect

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.vm = new ViewModel(new Model());

        setContentView(R.layout.activity_main);
     //   ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //  ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Joystick joystick = (Joystick)findViewById(R.id.joystick);

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
                    vm.connect(asafIP, Integer.parseInt(port));
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
                Log.d(null, "Rudder: " + rudderVal);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        // create the throttle seekbar
        SeekBar throttle = (SeekBar)findViewById(R.id.throttle);
        throttle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float throttleVal = (float) progress / 100;
                vm.VM_setThrottle(throttleVal);
                Log.d(null, "throttleVal: " + throttleVal);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
    @Override
    public void onJoystickMoved(float x, float y) {
        Log.d("Main Method", "X percent: " + x + " Y percent: " + y);
       // float elevator = (float) ((float) (x - 0.5) * -0.2);
      //  float aileron = (float) ((float) (y - 0.5) * 0.2);
        //Log.d(null, "elevator: " + elevator);
       // Log.d(null, "aileron: " + aileron);
        if(vm != null) {
            this.vm.VM_setElevator(x);
            this.vm.VM_setAileron(y);
        }
    }

}