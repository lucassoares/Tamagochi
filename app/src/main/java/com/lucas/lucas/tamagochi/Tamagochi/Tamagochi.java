package com.lucas.lucas.tamagochi.Tamagochi;

/**
 * Created by Lucas on 13/06/2017.
 */
public class Tamagochi
{
    private static Tamagochi instance;
    private static String name;
    private int food;
    private int life;
    private boolean isDead = true;

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }


    public static Tamagochi getInstance()
    {
        if (instance == null)
        {
            instance = new Tamagochi();
        }
        return instance;
    }

    public String getName()
    {
        return name;
    }

    public int getFood()
    {
        return food;
    }

    public int getLife()
    {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setName(String name) {
        Tamagochi.name = name;
    }
}
