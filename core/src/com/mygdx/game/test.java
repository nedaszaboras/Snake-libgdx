package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.Collections;

//Entirety of this class is used for testing various methods, it is being modified at all times


public class test extends ApplicationAdapter {

    public ArrayList findTopHighscores(String text)
    {

        ArrayList<Integer> Temp = new ArrayList<Integer>();
        ArrayList<Integer> Temp2 = new ArrayList<Integer>();
        int n = 0;
        int t = 0;
        //System.out.println(text);

        String[] Split = text.split(" ",0);
        for (String i : Split){
            System.out.println(i);
        }
        for (int i=2; i < Split.length; i++)
        {
            int a = Integer.parseInt(Split[i]);
            Temp.add(a);
            System.out.println("Split[i] " +Split[i]);
        }
        //System.out.println("n " +n);

        System.out.println("TEMP PRE sort " +Temp);
        Collections.sort(Temp);
        System.out.println("TEMP POST sort " +Temp);
        ArrayList<Integer> top10 = new ArrayList<Integer>(Temp.subList(Temp.size()-5, Temp.size()));
        System.out.println("Top 5" +top10);
        return Temp;
    }
    @Override
    public void create() {
        FileHandle file = Gdx.files.internal("highscores.txt");
        String text = file.readString();
        System.out.println("top hs " +findTopHighscores(text));
    }

    @Override
    public void render() {

    }


}
