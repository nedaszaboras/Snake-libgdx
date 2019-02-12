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
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class UI extends ApplicationAdapter {

    public Controller controller;

    Stage stage;
    Skin skin;
    SpriteBatch spriteBatch;
    BitmapFont BitmapFont;
    String score;
    int n =0;
    boolean lastState;

    @Override
    public void create() {

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        spriteBatch = new SpriteBatch();
        BitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont2.fnt"), Gdx.files.internal("BitmapFont2.png"), false);
        stage = new Stage(new ScreenViewport());

        final TextButton buttonExit = new TextButton("Exit", skin, "default");
        buttonExit.setPosition(215,150);
        buttonExit.setWidth(160);
        buttonExit.setHeight(50);
        buttonExit.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Exit");
                Gdx.app.exit();
            }
        });

        final TextButton buttonHighscores = new TextButton("Highscores", skin, "default");
        buttonHighscores.setPosition(215,200);
        buttonHighscores.setWidth(160);
        buttonHighscores.setHeight(50);
        buttonHighscores.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Highscores");
                controller.setHighscore(true);
                controller.setGameStates(Controller.GameStates.Highscores);
            }
        });

        final TextButton buttonStart = new TextButton("Start", skin, "default");
        final Dialog dialog = new Dialog("Click message", skin);
        buttonStart.setPosition(215,250);
        buttonStart.setWidth(160);
        buttonStart.setHeight(50);
        buttonStart.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                System.out.println("Start");
                controller.setGameplay(true);
                lastState = controller.isGameplay;
                controller.setGameStates(Controller.GameStates.Mainsnake);


            }
        });
        if (lastState) buttonStart.setText("Play again");

        stage.addActor(buttonHighscores);
        stage.addActor(buttonExit);
        stage.addActor(buttonStart);

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(204 / 255f, 254 / 255f, 204 / 255f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        spriteBatch.begin();
        FileHandle file = Gdx.files.internal("highscores.txt");
        String text = file.readString();
        score = "Highest score: " + controller.findTopHighscore(text);
        BitmapFont.setColor(Color.FOREST);
        BitmapFont.draw(spriteBatch, score, 200, 350);
        spriteBatch.end();
        stage.draw();
        n = controller.lastScore;
    }
}
