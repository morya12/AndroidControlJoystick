package com.example.androidappremotecontroljoystick.viewmodel;

import android.util.Log;
import android.widget.SeekBar;

import androidx.databinding.Bindable;

import com.example.androidappremotecontroljoystick.model.Model;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private Model m;

    public ViewModel(Model model){
        this.m = model;
    }

    public void VM_setThrottle(float throttle) {
        m.setThrottle(throttle);
    }

    public void VM_setAileron(float aileron) {
        m.setAileron(aileron);
    }

    public void VM_setElevator(float elevator  ) {
        m.setElevator(elevator);
    }

    public void VM_setRudder(float rudder){
        this.m.setRudder(rudder);
    }

    public void connect(String ip, int port){
        m.connect(ip, port);
    }
}

