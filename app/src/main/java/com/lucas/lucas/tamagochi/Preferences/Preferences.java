package com.lucas.lucas.tamagochi.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lucas on 14/06/2017.
 */
public class Preferences{
    private Context context;
    private static final String KEY_PREFERENCES = "TamagochiUserPreferences";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private final String KEY_NAME = "name";
    private final String KEY_FOOD = "food";
    private final String KEY_LIFE = "life";

    public Preferences(Context c){
        context = c;
        sharedPreferences = context.getSharedPreferences(KEY_PREFERENCES,0);
        editor = sharedPreferences.edit();
    }

    public void saveUserPreferences(String name, int life, int food){
        editor.putString(KEY_NAME,name);
        editor.putInt(KEY_LIFE,life);
        editor.putInt(KEY_FOOD,food);
        editor.apply();
    }

    public String getNamePreferences(){
        String userName = sharedPreferences.getString(KEY_NAME,null);
        return userName;
    }

    public int getFoodPreferences(){
        int food = sharedPreferences.getInt(KEY_FOOD,0);
        return food;
    }

    public int getLifePreferences(){
        int life = sharedPreferences.getInt(KEY_LIFE,0);
        return life;
    }

    public void setLifePreferences(int life){
        editor.putInt(KEY_LIFE,life);
        editor.apply();
    }
    public void setFoodPreferences(int food){
        editor.putInt(KEY_FOOD,food);
        editor.apply();
    }
}
