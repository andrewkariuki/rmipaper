package com.example.rmipaper;

import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;


import java.io.IOException;

import lipermi.handler.CallHandler;
import lipermi.net.Client;

public class Connection extends AsyncTask<Void, Void, MainActivity> {

    @Override
    protected MainActivity doInBackground(Void... voids) {
        Looper.prepare();
        try {
            CallHandler callHandler = new CallHandler();
            String serverIP = "192.168.0.12";
            Client client = new Client(serverIP, 1099, callHandler);
            TestService testService = (TestService) client.getGlobal(TestService.class);
            String msg = testService.getResponse("qwe");
            //Toast.makeText(MainActivity.this, testService.getResponse("abc"), Toast.LENGTH_SHORT).show();
            Log.d("SERVER RESPONSE: ", msg);

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Looper.loop();

        return null;
    }
}
