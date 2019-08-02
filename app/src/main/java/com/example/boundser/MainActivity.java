package com.example.boundser;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.boundser.MyService.MyLocalBinder;
public class MainActivity extends AppCompatActivity {

    MyService myservice;
    boolean isbound = false;
    public void showTime(View view)
    {
        String currenttime = myservice.getCurrentTime();
        TextView mytext = (TextView)findViewById(R.id.tex);
        mytext.setText(currenttime);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this,MyService.class);
        bindService(i,myconnection, Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection myconnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
           MyLocalBinder binder = (MyLocalBinder) service;
           myservice = binder.getService();
           isbound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isbound = false;
        }
    };
}
