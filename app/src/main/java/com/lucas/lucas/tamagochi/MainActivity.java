package com.lucas.lucas.tamagochi;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lucas.lucas.tamagochi.Tamagochi.Tamagochi;
import com.lucas.lucas.tamagochi.Util.Util;

import org.w3c.dom.Text;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
{
    private Tamagochi tamagochi;
    private Util util;
    private String name;

    private Button lifeBtn;
    private Button foodBtn;
    private TextView nameTmagochi;
    private TextView lifeDisplay;
    private TextView foodDisplay;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lifeBtn = (Button)findViewById(R.id.btnLife);
        foodBtn = (Button)findViewById(R.id.btnFood);
        nameTmagochi = (TextView)findViewById(R.id.nameTamagochi);
        lifeDisplay = (TextView)findViewById(R.id.lifeDisplay);
        foodDisplay = (TextView)findViewById(R.id.foodDisplay);

        tamagochi = Tamagochi.getInstance();
        util = new Util();

        name = tamagochi.getName();

        if(tamagochi.isDead()){
                //util.AlertMessage(getApplicationContext());
            tamagochi.setDead(false);
            tamagochi.setLife(100);
            tamagochi.setFood(100);
            tamagochi.setName("Lucas");
            nameTmagochi.setText(tamagochi.getName());
            lifeDisplay.setText(Integer.toString(tamagochi.getLife()));
            foodDisplay.setText(Integer.toString(tamagochi.getFood()));

            Log.d("Tamagochi",tamagochi.getName());
        }

        lifeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(getApplicationContext(),Integer.toString(tamagochi.getFood()),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
