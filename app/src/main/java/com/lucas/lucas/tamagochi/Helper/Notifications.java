package com.lucas.lucas.tamagochi.Helper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import com.lucas.lucas.tamagochi.MainActivity;
import com.lucas.lucas.tamagochi.R;

/**
 * Created by LUCAS on 19/06/2017.
 */

public class Notifications{
    private Context notificationContext;

    public Notifications(Context context){

        notificationContext = context;
    }

    public void foodNotifications(int food){
        switch (food){
            case 15:
                notificationBuilder("Your Tamagochi needs food", "Goooo!!! I'm so hungry!!!" );
                break;
            case 25:
                //notificationBuilder("Your Tamagochi needs food",);
                break;
            case 35:
                //notificationBuilder("Your Tamagochi needs food",);
                break;
            case 50:
                //notificationBuilder("Your Tamagochi needs food",);
                break;
            case 70:
                //notificationBuilder("Your Tamagochi needs food",);
                break;
            case 85:
                //notificationBuilder("Your Tamagochi needs food",);
                break;
        }
    }

    public void lifeNotifications(int life){
        switch (life){
            case 15:
                notificationBuilder("Your Tamagochi needs life", "I'm with 15");
                break;
            case 25:
                notificationBuilder("Your Tamagochi needs life", "I'm with 25");
                break;
            case 35:
                notificationBuilder("Your Tamagochi needs life", "I'm with 35");
                break;
            case 50:
                notificationBuilder("Your Tamagochi needs life", "I'm with 50");
                break;
            case 70:
                notificationBuilder("Your Tamagochi needs life", "I'm with 70");
                break;
            case 85:
                notificationBuilder("Your Tamagochi needs life", "I'm with 85");
                break;
        }
    }


    private void notificationBuilder(String title, String text){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(notificationContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true);
        Intent intent = new Intent(notificationContext, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(notificationContext,0,intent,0);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) notificationContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
