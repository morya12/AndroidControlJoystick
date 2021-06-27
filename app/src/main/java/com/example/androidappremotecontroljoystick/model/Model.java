package com.example.androidappremotecontroljoystick.model;

import android.util.Log;
import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model {
   private Socket fg;
   private PrintWriter out;
   private ExecutorService executor  = Executors.newSingleThreadExecutor();

    public void connect(String ip, int port) {
        new Thread(() -> {
            try {
                Socket fg = new Socket(ip, port);
                out = new PrintWriter(fg.getOutputStream(), true);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }).start();
    }

    public void sendData(String attribute, float value) {
        Runnable runnable = () -> {
            out.print("set /controls/" + attribute + value + "\r\n");
            out.flush();
        };
        executor.submit(runnable);
    }


    public void setThrottle(float throttle) {
        sendData("engines/current-engine/throttle ", throttle);
    }

    public void setAileron(float aileron) {
        sendData("flight/aileron ", aileron);
    }

    public void setElevator(float elevator) {
        sendData("flight/elevator ", elevator);
    }

    public void setRudder(float rudder) {
        sendData("flight/rudder ", rudder);
    }

}
