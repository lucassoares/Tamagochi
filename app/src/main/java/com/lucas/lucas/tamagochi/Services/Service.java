package com.lucas.lucas.tamagochi.Services;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lucas.lucas.tamagochi.Helper.Notifications;
import com.lucas.lucas.tamagochi.Preferences.Preferences;

/**
 * Created by Lucas on 13/06/2017.
 */
public class Service extends android.app.Service implements Runnable, CountListener{
    private boolean active;

    private Preferences preferences;
    private Notifications notifications;
    private final LocalBinder connection = new LocalBinder();
    private int life;

    public class LocalBinder extends Binder
    {
        public CountListener getService() { return Service.this; }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return connection;
    }

    @Override
    public void onCreate() {
        Log.d("Script", "onCreate");
        super.onCreate();
        new Thread(this).start();
        active = true;
        preferences = new Preferences(this);
        life = preferences.getLifePreferences();
        notifications = new Notifications(getApplicationContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Script", "onStart");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void run(){
        while(active){
           //life = preferences.getLifePreferences();
            if(life > 0 ){
                life = preferences.getLifePreferences();
                life--;
                Log.d("coaaaaaaaaunt", Integer.toString(life));
                preferences.setLifePreferences(life);
                setInterval();
                notifications.lifeNotifications(life);
            }
        }
    }

    private void setInterval()
    {
        try { Thread.sleep(1000); }
        catch(InterruptedException e) { e.printStackTrace(); }
    }

    @Override
    public int getCount(){
        return life;
    }

}
