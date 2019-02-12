package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Game extends ApplicationAdapter {

	Array<Body> bodypartsBase = new Array<Body>();
	Stage stage;

	Snake snake;
	Food food;
	Body body;
	int n;
	int lastScore;
	int templastScore;
	int temp;
	String Score;
	SpriteBatch spriteBatch;
	BitmapFont bitmapFont;
	public Controller controller;
	public boolean deathFlag;


	@Override
	public void create() {

		spriteBatch = new SpriteBatch();
		bitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont2.fnt"),Gdx.files.internal("BitmapFont2.png"), false);

		stage = new Stage(new ScreenViewport());

		snake = new Snake();

		stage.addActor(snake);
		stage.setKeyboardFocus(snake);

		food = new Food();
		stage.addActor(food);
		Score = "Previous Score: " +lastScore;
		temp = templastScore;

		FileHandle file = Gdx.files.local("highscores.txt");

		if (templastScore != 0) {
			file.writeString( " " +templastScore, true);
			Gdx.input.setInputProcessor(stage);}
		}


	public void start(){
		create();

	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(204/255f, 254/255f, 204/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		spriteBatch.begin();
		bitmapFont.setColor(Color.FOREST);
		bitmapFont.draw(spriteBatch, Score, stage.getViewport().getScreenWidth()/2F- bitmapFont.getRegion().getRegionWidth()/8, 400);
		spriteBatch.end();

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		if (snake.sprite.getX()>600f) death();
		if (snake.sprite.getX()<0f) death();
		if (snake.sprite.getY()>400f) death();
		if (snake.sprite.getY()<0f) death();

		int randomX = (int)(Math.random() * 500 + 1);
		int randomY = (int)(Math.random() * 350 + 1);

		boolean isOverlappingFood = snake.rectangleSnake.overlaps(food.rectangleFood);

		if(isOverlappingFood){

			food.sprite.setPosition(randomX, randomY);
			food.rectangleFood.setPosition(food.sprite.getX(),food.sprite.getY());

			body = new Body();
			if ( n == 0 )
				body.snake = snake;
			else
				body.body = bodypartsBase.get(n-1);

			stage.addActor(body);
			bodypartsBase.add(body);

			body.mainsnake = this;

			n++;
			templastScore=n;

			Score = "Score: " + n;
			System.out.println("n = " +n);
			body.n = n;
		}


		for (int i=2; i<bodypartsBase.size; i++ ){
			boolean isOverlappingSnake = snake.rectangleSnake.overlaps(bodypartsBase.get(i).rectangleBody);

			if (isOverlappingSnake){
				death();
				deathFlag = true;
			}
		}
	}

	public void death() {
		snake.sprite.setRotation(0);
		lastScore = n;
		n=0;
		stage.clear();
		bodypartsBase.clear();
		start();
		controller.setGameplay(false);
		controller.setGameStates(Controller.GameStates.UI);
	}
}
