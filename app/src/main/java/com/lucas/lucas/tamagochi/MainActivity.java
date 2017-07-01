package com.lucas.lucas.tamagochi;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lucas.lucas.tamagochi.Helper.Notifications;
import com.lucas.lucas.tamagochi.Preferences.Preferences;
import com.lucas.lucas.tamagochi.Services.CountListener;
import com.lucas.lucas.tamagochi.Services.Service;
import com.lucas.lucas.tamagochi.Tamagochi.Tamagochi;

public class MainActivity extends AppCompatActivity implements ServiceConnection
{
    private CountListener countListener;
    private final ServiceConnection connection = this;

    private Tamagochi tamagochi;
    private Preferences preferences;
    private Notifications notifications;


    private String name;

    private Button lifeBtn;
    private Button foodBtn;
    private TextView nameTmagochi;
    private TextView lifeDisplay;
    private TextView foodDisplay;
    private ImageView imageViewDog;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setThread();
        lifeBtn = (Button)findViewById(R.id.btnLife);
        foodBtn = (Button)findViewById(R.id.btnFood);
        nameTmagochi = (TextView)findViewById(R.id.nameTamagochi);
        lifeDisplay = (TextView)findViewById(R.id.lifeDisplay);
        foodDisplay = (TextView)findViewById(R.id.foodDisplay);
        imageViewDog = (ImageView)findViewById(R.id.imageView_dog);

        tamagochi = Tamagochi.getInstance();
        preferences = new Preferences(getApplicationContext());
        notifications = new Notifications(getApplicationContext());

        name = preferences.getNamePreferences();

        if(!isMyServiceRunning(Service.class) && name!= null){
            Intent intent = new Intent(getApplicationContext(),Service.class);
            bindService(intent,connection, Context.BIND_AUTO_CREATE);
            startService(intent);
        }
        if(name == null){
            alertMessageNewUser();
        }

        tamagochi.setLife(preferences.getLifePreferences());
        tamagochi.setFood(preferences.getFoodPreferences());

        nameTmagochi.setText(preferences.getNamePreferences());
        lifeDisplay.setText(Integer.toString(tamagochi.getLife()));
        foodDisplay.setText(Integer.toString(tamagochi.getFood()));

        lifeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(preferences.getLifePreferences() < 100){
                    int life = preferences.getLifePreferences();
                    life += 10;
                    preferences.setLifePreferences(life);
                    //updateValues();
                }
            }
        });

        foodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(preferences.getFoodPreferences() < 100){
                    int food = preferences.getFoodPreferences();
                    food += 10;
                    preferences.setFoodPreferences(food);
                    //updateValues();
                }
            }
        });
    }

    private void updateValues(){
        lifeDisplay.setText(Integer.toString(preferences.getLifePreferences()));
        foodDisplay.setText(Integer.toString(preferences.getFoodPreferences()));
    }

    private void alertMessageNewUser(){
        final AlertDialog alert;
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        final EditText inputUserName = new EditText(MainActivity.this);

        inputUserName.setPadding(16,16,16,16);
        inputUserName.setHint("Your user name here");

        alertDialog.setTitle("New User");
        alertDialog.setMessage("Create a new user");
        alertDialog.setView(inputUserName);

        alertDialog.setPositiveButton("Create", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String userName = inputUserName.getText().toString();
                createUser(userName);
                Intent intent = new Intent(getApplicationContext(),Service.class);
                startService(intent);
                Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        alert = alertDialog.create();
        alert.show();
    }

    private void createUser(String userName){
        tamagochi.setLife(100);
        tamagochi.setFood(100);
        tamagochi.setName(userName);
        preferences.saveUserPreferences(tamagochi.getName(),tamagochi.getLife(),tamagochi.getFood());
        nameTmagochi.setText(preferences.getNamePreferences());
        updateValues();
    }

    private boolean isMyServiceRunning(Class<?> serviceClass){
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
        {
            if (serviceClass.getName().equals(service.service.getClassName())) return true;
        }
        return false;
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.d("BIND SERVICE SAMPLE", "SERVICE CONNECTED");
        Service.LocalBinder binder = (Service.LocalBinder) iBinder;
        countListener = binder.getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.d("BIND SERVICE SAMPLE", "SERVICE DISCONNECTED");
        //countListener = null;
    }

    private void UnbindConnection()
    {
        if(countListener != null)
        {
            Log.d("BIND SERVICE SAMPLE", "STOP BIND SERVICE");
            countListener = null;
            unbindService(connection);
        } else Log.d("BIND SERVICE SAMPLE", "THE SERVICE ISN'T CONNECTED");
    }

    public void setThread(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (!isInterrupted()){
                    if(preferences.getLifePreferences() == 0 || preferences.getFoodPreferences() == 0){
                        imageViewDog.setImageResource(R.drawable.dogdead);
                    }
                    else if(preferences.getLifePreferences() > 0 || preferences.getFoodPreferences() > 0){
                        imageViewDog.setImageResource(R.drawable.dog);
                    }
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lifeDisplay.setText(Integer.toString(preferences.getLifePreferences()));
                                foodDisplay.setText(Integer.toString(preferences.getFoodPreferences()));
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
