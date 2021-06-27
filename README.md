# AndroidControlJoystick

This is Our Android App

in this app we can control remotely the flight simualtor - Flight Gear,Send flight data of the aircraft.

This app is written in Java.

![WhatsApp Image 2021-06-26 at 21 36 52](https://user-images.githubusercontent.com/73069286/123553357-64659a80-d783-11eb-840c-ff6d1790383f.jpeg)

Requirements
-------------------------------------------------------------------------------------------

-Android minimum SDK - API 16 Android 4.1 (Jelly Bean).

-Installed and running FlightGear

Technical Instructions: 
1. Download and install FlightGear (from https://www.flightgear.org/download)
2. Open FlightGear and go to settings, in additional Settings write the next line: 
--telnet=socket,in,10,127.0.0.1,7200,tcp
3. Download or Clone the app (using Git, or a zip archive to unzip)
4. Open your project in Android Studio
5. Select an android phone or AVD and press run
7. Press Fly! and in the app connect to the FlightGear's server (IP: IPv4 Adress on your computer port : 7200)

App running Instructions:
App runnung Instructions: 
---------------------------------------------------
1. Insert yout IP address and port 7200 in the suitable text boxes
2. press connent
3. start flying, move the bars from their places and move the joystick up and down, right and left


 <img width="707" alt="A" src="https://user-images.githubusercontent.com/73069286/123553418-b8707f00-d783-11eb-9c2a-bd4a77527594.png">


Project Structure
---------------------------------------------------

The project was written in MVVM architecture. Model, View and View Model. 
Model :
Responsible of sending data in order to create connecion and send aircraft data to FlightGear simulator.

ViewModel: 
Responsible of the connection between Model and View. 

View:
Is the Set of screens of the app. Includes MainActivity window and Joystick window.


# UML Chart:
![uml](https://user-images.githubusercontent.com/73069286/123553315-19e41e00-d783-11eb-9465-c0f408762b4c.png)


# Explanation Video:
Press [here](https://www.youtube.com/watch?v=E2tp2mWOwWQ) to watch the explanation video.

Authors
* Asaf Mor
* Mor Yaacov











