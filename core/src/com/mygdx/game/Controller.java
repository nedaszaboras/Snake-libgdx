package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import java.util.ArrayList;
import java.util.Collections;

public class Controller extends ApplicationAdapter {

    enum GameStates {
        Highscores,
        Mainsnake,
        UI
    }

    private Game game;
    private UI ui;
    private Highscore highscore;
    public boolean isHighscores;
    public boolean isGameplay;
    public int lastScore;

    public GameStates State = GameStates.UI;

    public void setGameStates(GameStates state){
        State = state;

        if (State == GameStates.Mainsnake)  game.create();
        if (State == GameStates.Highscores) highscore.create();
        if (State == GameStates.UI) ui.create();

    }

    public void setGameplay(boolean isgameplay) {
        isGameplay = isgameplay;
    }

    public void setHighscore(boolean ishighscores) {
        isHighscores = ishighscores;
    }

    public int findTopHighscore(String text) {

        ArrayList<Integer> highscore = findTopHighscores(text);

        if ( highscore.size() > 0 )
            return highscore.get(0);
        return 0;
    }

    public ArrayList<Integer> findTopHighscores(String text)
    {
        ArrayList<Integer> Temp = new ArrayList<Integer>();

        String[] Split = text.split(" ",0);

        for (int i=2; i < Split.length; i++)
        {
            int a = Integer.parseInt(Split[i]);
            Temp.add(a);
        }

        Collections.sort(Temp);

        if (Temp.size()> 4) {
            ArrayList<Integer> top5 = new ArrayList<Integer>(Temp.subList(Temp.size() - 5, Temp.size()));
            Collections.reverse(top5);
            //System.out.println("Top 5" + top5);
            return top5;
        }

        else return Temp;
    }

    @Override
    public void create() {

        super.create();

        ui = new UI();
        ui.controller = this;

        game = new Game();
        game.controller = this;

        highscore = new Highscore();
        highscore.controller = this;

        if (State == GameStates.UI) ui.create();
        if (State == GameStates.Highscores) highscore.create();
        if (State == GameStates.Mainsnake)  {
            game.create();
        }
        if (State == GameStates.Mainsnake)  {
            ui.create();
        }

        System.out.println("State " +State);

    }


    @Override
    public void render() {
        lastScore = game.n;

        if (State == GameStates.UI) ui.render();
        if (State == GameStates.Highscores) highscore.render();
        if (State == GameStates.Mainsnake) game.render();

    }
}

