package com.lucas.lucas.tamagochi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lucas.lucas.tamagochi.Tamagochi.Tamagochi;

public class MainActivity extends AppCompatActivity
{
    private Tamagochi tamagochi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tamagochi = Tamagochi.getInstance();
    }
}
