package com.lucas.lucas.tamagochi.Services;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by LUCAS on 16/06/2017.
 */

public class Broadcast extends BroadcastReceiver {
    Intent i;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"BROADCAST",Toast.LENGTH_SHORT).show();
        if(!isMyServiceRunning(context,Service.class)){
            i = new Intent(context, Service.class);
            context.startService(i);
        }
    }

    private boolean isMyServiceRunning(Context c, Class<?> serviceClass){
        ActivityManager manager = (ActivityManager) c.getSystemService(ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
        {
            if (serviceClass.getName().equals(service.service.getClassName())) return true;
        }
        return false;
    }
}
