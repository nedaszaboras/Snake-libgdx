package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;

public class Highscore extends ApplicationAdapter {

    public Controller controller;
    Stage stage;
    Skin skin;
    SpriteBatch spriteBatch;
    BitmapFont BitmapFont;
    String Text;
    ArrayList<Integer> top5 = new ArrayList<Integer>();


    @Override
    public void create() {
        super.create();

        FileHandle file = Gdx.files.internal("highscores.txt");
        String text = file.readString();

        top5 = controller.findTopHighscores(text);


        if (top5.size() > 4)    Text = top5.get(0) + "\n"+ top5.get(1) + "\n" +top5.get(2) + "\n" + top5.get(3) + "\n"+ top5.get(4);
        else                    Text = "Not enough \n data to  \n determine highscores \n :^) \n Need at least \n 5 highscores";

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        spriteBatch = new SpriteBatch();
        BitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont2.fnt"), Gdx.files.internal("BitmapFont2.png"), false);
        stage = new Stage(new ScreenViewport());

        final TextButton buttonBack = new TextButton("Back", skin, "default");
        buttonBack.setPosition(225,50);
        buttonBack.setWidth(150);
        buttonBack.setHeight(50);
        buttonBack.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Back");
                controller.setGameplay(false);
                controller.setGameStates(Controller.GameStates.UI);
            }
        });

        stage.addActor(buttonBack);

        Gdx.input.setInputProcessor(stage);
    }



    @Override
    public void render() {

        Gdx.gl.glClearColor(204 / 255f, 254 / 255f, 204 / 255f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        spriteBatch.begin();
        BitmapFont.setColor(Color.GRAY);
        BitmapFont.draw(spriteBatch, Text, 275, 350);
        spriteBatch.end();
        stage.draw();

    }


}
