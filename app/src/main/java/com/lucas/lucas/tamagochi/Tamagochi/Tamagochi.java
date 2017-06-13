package com.lucas.lucas.tamagochi.Tamagochi;

/**
 * Created by Lucas on 13/06/2017.
 */
public class Tamagochi
{
    private static Tamagochi instance;
    private String name;
    private int food;
    private int life;


    public static Tamagochi getInstance()
    {
        if (instance == null)
        {
            //instance = new Tamagochi();
        }
        return instance;
    }
    public Tamagochi(String name, int food, int life)
    {
        this.name = name;
        this.food = food;
        this.life = life;
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
}
