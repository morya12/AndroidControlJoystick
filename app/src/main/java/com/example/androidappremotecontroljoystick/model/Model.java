package com.example.androidappremotecontroljoystick.model;

import android.util.Log;

import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Model {
    Socket fg;
    PrintWriter out;
    //  BlockingQueue<Runnable> dispatchQueue = new LinkedBlockingQueue<>();
    // Executor executor = Executors.newSingleThreadExecutor();
    ExecutorService executor  = Executors.newSingleThreadExecutor();
    //  boolean stop = false;
    // for disconnect https://www.javatpoint.com/java-executors-newsinglethreadexecutor-method

    public void connect(String ip, int port) {
        new Thread(() -> {
            try {
                //System.out.println("here with ip = " + ip + " and port = "+port);
                Socket fg = new Socket(ip, port);
                if(fg == null)
                    Log.d("myTag", "fg null");
                if(fg != null)
                    Log.d("myTag", "fg not null");
                Log.d("socket", "open");
                out = new PrintWriter(fg.getOutputStream(), true);
            } catch (IOException exception) {
                Log.d("myTag", "This is my message");
                exception.printStackTrace();
            }
        }).start();

    }

    public void sendData(String attribute, float value) {
        System.out.println("Runnable...");
        Runnable runnable = () -> {
            out.print("set /controls/" + attribute + value + "\r\n");
            out.flush();
        };
        System.out.println("Submit the task ");
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
